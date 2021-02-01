package com.user.util.schema.practice;

import com.user.util.schema.practice.Person;

import java.util.Observable;
import java.util.Observer;
/**
 * <p>@description : 观察者 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2021/1/11 10:26 </p>
 *
 **/
public class ProductServiceImpl implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Person person = (Person) arg;
        System.out.println("观察对象："+person);
    }

}
