package com.luwei.supermarket.service.region;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luwei.supermarket.admin.entity.po.District;
import com.luwei.supermarket.base.SuperServiceImpl;
import com.luwei.supermarket.mapper.DistrictMapper;
import org.springframework.stereotype.Service;

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
@Service
public class DistrictServiceImpl extends SuperServiceImpl<DistrictMapper, District>
        implements DistrictService {

    @Override
    public List<District> findDistrictLevel(District.DistrictType districtType) {
        LambdaQueryWrapper<District> lambda = new QueryWrapper<District>().lambda();
        return baseMapper.selectList(lambda.eq(District::getLevel, districtType.getIndex()));
    }
}
