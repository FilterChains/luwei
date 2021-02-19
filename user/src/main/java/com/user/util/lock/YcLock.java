package com.user.util.lock;

import java.util.concurrent.CountDownLatch;

public class YcLock {

    private static volatile int status = 0;


    private static CountDownLatch countDownLatch = new CountDownLatch(10);
    private static CountDownLatch countDown = new CountDownLatch(1);

    private static int num = 1;


    public static synchronized void lock() {
        while (status == 1) {
            System.out.println("当前线程:" + Thread.currentThread().getName() + "已被阻塞");
        }
        status = 1;
    }

    public static void unLock() {
        status = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "将要执行");
                    System.out.println("NUM:" + num);
                    countDownLatch.countDown();
                    countDown.await();
                    printMsg(5000);
                    System.out.println(Thread.currentThread().getName() + "执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "线程" + i).start();
        }

        countDownLatch.await();
        countDown.countDown();
        System.out.println("NUM:" + num);
    }


    public static void printMsg(long lg) throws InterruptedException {
        lock();
        // Thread.sleep(lg);
        String name = Thread.currentThread().getName();
        System.out.println("currentThread: [" + name + "] doing now");
        num++;
        unLock();
    }
}
