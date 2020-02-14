package com.example.mongodb_demo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;
@Data
@Configuration
//读取配置文件中spring.data.mongodb 开头的属性如database，通过反射调用setDatabase()保存从配置文件中读取的database值
//需要maven依赖 spring-boot-configuration-processor
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoConfiguration {
    // MongoDB Properties
    private String host, database, username, password;
    private int port;

    private MongoDbFactory mongoDbFactory() throws Exception {
        //不认证账号密码
//        return new SimpleMongoDbFactory(new MongoClient(host, port), database);
        //连接mongodb的工厂认证账号密码
        ServerAddress serverAddress = new ServerAddress(host, port);
        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        mongoCredentialList.add(MongoCredential.createCredential(username, database, password.toCharArray()));
        return new SimpleMongoDbFactory(new MongoClient(serverAddress, mongoCredentialList), database);
    }

    //第一个数据库 默认作为主数据库 需要添加注解 @Primary ，后面的数据库不需要这个注解
    @Primary
    @Bean(name = "mongoTemplate")
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}
