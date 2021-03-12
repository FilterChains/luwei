package com.user.util.lock.distributedlock;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
public class RedisDistributedLock extends AbstractDistributedLock {

    @Autowired
    private final RedisTemplate<String, String> redisTemplate;

    private final ThreadLocal<String> lockFlag = new ThreadLocal<>();

    private static final String LOCK_LUA;
    private static final String UNLOCK_LUA;
    private static final String ZERO = "0";

    /*
     * 通过lua脚本释放锁,来达到释放锁的原子操作
     * KEYS[1] 用来表示在redis 中用作键值的参数占位，主要用來传递在redis 中用作key值的参数。
     * ARGV[1] 用来表示在redis 中用作参数的占位，主要用来传递在redis中用做 value值的参数。
     * lua: 如果调用方法 get(redisKey) 获取的值等于传进去的值就删除，否则返回0;
     * 书写过程中注意空格要求最好采用\n
     */
    static {
        LOCK_LUA = "if redis.call(\"EXISTS\",KEYS[1]) > 0 " +
                "then " +
                "    return 0 " +
                "else " +
                "     redis.call(\"set\",KEYS[1],ARGV[1]) " +
                "     return redis.call(\"expire\",KEYS[1],ARGV[2]) " +
                "end ";
        UNLOCK_LUA = "if redis.call(\"get\",KEYS[1]) == ARGV[1] " +
                "then " +
                "    return redis.call(\"del\",KEYS[1]) " +
                "else " +
                "    return 0 " +
                "end ";
    }

    public RedisDistributedLock(RedisTemplate<String, String> redisTemplate) {
        super();
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean lock(final String key, final long expire,
                        int retryTimes, final long sleepMillis) {
        // 开始加锁
        boolean result = automaticLock(key, expire);
        // 如果获取锁失败，按照传入的重试次数进行重试
        while ((!result) && retryTimes-- > 0) {
            try {
                log.debug("get redisDistributeLock failed, retrying..." + retryTimes);
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                log.warn("Interrupted!", e);
                System.err.println("获取锁重试失败：" + e);
                // 报异常,立即向当前线程发送中断状态,请求中断停止当前线程
                Thread.currentThread().interrupt();
            }
            result = automaticLock(key, expire);
        }
        return result;
    }

    /**
     * <p>@description : 上锁 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/11 19:06 </p>
     *
     * @param key    ->redisKey
     * @param expire ->锁失效时间
     * @return {@link  boolean }
     **/
    private boolean automaticLock(final String key, final long expire) {
        try {
            // 为每一个线程创建一个唯一标识
            final String uuid = String.valueOf(UUID.randomUUID());
            // 将线程的唯一标识放入当前线程的线程变量中(ThreadLocal ->特殊变量 只存在当前线程，每个线程都有一个属于自己的 ThreadLocal)
            lockFlag.set(uuid);
            // 若遇redis集群环境，则需要获取redis连接在进行操作
            RedisScript<String> script = new DefaultRedisScript<>(LOCK_LUA, String.class);
            String execute = String.valueOf(redisTemplate.execute(script, Lists.newArrayList(key), uuid, String.valueOf(expire)));
            return StringUtils.isNotBlank(execute) && !ZERO.equals(execute);
        } catch (Exception e) {
            log.error("set redisDistributeLock occurred an exception", e);
        }
        return false;
    }

    @Override
    public Boolean releaseLock(final String key) {
        try {
            // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
            RedisScript<String> script = new DefaultRedisScript<>(UNLOCK_LUA, String.class);
            String execute = String.valueOf(redisTemplate.execute(script, Lists.newArrayList(key), lockFlag.get()));
            return StringUtils.isNotBlank(execute) && !ZERO.equals(execute);
        } catch (Exception e) {
            log.error("release redisDistributeLock occurred an exception", e);
        } finally {
            // 移除当前线程变量中的唯一标识
            lockFlag.remove();
        }
        return Boolean.FALSE;
    }
}
