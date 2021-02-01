package com.user.util.schema.event;

import org.springframework.context.ApplicationListener;
/**
 * <p>@description : 事件监听 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/24 11:39 </p>
 *
 **/
public class EventMonitor implements ApplicationListener<EventResource> {

    @Override
    public void onApplicationEvent(EventResource eventResource) {
        // 监听与设计模式中的观察者模式有责异曲同工之处
        System.out.println("事件监听到:".concat(eventResource.getResource()).concat("发生变化"));
    }
}
