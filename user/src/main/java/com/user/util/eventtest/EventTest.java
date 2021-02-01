package com.user.util.eventtest;

public class EventTest {


    public static void main(String[] args) {

        //EventResource eventResource = new EventResource();
        //eventResource.registerEvent(new EventListener());
        //eventResource.setEventObject(new DemoTest());
        //// 开启事件
        //EventResource.status = Boolean.TRUE;
        //eventResource.startEvent();
        System.out.println(textTryCatch());
    }

    private static String textTryCatch(){
        System.out.println("开始执行");
        try {
            int i = 1/0;
            //System.out.println("正常执行");
            return "正常返回";
        } catch (Exception e) {
            return "catch";
        }finally {
            System.out.println("你好");
        }
        //System.out.println("执行没有");
        //return "over";
    }


}
