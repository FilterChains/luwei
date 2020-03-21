package com.luwei.designmode.createpattern.factory.generalfactorymode;

public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is SmsSender !");
    }
}
