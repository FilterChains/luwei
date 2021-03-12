package com.user.config;

import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Import(value = {BaseApplication.class, GlobalExceptionHandler.class, SpringUtil.class})
@EnableElasticsearchRepositories(basePackages = "com.user.service")
public @interface EnableAutoBaseConfig {
}
