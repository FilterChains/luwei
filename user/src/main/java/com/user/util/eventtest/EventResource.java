package com.user.util.eventtest;

import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>@description : 时间源，注册事件 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/9/27 15:10 </p>
 **/
public class EventResource {

    /**
     * 事件监听对象
     */
    private Object object;

    /**
     * 事件触发状态
     */
    public static boolean status;


    /**
     * 监听事件容器
     */
    private final Set<EventListener> eventListenerSet = new HashSet<EventListener>();

    /**
     * <p>@description : 注入事件对象 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/9/27 17:06 </p>
     *
     * @param object
     **/
    public void setEventObject(Object object) {
        this.object = object;
    }

    /**
     * <p>@description : 注册事件监听 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/9/27 15:13 </p>
     *
     * @param eventListener ->事件监听
     **/
    public void registerEvent(EventListener eventListener) {
        this.eventListenerSet.add(eventListener);
    }

    /**
     * 开启事件
     */
    public void startEvent() {
        if (status) {
            registerEvent();
        }
    }

    /**
     * 注册事件监听，并执行
     */
    private void registerEvent() {
        Set<EventListener> listenerSet = this.eventListenerSet;
        for (EventListener listener : listenerSet) {
            if ("1".equals(listener.getType())) {
                // Execute Task
                listener.eventOperation(new EventObject(this.object));
            }
        }
    }
}
