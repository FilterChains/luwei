package com.luwei.supermarket.admin.business.product;

import com.luwei.supermarket.admin.entity.bo.request.ProductCategoryRequest;
import com.luwei.supermarket.admin.entity.bo.request.ProductRequest;
import com.luwei.supermarket.admin.entity.bo.request.ProductSearchRequest;
import com.luwei.supermarket.admin.entity.bo.response.ProductListResponse;
import com.luwei.supermarket.admin.entity.po.District;
import com.luwei.supermarket.admin.entity.po.Product;
import com.luwei.supermarket.admin.entity.po.ProductCategory;
import com.luwei.supermarket.admin.entity.vo.ProductSearchVO;
import com.luwei.supermarket.service.product.ProductCategoryService;
import com.luwei.supermarket.service.product.ProductService;
import com.luwei.supermarket.service.region.DistrictService;
import com.luwei.supermarket.util.Notify;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
public class ProductBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductBusiness.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private DistrictService districtService;

    /**
     * @Title: operationCategory
     * @Description: 操作商品分类
     * @Param: [request, userId]   参数
     * @Return: java.lang.String  返回类型
     * @Date: 2020/5/20 0:58
     */
    public Notify<String> operationCategory(ProductCategoryRequest request, Integer userId) {
        if (Objects.isNull(request)) {
            return new Notify<>(Notify.Code.ERROR, "请求数据不能空");
        }
        final Integer id = request.getId();
        final ProductCategoryRequest.ProductCategoryOperation operation = request.getOperation();
        final String title = request.getTitle();
        ProductCategory productCategory = new ProductCategory();
        switch (operation) {
            case INSERT:
                productCategory.setCreateBy(userId);
                if (null == id && StringUtils.isNotEmpty(title)) {
                    // 新增一级分类
                    productCategory.setName(title);
                    productCategory.setPrev(0);
                    productCategory.setType(ProductCategory.ProductCategoryType.FIRST_LEVEL);
                }
                if (null != id && StringUtils.isNotEmpty(title)) {
                    ProductCategory byId = productCategoryService.get(id);
                    if (Objects.nonNull(byId)) {
                        // 新增一级分类
                        productCategory.setName(title);
                        productCategory.setPrev(id);
                        productCategory.setType(
                                ProductCategory.ProductCategoryType.FIRST_LEVEL.equals(byId.getType()) ?
                                        ProductCategory.ProductCategoryType.SECOND_LEVEL : ProductCategory.ProductCategoryType.SECOND_LEVEL
                                        .equals(byId.getType()) ? ProductCategory.ProductCategoryType.THIRDLY_LEVEL : byId.getType());
                    }
                }
                break;
            case UPDATE:
                productCategory.setId(id);
                productCategory.setName(title);
                break;
            case DELETE:
                productCategoryService.deleteById(id);
                return new Notify<>(Notify.Code.SUCCESS);
            default:
                return new Notify<>(Notify.Code.ERROR, "操作类型选择错误");
        }
        productCategory.setUpdateBy(userId);
        productCategoryService.insertOrUpdate(productCategory);
        return new Notify<>(Notify.Code.SUCCESS);
    }


    /**
     * @Title: createOrUpdateProduct
     * @Description: 编辑商品
     * @Param: [request]   参数
     * @Return: String  返回类型
     * @Date: 2020/5/17 10:40
     */
    public Notify<String> createOrUpdateProduct(ProductRequest request, final Integer userId) {
        final String productName = request.getProductName().replaceAll("\\s+", "");
        final Integer id = request.getId();
        String productRegion = request.getProductRegion();
        if (StringUtils.isEmpty(productName)) {
            return new Notify<>(Notify.Code.ERROR, "商品名称不能为空");
        }
        if (StringUtils.isEmpty(productRegion)) {
            return new Notify<>(Notify.Code.ERROR, "商品地区不能为空");
        }
        District byId = districtService.get(Integer.valueOf(productRegion));
        final Product product = new Product();
        final Date time = Calendar.getInstance().getTime();
        if (null == id) {
            Product validateProduct = productService.validateProduct(productName);
            if (Objects.nonNull(validateProduct)) {
                return new Notify<>(Notify.Code.ERROR, "商品已存在请前往修改");
            }
            product.setCreateBy(userId);
            product.setCreateTime(time);
        } else {
            product.setId(id);
        }
        //商品名称
        product.setProductName(productName);
        //商品价格
        product.setProductPrice(request.getProductPrice());
        //商品库存
        product.setProductStock(request.getProductStock());
        //商品状态：0-下架，1-上架，2-待上架
        product.setProductStatus(request.getProductStatus());
        //商品图片
        product.setProductImagesUrl(request.getProductImagesUrl());
        //商品地区
        product.setProductRegion(request.getProductRegion());
        if (Objects.nonNull(byId)) {
            product.setProductAddress(byId.getName());
        }
        //商品类型
        product.setProductType(request.getProductType());
        //商品单位
        product.setProductUnit(request.getProductUnit());
        //商品描述
        product.setProductRemark(request.getProductRemark());
        product.setUpdateBy(userId);
        product.setUpdateTime(time);
        productService.insertOrUpdate(product);
        return new Notify<>(Notify.Code.SUCCESS, "编辑成功");
    }


    /**
     * @Title: showProductListBody
     * @Description: 商品列表展示/查询
     * @Return: 返回类型
     * @Throws:
     * @Date: 2020/5/17 12:20
     */
    public Notify<List<ProductListResponse>> showProductListBody(ProductSearchRequest request) {
        if (Objects.isNull(request)) {
            return new Notify<>(Notify.Code.ERROR);
        }
        final String name = request.getName();
        final String type = request.getType();
        final String region = request.getRegion();
        final Integer pageNo = request.getPageNo();
        final Integer pageSize = request.getPageSize();
        ProductSearchVO productSearchVO = new ProductSearchVO();
        productSearchVO.setName(name);
        productSearchVO.setTypeList(findProductType(type));
        productSearchVO.setRegion(region);
        final Integer totalPages = productService.searchProductListTotalPages(productSearchVO);
        List<ProductListResponse> listResponses = new ArrayList<>();
        if (0 < totalPages) {
            // 查询商品类型
            Map<Integer, ProductCategory> productTypeAll = productCategoryService.findProductTypeAll();
            productSearchVO.setPageNo(pageNo);
            productSearchVO.setPageSize(pageSize > totalPages ? totalPages : pageSize);
            List<Product> products = productService.searchProductList(productSearchVO);
            if (!CollectionUtils.isEmpty(products)) {
                products.forEach(lt -> {
                    ProductListResponse productListResponse = new ProductListResponse();
                    productListResponse.setId(lt.getId());
                    productListResponse.setProductName(lt.getProductName());
                    productListResponse.setProductAddress(lt.getProductAddress());
                    productListResponse.setProductStock(lt.getProductStock());
                    productListResponse.setProductPrice(lt.getProductPrice());
                    productListResponse.setProductStatus(lt.getProductStatus().getValue());
                    productListResponse.setProductImagesUrl(lt.getProductImagesUrl());
                    productListResponse.setProductUnit(lt.getProductUnit());
                    productListResponse.setProductRemark(lt.getProductRemark());
                    if (!ObjectUtils.isEmpty(productTypeAll)) {
                        ProductCategory productCategory = productTypeAll.get(lt.getProductType());
                        if (Objects.nonNull(productCategory)) {
                            productListResponse.setProductType(productCategory.getName());
                        }
                    }
                    productListResponse.setTotalPage(totalPages);
                    listResponses.add(productListResponse);
                });
            }
        }
        return new Notify<>(Notify.Code.SUCCESS, listResponses);
    }

    /**
     * @Title: echoProductMsg
     * @Description: 商品信息回显
     * @Param: [id]   参数
     * @Return: ProductListResponse   返回类型
     * @Date: 2020/5/17 21:37
     */
    public Notify<ProductListResponse> echoProductMsg(Integer id) {
        Product product = productService.get(id);
        ProductListResponse productListResponse = new ProductListResponse();
        if (Objects.nonNull(product)) {
            productListResponse.setId(product.getId());
            productListResponse.setProductName(product.getProductName());
            productListResponse.setProductAddress(product.getProductAddress());
            productListResponse.setProductPrice(product.getProductPrice());
            productListResponse.setProductStock(product.getProductStock());
            productListResponse.setProductStatus(product.getProductStatus().getValue());
            productListResponse.setProductImagesUrl(product.getProductImagesUrl());
            ProductCategory byId = productCategoryService.get(product.getProductType());
            if (Objects.nonNull(byId)) {
                productListResponse.setProductType(byId.getName());
            }
            productListResponse.setProductUnit(product.getProductUnit());
            productListResponse.setProductRemark(product.getProductRemark());
        }
        return new Notify<>(Notify.Code.SUCCESS, productListResponse);
    }

    /**
     * @Title: deleteProductMsg
     * @Description: 删除商品
     * @Param: [id]   参数
     * @Return: String  返回类型
     * @Date: 2020/5/17 13:45
     */
    public Notify<String> deleteProductMsg(final Integer id) {
        return new Notify<>(Notify.Code.SUCCESS,
                productService.deleteById(id) ? "删除成功" : "删除失败");
    }

    /**
     * @Title: findProductType
     * @Description: 查询商品类型
     * @Param: [type]   参数
     * @Return: List<Integer>   返回类型
     * @Date: 2020/5/20 1:04
     */
    private List<Integer> findProductType(String type) {
        if (StringUtils.isEmpty(type)) {
            return Collections.emptyList();
        }
        return productCategoryService.vagueSearchProductType(type);
    }
}
