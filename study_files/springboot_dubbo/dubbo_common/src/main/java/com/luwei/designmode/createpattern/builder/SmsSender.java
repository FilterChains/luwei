package com.luwei.designmode.createpattern.builder;

public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is SmsSender !");
    }
}
