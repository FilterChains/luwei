package com.luwei.util;

import java.math.BigDecimal;

/**
 * @projectName： springbootdubbo
 * @packageName: com.dubbo.common.util
 * @auther: luwei
 * @description:
 * @date: 2020/2/5 13:01
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class ZenZeBiaoDaShiTest {
    public static void main(String[] args) {
        // 避免输出科学计数法
        String s = new BigDecimal("9").stripTrailingZeros().toPlainString();
        System.out.println(s);
        System.out.println(!s.matches("([1-9])|([1-9]\\d{1,7})|([1-9]\\d{1,7}\\.\\d{1,2})|([0-9]\\.\\d{1,2})"));

        System.out.println("19922218826".matches("[1][0-9]{10}"));
    }
}
