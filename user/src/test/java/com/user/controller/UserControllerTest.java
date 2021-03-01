package com.user.controller;

import com.alibaba.fastjson.JSON;
import com.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserControllerTest {

    @Autowired
    private UserService userService;

    @Test
    void findUser() {
        String s = JSON.toJSONString(userService.getById(1L));
        System.out.println(s);
    }
}