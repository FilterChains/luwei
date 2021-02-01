package com.user.util.designmode.createpattern.factory.multiplefactorypatterns;

import com.luwei.designmode.createpattern.factory.generalfactorymode.MailSender;
import com.luwei.designmode.createpattern.factory.generalfactorymode.Sender;
import com.luwei.designmode.createpattern.factory.generalfactorymode.SmsSender;

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
