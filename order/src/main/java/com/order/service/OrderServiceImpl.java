package com.order.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.order.entity.Order;
import com.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order>
        implements OrderService {

}
