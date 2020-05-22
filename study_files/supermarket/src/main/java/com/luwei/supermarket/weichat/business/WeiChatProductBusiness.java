package com.luwei.supermarket.weichat.business;

import com.luwei.supermarket.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.admin.business.product
 * @auther: luwei
 * @description:
 * @date: 2020/5/14 23:16
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class WeiChatProductBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeiChatProductBusiness.class);

    @Autowired
    private ProductService productService;
}
