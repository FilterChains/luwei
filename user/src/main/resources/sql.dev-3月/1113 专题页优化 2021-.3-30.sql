ALTER TABLE td_prefecture_pc_item
    ADD COLUMN `product_id` int(11) DEFAULT NULL COMMENT '专题页添加商品，商品ID(td_product_sku)主键ID';
ALTER TABLE td_prefecture_pc_item
    ADD COLUMN `is_interval` bit(1) NOT NULL DEFAULT b'0' COMMENT  '是否需要间距,0->不需要;1->需要';