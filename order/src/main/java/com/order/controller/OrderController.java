package com.order.controller;

import com.alibaba.fastjson.JSON;
import com.order.entity.Order;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order/{string}", method = RequestMethod.GET)
    public String test(@PathVariable String string) {
        List<Order> list = orderService.list();
        String result = CollectionUtils.isEmpty(list) ? string : JSON.toJSONString(list);
        return "Hello Nacos This is OrderService :" +result ;
    }
}
