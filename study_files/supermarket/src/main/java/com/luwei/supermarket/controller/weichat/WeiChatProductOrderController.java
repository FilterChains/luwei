package com.luwei.supermarket.controller.weichat;

import com.luwei.supermarket.base.BaseController;
import com.luwei.supermarket.business.weichat.WeiChatProductOrderBusiness;
import com.luwei.supermarket.entity.bo.request.WcOrderRequest;
import com.luwei.supermarket.util.Notify;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


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

    @PostMapping("/createOrder")
    @ApiOperation(value = "创建订单", notes = "创建订单")
    public Notify<String> addOrder(@RequestBody @Valid WcOrderRequest request) {
        return weiChatProductOrderBusiness.createOrder(request);
    }

}
