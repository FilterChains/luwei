package com.luwei.supermarket.entity.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.entity.bo.request
 * @auther: luwei
 * @description:
 * @date: 2020/5/19 23:48
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@ApiModel("商品搜索Model")
public class WcProductSearchRequest implements Serializable {

    @ApiModelProperty("商品分类")
    private Integer type;

    @ApiModelProperty("起始页")
    private Integer pageNo;

    @ApiModelProperty("展示行数")
    private Integer pageSize;

}
