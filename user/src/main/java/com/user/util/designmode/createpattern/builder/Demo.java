package com.user.util.designmode.createpattern.builder;

import com.luwei.designmode.createpattern.builder.Builder;
import com.luwei.designmode.createpattern.builder.Sender;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        //测试建造者模式
        Builder builder = new Builder();
        builder.produceMailSender(10);
        builder.produceSmsSender(10);
        List<Sender> list = builder.getList();
        list.forEach(Sender::Send);

    }
}
