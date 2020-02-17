package com.luwei.excelutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName： EasyExcelUtil
 * @packageName: com.excel.util
 * @auther: luwei
 * @description: excel annotation
 * @date: 2019/10/20 16:04
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTitleName {
    String value();
    int index() default -1;
}
