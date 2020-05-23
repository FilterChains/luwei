package com.luwei.supermarket.service.product;

import com.luwei.supermarket.base.SuperService;
import com.luwei.supermarket.entity.po.ShoppingCart;

import java.util.List;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.service.product
 * @auther: luwei
 * @description: 购物车服务
 * @date: 2020/5/24 00:48
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public interface ShoppingCartService extends SuperService<ShoppingCart> {

    /**
     * @Title: getShoppingCartList
     * @Description: 购物车搜索
     * @Param: [userId, pageNo, pageSize]   参数
     * @Return: List<ShoppingCart>  返回类型
     * @Date: 2020/5/24 1:55
     */
    List<ShoppingCart> getShoppingCartList(Integer userId, Integer pageNo, Integer pageSize);

    /**
     * @Title: getShoppingCartList
     * @Description: 购物车搜索总条数
     * @Param: [userId]  参数
     * @Return: List<ShoppingCart>  返回类型
     * @Date: 2020/5/24 1:55
     */
    Integer getShoppingCartListTotalPage(Integer userId);

    /**
     * @Title: cleanUserShoppingCart
     * @Description: 清空用户购物车
     * @Param: [userId]   参数
     * @Return: void   返回类型
     * @Date: 2020/5/24 2:13
     */
    void cleanUserShoppingCart(Integer userId);
}
