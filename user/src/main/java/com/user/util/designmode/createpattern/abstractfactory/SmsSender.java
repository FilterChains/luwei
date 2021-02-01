package com.user.util.designmode.createpattern.abstractfactory;

public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is SmsSender !");
    }
}
