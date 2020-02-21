package com.luwei.excelutils;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.excelutils
 * @auther: luwei
 * @description: 枚举类
 * @date: 2020/2/19 19:38
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class ExcelType {

    /**
     * excel version(excel版本)
     */
    public enum ExcelVersion{
        EXCEL_XLS(1, "^.+\\.(?i)(xls)$"),
        EXCEL_XLSX(2, "^.+\\.(?i)(xlsx)$");

        int index;
        String value;

        ExcelVersion(int index, String value) {
            this.index = index;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * excel error(excel报错)
     */
    public enum ExcelError{
        EXCEL_VERSION(1,"上传文件格式与模板不匹配"),
        TITLE_IS_NULL(2,"上传文件为空,请仔细检查"),
        CONTENT_IS_NULL(3,"表格内容为空"),
        TITLE_THE_MANIPULATION(4,"表头已被篡改,请仔细检查后重新上传");
        private int index;
        private String error;

        ExcelError(int index, String error) {
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
}
