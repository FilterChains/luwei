package com.luwei.supermarket.controller.weichat;

import com.luwei.supermarket.base.BaseController;
import com.luwei.supermarket.business.weichat.WeiChatShoppingCartBusiness;
import com.luwei.supermarket.entity.bo.request.WcShoppingCartCleanRequest;
import com.luwei.supermarket.entity.bo.request.WcShoppingCartRequest;
import com.luwei.supermarket.entity.bo.request.WcShoppingCartSearchRequest;
import com.luwei.supermarket.entity.bo.response.WcShoppingCartSearchResponse;
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

    @PostMapping("/listCart")
    @ApiOperation(value = "获取购物车信息", notes = "获取购物车信息")
    public Notify<List<WcShoppingCartSearchResponse>> getCart(@RequestBody @Valid WcShoppingCartSearchRequest request) {
        return weiChatShoppingCartBusiness.getShoppingCart(request);
    }

    @PostMapping("/addCart")
    @ApiOperation(value = "添加购物车", notes = "添加购物车")
    public Notify<String> createCart(@RequestBody @Valid WcShoppingCartRequest request) {
        return weiChatShoppingCartBusiness.createShoppingCart(request);
    }

    @PostMapping("/cleanCart")
    @ApiOperation(value = "清空/删除购物车", notes = "清空/删除购物车")
    public Notify<String> cleanCart(@RequestBody @Valid WcShoppingCartCleanRequest request) {
        return weiChatShoppingCartBusiness.cleanShoppingCart(request);
    }

    @PostMapping("/updateCart")
    @ApiOperation(value = "修改购物车", notes = "修改购物车")
    public Notify<String> updateCart(@RequestBody @Valid WcShoppingCartRequest request) {
        return weiChatShoppingCartBusiness.updateShoppingCart(request);
    }
}
