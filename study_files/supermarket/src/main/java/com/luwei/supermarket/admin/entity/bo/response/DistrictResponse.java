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
 * @description:
 * @date: 2020/5/18 22:38
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@ApiModel("地区响应Model")
@Data
public class DistrictResponse implements Serializable {

    @ApiModelProperty("地区ID")
    private Integer id;

    @ApiModelProperty("地区名称")
    private String name;

    @ApiModelProperty("地区上一级ID,0-表示最高级")
    private Integer upid;

    @ApiModelProperty("地区等级：0->1级；1->2级;2->3级")
    private Integer level;

    @ApiModelProperty("地区子集")
    private List<DistrictResponse> children;
}
