package com.luwei.designmode.createpattern.builder;

public class MailSender implements Sender {

    @Override
    public void Send() {
        System.out.println("this is MailSender !");
    }
}
