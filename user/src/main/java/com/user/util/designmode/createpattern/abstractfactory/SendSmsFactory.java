package com.user.util.designmode.createpattern.abstractfactory;

public class SendSmsFactory implements Provider {

    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
