package com.luwei.excelutils;

import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName： EasyExcelUtil
 * @packageName: com.excel.util
 * @auther: luwei
 * @description:
 * @date: 2019/10/20 11:07
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
class ExcelCode{
    /**
     * 构造方法
     */
    ExcelCode() {}

    /**
     * 总行数
     */
    static int totalRows =0;

    /**
     * 总列数 -> 最大支持127列
     */
    static short totalCells = 0;

    /**
     * 表头行数
     */
    static short titleRows =1;

    /**
     * 错误信息
     */
    static String errorInfo;

    /**
     * 用于存储方法名 ->表头列数即为需要的set方法个数
     */
    static List<String> methodNames = new ArrayList<>();
    /**
     * 用于存储属性类型 ->字段数据类型
     */
    static List<String> fieldTypes = new ArrayList<>();


    /**@Title:  validateExcelTitle
     * @Description: 验证excel中的表头
     * @Param:    参数
     * @Return:    返回类型
     * @Throws:
     * @Date: 2019/10/20 16:14
     */
    static Map<String, ExcelTitle> validateExcelTitle(Class objectClass){
        Map<String, ExcelTitle> hashMap = new HashMap<>();
        // 获得该类的所有属性
        Field[] fields = objectClass.getDeclaredFields();
        for (Field field : fields) {
            // 获得字段注解
            ExcelTitleName excelTitleName = field.getAnnotation(ExcelTitleName.class);
            if (!org.springframework.util.ObjectUtils.isEmpty(excelTitleName)) {
                ExcelTitle excelTitle = new ExcelTitle();
                excelTitle.setTitleName(excelTitleName.value());
                excelTitle.setTitleIndex(excelTitleName.index());
                hashMap.put(field.getName(), excelTitle);
            }
        }
        return hashMap;
    }

    /**@Title: getNewFile
     * @Description: 将要上传的excel文件写入本地准备读入
     * @Param:    fileName
     * @Return:    File
     * @Date: 2019/10/20 18:09
     */
    static File getNewFile(String fileName){
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        File fil = new File("D:"+File.separator+"upLoadFile");
        if (!fil.exists()) {
            fil.mkdirs();
        }
       return new File("D:"+File.separator+"upLoadFile" + System.currentTimeMillis() + suffix);
    }

    /**@Title:  getCellValue
     * @Description: 匹配内容的数据类型
     * @Param:  Cell
     * @Return:  String
     * @Throws: 无
     * @Date: 2019/10/20 16:01
     */
    static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        // 把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }

        // 去掉换行键/垂直制表符/换页键/回车键
        int enterStart = 10;
        int enterEnd = 13;
        for (int i = enterStart; i <= enterEnd; i++) {
            cellValue = cellValue.replaceAll(String.valueOf((char) i), "");
        }
        return cellValue;
    }

    /**@Title: validateExcel
     * @Description: 验证excel文件
     * @Param:  filePath 文件完整路径
     * @Return:  返回 true 表示文件没有问题
     * @Throws:  无
     * @Date: 2019/10/20 11:15
     */
    static boolean validateExcel(String filePath) throws ExcelException {
        /*检查文件名是否为空或者是否是Excel格式的文件*/
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            throw new ExcelException("文件不是excel格式");
        }
        /*检查文件是否存在*/
        File file = new File(filePath);
        if (!file.exists()) {
            throw new ExcelException("文件不存在");
        }
        return true;
    }

    /**@Title:  isExcel2003
     * @Description: 是否是2003的excel，返回true是2003
     * @Param:  filePath 文件完整路径
     * @Return:  boolean true代表是2003
     * @Throws: 无
     * @Date: 2019/10/20 11:17
     */
    static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**@Title:  isExcel2007
     * @Description: 是否是2007的excel，返回true是2007
     * @Param:  filePath 文件完整路径
     * @Return: boolean true代表是2007
     * @Throws: 无
     * @Date: 2019/10/20 11:17
     */
    static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}
