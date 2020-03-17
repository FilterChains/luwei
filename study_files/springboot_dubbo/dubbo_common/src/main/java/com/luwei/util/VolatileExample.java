package com.luwei.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>@description : volatile 的使用 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/3/12 19:29 </p>
 */
public class VolatileExample {

    public static void main(String[] args){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(15, 25, 2L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(5),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        final ThreadTest build = ThreadTest.builder().flag(true).build();

        for (int i = 0; i < 20; i++) {
            executor.execute(()->{
                if (build.isFlag()) {
                    build.setFlag(false);
                    System.out.println("开始执行"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("线程没执行");
                }
            });

            executor.execute(()->{
                if (build.isFlag()) {
                    System.err.println("开始执行"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.err.println("线程没执行");
                }
            });
        }


        executor.shutdown();
    }

}
