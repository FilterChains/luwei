package com.user.util.excelimoprtutil;

import org.springframework.util.StringUtils;

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
 * @version: 1.8.00_66
 */
public class ExcelCode {
    /**
     * 总行数
     */
    static int totalRows = 0;

    /**
     * 总列数
     */
    static int totalCells = 0;

    /**
     * 表头行数
     */
    static int titleRows = 1;

    /**
     * 方法set
     */
    static final String METHOD_SET = "set";

    /**
     * 序号排除
     */
    static final String EXCEL_SERIAL_NUMBER = "序号";

    /**
     * 排除表头集合
     */
    static List<String> excludeTitle = new ArrayList<>();

    /**
     * 用于存储方法名 ->表头列数即为需要的set方法个数
     */
    static Map<String, String> methodNames = new HashMap<>(16);
    /**
     * 用于存储属性类型 ->字段数据类型;key->字段,value->字段类型
     */
    static Map<String, Class<?>> fieldTypes = new HashMap<>(16);

    /**
     * 验证实体类
     */
    static Map<String, Field> validateName = new HashMap<>(16);

    /**
     * 实体类名称
     */
    static Map<String, ExcelTitle> titleName = new HashMap<>(16);

    /**
     * excel read the way(excel读取方式)
     */
    static Boolean EXCEL_READ_WAY = false;

    /**
     * excel check tableTitle(excel检查表头)
     */
    static Boolean EXCEL_CHECK_TITLE = false;

    /**
     * @Title: stringReplace
     * @Description: 字符串替换英文
     * @Param: [data]   参数
     * @Return: String ->结果  返回类型
     * @Date: 2020/2/19 20:57
     */
    static String stringReplace(String data) {
        String result = "";
        if (!StringUtils.isEmpty(data)) {
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
    static void destroyColl() {
        titleName = null;
        methodNames = null;
        fieldTypes = null;
        validateName = null;
        excludeTitle = null;
    }
}
