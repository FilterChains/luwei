package com.luwei.supermarket.service.product;

import com.luwei.supermarket.base.SuperService;
import com.luwei.supermarket.entity.po.Product;
import com.luwei.supermarket.entity.vo.ProductSearchVO;

import java.util.List;
import java.util.Map;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.service.product
 * @auther: luwei
 * @description: 商品服务
 * @date: 2020/5/13 22:42
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public interface ProductService extends SuperService<Product> {

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
     * @Param: [productSearchVO, flag]   参数
     * @Return: List<Product>  返回类型
     * @Date: 2020/5/20 0:50
     */
    List<Product> searchProductList(ProductSearchVO productSearchVO, boolean flag);

    /**
     * @Title: searchProductListTotalPages
     * @Description: 商品搜索总条数
     * @Param: [productSearchVO, flag]   参数
     * @Return: java.lang.Integer   返回类型
     * @Date: 2020/5/20 0:51
     */
    Integer searchProductListTotalPages(ProductSearchVO productSearchVO, boolean flag);

    /**
     * @Title: findProductMsg
     * @Description: 根据商品ID查询商品信息
     * @Param: [idList]   参数
     * @Return: Map  返回类型
     * @Date: 2020/5/24 14:57
     */
    Map<Integer, Product> findProductMsg(List<Integer> idList);

    /**
     * @Title: findProductCategory
     * @Description: 查询商品中是否有此分类
     * @Param: [categoryId]   参数
     * @Return: List<Product>  返回类型
     * @Date: 2020/5/24 15:40
     */
    List<Product> findProductCategory(Integer categoryId);
}
