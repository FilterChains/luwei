package com.user.util.pushmessage;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Calendar;

public class PushServiceImpl extends PushExpandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushServiceImpl.class);

    @Autowired
    private IosPushExpandServiceImpl iosPushExpandServiceImpl;

    @Autowired
    private AndroidPushExpandServiceImpl androidPushExpandServiceImpl;

    private void validateMsg(PushParams pushParams) {
        Assert.notNull(pushParams, "推送参数不能为空");
        Assert.isTrue(!(ObjectUtils.isEmpty(pushParams.getIosKey()) &&
                ObjectUtils.isEmpty(pushParams.getAndroidKey())), "请选择一个应用");
        Assert.notNull(pushParams.getTarget(), "推送目标不能为空");
        Assert.hasText(pushParams.getTargetValue(), "推送目标设备不能为空");
        Assert.hasText(pushParams.getTitle(), "推送标题不能为空");
        Assert.hasText(pushParams.getBody(), "推送内容不能为空");
        Assert.notNull(pushParams.getDeviceType(), "推送设备不能为空");
        Assert.notNull(pushParams.getPushType(), "推送类型不能为空");
    }

    @Override
    public void pushSingleMessageOrNotice(PushParams pushParams) {
        validateMsg(pushParams);
        String format = DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss");
        switch (pushParams.getDeviceType()) {
            case IOS:
                LOGGER.info("正在进行IOS推送,时间:[{}]", format);
                new IosPushExpandServiceImpl().pushSingleMessageOrNotice(pushParams);
                //iosPushExpandServiceImpl.pushSingleMessageOrNotice(pushParams);
                break;
            case ANDROID:
                LOGGER.info("正在进行ANDROID推送,时间:[{}]", format);
                androidPushExpandServiceImpl.pushSingleMessageOrNotice(pushParams);
                break;
            default:
                LOGGER.info("正在进行IOS和ANDROID推送,时间:[{}]", format);
                iosPushExpandServiceImpl.pushSingleMessageOrNotice(pushParams);
                androidPushExpandServiceImpl.pushSingleMessageOrNotice(pushParams);
                break;
        }
    }
}
