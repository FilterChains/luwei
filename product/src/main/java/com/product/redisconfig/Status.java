package com.product.redisconfig;

import java.util.concurrent.TimeUnit;

/**
 * @projectName： springbootdubbo
 * @packageName: com.dubbo.common.config
 * @auther: luwei
 * @description: setting redis time out
 * @date: 2020/1/24 23:11
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class Status {
    /**
     * 过期时间相关枚举
     */
    public static enum ExpireEnum {
        //未读消息的有效期为30天
        UNREAD_MSG(30L, TimeUnit.DAYS);

        /**
         * 过期时间
         */
        private Long time;
        /**
         * 时间单位
         */
        private TimeUnit timeUnit;

        ExpireEnum(Long time, TimeUnit timeUnit) {
            this.time = time;
            this.timeUnit = timeUnit;
        }

        public Long getTime() {
            return time;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
    }
}
