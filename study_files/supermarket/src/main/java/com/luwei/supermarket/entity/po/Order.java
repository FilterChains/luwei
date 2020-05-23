package com.luwei.supermarket.entity.po;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.entity.po
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 00:50
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@TableName("td_order")
@Accessors(chain = true)
@ToString(callSuper = true)
public class Order implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 预付金额,精确到2位小数
     */
    private BigDecimal payment;

    /**
     * 支付类型，1、在线支付，2、货到付款
     */
    private PayType paymentType;

    public enum PayType {

        ONLINE_PAYMENT(1, "在线支付"),
        PAY_DELIVERY(2, "货到付款");

        @EnumValue
        private int index;
        private String value;

        PayType(int index, String value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 状态：1、未付款，2、已付款，3、待发货，4、已发货，5、已收货
     */
    private OrderStatus status;

    public enum OrderStatus {

        UNPAID(1, "未付款"),
        ACCOUNT_PAID(2, "已付款"),
        AWAIT_SHIPPED(3, "待发货"),
        SHIPPED(4, "已发货"),
        RECEIVED(5, "已收货");

        @EnumValue
        private int index;
        private String value;

        OrderStatus(int index, String value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单更新时间
     */
    private Date updateTime;

    /**
     * 收货人
     */
    private Integer receiverBy;

    /**
     * 收货电话
     */
    private String receiverPhone;

    /**
     * 收货地址
     */
    private String receiverAddress;

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

    /**
     * 买家留言
     */
    private String buyerMessage;

    /**
     * 买家是否已经评价
     */
    private Boolean buyerRate;

}
