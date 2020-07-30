/*
 * Copyright (c) 2020.
 */
package com.springboot.demo.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimerTask;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2020-07-27 16:14
 */
public class Winform extends JFrame {
    private final Logger logger = LoggerFactory.getLogger(Winform.class);

    private JPanel panel1;
    private JTextArea textArea1;
    private JCheckBox checkBoxWrap;
    private JButton buttonStart;
    private JButton buttonStop;
    private JLabel labelStartTime;
    private JLabel labelRunTime;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private boolean WatchLogFileStatus = true;
    private static boolean RunStatus = false;
    private Date startTime;
    private String BasePath = "E:\\application\\proj-xuzhou-web-api\\bin\\";

    public static void main(String[] args) {
        Winform winform2 = new Winform();
    }

    public Winform() {
        BasePath = System.getProperty("user.dir") + File.separator;
        setTitle(BasePath);
        BasePath = "D:\\github\\code\\java\\spring-project-web1.0\\project-web-api\\target\\project-web-api-1.0-SNAPSHOT\\bin\\";
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        pack();
        setVisible(true);
        setSize(800, 400);
        //窗体居中
        setLocationRelativeTo(null);
        //设置窗口置顶显示
//        setAlwaysOnTop(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


        buttonStop.setEnabled(false);
        buttonStart.setEnabled(true);
        labelStartTime.setText("              ");
        labelRunTime.setText("  ");

        //读取配置信息
        Properties prop = new Properties();
        try {
            prop.load(new InputStreamReader(new FileInputStream(BasePath + "app.properties"), "UTF-8"));
            setTitle(prop.getProperty("appName"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //启动服务
        buttonStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (RunStatus) {
                    return;
                }
                RunStatus = true;
                buttonStop.setEnabled(true);
                buttonStart.setEnabled(false);
                String startBat = BasePath + "start.bat";
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
                startTime = new Date();
                labelStartTime.setText(simpleDateFormat.format(startTime));
            }
        });
        //停止服务
        buttonStop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RunStatus = false;
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);
                startTime = null;
                labelStartTime.setText("              ");
                labelRunTime.setText("  ");
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
                int option = JOptionPane.showConfirmDialog(Winform.this, "系统正在运行，确定退出? ", "提示 ", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    if (e.getWindow() == Winform.this) {
                        if (RunStatus) {
                            closeApp(true);
                        } else {
                            System.exit(0);
                        }
                    } else {
                        return;
                    }
                }
            }
        });


        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
//                    if(!RunStatus){
//                        return;
//                    }
                    Date currentDate = new Date();
                    if (startTime != null) {
                        long n = (currentDate.getTime() - startTime.getTime()) / 1000;
//                        labelRunTime.setText(n + "秒");
                        labelRunTime.setText(diffDay(startTime, currentDate));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000);
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
                    //System.out.println(line);
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

            if(!RunStatus){
                return;
            }
            if (isExit) {
                System.exit(0);
            }
        }
    }

    public String diffDay(Date before, Date after) {
        long n = after.getTime() - before.getTime();
//        int yearN = diffYear(before, after);
//        int monthN = diffMonth(before, after);
        int dayN = (int) (n / 86400000);
        int hourN = (int) ((n % 86400000) / 3600000);
        int minN = (int) ((n % 3600000) / 60000);
        int secN = (int) ((n % 60000) / 1000);
        StringBuilder sb = new StringBuilder();
//        if (yearN > 0) {
//            sb.append(yearN + "年");
//        }
//        if (monthN > 0) {
//            sb.append(monthN + "月");
//        }
        if (dayN > 0) {
            sb.append(dayN + "天");
        }
        if (dayN > 0 || hourN > 0) {
            sb.append(hourN + "时");
        }
        if (dayN > 0 || hourN > 0 || minN > 0) {
            sb.append(minN + "分");
        }
        if (dayN > 0 || hourN > 0 || minN > 0 || secN > 0) {
            sb.append(secN + "秒");
        }
        return sb.toString();
    }

    public int diffMonth(Date before, Date after) {
        int monthAll = 0;
        int yearsX = diffYear(before, after);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(before);
        c2.setTime(after);
        int monthsX = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        monthAll = yearsX * 12 + monthsX;
        int daysX = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
        if (daysX > 0) {
            monthAll = monthAll + 1;
        }
        return monthAll;
    }

    public int diffYear(Date before, Date after) {
        return getYear(after) - getYear(before);
    }

    public int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    private Date addDay(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }
}
