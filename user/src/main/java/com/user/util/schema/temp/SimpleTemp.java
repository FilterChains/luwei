package com.user.util.schema.temp;
/**
 * <p>@description : 模板模式(基类) </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/22 10:48 </p>
 *
 **/
public abstract class SimpleTemp {

    /**
     * <p>@description : 模板模式基本方法，不能被重写只能被调用 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/22 10:49 </p>
     *
      * @param resource ->模拟数据源
     * @return String ->返回执行结果
     **/
    public final String baseFunction(String resource){
        // 可拓展
        return coreFunction(implFunction(resource));
    }

    /**
     * <p>@description : 子类实现执行方法体，并返回执行结果(servlet->service()) </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/22 10:52 </p>
     *
      * @param execute ->子类需要组装的数据
     * @return String ->返回子类执行完后的数据
     **/
    protected abstract String implFunction(String execute);
    
    /**
     * <p>@description : 核心方法，不能被重写 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/22 10:54 </p>
     *
      * @param coreData ->需要执行的核心数据
     * @return String ->返回需要的数据
     **/
    private String coreFunction(String coreData){
        return "核心方法正在执行:".concat(coreData);
    }
}
