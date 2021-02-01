package com.user.util.schema.event;

/**
 * <p>@description : 测试事件监听 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/24 11:48 </p>
 *
 **/
public class TestEvent {
    public static void main(String[] args) {
        EventOperation eventOperation = new EventOperation();
        eventOperation.notifyEvent("142857");
    }
}
