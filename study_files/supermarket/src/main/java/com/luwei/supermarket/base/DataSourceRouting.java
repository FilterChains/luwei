package com.luwei.supermarket.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

/**
 * <p>@Description : 动态数据源路由配置</p>
 * <p>@Author : QiLin.Xing </p>
 * <p>@Date : 2018/11/11 9:06 </p>
 */
public class DataSourceRouting extends AbstractRoutingDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceRouting.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DataSourceContext.getDataSource();
        LOGGER.debug("当前数据源是：{}", StringUtils.isEmpty(dataSourceName) ? DataSourceContext.MASTER : dataSourceName);
        return dataSourceName;
    }
}
