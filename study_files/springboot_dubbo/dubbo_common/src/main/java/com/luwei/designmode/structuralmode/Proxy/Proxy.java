package com.luwei.designmode.structuralmode.Proxy;

public class Proxy implements SourceTable {

    private Source source;

    public Proxy(Source source) {
        this.source = source;
    }

    @Override
    public void method() {
        before();
        source.method();
        atfer();
    }

    private void atfer() {
        System.out.println("after proxy !");
    }

    private void before() {
        System.out.println("before proxy !");
    }

}
