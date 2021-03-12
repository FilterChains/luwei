package com.user.util.lock.distributedlock;

/**
 * <p>@description : Redis分布式锁接口,默认：锁失效是6S,重试次数：6次,每次间隔:1s
 * 最长会等待6秒后抛超时异常, </p>
 * <p>@alert : 使用时需注意锁代码块的执行时间,建议按照具体逻辑自行设置。分布式锁推荐使用：客户端->redisson
 * 优点在于Key的失效时间不需要很精确，由内部监听来完成。默认是设置失效时间的1/3时监听一次key是否失效，若失效则自动延续key
 * 的失效时间，弥补人为设置失效时间的不足</p>
 * <p>@test-data : 瞬时并发 350 左右,若增加并发量需调节redis最大连接次数。测试工具:JMeter </p>
 * <p>@author : Wei.Lu </p>
 * <p>@date : 2021/3/11 18:46 </p>
 **/
public interface DistributedLock {

    /**
     * <p>@description : 获取锁 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/11 19:33 </p>
     *
     * @param key ->redisKey
     * @return {@link  boolean }
     **/
    boolean lock(String key);

    /**
     * <p>@description : 获取锁 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/11 19:33 </p>
     *
     * @param key        ->redisKey
     * @param retryTimes ->重试次数
     * @return {@link  boolean }
     **/
    boolean lock(String key, int retryTimes);

    /**
     * <p>@description : lock </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/11 19:33 </p>
     *
     * @param key         ->redisKey
     * @param retryTimes  ->重试次数
     * @param sleepMillis ->获取锁失败的重试间隔
     * @return {@link  boolean }
     **/
    boolean lock(String key, int retryTimes, long sleepMillis);

    /**
     * <p>@description : lock </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/11 19:34 </p>
     *
     * @param key    ->redisKey
     * @param expire -> 锁失效时间
     * @return {@link  boolean }
     **/
    boolean lock(String key, long expire);

    /**
     * <p>@description : lock </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/11 19:34 </p>
     *
     * @param key        ->redisKey
     * @param expire     ->锁失效时间
     * @param retryTimes ->重试次数
     * @return {@link  boolean }
     **/
    boolean lock(String key, long expire, int retryTimes);

    /**
     * <p>@description : lock </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/11 19:30 </p>
     *
     * @param key         ->redisKey
     * @param expire      ->锁失效时间
     * @param retryTimes  ->重试次数
     * @param sleepMillis ->获取锁失败的重试间隔
     * @return {@link  boolean }
     **/
    boolean lock(String key, long expire, int retryTimes, long sleepMillis);

    /**
     * <p>@description : 释放锁 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/11 19:30 </p>
     *
     * @param key ->redisKey
     * @return {@link  Boolean}
     **/
    Boolean releaseLock(String key);
}
