package com.luwei.supermarket.util;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.util
 * @auther: luwei
 * @description:
 * @date: 2020/5/13 21:48
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class Notify<T> {
    private Integer code;
    private String msg;
    private T data;

    public Notify() {
        super();
    }

    public enum Code {
        //这里是可以自己定义的，方便与前端交互即可
        UNKNOWN_ERROR(-1, "未知错误"),
        SUCCESS(200, "成功"),
        ERROR(400, "失败"),
        EXCEPTION(500, "消息异常"),
        NOT_RESPONSE(-2, "无数据响应"),
        DATA_IS_NULL(0, "数据为空");

        @EnumValue
        private int index;
        private String value;

        Code(int index, String value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public String getValue() {
            return value;
        }
    }

    public Notify(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Notify(Code code) {
        this.code = code.index;
        this.msg = code.value;
    }

    public Notify(Code code, T data) {
        this.msg = code.value;
        this.code = code.index;
        this.data = data;
    }

    public Notify(String msg) {
        this.msg = msg;
    }

    public Notify(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public Notify(T data) {
        this.data = data;
    }

    public Notify(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}