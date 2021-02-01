package com.user.util.schema.temp;


/**
 * <p>@description : 测试模板模式 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/22 11:02 </p>
 *
 **/
public class TestSimpleTemp {

    public static void main(String[] args) throws InterruptedException {
        SimpleTemp childA = new ChildAImpl();
        System.out.println(childA.baseFunction("ChildAImpl_".concat(String.valueOf(System.currentTimeMillis()))));
        Thread.sleep(1000L);
        SimpleTemp childB = new ChildBImpl();
        System.out.println(childB.baseFunction("ChildBImpl_".concat(String.valueOf(System.currentTimeMillis()))));
    }
}
