package com.luwei.supermarket.entity.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.entity.bo.request
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 01:43
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@ApiModel("购物车搜索Model")
public class WcShoppingCartSearchRequest implements Serializable {

    @ApiModelProperty("用户ID")
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty("起始页")
    private Integer pageNo;

    @ApiModelProperty("展示行数")
    private Integer pageSize;
}
