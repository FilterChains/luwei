package com.luwei.supermarket.admin.entity.bo.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.bo.request
 * @auther: luwei
 * @description:
 * @date: 2020/5/13 22:20
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@Valid
public class UserRequest implements Serializable {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String userPassword;

    /**
     * 微信号
     */
    private String userWeiChat;
}
