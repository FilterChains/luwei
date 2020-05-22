package com.luwei.designmode.createpattern.factory.multiplefactorypatterns;

public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is SmsSender !");
    }
}
