package com.user.util.designmode.structuralmode.Proxy;

import com.luwei.designmode.structuralmode.Proxy.SourceTable;

public class Source implements SourceTable {
    @Override
    public void method() {
        System.out.println("the original method!");
    }


}
