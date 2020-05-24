package com.luwei.supermarket.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luwei.supermarket.base.SuperServiceImpl;
import com.luwei.supermarket.entity.po.Product;
import com.luwei.supermarket.entity.vo.ProductSearchVO;
import com.luwei.supermarket.mapper.ProductMapper;
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
 * @date: 2020/5/14 23:00
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class ProductServiceImpl extends SuperServiceImpl<ProductMapper, Product>
        implements ProductService {

    @Override
    public Product validateProduct(String productName) {
        LambdaQueryWrapper<Product> lambda = new QueryWrapper<Product>().lambda();
        return baseMapper.selectOne(lambda.eq(Product::getProductName, productName)
                .last("limit 1"));
    }

    @Override
    public List<Product> searchProductList(ProductSearchVO productSearchVO, boolean flag) {
        LambdaQueryWrapper<Product> lambda = new QueryWrapper<Product>().lambda();
        final String name = productSearchVO.getName();
        final List<Integer> typeList = productSearchVO.getTypeList();
        final String region = productSearchVO.getRegion();
        IPage<Product> page = new Page<>(productSearchVO.getPageNo(), productSearchVO.getPageSize(), false);
        return baseMapper.selectPage(page,
                lambda.like(StringUtils.isNotEmpty(name), Product::getProductName, name)
                        .in(!CollectionUtils.isEmpty(typeList), Product::getProductType, typeList)
                        .eq(StringUtils.isNotEmpty(region), Product::getProductAddress, region)
                        .eq(flag, Product::getProductStatus, Product.ProductStatus.PUT_AWAY)
                        .orderByDesc(Product::getUpdateTime).orderByAsc(Product::getId)).getRecords();
    }

    @Override
    public Integer searchProductListTotalPages(ProductSearchVO productSearchVO, boolean flag) {
        LambdaQueryWrapper<Product> lambda = new QueryWrapper<Product>().lambda();
        final String name = productSearchVO.getName();
        final List<Integer> typeList = productSearchVO.getTypeList();
        final String region = productSearchVO.getRegion();
        return baseMapper.selectCount(lambda.like(StringUtils.isNotEmpty(name), Product::getProductName, name)
                .in(!CollectionUtils.isEmpty(typeList), Product::getProductType, typeList)
                .eq(StringUtils.isNotEmpty(region), Product::getProductAddress, region)
                .eq(flag, Product::getProductStatus, Product.ProductStatus.PUT_AWAY));
    }

    @Override
    public Map<Integer, Product> findProductMsg(List<Integer> idList) {
        LambdaQueryWrapper<Product> lambda = new QueryWrapper<Product>().lambda();
        List<Product> products = baseMapper.selectList(lambda.in(Product::getId, idList));
        return CollectionUtils.isEmpty(products) ? Collections.emptyMap() :
                products.stream().collect(Collectors.toMap(Product::getId, Product -> Product));
    }

    @Override
    public List<Product> findProductCategory(Integer categoryId) {
        LambdaQueryWrapper<Product> lambda = new QueryWrapper<Product>().lambda();
        return baseMapper.selectList(lambda.eq(Product::getProductType, categoryId));
    }
}
