package com.user.util.schema.event;

import org.springframework.context.ApplicationEvent;
/**
 * <p>@description : 创建事件源 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/24 11:37 </p>
 *
 **/
public class EventResource extends ApplicationEvent {

    private final String resource;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public EventResource(Object source,String resource) {
        super(source);
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
