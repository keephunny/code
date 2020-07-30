/*
 * Copyright (c) 2020.
 */
package com.spring.project.demo.other;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-07-22 11:09
 */
public class WindowsForm extends JFrame {

    private JPanel jPanelTop;
    private JPanel jPanelCentent;

    private JButton jButton;
    private TextArea textArea;

    private JLabel jLabel1;
    private JLabel jLabel2;

    public WindowsForm() throws HeadlessException {
//        setLayout(null);

        jPanelTop = new JPanel();
        jPanelCentent = new JPanel();
        jPanelCentent.setLayout(new BorderLayout(0, 0));
        this.add(jPanelTop, BorderLayout.NORTH);
        this.add(jPanelCentent, BorderLayout.CENTER);

        jLabel1 = new JLabel("启动时间：");
        jLabel1.setSize(120, 25);
        jPanelTop.add(jLabel1);
        jLabel2 = new JLabel("累计运行时间：");
        jLabel2.setSize(120, 25);
        jPanelTop.add(jLabel2);

        jButton = new JButton("启动程序");
        jButton.setBounds(0, 0, 80, 25);
        jPanelTop.add(jButton);

        textArea = new TextArea();
        textArea.setRows(1000);
        textArea.setBackground(new Color(-16777216));
        textArea.setForeground(new Color(-1));
        jPanelCentent.add(textArea, BorderLayout.CENTER);


        setSize(100, 200);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(WindowsForm.this, "系统正在运行，确定退出? ", "提示 ", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    if (e.getWindow() == WindowsForm.this) {
                        System.exit(0);
                    } else {
                        return;
                    }
                }
            }
        });

    }
}
