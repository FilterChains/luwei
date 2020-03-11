package com.luwei.excelutils;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.excelutils
 * @auther: luwei
 * @description: excel function list
 * @date: 2020/2/19 19:38
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
class ExcelFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelFunction.class);

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
    static <T> List<T> readExcel(MultipartFile file, Class<T> objectClass, boolean operation, boolean checkTitle) throws ExcelException {
        List<T> resultDate = null;
        //创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3,
                10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        try {
            //读取数据方式
            ExcelCode.EXCEL_READ_WAY = operation;
            //是否检查表头
            ExcelCode.Excel_check_title = checkTitle;
            //验证实体类字段
            executor.execute(() -> {
                validateFields(objectClass.getDeclaredFields());
            });
            //获取文件上传流对象
            InputStream finalInputStream = file.getInputStream();
            //验证文件是否合法,并判断文件的类型，是2003还是2007
            Future<Workbook> submit = executor.submit((new Callable<Workbook>() {
                @Override
                public Workbook call() throws ExecutionException {
                    return validateExcel(file.getOriginalFilename(), finalInputStream);
                }
            }));
            Workbook workbook = submit.get(3000, TimeUnit.MILLISECONDS);
            //准备关闭线程池
            executor.shutdown();
            //开始保存数据
            resultDate = readFromExcel(workbook, objectClass);
        } catch (IOException e) {
            e.fillInStackTrace();
            LOGGER.info("获取上传文件输入流异常", e.getMessage());
        } catch (ExcelException ex) {
            LOGGER.info("上传文件验证失败", ex.getMessage());
            throw ex;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.fillInStackTrace();
            LOGGER.info("获取对应文件操作超时,时间:3s", e.getCause());
            throw new ExcelException("上传文件格式与模板不匹配", ExcelType.ExcelError.EXCEL_VERSION);
        } finally {
            //关闭线程池
            executor.shutdownNow();
            //System.out.println("线程池是否关闭"+executor.isShutdown());
            //清除集合数据
            ExcelCode.titleName.clear();
            ExcelCode.methodNames.clear();
            ExcelCode.fieldTypes.clear();
            ExcelCode.validateName.clear();
            ExcelCode.excludeTitle.clear();
        }
        return resultDate;
    }

    /**
     * @Title: validateExcel
     * @Description: 验证上传文件合法性
     * @Param: [fileName]   参数
     * @Return: com.luwei.excelutils.ExcelType   返回类型
     * @Throws: ExcelException
     * @Date: 2020/2/19 21:37
     */
    static Workbook validateExcel(String fileName, InputStream inputStream) throws ExecutionException {
        Workbook workbook = null;
        /*检查文件名是否为空或者是否是Excel格式的文件,并验证相应版本*/
        if (StringUtils.isEmpty(fileName)) {
            throw new ExecutionException(new Throwable("文件上传失败,请检查上传文件路径"));
        }
        try {
            if (fileName.matches(ExcelType.ExcelVersion.EXCEL_XLS.value)) {
                workbook = new HSSFWorkbook(inputStream); //excel ->2003版(suffix ->.xls)
            } else if (fileName.matches(ExcelType.ExcelVersion.EXCEL_XLSX.value)) {
                workbook = new XSSFWorkbook(inputStream); //excel ->2007版(suffix ->.xlsx)
            }
        } catch (IOException e) {
            LOGGER.info("获取文件对应版本异常", e.getMessage());
            e.fillInStackTrace();
        } finally {
            try {
                //关闭文件流
                if (inputStream != null) {
                    inputStream.close();
                    //System.out.println("流已关闭");
                }
            } catch (IOException e) {
                LOGGER.info("关闭上传文件输入流异常", e.getMessage());
                e.fillInStackTrace();
            }
        }
        if (ObjectUtils.isEmpty(workbook)) {
            throw new ExecutionException(new Throwable(ExcelType.ExcelError.EXCEL_VERSION.getError()));
        }
        return workbook;
    }

    /**
     * @Title: readFromExcel
     * @Description: 开始读取表格信息
     * @Param: [wb, objectClass]   参数
     * @Return: java.util.List<?>   返回类型
     * @Throws: ExcelException
     * @Date: 2020/2/19 21:39
     */
    static <T> List<T> readFromExcel(Workbook workbook, Class<T> objectClass) throws ExcelException {
        //验证表中数据
        Sheet sheet = validateExcelContent(workbook);
        //读取表头信息,并验证表头信息,确定需要用的方法名---set方法
        readExcelTitle(sheet.getRow(0));
        //读取表信息,并转存实体类
        return saveMessage(objectClass, sheet);
    }

    /**
     * @Title: validateExcelContent
     * @Description: 验证表中数据
     * @Param: [workbook]   参数
     * @Return: org.apache.poi.ss.usermodel.Sheet   返回类型
     * @Throws: ExcelException
     * @Date: 2020/2/21 17:51
     */
    static Sheet validateExcelContent(Workbook workbook) throws ExcelException {
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
            throw new ExcelException("上传文件为空,请仔细检查", ExcelType.ExcelError.TITLE_IS_NULL);
        }
        // 获取表格的总行数
        ExcelCode.totalRows = sheet.getLastRowNum() + ExcelCode.titleRows; // 需要算表头
        if (ExcelCode.totalRows == ExcelCode.titleRows) {
            throw new ExcelException("表格为空没有信息", ExcelType.ExcelError.CONTENT_IS_NULL);
        }
        return sheet;
    }


    /**
     * @Title: validateFields
     * @Description: 验证获取实体类字段
     * @Param: [fields]   参数
     * @Return: void   返回类型
     * @Throws: ExcelException
     * @Date: 2020/2/19 19:52
     */
    static void validateFields(Field... fields) throws RuntimeException {
        if (null == fields || 0 == fields.length) {
            throw new RuntimeException("请为实体类创建字段");
        }
        //获取字段
        validateExcelTitle(fields);
    }

    /**
     * @Title: readExcelTitle
     * @Description: 读取表头信息，设置总行数和总列数并组装set方法
     * @Param: [titleRow]   参数
     * @Return: void   返回类型
     * @Throws: ExcelException
     * @Date: 2020/2/19 21:41
     */
    static void readExcelTitle(Row titleRow) throws ExcelException {
        //获取注解信息
        Map<String, ExcelTitle> excelTitle = ExcelCode.titleName;
        Map<String, Field> validateExcelTitle = ExcelCode.validateName;
        // 获取表头的列数
        ExcelCode.totalCells = titleRow.getLastCellNum();
        // 遍历
        for (int columnIndex = 0; columnIndex < ExcelCode.totalCells; columnIndex++) {
            //获取对应列的名称
            String data = StringReplace(titleRow.getCell(columnIndex).toString());
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
        if (ExcelCode.Excel_check_title) {
            int size = ExcelCode.totalCells - ExcelCode.excludeTitle.size();
            int methodSize = ExcelCode.methodNames.size();
            if (size != excelTitle.size()) {
                throw new ExcelException("实体类字段缺省,与上传文件表头不匹配", ExcelType.ExcelError.TITLE_THE_MANIPULATION);
            }
            if (excelTitle.size() != methodSize) {
                throw new ExcelException("表头已被篡改,请仔细检查后重新上传", ExcelType.ExcelError.TITLE_THE_MANIPULATION);
            }
        }
    }

    /**
     * @Title: validateExcelTitle
     * @Description: 获取实体类字段信息
     * @Param: [fields]   参数
     * @Return: void   返回类型
     * @Throws: ExcelException
     * @Date: 2020/2/21 17:52
     */
    static void validateExcelTitle(Field... fields) throws RuntimeException {
        // 获得该类的所有属性
        for (Field field : fields) {
            // 获得字段注解
            ExcelTitleName excelTitleName = field.getAnnotation(ExcelTitleName.class);
            if (!ObjectUtils.isEmpty(excelTitleName)) {
                ExcelTitle excelTitle = new ExcelTitle();
                excelTitle.setTitleName(excelTitleName.value());
                excelTitle.setTitleIndex(excelTitleName.index());
                ExcelCode.titleName.put(field.getName(), excelTitle);
                ExcelCode.validateName.put(StringReplace(excelTitleName.value()), field);
            }
        }
        if (MapUtils.isEmpty(ExcelCode.titleName) || MapUtils.isEmpty(ExcelCode.validateName)) {
            throw new RuntimeException("获取实体类信息异常,请为字段添加注解");
        }
    }

    /**
     * @Title: saveMessage
     * @Description: 保存信息
     * @Param: [objectClass, sheets]   参数
     * @Return: java.util.List<?>   返回类型
     * @Date: 2020/2/19 20:32
     */
    static <T> List<T> saveMessage(Class<T> objectClass, Sheet... sheets) {
        List<T> result = null;
        Sheet sheet = sheets[0]; //获取第一个sheet文件
        Row titleRow = sheet.getRow(0); //获取表头
        Map<String, Field> validateExcelTitle = ExcelCode.validateName;
        try {
            result = new ArrayList<>();
            // 逐行读取数据
            for (int rowIndex = 0; rowIndex < ExcelCode.totalRows; rowIndex++) {
                // 获得行对象
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    // 实例化该泛型类的对象
                    T obj = objectClass.newInstance();
                    // 获得本行中各单元格中的数据,从第一列开始读取
                    for (int columnIndex = 0; columnIndex < ExcelCode.totalCells; columnIndex++) {
                        String tableName = StringReplace(titleRow.getCell(columnIndex).toString());
                        Field field = validateExcelTitle.get(tableName);
                        //验证保存信息,验证表中的字段是否已在实体类中创建
                        if (ObjectUtils.isEmpty(field)) {
                            continue;
                        }
                        //获取本行的所有数据类型
                        Cell cell = row.getCell(columnIndex);
                        //获取方法名
                        String methodName = ExcelCode.methodNames.get(field.getName());
                        if (null != cell && !StringUtils.isEmpty(methodName)) {
                            //获取方法数据类型
                            Class<?> methodClass = ExcelCode.fieldTypes.get(methodName);
                            if (!ObjectUtils.isEmpty(methodClass)) {
                                //读取信息->返回对应数据类型
                                Object cellValue = getCellValue(cell, methodClass);
                                if (!ObjectUtils.isEmpty(cellValue)) {
                                    //将读取出来的信息保存至对象中
                                    objectClass.getMethod(methodName, methodClass).invoke(obj, cellValue);
                                }
                            }
                        }
                    }
                    result.add(obj);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.info("实例化对象异常", e.getMessage());
            e.fillInStackTrace();
        } catch (InvocationTargetException | NoSuchMethodException e) {
            LOGGER.info("保存数据异常,检查字段类型是否正确", e.getMessage());
            e.fillInStackTrace();
        }
        return result;
    }

    /**
     * @Title: getCellValue
     * @Description: 匹配内容的数据类型
     * @Param: [cell, methodClass]   参数
     * @Return: java.lang.Object   返回类型
     * @Date: 2020/2/19 21:43
     */
    static Object getCellValue(Cell cell, Class<?> methodClass) {
        Object cellValue = null;
        // 判断数据类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                // 如果数字是日期类型，就转换成日期
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date dateCellValue = cell.getDateCellValue();
                    if (ExcelCode.EXCEL_READ_WAY) {
                        if (methodClass.equals(Date.class)) {
                            cellValue = dateCellValue;
                        }
                    } else if (methodClass.equals(String.class)) {
                        cellValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateCellValue);
                    }
                } else {
                    cell.setCellType(Cell.CELL_TYPE_STRING); //转换成字符串
                    //所有数字用BigDecimal容器来装;stripTrailingZeros->去除小数点后多余的0,toPlainString->避免科学计数法
                    String number = new BigDecimal(cell.getStringCellValue())
                            .stripTrailingZeros().toPlainString();
                    //根据字段类型进行相应转换
                    cellValue = conversionNumber(number, methodClass);
                }
                break;
            case Cell.CELL_TYPE_STRING: // 字符串
                if (methodClass.equals(String.class)) {
                    //去除所有空格
                    String trim = cell.getStringCellValue().replaceAll("\\s*", "");
                    cellValue = (0 == trim.length()) ? null : trim;
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
                boolean booleanCellValue = cell.getBooleanCellValue();
                if (ExcelCode.EXCEL_READ_WAY) {
                    if (methodClass.equals(Boolean.class) || methodClass.equals(boolean.class)) {
                        cellValue = booleanCellValue;
                    }
                } else if (methodClass.equals(String.class)) {
                    cellValue = booleanCellValue ? "是" : "否";
                }
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                //获取excel公式计算后的值
                String result;
                try {
                    result = String.valueOf(cell.getNumericCellValue());
                } catch (IllegalStateException e) {
                    result = String.valueOf(cell.getRichStringCellValue());
                }
                //根据字段类型进行相应转换
                cellValue = conversionNumber(result, methodClass);
                break;
            case Cell.CELL_TYPE_BLANK: // 空值
                break;
            case Cell.CELL_TYPE_ERROR: // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    /**
     * @Title: conversionNumber
     * @Description: 字段类型转换
     * @Param: [number, methodClass]   参数
     * @Return: java.lang.Object   返回类型
     * @Date: 2020/2/19 20:57
     */
    static Object conversionNumber(String number, Class<?> methodClass) {
        Object cellValue = null;
        if (methodClass.equals(String.class)) {
            cellValue = number;
        } else if (methodClass.equals(BigDecimal.class)) {
            cellValue = new BigDecimal(number).setScale(6, BigDecimal.ROUND_DOWN); //默认保留6位
        } else if (methodClass.equals(Integer.class) || methodClass.equals(int.class)) {
            cellValue = Integer.valueOf(number);
        } else if (methodClass.equals(Byte.class) || methodClass.equals(byte.class)) {
            cellValue = Byte.valueOf(number);
        } else if (methodClass.equals(Short.class) || methodClass.equals(short.class)) {
            cellValue = Short.valueOf(number);
        } else if (methodClass.equals(Long.class) || methodClass.equals(long.class)) {
            cellValue = Long.valueOf(number);
        } else if (methodClass.equals(Float.class) || methodClass.equals(float.class)) {
            cellValue = Float.valueOf(number);
        } else if (methodClass.equals(Double.class) || methodClass.equals(double.class)) {
            cellValue = Double.valueOf(number);
        }
        return cellValue;
    }

    /**
     * @Title: StringReplace
     * @Description: 字符串替换英文
     * @Param: [data]   参数
     * @Return: String ->结果  返回类型
     * @Date: 2020/2/19 20:57
     */
    static String StringReplace(String data) {
        String result = "";
        if (!StringUtils.isEmpty(data)) {
            result = data.replaceAll("\\s+", "")
                    .replace("（", "(")
                    .replace("）", ")");
        }
        return result;
    }
}
