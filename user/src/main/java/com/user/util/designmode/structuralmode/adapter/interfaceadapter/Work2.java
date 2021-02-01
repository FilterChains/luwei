package com.user.util.designmode.structuralmode.adapter.interfaceadapter;

import com.luwei.designmode.structuralmode.adapter.interfaceadapter.Target;

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
