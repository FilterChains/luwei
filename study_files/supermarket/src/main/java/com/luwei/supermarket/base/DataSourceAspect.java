package com.luwei.supermarket.base;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/**
 * <p>@Description : AOP动态切换数据源</p>
 * <p>@Author : luwei </p>
 * <p>@Date : 2018/11/11 9:15 </p>
 */
@Aspect
@Order(0)
public class DataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    @Before("@annotation(dataSource)")
    public void changeDataSource(JoinPoint point, DataSource dataSource) {
        String dataSourceId = dataSource.value();
        LOGGER.debug("Use DataSource:{} > {}", dataSourceId, point.getSignature());
        DataSourceContext.setDataSource(dataSourceId);
    }

    @After("@annotation(dataSource)")
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
        LOGGER.debug("Remove DataSource : {} > {}", dataSource.value(), point.getSignature());
        DataSourceContext.cleanDataSource();
    }
}
