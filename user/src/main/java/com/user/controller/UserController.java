package com.user.controller;

import com.user.feign.OrderServiceFeign;
import com.user.feign.ProductServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@Slf4j
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductServiceFeign productServiceFeign;

    @Autowired
    private OrderServiceFeign orderServiceFeign;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("user/{string}")
    public String test(@PathVariable String string) {

        // 新增数据
        // Map<String, String> hashMap = new HashMap<>();
        // restTemplate.postForLocation("http://product/add/",JSON.toJSONString(hashMap));
        return "User服务响应:" + string;

       /* String fromOrder = restTemplate.getForObject("http://order/order/" + string, String.class);
        String fromProduct = restTemplate.getForObject("http://product/product/" + string, String.class);
        return "访问商品服务:".concat(fromProduct).concat("—————访问订单服务:").concat(fromOrder);*/
    }


    @GetMapping(value = "msg/{id}")
    public String cloudFunction(@PathVariable long id){

        String product = productServiceFeign.getProduct(String.valueOf(id));

        String order = orderServiceFeign.findOrder(String.valueOf(id));

        return "Product：".concat(product).concat("--------Order:").concat(order);
    }

    @GetMapping(value = "po")
    // @GlobalTransactional(name = "用户端调用商品服务和订单服务",rollbackFor = Exception.class)
    public String createMsg(){
        String port = applicationContext.getEnvironment().getProperty("server.port");
        log.error("当前USER:"+port);
        String product = productServiceFeign.createProduct();
        String order = orderServiceFeign.createOrder();
        return "Product：".concat(product).concat("--------Order:").concat(order);
    }
}
