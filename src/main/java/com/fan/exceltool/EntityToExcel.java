package com.fan.exceltool;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

import com.fan.metastool.EntityAnalysisInfo;
import com.fan.metastool.EntityColumnInfo;

public class EntityToExcel {

    private ExcelWriter excelWriter = null;

    public ExcelWriter getExcelWriter() {
        return excelWriter;
    }

    public void setExcelWriter(ExcelWriter excelWriter) {
        this.excelWriter = excelWriter;


        HSSFCellStyle style = excelWriter.getWb().createCellStyle();
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor((short) 24);

        excelWriter.print("表名").setCellStyle(style);
        excelWriter.print("表别名").setCellStyle(style);
        excelWriter.print("字段名").setCellStyle(style);
        excelWriter.print("字段别名").setCellStyle(style);
        excelWriter.print("字段类型").setCellStyle(style);
        excelWriter.print("Length").setCellStyle(style);
        excelWriter.print("Prec").setCellStyle(style);
        excelWriter.print("Scale").setCellStyle(style);
        excelWriter.print("字段描述").setCellStyle(style);
        excelWriter.println("字段链接信息").setCellStyle(style);
        excelWriter.println("");
    }

    public EntityToExcel() {

    }

    public void write(EntityAnalysisInfo entityAnalysisInfo) throws IOException {
        excelWriter.print(entityAnalysisInfo.getEntityTable()).setCellStyle(
                excelWriter.getStyle());
        excelWriter.print(entityAnalysisInfo.getEntityAlias()).setCellStyle(
                excelWriter.getStyle());
        excelWriter.print(entityAnalysisInfo.getEntityPackage())
                .setCellStyle(excelWriter.getStyle());

        excelWriter.print("").setCellStyle(excelWriter.getStyle());
        excelWriter.print("").setCellStyle(excelWriter.getStyle());
        excelWriter.print("").setCellStyle(excelWriter.getStyle());
        excelWriter.print("").setCellStyle(excelWriter.getStyle());
        excelWriter.print("").setCellStyle(excelWriter.getStyle());
        excelWriter.print("").setCellStyle(excelWriter.getStyle());
        excelWriter.println("").setCellStyle(excelWriter.getStyle());

        this.writeColumn(entityAnalysisInfo, entityAnalysisInfo
                .getEntityTable(), entityAnalysisInfo.getEntityAlias());

        excelWriter.println("");

    }

    private void writeColumn(EntityAnalysisInfo entityAnalysisInfo,
                             String tableName, String tableAlias) {

        if (entityAnalysisInfo == null) {
            return;
        }

        Iterator it = entityAnalysisInfo.getColumns().iterator();
        while (it.hasNext()) {
            EntityColumnInfo entityColumnInfo = (EntityColumnInfo) it.next();

            excelWriter.print(tableName);
            excelWriter.print(tableAlias);
            excelWriter.print(entityColumnInfo.getColumnField());
            excelWriter.print(entityColumnInfo.getColumnAlias());
            excelWriter.print(entityColumnInfo.getColumnType());

            excelWriter.print(entityColumnInfo.getColumnLength());
            excelWriter.print(entityColumnInfo.getColumnPrec());
            excelWriter.print(entityColumnInfo.getColumnScale());

            excelWriter.print(entityColumnInfo.getColumnDesc());
            excelWriter.println(entityColumnInfo.getLinkMessage());

        }

        this.writeColumn(entityAnalysisInfo.getBaseEntityAnalysisInfo(),
                tableName, tableAlias);

    }

    public void writeFile(String filePath) throws IOException {
        excelWriter.writeFile(filePath);
    }
}
