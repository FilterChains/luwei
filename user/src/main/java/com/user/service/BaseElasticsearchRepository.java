package com.user.service;

import com.user.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BaseElasticsearchRepository extends ElasticsearchRepository<Product, String> {
}
