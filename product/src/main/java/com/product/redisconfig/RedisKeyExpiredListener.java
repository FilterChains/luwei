package com.product.redisconfig;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <p>@description : redis监听 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/7/14 21:03 </p>
 **/

@Component
public class RedisKeyExpiredListener extends KeyExpirationEventMessageListener {

    /**
     * @param listenerContainer must not be {@literal null}.
     */
    public RedisKeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // message.toString()可以返回redis库中失效的 key
        // your code
        System.err.println("redis监听的值:" + message.toString());
        System.out.println("redis监听的值:" + Arrays.toString(message.getBody()));
        if (message.toString().contains("PlatformActivityEnum.REGISTRATION")) {
            System.err.println("Redis监听到平台活动即将开始报名");
        }
    }

}
