package com.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.entity.Account;
import com.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Account>
        implements UserService {
    

}
