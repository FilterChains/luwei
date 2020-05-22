package com.luwei.supermarket.service.product;

import com.luwei.supermarket.admin.entity.po.ProductCategory;
import com.luwei.supermarket.base.SuperService;

import java.util.List;
import java.util.Map;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.service.product
 * @auther: luwei
 * @description: 商品分类服务
 * @date: 2020/5/14 23:03
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public interface ProductCategoryService extends SuperService<ProductCategory> {

    /**
     * @Title: findProductCategory
     * @Description: 根据分类等级查询分类
     * @Param: [productCategoryType]   参数
     * @Return: List<ProductCategory>  返回类型
     * @Date: 2020/5/18 23:47
     */
    List<ProductCategory> findProductCategory(ProductCategory.ProductCategoryType productCategoryType);

    /**
     * @Title: vagueSearchProductType
     * @Description: 模糊搜索商品类型
     * @Param: [type]   参数
     * @Return: java.util.List<java.lang.Integer>   返回类型
     * @Date: 2020/5/20 1:06
     */
    List<Integer> vagueSearchProductType(String type);

    /**
     * @Title: findProductTypeAll
     * @Description: 查询所有商品类型
     * @Return: Map 返回类型
     * @Date: 2020/5/22 1:35
     */
    Map<Integer, ProductCategory> findProductTypeAll();

}
