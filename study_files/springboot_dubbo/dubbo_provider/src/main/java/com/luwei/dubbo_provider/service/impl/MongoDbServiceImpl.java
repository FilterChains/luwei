package com.luwei.dubbo_provider.service.impl;

import com.google.common.collect.Lists;
import com.luwei.entity.User;
import com.luwei.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.dubbo_provider.service.impl
 * @auther: luwei
 * @description:
 * @date: 2020/2/16 15:03
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class MongoDbServiceImpl implements MongoDbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> save(User user) {
        User save = mongoTemplate.save(user);
        System.out.println("保存结果:"+save);
        return Lists.newArrayList(user);
    }
}
