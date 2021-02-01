package com.luwei.util.schema.bridge;

import com.user.util.schema.bridge.Cpu;
import com.user.util.schema.bridge.Memory;
import com.user.util.schema.bridge.Phone;

public class PhoneImpl extends Phone {

    private final Cpu cpu;

    private final Memory memory;

    public PhoneImpl(Cpu cpu, Memory memory) {
        this.cpu = cpu;
        this.memory = memory;
    }

    @Override
    protected void iPhone() {
        System.out.println("苹果手机");
        System.out.println("配置参数:");
        cpu.cpuType();
        cpu.cpuManufacturers();
        memory.memoryName();
        memory.memorySize();
    }

    @Override
    protected void huaWeiPhone() {
        System.out.println("华为手机");
        System.out.println("配置参数:");
        cpu.cpuType();
        cpu.cpuManufacturers();
        memory.memoryName();
        memory.memorySize();
    }

    @Override
    protected void xiaoMiPhone() {
        System.out.println("小米手机");
        System.out.println("配置参数:");
        cpu.cpuType();
        cpu.cpuManufacturers();
        memory.memoryName();
        memory.memorySize();
    }
}
