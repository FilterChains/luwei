package com.luwei.supermarket.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luwei.supermarket.admin.entity.po.Product;
import com.luwei.supermarket.admin.entity.vo.ProductSearchVO;

import java.util.List;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.service.product
 * @auther: luwei
 * @description: 商品服务
 * @date: 2020/5/13 22:42
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public interface ProductService extends IService<Product> {

    /**
     * @Title: validateProduct
     * @Description: 验证商品名称
     * @Param: productName ->商品名称 参数
     * @Return: Product 返回类型
     * @Date: 2020/5/17 12:02
     */
    Product validateProduct(String productName);

    /**
     * @Title: searchProductList
     * @Description: 商品搜索列表信息
     * @Param: [productSearchVO]   参数
     * @Return: List<Product>  返回类型
     * @Date: 2020/5/20 0:50
     */
    List<Product> searchProductList(ProductSearchVO productSearchVO);

    /**
     * @Title: searchProductListTotalPages
     * @Description: 商品搜索总条数
     * @Param: [productSearchVO]   参数
     * @Return: java.lang.Integer   返回类型
     * @Date: 2020/5/20 0:51
     */
    Integer searchProductListTotalPages(ProductSearchVO productSearchVO);
}
