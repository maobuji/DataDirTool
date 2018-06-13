package com.fan.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

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

    public CreateDataDirUI() {
        super();
        this.setSize(500, 400);
        setLocation(300, 150);
        this.setJMenuBar(new DataDirMenu());
        this.setTitle("数据字典生成工具-猫不急");

        setLayout(new GroupLayout());
        add(getDirJButton(), new Constraints(new Trailing(100, 10, 234),
                new Leading(6, 12, 12)));
        add(getCreateJButton(), new Constraints(new Trailing(5, 10, 234),
                new Leading(6, 12, 12)));
        add(dirSelectJTextField(), new Constraints(new Bilateral(14, 200, 4),
                new Leading(12, 12, 12)));
        add(getJScrollPane0(), new Constraints(new Bilateral(11, 5, 22),
                new Bilateral(43, 12, 22)));

    }

    private JButton getCreateJButton() {
        if (buttonCreate == null) {
            buttonCreate = new JButton("生成字典");
            buttonCreate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jTextArea0.setText("");
                    buttonCreate.setEnabled(false);
                    String path=dirSelectJTextField().getText();

                    DataDirThread  dataDirThread=new DataDirThread(jTextArea0,path);
                    dataDirThread.addLsJButton(buttonCreate);
                    dataDirThread.addLsJButton(buttonDir);
                    Thread t=new Thread (dataDirThread);
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
                    JFileChooser chooser = new JFileChooser();
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
