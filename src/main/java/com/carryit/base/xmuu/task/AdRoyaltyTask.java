package com.carryit.base.xmuu.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdRoyaltyTask {

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
        //TODO
        //统计当月广告总收益

        //统计当月同城商家净利润

        //统计当月该区域会员升级数总额

        System.out.println("=========================收益统计执行结束=========================");
    }

    //每月1号凌晨2点分发收益
    @Scheduled(cron="59 59 23 L * ?")
    private void configureTasks() {
        System.err.println("=========================开始执行收益分红=========================");
        //TODO
        //分发广告收益，【高级VIP30%、钻石VIP10%】



        //分发同城商家净利润【钻石VIP15%、管理员15%、副圈主10%、常务圈主5%、  UU圈主5%】


        //分发该区域的会员升级数总额【副区长2.5%、区长5%、资源对接人0.5%】

        //分发该区域该圈子的会员升级数总额【钻石VIP(该圈子前250名)10%、管理员（前50）10%、副圈主（前10）10%、常务圈主（前2）5%、  UU圈主（1个）5%】

        System.out.println("=========================收益分红执行结束=========================");
    }
}
