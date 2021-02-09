-- 平台优惠券商家商品黑名单
CREATE TABLE `td_platform_coupon_exclude_product`
(
    `id`          int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `coupon_id`   int(11)  NOT NULL COMMENT '优惠券ID，引用td_platform_coupon(id)',
    `product_id`  int(11)  NOT NULL COMMENT '商家商品ID，引用td_product_sku(id)',
    `create_by`   int(11)  NOT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   int(11)  NOT NULL COMMENT '更新人',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `index_p` (`coupon_id`, `product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='平台优惠券商品黑名单表';