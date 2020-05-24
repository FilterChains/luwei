package com.luwei.supermarket.business.weichat;

import com.luwei.supermarket.base.BaseBusiness;
import com.luwei.supermarket.entity.bo.request.WcShoppingCartCleanRequest;
import com.luwei.supermarket.entity.bo.request.WcShoppingCartRequest;
import com.luwei.supermarket.entity.bo.request.WcShoppingCartSearchRequest;
import com.luwei.supermarket.entity.bo.response.WcShoppingCartSearchResponse;
import com.luwei.supermarket.entity.po.Product;
import com.luwei.supermarket.entity.po.ShoppingCart;
import com.luwei.supermarket.service.product.ProductService;
import com.luwei.supermarket.service.product.ShoppingCartService;
import com.luwei.supermarket.util.CommonEnum;
import com.luwei.supermarket.util.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.weichat.business
 * @auther: luwei
 * @description: 购物车业务层
 * @date: 2020/5/23 23:13
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class WeiChatShoppingCartBusiness extends BaseBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeiChatProductOrderBusiness.class);

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    /**
     * @Title: getShoppingCart
     * @Description: 获取购物车信息
     * @Param: [request]   参数
     * @Return: List<WcShoppingCartSearchResponse>> 返回类型
     * @Date: 2020/5/24 1:46
     */
    public Notify<List<WcShoppingCartSearchResponse>> getShoppingCart(WcShoppingCartSearchRequest request) {
        final String userId = request.getUserId();
        final Integer pageNo = request.getPageNo();
        Integer pageSize = request.getPageSize();
        Integer listTotalPage = shoppingCartService.getShoppingCartListTotalPage(userId);
        List<WcShoppingCartSearchResponse> list = new ArrayList<>();
        if (0 < listTotalPage) {
            List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartList(userId, pageNo, pageSize > listTotalPage ? listTotalPage : pageSize);
            if (!CollectionUtils.isEmpty(shoppingCartList)) {
                List<Integer> idList = shoppingCartList.stream().map(ShoppingCart::getProductId).collect(Collectors.toList());
                Map<Integer, Product> productMsg = productService.findProductMsg(idList, false);
                shoppingCartList.forEach(sp -> {
                    WcShoppingCartSearchResponse wcShoppingCartSearchResponse = new WcShoppingCartSearchResponse();
                    wcShoppingCartSearchResponse.setId(sp.getId());
                    final Integer productId = sp.getProductId();
                    if (!CollectionUtils.isEmpty(productMsg)) {
                        Product product = productMsg.get(productId);
                        if (Objects.nonNull(product)) {
                            final Integer productNumber = sp.getProductNumber();
                            final Integer productStock = product.getProductStock();
                            wcShoppingCartSearchResponse.setProductId(productId);
                            wcShoppingCartSearchResponse.setProductName(product.getProductName());
                            wcShoppingCartSearchResponse.setProductImageUrl(product.getProductImagesUrl());
                            wcShoppingCartSearchResponse.setProductPrice(product.getProductPrice());
                            wcShoppingCartSearchResponse.setProductNumber(productNumber > productStock ? productStock : productNumber);
                        }
                    }
                    wcShoppingCartSearchResponse.setTotalPage(listTotalPage);
                    list.add(wcShoppingCartSearchResponse);
                });
            }
        }
        return new Notify<>(Notify.Code.SUCCESS, list);
    }

    /**
     * @Title: createShoppingCart
     * @Description: 添加购物车
     * @Param: [request]   参数
     * @Return: String  返回类型
     * @Date: 2020/5/24 11:27
     */
    public Notify<String> createShoppingCart(WcShoppingCartRequest request) {
        final Integer productId = request.getProductId();
        final String userId = request.getUserId();
        final Integer productNumber = request.getProductNumber();
        if (CommonEnum.ZERO.getIndex() >= productId) {
            return new Notify<>(Notify.Code.ERROR, "请正确填写商品ID");
        }
        if (CommonEnum.ZERO.getIndex() >= productNumber) {
            return new Notify<>(Notify.Code.ERROR, "请正确填写商品数量");
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setProductId(productId);
        shoppingCart.setCreateBy(userId);
        shoppingCart.setProductNumber(productNumber);
        shoppingCart.setCreateTime(Calendar.getInstance().getTime());
        // 查询商品信息
        Product product = productService.get(productId);
        // 判断商品是否存在
        if (Objects.nonNull(product)) {
            final Product.ProductStatus productStatus = product.getProductStatus();
            Integer productStock = product.getProductStock() - productNumber;
            // 判断商品状态和库存
            if (!Product.ProductStatus.PUT_AWAY.equals(productStatus)) {
                return new Notify<>(Notify.Code.ERROR, "添加失败,商品已下架");
            }
            // 查询购物车信息
            ShoppingCart cart = shoppingCartService.validateShoppingCart(productId, userId);
            if (Objects.nonNull(cart)) {
                final Integer number = cart.getProductNumber();
                productStock -= number;
                shoppingCart.setId(cart.getId());
                shoppingCart.setProductNumber(productNumber + number);
                shoppingCart.setVersion(cart.getVersion());
            }
            if (CommonEnum.ZERO.getIndex() > productStock) {
                return new Notify<>(Notify.Code.ERROR, "添加失败,商品库存不足");
            }
            if (shoppingCartService.insertOrUpdate(shoppingCart)) {
                return new Notify<>(Notify.Code.SUCCESS, "添加成功");
            } else {
                return new Notify<>(Notify.Code.ERROR, "添加失败");
            }
        }
        return new Notify<>(Notify.Code.ERROR, "添加失败,无此商品");
    }

    /**
     * @Title: cleanShoppingCart
     * @Description: 清空购物车
     * @Param: [userId]   参数
     * @Return: String  返回类型
     * @Date: 2020/5/24 11:42
     */
    public Notify<String> cleanShoppingCart(WcShoppingCartCleanRequest request) {
        shoppingCartService.cleanShoppingCart(request.getUserId(), request.getProductId());
        return new Notify<>(Notify.Code.SUCCESS, "成功清空购物车");
    }

    /**
     * @Title: updateShoppingCart
     * @Description: 修改购物车
     * @Param: [request]   参数
     * @Return: String   返回类型
     * @Date: 2020/5/24 12:14
     */
    public Notify<String> updateShoppingCart(WcShoppingCartRequest request) {
        final Integer productId = request.getProductId();
        final Integer productNumber = request.getProductNumber();
        final String userId = request.getUserId();
        // 查询购物车信息
        ShoppingCart shoppingCart = shoppingCartService.validateShoppingCart(productId, userId);
        if (Objects.isNull(shoppingCart)) {
            return new Notify<>(Notify.Code.ERROR, "购物车暂无此商品,请前往添加");
        }
        // 查询商品信息
        Product product = productService.get(productId);
        if (Objects.isNull(product)) {
            return new Notify<>(Notify.Code.ERROR, "暂无此商品");
        }
        Integer number = shoppingCart.getProductNumber() + productNumber;
        final Product.ProductStatus productStatus = product.getProductStatus();
        final Integer productStock = product.getProductStock();
        // 判断商品状态和库存
        if (!Product.ProductStatus.PUT_AWAY.equals(productStatus)) {
            return new Notify<>(Notify.Code.ERROR, "修改失败,商品已下架");
        }
        if (productStock < number) {
            shoppingCart.setProductNumber(productStock);
            shoppingCartService.updateById(shoppingCart);
            return new Notify<>(Notify.Code.SUCCESS, "修改成功,已为您添加商品最大库存");
        } else {
            shoppingCart.setProductNumber(number < 0 ? 0 : number);
            shoppingCartService.updateById(shoppingCart);
            return new Notify<>(Notify.Code.SUCCESS, "修改成功");
        }
    }
}
