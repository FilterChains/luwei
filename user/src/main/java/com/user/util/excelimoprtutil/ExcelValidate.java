package com.user.util.excelimoprtutil;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>@description : excel validate </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2021/2/9 9:26 </p>
 **/
public class ExcelValidate {


    /**
     * <p>@description : 验证对象是否有属性和属性上是否加了ExcelName注解 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2021/2/9 9:31 </p>
     *
     * @param fields fields ->字段数组
     **/
    public static void validateFields(Field... fields) throws ExcelException {
        if (null == fields || 0 == fields.length) {
            throw new ExcelException("请为实体类创建字段");
        }
        //获取字段
        getExcelFieldAnnotation(fields);
    }

    /**
     * <p>@description : 验证上传文件是否为空 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2021/2/9 9:40 </p>
     *
     * @param workbook ->excel WorkBook
     * @return {@link Sheet}
     **/
    public static Sheet validateExcelContent(Workbook workbook) throws ExcelException {
        //获取excel中sheet的总数
        int sheets = workbook.getNumberOfSheets();
        List<Sheet> she = new ArrayList<>();
        //获取Sheet
        for (int i = 0; i < sheets; i++) {
            she.add(workbook.getSheet(workbook.getSheetName(i)));
        }
        //读取excel数据 ->获得指定的excel表 ,目前只开发读取第一个sheet表格
        Sheet sheet = she.get(0);
        Row row = sheet.getRow(0);
        if (ObjectUtils.isEmpty(row)) {
            throw new ExcelException(ExcelErrorType.TITLE_IS_NULL);
        }
        // 获取表格的总行数
        ExcelCode.totalRows = sheet.getLastRowNum() + ExcelCode.titleRows; // 需要算表头
        if (ExcelCode.totalRows == ExcelCode.titleRows) {
            throw new ExcelException(ExcelErrorType.CONTENT_IS_NULL);
        }
        readExcelTitleAndValidate(row);
        return sheet;
    }

    /**
     * <p>@description : 读取表头信息,并验证表头信息,确定需要用的方法名---set方法 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2021/2/9 9:45 </p>
     *
     * @param titleRow ->列表头
     **/
    private static void readExcelTitleAndValidate(Row titleRow) throws ExcelException {
        //获取注解信息
        Map<String, ExcelTitle> excelTitle = ExcelCode.titleName;
        Map<String, Field> validateExcelTitle = ExcelCode.validateName;
        // 获取表头的列数
        ExcelCode.totalCells = titleRow.getLastCellNum();
        // 遍历
        for (int columnIndex = 0; columnIndex < ExcelCode.totalCells; columnIndex++) {
            //获取对应列的名称
            Cell cell = titleRow.getCell(columnIndex);
            String data = ExcelCode.stringReplace(ObjectUtils.isEmpty(cell) ? null : cell.toString());
            //指定排除列表表头字段
            if (ExcelCode.EXCEL_SERIAL_NUMBER.equals(data)) {
                ExcelCode.excludeTitle.add(data);
            }
            //排除表名
            Field field = validateExcelTitle.get(data);
            if (ObjectUtils.isEmpty(field)) {
                continue;
            }
            //获取对应字段
            ExcelTitle title = excelTitle.get(field.getName());
            if (!ObjectUtils.isEmpty(title)) {
                //Judge Excel Title Name
                String capitalizeTheFirstLetter = null;
                if (title.getTitleIndex() != -1 && title.getTitleIndex() == columnIndex) {
                    capitalizeTheFirstLetter = field.getName().substring(0, 1).toUpperCase()
                            + field.getName().substring(1); // 使其首字母大写

                } else if (title.getTitleIndex() == -1) {
                    capitalizeTheFirstLetter = field.getName().substring(0, 1).toUpperCase()
                            + field.getName().substring(1); // 使其首字母大写
                }
                if (!StringUtils.isEmpty(capitalizeTheFirstLetter)) {
                    String methodName = ExcelCode.METHOD_SET + capitalizeTheFirstLetter;
                    //组装方法名
                    ExcelCode.methodNames.put(field.getName(), methodName);
                    //组装方法类型
                    ExcelCode.fieldTypes.put(methodName, field.getType());
                }
            }
        }
        //是否检查表头
        if (ExcelCode.EXCEL_CHECK_TITLE) {
            int size = ExcelCode.totalCells - ExcelCode.excludeTitle.size();
            int methodSize = ExcelCode.methodNames.size();
            if (size != excelTitle.size() || excelTitle.size() != methodSize) {
                throw new ExcelException(ExcelErrorType.TITLE_THE_MANIPULATION);
            }
        }
    }

    /**
     * <p>@description : 获取注解 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2021/2/9 9:29 </p>
     *
     * @param fields ->字段数组
     **/
    private static void getExcelFieldAnnotation(Field... fields) throws ExcelException {

        // 获得该类的所有属性
        for (Field field : fields) {
            // 获得字段注解
            ExcelTitleName excelTitleName = field.getAnnotation(ExcelTitleName.class);
            if (!ObjectUtils.isEmpty(excelTitleName)) {
                ExcelTitle excelTitle = new ExcelTitle();
                excelTitle.setTitleName(excelTitleName.value());
                excelTitle.setTitleIndex(excelTitleName.index());
                ExcelCode.titleName.put(field.getName(), excelTitle);
                ExcelCode.validateName.put(ExcelCode.stringReplace(excelTitleName.value()), field);
            }
        }
        if (MapUtils.isEmpty(ExcelCode.titleName) || MapUtils.isEmpty(ExcelCode.validateName)) {
            throw new ExcelException("获取实体类信息异常,请为字段添加注解");
        }
    }
}
