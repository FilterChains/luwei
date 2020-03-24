package com.luwei.designmode.structuralmode.adapter.classadapter;

public class AdapterTest {
    public static void main(String[] args) {
        Targetable adapter = new Adapter();
        adapter.method1();
        adapter.method2();
    }
}
