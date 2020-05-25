package com.luwei.supermarket.entity.vo;

import com.luwei.supermarket.entity.po.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 订单状态
     */
    private Order.OrderStatus status;

    /**
     * 发货时间
     */
    private Date consignTime;

    /**
     * 收货时间
     */
    private Date endTime;

    /**
     * 物流名称
     */
    private String shippingName;

    /**
     * 物流单号
     */
    private String shippingCode;
}
