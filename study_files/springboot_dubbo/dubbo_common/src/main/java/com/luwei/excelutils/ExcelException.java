package com.luwei.excelutils;

import java.io.Serializable;

/**
 * @projectName： EasyExcelUtil
 * @packageName: com.excel.util
 * @auther: luwei
 * @description: class excel exception
 * @date: 2019/10/20 11:20
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class ExcelException extends Exception implements Serializable {

    ExcelException(String message) {
        super(message);
    }
}
