package com.fan.metastool;

import java.util.ArrayList;
import java.util.List;

public class EntityAnalysisInfo {

    private EntityAnalysisInfo baseEntityAnalysisInfo = null;

    private String entityName = "";
    private String entityPackage = "";
    private String entityAlias = "";
    private String entityDesc = "";
    private String entityTable = "";

    private List columns = new ArrayList();

    public EntityAnalysisInfo getBaseEntityAnalysisInfo() {
        return baseEntityAnalysisInfo;
    }

    public void setBaseEntityAnalysisInfo(
            EntityAnalysisInfo baseEntityAnalysisInfo) {
        this.baseEntityAnalysisInfo = baseEntityAnalysisInfo;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public List getColumns() {
        return columns;
    }

    public void setColumns(List columns) {
        this.columns = columns;
    }

    public String getEntityAlias() {
        return entityAlias;
    }

    public void setEntityAlias(String entiryAlias) {
        this.entityAlias = entiryAlias;
    }

    public String getEntityDesc() {
        return entityDesc;
    }

    public void setEntityDesc(String entityDesc) {
        this.entityDesc = entityDesc;
    }

    public String getEntityTable() {
        return entityTable;
    }

    public void setEntityTable(String entityTable) {
        this.entityTable = entityTable;
    }

}
