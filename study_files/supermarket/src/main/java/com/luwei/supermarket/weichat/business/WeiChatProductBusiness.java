package com.luwei.supermarket.weichat.business;

import com.google.common.collect.Lists;
import com.luwei.supermarket.base.BaseBusiness;
import com.luwei.supermarket.entity.bo.request.WcProductSearchRequest;
import com.luwei.supermarket.entity.bo.response.ProductListResponse;
import com.luwei.supermarket.entity.po.Product;
import com.luwei.supermarket.entity.vo.ProductSearchVO;
import com.luwei.supermarket.service.product.ProductService;
import com.luwei.supermarket.util.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.admin.business.product
 * @auther: luwei
 * @description:
 * @date: 2020/5/14 23:16
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class WeiChatProductBusiness extends BaseBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeiChatProductBusiness.class);

    @Autowired
    private ProductService productService;

    /**
     * @Title: getProductMsg
     * @Description: 获取商品分类对应商品
     * @Param: [request]   参数
     * @Return: List<ProductListResponse> 返回类型
     * @Date: 2020/5/23 0:58
     */
    public Notify<List<ProductListResponse>> getProductMsg(final WcProductSearchRequest request) {
        final Integer type = request.getTypeId();
        Integer pageNo = request.getPageNo();
        Integer pageSize = request.getPageSize();
        ProductSearchVO build = ProductSearchVO.builder().pageNo(pageNo).typeList(Lists.newArrayList(type)).build();
        Integer totalPages = productService.searchProductListTotalPages(build);
        List<ProductListResponse> list = new ArrayList<>();
        if (0 < totalPages) {
            build.setPageSize(pageSize > totalPages ? totalPages : pageSize);
            List<Product> productList = productService.searchProductList(build);
            if (!CollectionUtils.isEmpty(productList)) {
                productList.forEach(p -> {
                    ProductListResponse response = new ProductListResponse();
                    response.setId(p.getId());
                    response.setProductName(p.getProductName());
                    response.setProductAddress(p.getProductAddress());
                    response.setProductPrice(p.getProductPrice());
                    response.setProductStock(p.getProductStock());
                    response.setProductImagesUrl(p.getProductImagesUrl());
                    response.setProductUnit(p.getProductUnit());
                    response.setProductRemark(p.getProductRemark());
                    response.setTotalPage(totalPages);
                    list.add(response);
                });
            }
        }
        return new Notify<>(Notify.Code.SUCCESS, list);
    }
}
