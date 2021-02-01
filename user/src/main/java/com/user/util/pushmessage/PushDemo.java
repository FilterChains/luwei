package com.user.util.pushmessage;

import com.google.common.collect.Maps;
import com.luwei.pushmessage.PushParams;
import com.luwei.pushmessage.PushServiceImpl;

import java.util.Map;

public class PushDemo {

    public static void main(String[] args) {
        Map<String, String> concurrentMap = Maps.newConcurrentMap();
        concurrentMap.put("msg", "这是消息通知跳转参数");
        concurrentMap.put("id", "1234567");
        concurrentMap.put("url", "www.yuncai998.com");
        new PushServiceImpl().pushSingleMessageOrNotice(new PushParams()
                .setIosKey(PushParams.IosAppKey.CLOUD_MEDICINE_IOS)
                .setTarget(PushParams.PushTarget.ACCOUNT)
                .setTargetValue("18883528101")
                .setTitle("测试推送标题")
                .setParameters(concurrentMap)
                .setBody("你好，正在推送消息,这是消息通知，消息内容")
                .setDeviceType(PushParams.PushDeviceType.IOS)
                .setPushType(PushParams.Type.NOTICE));
    }
}
