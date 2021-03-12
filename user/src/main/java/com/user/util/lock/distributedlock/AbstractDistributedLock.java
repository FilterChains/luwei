package com.user.util.lock.distributedlock;


/**
 * <p>@description : 分布式锁抽象类 </p>
 * <p>@author : Wei.Lu </p>
 * <p>@date : 2021/3/11 18:46 </p>
 **/
public abstract class AbstractDistributedLock implements DistributedLock {

    /**
     * 默认超时时间
     */
    private static final long TIMEOUT_MILLIS = 6000;

    /**
     * 重试次数
     */
    private static final int RETRY_TIMES = 6;

    /**
     * 每次重试后等待的时间
     */
    private static final long SLEEP_MILLIS = 1000;

    @Override
    public boolean lock(String key) {
        return lock(key, TIMEOUT_MILLIS, RETRY_TIMES, SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, int retryTimes) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, int retryTimes, long sleepMillis) {
        return lock(key, TIMEOUT_MILLIS, retryTimes, sleepMillis);
    }

    @Override
    public boolean lock(String key, long expire) {
        return lock(key, expire, RETRY_TIMES, SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, long expire, int retryTimes) {
        return lock(key, expire, retryTimes, SLEEP_MILLIS);
    }
}
