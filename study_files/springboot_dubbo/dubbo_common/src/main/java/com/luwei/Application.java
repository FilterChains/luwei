package com.luwei;

import com.luwei.redisconfig.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @projectName： springbootdubbo
 * @packageName: com.dubbo.common.redisconfig
 * @auther: luwei
 * @description: 引入了spring-boot-maven-plugin，打包时会去扫描项目main方法入口，也就是说引入该配置，你就必须在项目src/main/java/下创建一个spring-boot启动类：
 * @date: 2020/1/27 16:09
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
