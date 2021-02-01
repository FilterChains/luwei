package com.user.util.designmode.createpattern.factory.multiplefactorypatterns;


import com.luwei.designmode.createpattern.factory.multiplefactorypatterns.SendFactory;

public class Demo {
    public static void main(String[] args) {
        //测试多工厂
        SendFactory sendFactory = new SendFactory();
        sendFactory.produceMail().Send();
        sendFactory.produceSms().Send();
    }
}
