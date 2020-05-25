package com.luwei.supermarket.service.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.luwei.supermarket.base.SuperServiceImpl;
import com.luwei.supermarket.entity.po.Order;
import com.luwei.supermarket.entity.po.Product;
import com.luwei.supermarket.entity.po.ShoppingCart;
import com.luwei.supermarket.entity.vo.OrderCreateVO;
import com.luwei.supermarket.mapper.OrderMapper;
import com.luwei.supermarket.util.CommonEnum;
import com.luwei.supermarket.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.service.product
 * @auther: luwei
 * @description:
 * @date: 2020/5/24 00:53
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class OrderServiceImpl extends SuperServiceImpl<OrderMapper, Order>
        implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized String createOrder(OrderCreateVO orderCreateVO) {
        final BigDecimal payment = orderCreateVO.getPayment();
        final String userId = orderCreateVO.getUserId();
        final String receiverBy = orderCreateVO.getReceiverBy();
        final String receiverPhone = orderCreateVO.getReceiverPhone();
        final String receiverAddress = orderCreateVO.getReceiverAddress();
        // 获取用户购物车信息
        Map<Integer, ShoppingCart> shoppingCart = shoppingCartService.findShoppingCart(userId);
        if (CollectionUtils.isEmpty(shoppingCart)) {
            return "创建失败，购物车暂无商品";
        }
        // 查询对应商品库存情况
        Map<Integer, Product> productMsg = productService.findProductMsg(Lists.newArrayList(shoppingCart.keySet()), true);
        if (CollectionUtils.isEmpty(productMsg)) {
            return "创建失败，商品库异常";
        }
        final int size = productMsg.size();
        if (shoppingCart.size() != size) {
            return "创建失败，购物车中有下架商品";
        }
        // 验证商品价格
        // 扣减对应商品库存
        List<Product> products = new ArrayList<>(size);
        BigDecimal zero = BigDecimal.ZERO;
        Collection<Product> values = productMsg.values();
        for (Product product : values) {
            Integer id = product.getId();
            ShoppingCart cart = shoppingCart.get(id);
            if (Objects.nonNull(cart)) {
                // 验证每个商品的库存量
                Integer productStock = product.getProductStock();
                // 购物车数量
                Integer productNumber = cart.getProductNumber();
                final int number = productStock - productNumber;
                // 商品价格
                BigDecimal productPrice = product.getProductPrice();
                BigDecimal multiply = new BigDecimal(String.valueOf(productNumber)).multiply(productPrice);
                zero = zero.add(multiply);
                if (CommonEnum.ZERO.getIndex() <= number) {
                    product.setProductStock(number);
                    products.add(product);
                }
            }
        }
        if (size != products.size()) {
            return "创建失败，购物车中有商品库存不足";
        }
        if (CommonEnum.ZERO.getIndex() >= zero.compareTo(BigDecimal.ZERO)
                || CommonEnum.ZERO.getIndex() != zero.compareTo(payment)) {
            return "创建失败，购物车商品预付金额有误";
        }
        // 扣减商品库存
        productService.batchUpdateById(products);
        // 清空购物车
        shoppingCartService.cleanShoppingCart(userId, null);
        // 生成订单
        baseMapper.insert(Order.builder()
                .orderNumber(new IDGenerator().generateNo(IDGenerator.Type.IMPORT_ORDER_NO))
                .productId(orderCreateVO.getProductId())
                .payment(payment)
                .paymentType(Order.PayType.ONLINE_PAYMENT)
                .status(Order.OrderStatus.UNPAID)
                .createBy(userId)
                .receiverBy(receiverBy)
                .receiverPhone(receiverPhone)
                .receiverAddress(receiverAddress)
                .build());
        return "创建成功";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrder(OrderCreateVO orderCreateVO) {
        baseMapper.update(Order.builder()
                .status(orderCreateVO.getStatus())
                .consignTime(orderCreateVO.getConsignTime())
                .endTime(orderCreateVO.getEndTime())
                .shippingName(orderCreateVO.getShippingName())
                .shippingCode(orderCreateVO.getShippingCode())
                .build(), new QueryWrapper<Order>().lambda()
                .eq(Order::getOrderNumber, orderCreateVO.getOrderNumber()));
    }

    @Override
    public List<Order> findOrder(String userId, Integer pageNo, Integer pageSize) {
        return baseMapper.selectPage(new Page<>(pageNo, pageSize, false),
                new QueryWrapper<Order>().lambda().eq(!StringUtils.isEmpty(userId), Order::getCreateBy, userId)).getRecords();
    }

    @Override
    public Integer findOrderTotalPage(String userId) {
        return baseMapper.selectCount(new QueryWrapper<Order>()
                .lambda().eq(!StringUtils.isEmpty(userId), Order::getCreateBy, userId));
    }
}
