package com.user.util.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <p>@description : 缓存操作 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/3/19 8:26 </p>
 */
public class CacheDemo implements PlatformTransactionManager {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Cache<String, List<String>> build = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS) // 设置过期时间，TimeUnit.SECONDS是指的多少秒过期
                .build();

        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            List<String> value = build.get("getValue", new Callable<List<String>>() {
                @Override
                public List<String> call() throws Exception {
                    System.err.println("第二次获取值==========================");
                    return Lists.newArrayList("1", "2", "3");
                }
            });
            System.out.println("第一次拿取值:" + value);
        }
        // 清除缓存
        build.invalidate("getValue");

    }


    @Override
    public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
        return null;
    }

    @Override
    public void commit(TransactionStatus transactionStatus) throws TransactionException {

    }

    @Override
    public void rollback(TransactionStatus transactionStatus) throws TransactionException {

    }
}
