package com.luwei.supermarket.admin.entity.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.admin.entity.bo.request
 * @auther: luwei
 * @description:
 * @date: 2020/5/19 23:48
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@ApiModel("商品搜索Model")
@Data
public class ProductSearchRequest implements Serializable {

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品分类")
    private String type;

    @ApiModelProperty("商品地区")
    private String region;

    @ApiModelProperty("起始页")
    private Integer pageNo;

    @ApiModelProperty("展示行数")
    private Integer pageSize;

}
