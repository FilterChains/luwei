package com.user.util.excelimoprtutil;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.excelutils
 * @author: luwei
 * @description:
 * @date: 2020/2/19 19:38
 * @alert: This document is private to luwei
 * @version: 1.8.00_6
 */
class ExcelCode {
    /**
     * 总行数
     */
    static int totalRows;

    /**
     * 总列数
     */
    static int totalCells;

    /**
     * 表头行数
     */
    static int titleRows;

    /**
     * 排除表头集合
     */
    static List<String> excludeTitle;

    /**
     * 用于存储方法名 ->表头列数即为需要的set方法个数
     */
    static Map<String, String> methodNames;
    /**
     * 用于存储属性类型 ->字段数据类型;key->字段,value->字段类型
     */
    static Map<String, Class<?>> fieldTypes;

    /**
     * 验证实体类
     */
    static Map<String, Field> validateName;

    /**
     * 实体类名称
     */
    static Map<String, ExcelTitle> titleName;

    /**
     * excel read the way(excel读取方式)
     */
    static Boolean EXCEL_READ_WAY;

    /**
     * excel check tableTitle(excel检查表头)
     */
    static Boolean EXCEL_CHECK_TITLE;
    /**
     * 读取数据集合的初始长度
     */
    static int INITIAL_CAPACITY;

    /**
     * 方法set
     */
    static final String METHOD_SET = "set";

    /**
     * 序号排除
     */
    static final String EXCEL_SERIAL_NUMBER = "序号";

    /**
     * <p>@description : 初始化本类信息 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/5 14:36 </p>
     **/
    static void init() {
        totalRows = 0;
        totalCells = 0;
        titleRows = 1;
        INITIAL_CAPACITY = 2000;
        EXCEL_CHECK_TITLE = false;
        EXCEL_READ_WAY = false;
        excludeTitle = new ArrayList<>();
        titleName = new HashMap<>(16);
        methodNames = new HashMap<>(32);
        fieldTypes = new HashMap<>(16);
        validateName = new HashMap<>(16);
    }

    /**
     * <p>@description : 清空所有对象 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2021/2/9 10:12 </p>
     **/
    static void destroyColl() {
        titleName.clear();
        methodNames.clear();
        fieldTypes.clear();
        validateName.clear();
        excludeTitle.clear();
    }


    /**
     * <p>@description : 字符串替换英文,可拓展 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/2 9:09 </p>
     *
     * @param data ->数据源
     * @return {@link  String }
     **/
    static String stringReplace(String data) {
        String result = "";
        if (null != data) {
            result = data.replaceAll("\\s+", "")
                    .replace("（", "(")
                    .replace("）", ")");
        }
        return result;
    }
}
