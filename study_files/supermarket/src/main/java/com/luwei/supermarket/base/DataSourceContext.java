package com.luwei.supermarket.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>@Description : 数据源上下文</p>
 * <p>@Author : luwei </p>
 * <p>@Date : 2018/11/11 8:58 </p>
 */
public class DataSourceContext {

    public static final String MASTER = "master";

    public static final String SLAVE = "slave";

    private static Logger logger = LoggerFactory.getLogger(DataSourceContext.class);

    /**
     * 线程级别的私有变量
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 设置数据源
     *
     * @param dataSourceName
     */
    public static void setDataSource(String dataSourceName) {
        logger.debug("切换至{}数据源", dataSourceName);
        CONTEXT_HOLDER.set(dataSourceName);
    }

    /**
     * 移除数据源
     */
    public static void cleanDataSource() {
        CONTEXT_HOLDER.remove();
    }

}
