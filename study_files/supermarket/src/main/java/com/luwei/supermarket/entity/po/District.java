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
 * @description: 地区
 * @date: 2020/5/14 23:09
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@TableName("td_district")
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class District extends BaseTable implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 地区名称
     */
    private String name;

    /**
     * 上一级ID
     */
    private Integer upid;

    /**
     * 地区等级：0->1级；1->2级;2->3级，以此类推
     */
    private DistrictType level;

    public enum DistrictType {

        FIRST_LEVEL(1, "一级"),
        SECOND_LEVEL(2, "二级"),
        THIRDLY_LEVEL(3, "三级");
        @EnumValue
        private int index;
        private String value;

        DistrictType(int index, String value) {
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
