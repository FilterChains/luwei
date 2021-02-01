package com.user.util.designmode.structuralmode.decorator;

import com.luwei.designmode.structuralmode.decorator.SourceAble;

public class Source implements SourceAble {

    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
