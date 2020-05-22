package com.luwei.supermarket.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>@Description : 切换数据源注解</p>
 * <p>@Author : QiLin.Xing </p>
 * <p>@Date : 2018/11/11 9:10 </p>
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String value() default DataSourceContext.MASTER;

}
