package com.luwei.dubbo_provider.rocketmq.listener;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luwei.dubbo_provider.rocketmq.config.ParamConfigService;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.rocketmq.listener
 * @auther: luwei
 * @description: 消息消费监听
 * @date: 2020/2/23 19:40
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Component
public class RocketMsgListener implements MessageListenerConcurrently {
    private static final Logger LOG = LoggerFactory.getLogger(RocketMsgListener.class) ;
    @Resource
    private ParamConfigService paramConfigService ;
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(list)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        MessageQueue messageQueue = context.getMessageQueue();
        try {
            System.out.println("消费消息："+messageQueue);
            String result = new String(messageExt.getBody(), "UTF-8");
            JSONObject jsonObject = JSONArray.parseObject(result);
            LOG.info("接受到的消息为："+jsonObject);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int reConsume = messageExt.getReconsumeTimes();
        // 消息已经重试了3次，如果不需要再次消费，则返回成功
        if(reConsume ==3){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        if(messageExt.getTopic().equals(paramConfigService.feePlatTopic)){
            String tags = messageExt.getTags() ;
            switch (tags){
                case "luwei_Tag":
                    LOG.info("开户 tag == >>"+tags);
                    break ;
                default:
                    LOG.info("未匹配到Tag == >>"+tags);
                    break;
            }
        }
        // 消息消费成功
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
