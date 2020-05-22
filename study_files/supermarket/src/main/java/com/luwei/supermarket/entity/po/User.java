package com.luwei.supermarket.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.entity.po
 * @auther: luwei
 * @description: 用户实体类
 * @date: 2020/5/13 22:46
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@TableName("td_user")
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseTable implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户微信
     */
    private String userWeiChat;

}
