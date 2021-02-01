package com.user.util.schema.decorate;
/**
 * <p>@description : 具体组件：将要被附加功能的类，实现抽象构件角色接口 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/23 9:42 </p>
 **/
public class AppleDecorate extends BaseDecorate {

    @Override
    protected void productName() {
        System.out.println("苹果->APPLE");
    }

    @Override
    protected void price() {
        System.out.println("价格:998.89元");
    }
}
