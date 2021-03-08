package com.user.service;

import com.user.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface EsSearchService extends CrudRepository<Product, String> {
}
