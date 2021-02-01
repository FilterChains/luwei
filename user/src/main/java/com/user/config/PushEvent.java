package com.user.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

/**
 * <p>@description : 实现 ApplicationContextAware 可以从BeanFactory获取所有注入的实例 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2021/2/1 21:20 </p>
 **/
public class PushEvent implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * <p>@description : 发布事件 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2021/2/1 21:20 </p>
     *
     * @param event {@link ApplicationEvent}
     **/
    public static void pushApplicationEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }
}

