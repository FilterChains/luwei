package com.luwei.pushmessage;

/**
 * <p>@description : 推送服务 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/3/27 10:42 </p>
 */
public interface PushService {

    /**
     * <p>@description : 发送单条（消息、通知），有定时发送（消息、通知）参数，默认立即发送。
     * 注：发送通知消息的时候必须带跳转参数
     * 目的在于不需要跳转的消息推送走消息通道，有跳转的推送走通知通道</p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/3/27 12:30 </p>
     */
    void pushSingleMessageOrNotice(PushParams pushParams);

}
