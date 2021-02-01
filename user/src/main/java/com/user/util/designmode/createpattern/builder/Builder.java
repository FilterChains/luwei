package com.user.util.designmode.createpattern.builder;

import com.luwei.designmode.createpattern.builder.MailSender;
import com.luwei.designmode.createpattern.builder.SmsSender;

import java.util.ArrayList;
import java.util.List;

public class Builder {

    private List<Sender> list = new ArrayList<Sender>();

    public void produceMailSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new MailSender());
        }
    }

    public void produceSmsSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new SmsSender());
        }
    }

    public List<Sender> getList() {
        return list;
    }
}
