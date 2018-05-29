package com.fan.metastool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuMetasInfo {

    private String enumDataType = "";

    public String getEnumDataType() {
        return enumDataType;
    }

    public void setEnumDataType(String enumDataType) {
        this.enumDataType = enumDataType;
    }

    List menuItems = new ArrayList();



    public void addMenuItem(MenuMetasItemInfo menuMetasItemInfo) {
        this.menuItems.add(menuMetasItemInfo);
    }



    public String getMenuString(){
        String message="数据类型:"+enumDataType;
        Iterator it= menuItems.iterator();
        while (it.hasNext()) {
            MenuMetasItemInfo menuMetasItemInfo = (MenuMetasItemInfo) it.next();
            message=message+" "+menuMetasItemInfo.getValue()+":"+menuMetasItemInfo.getAlias();

        }
        return message;

    }

}
