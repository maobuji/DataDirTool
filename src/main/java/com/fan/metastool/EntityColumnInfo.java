package com.fan.metastool;

public class EntityColumnInfo {

    // 字段名称
    private String columnName = "";

    // 字段别名
    private String columnAlias = "";

    // 字段备注
    private String columnDesc = "";

    // 字段类型
    private String columnType = "";

    // 表字段名
    private String columnField = "";

    // 字段长度
    private String columnLength = "";

    // 字段
    private String columnPrec = "";

    // 小数精度
    private String columnScale = "";

    // 链接信息如果是连接属性，则显示表名。如果是枚举属性，则显示枚举列表
    private String linkMessage="";

    public String getLinkMessage() {
        return linkMessage;
    }

    public void setLinkMessage(String linkMessage) {
        this.linkMessage = linkMessage;
    }

    public String getColumnField() {
        return columnField;
    }

    public void setColumnField(String columnField) {
        this.columnField = columnField;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnAlias() {
        return columnAlias;
    }

    public void setColumnAlias(String columnAlias) {
        this.columnAlias = columnAlias;
    }

    public String getColumnDesc() {
        return columnDesc;
    }

    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnLength() {
        return columnLength;
    }

    public void setColumnLength(String columnLength) {
        this.columnLength = columnLength;
    }

    public String getColumnPrec() {
        return columnPrec;
    }

    public void setColumnPrec(String columnPrec) {
        this.columnPrec = columnPrec;
    }

    public String getColumnScale() {
        return columnScale;
    }

    public void setColumnScale(String columnScale) {
        this.columnScale = columnScale;
    }

}
