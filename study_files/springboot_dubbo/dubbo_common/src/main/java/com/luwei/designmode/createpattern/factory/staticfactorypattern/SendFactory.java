package com.luwei.designmode.createpattern.factory.staticfactorypattern;

import com.luwei.designmode.createpattern.factory.generalfactorymode.MailSender;
import com.luwei.designmode.createpattern.factory.generalfactorymode.Sender;
import com.luwei.designmode.createpattern.factory.generalfactorymode.SmsSender;

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
