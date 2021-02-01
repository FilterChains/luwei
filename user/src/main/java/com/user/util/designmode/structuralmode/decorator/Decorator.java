package com.user.util.designmode.structuralmode.decorator;


public class Decorator implements SourceAble {

    private Source source;

    Decorator(Source source) {
        this.source = source;
    }

    @Override
    public void method() {
        System.out.println("before decorator !");
        source.method();
        System.out.println("after decorator !");
    }
}
