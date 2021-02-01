package com.user.util.schema.bridge;

public class I5Cpu implements Cpu {

    @Override
    public void cpuType() {
        System.out.println("CPU-类型: i5");
    }

    @Override
    public void cpuManufacturers() {
        System.out.println("CPU-制造商: 苹果公司");
    }
}
