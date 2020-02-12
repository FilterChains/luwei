package com.luwei.dubbo_provider;

import com.luwei.dubbo_provider.rabbitmqconfig.RabbitMqConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

@MapperScan("com.luwei.dubbo_provider.mapper")
@ImportResource("classpath:spring-dubbo.xml")
@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
        SpringApplication.run(RabbitMqConfig.class, args); //rabbitmq配置类
        System.out.println("服务端启动成功！！！！！！！！！！！！！！");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
