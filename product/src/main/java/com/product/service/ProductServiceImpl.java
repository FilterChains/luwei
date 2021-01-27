package com.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.product.entity.Product;
import com.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {

}
