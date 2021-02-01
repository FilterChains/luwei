package com.user.util.designmode.structuralmode.adapter.interfaceadapter;

public interface Target {

    // 实现类必须实现该方法
    void study();

    // 取缔以前创建抽象类在继承的方法,实现类可以不用实现该方法
    default void eat() {
    }

}
