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
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<ShoppingCart> getShoppingCartList(final String userId,
                                                  final Integer pageNo,
                                                  final Integer pageSize) {
        LambdaQueryWrapper<ShoppingCart> lambda = new QueryWrapper<ShoppingCart>().lambda();
        IPage<ShoppingCart> page = new Page<>(pageNo, pageSize, false);
        return baseMapper.selectPage(page, lambda
                .eq(ShoppingCart::getCreateBy, userId)
                .orderByDesc(ShoppingCart::getCreateTime)).getRecords();
    }

    @Override
    public Integer getShoppingCartListTotalPage(final String userId) {
        return baseMapper.selectCount(new QueryWrapper<ShoppingCart>().lambda()
                .eq(ShoppingCart::getCreateBy, userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cleanShoppingCart(final String userId, final Integer productId) {
        baseMapper.delete(new QueryWrapper<ShoppingCart>().lambda()
                .eq(ShoppingCart::getCreateBy, userId)
                .eq(!ObjectUtils.isEmpty(productId), ShoppingCart::getProductId, productId));
    }

    @Override
    public ShoppingCart validateShoppingCart(final Integer productId, final String userId) {
        return baseMapper.selectOne(new QueryWrapper<ShoppingCart>().lambda()
                .eq(ShoppingCart::getProductId, productId)
                .eq(ShoppingCart::getCreateBy, userId).last("limit 1"));
    }

    @Override
    public Map<Integer, ShoppingCart> findShoppingCart(String userId) {
        LambdaQueryWrapper<ShoppingCart> lambda = new QueryWrapper<ShoppingCart>().lambda();
        List<ShoppingCart> shoppingCarts = baseMapper.selectList(lambda.eq(ShoppingCart::getCreateBy, userId));
        return CollectionUtils.isEmpty(shoppingCarts) ? Collections.EMPTY_MAP :
                shoppingCarts.stream().collect(Collectors.toMap(ShoppingCart::getProductId, ShoppingCart -> ShoppingCart));
    }
}
