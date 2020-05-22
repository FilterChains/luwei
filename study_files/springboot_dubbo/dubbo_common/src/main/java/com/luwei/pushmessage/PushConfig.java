package com.luwei.pushmessage;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>@description : 阿里云移动推送配置类 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/3/31 15:09 </p>
 */
public class PushConfig {

    /**
     * 区域设置，目前这有这一个值
     */
    private static final String REGION = "cn-hangzhou";

    private DefaultAcsClient client = new DefaultAcsClient(DefaultProfile.getProfile(REGION, AliYunIm.ACCESSKEY, AliYunIm.SECRET));

    public DefaultAcsClient getClient() {
        return client;
    }

    /**
     * IOS应用集合
     */
    private Map<PushParams.IosAppKey, Long> iosApply = new HashMap<PushParams.IosAppKey, Long>(16);

    public Map<PushParams.IosAppKey, Long> getIosApply() {
        iosApply.put(PushParams.IosAppKey.CLOUD_MEDICINE_IOS, 29117683L);
        return iosApply;
    }

    /**
     * Android应用集合
     */
    private Map<PushParams.AndroidAppKey, Long> androidApply = new HashMap<PushParams.AndroidAppKey, Long>(16);

    public Map<PushParams.AndroidAppKey, Long> getAndroidApply() {
        androidApply.put(PushParams.AndroidAppKey.CLOUD_MEDICINE_ANDROID, 29117683L);
        return androidApply;
    }
}
