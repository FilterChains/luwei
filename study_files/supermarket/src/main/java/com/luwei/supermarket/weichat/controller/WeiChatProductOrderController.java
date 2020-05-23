package com.luwei.supermarket.weichat.controller;

import com.luwei.supermarket.base.BaseController;
import com.luwei.supermarket.weichat.business.WeiChatProductOrderBusiness;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.weichat.controller
 * @auther: luwei
 * @description:
 * @date: 2020/5/23 23:09
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Api(tags = "微信端订单接口")
@Validated
@RestController
@RequestMapping("/wcOrder")
public class WeiChatProductOrderController extends BaseController {

    @Autowired
    private WeiChatProductOrderBusiness weiChatProductOrderBusiness;


}
