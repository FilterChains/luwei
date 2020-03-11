package com.luwei.dubbo_provider.rocketmq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.rocketmq.config
 * @auther: luwei
 * @description: 配置参数绑定
 * @date: 2020/2/23 19:42
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Service
public class ParamConfigService {
    @Value("${fee-plat.fee-plat-group}")
    public String feePlatGroup ;
    @Value("${fee-plat.fee-plat-topic}")
    public String feePlatTopic ;
    @Value("${fee-plat.fee-account-tag}")
    public String feeAccountTag ;
}
