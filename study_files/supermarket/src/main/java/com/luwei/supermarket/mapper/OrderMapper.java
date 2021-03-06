package com.luwei.supermarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luwei.supermarket.entity.po.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.mapper
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 00:52
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
