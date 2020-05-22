package com.luwei.supermarket.entity.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel("用户信息请求Model")
public class UserRequest implements Serializable {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String userPassword;

    @ApiModelProperty("微信号")
    private String userWeiChat;
}
