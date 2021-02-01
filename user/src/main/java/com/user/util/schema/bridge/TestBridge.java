package com.user.util.schema.bridge;

/**
 * <p>@description : 测试桥接模式(各部分组件化，各自完成各自的事情。逻辑解耦) </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/24 15:47 </p>
 **/
public class TestBridge {

    public static void main(String[] args) {

        Phone phone1 = new com.luwei.util.schema.bridge.PhoneImpl(new I7Cpu(),new Memory526GB());
        System.out.println("========================================");
        phone1.iPhone();
        System.out.println("========================================");
        Phone phone2 = new com.luwei.util.schema.bridge.PhoneImpl(new I7Cpu(),new Memory128GB());
        phone2.huaWeiPhone();
        System.out.println("========================================");
        Phone phone3 = new com.luwei.util.schema.bridge.PhoneImpl(new I5Cpu(),new Memory128GB());
        phone3.xiaoMiPhone();
    }
}
