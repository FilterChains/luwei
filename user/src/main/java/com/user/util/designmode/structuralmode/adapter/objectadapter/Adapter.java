package com.user.util.designmode.structuralmode.adapter.objectadapter;

import com.luwei.designmode.structuralmode.adapter.objectadapter.Targetable;

public class Adapter implements Targetable {

    private Source source;

    public Adapter(Source source) {
        super();
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method !");
    }
}
