package com.user.util.designmode.createpattern.factory.multiplefactorypatterns;


public class SendFactory {

    // 工厂方法
    public Sender produceMail() {
        return new MailSender();
    }

    // 工厂方法
    public Sender produceSms() {
        return new SmsSender();
    }
}
