package com.luwei.supermarket.entity.bo.request;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.entity.bo.request
 * @auther: luwei
 * @description:
 * @date: 2020/5/19 00:27
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@ApiModel("商品分类Model")
public class ProductCategoryRequest implements Serializable {

    @ApiModelProperty("分类ID")
    private Integer id;

    @ApiModelProperty("分类名称")
    private String title;

    @ApiModelProperty("商品分类操作:INSERT-新增,UPDATE-修改,DELETE-删除")
    private ProductCategoryOperation operation;

    public enum ProductCategoryOperation {
        INSERT(1, "新增"),
        UPDATE(2, "修改"),
        DELETE(3, "删除");
        @EnumValue
        private int index;
        private String value;

        ProductCategoryOperation(int index, String value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public String getValue() {
            return value;
        }
    }

}
