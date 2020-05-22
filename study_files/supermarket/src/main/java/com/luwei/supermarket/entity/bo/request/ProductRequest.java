package com.luwei.supermarket.entity.bo.request;

import com.luwei.supermarket.entity.po.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.entity.bo.request
 * @auther: luwei
 * @description: 创建商品请求实体类
 * @date: 2020/5/17 00:14
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@ApiModel("商品编辑请求Model")
public class ProductRequest implements Serializable {

    @ApiModelProperty("修改ID")
    private Integer id;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty("商品库存")
    private Integer productStock;

    @ApiModelProperty("商品状态：SOLD_OUT-下架，PUT_AWAY-上架，TO_STAY_ON-待上架")
    private Product.ProductStatus productStatus;

    @ApiModelProperty("商品图片")
    private String productImagesUrl;

    @ApiModelProperty("商品地区，地区ID用逗号分隔")
    private String productRegion;

    @ApiModelProperty("商品类型：只存储最后一级分类")
    private Integer productType;

    @ApiModelProperty("商品单位")
    private String productUnit;

    @ApiModelProperty("商品描述")
    private String productRemark;
}
