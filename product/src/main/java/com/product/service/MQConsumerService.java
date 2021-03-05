package com.product.service;

import com.alibaba.fastjson.JSON;
import com.product.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MQConsumerService {
    // 建议正常规模项目统一用一个TOPIC
    private static final String topic = "RLT_TEST_TOPIC_TEST_WEI_LU";
    private static final String TAGS = "LU";

    /**
     * <p>@description : 模拟处理redis缓存穿透或击穿问题 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/5 16:43 </p>
     **/
    @Service
    @RocketMQMessageListener(topic = topic, selectorExpression = TAGS, consumerGroup = "REDIS_CACHE_TEST_WEI")
    public static class ConsumerRedisCache implements RocketMQListener<Map<String, Object>> {

        @Autowired
        private RedisTemplate<String, Object> redisTemplate;

        private List<String> list = new ArrayList<>();

        @Override
        public void onMessage(Map<String, Object> message) {
            System.out.println("消费消息:" + message);
            final String redisKey = (String) message.get("redisKey");
            final String requestId = String.valueOf(message.get("requestId"));

            // 记录日志判断是否重复消费
            if (list.contains(requestId)) {
                redisTemplate.opsForValue().set(redisKey,
                        "网络服务繁忙请稍后再试",
                        1L, TimeUnit.MINUTES);
                return;
            }
            try {
                // 查询数据库 存在->true,数据库不存在->false;
                System.out.println("使用请求ID:" + requestId + "查询数据库");
                Thread.sleep(3000L); // 模拟数据库查询
                if (false) { // 假设数据中有此数据
                    // 同步数据库查询的消息
                    redisTemplate.opsForValue().set(redisKey,
                            "Hello Redis Cache Temporarily Expired",
                            20L, TimeUnit.SECONDS);
                } else {
                    // 为请求key赋值
                    redisTemplate.opsForValue().set(redisKey,
                            "网络服务繁忙请稍后再试",
                            1L, TimeUnit.MINUTES);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // topic需要和生产者的topic一致，consumerGroup属性是必须指定的，内容可以随意
    // selectorExpression的意思指的就是tag，默认为“*”，不设置的话会监听所有消息
    @Service
    @RocketMQMessageListener(topic = topic, consumerGroup = "Pro_Group")
    public static class ConsumerSend implements RocketMQListener<Account> {
        // 监听到消息就会执行此方法
        @Override
        public void onMessage(Account user) {
            log.info("监听到消息：user={}", JSON.toJSONString(user));
            System.err.println("未填写TAGEt：" + JSON.toJSONString(user));
        }
    }

    // 注意：这个ConsumerSend2和上面ConsumerSend在没有添加tag做区分时，不能共存，不然生产者发送一条消息，这两个都会去消费，类型不同会有一个报错，所以实际运用中最好加上tag，写这只是让你看知道就行
    @Service
    @RocketMQMessageListener(topic = topic, selectorExpression = TAGS, consumerGroup = "Con_Group_Two")
    public static class ConsumerSend2 implements RocketMQListener<Account> {
        @Override
        public void onMessage(Account str) {
            log.info("监听到消息：str={}", JSON.toJSONString(str));
            System.err.println("未填写TAGEt和分组名不正确" + JSON.toJSONString(str));
        }
    }

    // MessageExt：是一个消息接收通配符，不管发送的是String还是对象，都可接收，当然也可以像上面明确指定类型
    @Service
    @RocketMQMessageListener(topic = topic, selectorExpression = TAGS, consumerGroup = "Pro_Groups")
    public static class Consumer implements RocketMQListener<MessageExt> {
        @Override
        public void onMessage(MessageExt messageExt) {
            System.err.println("整个消费消息:" + JSON.toJSONString(messageExt));
            byte[] body = messageExt.getBody();
            String msg = new String(body);
            log.info("监听到消息：msg={}", msg);
            System.err.println("信息全部正确消息：" + msg);
        }
    }

    @Service
    @RocketMQMessageListener(topic = "topic", selectorExpression = "TAGS", consumerGroup = "Pro_Groupsss")
    public static class Consumers implements RocketMQListener<MessageExt> {
        @Override
        public void onMessage(MessageExt messageExt) {
            byte[] body = messageExt.getBody();
            String msg = new String(body);
            log.info("监听到消息：msg={}", msg);
            System.err.println("信息全部不正确：" + msg);
        }
    }

}