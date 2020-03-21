package com.luwei.designmode.createpattern.factory.multiplefactorypatterns;

public class MailSender implements Sender {

    @Override
    public void Send() {
        System.out.println("this is MailSender !");
    }
}
