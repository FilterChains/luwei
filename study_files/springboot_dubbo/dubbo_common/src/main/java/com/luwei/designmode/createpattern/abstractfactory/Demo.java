package com.luwei.designmode.createpattern.abstractfactory;


public class Demo {
    public static void main(String[] args) {
        //测试抽象工厂
        new SendMailFactory().produce().Send();
        new SendSmsFactory().produce().Send();

    }
}
