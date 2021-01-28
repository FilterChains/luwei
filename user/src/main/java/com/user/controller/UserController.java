package com.user.controller;

import com.alibaba.fastjson.JSON;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("user/{string}")
    @GlobalTransactional
    public String test(@PathVariable String string) {

        // 新增数据
        Map<String, String> hashMap = new HashMap<>();
        restTemplate.postForLocation("http://product/add/",JSON.toJSONString(hashMap));

        String fromProduct = restTemplate.getForObject("http://product/product/" + string, String.class);
        String fromOrder = restTemplate.getForObject("http://order/order/" + string, String.class);
        return "访问商品服务:".concat(fromProduct).concat("—————访问订单服务:").concat(fromOrder);
    }
}
