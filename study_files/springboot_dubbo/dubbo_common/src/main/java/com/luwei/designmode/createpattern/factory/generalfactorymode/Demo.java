package com.luwei.designmode.createpattern.factory.generalfactorymode;

public class Demo {
    public static void main(String[] args) {
        //测试普通工厂
        SendFactory sendFactory = new SendFactory();

        Sender sms = sendFactory.produce("sms");
        sms.Send();
        Sender mail = sendFactory.produce("mail");
        mail.Send();
    }
}
