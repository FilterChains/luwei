package com.luwei.dubbo_provider.rabbitmqconfig;

/**
 * @projectName： springbootdubbo
 * @packageName: com.luwei.dubbo_provider.rabbitmq
 * @auther: luwei
 * @description: 配置生产者，这里生产者需要指定前面配置了过期时间的队列deadLetterQueue
 * @date: 2020/1/31 14:59
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RabbitMqProvider {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void send(String msg) {
        System.out.println("DeadLetterSender 发送时间:"+LocalDateTime.now().toString()+" msg内容："+msg);
        rabbitTemplate.convertAndSend("deadLetterQueue", msg);
    }
}
