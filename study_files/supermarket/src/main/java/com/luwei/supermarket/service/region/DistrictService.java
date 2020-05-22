package com.luwei.supermarket.service.region;

import com.luwei.supermarket.admin.entity.po.District;
import com.luwei.supermarket.base.SuperService;

import java.util.List;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.service.region
 * @auther: luwei
 * @description:
 * @date: 2020/5/14 23:08
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public interface DistrictService extends SuperService<District> {

    /**
     * @Title: findDistrictLevel
     * @Description: 根据地区等级查询地区集合
     * @Param: [districtType]   参数
     * @Return: List<District>  返回类型
     * @Date: 2020/5/18 22:53
     */
    List<District> findDistrictLevel(District.DistrictType districtType);
}
