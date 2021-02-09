package com.user.util.excelimoprtutil;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.excelutils
 * @author: luwei
 * @description: 枚举类 excel error(excel报错)
 * @date: 2020/2/19 19:38
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public enum ExcelErrorType {
    /**
     * 上传文件格式与模板不匹配
     */
    EXCEL_VERSION(1, "上传文件格式与模板不匹配"),
    /**
     * 上传文件为空,请仔细检查
     */
    TITLE_IS_NULL(2, "上传文件为空,请仔细检查"),
    /**
     * 表格内容为空
     */
    CONTENT_IS_NULL(3, "表格内容为空"),
    /**
     * 表头已被篡改,请仔细检查后重新上传
     */
    TITLE_THE_MANIPULATION(4, "表头已被篡改,请仔细检查后重新上传");

    private final int index;
    private final String error;

    ExcelErrorType(int index, String error) {
        this.index = index;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public int getIndex() {
        return index;
    }
}
