package com.fan.ui;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.fan.exceltool.ExcelWriterFactory;
import com.fan.exceltool.IExcelWriter;
import org.dom4j.DocumentException;

import com.fan.exceltool.EntityToExcel;
import com.fan.exceltool.InitDescTool;
import com.fan.filelist.FileList;
import com.fan.filelist.MetasList;
import com.fan.metastool.EntityAnalysisInfo;
import com.fan.metastool.EntityAnalysisTool;

public class CreateDataDirTool {

    private JTextArea JTextArea = null;

    public JTextArea getJTextArea() {
        return JTextArea;
    }

    public void setJTextArea(JTextArea jTextArea) {
        JTextArea = jTextArea;
    }

    public void createDataDir(String path,String excelType) throws IOException,
            DocumentException {
        IExcelWriter excelWriter = null;

        try {
            excelWriter = ExcelWriterFactory.getExcelWriter(excelType);
        } catch (Exception ex) {
            JTextArea.append(ex.getMessage());
            return;
        }

        JTextArea.append("开始分析元数据文件" + "\n");

        InitDescTool initDescTool = new InitDescTool();
        excelWriter.createSheet("说明");
        initDescTool.setExcelWriter(excelWriter);
        initDescTool.initDesc();

        EntityToExcel entityToExcel = new EntityToExcel();
        excelWriter.createSheet("EAS数据字典");
        entityToExcel.setExcelWriter(excelWriter);


        FileList fileList = new FileList(path);
        fileList.find("jar");

        MetasList matesList = new MetasList();
        matesList.setMatesExt("entity,relation,enum");
        matesList.addMatesJar(fileList.getFileList());

        EntityAnalysisTool entityAnalysisTool = new EntityAnalysisTool(
                matesList.getMatesMap());
        String[] keys = entityAnalysisTool.getKeys();



        for (int i = 0; i < keys.length; i++) {
            if (!keys[i].endsWith(".entity")) {
                continue;
            }

            String outPut = keys[i] + "(" + (i + 1) + "-" + keys.length + ")";
            System.out.println(outPut);
            JTextArea.append(outPut + "\n");
            JTextArea.setCaretPosition(JTextArea.getText().length());
            JTextArea.paintImmediately(JTextArea.getBounds());

            try {
                EntityAnalysisInfo entityAnalysisInfo = entityAnalysisTool
                        .find(keys[i]);
                entityToExcel.write(entityAnalysisInfo);
            }catch(Exception ex){
                ex.printStackTrace();
                String errorMessage="该entity解析失败:"+ex.getMessage();
                System.out.println(errorMessage);
                JTextArea.append(errorMessage + "\n");
                JTextArea.setCaretPosition(JTextArea.getText().length());
                JTextArea.paintImmediately(JTextArea.getBounds());
            }
        }
        JTextArea.append("开始生成字典文件" + "\n");

        if (ExcelWriterFactory.XLSX_TYPE.equals(excelType)) {
            entityToExcel.writeFile("DataDirectory.xlsx");
        } else {
            entityToExcel.writeFile("DataDirectory.xls");
        }
        // System.out.println(rootElm.asXML());
        JTextArea.append("字典文件生成完毕" + "\n");
        JOptionPane.showMessageDialog(null, "字典文件生成完毕");

    }

}
