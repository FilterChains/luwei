package com.luwei.supermarket.util;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.util
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 14:03
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public enum CommonEnum {

    ZERO(0, "零");

    private Integer index;
    private String value;

    CommonEnum(Integer index, String value) {
        this.index = index;
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }
}
