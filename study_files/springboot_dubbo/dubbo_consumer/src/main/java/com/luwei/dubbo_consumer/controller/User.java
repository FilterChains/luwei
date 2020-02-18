package com.luwei.dubbo_consumer.controller;

import com.luwei.excelutils.ExcelTitleName;
import lombok.Data;

import java.io.Serializable;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.dubbo_consumer.controller
 * @auther: luwei
 * @description:
 * @date: 2020/2/18 17:06
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
public class User implements Serializable {

    @ExcelTitleName("名称")
    private String name;
    @ExcelTitleName("地址")
    private String address;
    @ExcelTitleName("电话")
    private String phoneNumber;

}
