package com.luwei.supermarket.admin.entity.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.admin.entity.bo.response
 * @auther: luwei
 * @description:
 * @date: 2020/5/17 12:21
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@ApiModel("商品搜索响应Model")
public class ProductListResponse implements Serializable {

    @ApiModelProperty("商品主键ID")
    private Integer id;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品地址")
    private String productAddress;

    @ApiModelProperty("商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty("商品库存")
    private Integer productStock;

    @ApiModelProperty("商品状态：0-下架，1-上架，2-待上架")
    private String productStatus;

    @ApiModelProperty("商品图片")
    private String productImagesUrl;

    @ApiModelProperty("商品类型")
    private String productType;

    @ApiModelProperty("商品单位")
    private String productUnit;

    @ApiModelProperty("商品描述")
    private String productRemark;

    @ApiModelProperty("总条数")
    private Integer totalPage;

}
