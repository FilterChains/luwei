package com.luwei.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName： springbootdubbo
 * @packageName: com.luwei.entity
 * @auther: luwei
 * @description:
 * @date: 2020/1/23 17:35
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@Builder
@ToString
@TableName("user")
public class User implements Serializable {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户余额
     */
    private BigDecimal moneys;
}
