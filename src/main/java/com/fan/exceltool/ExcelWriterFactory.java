package com.fan.exceltool;

import com.fan.exceltool.impl.XlsExcelWriter;
import com.fan.exceltool.impl.XlsxExcelWriter;

/**
 * Created by Administrator on 2018/6/13.
 */
public class ExcelWriterFactory {

    public final static String XLS_TYPE = "xls";

    public final static String XLSX_TYPE = "xlsx";


    /**
     * 制作一个工厂，使excelWrite可以根据需要改变
     * <p>
     * 目前考虑支持xls和xlsx两种type
     *
     * @param type
     * @return
     */
    public static IExcelWriter getExcelWriter(String type) throws Exception {

        if (XLSX_TYPE.equals(type)) {
            return new XlsxExcelWriter();
        } else if (XLS_TYPE.equals(type)) {
            return new XlsExcelWriter();
        }

        throw new Exception("不支持的文件类型:" + type);
    }
}
