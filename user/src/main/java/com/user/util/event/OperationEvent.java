package com.user.util.event;

import java.util.EventObject;

/**
 * <p>@description : 事件。一般继承自java.util.EventObject类，封装了事件源对象及跟事件相关的信息。
 * 事件类，用于封装事件源和一些事件相关的参数 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/9/27 10:17 </p>
 **/
public class OperationEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    /**
     * 事件源
     */
    private final Object source;

    public OperationEvent(Object source) {
        super(source);
        this.source = source;
    }

    @Override
    public Object getSource() {
        return source;
    }
}
