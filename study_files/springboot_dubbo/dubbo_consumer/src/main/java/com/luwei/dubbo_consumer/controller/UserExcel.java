package com.luwei.dubbo_consumer.controller;

import com.luwei.excelutils.ExcelTitleName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class UserExcel implements Serializable {

    /*@ExcelTitleName("名称")
    private int name;*/

    /*@ExcelTitleName("电话")
    private String phoneNumber;*/

    @ExcelTitleName(index = 2,value = "地址")
    private String address;

   /* @ExcelTitleName("判断")
    private boolean boo;*/

    @ExcelTitleName("是否")
    private Boolean ba;

    @ExcelTitleName(index = 4,value = "金钱")
    private BigDecimal moneys;
    /*@ExcelTitleName("byte1")
    private byte numByte;
    @ExcelTitleName("short1")
    private short numShort;
    @ExcelTitleName("int1")
    private int numInt;
    @ExcelTitleName("long1")
    private long numLong;
    @ExcelTitleName("float1")
    private float numFloat;
    @ExcelTitleName("double1")
    private double numDouble;*/

    /*@ExcelTitleName("Byte2")
    private Byte berByte;
    @ExcelTitleName("Short2")
    private Short berShort;
    @ExcelTitleName("Integer2")
    private Integer berInteger;
    @ExcelTitleName("Long2")
    private Long berLong;
    @ExcelTitleName("Float2")
    private Float berFloat;*/
    @ExcelTitleName("Double2")
    private Double berDouble;

    @ExcelTitleName(index = 5,value = "时间")
    private Date times;

}
