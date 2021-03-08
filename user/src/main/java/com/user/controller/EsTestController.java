package com.user.controller;

import com.user.entity.Product;
import com.user.entity.TdProduct;
import com.user.service.EsSearchService;
import com.user.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EsTestController {

    @Autowired // "商品Es"
    private EsSearchService esSearchService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "saveProduct/{id}")
    public String saveProduct(@PathVariable String id) {
        List<TdProduct> limitProduct = productService.findLimitProduct();
        List<Product> products = new ArrayList<>();
        Product product;
        for (TdProduct tdProduct : limitProduct) {
            product = new Product();
            BeanUtils.copyProperties(tdProduct, product);
            product.setId(String.valueOf(tdProduct.getId()));
            products.add(product);
        }
        esSearchService.saveAll(products);
        return "成功";
    }

    @GetMapping(value = "deleteProduct/{id}")
    public String deleteProduct(@PathVariable String id) {
        esSearchService.deleteById(id);
        return "删除成功";
    }

    @GetMapping(value = "findProduct/{id}")
    public Product findProduct(@PathVariable String id) {
        return esSearchService.findById(id).orElse(new Product());
    }
}
