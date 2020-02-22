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
 * @date: 2020/2/21 09:57
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
public class GroupTest implements Serializable {
    @ExcelTitleName("分组名称")
    private String groupName;
    @ExcelTitleName("描述")
    private String ramk;
    @ExcelTitleName("药店编码")
    private String taxCode;
    @ExcelTitleName(value = "药店名称")
    private String drugstoreName;
    @ExcelTitleName("时间")
    private String times ;

    private String errorMsg;

    private String errorIndex;
}
