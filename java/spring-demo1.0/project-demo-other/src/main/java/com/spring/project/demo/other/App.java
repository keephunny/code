package com.spring.project.demo.other;

import com.spring.project.demo.other.enums.TypeEnum;

import javax.swing.*;
import java.awt.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //创建一个窗口对象
        WindowsForm frame = new WindowsForm();
        //对窗口设置标题
        frame.setTitle("我的第一个Java窗口应用");
        //设置窗口的大小
        frame.setSize(900, 500);
        //设置窗口界面的关闭按钮真的生效(也可以直接传一个3进去，JFrame.EXIT_ON_CLOSE==3，效果一样)
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //frame.setDefaultCloseOperation(3);


        //设置窗口居中显示
        /*
         * setLocationRelativeTo
         * 该方法可以对绝大部分窗口进行居中，如果你的电脑分辨率比较奇葩，那么可能无法居中
         */
        frame.setLocationRelativeTo(null);
        //设置窗口置顶显示
        frame.setAlwaysOnTop(true);
        //设置窗口显示
        frame.setVisible(true);


    }
}
