package com.luwei.supermarket.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luwei.supermarket.admin.entity.po.User;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.service
 * @auther: luwei
 * @description: 用户服务
 * @date: 2020/5/13 22:40
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public interface UserService extends IService<User> {

    /**
     * @Title: findUserName
     * @Description: 根据名称快速查找用户信息
     * @Param: userName ->用户名称   参数
     * @Return: User ->用户信息 返回类型
     * @Date: 2020/5/13 23:55
     */
    User findUserName(String userName);
}
