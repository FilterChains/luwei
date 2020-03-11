package com.luwei.util;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadIntroduce {

        /**
         * ThreadPoolExecutor:
         *      corePoolSize: 指定了线程池中的线程数量，它的数量决定了添加的任务是开辟新的线程去执行，还是放到workQueue任务队列中去；
         *      maximumPoolSize: 指定了线程池中的最大线程数量，这个参数会根据你使用的workQueue任务队列的类型，决定线程池会开辟的最大线程数量；
         *      keepAliveTime: 当线程池中空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；
         *      unit: keepAliveTime的单位
         *      workQueue: 任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列几种；
         *      threadFactory: 线程工厂，用于创建线程，一般用默认即可；
         *      handler:拒绝策略；当任务太多来不及处理时，如何拒绝任务；
         * =================================workQueue=========================================================
         * 有四种:
         * 一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列
         *
         *  1、直接提交队列：设置为SynchronousQueue队列，SynchronousQueue是一个特殊的BlockingQueue，它没有容量，
         *      没执行一个插入操作就会阻塞，需要再执行一个删除操作才会被唤醒，反之每一个删除操作也都要等待对应的插入操作。
         * 使用:
         *          SynchronousQueue队列，提交的任务不会被保存，总是会马上提交执行。如果用于执行任务的线程数量小于maximumPoolSize，
         *      则尝试创建新的进程，如果达到maximumPoolSize设置的最大值，则根据你设置的handler执行拒绝策略。因此这种方式你提交的任务不会被缓存起来，
         *      而是会被马上执行，在这种情况下，你需要对你程序的并发量有个准确的评估，才能设置合适的maximumPoolSize数量，否则很容易就会执行拒绝策略；
         *
         *  2、有界的任务队列：有界的任务队列可以使用ArrayBlockingQueue实现
         * 使用:
         *          使用ArrayBlockingQueue有界任务队列，若有新的任务需要执行时，线程池会创建新的线程，直到创建的线程数量达到corePoolSize时，
         *      则会将新的任务加入到等待队列中。若等待队列已满，即超过ArrayBlockingQueue初始化的容量，则继续创建线程，直到线程数量达到maximumPoolSize设置的最大线程数量，
         *      若大于maximumPoolSize，则执行拒绝策略。在这种情况下，线程数量的上限与有界任务队列的状态有直接关系，如果有界队列初始容量较大或者没有达到超负荷的状态，
         *      线程数将一直维持在corePoolSize以下，反之当任务队列已满时，则会以maximumPoolSize为最大线程数上限。
         *
         *  3、无界的任务队列：无界任务队列可以使用LinkedBlockingQueue实现
         * 使用:
         *          使用LinkedBlockingQueue队列，线程池的任务队列可以无限制的添加新的任务，而线程池创建的最大线程数量就是你corePoolSize设置的数量，
         *      也就是说在这种情况下maximumPoolSize这个参数是无效的，哪怕你的任务队列中缓存了很多未执行的任务，当线程池的线程数达到corePoolSize后，
         *      就不会再增加了；若后续有新的任务加入，则直接进入队列等待，当使用这种任务队列模式时，一定要注意你任务提交与处理之间的协调与控制，
         *      不然会出现队列中的任务由于无法及时处理导致一直增长，直到最后资源耗尽的问题。
         *
         *  4、优先任务队列：优先任务队列通过PriorityBlockingQueue实现
         * 使用:
         *          使用PriorityBlockingQueue它其实是一个特殊的无界队列，它其中无论添加了多少个任务，线程池创建的线程数也不会超过corePoolSize的数量，
         *      只不过其他队列一般是按照先进先出的规则处理任务，而PriorityBlockingQueue队列可以自定义规则根据任务的优先级顺序先后执行。
         *
         *=================================threadFactory======================================================
         * 两种工厂:
         *
         * Executors.defaultThreadFactory() 返回用于创建新线程的默认线程工厂。(默认)
         * Executors.privilegedThreadFactory() 返回用于创建新线程的线程工厂，这些新线程与当前线程具有相同的权限。
         *
         *=================================handler============================================================
         * 四种策略
         *
         * 1、AbortPolicy策略：该策略会直接抛出异常，阻止系统正常工作;(默认)
         * 2、CallerRunsPolicy策略：如果线程池的线程数量达到上限，该策略会把任务队列中的任务放在调用者线程当中运行；
         * 3、DiscardOldestPolicy策略：该策略会丢弃任务队列中最老的一个任务，也就是当前任务队列中最先被添加进去的，马上要被执行的那个任务，并尝试再次提交；
         * 4、DiscardPolicy策略：该策略会默默丢弃无法处理的任务，不予任何处理。当然使用此策略，业务场景中需允许任务的丢失；
         * 调用:
         * ThreadPoolExecutor.AbortPolicy()  抛出java.util.concurrent.RejectedExecutionException异常
         * ThreadPoolExecutor.CallerRunsPolicy() 重试添加当前的任务，他会自动重复调用execute()方法
         * ThreadPoolExecutor.DiscardOldestPolicy() 抛弃旧的任务
         * ThreadPoolExecutor.DiscardPolicy() 抛弃当前的任务
         */
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 1L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        /**
         * Thread ->Exchanger,线程交换Exchanger的使用
         * Exchanger可以在两个线程之间交换数据，只能是2个线程，他不支持更多的线程之间互换数据。
         */

        final Exchanger exchanger = new Exchanger();

        executor.execute(()->{
            //创建一个数据
            ThreadTest build = ThreadTest.builder()
                    .userName("熊猫")
                    .userAge(100)
                    .userSex((byte) 1)
                    .userAddress("成都市")
                    .build();
            ThreadTest value = doExchangeWork(build, exchanger);
            System.err.println(Thread.currentThread().getName() + "成都市:>>>交换到数据" + value);
        });

        executor.execute(()->{
            //创建一个数据
            ThreadTest build = ThreadTest.builder()
                    .userName("轻轨")
                    .userAge(100)
                    .userSex((byte) 1)
                    .userAddress("重庆市")
                    .build();
            ThreadTest value = doExchangeWork(build, exchanger);
            System.err.println(Thread.currentThread().getName() + "重庆市:>>>交换到数据" + value);
        });
        executor.shutdown();
    }

    /**
     * <p>@description : 线程中数据交换 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@param : [data,exchanger] data ->被交换的数据,exchanger</p>
     * <p>@return : 泛型 </p>
     * <p>@date : 2020/3/11 14:23 </p>
     */
    private static<T> T doExchangeWork(T data, Exchanger exchanger) {
        T value = null;
        try {
            value = (T) exchanger.exchange(data, 3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        return value;
    }
}
