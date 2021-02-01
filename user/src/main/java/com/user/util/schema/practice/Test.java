package com.user.util.schema.practice;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("小念");
        person.setSex((byte)0);
        person.setIdCard("100234955222152637");
        ProductService productService = new ProductService();
        // 添加观察者
        productService.addObserver(new ProductServiceImpl());
        // 开始观察
        productService.obServiceFunction(person);
        StringUtils.isEmpty("");
        StringUtils.isBlank("");

        System.out.println(DateFormatUtils.format(getBeforeDay(-393),"yyyy-MM-dd HH:mm:ss"));
        boolean temp = 0 > BigDecimal.ZERO.compareTo(BigDecimal.valueOf(1));
        System.out.println("实收服务费:"+temp);
        System.out.println(Integer.parseInt("999999999"));
    }

    private static Date getBeforeDay(final int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        calendar.add(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
