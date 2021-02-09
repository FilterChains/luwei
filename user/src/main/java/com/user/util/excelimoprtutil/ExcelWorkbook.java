package com.user.util.excelimoprtutil;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * <p>@description : Get Excel WorkBook </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2021/2/9 9:34 </p>
 **/
public class ExcelWorkbook {

    /**
     * <p>@description : 获取excel对应版本的workbook </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2021/2/9 9:38 </p>
     *
     * @param fileName    ->文件名称
     * @param inputStream ->文件流
     * @return {@link Workbook}
     **/
    public static Workbook getExcelWorkbook(String fileName, InputStream inputStream) throws ExcelException {
        Workbook workbook = null;
        /*检查文件名是否为空或者是否是Excel格式的文件,并验证相应版本*/
        if (StringUtils.isEmpty(fileName)) {
            throw new ExcelException("文件上传失败,请检查上传文件路径");
        }
        try {
            // poi 升级后的新方法，获取对应excel的操作版本
            workbook = WorkbookFactory.create(inputStream);
        } catch (IOException | InvalidFormatException e) {
            throw new ExcelException("获取文件对应版本异常");
        } finally {
            try {
                //关闭文件流
                if (Objects.nonNull(inputStream)) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new ExcelException("关闭上传文件流异常");
            }
        }
        if (Objects.isNull(workbook)) {
            throw new ExcelException(ExcelErrorType.EXCEL_VERSION.getError());
        }
        return workbook;
    }
}
