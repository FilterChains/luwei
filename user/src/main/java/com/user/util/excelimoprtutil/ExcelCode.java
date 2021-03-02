package com.user.util.excelimoprtutil;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.excelutils
 * @author: luwei
 * @description:
 * @date: 2020/2/19 19:38
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class ExcelCode {
    /**
     * 总行数
     */
    public static int totalRows = 0;

    /**
     * 总列数
     */
    public static int totalCells = 0;

    /**
     * 表头行数
     */
    public static int titleRows = 1;

    /**
     * 方法set
     */
    public static final String METHOD_SET = "set";

    /**
     * 序号排除
     */
    public static final String EXCEL_SERIAL_NUMBER = "序号";

    /**
     * 排除表头集合
     */
    public static List<String> excludeTitle = new ArrayList<>();

    /**
     * 用于存储方法名 ->表头列数即为需要的set方法个数
     */
    public static Map<String, String> methodNames = new ConcurrentHashMap<>(16);
    /**
     * 用于存储属性类型 ->字段数据类型;key->字段,value->字段类型
     */
    public static Map<String, Class<?>> fieldTypes = new ConcurrentHashMap<>(16);

    /**
     * 验证实体类
     */
    public static Map<String, Field> validateName = new ConcurrentHashMap<>(16);

    /**
     * 实体类名称
     */
    public static Map<String, ExcelTitle> titleName = new ConcurrentHashMap<>(16);

    /**
     * excel read the way(excel读取方式)
     */
    public static volatile Boolean EXCEL_READ_WAY = false;

    /**
     * excel check tableTitle(excel检查表头)
     */
    public static volatile Boolean EXCEL_CHECK_TITLE = false;

    /**
     * <p>@description : 字符串替换英文 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/3/2 9:09 </p>
     *
     * @param data ->数据源
     * @return {@link  String }
     **/
    public static String stringReplace(String data) {
        String result = "";
        if (null != data) {
            result = data.replaceAll("\\s+", "")
                    .replace("（", "(")
                    .replace("）", ")");
        }
        return result;
    }

    /**
     * <p>@description : 清空所有对象 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2021/2/9 10:12 </p>
     **/
    public static void destroyColl() {
        titleName = null;
        methodNames = null;
        fieldTypes = null;
        validateName = null;
        excludeTitle = null;
    }
}
