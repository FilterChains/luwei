package com.luwei.util;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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
        //System.out.println(validateFuntion());
        /*ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture.runAsync(()->System.out.println(validateFuntion()),executorService);
        CompletableFuture.runAsync(()->System.out.println(validataFun()),executorService);
        executorService.shutdown();*/
        //System.out.println("开启线程:"+threadPoolsize());
        //SycThread();
        //sysEruptThread();
        //String str = ",1,2,3,4,5,6,7,8,";
        //System.out.println(StringConversion(str));
        threadDemo();
    }

    private static String validateFuntion(){
        long start = System.currentTimeMillis();
        //创建线程池
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorService executorService = Executors.newCachedThreadPool();
        //模拟数据操作
        CompletableFuture<String> supplyAsync1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    System.out.println("开始执行任务一:" + System.currentTimeMillis());
                    Thread.sleep(3000);
                    System.out.println("任务一执行完毕====");
                } catch (InterruptedException e) {
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
                }
                return "执行完毕：任务二";
            }
        }, executorService);

        CompletableFuture<String> supplyAsync3 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    System.out.println("开始执行任务三:" + System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("任务三执行完毕====");
                } catch (InterruptedException e) {
                }
                return "执行完毕：任务三";
            }
        }, executorService);
        try {
            System.out.println("报异常后1：======");
            long startTimes1 = System.currentTimeMillis();
            String result1 = supplyAsync1.get(3000,TimeUnit.MILLISECONDS);
            System.err.println("获取时间1:"+(System.currentTimeMillis()-startTimes1));
            System.out.println("执行结果："+result1);

            long startTimes2 = System.currentTimeMillis();
            String result2 = supplyAsync2.get(3000,TimeUnit.MILLISECONDS);
            System.err.println("获取时间2:"+(System.currentTimeMillis()-startTimes2));
            System.out.println("执行结果："+result2);

            long startTimes3 = System.currentTimeMillis();
            String result3 = supplyAsync3.get(3000,TimeUnit.MILLISECONDS);
            System.err.println("获取时间3:"+(System.currentTimeMillis()-startTimes3));
            System.out.println("执行结果："+result3);
        } catch (Exception e) {
            System.out.println("报异常后2：======");
        }finally {
            executorService.shutdownNow();
            System.out.println("最后执行3：======");
        }
        return "总的执行时间:"+(System.currentTimeMillis()-start);
    }

    private static String validataFun(){
        long start = System.currentTimeMillis();
        try {
            System.out.println("开始执行任务一:" + System.currentTimeMillis()+"==============");
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


    private static String createThread(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,3,60L,TimeUnit.SECONDS,new LinkedBlockingQueue<>(1));
        executor.execute(()->{
                    try {
                        Thread.sleep(10000);
                        System.err.println("线程正在执行第一个"+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
        System.err.println("是不是异步");
        ThreadFactory threadFactory = executor.getThreadFactory();
        threadFactory.newThread(()->
        {
            try {
                Thread.sleep(9000);
                System.err.println("创建新线程第二个："+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        executor.shutdown();
        System.out.println("直接执行，是不是异步");
        return "success";
    }

    private static String threadPoolsize(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3,
                2L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3));
        //阻塞队列有界 ArrayList 无界LinkedList 无界 优先。。。
        executor.execute(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1"+Thread.currentThread().getName());
        });
        executor.execute(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2"+Thread.currentThread().getName());
        });
        threadTransfer(executor);
        executor.execute(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程3"+Thread.currentThread().getName());
        });
        executor.execute(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程4"+Thread.currentThread().getName());
        });
        executor.execute(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程5"+Thread.currentThread().getName());
        });
        executor.shutdown();
        return "成功";

    }


    private static void SycThread(){

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 8, 1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        CountDownLatch startCountDownLatch = new CountDownLatch(1);
        CountDownLatch endCountDownLatch = new CountDownLatch(10);

        for (int i = 1; i <= 10; i++) {
            executor.execute(()->{
                try {
                    System.out.println("等待发出并发信号:>>>2");
                    //startCountDownLatch.await();
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+"开始执行程序:>>>3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endCountDownLatch.countDown();
                }
            });
        }

        try {
            System.out.println("开始向并发程序发出执行信号:>>>1");
            //startCountDownLatch.countDown();
            endCountDownLatch.await();
            System.out.println("并发开始执行:>>>4");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }


    private static void sysEruptThread(){
        // 模拟同步请求
        Semaphore semaphore = new Semaphore(1);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(100,()->{
            System.out.println("并发执行完毕");
        });
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    Thread.sleep(3000);
                    cyclicBarrier.await(); //等待屏障
                    semaphore.acquire();
                    System.err.println(Thread.currentThread().getName()+"正在执行"+ finalI);
                    Thread.sleep(2000);
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"执行完毕"+ finalI);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"线程"+i+"正在执行").start();
        }
    }

    private static void sycSemaphoreThread(Thread thread){
        Semaphore semaphore = new Semaphore(1);
        try {
            semaphore.acquire(); //获取信号量
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("=====>>>"+thread.currentThread().getName());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 10, 1L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            executor.execute(()->{
                try {
                    //System.out.println("执行任务");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.execute(()->{
            try {
                Thread.sleep(3000);
                System.out.println("任务执行完");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();

        semaphore.release(); //释放信号量
    }

    private static void threadTransfer(ThreadPoolExecutor executor){
            executor.execute(()->{
                System.err.println("线程传递进啦了");
            });
            executor.shutdownNow();
    };

    private static String StringConversion(String resource){
        String s = resource.replaceFirst(",", "[")
                .replaceAll(",", "][");
        return s.substring(0,s.length()-1);
    }


    private static void threadDemo(){

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 15, 2L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());


        // 先并发是个操作然后在并发10个操作
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"排队等待");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            System.err.println("马上准备执行");
            countDownLatch.await(1000,TimeUnit.MILLISECONDS);
            System.out.println("正在执行中");
            // 执行有返回值的线程
            List<Integer> list = Lists.newArrayList(1);
            List<Integer> submitList = null;

            if(CollectionUtils.isNotEmpty(list)){
                Future<List<Integer>> submit = executor.submit(() -> {
                    for (int i = 0; i < 10; i++) {
                        list.add(i);
                    }
                }, list);
                submitList = submit.get(2000, TimeUnit.MILLISECONDS);
            }

            List<Integer> finalSubmitList = submitList;
            executor.execute(()->{
                System.out.println("线程结果:"+ finalSubmitList);
            });


        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

    }
}
