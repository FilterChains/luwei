package com.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class BaseApplication {

    @Bean
    @LoadBalanced //开启负载均衡的功能
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
