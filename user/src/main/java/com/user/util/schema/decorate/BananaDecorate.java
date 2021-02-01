package com.user.util.schema.decorate;
/**
 * <p>@description : 具体组件：将要被附加功能的类，实现抽象构件角色接口 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/23 9:44 </p>
 **/
public class BananaDecorate extends BaseDecorate {

    @Override
    protected void productName() {
        System.out.println("香蕉->Banana");
    }

    @Override
    protected void price() {
        System.out.println("价格:889.98元");
    }
}
