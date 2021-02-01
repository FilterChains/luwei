package com.user.util.schema.event;

/**
 * <p>@description : 事件 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/24 11:46 </p>
 *
 **/
public class EventOperation {

    private static final String STRING = "YES";

    public void notifyEvent(String resource){
        if(!STRING.equals(resource)){
            // 发布事件
            new EventPublish().publishEvent(new EventResource(this,resource));
        }
    }
}
