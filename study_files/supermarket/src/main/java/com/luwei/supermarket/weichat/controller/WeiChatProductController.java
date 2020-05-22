package com.luwei.supermarket.weichat.controller;

import com.luwei.supermarket.admin.business.product.ProductBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.admin.controller.product
 * @auther: luwei
 * @description:
 * @date: 2020/5/14 23:18
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Valid
@RestController
@RequestMapping("/product")
public class WeiChatProductController {

    @Autowired
    private ProductBusiness productBusiness;


}
