package com.user.util.schema.observe;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

/**
 * <p>@description : 创建一个被观察者对象(本次观察金额是否变更) </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/24 10:47 </p>
 **/
public class TheObServeEd extends Observable {

    /**
     * 观察金额
     */
    private BigDecimal price;

    /**
     * 初始化被观察金额
     * @param price
     */
    public TheObServeEd(BigDecimal price) {
        this.price = price;
    }

    /**
     * <p>@description : 注册观察者 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/24 15:04 </p>
     *
      * @param coll
     **/
    public void registerObServe(Collection<Observer> coll){
        if(CollectionUtils.isEmpty(coll)){
            return;
        }
        coll.forEach(super::addObserver);
    }

    /**
     * 获取变更后的金额
     * @return
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置金额(即：金额即将发生变更)->无参通知，全部观察者
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
        /**
         * @Description: 向观察者通知被观察者已经变更
         */
        super.setChanged();
        /**
         * @Description: 只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干。
         * 注：这里需注意notifyObservers()和notifyObservers(Object arg)的区别
         *无参通知者表示通知所有实现Observer的观察者进行操作，而有参通知则可以当做是指定通知理解
         *即：有且只有参数相对应的观察者对象能操作被观察者的实例
         */
        super.notifyObservers();
    }

    /**
     * 设置金额(即：金额即将发生变更)->有参通知，指定观察者
     * @param price
     */
    public void setPriceArg(BigDecimal price,String arg){

        this.price = price;
        /**
         * @Description: 向观察者通知被观察者已经变更
         */
        super.setChanged();
        /**
         * @Description: 只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干。
         * 注：这里需注意notifyObservers()和notifyObservers(Object arg)的区别
         *无参通知者表示通知所有实现Observer的观察者进行操作，而有参通知则可以当做是指定通知理解
         *即：有且只有参数相对应的观察者对象能操作被观察者的实例
         */
        super.notifyObservers(arg);
    }

}
