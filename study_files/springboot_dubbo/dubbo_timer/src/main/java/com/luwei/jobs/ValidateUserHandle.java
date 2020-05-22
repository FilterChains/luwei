package com.luwei.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @projectName： springbootdubbo
 * @packageName: com.dubbo.timer.jobs
 * @auther: luwei
 * @description: fixedRate：上一次 启动时间点之后 X秒执行一次
 * fixedDelay： 上一次 结束时间点之后 每X秒执行一次
 * initialDelay： 第一次延迟 X秒执行，之后按照fixedRate的规则每X秒执行
 * @date: 2020/2/2 21:15
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Component
public class ValidateUserHandle {

    private static long count=1;
    private static long cot=1;

    @Scheduled(fixedDelay = 60 * 1000)
    public void timerToZZP(){
        System.out.println("执行:"+count+"次"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        count++;
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void timerTest(){
        System.out.println("执行:"+cot+"次"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        cot++;
    }
}
