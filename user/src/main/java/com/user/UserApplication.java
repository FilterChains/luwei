package com.user;

import com.user.config.BaseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserApplication extends BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}

