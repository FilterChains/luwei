package com.user.util.pushmessage;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>@description : 推送参数类,使用须知 </p>
 * <p>@description :
 * 标题小于等于 200 个字节 (中文算 3 个)；
 * 使用 Android 厂商通道的限制 ：
 * AndroidPopupTitle ：
 * 小米： 50个字符，中英文都算一个
 * 华为：未做明确限制，受payload整体长度限制
 * 魅族：32个字符，中英文都算一个
 * oppo：32个字符，中英文都算一个
 * vivo： 20个汉字，40个英文字符
 * AndroidPopupBody：
 * 小米：128个字符，中英文都算一个
 * 华为：未做明确限制，受payload整体长度限制
 * 魅族：100个字符，中英文都算一个
 * oppo：200个字符，中英文都算一个
 * vivo：50个汉字，100个英文字符
 * 一个设备只能绑定一个账号，账号小于等于 64 字节。一个账号可以绑定多个设备，设备数量没有限制；
 * 一个设备可以绑定多个别名，最多绑定128个别名，别名长度小于等于128字节。一个别名可以绑定多个设备，最多绑定128个设备；
 * targetValue如果为集合，按别名或者设备推送，一次最多传递1000个(通过“,”分割)，按账户推送，则最多传递100个;
 * 每个App最多创建1万个tag，tag名称要小于等于128个字符(中文算1个)，一个Tag下可以绑定多个设备，设备个数没有限制；
 * OpenAPI推送包括全推（推送所有设备）和批量推送（推送部分指定设备），批量推送时，可按照设备、账号或者别名推送，
 * 每次最多指定1000个设备、账号或别名；
 * OpenAPI调用频率：500 QPS/IP；
 * 全推频率限制，同一个Appkey，同一种操作系统（Android/IOS），两次全推的间隔至少为1秒；此外，
 * 连续10分钟内允许最多全推10次通知（消息转通知按通知处理），连续10分钟内允许最多全推30次消息。</p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/3/27 9:15 </p>
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class PushParams implements Serializable {

    /**
     * IOS应用Key(必须)->注：请选择对应的应用
     */
    private IosAppKey iosKey;

    public enum IosAppKey {

        /**
         * 云采医药
         */
        CLOUD_MEDICINE_IOS("CLOUD_MEDICINE_IOS", "云采医药"),

        /**
         * 云采管家
         */
        CLOUD_STEWARD_IOS("CLOUD_STEWARD_IOS", "云采管家");

        @EnumValue
        private final String value;
        private final String description;

        IosAppKey(String value, String description) {
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
     * Android应用Key(必须)->注：请选择对应的应用
     */
    private AndroidAppKey androidKey;

    public enum AndroidAppKey {

        /**
         * 云采医药
         */
        CLOUD_MEDICINE_ANDROID("CLOUD_MEDICINE_ANDROID", "云采医药"),

        /**
         * 云采管家
         */
        CLOUD_STEWARD_ANDROID("CLOUD_STEWARD_ANDROID", "云采管家");

        @EnumValue
        private final String value;
        private final String description;

        AndroidAppKey(String value, String description) {
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
     * 推送目标（必须）
     */
    private PushTarget target;

    public enum PushTarget {

        /**
         * 根据设备推送
         */
        DEVICE("DEVICE", "根据设备推送"),

        /**
         * 根据账号推送
         */
        ACCOUNT("ACCOUNT", "根据账号推送"),

        /**
         * 根据别名推送
         */
        ALIAS("ALIAS", "根据别名推送"),

        /**
         * 根据标签推送
         */
        FLAG("TAG", "根据标签推送"),

        /**
         * 推送给全部设备(同一种DeviceType的两次全推的间隔至少为1秒)
         */
        ALL("ALL", "推送给全部设备(同一种DeviceType的两次全推的间隔至少为1秒)"),

        /**
         * 初始化持续推送，推送目标由后续的ContinuouslyPush接口指定
         */
        TBD("TBD", "初始化持续推送，推送目标由后续的ContinuouslyPush接口指定");

        @EnumValue
        private final String value;
        private final String description;

        PushTarget(String value, String description) {
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
     * 根据Target来设定（必须）
     * 多个值使用逗号分隔，超过限制需要分多次推送
     * Target=ACCOUNT，值如account1,account2（最多支持1000个）
     */
    private String targetValue;


    /**
     * 推送标题
     * 注：Android推送时通知/消息的标题以及iOS消息的标题（必填）
     */
    private String title;

    /**
     * 推送内容（必须）
     * 注：Android推送时通知的内容/消息的内容；iOS消息/通知内容，推送的内容大小是有限制的
     */
    private String body;

    /**
     * 设备类型（必须）
     * 注：该参数仅对旧的不分端App有意义；新的分端App下，该参数填写”ALL”或与App分端类型对应的值均可
     */
    private PushDeviceType deviceType;

    public enum PushDeviceType {

        /**
         * 全部类型设备
         */
        ALL("ALL", "全部类型设备"),

        /**
         * iOS设备
         */
        IOS("iOS", "iOS设备"),

        /**
         * Android设备
         */
        ANDROID("ANDROID", "Android设备");

        @EnumValue
        private final String value;
        private final String description;

        PushDeviceType(String value, String description) {
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
     * 推送类型（必须）
     * 注：当设置为MESSAGE推送类型时,ios/android中的参数传递不生效
     */
    private Type pushType;

    public enum Type {

        /**
         * 消息类型
         */
        MESSAGE("MESSAGE", "消息类型"),

        /**
         * 通知类型
         */
        NOTICE("NOTICE", "通知类型");

        @EnumValue
        private final String value;
        private final String description;

        Type(String value, String description) {
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
     * 用于定时发送。不设置缺省是立即发送。时间格式按照ISO8601标准表示，
     * 并需要使用UTC时间，格式为YYYY-MM-DDThh:mm:ssZ。注：Target为TBD（持续推送）时，不支持定时设置。
     * 默认立即推送
     */
    private String pushTime = null;

    /**
     * 当推送类型为通知类型（NOTICE），才生效
     */
    private Map<String, String> parameters;

}
