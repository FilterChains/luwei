package com.user.util.schema.observe;

import java.util.Observable;
import java.util.Observer;
/**
 * <p>@description : 观察者壹 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/24 11:03 </p>
 *
 **/
public class ObServeOne implements Observer {

    /**
     * 便于区分：观察者名称
     */
    private final String obServeName;

    public ObServeOne(String obServeName) {
        this.obServeName = obServeName;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof TheObServeEd){
            if("ONE".equals(arg)){
                TheObServeEd theObServeEd =(TheObServeEd)o;
                System.err.println(obServeName.concat("正在指定观察价格变更为：").concat(theObServeEd.getPrice().toPlainString()));

            }else{
                TheObServeEd theObServeEd =(TheObServeEd)o;
                System.out.println(obServeName.concat("正在观察价格变更为：").concat(theObServeEd.getPrice().toPlainString()));
            }
        }
    }
}
