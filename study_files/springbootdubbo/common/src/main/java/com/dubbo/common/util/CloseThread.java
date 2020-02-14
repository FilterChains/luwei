package com.dubbo.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @projectName： springbootdubbo
 * @packageName: com.dubbo.common.util
 * @auther: luwei
 * @description: 关闭线程池的正确操作，一般场景适用
 * @date: 2020/2/11 12:00
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class CloseThread {
    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(5);
        final long waitTime = 8 * 1000;
        final long awaitTime = 5 * 1000;

        Runnable task1 = new Runnable() {
            public void run() {
                try {
                    System.out.println("线程1开始");
                    Thread.sleep(waitTime);
                    System.out.println("线程1结束");
                } catch (InterruptedException e) {
                    System.out.println("task1 interrupted: " + e);
                }
            }
        };

        Runnable task2 = new Runnable() {
            public void run() {
                try {
                    System.out.println("线程2开始");
                    Thread.sleep(2000);
                    System.out.println("线程2结束");
                } catch (InterruptedException e) {
                    System.out.println("task2 interrupted: " + e);
                }
            }
        };
        // 执行线程1
        pool.execute(task1);

        // 循环执行线程2
        for (int i = 0; i < 1000; ++i) {
            pool.execute(task2);
        }

        try {
            // 向线程池发起关闭线程池信号
            pool.shutdown();

            // 向线程池发送在规定时间内，未执行完成的终止执行
            if (!pool.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)) {
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            // awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
            System.out.println("awaitTermination interrupted: " + e);
            pool.shutdownNow();
        }

        System.out.println("end");

    }
}
