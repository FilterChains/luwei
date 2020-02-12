package com.luwei.dubbo_provider.rabbitmqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName： springbootdubbo
 * @packageName: com.luwei.dubbo_provider.rabbitmq
 * @auther: luwei
 * @description: 第一个配置的exchange是用来接收已过期的队列信息并进行重新分配队列进行消费，
 * 第二个配置的repeatTradeQueue为exchange重新分配的队列名，
 * 第三个是将repeatTradeQueue队列与exchange交换机绑定，并指定对应的routing key，
 * 第四个配置的就是我们要设置过期时间的队列deadLetterQueue，配置中有三个参数，
 * x-message-ttl为过期时间，该队列所有消息的过期时间都为我们配置的这个值，单位为毫秒，这里我设置过期时间为3秒，
 * x-dead-letter-exchange是指过期消息重新转发到指定交换机，也就是exchange，
 * x-dead-letter-routing-key是该交换机上绑定的routing-key，
 * 将通过配置的routing-key分配对应的队列，也就是前面配置的repeatTradeQueue。
 * @date: 2020/1/31 14:58
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Configuration
public class RabbitMqConfig {

    //交换机用于重新分配队列
    @Bean
    DirectExchange exchange() {
        return new DirectExchange("exchange");
    }

    //用于延时消费的队列
    @Bean
    public Queue repeatTradeQueue() {
        Queue queue = new Queue("repeatTradeQueue",true,false,false);
        return queue;
    }

    //绑定交换机并指定routing key
    @Bean
    public Binding  repeatTradeBinding() {
        return BindingBuilder.bind(repeatTradeQueue()).to(exchange()).with("repeatTradeQueue");
    }

    //配置死信队列
    @Bean
    public Queue deadLetterQueue() {
        Map<String,Object> args = new HashMap<>();
        args.put("x-message-ttl", 10000); //延迟多少秒(毫秒)
        args.put("x-dead-letter-exchange", "exchange"); //交换机
        args.put("x-dead-letter-routing-key", "repeatTradeQueue"); //延时队列
        return new Queue("deadLetterQueue", true, false, false, args);
    }

}
