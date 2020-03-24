package com.luwei.designmode.structuralmode.adapter.objectadapter;

public class AdapterTest {
    public static void main(String[] args) {
        Source source = new Source();
        Targetable adapter = new Adapter(source);
        adapter.method1();
        adapter.method2();
    }
}
