package com.luwei.supermarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.luwei.supermarket.mapper")
@EnableTransactionManagement(proxyTargetClass = true) // 开启数据库事务管理
public class SupermarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupermarketApplication.class, args);
    }
}
