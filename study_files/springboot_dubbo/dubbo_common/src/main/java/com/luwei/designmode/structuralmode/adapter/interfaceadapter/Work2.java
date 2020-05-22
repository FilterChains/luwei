package com.luwei.designmode.structuralmode.adapter.interfaceadapter;

public class Work2 implements Target {

    @Override
    public void study() {
        System.out.println("Study");
    }

    @Override
    public void eat() {
        System.out.println("Eat");
    }
}
