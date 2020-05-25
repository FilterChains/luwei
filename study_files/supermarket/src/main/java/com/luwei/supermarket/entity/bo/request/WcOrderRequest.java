package com.luwei.supermarket.entity.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.entity.bo.request
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 21:49
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@ApiModel("创建订单Model")
public class WcOrderRequest implements Serializable {

    @ApiModelProperty("商品ID,多个商品用逗号分隔")
    @NotBlank(message = "商品ID不能为空")
    private String productId;

    @ApiModelProperty("用户ID")
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty("预付金额,精确到2位小数")
    @NotNull(message = "预付金额不能为空")
    private BigDecimal payment;

    @ApiModelProperty("收货人")
    @NotNull(message = "收货人不能为空")
    private String receiverBy;

    @ApiModelProperty("收货电话")
    @NotBlank(message = "收货电话不能为空")
    private String receiverPhone;

    @ApiModelProperty("收货地址")
    @NotBlank(message = "收货地址不能为空")
    private String receiverAddress;
}
