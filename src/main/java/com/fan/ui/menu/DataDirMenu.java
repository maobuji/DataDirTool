package com.fan.ui.menu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DataDirMenu extends JMenuBar {

    private JDialog aboutDialog;

    public DataDirMenu() {
        JMenu fileMenu = new JMenu("文件");
        JMenuItem exitMenuItem = new JMenuItem("退出", KeyEvent.VK_E);
        JMenuItem aboutMenuItem = new JMenuItem("关于..", KeyEvent.VK_A);
        fileMenu.add(exitMenuItem);
        fileMenu.add(aboutMenuItem);
        this.add(fileMenu);
        aboutDialog = new JDialog();
        initAboutDialog();

        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // dispose();
                System.exit(0);
            }
        });
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aboutDialog.show();
            }
        });
    }

    public JDialog get() {
        return aboutDialog;
    }

    public void initAboutDialog() {
        aboutDialog.setTitle("关于");
        Container con = aboutDialog.getContentPane();
        Icon icon = new ImageIcon("sdmile.gif");

        String showMessage="制作人:猫不急 <br>  " +
                "Email:16770864@qq.com" + "<br>"+
                "项目地址:https://github.com/maobuji/DataDirTool";

        String about = "<html><b><font size=5>" +showMessage+
                "</font></b></html>";


        JLabel aboutLabel = new JLabel(about, icon, JLabel.CENTER);
        con.add(aboutLabel, BorderLayout.CENTER);
        aboutDialog.setSize(500, 225);
        aboutDialog.setLocation(300, 300);
        aboutDialog.addWindowListener(new WindowAdapter() {
            public void WindowClosing(WindowEvent e) {
                // dispose();
            }
        });

    }
}
