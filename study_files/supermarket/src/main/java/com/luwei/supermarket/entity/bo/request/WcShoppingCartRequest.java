package com.luwei.supermarket.entity.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.entity.bo.request
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 11:18
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@ApiModel("购物车Model")
public class WcShoppingCartRequest implements Serializable {

    @ApiModelProperty("商品ID")
    @NotNull(message = "商品ID不能为空")
    private Integer productId;

    @ApiModelProperty("商品数量")
    @NotNull(message = "商品数量不能为空")
    private Integer productNumber;

    @ApiModelProperty("用户ID")
    @NotBlank(message = "用户ID不能为空")
    private String userId;
}
