/*
 * Copyright (c) 2020.
 */
package com.springboot.demo.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-07-23 17:10
 */
public class Winform2 extends JFrame {
    private final Logger logger = LoggerFactory.getLogger(Winform2.class);

    private JPanel panel1;
    private JTextArea textArea1;
    private JCheckBox checkBoxWrap;
    private JButton buttonStart;
    private JButton buttonStop;
    private JLabel labelStartTime;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private boolean WatchLogFileStatus = true;
    private String BasePath = "E:\\application\\proj-xuzhou-web-api\\bin\\";

    public static void main(String[] args) {
        Winform2 winform2 = new Winform2();
//        JPanel panelContent=winform2.panel1;
//        winform2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        winform2.setContentPane(panelContent );
//        winform2.pack();
//        winform2.setVisible(true);
//        winform2.setSize(800, 400);
//
//        //窗体居中
//        winform2.setLocationRelativeTo(null);
////        //设置窗口置顶显示
//        winform2.setAlwaysOnTop(true);
    }

    public Winform2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        pack();
        setVisible(true);
        setSize(800, 400);
        //窗体居中
        setLocationRelativeTo(null);
        //设置窗口置顶显示
        setAlwaysOnTop(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


        buttonStop.setEnabled(false);
        buttonStart.setEnabled(true);
        labelStartTime.setText("              ");

        //启动服务
        buttonStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonStop.setEnabled(true);
                buttonStart.setEnabled(false);
                String startBat = BasePath + "startup.bat";
                textArea1.append("程序启动中……\r\n");

                RunCmdRunnable runCmdRunnable = new RunCmdRunnable(startBat, false);
                Thread thread2 = new Thread(runCmdRunnable, "tt2");
                thread2.start();
//                if (WatchLogFileStatus) {
//                    WatchLogFile watchLog = new WatchLogFile();
//                    Thread t1 = new Thread(watchLog, "tt1");
//                    t1.start();
//                    WatchLogFileStatus = false;
//                }
//                callCmd(startBat);

                labelStartTime.setText(simpleDateFormat.format(new Date()));
                super.mouseClicked(e);
            }
        });
        //停止服务
        buttonStop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);
                closeApp(false);
            }
        });
        checkBoxWrap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkBoxWrap.isSelected()) {
                    textArea1.setLineWrap(true);
                } else {
                    textArea1.setLineWrap(false);
                }
            }
        });


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(Winform2.this, "系统正在运行，确定退出? ", "提示 ", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    if (e.getWindow() == Winform2.this) {
                        closeApp(true);
                    } else {
                        return;
                    }
                }
            }
        });
    }

    private void closeApp(boolean isExit) {
        textArea1.append("程序关闭中……\r\n");
        String startBat = BasePath + "stop.bat";
        RunCmdRunnable runCmdRunnable = new RunCmdRunnable(startBat, isExit);
        Thread thread2 = new Thread(runCmdRunnable, "tt2");
        thread2.start();
    }


    class RunCmdRunnable implements Runnable {
        private String locationCmd;
        private boolean isExit;

        public RunCmdRunnable(String locationCmd, boolean isExit) {
            this.locationCmd = locationCmd;
            this.isExit = isExit;
        }

        @Override
        public void run() {
            System.out.println("开始执行:" + locationCmd);
            try {
                Process child = Runtime.getRuntime().exec(locationCmd);
                InputStream in = child.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (textArea1.getText().length() > 50000) {
                        textArea1.replaceRange("", 0, 1000);
                        //textArea1.setCaretPosition(textArea1.getText().length());
                    }
                    textArea1.append(line + "\r\n");
                    textArea1.setCaretPosition(textArea1.getText().length());
                }
                in.close();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("callCmd execute finished");
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println("结束执行:" + locationCmd);
            if (isExit) {
                System.exit(0);
            }
        }
    }

}
