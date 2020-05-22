package com.luwei.supermarket.weichat.controller;

import com.luwei.supermarket.admin.business.product.ProductBusiness;
import com.luwei.supermarket.base.BaseController;
import com.luwei.supermarket.entity.bo.request.WcProductSearchRequest;
import com.luwei.supermarket.entity.bo.response.ProductCategoryResponse;
import com.luwei.supermarket.entity.bo.response.ProductListResponse;
import com.luwei.supermarket.entity.po.ProductCategory;
import com.luwei.supermarket.util.Notify;
import com.luwei.supermarket.weichat.business.WeiChatProductBusiness;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@Valid
@RestController
@RequestMapping("/wcProduct")
public class WeiChatProductController extends BaseController {

    @Autowired
    private WeiChatProductBusiness weiChatProductBusiness;

    @ResponseBody
    @PostMapping("/type")
    @ApiOperation(value = "获取所有商品分类", notes = "获取所有商品分类")
    public Notify<List<ProductCategoryResponse>> getProductType(){
        return super.getCorrespondingType(ProductCategory.ProductCategoryType.FIRST_LEVEL);
    }

    @ResponseBody
    @PostMapping("/listBody")
    @ApiOperation(value = "获取商品分类对应商品", notes = "获取商品分类对应商品")
    public Notify<List<ProductListResponse>> getProduct(@RequestBody WcProductSearchRequest request){
        return weiChatProductBusiness.getProductMsg(request);
    }
}
