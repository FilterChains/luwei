package com.luwei.supermarket.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luwei.supermarket.base.SuperServiceImpl;
import com.luwei.supermarket.entity.po.ShoppingCart;
import com.luwei.supermarket.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.service.product
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 00:48
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class ShoppingCartServiceImpl extends SuperServiceImpl<ShoppingCartMapper, ShoppingCart>
        implements ShoppingCartService {

    @Override
    public List<ShoppingCart> getShoppingCartList(Integer userId, Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<ShoppingCart> lambda = new QueryWrapper<ShoppingCart>().lambda();
        IPage<ShoppingCart> page = new Page<>(pageNo, pageSize, false);
        return baseMapper.selectPage(page, lambda
                .eq(ShoppingCart::getCreateBy, userId)
                .orderByDesc(ShoppingCart::getCreateTime)).getRecords();
    }

    @Override
    public Integer getShoppingCartListTotalPage(Integer userId) {
        return baseMapper.selectCount(new QueryWrapper<ShoppingCart>().lambda()
                .eq(ShoppingCart::getCreateBy, userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cleanUserShoppingCart(Integer userId) {
        baseMapper.delete(new QueryWrapper<ShoppingCart>().lambda()
                .eq(ShoppingCart::getCreateBy, userId));
    }
}
