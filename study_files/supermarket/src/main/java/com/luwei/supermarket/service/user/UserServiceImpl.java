package com.luwei.supermarket.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luwei.supermarket.admin.entity.po.User;
import com.luwei.supermarket.base.SuperServiceImpl;
import com.luwei.supermarket.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.service.user
 * @auther: luwei
 * @description: 用户服务层实现类
 * @date: 2020/5/13 22:43
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public User findUserName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<User> lambda = wrapper.lambda();
        return baseMapper.selectOne(lambda.eq(User::getUserName, userName)
                .last("limit 1"));
    }
}
