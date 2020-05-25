package com.luwei.supermarket.base;

import java.lang.annotation.*;

/**
 * <p>@Description : 切换数据源注解</p>
 * <p>@Author : luwei</p>
 * <p>@Date : 2018/11/11 9:10 </p>
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String value() default DataSourceContext.MASTER;

}
