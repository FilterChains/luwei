package com.user;

import com.user.config.EnableAutoBaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableAutoBaseConfig
public class UserApplication {

    public static void main(String[] args) {
        // 解决netty启动冲突的问题（主要体现在启动redis和elasticsearch）
        // 可以看Netty4Util.setAvailableProcessors(..)
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(UserApplication.class, args);
    }


}

