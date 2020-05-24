package com.luwei.supermarket.service.product;

import com.luwei.supermarket.base.SuperService;
import com.luwei.supermarket.entity.po.Order;
import com.luwei.supermarket.entity.vo.OrderCreateVO;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.service.product
 * @auther: luwei
 * @description: 订单服务
 * @date: 2020/5/24 00:53
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public interface OrderService extends SuperService<Order> {

    /**
     * @Title: createOrder
     * @Description: 创建订单->同步方法
     * @Param: [orderCreateVO]   参数
     * @Return: String   返回类型
     * @Date: 2020/5/24 23:02
     */
    String createOrder(OrderCreateVO orderCreateVO);
}
