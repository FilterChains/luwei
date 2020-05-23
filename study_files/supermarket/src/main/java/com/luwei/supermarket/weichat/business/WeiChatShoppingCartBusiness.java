package com.luwei.supermarket.weichat.business;

import com.luwei.supermarket.base.BaseBusiness;
import com.luwei.supermarket.entity.bo.request.WcShoppingCartSearchRequest;
import com.luwei.supermarket.entity.bo.response.WcShoppingCartSearchResponse;
import com.luwei.supermarket.entity.po.ShoppingCart;
import com.luwei.supermarket.service.product.ShoppingCartService;
import com.luwei.supermarket.util.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.weichat.business
 * @auther: luwei
 * @description:
 * @date: 2020/5/23 23:13
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class WeiChatShoppingCartBusiness extends BaseBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeiChatProductOrderBusiness.class);

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * @Title: getShoppingCart
     * @Description: 获取购物车信息
     * @Param: [request]   参数
     * @Return: List<WcShoppingCartSearchResponse>> 返回类型
     * @Date: 2020/5/24 1:46
     */
    public Notify<List<WcShoppingCartSearchResponse>> getShoppingCart(WcShoppingCartSearchRequest request) {
        final Integer userId = request.getUserId();
        final Integer pageNo = request.getPageNo();
        Integer pageSize = request.getPageSize();
        Integer listTotalPage = shoppingCartService.getShoppingCartListTotalPage(userId);
        List<WcShoppingCartSearchResponse> list = new ArrayList<>();
        if (0 < listTotalPage) {
            List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartList(userId, pageNo, pageSize > listTotalPage ? listTotalPage : pageSize);
            if (!CollectionUtils.isEmpty(shoppingCartList)) {
                shoppingCartList.forEach(sp -> {
                    WcShoppingCartSearchResponse wcShoppingCartSearchResponse = new WcShoppingCartSearchResponse();
                    wcShoppingCartSearchResponse.setId(sp.getId());
                    wcShoppingCartSearchResponse.setProductId(sp.getProductId());
                    wcShoppingCartSearchResponse.setProductName(sp.getProductName());
                    wcShoppingCartSearchResponse.setProductImageUrl(sp.getProductImageUrl());
                    wcShoppingCartSearchResponse.setProductNumber(sp.getProductNumber());
                    wcShoppingCartSearchResponse.setProductPrice(sp.getProductPrice());
                    wcShoppingCartSearchResponse.setTotalPage(listTotalPage);
                    list.add(wcShoppingCartSearchResponse);
                });
            }
        }
        return new Notify<>(Notify.Code.SUCCESS, list);
    }
}
