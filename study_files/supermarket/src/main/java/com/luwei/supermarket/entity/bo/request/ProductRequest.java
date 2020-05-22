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

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品状态：0-下架，1-上架，2-待上架
     */
    private Product.ProductStatus productStatus;

    /**
     * 商品图片
     */
    private String productImagesUrl;

    /**
     * 商品地区，地区ID用逗号分隔
     */
    private String productRegion;

    /**
     * 商品类型：只存储最后一级分类
     */
    private Integer productType;

    /**
     * 商品单位
     */
    private String productUnit;

    /**
     * 商品描述
     */
    private String productRemark;
}
