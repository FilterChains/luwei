package com.user.util.designmode.createpattern.singleton;

/**
 * <p>@description : 维护单列 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/3/21 15:39 </p>
 */
public class SingletonFactory {

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return SingletonFactory.instance;
    }
}
