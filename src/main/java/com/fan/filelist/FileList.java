package com.fan.filelist;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileList {

    private String filePath = "";
    public final static String FILE_FLAG = ".";
    public final static String EXT_FLAG = ",";
    private String[] extArray = new String[] {};

    private List fileList = new ArrayList();

    public FileList(String path) {
        filePath = path;
    }

    public void find(String ext) {
        fileList.clear();
        setFileExt(ext);
        seachFile(filePath);
    }

    private void setFileExt(String ext) {
        if (ext == null || "".equals(ext)) {
            extArray = new String[] {};
        } else {
            extArray = ext.split(EXT_FLAG);
        }
    }

    private void seachFile(String path) {
        File f = new File(path);
        if (!f.exists()) {
            return;
        }

        if (f.isDirectory()) {
            File[] flist = f.listFiles();
            for (int i = 0; i < flist.length; i++) {
                seachFile(flist[i].getAbsolutePath());
            }
        } else if (f.isFile()) {
            addFile(f);
        } else {

        }

    }

    private void addFile(File f) {
        String fName = f.getName();

        for (int i = 0; i < extArray.length; i++) {
            String ext = extArray[i];
            if (fName.lastIndexOf(FILE_FLAG + ext) > 0) {
                fileList.add(f.getAbsolutePath());
            }

        }

    }

    public List getFileList() {
        return fileList;
    }

    public void print() {

        Iterator it = fileList.iterator();

        while (it.hasNext()) {
            String object = (String) it.next();
            System.out.println(object);

        }
    }

}
