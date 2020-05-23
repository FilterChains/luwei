package com.luwei.supermarket.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
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
     * 商品名称
     */
    private String productName;

    /**
     * 商品图片
     */
    private String productImageUrl;

    /**
     * 商品数量
     */
    private Integer productNumber;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 商品类型
     */
    private Integer productType;

    /**
     * 购买者
     */
    private Integer createBy;

    /**
     * 创建时间/修改时间
     */
    private Date createTime;
}
