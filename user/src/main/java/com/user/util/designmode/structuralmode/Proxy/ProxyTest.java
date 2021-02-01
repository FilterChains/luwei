package com.user.util.designmode.structuralmode.Proxy;

import com.luwei.designmode.structuralmode.Proxy.DynamicProxy;
import com.luwei.designmode.structuralmode.Proxy.SourceTable;

public class ProxyTest {
    public static void main(String[] args) {
        SourceTable proxy = new Proxy(new Source());
        proxy.method();

        DynamicProxy dynamicProxy = new DynamicProxy(new SourceTable() {
            @Override
            public void method() {
                System.out.println("JDK动态代理");
            }
        });
    }
}
