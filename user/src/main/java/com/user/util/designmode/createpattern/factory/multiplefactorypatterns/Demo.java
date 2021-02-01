package com.user.util.designmode.createpattern.factory.multiplefactorypatterns;



public class Demo {
    public static void main(String[] args) {
        //测试多工厂
        SendFactory sendFactory = new SendFactory();
        sendFactory.produceMail().Send();
        sendFactory.produceSms().Send();
    }
}
