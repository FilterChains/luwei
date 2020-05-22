package com.luwei.supermarket.admin.controller.user;

import com.luwei.supermarket.admin.business.user.UserBusiness;
import com.luwei.supermarket.admin.controller.BaseController;
import com.luwei.supermarket.entity.bo.request.UserRequest;
import com.luwei.supermarket.util.Notify;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.admin.controller
 * @auther: luwei
 * @description:
 * @date: 2020/5/11 23:42
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Api(tags = "用户信息相关接口")
@Validated
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserBusiness userBusiness;

    @ResponseBody
    @PostMapping("/register")
    @ApiOperation(value = "用户注册接口", notes = "用户注册接口")
    public Notify<String> register(@RequestBody @Valid UserRequest request) {
        return userBusiness.userRegister(request);
    }

    @ResponseBody
    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口", notes = "用户登录接口")
    public Notify<String> login(@RequestBody @Valid UserRequest request, HttpServletRequest hsq) {
        return userBusiness.userLogin(request, hsq);
    }
}
