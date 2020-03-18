package com.luwei.util;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Cache<String, List<String>> build = CacheBuilder.newBuilder()
                .expireAfterAccess(600, TimeUnit.SECONDS)
                .build();

        for (int i = 0; i < 10; i++) {
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
