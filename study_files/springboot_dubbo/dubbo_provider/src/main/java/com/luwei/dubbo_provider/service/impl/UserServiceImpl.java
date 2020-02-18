package com.luwei.dubbo_provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luwei.dubbo_provider.mapper.UserMapper;
import com.luwei.dubbo_provider.rabbitmqconfig.RabbitMqProvider;
import com.luwei.entity.User;
import com.luwei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @projectName： springbootdubbo
 * @packageName: com.luwei.dubbo_provider.service
 * @auther: luwei
 * @description:
 * @date: 2020/1/23 19:30
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User>
        implements UserService {

    @Autowired
    private RabbitMqProvider rabbitMqProvider;

    @Override
    public List<User> getAllUser(String userName,String userPassword) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<User> lambda = wrapper.lambda();
        return  baseMapper.selectList(lambda
                .eq(User::getUsername, userName)
                .eq(User::getPassword, userPassword));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUserMsgCheckVersion(User user) {
        Boolean flag = false;
        if(!ObjectUtils.isEmpty(user)){
            User selectById = baseMapper.selectById(user);
            if(!ObjectUtils.isEmpty(selectById)&& user.getVersion().equals(selectById.getVersion())){
                user.setVersion(user.getVersion()+1);
                flag = 0 < baseMapper.updateById(user);
            }
        }
        return flag;
    }

    @Override
    public void sendRabbitMqMessage(String useName) {
        rabbitMqProvider.send("队列设置过期时间测试");
    }
}
