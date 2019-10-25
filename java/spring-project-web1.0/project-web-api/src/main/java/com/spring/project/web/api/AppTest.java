/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api;

import com.spring.project.web.utils.lang.DateTimeUtils;
import org.apache.commons.lang3.RandomUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * //TODO 添加说明
 *
 * @author w
 * 创建时间 2019-09-10 9:50
 */
public class AppTest {
    public static void main(String[] args) {
        drain();

    }

    private static void drain() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = new Date();
        Date endDate = null;
        try {
            beginDate = format.parse("2019-01-01 00:00:00");
            endDate = format.parse("2019-12-31 23:59:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer[] minValue = {8000,8000,8000,7000,8000,11000,12000,15000,16000,15000,16000,16000,17000,14000,14000,14000,14000,15000,15000,14000,13000,9000,9000,8000};
        Integer[] maxValue = {8100,8100,8100,7100,8100,12000,13000,16000,17000,16000,17000,17000,18000,15000,14000,14000,15000,16000,16000,15000,14000,10000,9000,8000};

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        StringBuilder sb = new StringBuilder();
        String reportTime;
        int value, month;
        for (int i = 0; i < 8760; i++) {
            month = calendar.get(Calendar.MONTH);
//            System.out.println(calendar.get(Calendar.MONTH));
//            System.out.println(format.format(calendar.getTime()));
            reportTime = format.format(calendar.getTime());


            value = RandomUtils.nextInt(minValue[month]-3500, maxValue[month]-3000);

            sb.append("insert into report_city (app_name,app_id,report_time,alarm_num,value) values('供水',2,'" + reportTime + "',1," + value + ");\r\n");

            calendar.add(Calendar.HOUR_OF_DAY, 1);
        }

        System.out.println(sb.toString());
    }

    private void gas() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = new Date();
        Date endDate = null;
        try {
            beginDate = format.parse("2019-01-01 00:00:00");
            endDate = format.parse("2019-12-31 23:59:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer[] maxValue = {22282, 20550, 21320, 20336, 21210, 20247, 22597, 27663, 26056, 28069, 27191, 28869, 83858, 29675, 24461, 24304, 24095, 25892, 36786, 28212, 24805, 26635, 23485, 24001};
        Integer[] minValue = {15256, 14428, 13507, 13906, 13827, 13310, 16043, 18866, 8575, 6789, 6630, 6910, 24635, 18849, 15097, 16571, 18588, 19946, 24145, 20791, 19623, 19094, 17673, 17246};

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        StringBuilder sb = new StringBuilder();
        String reportTime;
        int value, hour;
        for (int i = 0; i < 8760; i++) {
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            //System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
//            System.out.println(format.format(calendar.getTime()));
            reportTime = format.format(calendar.getTime());

            value = RandomUtils.nextInt(minValue[hour], maxValue[hour]);
            sb.append("insert into report_city (app_name,app_id,report_time,alarm_num,value) values('燃气',1,'" + reportTime + "',1," + value + ");\r\n");

            calendar.add(Calendar.HOUR_OF_DAY, 1);
        }

        System.out.println(sb.toString());
    }
}
