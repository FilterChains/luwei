package com.luwei.excelutils;

import lombok.Data;

import java.io.Serializable;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.excelutils
 * @auther: luwei
 * @description: validate excel title
 * @date: 2020/2/19 19:38
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
class ExcelTitle implements Serializable {
    /**
     * excel title name
     */
     String titleName;

    /**
     * excel title index
     */
     int titleIndex;
}
