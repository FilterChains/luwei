package com.user.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @projectName： springbootdubbo
 * @packageName: com.dubbo.common.util
 * @auther: luwei
 * @description:
 * @date: 2020/2/11 10:55
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class ThreadDemo {

    private static final int PAGE_SIZE = 3000;

    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        // 任务1
        pool.execute(() -> {
            try {
                Thread.sleep(3 * 1000);
                System.out.println("--helloWorld_001--" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //任务2
        pool.execute(() -> System.out.println("--helloWorld_002--" + Thread.currentThread().getName()));
        pool.shutdown();

        //创建固定核心数的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(Math.max(Runtime.getRuntime().availableProcessors() + 1, 2));
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        System.out.println("即将被执行的数据:"+list);

        /*CompletableFuture.runAsync(() ->subList(list) ,threadPool);
        CompletableFuture.runAsync(() ->subList(list) ,threadPool);*/
        long timeMillis = System.currentTimeMillis();
        System.out.println("执行开始时间："+timeMillis);
        int len = list.size();
        int length = (len / PAGE_SIZE) + (len % PAGE_SIZE > 0 ? 1 : 0);
        for (int j = 1; j <= length; j++) {
            int finalJ = j;
            CompletableFuture.runAsync(() ->list.subList((finalJ - 1) * PAGE_SIZE, (finalJ * PAGE_SIZE) > len ? len : (finalJ * PAGE_SIZE)) ,threadPool);
            /*CompletableFuture<List<Integer>> supplyAsync = CompletableFuture.supplyAsync(new Supplier<List<Integer>>() {
                @Override
                public List<Integer> get() {
                    //一步窃取数据源信息
                    return list.subList((finalJ - 1) * PAGE_SIZE, (finalJ * PAGE_SIZE) > len ? len : (finalJ * PAGE_SIZE));
                }
            }, threadPool);
            try {
                System.err.println("线程抢占："+supplyAsync.get());
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }
        threadPool.shutdown();
        System.out.println("执行花费时间:"+(System.currentTimeMillis()-timeMillis));
    }

   /* private static void  subList(List<Integer> list){
        int len = list.size();
        int length = (len / PAGE_SIZE) + (len % PAGE_SIZE > 0 ? 1 : 0);
        for (int j = 1; j <= length; j++) {
            //一步窃取数据源信息
            List<Integer> subList = list.subList((j - 1) * PAGE_SIZE, (j * PAGE_SIZE) > len ? len : (j * PAGE_SIZE));
            System.out.println("线程抢占："+subList);
        }
    }
*/
}
