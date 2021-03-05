package com.user.util.excelimoprtutil;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.excelutils
 * @author: luwei
 * @description: excel function list
 * @date: 2020/2/19 19:38
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class ExcelFunction {

    /**
     * @Title: readExcel
     * @Description: 读取信息并保存至相应的类
     * @Param: [file, objectClass,operation,checkTitle]  参数
     * @Param: file ->上传文件对象
     * @Param: objectClass ->获取实体类Class
     * @Param: operation ->获取方式操作,operation ->true:将按照实体类字段对应数据类型获取表中的值
     * @Param: operation ->false:全部默认为String方式获取,注:字段对应数据类型必须为String。(默认：false)
     * @Param: checkTitle:是否检查表头,checkTitle ->true 检查,checkTitle ->false 不检查
     * @Return: java.util.List<?>   返回类型
     * @Throws: ExcelException
     * @Date: 2020/2/19 20:39
     */
    public static <T> List<T> readExcel(MultipartFile file, Class<T> objectClass, boolean operation, boolean checkTitle) throws ExcelException {
        List<T> resultDate;
        try {
            ExcelCode.init();
            //读取数据方式
            ExcelCode.EXCEL_READ_WAY = operation;
            //是否检查表头
            ExcelCode.EXCEL_CHECK_TITLE = checkTitle;
            //验证实体类字段
            ExcelValidate.validateFields(objectClass.getDeclaredFields());
            //验证文件是否合法,并判断文件的类型，是2003还是2007 ->验证表中数据->开始读取数据
            resultDate = ExcelReadFrom.readFromExcel(objectClass, ExcelValidate.validateExcelContent(
                    ExcelWorkbook.getExcelWorkbook(file.getOriginalFilename(), file.getInputStream())));
        } catch (ExcelException e) {
            throw e;
        } catch (IOException e) {
            throw new ExcelException("获取上传文件流异常");
        } finally {
            ExcelCode.destroyColl();
        }
        return resultDate;
    }
}
