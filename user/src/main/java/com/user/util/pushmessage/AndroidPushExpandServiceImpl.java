package com.user.util.pushmessage;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.push.model.v20160801.PushRequest;
import com.aliyuncs.push.model.v20160801.PushResponse;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndroidPushExpandServiceImpl extends AndroidPushExpandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AndroidPushExpandServiceImpl.class);

    @Override
    public void pushSingleMessageOrNotice(PushParams pushParams) {
        DefaultAcsClient client = new DefaultAcsClient(DefaultProfile.getProfile("cn-hangzhou", "", ""));
        PushRequest pushRequest = new PushRequest();
        try {
            PushResponse response = client.getAcsResponse(pushRequest);
            LOGGER.info("ANDROID发送消息成功：消息码[{}]", JSONObject.parseObject(new Gson().toJson(response)).get("messageId"));
        } catch (ClientException e) {
            LOGGER.error("ANDROID发送消息失败,消息码：[{}]", e.getRequestId());
            LOGGER.error("ANDROID发送消息失败,返回码：[{}],失败原因：[{}]", e.getErrCode(), e.getErrMsg());
        }
    }

    @Override
    public void pushNotice(PushParams pushParams) {
        DefaultAcsClient client = new DefaultAcsClient(DefaultProfile.getProfile("cn-hangzhou", "", ""));
        PushRequest pushRequest = new PushRequest();
        try {
            PushResponse response = client.getAcsResponse(pushRequest);
            LOGGER.info("ANDROID发送通知成功：消息码[{}]", JSONObject.parseObject(new Gson().toJson(response)).get("messageId"));
        } catch (ClientException e) {
            LOGGER.error("ANDROID发送通知失败,消息码：[{}]", e.getRequestId());
            LOGGER.error("ANDROID发送通知失败,返回码：[{}],失败原因：[{}]", e.getErrCode(), e.getErrMsg());
        }
    }
}
