package com.luwei.util;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
/**
 * <p>@description : 缓存操作 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/3/19 8:26 </p>
 */
public class CacheDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Cache<String, List<String>> build = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS) // 设置过期时间，TimeUnit.SECONDS是指的多少秒过期
                .build();

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            List<String> getValue = build.getIfPresent("getValue");
            System.out.println("第一次拿取值:"+getValue);
            if (CollectionUtils.isEmpty(getValue)) {
                getValue = Lists.newArrayList("1", "2", "3");
                build.put("getValue",getValue);
                System.err.println("第二次获取值:" + getValue);
            }
        }

    }


}
