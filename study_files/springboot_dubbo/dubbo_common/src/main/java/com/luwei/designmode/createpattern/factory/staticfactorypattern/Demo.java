package com.luwei.designmode.createpattern.factory.staticfactorypattern;

public class Demo {
    public static void main(String[] args) {
        //测试静态工厂
        SendFactory.produceMail().Send();
        SendFactory.produceSms().Send();
    }
}
