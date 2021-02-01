package com.user.util.schema.decorate;

/**
 * <p>@description : 抽象装饰者：持有对具体构件角色的引用并定义与抽象构件角色一致的接口
 * (作用就是组装基类装饰和具体装饰)其目的达到：装饰的拓展和延伸 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/23 9:47 </p>
 *
 **/
public class BuyFruitsDecorate extends BaseDecorate {

    private final BaseDecorate baseDecorate;

    public BuyFruitsDecorate(BaseDecorate baseDecorate) {
        this.baseDecorate = baseDecorate;
    }

    @Override
    protected void productName() {
        baseDecorate.productName();
    }

    @Override
    protected void price() {
        baseDecorate.price();
    }

    void buyFruits(){};
}
