package com.luwei.supermarket.entity.po;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.entity.po
 * @auther: luwei
 * @description:
 * @date: 2020/5/14 22:43
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@TableName("td_product_category")
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductCategory extends BaseTable implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品类型名称
     */
    private String name;

    /**
     * 上一级ID
     */
    private Integer prev;

    /**
     * 商品类型等级：0->1级；1->2级;2->3级，以此类推
     */
    private ProductCategoryType type;

    public enum ProductCategoryType {
        FIRST_LEVEL(1, "一级"),
        SECOND_LEVEL(2, "二级"),
        THIRDLY_LEVEL(3, "三级");
        @EnumValue
        private int index;
        private String value;

        ProductCategoryType(int index, String value) {
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

    /**
     * 排序值，同级排序值小越靠前
     */
    private Integer sortOrder;
}
