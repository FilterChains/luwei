package com.luwei.util;

import com.google.common.collect.Lists;

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

        String[] s = {"JAVA","JAVAEE","IOS","ANDROID","Go"};

        String x = "Hello->JAVA->C++";

        List<String> string = getString(x);
        System.out.println(string);

        List<Integer> integer = getInteger(1,2);
        System.out.println(integer);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        System.out.println(new Date()+":"+calendar.getTime());

        BigDecimal bigDecimal = new BigDecimal(20);

        BigDecimal bigDecimal1 = new BigDecimal(20);

        System.err.println(bigDecimal.compareTo(bigDecimal1));


        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.MONTH, -5);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(instance.getTime()));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        c.add(Calendar.MINUTE, 1);
        Calendar currentTimes = Calendar.getInstance();
        currentTimes.set(Calendar.SECOND,0);
        currentTimes.set(Calendar.MILLISECOND,0);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTimes.getTime())+"-"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));

    }
    private static List<String> getString(String... str){
        return Lists.newArrayList(str);
    }

    private static List<Integer> getInteger(Integer... t){
        return Lists.newArrayList(t);
    }

    private static List<?> getObjcet(Object... obj){
        return Lists.newArrayList(obj);
    }
}
