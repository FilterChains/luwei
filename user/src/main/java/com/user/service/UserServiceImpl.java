package com.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.entity.Account;
import com.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Account>
        implements UserService {

    @Override
    public Account selectOne(Long id) {
        System.out.println("开始第一次查询数据");
        baseMapper.selectById(id);
        System.out.println("完成第一次查询");
        System.out.println("开始第二次查询数据");
        baseMapper.selectById(id);
        System.out.println("完成第二次查询");
        System.out.println("开始第三次查询数据");
        baseMapper.selectById(id);
        System.out.println("完成第三次查询");
        return null;
    }
}
