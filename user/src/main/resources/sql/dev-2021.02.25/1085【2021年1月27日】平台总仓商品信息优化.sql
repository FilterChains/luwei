-- 1085【2021年1月27日】平台总仓商品信息优化,平台商品库td_product新增字段sql
ALTER TABLE td_product
    ADD COLUMN production_address
        varchar(255) DEFAULT NULL COMMENT '生产产地';
ALTER TABLE td_product
    ADD COLUMN trait
        text DEFAULT NULL COMMENT '性状';
ALTER TABLE td_product
    ADD COLUMN drug_interactions
        text DEFAULT NULL COMMENT '药物相互作用';
ALTER TABLE td_product
    ADD COLUMN lay_in_deposit
        text DEFAULT NULL COMMENT '贮藏';
ALTER TABLE td_product
    ADD COLUMN in_date
        varchar(255) DEFAULT NULL COMMENT '有效期';
ALTER TABLE td_product
    modify COLUMN p_usage
    text DEFAULT NULL COMMENT '用法用量';
ALTER TABLE td_product
    modify COLUMN attention
    text DEFAULT NULL COMMENT '注意事项';
ALTER TABLE td_product
    modify COLUMN reaction
    text DEFAULT NULL COMMENT '不良反应';
ALTER TABLE td_product
    modify COLUMN avoid
    text DEFAULT NULL COMMENT '禁忌说明';
ALTER TABLE td_product
    modify COLUMN indication
    text DEFAULT NULL COMMENT '功能主治';
ALTER TABLE td_product
    modify COLUMN components
    text DEFAULT NULL COMMENT '主要成分';
