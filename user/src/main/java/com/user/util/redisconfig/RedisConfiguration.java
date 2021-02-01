package com.user.util.redisconfig;

import com.luwei.redisconfig.RedisKeyExpiredListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisConfiguration extends RedisMessageListenerContainer {

    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;

    @Bean
    public ChannelTopic expiredTopic() {
        return new ChannelTopic("__keyevent@0__:expired");  // 选择0号数据库
    }


    @Bean
    public RedisKeyExpiredListener keyExpiredListener() {
        return new RedisKeyExpiredListener(redisMessageListenerContainer);
    }
}