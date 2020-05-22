package com.luwei.supermarket.entity.vo;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.entity.vo
 * @auther: luwei
 * @description: 商品搜索
 * @date: 2020/5/20 00:48
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class ProductSearchVO implements Serializable {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品分类
     */
    private List<Integer> typeList;

    /**
     * 商品地区
     */
    private String region;

    /**
     * 起始页
     */
    private Integer pageNo;

    /**
     * 展示行数
     */
    private Integer pageSize;

}
