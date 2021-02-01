package com.user.util;

import com.google.common.base.Splitter;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
    public static void main(String[] args) {
        //创建时间对象
        Date date = Calendar.getInstance().getTime();
        System.out.println("旧API:" + date);


        /**
         * ====================================时间获取======================================
         */

        // 获取日期值对象如 2020-03-11
        LocalDate localDate = LocalDate.now();
        System.out.println("新API:" + localDate);

        // 获取时间值对象如 17:24:02.342
        LocalTime localTime = LocalTime.now();
        System.out.println("新API:" + localTime);

        // 获取日期+时间值对象 2020-03-11T17:24:02.342
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("新API:" + localDateTime);

        // 获取日期+时间+时区值对象 2020-03-11T17:24:02.343+08:00[Asia/Shanghai]
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("新API:" + zonedDateTime);


        // 获取所有时区
        // Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        // System.out.println("新API:"+zoneIds);

        // 用于日期时间的格式化
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(zonedDateTime);
        System.out.println("新API:" + format);


        /**
         * ==================================时间计算===========================================
         */
        System.err.println("==============================================");
        // 创建一个两周的间隔
        Period periodWeeks = Period.ofWeeks(2);
        System.out.println("新API:" + periodWeeks);

        // 一年三个月零二天的间隔
        Period custom = Period.of(1, 3, 2);
        System.out.println("新API:" + custom);

        // 一天的时长
        Duration duration = Duration.ofDays(1);
        System.out.println("新API:" + duration);

        // 计算2019/3/10 号到现在 2020/3/11 过了多久，它这个把间隔分到每个 part 了
        LocalDate now = LocalDate.now();
        LocalDate customDate = LocalDate.of(2019, 3, 10);
        Period between = Period.between(customDate, now);
        // 结果为 1:0:1 即过去了 1年0个月1天了
        System.out.println(between.getYears() + ":" + between.getMonths() + ":" + between.getDays());
        // 比较两个瞬时的时间间隔
        Instant begin = Instant.now();
        Instant end = Instant.now();
        Duration.between(begin, end);

        // 同样可以修改 part 信息和设置 part 信息，都是返回新的对象来表示设置过的值，原来的对象不变
        Period plusDays = between.plusDays(1);
        Period withDays = between.withDays(4);
        System.err.println(Calendar.getInstance().getTime());
        System.err.println(new Date());

        String s = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36";
        List<String> strings = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(s);
        System.out.println("截取结果:" + strings);
    }
}
