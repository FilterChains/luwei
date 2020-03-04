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
     * @Description: 读取信息并保存至相应的类, 全部默认为String方式获取, 注:字段对应数据类型必须为String。
     * @Description: 不严格检查表格title和实体类注解标记字段是否一致(实体类字段可缺省), 只要表头与实体类字段有一个对应就能读取, 对应的值
     * @Param: [file, objectClass]  参数
     * @Param: file ->上传文件对象
     * @Param: objectClass ->获取实体类Class
     * @Return: java.util.List<?>   返回类型
     * @Throws: ExcelException 注：getMessage()为所有操作异常信息,e.getCause()为验证异常信息
     * @Date: 2020/2/19 20:39
     */
    public static<T> List<T>  readSingleTitleExcel(MultipartFile file, Class<T> objectClass) throws ExcelException {
        return ExcelFunction.readExcel(file, objectClass, false, false);
    }

    /**
     * @Title: readSingleTitleExcelCheckReadWay
     * @Description: 读取信息并保存至相应的类 ,根据对应信息读取表中信息功能暂不完善
     * @Description： 不严格检查表格title和实体类注解标记字段是否一致(实体类字段可缺省), 只要表头与实体类字段有一个对应就能读取, 对应的值
     * @Description: ->无法读取表头(所有表头为字符串,字段不一定都是字符串原因) 推荐使用readSingleTitleExcel()方法
     * @Param: [file, objectClass]  参数
     * @Param: file ->上传文件对象
     * @Param: objectClass ->获取实体类Class
     * @Return: java.util.List<?>   返回类型
     * @Throws: ExcelException 注：getMessage()为所有操作异常信息,e.getCause()为验证异常信息
     * @Date: 2020/2/19 20:39
     */
    public static<T> List<T>  readSingleTitleExcelCheckReadWay(MultipartFile file, Class<T> objectClass) throws ExcelException {
        return ExcelFunction.readExcel(file, objectClass, true, false);
    }

    /**
     * @Title: readSingleTitleExcelCheckTableTitle
     * @Description: 读取信息并保存至相应的类, 全部默认为String方式获取, 注:字段对应数据类型必须为String。
     * @Description: 严格检查表中title, 与实体类注解标记的字段是否一致(实体类字段不可缺省,序号自动排除)。
     * @Description: 注:若实体类注解标记表头下标,则字段顺序可以改变, 但标记字段与表头必须匹配
     * @Param: [file, objectClass]   参数
     * @Return: java.util.List<?>   返回类型
     * @Throws: ExcelException 注：getMessage()为所有操作异常信息,e.getCause()为验证异常信息
     * @Date: 2020/2/19 20:42
     */
    public static <T> List<T> readSingleTitleExcelCheckTableTitle(MultipartFile file, Class<T> objectClass) throws ExcelException {
        return ExcelFunction.readExcel(file, objectClass, false, true);
    }


}
