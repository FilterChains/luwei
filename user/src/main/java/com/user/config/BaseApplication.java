package com.user.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class BaseApplication {

    @Bean
    @LoadBalanced //开启负载均衡的功能
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Redisson redisson() throws IOException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.3.111:6379")
                .setPassword("n1yEl39pMQZkH").setDatabase(13);
        return (Redisson) Redisson.create(config);
    }
}
