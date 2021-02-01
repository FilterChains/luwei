package com.user.util;

import com.google.common.collect.Lists;
import com.luwei.encryption.CaesarEncryption;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @projectName： springbootdubbo
 * @packageName: com.dubbo.common.util
 * @auther: luwei
 * @description:
 * @date: 2020/1/28 22:30
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class PaChongTest {
    public static void main(String[] args) {
        String proclaim = "JAVA,JAVAEE,IOS,ANDROID,Go414124124eeasvasvsavavav";
        int secretKey = -12;
        //加密
        String caesarEncryption = CaesarEncryption.caesarEncryption(proclaim, secretKey, CaesarEncryption.coder.ENCODER);
        System.out.println("加密:" + caesarEncryption);
        //解密
        String encryption = CaesarEncryption.caesarEncryption(caesarEncryption, secretKey, CaesarEncryption.coder.DECODER);
        System.out.println("解密:" + encryption);
        if (proclaim.equals(encryption)) {
            System.err.println("加密解密成功");
        }

        String[] s = {"JAVA", "JAVAEE", "IOS", "ANDROID", "Go"};

        String x = "Hello->JAVA->C++";

        List<String> string = getList(x);
        System.out.println(string);

        List<Integer> integer = getList(1, 2);
        System.out.println(integer);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        System.out.println(new Date() + ":" + calendar.getTime());

        BigDecimal bigDecimal = new BigDecimal(20);

        BigDecimal bigDecimal1 = new BigDecimal(20);

        System.out.println(getList(bigDecimal, bigDecimal1));

        System.err.println(bigDecimal.compareTo(bigDecimal1));


        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.MONTH, -5);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(instance.getTime()));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.MINUTE, 1);
        Calendar currentTimes = Calendar.getInstance();
        currentTimes.set(Calendar.SECOND, 0);
        currentTimes.set(Calendar.MILLISECOND, 0);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTimes.getTime()) + "-" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));

    }

    @SafeVarargs
    private static <T> List<T> getList(T... t) {
        return Lists.newArrayList(t);
    }
}
