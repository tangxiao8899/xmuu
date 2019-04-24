package com.carryit.base.xmuu.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdRoyaltyTask {

    //每月最后一天23点59分59秒执行定时任务
    @Scheduled(cron="59 59 23 L * ?")
    private void configureTasks() {
        System.err.println("=========================开始执行广告分红=========================");
        //TODO
        //每月一号执行上月广告分红

        System.out.println("=========================广告分红执行结束=========================");
    }
}
