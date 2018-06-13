package com.fan.exceltool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InitDescTool {

    private IExcelWriter excelWriter = null;

    public IExcelWriter getExcelWriter() {
        return excelWriter;
    }

    public void setExcelWriter(IExcelWriter excelWriter) {
        this.excelWriter = excelWriter;
    }

    public void initDesc() {

        excelWriter.print("制作人：");
        excelWriter.println("猫不急");
        excelWriter.print("Email：");
        excelWriter.println("16770864@qq.com");
        excelWriter.print("项目地址:");
        excelWriter.println("https://github.com/maobuji/DataDirTool");
        excelWriter.println("");
        excelWriter.println("本文件由EAS数据字典生成工具DataDirTool根据EAS服务器中的元数据生成.");
        excelWriter.println("生成工具DataDirTool可以向作者邮件索取或访问https://github.com/maobuji/DataDirTool获得！");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        excelWriter.println("文件生成时间:"+sdf.format(date));


    }

}
