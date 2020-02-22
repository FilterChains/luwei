package com.luwei.excelutils;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.excelutils
 * @auther: luwei
 * @description: read excelSingleTitle common function(读取excel单表头公共方法)->表转实体类
 * @date: 2020/2/19 19:38
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class ExcelUtil {


    /**
     * @Title: readSingleTitleExcel
     * @Description: 读取信息并保存至相应的类,全部默认为String方式获取,注:字段对应数据类型必须为String。
     * @Param: [file, objectClass]  参数
     * @Param: file ->上传文件对象
     * @Param: objectClass ->获取实体类Class
     * @Return: java.util.List<?>   返回类型
     * @Throws: ExcelException 注：getMessage()为所有操作异常信息,e.getCause()为验证异常信息
     * @Date: 2020/2/19 20:39
     */
    public static List<?> readSingleTitleExcel(MultipartFile file, Class<?> objectClass) throws ExcelException {
        return ExcelFunction.readExcel(file,objectClass,false);
    }

    /**
     * @Title: readSingleTitleExcelCheckReadWay
     * @Description: 读取信息并保存至相应的类 ,根据对应信息读取表中信息功能占不完善
     * @Description: ->无法读取表头(所有表头为字符串,字段不一定都是字符串原因) 推荐使用readSingleTitleExcel()方法
     * @Param: [file, objectClass,operation]  参数
     * @Param: file ->上传文件对象
     * @Param: objectClass ->获取实体类Class
     * @Param: operation ->获取读取方式,operation ->true:将按照实体类字段对应数据类型获取表中的值
     * @Param: operation ->false:全部默认为String方式获取,注:字段对应数据类型必须为String。(默认：false)
     * @Return: java.util.List<?>   返回类型
     * @Throws: ExcelException 注：getMessage()为所有操作异常信息,e.getCause()为验证异常信息
     * @Date: 2020/2/19 20:39
     */
    public static List<?> readSingleTitleExcelCheckReadWay(MultipartFile file, Class<?> objectClass, boolean operation) throws ExcelException {
        return ExcelFunction.readExcel(file, objectClass, operation);
    }

}
