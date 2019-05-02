package com.carryit.base.xmuu.task;

import com.carryit.base.xmuu.XmuuApplication;
import com.carryit.base.xmuu.entity.ImsUserCapitalFlowEntity;
import com.carryit.base.xmuu.entity.Member;
import com.carryit.base.xmuu.service.*;
import com.util.PropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;


@Component
@PropertySource("classpath:cron.props")
public class AdRoyaltyTask {

    @Autowired
    AdStatisticsService adStatisticsService;

    @Autowired
    MemberUpgradeStatisticsService memberUpgradeStatisticsService;

    @Autowired
    MerchantProfitStatisticsService merchantProfitStatisticsService;

    @Autowired
    MemberService memberService;

    @Autowired
    ImsUserCapitalFlowService imsUserCapitalFlowService;

    private static final int UUQZ = 0;//UU圈主
    private static final int CWQZ = 1;//常务圈主
    private static final int FQZ = 2;//副圈主
    private static final int UUGLY = 3;//UU管理员
    private static final int GVIP = 4;//高级VIP
    private static final int MVIP = 5;//梦想VIP
    private static final int ZSVIP = 6; //钻石VIP

    //每月最后一天23点59分59秒执行定时任务,统计不同收益
    @Scheduled(cron = "${jobs.schedule}")
    private void statisticsTasks() {
        System.err.println("=========================开始执行收益统计=========================");
        //统计当月广告总收益
        adStatisticsService.statisticsAdMoneyForMonth();
        //统计当月同城商家净利润
        merchantProfitStatisticsService.statisticsAdMoneyForMonth();
        //统计当月该区域会员升级数总额
        memberUpgradeStatisticsService.statisticsAdMoneyForMonth();

        System.out.println("=========================收益统计执行结束=========================");
    }

    //每月1号凌晨2点分发收益
    @Scheduled(cron = "0 0 2 1 * ? ")
    private void configureTasks() {
        System.err.println("=========================开始执行收益分红=========================");

        //待广告分红的会员
        List<Member> gList = memberService.getMemberForLevel(GVIP);//高级VIP会员
        List<Member> zhList = memberService.getMemberForLevel(ZSVIP); //钻石VIP

        //分发广告分红，【高级VIP30%、钻石VIP10%】
        adShare(gList, zhList);


        //分发同城商家净利润【钻石VIP15%、管理员15%、副圈主10%、常务圈主5%、  UU圈主5%】
        List<Member> merchant_list = memberService.getMemberCity();
        for (Member m : merchant_list) {
            String city = m.getCity();
            //所有的城市
            Double tc_merchant_money = merchantProfitStatisticsService.sameCityMoney(city);

            //同城钻石vip
            shareSameCityMerchantMoney(Double.valueOf(PropertyUtil.getProperty("merchant_zsvip")), city, tc_merchant_money, ZSVIP);

            //同城的管理员
            shareSameCityMerchantMoney(Double.valueOf(PropertyUtil.getProperty("merchant_uugly")), city, tc_merchant_money, UUGLY);

            //同城的副圈主
            shareSameCityMerchantMoney(Double.valueOf(PropertyUtil.getProperty("merchant_fqz")), city, tc_merchant_money, FQZ);

            //同城的常务圈主
            shareSameCityMerchantMoney(Double.valueOf(PropertyUtil.getProperty("merchant_cwqz")), city, tc_merchant_money, CWQZ);

            //同城的UU圈主
            shareSameCityMerchantMoney(Double.valueOf(PropertyUtil.getProperty("merchant_uuqz")), city, tc_merchant_money, UUQZ);
        }

        //分发该区域的会员升级数总额【副区长2.5%、区长5%、资源对接人0.5%】

        //分发该区域该圈子的会员升级数总额【钻石VIP(该圈子前250名)10%、管理员（前50）10%、副圈主（前10）10%、常务圈主（前2）5%、  UU圈主（1个）5%】
        List<Member> qz_list = memberService.getMemberByCityAndQz();

        for (Member m : qz_list) {
            String city = m.getCity();
            int zhuquanzi = m.getZhuquanzi();
            Double qz_money = this.memberUpgradeStatisticsService.areaCircleUpgradeMoney(city, String.valueOf(zhuquanzi));

            //钻石VIP
            shareQzMoney(Double.valueOf(PropertyUtil.getProperty("qz_zsvip")), city, zhuquanzi, qz_money, ZSVIP);
            //管理员
            shareQzMoney(Double.valueOf(PropertyUtil.getProperty("qz_uugly")), city, zhuquanzi, qz_money, UUGLY);
            //副圈主
            shareQzMoney(Double.valueOf(PropertyUtil.getProperty("qz_fqz")), city, zhuquanzi, qz_money, FQZ);
            //常务圈主
            shareQzMoney(Double.valueOf(PropertyUtil.getProperty("qz_cwqz")), city, zhuquanzi, qz_money, CWQZ);
            //UU圈主
            shareQzMoney(Double.valueOf(PropertyUtil.getProperty("qz_uuqz")), city, zhuquanzi, qz_money, UUQZ);

        }


        System.out.println("=========================收益分红执行结束=========================");
    }

    /**
     * 同城通圈子分红
     *
     * @param city      城市
     * @param zhuquanzi 圈子
     * @param qz_money  同城同圈子总分红金额
     */
    private void shareQzMoney(double rate, String city, int zhuquanzi, Double qz_money, Integer level) {
        List<Member> list = memberService.getMemberByCityAndQzAndLevel(city, zhuquanzi, level);
        //每人分得金额
        double qz_share_money = rate * qz_money / list.size();
        for (Member m : list) {
            //更新账户金额
            int uid = m.getUid();
            Float f = m.getCredit2() + (float) qz_share_money;
            memberService.updateMemberByUid(uid, f);
            //记录分红流水
            recordShareFlow(qz_share_money, m, 7);
        }
    }

    /**
     * 同城商家收益分红
     *
     * @param city              城市
     * @param tc_merchant_money 同城总的可分配收益
     */
    private void shareSameCityMerchantMoney(double rate, String city, Double tc_merchant_money, int level) {
        List<Member> merchant_list = memberService.getMemberByCityAndLevel(city, level);

        //高级VIP每人可获得收益
        double merchant_money = rate * tc_merchant_money / merchant_list.size();
        for (Member gm : merchant_list) {
            //更新账户金额
            int uid = gm.getUid();
            Float f = gm.getCredit2() + (float) merchant_money;
            memberService.updateMemberByUid(uid, f);
            //记录分红流水
            recordShareFlow(merchant_money, gm, 5);
        }

    }

    /**
     * 广告分红
     *
     * @param gList  高级VIP会员集合
     * @param zhList 钻石VIP会员集合
     */
    private void adShare(List<Member> gList, List<Member> zhList) {
        Double adMoney = this.adStatisticsService.adMoneyForMonth(); //当月广告总收益
        //高级VIP每人可分得的收益
        shareAdMoney(gList, adMoney, Double.valueOf(PropertyUtil.getProperty("ad_gvip")));

        //钻石VIP每人可分得的收益
        shareAdMoney(zhList, adMoney, Double.valueOf(PropertyUtil.getProperty("ad_zsvip")));
    }

    /**
     * 广告分红
     *
     * @param list    会员集合
     * @param adMoney 广告总收益
     * @param v       分红比例
     */
    private void shareAdMoney(List<Member> list, Double adMoney, double v) {
        double ad_money = v * adMoney / list.size();
        for (Member m : list) {
            //更新账户金额
            int uid = m.getUid();
            Float f = m.getCredit2() + (float) ad_money;
            memberService.updateMemberByUid(uid, f);

            //记录分红流水
            recordShareFlow(ad_money, m, 4);
        }
    }

    /**
     * 记录分红流水
     *
     * @param ad_gj_money 广告收益
     * @param m           会员
     * @param type        分红类型  4：广告分红  5：商家分红 6：区域会员升级分红  7：圈子会员升级分红
     */
    private void recordShareFlow(double ad_gj_money, Member m, int type) {
        ImsUserCapitalFlowEntity entity = new ImsUserCapitalFlowEntity();
        entity.setUid(m.getUid());
        entity.setPrice(ad_gj_money);
        entity.setType(0);  //分红收入
        entity.setSource(type); //广告分红
        imsUserCapitalFlowService.save(entity);
    }
}
