package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("user/{string}")
    // @GlobalTransactional
    public String test(@PathVariable String string) {
        String fromProduct = restTemplate.getForObject("http://product/product/" + string, String.class);
        String fromOrder = restTemplate.getForObject("http://order/order/" + string, String.class);
        return "访问商品服务:".concat(fromProduct).concat("—————访问订单服务:").concat(fromOrder);
    }
}
