package com.luwei.supermarket.admin.entity.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.admin.entity.bo.response
 * @auther: luwei
 * @description: 商品分类数据响应实体类
 * @date: 2020/5/15 00:14
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@ApiModel("商品分类Model")
public class ProductCategoryResponse implements Serializable {

    @ApiModelProperty("分类ID")
    private Integer value;

    @ApiModelProperty("分类名称")
    private String label;

    @ApiModelProperty("上一级ID")
    private Integer prev;

    @ApiModelProperty("商品类型等级：1->1级；2->2级;3->3级")
    private Integer type;

    @ApiModelProperty("分类子集")
    private List<ProductCategoryResponse> children;
}
