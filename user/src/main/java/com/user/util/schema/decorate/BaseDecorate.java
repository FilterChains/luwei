package com.user.util.schema.decorate;
/**
 * <p>@description : 装饰者基类(最顶层)抽象组件:定义一个抽象接口，来规范准备附加功能的类 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/23 9:37 </p>
 *
 **/
public abstract class BaseDecorate {

    /**
     * <p>@description : 装饰者模式基类方法 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/23 9:39 </p>
     **/
    protected abstract void productName();

    /**
     * <p>@description : 装饰者模式基类方法 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/23 9:39 </p>
     **/
    protected abstract void price();
}
