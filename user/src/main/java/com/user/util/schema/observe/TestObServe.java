package com.user.util.schema.observe;

import com.google.common.collect.Lists;

import java.math.BigDecimal;

/**
 * <p>@description : 测试观察者模式 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/24 11:11 </p>
 *
 **/
public class TestObServe {
    public static void main(String[] args) {
        // 创建一个被观察者，并给予被观察值初始值
        com.luwei.util.schema.observe.TheObServeEd obServeEd = new com.luwei.util.schema.observe.TheObServeEd(BigDecimal.valueOf(9999L));
        System.out.println("初始值:".concat(obServeEd.getPrice().toPlainString()));

        // 创建观察者
        ObServeOne serveOne = new ObServeOne("观察者壹");
        ObServeTwo serveTwo = new ObServeTwo("观察者贰");

        // 注册观察者
        obServeEd.registerObServe(Lists.newArrayList(serveOne,serveTwo));
        // obServeEd.addObserver(serveOne);
        // obServeEd.addObserver(serveTwo);

        System.out.println("=======================查看观察者观察结果=======================");
        // 修改被观察者对象的被观察值
        obServeEd.setPrice(BigDecimal.valueOf(998L));

        System.out.println("=======================查看指定观察者观察结果=======================");
        obServeEd.setPriceArg(BigDecimal.valueOf(142857L),"TWO");

        System.out.println("=======================查看移除观察者贰后观察结果=======================");
        // 移除观察者
        obServeEd.deleteObserver(serveTwo);
        obServeEd.setPrice(BigDecimal.valueOf(3.1415926));

    }
}
