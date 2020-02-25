package com.luwei.dubbo_provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.luwei.dubbo_provider.rocketmq.config.ParamConfigService;
import com.luwei.service.RcoketMqService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.dubbo_provider.service.impl
 * @auther: luwei
 * @description:
 * @date: 2020/2/23 20:06
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class RcoketMqServiceImpl implements RcoketMqService {

    @Autowired(required = false)
    private DefaultMQProducer defaultMQProducer;

    @Autowired(required = false)
    private ParamConfigService paramConfigService ;

    @Override
    public SendResult openAccountMsg(String msgInfo) {
        // 可以不使用Config中的Group
        defaultMQProducer.setProducerGroup(paramConfigService.feePlatGroup);
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message(paramConfigService.feePlatTopic,
                    paramConfigService.feeAccountTag,
                    "fee_open_account_key", msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult ;
    }
}
