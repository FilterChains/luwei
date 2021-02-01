package com.user.util.schema.practice;

import java.util.Observable;
/**
 * <p>@description : 被观察者 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2021/1/11 10:27 </p>
 **/
public class ProductService extends Observable {

    public void obServiceFunction(Person person){
        // 通知观察者
        super.setChanged();
        // 执行update -> function
        super.notifyObservers(person);
    }
}
