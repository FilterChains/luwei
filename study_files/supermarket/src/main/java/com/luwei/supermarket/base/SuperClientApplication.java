package com.luwei.supermarket.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luwei.supermarket.intercepter.RequestBodyFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

public class SuperClientApplication {

    //@Bean
    //public RequestLogAspect requestLogAspect() {
    //    return new RequestLogAspect();
    //}

    @Bean
    public SuperExceptionHandler defaultExceptionHandler() {
        return new SuperExceptionHandler();
    }

//    /**
//     * <p>@Description : 解决请求BODY只能读取一次的问题</p>
//     * <p>@Author : QiLin.Xing </p>
//     * <p>@Date : 2018/8/1 9:23 </p>
//     */
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new RequestBodyFilter());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setName("requestBodyFilter");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }

    /**
     * <p>@Description : 解决跨域问题</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2018/8/11 9:13 </p>
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

    /**
     * <p>@Description : 处理响应JSON时，字符串为null不序列化</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/3/16 15:10 </p>
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        //通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
        //Include.ALWAYS 默认
        //Include.NON_DEFAULT 属性为默认值不序列化
        //Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
        //Include.NON_NULL 属性为NULL 不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
}
