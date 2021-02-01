package com.order.controller;

import com.order.entity.Order;
import com.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order/{string}", method = RequestMethod.GET)
    public String test(@PathVariable String string) {
        return orderService.list().toString();
        // List<Order> list = orderService.list();
        // String result = CollectionUtils.isEmpty(list) ? string : JSON.toJSONString(list);
        //return "Hello Nacos This is OrderService :" + string;
    }


    @PostMapping(value = "add")
    public String addOrder(){

        orderService.save(Order.builder()
                .money(BigDecimal.valueOf(100L)).orderStatus(100)
                .productId(100).userId(100).count(100)
                .build());
        return "SUCCESS";
    }
}
