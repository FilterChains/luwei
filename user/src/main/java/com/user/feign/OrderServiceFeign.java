package com.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "order",url = "http://127.0.0.1:9125")
public interface OrderServiceFeign {

    @GetMapping(value = "/order/{string}")
    String findOrder(@PathVariable(value = "string") String string);

    @PostMapping(value = "add")
     String createOrder();
}
