package com.dubbo.common.util;

import com.google.common.collect.Lists;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @projectName： springbootdubbo
 * @packageName: com.dubbo.common.util
 * @auther: luwei
 * @description:
 * @date: 2020/2/14 15:59
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class ThreaPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture.runAsync(()->System.out.println(validateFuntion()),executorService);
        CompletableFuture.runAsync(()->System.out.println(validataFun()),executorService);
        executorService.shutdown();
    }

    private static String validateFuntion(){
        long start = System.currentTimeMillis();
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //模拟数据操作
        CompletableFuture<String> supplyAsync1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    System.out.println("开始执行任务一:" + System.currentTimeMillis());
                    Thread.sleep(5000);
                    System.out.println("任务一执行完毕====");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "执行完毕：任务一";
            }
        }, executorService);
        CompletableFuture<String> supplyAsync2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    System.out.println("开始执行任务二:" + System.currentTimeMillis());
                    Thread.sleep(3000);
                    System.out.println("任务二执行完毕====");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "执行完毕：任务二";
            }
        }, executorService);
        CompletableFuture<String> supplyAsync3 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    System.out.println("开始执行任务三:" + System.currentTimeMillis());
                    Thread.sleep(3000);
                    System.out.println("任务三执行完毕====");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "执行完毕：任务三";
            }
        }, executorService);

        try {
            long startTimes1 = System.currentTimeMillis();
            String result1 = supplyAsync1.get();
            System.err.println("获取时间:"+(System.currentTimeMillis()-startTimes1));
            System.out.println("执行结果："+result1);

            long startTimes2 = System.currentTimeMillis();
            String result2 = supplyAsync2.get();
            System.err.println("获取时间:"+(System.currentTimeMillis()-startTimes2));
            System.out.println("执行结果："+result2);

            long startTimes3 = System.currentTimeMillis();
            String result3 = supplyAsync3.get();
            System.err.println("获取时间:"+(System.currentTimeMillis()-startTimes3));
            System.out.println("执行结果："+result3);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
            try {
                //如果大于规定时间
                if(!executorService.awaitTermination(2*1000,TimeUnit.MILLISECONDS)){
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                executorService.shutdownNow();
            }
        }
        return "总的执行时间:"+(System.currentTimeMillis()-start);
    }

    private static String validataFun(){
        long start = System.currentTimeMillis();
        try {
            System.out.println("开始执行任务一:" + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("任务一执行完毕====");
            System.out.println("开始执行任务二:" + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("任务二执行完毕====");
            System.out.println("开始执行任务三:" + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("任务三执行完毕====");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "执行总时间:"+(System.currentTimeMillis()-start);
    }
}
