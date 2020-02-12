package com.luwei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luwei.entity.User;

import java.util.List;

/**
 * @projectName： springbootdubbo
 * @packageName: com.luwei.service
 * @auther: luwei
 * @description:
 * @date: 2020/1/23 17:37
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public interface UserService extends IService<User> {

    List<User> getAllUser(String userName,String userPassword);

    void sendRabbitMqMessage(String useName);
}
