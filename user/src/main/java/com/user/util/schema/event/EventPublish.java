package com.user.util.schema.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

public class EventPublish implements ApplicationContextAware {

    /**
     * 注册上下文
     */
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * <p>@description : 发布事件 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/24 11:53 </p>
     *
      * @param event
     **/
    public void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

}
