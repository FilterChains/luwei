package com.user.util.schema.bridge;

public class Memory128GB implements Memory {

    @Override
    public void memorySize() {
        System.out.println("内存: 128GB");
    }

    @Override
    public void memoryName() {
        System.out.println("内存名称: 金士顿");
    }
}
