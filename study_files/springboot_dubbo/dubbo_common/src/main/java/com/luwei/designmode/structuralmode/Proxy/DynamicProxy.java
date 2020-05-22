package com.luwei.designmode.structuralmode.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//JDK动态代理
public class DynamicProxy implements InvocationHandler {

    private SourceTable sourceTable;

    //构造方法
    public DynamicProxy(SourceTable sourceTable) {
        this.sourceTable = sourceTable;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("开始JDK动态代理");
        method.invoke(sourceTable, args);
        System.out.println("结束JDK动态代理");
        return null;
    }


}
