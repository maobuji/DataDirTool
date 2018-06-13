package com.fan.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class DataDirThread implements Runnable {

    CreateDataDirTool createDataDirTool = null;
    String path = "";

    List lsJButton = new ArrayList();
    JTextArea jTextArea=null;

    public void addLsJButton(JButton JButton) {
        this.lsJButton.add(JButton);
    }

    public void setJButtonEnable(boolean flag) {
        Iterator it = lsJButton.iterator();
        while (it.hasNext()) {
            JButton JButton = (JButton) it.next();
            JButton.setEnabled(flag);
        }
    }

    public DataDirThread(JTextArea jTextArea, String path) {
        createDataDirTool = new CreateDataDirTool();
        createDataDirTool.setJTextArea(jTextArea);
        this.path = path;
        this.jTextArea=jTextArea;
    }

    public void run() {
        try {
            setJButtonEnable(false);
            createDataDirTool.createDataDir(this.path);
        } catch (Exception e) {
            e.printStackTrace();
            jTextArea.append("/n"+e.getMessage());



        } finally {
            setJButtonEnable(true);
        }

    }

}
