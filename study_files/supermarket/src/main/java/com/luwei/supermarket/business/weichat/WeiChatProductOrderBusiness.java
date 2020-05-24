package com.luwei.supermarket.business.weichat;

import com.google.common.base.Splitter;
import com.luwei.supermarket.base.BaseBusiness;
import com.luwei.supermarket.entity.bo.request.WcOrderRequest;
import com.luwei.supermarket.entity.vo.OrderCreateVO;
import com.luwei.supermarket.service.product.OrderService;
import com.luwei.supermarket.service.product.ProductService;
import com.luwei.supermarket.util.CommonEnum;
import com.luwei.supermarket.util.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @projectName： spring-boot-seckill
 * @packageName: com.luwei.supermarket.weichat.business
 * @auther: luwei
 * @description:
 * @date: 2020/5/23 23:11
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class WeiChatProductOrderBusiness extends BaseBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeiChatProductOrderBusiness.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    /**
     * @Title: createOrder
     * @Description: 创建订单
     * @Param: [request]   参数
     * @Return: String  返回类型
     * @Date: 2020/5/24 22:00
     */
    public Notify<String> createOrder(WcOrderRequest request) {
        final BigDecimal payment = request.getPayment();
        final String userId = request.getUserId();
        final String receiverBy = request.getReceiverBy();
        final String receiverPhone = request.getReceiverPhone();
        final String receiverAddress = request.getReceiverAddress();
        String productId = request.getProductId();
        List<String> idList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(productId);
        if (CollectionUtils.isEmpty(idList)) {
            return new Notify<>(Notify.Code.SUCCESS, "创建失败,请添加商品");
        }
        if (CommonEnum.ZERO.getIndex() >= payment.compareTo(BigDecimal.ZERO)) {
            return new Notify<>(Notify.Code.SUCCESS, "创建失败,预付金额不正确");
        }
        // 开始创建订单
        return new Notify<>(Notify.Code.SUCCESS,
                orderService.createOrder(OrderCreateVO.builder()
                        .payment(payment).productId(productId)
                        .receiverAddress(receiverAddress)
                        .receiverBy(receiverBy)
                        .receiverPhone(receiverPhone)
                        .userId(userId)
                        .build()));
    }
}
