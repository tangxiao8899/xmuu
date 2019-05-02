package com.carryit.base.xmuu.task;

import com.carryit.base.xmuu.entity.ImsUserCapitalFlowEntity;
import com.carryit.base.xmuu.entity.Member;
import com.carryit.base.xmuu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
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
    @Scheduled(cron="59 59 23 L * ?")
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
    @Scheduled(cron="0 0 2 1 * ? *")
    private void configureTasks() {
        System.err.println("=========================开始执行收益分红=========================");

        //待分红的会员
        List<Member> gList =  memberService.getMemberForLevel(GVIP);//高级VIP会员

        int person_gvip = gList.size();//高级VIP人数

        List<Member> zhList = memberService.getMemberForLevel(ZSVIP); //钻石VIP

        int person_zhvip = zhList.size(); //钻石VIP人数

        List<Member> glList = memberService.getMemberForLevel(UUGLY); //管理员

        int person_uugly = glList.size(); //管理员人数

        List<Member> fqzList = memberService.getMemberForLevel(FQZ); //副圈主

        int person_gqz = fqzList.size(); //副圈主人数

        List<Member> cwList =  memberService.getMemberForLevel(CWQZ); //常务圈主

        int person_cwqz = cwList.size(); //常务圈主人数

        List<Member> uuList = memberService.getMemberForLevel(UUQZ); //UU圈主

        int person_uuqz = uuList.size(); //UU圈主人数


        //TODO
        //分发广告收益，【高级VIP30%、钻石VIP10%】
        adShare(gList, person_gvip, zhList, person_zhvip);


        //分发同城商家净利润【钻石VIP15%、管理员15%、副圈主10%、常务圈主5%、  UU圈主5%】
        //this.merchantProfitStatisticsService.sameCityMoney();


        //分发该区域的会员升级数总额【副区长2.5%、区长5%、资源对接人0.5%】

        //分发该区域该圈子的会员升级数总额【钻石VIP(该圈子前250名)10%、管理员（前50）10%、副圈主（前10）10%、常务圈主（前2）5%、  UU圈主（1个）5%】

        //this.memberUpgradeStatisticsService.areaCircleUpgradeMoney();

        System.out.println("=========================收益分红执行结束=========================");
    }

    /**
     * 广告分红
     * @param gList
     * @param person_gvip
     * @param zhList
     * @param person_zhvip
     */
    private void adShare(List<Member> gList, int person_gvip, List<Member> zhList, int person_zhvip) {
        Double adMoney = this.adStatisticsService.adMoneyForMonth(); //当月广告总收益
        //高级VIP每人可分得的收益
        double ad_gj_money =0.3*adMoney/person_gvip;
        for(Member m : gList){
            //更新账户金额
            int uid = m.getUid();
            Float f = m.getCredit2() + (float)ad_gj_money;
            memberService.updateMemberByUid(uid,f);

            //记录分红流水
            recordShareFlow(ad_gj_money, m,4);
        }

        //钻石VIP每人可分得的收益
        double ad_zs_money = 0.1 * adMoney/person_zhvip;
        for(Member m : zhList){
            int uid = m.getUid();
            Float f = m.getCredit2() + (float)ad_zs_money;
            memberService.updateMemberByUid(uid,f);

            //记录分红流水
            recordShareFlow(ad_gj_money, m,4);
        }
    }

    /**记录分红流水
     *
     * @param ad_gj_money
     * @param m
     * @param type 分红类型  4：广告分红  5：商家分红 6：区域会员升级分红  7：圈子会员升级分红
     */
    private void recordShareFlow(double ad_gj_money, Member m,int type) {
        ImsUserCapitalFlowEntity entity = new ImsUserCapitalFlowEntity();
        entity.setUid(m.getUid());
        entity.setPrice(ad_gj_money);
        entity.setType(0);  //分红收入
        entity.setSource(type); //广告分红
        imsUserCapitalFlowService.save(entity);
    }
}
