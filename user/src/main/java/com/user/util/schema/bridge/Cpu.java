package com.user.util.schema.bridge;
/**
 * <p>@description : 桥接模式CPU组件 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/24 15:30 </p>
 **/
public interface  Cpu {

    /**
     * CPU类型
     */
      void cpuType();

    /**
     * CPU生产厂家
     */
    void cpuManufacturers();
}
