package com.user.util.lock;

import org.springframework.util.StopWatch;

public class Interrupet {

    public static void main(String[] args) throws InterruptedException {

//        Thread thread = new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                if (i > 2000 && Thread.currentThread().isInterrupted()) {
//                    break;
//                }
//                System.out.println(i);
//            }
//            System.out.println("执行到了最后面了");
//        }, "线程1======");
//
//        thread.start();
//        thread.join();
//        Thread.yield();
//        Thread.sleep(10);
//        // thread.stop();
//        thread.interrupt();
//
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES,
//                new LinkedBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
//        try {
//            executor.execute(() -> {
//                try {
//                    Thread.sleep(3000);
//                    System.out.println("你好");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            System.out.println(executor.isTerminated());
//            executor.shutdown();
//        } catch (Exception w) {
//            executor.shutdownNow();
//            Integer.valueOf(1).equals(1);
//        }
//        System.out.println("isTerminated:=====> " + executor.isTerminated());
//        System.out.println("isShutdown:===== >" + executor.isShutdown());
//        System.out.println("isTerminating:=====> " + executor.isTerminating());

        StopWatch stopWatch = new StopWatch();
        System.out.println("开始执行");
        stopWatch.start("开始任务一");
        Thread.sleep(3000L);
        System.out.println("执行结束");
        stopWatch.stop();
        stopWatch.start("开始任务二");
        Thread.sleep(2000L);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

}
