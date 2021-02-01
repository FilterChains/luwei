package com.user.util.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

public class PushEvent implements ApplicationContextAware {


        private ApplicationContext applicationContext;


        public void publishEvent(ApplicationEvent event) {
            applicationContext.publishEvent(event);
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
                this.applicationContext = applicationContext;
        }
}
