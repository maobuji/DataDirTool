package com.fan.metastool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fan.filelist.MetasJarBean;

public class EntityAnalysisTool {

    // 存储元数据的jar信息与实体路径
    Map matesJarBeanMap = new HashMap();

    // 存储实体元数据表信息
    Map tableEntityMap = new HashMap();

    // 存储枚举信息
    Map menuMap = new HashMap();

    String[] keys = null;

    public String[] getKeys() {
        return keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public EntityAnalysisTool(Map entityMap) {
        if (entityMap == null) {
            throw new NullPointerException();
        }
        matesJarBeanMap = entityMap;

        // 获取所有keys，并存储
        String[] keys = (String[]) matesJarBeanMap.keySet().toArray(
                new String[] {});
        Arrays.sort(keys);
        this.keys = keys;

        // 获取所有实体的表信息，用于做引用
        createTableEntityMap();
        // 获取所有枚举实体
        createMenuMap();
    }

    private void createTableEntityMap() {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].endsWith(".entity")) {
                try {
                    EntityAnalysisInfo entityAnalysisInfo = findTableMessage(keys[i]);
                    tableEntityMap.put(keys[i], entityAnalysisInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createMenuMap() {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].endsWith(".enum")) {
                try {
                    MenuMetasInfo menuMetasInfo = getMenuMetasInfo(keys[i]);
                    if (menuMetasInfo != null) {
                        menuMap.put(keys[i], menuMetasInfo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public MenuMetasInfo getMenuMetasInfo(String menuMetas) throws Exception {
        MenuMetasInfo menuMetasInfo = new MenuMetasInfo();
        Element rootElm = readEntityFile(menuMetas);
        menuMetasInfo
                .setEnumDataType(rootElm.element("enumDataType").getText());

        if (rootElm.element("enumValues") != null) {
            Iterator it = rootElm.element("enumValues").elementIterator();
            while (it.hasNext()) {
                Element element = (Element) it.next();

                MenuMetasItemInfo menuMetasItemInfo = new MenuMetasItemInfo();
                menuMetasItemInfo.setName(element.element("name").getText());

                String alias = getResource(rootElm, element.element("alias")
                        .getText());
                menuMetasItemInfo.setAlias(alias);
                menuMetasItemInfo.setValue(element.element("value").getText());
                menuMetasInfo.addMenuItem(menuMetasItemInfo);
            }
        }
        return menuMetasInfo;
    }

    public EntityAnalysisInfo find(String entity) throws IOException,
            DocumentException {
        EntityAnalysisInfo entityAnalysisInfo = new EntityAnalysisInfo();
        Element rootElm = readEntityFile(entity);
        setInfo(rootElm, entityAnalysisInfo);
        setColumn(rootElm, entityAnalysisInfo);
        setBase(rootElm, entityAnalysisInfo);

        return entityAnalysisInfo;
    }

    public EntityAnalysisInfo findTableMessage(String entity)
            throws IOException, DocumentException {
        EntityAnalysisInfo entityAnalysisInfo = new EntityAnalysisInfo();
        Element rootElm = readEntityFile(entity);
        setInfo(rootElm, entityAnalysisInfo);
        return entityAnalysisInfo;
    }

    private Element readEntityFile(String metasPath) throws IOException,
            DocumentException {
        MetasJarBean matesJarBean = (MetasJarBean) matesJarBeanMap
                .get(metasPath);

        JarFile jarFile = new JarFile(matesJarBean.getJarPath());
        JarEntry JarEntry = (JarEntry) jarFile.getJarEntry(matesJarBean
                .getClassString());

        InputStream input = jarFile.getInputStream(JarEntry);
        SAXReader reader = new SAXReader();
        Document document = reader.read(input);
        Element rootElm = document.getRootElement();

        return rootElm;
    }

    private void setInfo(Element rootElm, EntityAnalysisInfo entityAnalysisInfo) {

        entityAnalysisInfo.setEntityName(rootElm.element("name").getText());
        entityAnalysisInfo.setEntityPackage(rootElm.element("package")
                .getText());

        entityAnalysisInfo.setEntityAlias(this.getResource(rootElm, rootElm
                .element("alias").getText()));
        entityAnalysisInfo.setEntityDesc(this.getResource(rootElm, rootElm
                .element("description").getText()));

        if (rootElm.element("table") != null) {
            Iterator it = rootElm.element("table").elementIterator();
            while (it.hasNext()) {
                Element object = (Element) it.next();
                if ("name".equals(object.attribute("name").getText())) {

                    entityAnalysisInfo.setEntityTable(object.attribute("value")
                            .getText());

                }

            }
        }

    }

    private String getResource(Element rootElm, String key) {

        String value = "";
        Iterator it = rootElm.element("resource").elementIterator();
        while (it.hasNext()) {
            Element rsElement = (Element) it.next();
            if (key.equals(rsElement.attribute("key").getText())) {
                Iterator itrs = rsElement.elementIterator();
                while (itrs.hasNext()) {
                    Element langElement = (Element) itrs.next();

                    if ("zh_CN".equals(langElement.attribute("locale")
                            .getText())) {
                        value = langElement.attribute("value").getText();
                        return value;
                    }

                }

            }

        }

        return value;
    }

    private void setColumn(Element rootElm,
                           EntityAnalysisInfo entityAnalysisInfo) {

        if (rootElm.element("properties") == null) {
            return;
        }
        Iterator it = rootElm.element("properties").elementIterator();
        while (it.hasNext()) {
            Element propertiesElement = (Element) it.next();
            // 如果是逻辑键则跳过
            if (propertiesElement.getName().equals("logicalKey")) {
                continue;
            }

            EntityColumnInfo entityColumnInfo = new EntityColumnInfo();
            entityColumnInfo.setColumnName(propertiesElement.element("name")
                    .getText());
            entityColumnInfo.setColumnAlias(this.getResource(rootElm,
                    propertiesElement.element("alias").getText()));

            entityColumnInfo.setColumnDesc(this.getResource(rootElm,
                    propertiesElement.element("description").getText()));

            if (propertiesElement.element("dataType") != null) {
                entityColumnInfo.setColumnType(propertiesElement.element(
                        "dataType").getText());
            }

            if (propertiesElement.element("mappingField") != null) {
                Iterator itField = propertiesElement.element("mappingField")
                        .elementIterator();
                while (itField.hasNext()) {
                    Element fieldElement = (Element) itField.next();
                    if ("name".equals(fieldElement.attribute("name").getText())) {
                        entityColumnInfo.setColumnField(fieldElement.attribute(
                                "value").getText());
                    }
                }
            }

            if (propertiesElement.element("length") != null) {
                entityColumnInfo.setColumnLength(propertiesElement.element(
                        "length").getText());
            }

            if (propertiesElement.element("precision") != null) {
                entityColumnInfo.setColumnPrec(propertiesElement.element(
                        "precision").getText());
            }

            if (propertiesElement.element("decimalDigits") != null) {
                entityColumnInfo.setColumnScale(propertiesElement.element(
                        "decimalDigits").getText());
            }

            // 如果是链接属性，则获取对应的表信息
            if (propertiesElement.getName().equals("linkProperty")) {
                entityColumnInfo
                        .setLinkMessage(getLinkTableMessage(propertiesElement));
            }

            if ("Enum".equals(entityColumnInfo.getColumnType())) {
                String packageString = propertiesElement.element("metadataRef")
                        .getText();

                String enumString = packageString.replaceAll("\\.", "/")
                        + ".enum";

                MenuMetasInfo menuMetasInfo = (MenuMetasInfo) this.menuMap
                        .get(enumString);

                if (menuMetasInfo != null) {
                    entityColumnInfo.setLinkMessage(menuMetasInfo
                            .getMenuString());
                }
            }

            entityAnalysisInfo.getColumns().add(entityColumnInfo);

        }

    }

    /**
     * 获取链接信息
     *
     * @param propertiesElement
     * @return
     */
    private String getLinkTableMessage(Element propertiesElement) {
        String packageString = "";
        String fileString = "";
        Iterator it = propertiesElement.element("relationship")
                .elementIterator();
        while (it.hasNext()) {
            Element object = (Element) it.next();
            if ("package".equals(object.attribute("name").getText())) {
                packageString = object.attribute("value").getText();
            }

            if ("name".equals(object.attribute("name").getText())) {
                fileString = object.attribute("value").getText();
            }
        }

        // 拼合relation链接，如果能够找到就进行解析，找到对应表。如果找不到就返回链接信息
        String relationString = packageString.replaceAll("\\.", "/") + "/"
                + fileString + ".relation";
        try {
            String entityString = getEntityByRelation(relationString);
            if (entityString != null) {
                EntityAnalysisInfo entityAnalysisInfo = (EntityAnalysisInfo) tableEntityMap
                        .get(entityString);
                if (entityAnalysisInfo != null) {
                    return entityAnalysisInfo.getEntityTable();
                }

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }

    private String getEntityByRelation(String relationString)
            throws IOException, DocumentException {
        if (matesJarBeanMap.get(relationString) != null) {
            MetasJarBean matesJarBean = (MetasJarBean) matesJarBeanMap
                    .get(relationString);
            JarFile jarFile = new JarFile(matesJarBean.getJarPath());
            JarEntry JarEntry = (JarEntry) jarFile.getJarEntry(matesJarBean
                    .getClassString());

            InputStream input = jarFile.getInputStream(JarEntry);
            SAXReader reader = new SAXReader();
            Document document = reader.read(input);
            Element rootElm = document.getRootElement();
            Iterator it = rootElm.element("supplierObject").elementIterator();

            String packageString = "";
            String fileString = "";
            while (it.hasNext()) {
                Element object = (Element) it.next();
                if ("package".equals(object.attribute("name").getText())) {
                    packageString = object.attribute("value").getText();
                }

                if ("name".equals(object.attribute("name").getText())) {
                    fileString = object.attribute("value").getText();
                }
            }
            String entityString = packageString.replaceAll("\\.", "/") + "/"
                    + fileString + ".entity";
            return entityString;
        } else {
            return null;
        }
    }

    private void setBase(Element rootElm, EntityAnalysisInfo entityAnalysisInfo)
            throws IOException, DocumentException {

        String packageString = "";
        String fileString = "";

        if (rootElm.element("baseEntity") == null) {
            return;
        }

        Iterator it = rootElm.element("baseEntity").elements().iterator();
        while (it.hasNext()) {
            Element object = (Element) it.next();
            if ("package".equals(object.attribute("name").getValue())) {
                packageString = object.attribute("value").getValue();
                packageString = packageString.replaceAll("\\.", "/");
            }

            if ("name".equals(object.attribute("name").getValue())) {
                fileString = object.attribute("value").getValue();
                fileString = fileString + ".entity";
            }

        }
        entityAnalysisInfo.setBaseEntityAnalysisInfo(this.find(packageString
                + "/" + fileString));

    }

}
