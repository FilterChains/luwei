package com.luwei.supermarket.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.entity.po
 * @auther: luwei
 * @description: 购物车
 * @date: 2020/5/24 00:43
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("td_shopping_cart")
@Accessors(chain = true)
@ToString(callSuper = true)
public class ShoppingCart implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 商品数量
     */
    private Integer productNumber;

    /**
     * 购买者
     */
    private String createBy;

    /**
     * 创建时间/修改时间
     */
    private Date createTime;

    /**
     * 版本控制
     */
    @Version
    private Integer version;
}
