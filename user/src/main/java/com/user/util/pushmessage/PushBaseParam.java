package com.user.util.pushmessage;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>@description : 推送基本参数 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/3/27 10:08 </p>
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class PushBaseParam implements Serializable {

    /**
     * 操作接口名称(必须)
     */
    private PushAction action;

    public enum PushAction {

        /**
         * 推送接口设置
         */
        PUSH_MESSAGE_TO_ANDROID("PushMessageToAndroid", "推消息给Android设备"),
        PUSH_MESSAGE_TO_IOS("PushMessageToiOS", "推消息给iOS设备"),
        PUSH_NOTICE_TO_ANDROID("PushNoticeToAndroid", "推通知给Android设备"),
        PUSH_NOTICE_TO_IOS("PushNoticeToiOS", "推通知给iOS设备"),
        PUSH("Push", "推送高级接口,本接口区分Android和iOS平台"),
        PUSH_BATCH_MESSAGE("MassPush", "批量推送接口"),
        PUSH_CONTINUOUSLY("ContinuouslyPush", "持续推送接口"),
        PUSH_CANCEL("CancelPush", "取消定时推送任务");

        @EnumValue
        private final String value;
        private final String description;

        PushAction(String value, String description) {
            this.value = value;
            this.description = description;
        }

        public String getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * IOS环境开关
     */
    private EnvironmentalSwitch iosEnvironmentalSwitch;

    public enum EnvironmentalSwitch {

        /**
         * 开发环境
         */
        DEV("DEV", "开发环境"),

        /**
         * 生产环境
         */
        PRODUCT("PRODUCT", "生产环境");
        @EnumValue
        private final String value;
        private final String description;

        EnvironmentalSwitch(String value, String description) {
            this.value = value;
            this.description = description;
        }

        public String getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }
    }

}
