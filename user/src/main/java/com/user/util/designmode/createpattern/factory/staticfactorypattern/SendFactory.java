package com.user.util.designmode.createpattern.factory.staticfactorypattern;


public class SendFactory {

    // 工厂方法
    public static Sender produceMail() {
        return new MailSender();
    }

    // 工厂方法
    public static Sender produceSms() {
        return new SmsSender();
    }
}
