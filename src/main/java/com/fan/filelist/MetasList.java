package com.fan.filelist;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MetasList {

    private Map matesMap = new HashMap();
    public final static String EXT_FLAG = ",";
    public final static String FILE_FLAG = ".";
    private String[] extArray = new String[] {};

    public void setMatesExt(String ext) {
        if (ext == null || "".equals(ext)) {
            extArray = new String[] {};
        } else {
            extArray = ext.split(EXT_FLAG);
        }
    }

    public void addMatesJar(List addMatesJar) {
        if (addMatesJar == null)
            return;
        Iterator it = addMatesJar.iterator();

        while (it.hasNext()) {
            String s = (String) it.next();
            addMatesJar(s);
        }

    }

    public void addMatesJar(String path) {
        JarFile jarFile;
        try {
            jarFile = new JarFile(path);

            Enumeration enum1 = jarFile.entries();
            while (enum1.hasMoreElements()) {
                JarEntry entry = (JarEntry) enum1.nextElement();

                addMates(path,entry);

            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    private void addMates(String path,JarEntry entry) {

        String fName = entry.getName();
        for (int i = 0; i < extArray.length; i++) {
            String ext = extArray[i];
            if (fName.lastIndexOf(FILE_FLAG + ext) > 0) {
                // InputStream input = jarFile.getInputStream(entry);
                //fName = fName.replaceAll("/", ".");
                MetasJarBean matesJarBean=new MetasJarBean();
                matesJarBean.setJarPath(path);
                matesJarBean.setClassString(fName);
                matesMap.put(fName, matesJarBean);
            }
        }

    }

    public Map getMatesMap() {
        return matesMap;
    }

}
