package com.fan.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import com.fan.exceltool.ExcelWriterFactory;
import com.fan.exceltool.WriteType;
import com.fan.version.VersionMessage;
import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import com.fan.ui.menu.DataDirMenu;

public class CreateDataDirUI extends JFrame {

    private JTextArea jTextArea0 = null;
    private JScrollPane jScrollPane0 = null;
    private JTextField textField = null;
    JButton buttonDir = null;
    JButton buttonCreate = null;
    private JCheckBox jcbExcelType = null;

    public CreateDataDirUI() {
        super();
        this.setSize(500, 400);
        setLocation(300, 150);
        this.setJMenuBar(new DataDirMenu());
        this.setTitle(VersionMessage.PRODUCT_NAME_CN + VersionMessage.VERSION);

        setLayout(new GroupLayout());

        add(dirSelectJTextField(), new Constraints(new Bilateral(14, 250, 4),
                new Leading(12, 12, 12)));

        add(getDirJButton(), new Constraints(new Trailing(150, 10, 234),
                new Leading(6, 12, 12)));
        add(getCreateJButton(), new Constraints(new Trailing(60, 10, 234),
                new Leading(6, 12, 12)));

        add(getExcelTypeJCheckBox(), new Constraints(new Trailing(5, 10, 234),
                new Leading(6, 12, 12)));

        add(getJScrollPane0(), new Constraints(new Bilateral(11, 5, 22),
                new Bilateral(43, 12, 22)));

    }

    private JCheckBox getExcelTypeJCheckBox() {
        jcbExcelType = new JCheckBox("xlsx");
        jcbExcelType.setSelected(true);
        return jcbExcelType;
    }

    private JButton getCreateJButton() {
        if (buttonCreate == null) {
            buttonCreate = new JButton("生成字典");
            buttonCreate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jTextArea0.setText("");
                    buttonCreate.setEnabled(false);
                    String path = dirSelectJTextField().getText();

                    String excelType= ExcelWriterFactory.XLS_TYPE;
                    if(jcbExcelType.isSelected()){
                        excelType=ExcelWriterFactory.XLSX_TYPE;
                    }

                    DataDirThread dataDirThread = new DataDirThread(jTextArea0, path,excelType);
                    dataDirThread.addLsJButton(buttonCreate);
                    dataDirThread.addLsJButton(buttonDir);
                    Thread t = new Thread(dataDirThread);
                    t.start();

                }
            });

        }
        return buttonCreate;
    }

    public JTextField dirSelectJTextField() {
        if (textField == null) {
            textField = new JTextField("请选择/eas/server/mates目录！");
            textField.setEditable(false);
        }

        return textField;
    }


    private JButton getDirJButton() {
        if (buttonDir == null) {
            buttonDir = new JButton("选择目录");
            buttonDir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    File f=new File("");
                    JFileChooser chooser = new JFileChooser(f.getAbsolutePath());
                    chooser.setFileFilter(getFileFilter());
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    if (chooser.showOpenDialog(new JPanel()) == JFileChooser.APPROVE_OPTION) {
                        String dir = chooser.getSelectedFile()
                                .getAbsolutePath();
                        dirSelectJTextField().setText(dir);
                    }
                }
            });
        }
        return buttonDir;
    }

    private FileFilter getFileFilter() {
        FileFilter dirFilter = new FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory();
            }

            public String getDescription() {
                return "";
            }
        };

        return dirFilter;

    }

    private JTextArea getJTextArea0() {
        if (jTextArea0 == null) {
            jTextArea0 = new JTextArea();
            jTextArea0.setText("");
            jTextArea0.setLineWrap(true);

            jTextArea0.append("制作人：猫不急\n");
            jTextArea0.append("Email：16770864@qq.com\n");
            jTextArea0.append("项目地址:https://github.com/maobuji/DataDirTool\n");
        }
        return jTextArea0;
    }

    private JScrollPane getJScrollPane0() {
        if (jScrollPane0 == null) {
            jScrollPane0 = new JScrollPane();
            jScrollPane0.setViewportView(getJTextArea0());
        }
        return jScrollPane0;


    }

}
