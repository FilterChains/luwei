package com.user.util.designmode.createpattern.factory.generalfactorymode;

import com.luwei.designmode.createpattern.factory.generalfactorymode.SendFactory;
import com.luwei.designmode.createpattern.factory.generalfactorymode.Sender;

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
