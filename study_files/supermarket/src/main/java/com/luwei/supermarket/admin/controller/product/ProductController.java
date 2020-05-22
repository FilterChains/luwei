package com.luwei.supermarket.admin.controller.product;

import com.luwei.supermarket.admin.business.product.ProductBusiness;
import com.luwei.supermarket.admin.controller.BaseController;
import com.luwei.supermarket.entity.bo.request.ProductCategoryRequest;
import com.luwei.supermarket.entity.bo.request.ProductRequest;
import com.luwei.supermarket.entity.bo.request.ProductSearchRequest;
import com.luwei.supermarket.entity.bo.response.DistrictResponse;
import com.luwei.supermarket.entity.bo.response.ProductCategoryResponse;
import com.luwei.supermarket.entity.bo.response.ProductListResponse;
import com.luwei.supermarket.entity.po.User;
import com.luwei.supermarket.util.Notify;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.admin.controller.product
 * @auther: luwei
 * @description:
 * @date: 2020/5/14 23:18
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Api(tags = "商品信息相关接口")
@Validated
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductBusiness productBusiness;

    @ResponseBody
    @PostMapping("/district")
    @ApiOperation(value = "获取平台一级地区", notes = "获取平台一级地区")
    public Notify<List<DistrictResponse>> findDistrict() {
        return super.getProvinceList();
    }

    @ResponseBody
    @PostMapping("/category")
    @ApiOperation(value = "查询商品分类", notes = "查询商品分类")
    public Notify<List<ProductCategoryResponse>> findProductCategory() {
        return super.getProductCategory();
    }

    @PostMapping("/operationCategory")
    @ApiOperation(value = "操作商品分类", notes = "操作商品分类")
    public Notify<String> operationProductCategory(@RequestBody ProductCategoryRequest request, HttpServletRequest hsq) {
        HttpSession session = hsq.getSession();
        User user = (User) session.getAttribute("userSession");
        if (Objects.isNull(user)) {
            return new Notify<>(Notify.Code.UNKNOWN_ERROR, "操作分类失败，请先登录");
        }
        return productBusiness.operationCategory(request, user.getId());
    }

    @PostMapping("/createOrUpdateProduct")
    @ApiOperation(value = "编辑商品-新增或修改", notes = "编辑商品-新增或修改")
    public Notify<String> createOrUpdate(@RequestBody @Valid ProductRequest request, HttpServletRequest hsq) {
        HttpSession session = hsq.getSession();
        User user = (User) session.getAttribute("userSession");
        if (Objects.isNull(user)) {
            return new Notify<>(Notify.Code.UNKNOWN_ERROR, "创建失败，请先登录");
        }
        return productBusiness.createOrUpdateProduct(request, user.getId());
    }

    @PostMapping("/listBody")
    @ApiOperation(value = "商品查询", notes = "商品查询")
    public Notify<List<ProductListResponse>> getProductList(@RequestBody ProductSearchRequest request) {
        return productBusiness.showProductListBody(request);
    }

    @PostMapping("/echoProduct")
    @ApiOperation(value = "商品信息回显", notes = "商品信息回显")
    @ApiImplicitParam(name = "id", value = "商品ID", dataType = "int")
    public Notify<ProductListResponse> echoProductMsg(Integer id, HttpServletRequest hsq) {
        HttpSession session = hsq.getSession();
        User user = (User) session.getAttribute("userSession");
        if (Objects.isNull(user)) {
            return new Notify<>(Notify.Code.UNKNOWN_ERROR);
        }
        return productBusiness.echoProductMsg(id);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除商品", notes = "删除商品")
    @ApiImplicitParam(name = "id", value = "商品ID", dataType = "int")
    public Notify<String> deleteProduct(Integer id) {
        return productBusiness.deleteProductMsg(id);
    }
}
