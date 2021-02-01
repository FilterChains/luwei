package com.user.util.eventtest;


import java.util.EventObject;

/**
 * <p>@description : 事件监听，处理事件触发后的操作 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/9/27 15:05 </p>
 **/
public class EventListener implements java.util.EventListener {

    public String type;

    public String getType() {
        return type = "1";
    }

    public void setType(String type) {
        this.type = type;
    }

    public void eventOperation(EventObject eventObject) {
        try {
            System.out.println("开始处理事件");
            Thread.sleep(3000L);
            // DemoTest source = (DemoTest) eventObject.getSource();
            // Map<Integer, String> mp1 = source.getMp1();
            // System.out.println(source.toString());
            // System.out.println(mp1);
            // System.out.println("结束事件处理");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
