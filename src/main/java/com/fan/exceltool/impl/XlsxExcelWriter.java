package com.fan.exceltool.impl;

import com.fan.exceltool.IExcelWriter;
import com.fan.exceltool.WriteType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class XlsxExcelWriter implements IExcelWriter {

    private Workbook wb = null;
    private Sheet st = null;
    private Row row = null;

    private int rowIndex = 0;
    private int columnIndex = 0;

    private String sheetName = "";

    private int sheetCopyNum = 0;

    CellStyle dirHeadstyle;

    CellStyle tableHeadstyle;

    public XlsxExcelWriter() {
        this.wb = new XSSFWorkbook();

        dirHeadstyle = wb.createCellStyle();
        dirHeadstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND );
        dirHeadstyle.setFillForegroundColor((short) 29);

        tableHeadstyle = wb.createCellStyle();
        tableHeadstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND );
        tableHeadstyle.setFillForegroundColor((short) 24);

    }

    @Override
    public void createSheet(String sheetName) {
        String tempSheetName = "";
        if (sheetName != null) {
            sheetCopyNum = 0;
            this.sheetName = sheetName;
            tempSheetName = sheetName;
        } else {
            tempSheetName = this.sheetName + (++sheetCopyNum);
        }

        this.st = wb.createSheet(tempSheetName);
        this.row = this.st.createRow(0);
        rowIndex = 0;
        columnIndex = 0;
    }

    @Override
    public void print(String value) {
        this.print(value,WriteType.NONE);
    }

    @Override
    public void print(String value, WriteType type) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(new XSSFRichTextString(value));
        columnIndex = columnIndex + 1;

        switch (type) {
            case DIR_HEAD:
                cell.setCellStyle(dirHeadstyle);
                break;
            case TABLE_HEAD:
                cell.setCellStyle(tableHeadstyle);
                break;
            case NONE:
                break;
        }
    }

    @Override
    public void println(String value, WriteType type) {
        print(value,type);
        rowIndex = rowIndex + 1;
        columnIndex = 0;
        this.row = this.st.createRow(rowIndex);
    }

    @Override
    public void println(String value) {
        println(value,WriteType.NONE);
    }


    @Override
    public void writeFile(String filePath) throws IOException {
        FileOutputStream writeFile = new FileOutputStream(filePath);
        wb.write(writeFile);
        writeFile.close();
    }
}
