package com.luwei.supermarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luwei.supermarket.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.mapper
 * @auther: luwei
 * @description:
 * @date: 2020/5/13 22:50
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
