/*
 * Copyright (c) 2020.
 */
package com.springboot.demo.swing;

import com.springboot.demo.swing.log.LoggerMessage;
import com.springboot.demo.swing.log.LoggerQueue;
import org.springframework.boot.SpringApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-07-22 17:29
 */
public class WinForm extends JFrame {
    private JPanel jPanelTop;
    private JPanel jPanelContent;

    private JButton jButton;
    private JTextArea jTextArea;

    public static void main(String[] args) {
        WinForm winForm = new WinForm();
    }

    public WinForm() throws HeadlessException {

        jPanelTop = new JPanel();
        jPanelContent = new JPanel();
        this.add(jPanelTop);
        this.add(jPanelContent);

        this.setLocationRelativeTo(null);
        //设置窗口置顶显示
        this.setAlwaysOnTop(true);
        //设置窗口显示
        this.setVisible(true);

        //对窗口设置标题
        this.setTitle("我的第一个Java窗口应用");
        //设置窗口的大小
        this.setSize(900, 220);
        //设置窗口界面的关闭按钮真的生效(也可以直接传一个3进去，JFrame.EXIT_ON_CLOSE==3，效果一样)
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        jButton = new JButton("启动程序");
        jButton.setSize(120, 30);
        this.add(jButton);

        jTextArea = new JTextArea("");
        jTextArea.setBounds(0, 30, 900, 100);
        this.add(jTextArea);

        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                sendLog();
                SpringApplication.run(ApplicationSwing.class);

            }
        });


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(WinForm.this, "系统正在运行，确定退出? ", "提示 ", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    if (e.getWindow() == WinForm.this) {
                        System.exit(0);
                    } else {
                        return;
                    }
                }
            }
        });
    }

    private void sendLog() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        LoggerMessage log = LoggerQueue.getInstance().poll();
                        jTextArea.append(log.toString() + "\r\n");
//                        System.out.println(log);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executorService.submit(runnable);
    }
}
