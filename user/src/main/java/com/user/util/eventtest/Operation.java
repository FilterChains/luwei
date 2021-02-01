package com.user.util.eventtest;

import com.google.common.base.Splitter;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class Operation {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i * j + " ");
            }
            System.out.println("");
        }
        int[][] num = new int[10][30];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 30; j++) {
                num[i][j] = j;
            }
        }
        String province = "重庆  南岸-重庆-南岸-重庆 —南岸—重庆—南岸";
        List<String> list1 = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(province);
        List<String> list2 = Splitter.on("—").omitEmptyStrings().trimResults().splitToList(province);
        List<String> list3 = Splitter.on(" ").omitEmptyStrings().trimResults().splitToList(province);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
        System.out.println(list1.size());
        System.out.println(list2.size());
        System.out.println(list3.size());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("12312").append(",");
        if(!StringUtils.isEmpty(stringBuilder.toString())){
            stringBuilder.insert(0,",");
        }
        System.out.println(stringBuilder.toString());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTime());

        System.out.println("价格："+new BigDecimal("2.5").multiply(BigDecimal.valueOf(2)));

        Calendar c = Calendar.getInstance();
        c.setTime(Calendar.getInstance().getTime());
        c.add(Calendar.DAY_OF_MONTH, -30);
        System.out.println("之前多少天"+ DateFormatUtils.format(c.getTime(),"yyyy-MM-dd HH:mm:ss"));



        System.out.println("总退款金额:"+BigDecimal.valueOf(0L).divide(BigDecimal.TEN));

        String td="";
        //System.out.println("String强转Integer"+Integer.parseInt(td));

        System.out.println(0> BigDecimal.ZERO.compareTo(BigDecimal.valueOf(1)));
    }
}
