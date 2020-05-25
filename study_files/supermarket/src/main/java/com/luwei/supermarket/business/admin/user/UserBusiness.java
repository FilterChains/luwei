package com.luwei.supermarket.business.admin.user;

import com.luwei.supermarket.base.BaseBusiness;
import com.luwei.supermarket.entity.bo.request.UserRequest;
import com.luwei.supermarket.entity.po.User;
import com.luwei.supermarket.service.user.UserService;
import com.luwei.supermarket.util.Md5Util;
import com.luwei.supermarket.util.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.business
 * @auther: luwei
 * @description:
 * @date: 2020/5/13 21:42
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class UserBusiness extends BaseBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBusiness.class);

    @Autowired
    private UserService userService;


    /**
     * @Title: userRegister
     * @Description: 用户注册
     * @Param: request ->用户注册请求体 参数
     * @Return: String ->成功或失败 返回类型 String
     * @Throws:
     * @Date: 2020/5/13 22:38
     */
    public Notify<String> userRegister(UserRequest request) {
        LOGGER.debug("用户[{}]正在注册", request.getUserName());
        User userName = userService.findUserName(request.getUserName());
        if (Objects.nonNull(userName)) {
            return new Notify<>(Notify.Code.ERROR, "用户名已存在");
        } else {
            Date time = Calendar.getInstance().getTime();
            User user = new User();
            user.setUserName(request.getUserName());
            user.setUserPassword(Md5Util.encoder(request.getUserPassword()));
            user.setUserWeiChat(request.getUserWeiChat());
            user.setCreateTime(time);
            user.setCreateBy(1);
            user.setUpdateBy(1);
            user.setUpdateTime(time);
            userService.insert(user);
            return new Notify<>(Notify.Code.SUCCESS);
        }
    }

    /**
     * @Title: userLogin
     * @Description: 用户登录
     * @Param: request ->用户登录信息 参数
     * @Return: String ->成功与否 返回类型 String
     * @Date: 2020/5/14 0:39
     */
    public Notify<String> userLogin(UserRequest request, HttpServletRequest hsq) {
        LOGGER.debug("用户[{}]正在登录", request.getUserName());
        User user = userService.findUserName(request.getUserName());
        if (Objects.nonNull(user)) {
            if (user.getUserPassword().equals(Md5Util.encoder(request.getUserPassword()))) {
                HttpSession session = hsq.getSession();
                session.setAttribute("userSession", user);
                return new Notify<>(Notify.Code.SUCCESS);
            } else {
                return new Notify<>(Notify.Code.ERROR, "密码不正确请重新输入");
            }
        } else {
            return new Notify<>(Notify.Code.ERROR, "用户不存在，请先注册");
        }
    }
}
