package com.luwei.util;

import java.util.concurrent.*;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.util
 * @auther: luwei
 * @description: 线程并发模拟
 * @date: 2020/3/9 20:20
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class ConcurrencyThreadDemo {

    private static final int len = 10;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 25, 2L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(5));

        /**
         * CountDownLatch的字面意思：倒计时 门栓
         * 它的功能是：让一些线程阻塞直到另一些线程完成一系列操作后才唤醒。
         * 它通过调用await方法让线程进入阻塞状态等待倒计时0时唤醒。
         * 它通过线程调用countDown方法让倒计时中的计数器减去1，当计数器为0时，
         * 会唤醒哪些因为调用了await而阻塞的线程。
         * alert:不可复用,做减法
         */
        // 创建一个倒计时门栓
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < len; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("CountDownLatch:"+Thread.currentThread().getName() + "->等待执行");
                    countDownLatch.countDown(); //开始倒计时，调用一次见一次
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            countDownLatch.await();
            System.err.println("CountDownLatch:开始执行。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        /**
         * CyclicBarrier [ˈsaɪklɪk] [ˈbæriər] 的字面意思：可循环使用的屏障 【栅栏】
         * 它的功能是：让一组线程到达一个屏障时被阻塞，知道最后一个线程到达屏障，
         * 所有被屏障拦截的线程才会继续执行。它通过调用await方法让线程进入屏障
         * alert:做加法
         */

        // 创建CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("CyclicBarrier:开始");

        });

        for (int i = 0; i < len; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("CyclicBarrier:"+Thread.currentThread().getName() + "->等待执行");
                    cyclicBarrier.await();
                    Thread.sleep(3000);
                    System.err.println("CyclicBarrier:开始执行");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        /**
         * Semaphor [ˈseməfɔːr] 信号量的意思；
         * 它主要用于两个目的：
         * 用于多个共享资源互斥使用。【也就是具有锁的功能】
         * 一个停车场有多个停车位，这多个停车位对应汽车来说就是多个共享资源，
         * Semaphor可以实现多个停车位和多个汽车之间的互斥。
         * 用于控制并发线程数。(就是控制同时得到共享资源的线程数量)
         * 在创建Semaphor时可以指定并发线程数，
         * //permits :允许
         * public Semaphore(int permits) {
         *     sync = new NonfairSync(permits);
         * }
         * //还可以指定是否为公平锁
         * public Semaphore(int permits, boolean fair) {
         *     sync = fair ? new FairSync(permits) : new NonfairSync(permits);
         * }
         * 信号量为1时 相当于独占锁 信号量大于1时相当于共享锁。
         */
        // 创建信号量
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < len; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire(); //获取信号量
                    System.err.println("Semaphore:"+Thread.currentThread().getName() + "->开始执行");
                    Thread.sleep(3000);
                    semaphore.release(); // 释放信号量
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}
