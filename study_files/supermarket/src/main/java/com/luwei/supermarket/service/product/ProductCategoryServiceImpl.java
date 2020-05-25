package com.luwei.supermarket.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luwei.supermarket.base.SuperServiceImpl;
import com.luwei.supermarket.entity.po.ProductCategory;
import com.luwei.supermarket.mapper.ProductCategoryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.service.product
 * @auther: luwei
 * @description:
 * @date: 2020/5/14 23:04
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class ProductCategoryServiceImpl extends SuperServiceImpl<ProductCategoryMapper, ProductCategory>
        implements ProductCategoryService {

    @Override
    public List<ProductCategory> findProductCategory(ProductCategory.ProductCategoryType productCategoryType) {
        LambdaQueryWrapper<ProductCategory> lambda = new QueryWrapper<ProductCategory>().lambda();
        return baseMapper.selectList(lambda.eq(ProductCategory::getType, productCategoryType.getIndex()));
    }

    @Override
    public List<Integer> vagueSearchProductType(String type) {
        if (StringUtils.isEmpty(type)) {
            return Collections.emptyList();
        }
        LambdaQueryWrapper<ProductCategory> lambda = new QueryWrapper<ProductCategory>().lambda();
        List<ProductCategory> productCategories = baseMapper.selectList(lambda.like(ProductCategory::getName, type));
        return CollectionUtils.isEmpty(productCategories) ? Collections.emptyList() :
                productCategories.stream().map(ProductCategory::getId).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, ProductCategory> findProductTypeAll() {
        List<ProductCategory> list = super.find(null);
        return CollectionUtils.isEmpty(list) ? Collections.<Integer, ProductCategory>emptyMap() :
                list.stream().collect(Collectors.toMap(ProductCategory::getId, ProductCategory -> ProductCategory));
    }
}
