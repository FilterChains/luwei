package com.luwei.supermarket.entity.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.entity.bo.response
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 01:45
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@ApiModel("购物车响应Model")
public class WcShoppingCartSearchResponse implements Serializable {

    @ApiModelProperty("购物车主键ID")
    private Integer id;

    @ApiModelProperty("商品ID")
    private Integer productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品图片")
    private String productImageUrl;

    @ApiModelProperty("商品数量")
    private Integer productNumber;

    @ApiModelProperty("商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty("总条数")
    private Integer totalPage;
}
