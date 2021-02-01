package com.user.util.schema.bridge;
/**
 * <p>@description : 桥接模式基础组件(手机) </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/24 15:25 </p>
 **/
public abstract class Phone {

    /**
     * 苹果手机
     */
    protected abstract void iPhone();

    /**
     * 华为手机
     */
    protected abstract void huaWeiPhone();

    /**
     * 小米手机
     */
    protected abstract void xiaoMiPhone();
}
