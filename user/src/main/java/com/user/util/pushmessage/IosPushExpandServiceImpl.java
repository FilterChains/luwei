package com.user.util.pushmessage;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.push.model.v20160801.PushNoticeToiOSRequest;
import com.aliyuncs.push.model.v20160801.PushNoticeToiOSResponse;
import com.aliyuncs.push.model.v20160801.PushRequest;
import com.aliyuncs.push.model.v20160801.PushResponse;
import com.aliyuncs.utils.ParameterHelper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Date;

public class IosPushExpandServiceImpl extends IosPushExpandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IosPushExpandServiceImpl.class);

    @Override
    public void pushMessage(PushParams pushParams) {

    }

    @Override
    public void pushSingleMessageOrNotice(PushParams pushParams) {
        try {
            PushRequest pushRequest = new PushRequest();
            pushRequest.setRegionId("cn-hangzhou");
            // 设置请求接口 本接口区分Android和iOS平台，对于不同平台的推送调用时需要传入平台对应的AppKey
            pushRequest.setActionName(PushBaseParam.PushAction.PUSH.getValue());
            // 设置APP KEY
            pushRequest.setAppKey(super.getIosApply().get(pushParams.getIosKey()));
            // 设置发送的消息的标题（iOS10以上才会显示，可不填）
            pushRequest.setTitle(pushParams.getTitle());
            // 设置通知内容
            pushRequest.setBody(pushParams.getBody());
            // 设置设备类型
            pushRequest.setDeviceType(PushParams.PushDeviceType.IOS.getValue());
            // 设置推送目标
            pushRequest.setTarget(pushParams.getTarget().getValue());
            // 设置推送账号，根据Target来设定，多个值使用逗号分隔，超过限制需要分多次推送，最多个值使用逗号分隔，最多支持100个。
            pushRequest.setTargetValue(pushParams.getTargetValue());
            // 设置环境
            // iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。"DEV" : 表示开发环境 "PRODUCT" : 表示生产环境
            pushRequest.setIOSApnsEnv(PushBaseParam.EnvironmentalSwitch.DEV.getValue());
            // 用于定时发送。不设置缺省是立即发送。时间格式按照ISO8601标准表示，并需要使用UTC时间，格式为YYYY-MM-DDThh:mm:ssZ。
            // 注：Target为TBD（持续推送）时，不支持定时设置。
            final Date pushDate = new Date(System.currentTimeMillis() + 3600 * 1000); //用于定时发送。不设置缺省是立即发送。时间格式按照ISO8601标准表示，并需要使用UTC时间，格式为`YYYY-MM-DDThh:mm:ssZ`。
            final String pushTime = ParameterHelper.getISO8601Time(pushDate);
            pushRequest.setPushTime(pushParams.getPushTime());
            // 设置推送类型
            switch (pushParams.getPushType()) {
                case MESSAGE: // 消息
                    // 离线消息转通知仅适用于生产环境。
                    pushRequest.setIOSRemind(true);
                    // iOS消息转通知时使用的iOS通知内容，仅当iOSApnsEnv=PRODUCT && iOSRemind为true时有效
                    pushRequest.setIOSRemindBody(pushParams.getBody());
                    // 设置推送类型
                    pushRequest.setPushType(PushParams.Type.MESSAGE.getValue());
                    break;
                case NOTICE: // 通知
                    // 验证是否传有参数
                    Assert.notEmpty(pushParams.getParameters(), "跳转参数不能为空");
                    // 是否使能iOS通知扩展处理（iOS 10+）
                    pushRequest.setIOSMutableContent(true);
                    // iOS通知的扩展属性。
                    //iOS10+可以在此指定富媒体推送通知的资源Url：{“attachment”: “https://xxxx.xxx/notification_pic.png"} 。
                    // 该参数要以Json map的格式传入，否则会解析出错
                    pushRequest.setIOSExtParameters(JSONObject.toJSONString(pushParams.getParameters()));
                    // 设置推送类型
                    pushRequest.setPushType(PushParams.Type.NOTICE.getValue());
                    break;
                default:
                    LOGGER.error("推送类型选择错误,请仔细检查");
                    throw new IllegalArgumentException("推送类型选择错误,请仔细检查");
            }

            //发送消息、通知
            PushResponse response = super.getClient().getAcsResponse(pushRequest);
            LOGGER.info("IOS发送消息成功：消息码[{}]", JSONObject.parseObject(new Gson().toJson(response)).get("messageId"));
        } catch (ClientException e) {
            LOGGER.error("IOS发送消息失败,消息码：[{}]", e.getRequestId());
            LOGGER.error("IOS发送消息失败,返回码：[{}],失败原因：[{}]", e.getErrCode(), e.getErrMsg());
        }
    }

    @Override
    public void pushNotice(PushParams pushParams) {
        try {
            PushNoticeToiOSRequest pushNoticeToiOSRequest = new PushNoticeToiOSRequest();
            // 设置请求接口
            pushNoticeToiOSRequest.setActionName(PushBaseParam.PushAction.PUSH_NOTICE_TO_IOS.getValue());
            // 设置环境
            // iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。"DEV" : 表示开发环境 "PRODUCT" : 表示生产环境
            pushNoticeToiOSRequest.setApnsEnv(PushBaseParam.EnvironmentalSwitch.DEV.getValue());
            // 设置APP KEY
            pushNoticeToiOSRequest.setAppKey(29117683L);
            // 设置发送的通知的标题（iOS10以上才会显示，可不填）
            pushNoticeToiOSRequest.setTitle(pushParams.getTitle());
            // 设置通知内容
            pushNoticeToiOSRequest.setBody(pushParams.getBody());
            // 设置推送目标
            pushNoticeToiOSRequest.setTarget(pushParams.getTarget().getValue());
            // 设置推送账号，最多个值使用逗号分隔，最多支持100个。
            pushNoticeToiOSRequest.setTargetValue(pushParams.getTargetValue());
            // 设置JobKey信息可不填写
            //pushNoticeToiOSRequest.setJobKey("");
            // 设置跳转参数
            pushNoticeToiOSRequest.setExtParameters(JSONObject.toJSONString(pushParams.getParameters()));
            // 发送消息
            PushNoticeToiOSResponse response = super.getClient().getAcsResponse(pushNoticeToiOSRequest);
            LOGGER.info("IOS发送通知成功：消息码[{}]", JSONObject.parseObject(new Gson().toJson(response)).get("messageId"));
        } catch (ClientException e) {
            LOGGER.error("IOS发送通知失败,消息码：[{}]", e.getRequestId());
            LOGGER.error("IOS发送通知失败,返回码：[{}],失败原因：[{}]", e.getErrCode(), e.getErrMsg());
        }
    }
}
