package com.luwei.dubbo_provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luwei.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @projectName： springbootdubbo
 * @packageName: com.luwei.dubbo_provider.mapper
 * @auther: luwei
 * @description:
 * @date: 2020/1/23 19:35
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
