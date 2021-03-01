package com.user.util.lock;

public class TestDemo {

    private static Object obj1 = "obj1";
    private static Object obj2 = "obj2";

    public static void main(String[] args) {
        System.out.println("死锁Demo");
        Thread thread = new Thread(() -> {
            synchronized (obj2) {
                System.out.println(Thread.currentThread().getName() + "拿到了锁：" + obj2);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }, "线程一");

        Thread thread1 = new Thread(() -> {
            synchronized (obj1) {
                System.out.println(Thread.currentThread().getName() + "拿到了锁：" + obj1);
                synchronized (obj2) {
                    try {
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程二");

        thread.start();
        thread1.start();
    }

}
