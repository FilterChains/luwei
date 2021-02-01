package com.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "product",url = "http://127.0.0.1:9124")
public interface ProductServiceFeign {

    @GetMapping(value = "/product/{string}")
    String getProduct(@PathVariable(value = "string") String string);

    @PostMapping(value = "/add")
    String createProduct();
}
