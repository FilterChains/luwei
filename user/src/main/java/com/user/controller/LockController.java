package com.user.controller;

import com.user.util.lock.distributedlock.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
public class LockController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DistributedLock lock;

    private static final String REDIS_KEY = "DISTRIBUTED_LOCK_TEST";

    @GetMapping(value = "distributed/{id}")
    public String distributedLock(@PathVariable Long id) {
        final String lockKey = "lock_" + id;
        boolean acquired = lock.lock(lockKey, 6000, 2, 1000L);
        if (!acquired) {
            System.err.println("网络繁忙请稍后再试");
            return "网络繁忙请稍后再试";
        }
        try {
            System.out.println("开始执行任务");

//            Thread.sleep(300);

            // 模拟操作
            Integer stroe = Integer.valueOf(Objects.requireNonNull(redisTemplate.opsForValue().get(REDIS_KEY)));
            stroe--;
            System.err.println("剩余库存:" + stroe);
            if (0 > stroe) {
                System.err.println("库存不足");
                return "FAILED";
            }
            redisTemplate.opsForValue().set(REDIS_KEY, String.valueOf(stroe));
            System.out.println("任务执行完毕");
            return "剩余库存:" + stroe;
        } finally {
            boolean b = lock.releaseLock(lockKey);
            System.out.println("是否释放锁" + b);
        }
//        return "FAILED";
    }

    @GetMapping(value = "save/{id}")
    public String saveRedisValue(@PathVariable Long id) {
        redisTemplate.opsForValue().set(REDIS_KEY, String.valueOf(id));
        return "SUCCESS";
    }
}
