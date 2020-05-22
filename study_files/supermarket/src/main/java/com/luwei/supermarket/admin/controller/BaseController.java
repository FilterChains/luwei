package com.luwei.supermarket.admin.controller;

import com.luwei.supermarket.admin.entity.bo.response.DistrictResponse;
import com.luwei.supermarket.admin.entity.bo.response.ProductCategoryResponse;
import com.luwei.supermarket.admin.entity.po.District;
import com.luwei.supermarket.admin.entity.po.ProductCategory;
import com.luwei.supermarket.service.product.ProductCategoryService;
import com.luwei.supermarket.service.region.DistrictService;
import com.luwei.supermarket.util.Notify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.admin.controller.user
 * @auther: luwei
 * @description:
 * @date: 2020/5/18 22:33
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class BaseController {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * @Title: getDistrict
     * @Description: 获取地区集合tree
     * @Return: List<DistrictResponse> 返回类型
     * @Date: 2020/5/18 22:38
     */
    protected Notify<List<DistrictResponse>> getDistrict() {
        List<District> provinceList = districtService.findDistrictLevel(District.DistrictType.FIRST_LEVEL);
        List<District> cityList = districtService.findDistrictLevel(District.DistrictType.SECOND_LEVEL);
        List<District> regionList = districtService.findDistrictLevel(District.DistrictType.THIRDLY_LEVEL);
        List<DistrictResponse> responses = new ArrayList<>();
        provinceList.forEach(p -> {
            DistrictResponse response = new DistrictResponse();
            response.setId(p.getId());
            response.setUpid(p.getUpid());
            response.setName(p.getName());
            response.setLevel(p.getLevel().getIndex());
            List<DistrictResponse> cityResponseList = new ArrayList<>();
            cityList.forEach(c -> {
                if (p.getId().equals(c.getUpid())) {
                    DistrictResponse cityResponse = new DistrictResponse();
                    cityResponse.setId(c.getId());
                    cityResponse.setName(c.getName());
                    cityResponse.setLevel(c.getLevel().getIndex());
                    cityResponse.setUpid(c.getUpid());
                    List<DistrictResponse> regionResponseList = new ArrayList<>();
                    regionList.forEach(r -> {
                        if (c.getId().equals(r.getUpid())) {
                            DistrictResponse regionResponse = new DistrictResponse();
                            regionResponse.setId(r.getId());
                            regionResponse.setName(r.getName());
                            regionResponse.setUpid(r.getUpid());
                            regionResponse.setLevel(r.getLevel().getIndex());
                            regionResponseList.add(regionResponse);
                        }
                    });
                    cityResponse.setChildren(regionResponseList);
                    cityResponseList.add(cityResponse);
                }
            });
            response.setChildren(cityResponseList);
            responses.add(response);
        });
        return new Notify<>(Notify.Code.SUCCESS, responses);
    }

    /**
     * @Title: getprovinceList
     * @Description: 获取所有一级地区
     * @Return: List<DistrictResponse>  返回类型
     * @Date: 2020/5/19 21:40
     */
    protected Notify<List<DistrictResponse>> getProvinceList() {
        List<District> provinceList = districtService.findDistrictLevel(District.DistrictType.FIRST_LEVEL);
        ArrayList<DistrictResponse> districtResponses = new ArrayList<>();
        if (!CollectionUtils.isEmpty(provinceList)) {
            provinceList.forEach(p -> {
                DistrictResponse districtResponse = new DistrictResponse();
                districtResponse.setName(p.getName());
                districtResponse.setId(p.getId());
                districtResponse.setUpid(p.getUpid());
                districtResponse.setLevel(p.getLevel().getIndex());
                districtResponses.add(districtResponse);
            });
        }
        return new Notify<>(Notify.Code.SUCCESS, districtResponses);
    }

    /**
     * @Title: getProductCategory
     * @Description: 获取商品分类
     * @Return: List<ProductCategoryResponse>  返回类型
     * @Date: 2020/5/18 23:40
     */
    protected Notify<List<ProductCategoryResponse>> getProductCategory() {
        List<ProductCategory> productCategory1 = productCategoryService.findProductCategory(ProductCategory.ProductCategoryType.FIRST_LEVEL);
        List<ProductCategory> productCategory2 = productCategoryService.findProductCategory(ProductCategory.ProductCategoryType.SECOND_LEVEL);
        List<ProductCategory> productCategory3 = productCategoryService.findProductCategory(ProductCategory.ProductCategoryType.THIRDLY_LEVEL);
        List<ProductCategoryResponse> responses = new ArrayList<>();
        productCategory1.forEach(f -> {
            ProductCategoryResponse response = new ProductCategoryResponse();
            response.setValue(f.getId());
            response.setLabel(f.getName());
            response.setPrev(f.getPrev());
            response.setType(f.getType().getIndex());
            List<ProductCategoryResponse> responses1 = new ArrayList<>();
            productCategory2.forEach(s -> {
                if (f.getId().equals(s.getPrev())) {
                    ProductCategoryResponse response1 = new ProductCategoryResponse();
                    response1.setPrev(s.getPrev());
                    response1.setValue(s.getId());
                    response1.setLabel(s.getName());
                    response1.setType(s.getType().getIndex());
                    List<ProductCategoryResponse> responses2 = new ArrayList<>();
                    productCategory3.forEach(t -> {
                        if (s.getId().equals(t.getPrev())) {
                            ProductCategoryResponse response2 = new ProductCategoryResponse();
                            response2.setValue(t.getId());
                            response2.setPrev(t.getPrev());
                            response2.setLabel(t.getName());
                            response2.setType(t.getType().getIndex());
                            responses2.add(response2);
                        }
                    });
                    if (!CollectionUtils.isEmpty(responses2)) {
                        response1.setChildren(responses2);
                    }
                    responses1.add(response1);
                }
            });
            if (!CollectionUtils.isEmpty(responses1)) {
                response.setChildren(responses1);
            }
            responses.add(response);
        });
        return new Notify<>(Notify.Code.SUCCESS, responses);
    }
}
