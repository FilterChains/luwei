package com.luwei.designmode.createpattern.abstractfactory;

public class MailSender implements Sender {

    @Override
    public void Send() {
        System.out.println("this is MailSender !");
    }
}
