package com.user.util.schema.decorate;

/**
 * <p>@description : 具体装饰：实现抽象装饰者角色，负责对具体构件添加额外功能。 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/23 9:53 </p>
 **/
public class XiaoNianBuyFruits extends BuyFruitsDecorate {

    public XiaoNianBuyFruits(BaseDecorate baseDecorate) {
        super(baseDecorate);
    }

    @Override
    public void buyFruits(){
        System.out.println("HanMeiMei正在购买水果:");
        super.productName();
        super.price();
    }
}
