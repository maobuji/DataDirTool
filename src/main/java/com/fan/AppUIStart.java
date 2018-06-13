package com.fan;

import com.fan.ui.CreateDataDirUI;

import javax.swing.*;

/**
 * Created by Administrator on 2018/6/13.
 */
public class AppUIStart {

    /**
     * @param args
     */
    public static void main(String[] args) {
        CreateDataDirUI createDataDirUI = new CreateDataDirUI();
        createDataDirUI.setVisible(true);
        createDataDirUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
}
