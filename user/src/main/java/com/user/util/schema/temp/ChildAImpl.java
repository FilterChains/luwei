package com.user.util.schema.temp;
/**
 * <p>@description : 模板模式子类A </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/22 11:02 </p>
 **/
public class ChildAImpl extends SimpleTemp {
    @Override
    protected String implFunction(String execute) {
        return "子类【ChildAImpl】的方法".concat(execute);
    }
}
