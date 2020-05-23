package com.luwei.supermarket.service.product;

import com.luwei.supermarket.base.SuperServiceImpl;
import com.luwei.supermarket.entity.po.Order;
import com.luwei.supermarket.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.service.product
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 00:53
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class OrderServiceImpl extends SuperServiceImpl<OrderMapper, Order>
        implements OrderService {
}
