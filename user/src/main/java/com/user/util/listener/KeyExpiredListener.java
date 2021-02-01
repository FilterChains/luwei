package com.user.util.listener;

import redis.clients.jedis.JedisPubSub;

/**
 * <p>@description : 监听redis key失效 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/7/14 11:32 </p>
 *
 * @return
 **/
public class KeyExpiredListener extends JedisPubSub {

    /**
     * <p>@description : 订阅频道模式时的回调 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/7/14 11:57 </p>
     *
     * @param pattern
     * @param subscribedChannels
     **/
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe "
                + pattern + " " + subscribedChannels);
    }

    /**
     * <p>@description : 监听到订阅模式接受到消息时的回调 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/7/14 11:58 </p>
     *
     * @param pattern
     * @param channel
     * @param message
     * @return
     **/
    @Override
    public void onPMessage(String pattern, String channel, String message) {

        //System.out.println("onPMessage pattern "
        //        + pattern + " " + channel + " " + message);
        System.out.println("监听信息:" + message);
        String concat = channel.concat(":").concat(message);
        System.out.println("变换：" + concat);
        if (pattern.concat("del:Hello,World").equals(concat)) {
            System.err.println("过期成功");
        }
        if ("__keyevent@0__:del:NOTIFY".equals(concat)) {
            System.err.println("过期成功");
        }
    }

    /**
     * <p>@description : 监听到订阅频道接受到消息时的回调 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/7/14 11:58 </p>
     *
     * @param channel
     * @param message
     * @return
     **/
    @Override
    public void onMessage(String channel, String message) {
        super.onMessage(channel, message);
    }

    /**
     * <p>@description : 订阅频道时的回调  </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/7/14 12:00 </p>
     *
     * @param channel
     * @param subscribedChannels
     * @return
     **/
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        super.onSubscribe(channel, subscribedChannels);
    }

    /**
     * <p>@description : 取消订阅频道时的回调 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/7/14 11:59 </p>
     *
     * @param channel
     * @param subscribedChannels
     * @return
     **/
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        super.onUnsubscribe(channel, subscribedChannels);
    }

    /**
     * <p>@description : 取消订阅模式时的回调 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/7/14 11:59 </p>
     *
     * @param pattern
     * @param subscribedChannels
     * @return
     **/
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        super.onPUnsubscribe(pattern, subscribedChannels);
    }
}
