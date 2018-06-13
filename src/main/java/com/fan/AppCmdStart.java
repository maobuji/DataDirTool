package com.fan;

import java.io.IOException;
import java.util.Arrays;

import com.fan.exceltool.ExcelWriterFactory;
import com.fan.exceltool.IExcelWriter;
import org.dom4j.DocumentException;

import com.fan.exceltool.EntityToExcel;
import com.fan.exceltool.InitDescTool;
import com.fan.filelist.FileList;
import com.fan.filelist.MetasList;
import com.fan.metastool.EntityAnalysisInfo;
import com.fan.metastool.EntityAnalysisTool;

public class AppCmdStart {

    /**
     * @param args
     * @throws IOException
     * @throws DocumentException
     */
    public static void main(String[] args) throws Exception,
            DocumentException {

        // 获取Metas目录
        String path = "c:\\metas";

        FileList fileList = new FileList(path);
        fileList.find("jar");

        MetasList matesList = new MetasList();
        matesList.setMatesExt("entity,relation,enum");
        matesList.addMatesJar(fileList.getFileList());

        EntityAnalysisTool entityAnalysisTool = new EntityAnalysisTool(
                matesList.getMatesMap());

        IExcelWriter excelWriter = ExcelWriterFactory.getExcelWriter(ExcelWriterFactory.XLS_TYPE);
        InitDescTool initDescTool = new InitDescTool();
        excelWriter.createSheet("说明");
        initDescTool.setExcelWriter(excelWriter);
        initDescTool.initDesc();


        EntityToExcel entityToExcel = new EntityToExcel();


        excelWriter.createSheet("EAS数据字典");
        entityToExcel.setExcelWriter(excelWriter);
        String[] keys = (String[]) matesList.getMatesMap().keySet().toArray(
                new String[]{});
        Arrays.sort(keys);
        for (int i = 0; i < keys.length; i++) {
            if (!keys[i].endsWith(".entity")) {
                continue;
            }
            EntityAnalysisInfo entityAnalysisInfo = entityAnalysisTool
                    .find(keys[i]);
            entityToExcel.write(entityAnalysisInfo);
            System.out.println(keys[i] + "(" + i + "-" + keys.length + ")");
        }
        entityToExcel.writeFile("600DataDirectory.xls");
        // System.out.println(rootElm.asXML());
    }
}
