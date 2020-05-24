package com.luwei.supermarket.controller.weichat;

import com.luwei.supermarket.base.BaseController;
import com.luwei.supermarket.business.weichat.WeiChatProductBusiness;
import com.luwei.supermarket.entity.bo.request.WcProductSearchRequest;
import com.luwei.supermarket.entity.bo.response.ProductCategoryResponse;
import com.luwei.supermarket.entity.bo.response.ProductListResponse;
import com.luwei.supermarket.entity.po.ProductCategory;
import com.luwei.supermarket.util.Notify;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.admin.controller.product
 * @auther: luwei
 * @description:
 * @date: 2020/5/14 23:18
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Api(tags = "微信端商品接口")
@Validated
@RestController
@RequestMapping("/wcProduct")
public class WeiChatProductController extends BaseController {

    @Autowired
    private WeiChatProductBusiness weiChatProductBusiness;

    @PostMapping("/type")
    @ApiOperation(value = "获取所有商品分类", notes = "获取所有商品分类")
    public Notify<List<ProductCategoryResponse>> getProductType() {
        return super.getCorrespondingType(ProductCategory.ProductCategoryType.FIRST_LEVEL);
    }

    @PostMapping("/listBody")
    @ApiOperation(value = "获取商品分类对应商品", notes = "获取商品分类对应商品")
    public Notify<List<ProductListResponse>> getProduct(@RequestBody WcProductSearchRequest request) {
        return weiChatProductBusiness.getProductMsg(request);
    }
}
