package com.spring.project.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtilsTest {

    public static void main(String[] args) {
        String DateTimeFomat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFomat);

        System.out.println(simpleDateFormat.format(new Date()));
    }
}