package com.fan.exceltool;

public class InitDescTool {

    private ExcelWriter excelWriter = null;

    public ExcelWriter getExcelWriter() {
        return excelWriter;
    }

    public void setExcelWriter(ExcelWriter excelWriter) {
        this.excelWriter = excelWriter;
    }

    public void initDesc() {

        excelWriter.print("制作人：");
        excelWriter.println("猫不急");
        excelWriter.print("Email：");
        excelWriter.println("16770864@qq.com");
        excelWriter.println("");
        excelWriter.println("");

        excelWriter.println("说明：EAS数据字典生成工具生成.生成工具可以根据服务器的补丁的生成对应的字典文件。工具可以向作者邮件索取！");


    }

}
