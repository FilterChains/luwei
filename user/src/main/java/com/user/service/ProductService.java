package com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.entity.TdProduct;

import java.util.List;

public interface ProductService extends IService<TdProduct> {

    List<TdProduct> findLimitProduct();
}
