package com.user.util.event;

import java.util.EventListener;

/**
 * <p>@description : 事件监听器。实现java.util.EventListener接口,注册在事件源上,当事件源的属性或状态改变时,取得相应的监听器调用其内部的回调方法。 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/9/27 14:26 </p>
 **/
public class OperationListener implements EventListener {
    /**
     * <p>@description :  事件监听器，实现java.util.EventListener接口。定义回调方法，将你想要做的事
     * *放到这个方法下,因为事件源发生相应的事件时会调用这个方法。 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/9/27 14:27 </p>
     *
     * @param e
     * @return
     **/
    //事件发生后的回调方法
    public void fireCusEvent(OperationEvent e) {
        EventSourceObject source = (EventSourceObject) e.getSource();
        System.out.println("My name has been changed!");
        System.out.println("I got a new name,named " + source.getName());
    }

}
