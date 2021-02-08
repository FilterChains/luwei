package com.user.util;

import java.math.BigDecimal;

public class MemoryTest {

    public static void main(String[] args) {
        // 基本配置
        // 堆内存大小配置 -Xmx100m -Xms50m
        System.out.print("最大内存： ");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024 + "MB");
        System.out.print("可用内存： ");
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024 + "MB");
        System.out.print("已使用内存： ");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024 + "MB");
        System.out.println("1234".contains("123"));
        System.out.println("1234".contains("123456"));

        System.out.println(0 >= new BigDecimal(100).compareTo(new BigDecimal(100)));
        System.out.println("ZF真善美医药江津区珞璜镇和美药店".equals("真善美医药江津区珞璜镇和美药店"));
    }
}
