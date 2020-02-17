package com.luwei.excelutils;

import com.alibaba.excel.util.ObjectUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @projectName： EasyExcelUtil
 * @packageName: com.excel.util
 * @auther: luwei
 * @description: 读取单一表头的excel文件 ->表头没有合并行或者列，公共方法
 * @date: 2019/10/20 11:06
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class ExcelUtil{

    /**@Title: readExcel
     * @Description: 读取excel表格中的内容
     * @Param:    filePath   文件完整路径
     * @Return:    List<List<Object>>
     * @Throws: throws ExcelException
     * @Date: 2019/10/20 12:32
     */
    public List readSingleTitleExcel(MultipartFile  file, Object object) throws ExcelException {
        // 把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
        CommonsMultipartFile cf = (CommonsMultipartFile)file;
        String fileName = file.getOriginalFilename();
        List resultDate = null;
        FileItem fileItem = null;
        InputStream is = null;
        try {
            //在本地写入要读入的excel
            fileItem = cf.getFileItem();
            fileItem.write(ExcelCode.getNewFile(fileName));
            // 验证文件是否合法
            if (!ExcelCode.validateExcel(fileName)) {
                return null;
            }
            // 判断文件的类型，是2003还是2007
            boolean isExcel2003 = true;
            if (ExcelCode.isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            // 调用本类提供的根据流读取的方法
            File files = new File(fileName);
            is = new FileInputStream(files);
            resultDate = readTheCorrespondingVersionExcel(is, isExcel2003, object);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if(null != fileItem){
                    fileItem.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultDate;
    }

    /**@Title:  readTheCorrespondingVersionExcel
     * @Description: 根据对应版本获取相应流读取对应excel ->alert：目前只支持2003/2007
     * @Param:    inputStream ->文件流，isExcel2003 ->validateExcelType Object ->储存结果的对象
     * @Return:  List<List<Object>>
     * @Throws: throws ExcelException
     * @Date: 2019/10/20 12:35
     */
    private List readTheCorrespondingVersionExcel(InputStream inputStream, boolean isExcel2003, Object object) throws ExcelException {
        List dataLst = null;
        Workbook wb = null;
        try {
            // 根据版本选择创建Workbook的方式
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream); //excel ->2003版(suffix ->.xls)
            } else {
                wb = new XSSFWorkbook(inputStream); //excel ->2007版(suffix ->.xlsx)
            }
            dataLst = readFromExcel(wb,object);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataLst;
    }

    /**@Title:  readFromExcel
     * @Description: 读取excel表中的数据.
     * @Param: sheetName 表格索引(EXCEL 是多表文档,所以需要输入表索引号，如sheet1
     * @Return:  List<Object>
     * @Throws: throws ExcelException
     * @Date: 2019/10/20 11:55
     */
    private List readFromExcel(Workbook wb , Object object) throws ExcelException {
        List result = new ArrayList();
        // 获取该对象的class对象
        Class<?> objectClass = object.getClass();
        // 获得该类的所有属性
        Field[] fields = objectClass.getDeclaredFields();
        //获取excel中sheet的总数
        int sheets = wb.getNumberOfSheets();
        List<Sheet> she = new ArrayList<>();
        //获取Sheet
        for (int i = 0; i < sheets; i++) {
            she.add(wb.getSheet(wb.getSheetName(i)));
        }
        /**读取excel数据 ->获得指定的excel表 ,目前只开发读取第一个sheet表格*/
        Sheet sheet = she.get(0);

        /**读取表头信息,并验证表头信息,确定需要用的方法名---set方法*/
        readExcelTitle(objectClass,sheet,fields);

        // 逐行读取数据 从1开始 忽略表头
        for (int rowIndex = ExcelCode.titleRows; rowIndex < ExcelCode.totalRows; rowIndex++) {
            // 获得行对象
            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                Object obj = null;
                // 实例化该泛型类的对象一个对象
                try {
                    obj = objectClass.newInstance();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                // 获得本行中各单元格中的数据
                for (int columnIndex = 1; columnIndex < ExcelCode.totalCells; columnIndex++) {
                    //获取本行的所有数据类型
                    Cell cell = row.getCell(columnIndex);
                    // 获取要调用方法的方法名
                    String methodName = ExcelCode.methodNames.get(columnIndex-1);
                    try {
                        // 将所有数据不论对错全部保存为String类型,在控制层进行筛选
                        if (null != cell) {
                            String cellValue = "";
                            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC  && HSSFDateUtil.isCellDateFormatted(cell)) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                cellValue = sdf.format(cell.getDateCellValue());
                            } else {
                                cellValue = ExcelCode.getCellValue(cell).replace(" ", "");
                            }
                            objectClass.getMethod(methodName, String.class).invoke(obj,cellValue);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                result.add(obj);
            }
        }
        return result;
    }

    /**@Title:  readExcelTitle
     * @Description: 读取表头信息，设置总行数和总列数并组装set方法
     * @Param:   fields ->对象字段集合
     * @Return:  throws ExcelException
     * @Throws: ExcelException
     * @Date: 2019/10/20 14:14
     */
    private void readExcelTitle(Class objectClass,Sheet sheet,Field[] fields) throws ExcelException {
        // 获取表格的总行数
        ExcelCode.totalRows = sheet.getLastRowNum() + ExcelCode.titleRows; // 需要算表头
        if (ExcelCode.totalRows < ExcelCode.titleRows) {
            throw new ExcelException("表格为空没有信息");
        }
        Map<String, ExcelTitle> excelTitle = ExcelCode.validateExcelTitle(objectClass);
        // 获取表头的列数
        ExcelCode.totalCells = sheet.getRow(0).getLastCellNum();
        // 获得表头行对象
        Row titleRow = sheet.getRow(0);
        //判断第一列是否是序号，并判断实体类里面有误序号字段
        String s = titleRow.getCell(0).toString();
        // 遍历
        for (int columnIndex = 1; columnIndex < ExcelCode.totalCells; columnIndex++) { // 遍历表头列
            String data = titleRow.getCell(columnIndex).toString(); // 某一列的内容
            //Judge Excel Title Name
            ExcelTitle title = excelTitle.get(fields[columnIndex-1].getName());
            String capitalizeTheFirstLetter = null;
            if(!ObjectUtils.isEmpty(title)&&title.getTitleIndex()!=-1&&title.getTitleIndex() == columnIndex){
                capitalizeTheFirstLetter = fields[columnIndex-1].getName().substring(0, 1).toUpperCase()
                        + fields[columnIndex-1].getName().substring(1); // 使其首字母大写

            }else if(!ObjectUtils.isEmpty(title)&&title.getTitleIndex()==-1){
                if(data.equals(excelTitle.get(fields[columnIndex-1].getName()).getTitleName())){
                    capitalizeTheFirstLetter = fields[columnIndex-1].getName().substring(0, 1).toUpperCase()
                            + fields[columnIndex-1].getName().substring(1); // 使其首字母大写
                }
            }
            if(!StringUtils.isEmpty(capitalizeTheFirstLetter)){
                ExcelCode.methodNames.add("set" + capitalizeTheFirstLetter);
                // 将属性类型放到数组中
                ExcelCode.fieldTypes.add(fields[columnIndex-1].getType().getName());
            }
        }
        if(fields.length != ExcelCode.methodNames.size()){
            throw new ExcelException("表头已被篡改,请仔细检查后重新上传");
        }
    }

}
