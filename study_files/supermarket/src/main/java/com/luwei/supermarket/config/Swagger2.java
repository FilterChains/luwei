package com.luwei.supermarket.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.config
 * @auther: luwei
 * @description:
 * @date: 2019/5/29 21:11
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI // swagger-ui 增强功能
public class Swagger2 {

    /**
     * 全局参数
     *
     * @return List<Parameter>
     */
    private List<Parameter> parameter() {
        List<Parameter> params = new ArrayList<>();
        params.add(new ParameterBuilder().name("token")
                .description("认证令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build());
        return params;
    }

    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//创建api基本信息
                .groupName("叮叮瞄API")//创建分组,可以创建多个分组
                .select()//控制需要暴露的接口
                //扫描暴露的包,每次扫描一个包
                .apis(RequestHandlerSelectors.basePackage("com.luwei.supermarket.admin.controller"))
                //设置过滤规则暴露接口
                .paths(PathSelectors.any())
                //.paths(or(regex("/api/.*")))过滤的接口
                .build().globalOperationParameters(parameter());
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("叮叮瞄API")
                //创建人
                .contact(new Contact("LuWei", "http://192.168.0.106:8080/swagger-ui.html", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
}