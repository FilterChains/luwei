package com.luwei.excelutils;

import java.io.Serializable;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.excelutils
 * @auther: luwei
 * @description: class excel exception
 * @date: 2020/2/19 19:38
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class ExcelException extends RuntimeException implements Serializable {

    ExcelException(String message) {
        super(message);
    }

    ExcelException(String message,ExcelType.ExcelError excelError) {
        super(message, new Throwable(excelError.getError()));
    }

    ExcelException(ExcelType.ExcelError excelError) {
        super(new Throwable(excelError.getError()));
    }
}
