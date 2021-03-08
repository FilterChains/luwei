package com.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.entity.TdProduct;
import com.user.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, TdProduct>
        implements ProductService {
    @Override
    public List<TdProduct> findLimitProduct() {
        // TODO 开始查询商品总库信息
        LambdaQueryWrapper<TdProduct> lambda = new QueryWrapper<TdProduct>().lambda();
        return baseMapper.selectList(lambda.last("limit 100,100"));
    }
}
