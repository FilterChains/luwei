package com.luwei.service;

import org.apache.rocketmq.client.producer.SendResult;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.service
 * @auther: luwei
 * @description:
 * @date: 2020/2/23 20:04
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public interface RcoketMqService {

    SendResult openAccountMsg(String msgInfo);
}
