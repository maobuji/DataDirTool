package com.fan.exceltool.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/6/13.
 */
public class XlsxExcelWriterTest {

    /**
     *
     * 打印出各种颜色
     * @throws Exception
     */
    @Test
    public void testColour() throws Exception {
        Workbook wb = new XSSFWorkbook();

        Sheet st = wb.createSheet("测试");
        Row row = st.createRow(0);

        for (int i = 1; i < 64; i++) {

            Cell cell = row.createCell(i);
            cell.setCellValue(new XSSFRichTextString("内容"+i));
            CellStyle style = wb.createCellStyle();
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor((short) i);
            cell.setCellStyle(style);
        }

        FileOutputStream writeFile = new FileOutputStream("aaa.xlsx");
        wb.write(writeFile);
        writeFile.close();

    }

}