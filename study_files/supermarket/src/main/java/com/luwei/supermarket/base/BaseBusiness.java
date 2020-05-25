package com.luwei.supermarket.base;

import com.luwei.supermarket.service.product.ProductCategoryService;
import com.luwei.supermarket.service.product.ProductService;
import com.luwei.supermarket.service.region.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.base
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 01:39
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class BaseBusiness {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private DistrictService districtService;

}
