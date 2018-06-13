package com.fan.exceltool;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

import com.fan.metastool.EntityAnalysisInfo;
import com.fan.metastool.EntityColumnInfo;
import org.apache.poi.ss.usermodel.CellStyle;

public class EntityToExcel {

    private IExcelWriter excelWriter = null;

    public IExcelWriter getExcelWriter() {
        return excelWriter;
    }

    public void setExcelWriter(IExcelWriter excelWriter) {
        this.excelWriter = excelWriter;
        excelWriter.print("表名",WriteType.DIR_HEAD);
        excelWriter.print("表别名",WriteType.DIR_HEAD);
        excelWriter.print("字段名",WriteType.DIR_HEAD);
        excelWriter.print("字段别名",WriteType.DIR_HEAD);
        excelWriter.print("字段类型",WriteType.DIR_HEAD);
        excelWriter.print("Length",WriteType.DIR_HEAD);
        excelWriter.print("Prec",WriteType.DIR_HEAD);
        excelWriter.print("Scale",WriteType.DIR_HEAD);
        excelWriter.print("字段描述",WriteType.DIR_HEAD);
        excelWriter.println("字段链接信息",WriteType.DIR_HEAD);
        excelWriter.println("");
    }

    public EntityToExcel() {

    }

    public void write(EntityAnalysisInfo entityAnalysisInfo) throws IOException {
        excelWriter.print(entityAnalysisInfo.getEntityTable(),WriteType.TABLE_HEAD);
        excelWriter.print(entityAnalysisInfo.getEntityAlias(),WriteType.TABLE_HEAD);
        excelWriter.print(entityAnalysisInfo.getEntityPackage(),WriteType.TABLE_HEAD);

        excelWriter.print("",WriteType.TABLE_HEAD);
        excelWriter.print("",WriteType.TABLE_HEAD);
        excelWriter.print("",WriteType.TABLE_HEAD);
        excelWriter.print("",WriteType.TABLE_HEAD);
        excelWriter.print("",WriteType.TABLE_HEAD);
        excelWriter.print("",WriteType.TABLE_HEAD);
        excelWriter.println("",WriteType.TABLE_HEAD);

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
