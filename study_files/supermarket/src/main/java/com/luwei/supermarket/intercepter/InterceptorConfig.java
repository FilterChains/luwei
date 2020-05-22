package com.luwei.supermarket.intercepter;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.intercepter
 * @auther: luwei
 * @description:
 * @date: 2020/5/14 00:59
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
//@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SpringSSMInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/testBean/test");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/", ".jsp");
        super.configureViewResolvers(registry);
    }
}
