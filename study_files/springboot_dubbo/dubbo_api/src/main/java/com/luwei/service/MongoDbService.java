package com.luwei.service;

import com.luwei.entity.User;

import java.util.List;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.service
 * @auther: luwei
 * @description:
 * @date: 2020/2/16 14:07
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public interface MongoDbService {

    List<User> save(User user);
}
