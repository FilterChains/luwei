package com.luwei.dubbo_provider.rabbitmqconfig;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @projectName： springbootdubbo
 * @packageName: com.luwei.dubbo_provider.rabbitmq
 * @auther: luwei
 * @description: 配置消费者，消费者监听指定用于延时消费的队列repeatTradeQueue
 * @date: 2020/1/31 15:00
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */

@Component
@RabbitListener(queues = "repeatTradeQueue")
public class RabbitMqListenerConsumer {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("repeatTradeQueue 接收时间:" + LocalDateTime.now().toString() + " 接收内容:" + msg);
    }
}