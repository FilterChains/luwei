package com.user.util.schema.decorate;


/**
 * <p>@description : 测试装饰者模式 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/23 10:13 </p>
 *
 **/
public class TestDecorate {
    public static void main(String[] args) {
        BaseDecorate appleDecorate = new AppleDecorate();
        BaseDecorate bananaDecorate = new BananaDecorate();

        BuyFruitsDecorate decorate = new ZhangSanBuyFruits(appleDecorate);
        decorate.buyFruits();
        decorate = new XiaoNianBuyFruits(bananaDecorate);
        decorate.buyFruits();
    }
}
