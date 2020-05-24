package com.luwei.supermarket.entity.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.entity.bo.request
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 16:27
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@ApiModel("清空购物车Model")
public class WcShoppingCartCleanRequest implements Serializable {

    @ApiModelProperty("商品ID")
    private Integer productId;

    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不能为空")
    private Integer userId;
}
