package com.product.controller;

import com.alibaba.fastjson.JSON;
import com.product.entity.Product;
import com.product.service.MQConsumerService;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MQConsumerService mqConsumerService;

    @RequestMapping(value = "/product/{string}", method = RequestMethod.GET)
    public String test(@PathVariable String string) throws Exception {
        // throw new Exception("product is Filaed");
        List<Product> list = productService.list();
        String result = CollectionUtils.isEmpty(list) ? string : JSON.toJSONString(list);
        return "Hello Nacos This is ProductService :" + result;
    }

    @PostMapping(value = "/add")
    public String addProduct() {
        productService.save(Product.builder()
                .productId(200).residue(200).total(200).used(200)
                .build());
        return "SUCCESS";
    }
}
