package com.user.util.schema.temp;
/**
 * <p>@description : 模板模式子类B </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/22 11:02 </p>
 **/
public class ChildBImpl extends SimpleTemp {
    @Override
    protected String implFunction(String execute) {
        return "子类【ChildBImpl】的方法".concat(execute);
    }
}
