package com.user.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    private static String lin = "https://www.cnblogs.com/ysocean/p/10541151.html" + "分布式任务调度平台XXL-JOB搭建教程";

    // 排队总人数（请求总数）
    public static final int clientTotal = 10;

    // 可同时受理业务的窗口数量（同时并发执行的线程数）
    public static final int threadTotal = 10;


    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire(1);
                    resolve(count);
                    semaphore.release(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void resolve(int i) throws InterruptedException {
        System.out.println("正在执行任务:" + i);
        Thread.sleep(2000);
    }
}
