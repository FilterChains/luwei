package com.luwei.supermarket.weichat.controller;

import com.luwei.supermarket.base.BaseController;
import com.luwei.supermarket.entity.bo.request.WcShoppingCartSearchRequest;
import com.luwei.supermarket.entity.bo.response.WcShoppingCartSearchResponse;
import com.luwei.supermarket.util.Notify;
import com.luwei.supermarket.weichat.business.WeiChatShoppingCartBusiness;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.weichat.controller
 * @auther: luwei
 * @description:
 * @date: 2020/5/23 23:12
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Api(tags = "微信端购物车接口")
@Validated
@RestController
@RequestMapping("/wcShoppingCart")
public class WeiChatShoppingCartController extends BaseController {

    @Autowired
    private WeiChatShoppingCartBusiness weiChatShoppingCartBusiness;

    @ResponseBody
    @PostMapping("/listCart")
    @ApiOperation(value = "获取购物车信息", notes = "获取购物车信息")
    public Notify<List<WcShoppingCartSearchResponse>> getShopCart(@RequestBody @Valid WcShoppingCartSearchRequest request) {
        return weiChatShoppingCartBusiness.getShoppingCart(request);
    }

}
