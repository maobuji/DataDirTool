package com.fan.exceltool;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelWriter {

    private HSSFWorkbook wb = null;
    private HSSFSheet st = null;
    private HSSFRow row = null;

    private int rowIndex = 0;
    private int columnIndex = 0;

    private String sheetName = "";

    private int sheetCopyNum=0;

    public ExcelWriter() {
        this.wb = new HSSFWorkbook();

        HSSFCellStyle style = wb.createCellStyle();
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor((short) 38);
        this.style = style;
    }

    public void createSheet(String sheetName) {

        String tempSheetName="";
        if(sheetName!=null){
            sheetCopyNum=0;
            this.sheetName = sheetName;
            tempSheetName=sheetName;
        }else{
            tempSheetName=this.sheetName+(++sheetCopyNum);
        }

        this.st = wb.createSheet(tempSheetName);
        this.row = this.st.createRow(0);
        rowIndex = 0;
        columnIndex = 0;
    }

    public HSSFCell print(String value) {
        HSSFCell cell = row.createCell(columnIndex);
        cell.setCellValue(new HSSFRichTextString(value));
        columnIndex = columnIndex + 1;
        return cell;

    }

    public HSSFCell println(String value) {
        HSSFCell cell = print(value);
        rowIndex=rowIndex+1;
        columnIndex=0;
        this.row = this.st.createRow(rowIndex);

        if (rowIndex == 65000) {
            createSheet(null);
        }
        return cell;
    }

    public void writeFile(String filePath) throws IOException {
        FileOutputStream writeFile = new FileOutputStream(filePath);
        wb.write(writeFile);
        writeFile.close();
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    private HSSFCellStyle style = null;

    public HSSFCellStyle getStyle() {
        return style;
    }

    public HSSFWorkbook getWb() {
        return wb;
    }

    public void setWb(HSSFWorkbook wb) {
        this.wb = wb;
    }

}
