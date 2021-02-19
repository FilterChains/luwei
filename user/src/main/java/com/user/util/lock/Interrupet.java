package com.user.util.lock;

public class Interrupet {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                if (i > 2000 && Thread.currentThread().isInterrupted()) {
                    break;
                }
                System.out.println(i);
            }
            System.out.println("执行到了最后面了");
        }, "线程1======");

        thread.start();
        thread.join();
        Thread.yield();
        Thread.sleep(10);
        // thread.stop();
        thread.interrupt();

//        ThreadPoolExecutor executor = new ThreadPoolExecutor();
//        executor.shutdown();
//        executor.shutdownNow();

    }
}
