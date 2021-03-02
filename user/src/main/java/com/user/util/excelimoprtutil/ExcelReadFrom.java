package com.user.util.excelimoprtutil;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>@description : reading Excel </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2021/2/9 9:54 </p>
 **/
public class ExcelReadFrom {

    /**
     * <p>@description : 读取excel信息保存至实体类 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2021/2/9 10:10 </p>
     *
     * @param objectClass ->将要保存信息的实体类
     * @param sheets      ->sheet 数组
     * @return {@link List<T>}
     **/
    public static <T> List<T> readFromExcel(Class<T> objectClass, Sheet... sheets) throws ExcelException {
        List<T> result = new ArrayList<>();
        //获取第一个sheet文件
        Sheet sheet = sheets[0];
        //获取表头
        Row titleRow = sheet.getRow(0);
        Map<String, Field> validateExcelTitle = ExcelCode.validateName;
        try {
            // 逐行读取数据
            for (int rowIndex = 0; rowIndex < ExcelCode.totalRows; rowIndex++) {
                // 获得行对象
                Row row = sheet.getRow(rowIndex);
                if (Objects.isNull(row)) {
                    continue;
                }
                // 实例化该泛型类的对象
                T obj = objectClass.newInstance();
                // 获得本行中各单元格中的数据,从第一列开始读取
                for (int columnIndex = 0; columnIndex < ExcelCode.totalCells; columnIndex++) {
                    Cell cl = titleRow.getCell(columnIndex);
                    final String tableName = ExcelCode.stringReplace(ObjectUtils.isEmpty(cl) ? null : cl.toString());
                    Field field = validateExcelTitle.get(tableName);
                    //验证保存信息,验证表中的字段是否已在实体类中创建
                    if (ObjectUtils.isEmpty(field)) {
                        continue;
                    }
                    //获取本行的所有数据类型
                    Cell cell = row.getCell(columnIndex);
                    //获取方法名
                    final String methodName = ExcelCode.methodNames.get(field.getName());
                    if (Objects.nonNull(cell) && null != methodName) {
                        //获取实体类属性的数据类型(根据方法名)
                        Class<?> fieldClass = ExcelCode.fieldTypes.get(methodName);
                        if (Objects.isNull(fieldClass)) {
                            continue;
                        }
                        //读取信息->返回对应数据类型
                        Object cellValue = getCellValue(cell, fieldClass);
                        if (Objects.isNull(cellValue)) {
                            continue;
                        }
                        //将读取出来的信息保存至对象中
                        objectClass.getMethod(methodName, fieldClass).invoke(obj, cellValue);
                    }
                }
                result.add(obj);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ExcelException("实例化对象异常");
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new ExcelException("保存数据异常,检查字段类型是否正确");
        }
        return result;
    }

    /**
     * <p>@description : 匹配内容的数据类型 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2021/2/9 10:01 </p>
     *
     * @param cell       ->某一行
     * @param fieldClass ->字段数据类型
     * @return {@link Object}
     **/
    private static Object getCellValue(Cell cell, Class<?> fieldClass) {
        Object cellValue = null;
        // 判断数据类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                // 如果数字是日期类型，就转换成日期
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date dateCellValue = cell.getDateCellValue();
                    if (ExcelCode.EXCEL_READ_WAY) {
                        if (fieldClass.equals(Date.class)) {
                            cellValue = dateCellValue;
                        }
                    } else if (fieldClass.equals(String.class)) {
                        cellValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateCellValue);
                    }
                } else {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    //所有数字用BigDecimal容器来装;stripTrailingZeros->去除小数点后多余的0,toPlainString->避免科学计数法
                    String number = new BigDecimal(cell.getStringCellValue())
                            .stripTrailingZeros().toPlainString();
                    //根据字段类型进行相应转换
                    cellValue = conversionNumber(number, fieldClass);
                }
                break;
            case Cell.CELL_TYPE_STRING:
                if (fieldClass.equals(String.class)) {
                    //去除所有空格
                    String trim = cell.getStringCellValue().replaceAll("\\s*", "");
                    cellValue = (0 == trim.length()) ? null : trim;
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                boolean booleanCellValue = cell.getBooleanCellValue();
                if (ExcelCode.EXCEL_READ_WAY) {
                    if (fieldClass.equals(Boolean.class) || fieldClass.equals(boolean.class)) {
                        cellValue = booleanCellValue;
                    }
                } else if (fieldClass.equals(String.class)) {
                    cellValue = booleanCellValue ? "是" : "否";
                }
                break;
            case Cell.CELL_TYPE_FORMULA:
                //获取excel公式计算后的值
                String result;
                try {
                    result = String.valueOf(cell.getNumericCellValue());
                } catch (IllegalStateException e) {
                    result = String.valueOf(cell.getRichStringCellValue());
                }
                //根据字段类型进行相应转换
                cellValue = conversionNumber(result, fieldClass);
                break;
            case Cell.CELL_TYPE_BLANK:
                // 空值
                break;
            case Cell.CELL_TYPE_ERROR:
                // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    /**
     * <p>@description : 字段类型转换 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2021/2/9 10:02 </p>
     *
     * @param number     ->字符串数字
     * @param fieldClass ->字段数据类型
     * @return {@link Object}
     **/
    private static Object conversionNumber(String number, Class<?> fieldClass) {
        Object cellValue = null;
        if (fieldClass.equals(String.class)) {
            cellValue = number;
        } else if (fieldClass.equals(BigDecimal.class)) {
            cellValue = new BigDecimal(number).setScale(6, BigDecimal.ROUND_DOWN);
        } else if (fieldClass.equals(Integer.class) || fieldClass.equals(int.class)) {
            cellValue = Integer.valueOf(number);
        } else if (fieldClass.equals(Byte.class) || fieldClass.equals(byte.class)) {
            cellValue = Byte.valueOf(number);
        } else if (fieldClass.equals(Short.class) || fieldClass.equals(short.class)) {
            cellValue = Short.valueOf(number);
        } else if (fieldClass.equals(Long.class) || fieldClass.equals(long.class)) {
            cellValue = Long.valueOf(number);
        } else if (fieldClass.equals(Float.class) || fieldClass.equals(float.class)) {
            cellValue = Float.valueOf(number);
        } else if (fieldClass.equals(Double.class) || fieldClass.equals(double.class)) {
            cellValue = Double.valueOf(number);
        }
        return cellValue;
    }
}
