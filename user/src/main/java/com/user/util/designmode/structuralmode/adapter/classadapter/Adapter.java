package com.user.util.designmode.structuralmode.adapter.classadapter;

import com.luwei.designmode.structuralmode.adapter.classadapter.Targetable;

public class Adapter extends Source implements Targetable {

    @Override
    public void method2() {
        System.out.println("this is the targetable method !");
    }
}
