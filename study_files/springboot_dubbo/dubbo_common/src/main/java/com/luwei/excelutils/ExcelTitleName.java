package com.luwei.excelutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.excelutils
 * @auther: luwei
 * @description: excel annotation 注:在不使用index的情况下实体类字段顺序和表中表头顺序一致(字段可缺省),
 * @description: 在使用了index后，index标记必须与表中表头下标一致，而实体类字段顺序可变(字段可缺省)
 * @date: 2020/2/19 19:38
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTitleName {
    String value();
    int index() default -1;
}
