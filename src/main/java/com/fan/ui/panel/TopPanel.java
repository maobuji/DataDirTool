package com.fan.ui.panel;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TopPanel extends JPanel {

    public TopPanel() {


        this.setLayout(new GridLayout(0, 8,12,80));
        JTextField textField = new JTextField("请选择/eas/server/mates目录！");
        JButton button = new JButton("打开文件");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx = 0;
        gbc.ipady = 0;
        this.add(textField,gbc);
        this.add(button);


//		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY,
//				Color.blue));
//
//		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
//		JTextField textField = new JTextField("文本域，点击打开<文件按钮>可选择文件");
//		textField.setActionCommand("textField");
//		JTextPane textPane = new JTextPane();
//		textPane.setCursor(new Cursor(Cursor.TEXT_CURSOR));
//		textPane.setText("编辑器，试着点击文本区，试着拉动分隔条。");
//
//		textPane.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				JTextPane textPane = (JTextPane) e.getSource();
//				textPane.setText("编辑器点击命令成功");
//			}
//		});
//		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
//				textField, textPane);
//		JTable table = new JTable(10, 10);
//		JPanel pane = new JPanel();
//		pane.add(table.getTableHeader(), BorderLayout.NORTH);
//		pane.add(table);
//		tab.addTab("文本演示", splitPane);
//		tab.addTab("表格演示", pane);
//		tab.setPreferredSize(new Dimension(500, 600));
//		this.add(tab);
        this.setEnabled(true);
    }
}
