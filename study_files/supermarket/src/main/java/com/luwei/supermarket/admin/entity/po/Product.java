package com.luwei.supermarket.admin.entity.po;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.admin.entity.po
 * @auther: luwei
 * @description:
 * @date: 2020/5/14 21:58
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@TableName("td_product")
@Accessors(chain = true)
@ToString(callSuper = true)
public class Product implements Serializable {

    /**
     * 商品主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品地址
     */
    private String productAddress;

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
    private ProductStatus productStatus;

    public enum ProductStatus {

        SOLD_OUT(0, "下架"),
        PUT_AWAY(1, "上架"),
        TO_STAY_ON(2, "待上架");
        @EnumValue
        private int index;
        private String value;

        ProductStatus(int index, String value) {
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
     * 商品图片
     */
    private String productImagesUrl;

    /**
     * 商品地区
     */
    private String productRegion;

    /**
     * 商品类型
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

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Integer updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;
}
