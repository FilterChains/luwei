package com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.entity.Account;

public interface UserService extends IService<Account> {

    /**
     * <p>@description : SelectOne 查询单条信息 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/6 17:20 </p>
     *
     * @param id -> 主键ID
     * @return {@link  Account }
     **/
    Account selectOne(Long id);
}
