package com.luwei.designmode.createpattern.abstractfactory;

public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
