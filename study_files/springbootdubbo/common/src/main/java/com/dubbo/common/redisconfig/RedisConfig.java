package com.dubbo.common.redisconfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @projectName： springbootdubbo
 * @packageName: com.dubbo.common.config
 * @auther: luwei
 * @description: redis相关配置
 * @date: 2020/1/24 22:34
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@EnableCaching //开启注解
@Configuration //spring配置注解
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * @Title: redisTemplate
     * @Description: redisTemplate相关配置
     * @Param: [factory]   参数
     * @Return: org.springframework.data.redis.core.RedisTemplate<java.lang.String   ,   java.lang.Object>   返回类型
     * @Date: 2020/1/24 22:53
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 配置连接工厂
        redisTemplate.setConnectionFactory(factory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(objectMapper);

        // 值采用json序列化
        redisTemplate.setValueSerializer(jacksonSeial);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jacksonSeial);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    /**@Title: hashOperations
     * @Description: 对hash类型的数据操作
     * @Param: [redisTemplate]   参数
     * @Return: org.springframework.data.redis.core.HashOperations<java.lang.String,java.lang.String,java.lang.Object>   返回类型
     * @Date: 2020/1/24 22:58
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**@Title: valueOperations
     * @Description: 对redis字符串类型数据操作
     * @Param: [redisTemplate]   参数
     * @Return: org.springframework.data.redis.core.ValueOperations<java.lang.String,java.lang.Object>   返回类型
     * @Date: 2020/1/24 22:58
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**@Title: listOperations
     * @Description: 对链表类型的数据操作
     * @Param: [redisTemplate]   参数
     * @Return: org.springframework.data.redis.core.ListOperations<java.lang.String,java.lang.Object>   返回类型
     * @Date: 2020/1/24 22:58
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**@Title: setOperations
     * @Description: 对无序集合类型的数据操作
     * @Param: [redisTemplate]   参数
     * @Return: org.springframework.data.redis.core.SetOperations<java.lang.String,java.lang.Object>   返回类型
     * @Date: 2020/1/24 22:57
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**@Title: zSetOperations
     * @Description: 对有序集合类型的数据操作
     * @Param: [redisTemplate]   参数
     * @Return: org.springframework.data.redis.core.ZSetOperations<java.lang.String,java.lang.Object>   返回类型
     * @Date: 2020/1/24 22:56
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
