package com.luwei.supermarket.entity.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.entity.vo
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 22:59
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class OrderCreateVO implements Serializable {
    /**
     * 商品ID
     */
    private String productId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 预付金额
     */
    private BigDecimal payment;

    /**
     * 收货人
     */
    private String receiverBy;

    /**
     * 收货电话
     */
    private String receiverPhone;

    /**
     * 收货地址
     */
    private String receiverAddress;
}
