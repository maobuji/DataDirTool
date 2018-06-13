package com.fan.exceltool;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

/**
 * Created by Administrator on 2018/6/13.
 */
public interface IExcelWriter {

    public void createSheet(String sheetName);

    public void print(String value);

    public void print(String value, WriteType type);

    public void println(String value);

    public void println(String value, WriteType type);

    public void writeFile(String filePath) throws IOException;

}
