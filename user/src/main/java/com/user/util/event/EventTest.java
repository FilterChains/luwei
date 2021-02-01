package com.user.util.event;

public class EventTest {


    public static void main(String[] args) {


        EventSourceObject object = new EventSourceObject();

        //注册监听器
        object.addCusListener(new OperationListener() {
            @Override
            public void fireCusEvent(OperationEvent e) {
                super.fireCusEvent(e);
            }
        });
        
        //触发事件
        object.setName("eric");


    }
}
