package com.user.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Slf4j
public class CompletableFutureTest {
    
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            allOfAnfAnyOf(executor);
//            exceptionThread(executor);
//            compareThread(executor);
//            operationThread(executor);
//            connectThread(executor);
        } catch (Exception e) {
            log.error(e.getMessage());
            executor.shutdownNow();
        } finally {
            executor.shutdown();
        }

    }

    /**
     * <p>@description : 用于连接两个线程 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/10 21:35 </p>
     *
     * @param executor ->Thread
     **/
    public static void connectThread(ThreadPoolExecutor executor) {
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> "hello", executor);
        CompletableFuture<String> futureB = futureA.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "world", executor));
//        int i = 1 / 0;
        CompletableFuture<String> future3 = futureB.thenCompose(s -> CompletableFuture.supplyAsync(s::toUpperCase, executor));
        System.out.println(future3.join());
    }

    /**
     * <p>@description : 操作两个线程的返回值并进行计算 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/10 21:10 </p>
     *
     * @param executor -> Thread
     **/
    public static void operationThread(ThreadPoolExecutor executor) {
        CompletableFuture<BigDecimal> futureA = CompletableFuture.supplyAsync(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " 开始执行任务");
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return BigDecimal.valueOf(0.08);
        }, executor);
        CompletableFuture<BigDecimal> futureB = CompletableFuture.supplyAsync(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " 开始执行任务");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return BigDecimal.valueOf(120);
        }, executor);
        // thenAcceptBoth 计算无返回值
        // runAfterBoth 计算无返回值
        CompletableFuture<BigDecimal> future = futureA.thenCombine(futureB,
                (x, y) -> x.multiply(y).setScale(2, BigDecimal.ROUND_DOWN));
        System.out.println("最后两个线程计算后的结果:" + future.join().stripTrailingZeros().toPlainString());
    }

    /**
     * <p>@description : 比较两个线程那个任务先执行完成 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/10 21:11 </p>
     *
     * @param executor ->Thread
     **/
    public static void compareThread(ThreadPoolExecutor executor) {
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(" 开始执行任务");
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + "任务执行完成";
        }, executor);
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " 开始执行任务");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + " 任务执行完成";
        }, executor);

        // acceptEither 操作无返回值
        // runAfterEither 操作无返回值
        // 获取两个异步任务最先执行完成的任务
        CompletableFuture<String> async = futureA.applyToEitherAsync(futureB, x -> x);
        System.out.println("看看那个异步任务执行完成" + async.join());
    }

    /**
     * <p>@description : 线程CompletableFuture 的正确操作 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/10 21:11 </p>
     *
     * @param executor ->Thread
     **/
    public static void exceptionThread(ThreadPoolExecutor executor) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            int i = 1 / 0;
            System.out.println("正在异步执行任务");
            return "Hello World";
        }, executor)
//                .thenAccept() 需要上个任务的返回值做为参数，并执行。没返回值
//                .thenRun() 不需要上个任务的返回值，都执行。没返回值
                .thenApply(x -> "上一个线程执行后的返回值" + x + "有返回值") // 需要上个任务的返回值做为参数，有返回值
                .exceptionally(x -> Thread.currentThread().getName() + "线程异常处理: " + x.getMessage()) // 对任务中的异常进行处理 设置默认值返回相当于catch
                .whenCompleteAsync((x, j) -> {
                    System.out.println("x:" + x);
                    System.out.println("j:" + j);
                    System.out.println(x.concat("==》》》》不管是否异常都要执行"));
                });// 不管上面的任务报不报异常都要执行 相当于finally ,handle有返回值

        // allOf:当所有的CompletableFuture都执行完后执行计算 会阻塞所有执行任务的线程并等待执行完成后的结果
        // anyOf:最快的那个CompletableFuture执行完之后执行计算 会阻塞，但是只要有任务被线程执行完成就不会阻塞了
        // applyToEither(..)  acceptEither(..)  runAfterEither(..) 比较两个任务线程谁先执行完成
        // thenCompose(..) 这个方法接收的输入是当前的CompletableFuture的计算值，返回结果将是一个新的CompletableFuture

        // thenCombine(..)  thenAcceptBoth(..)  runAfterBoth(..) 结合两个CompletionStage的结果，进行转化后返回
        // CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> 100d);
        // CompletableFuture<Double> futureDiscount = CompletableFuture.supplyAsync(() -> 0.8);
        // CompletableFuture<Double> futureResult = futurePrice.thenCombine(futureDiscount, (price, discount) -> price * discount);
        // System.out.println("最终价格为:" + futureResult.join()); //最终价格为:80.0
        CompletableFuture<Object> any = CompletableFuture.anyOf(future);
        any.join();
        // join 和 get 的却别在于join 会报运行时异常RunTimeException 而
        // Get是检查时异常，编译时异常Interrupter TimeOutException 等
        System.out.println("执行结果:" + future.join());
    }

    public static void allOfAnfAnyOf(ThreadPoolExecutor executor) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000 + RandomUtils.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "商品详情-> ";
        }, executor);

        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + RandomUtils.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "卖家信息-> ";
        }, executor);

        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + RandomUtils.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "库存信息-> ";
        }, executor);

        CompletableFuture<String> futureD = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(900 + RandomUtils.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "订单信息-> ";
        }, executor);

//        CompletableFuture<Void> allFuture = CompletableFuture.allOf(futureA, futureB, futureC, futureD);
//        allFuture.join();

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futureD, futureB, futureC, futureA);
        anyOf.join();

        System.out.println(futureA.join() + futureB.join() + futureC.join() + futureD.join());
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    public static <T> CompletableFuture<List<T>> allOf(Collection<CompletableFuture<T>> futures) {
        return futures.stream().collect(Collectors.collectingAndThen(
                Collectors.toList(),
                l -> CompletableFuture.allOf(l.toArray(new CompletableFuture[0]))
                        .thenApply(v -> l.stream().map(CompletableFuture::join).collect(Collectors.toList()))
                )
        );
    }

}
