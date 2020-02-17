package com.luwei.excelutils;

import lombok.Data;

import java.io.Serializable;

/**
 * @projectName： EasyExcelUtil
 * @packageName: com.excel.util
 * @auther: luwei
 * @description: validate excel title
 * @date: 2019/10/20 16:41
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
class ExcelTitle implements Serializable {
    /**
     * excel title name
     */
    private String titleName;

    /**
     * excel title index
     */
    private int titleIndex;
}
