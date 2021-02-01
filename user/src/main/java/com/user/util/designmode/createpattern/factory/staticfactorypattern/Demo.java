package com.user.util.designmode.createpattern.factory.staticfactorypattern;

import com.luwei.designmode.createpattern.factory.staticfactorypattern.SendFactory;

public class Demo {
    public static void main(String[] args) {
        //测试静态工厂
        SendFactory.produceMail().Send();
        SendFactory.produceSms().Send();
    }
}
