package com.project.utils.lang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期时间工具类
 *
 * @author 汪强
 */
public class DateTimeUtils {

    //#region 日期时间格式
    /**
     * 默认日期格式 yyyy-MM-dd
     */
    public static final String DATE_FOMAT = "yyyy-MM-dd";

    /**
     * 默认日期时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_FOMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式 yyyy
     */
    public static final String DATE_FORMAT_YYYY = "yyyy";

    /**
     * 日期格式 yyyyMM
     */
    public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

    /**
     * 日期格式 yyyy-MM
     */
    public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

    /**
     * 日期格式 yyMMdd
     */
    public static final String DATE_FORMAT_YYMMDD = "yyMMdd";
    public static final String DATE_FORMAT_DD = "dd";

    /**
     * 日期格式 yy-MM-dd
     */
    public static final String DATE_FORMAT_YY_MM_DD = "yy-MM-dd";

    /**
     * 日期格式 yyyyMMdd
     */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * 日期格式 yyyy-MM-dd
     */
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 日期格式 yyyy.MM.dd
     */
    public static final String DATE_FORMAT_POINTYYYYMMDD = "yyyy.MM.dd";

    /**
     * 日期格式 yyyy年MM月dd日
     */
    public static final String DATE_FORMAT_YYYYMMDD_CN = "yyyy年MM月dd日";

    /**
     * 日期格式 yyyy年MM月dd日
     */
    public static final String DATE_FORMAT_YYMMDD_CN = "yy年MM月dd日";

    /**
     * 日期格式 yyyy年MM月dd日
     */
    public static final String DATE_FORMAT_YYMM_CN = "yy年MM月";

    /**
     * 日期格式 yyyy年MM月dd日
     */
    public static final String DATE_FORMAT_MMDD_CN = "MM月dd日";

    /**
     * 日期格式 yyyyMMddHHmm
     */
    public static final String DATE_FORMAT_YYYYMMDDHHmm = "yyyyMMddHHmm";

    /**
     * 日期格式 yyyyMMdd HH:mm
     */
    public static final String DATE_TIME_FORMAT_YYYYMMDD_HH_MI = "yyyyMMdd HH:mm";

    /**
     * 日期格式 yyyy-MM-dd HH:mm
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";

    /**
     * 日期格式 yyyyMMddHHmmss
     */
    public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";

    /**
     * 日期格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式 yyyyMMddHHmmssSSS
     */
    public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS = "yyyyMMddHHmmssSSS";

    /**
     * 日期格式 MM-dd HH:mm
     */
    public static final String DATE_TIME_FORMAT_MMDDHHMI = "MM-dd HH:mm";
    public static final String DATE_TIME_FORMAT_MMDDHHMI_HOUR = "HH";
    public static final String DATE_TIME_FORMAT_HHMMSS = "HH:mm:ss";

    //#endregion

    /**
     * 把日期字符串格式化成日期类型
     *
     * @param datetimeStr 日期时间字符串
     * @param format      格式化
     * @return 操作结果
     */
    public static Date convert2Datetime(String datetimeStr, String format) {
        //日期或格式字符为空 返回空
        if (StringUtils.isEmpty(datetimeStr) || StringUtils.isEmpty(format)) {
            return null;
        }
        SimpleDateFormat simple = new SimpleDateFormat(format);
        try {
            //是否宽松解析字符串 false则严格解析 true宽松解析
            simple.setLenient(false);
            return simple.parse(datetimeStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 把日期字符串格式化成默认日期类型
     *
     * @param dateStr 日期字符串
     * @return 操作结果
     */
    public static Date convert2Date(String dateStr) {
        return convert2Datetime(dateStr, DATE_FOMAT);
    }

    /**
     * 把日期时间字符串格式化成默认日期时间类型
     *
     * @param datetimeStr 日期时间字符串
     * @return 操作结果
     */
    public static Date convert2Datetime(String datetimeStr) {
        return convert2Datetime(datetimeStr, DATETIME_FOMAT);
    }


    /**
     * 把日期类型格式化成字符串
     *
     * @param datetime 日期时间
     * @param format   格式化
     * @return 操作结果
     */
    public static String datetime2String(Date datetime, String format) {
        if (datetime == null) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            format = DATETIME_FOMAT;
        }
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            return formater.format(datetime);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 把日期类型格式化成字符串
     *
     * @param date 日期
     * @return 操作结果
     */
    public static String date2String(Date date) {
        return datetime2String(date, DATE_FOMAT);
    }

    /**
     * 将日期时间转换成默认格式的字符串
     *
     * @param datetime 日期时间
     * @return 操作结果
     */
    public static String datetime2String(Date datetime) {
        return datetime2String(datetime, DATETIME_FOMAT);
    }


    //-------------未验证-----------------

    /**
     * 转sql的time格式
     *
     * @param date 日期
     * @return 返回值
     */
    public static java.sql.Timestamp convertSqlTime(Date date) {
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        return timestamp;
    }

    /**
     * 转sql的日期格式
     *
     * @param date 日期
     * @return 返回值
     */
    public static java.sql.Date convertSqlDate(Date date) {
        java.sql.Date Datetamp = new java.sql.Date(date.getTime());
        return Datetamp;
    }


    /**
     * 获取当前日期
     *
     * @param format 格式化
     * @return 返回值
     */
    public static String getCurrentDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 获取时间戳
     *
     * @return 返回值
     */
    public static long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取月份的天数
     *
     * @param year  年
     * @param month 月
     * @return 返回值
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期的年
     *
     * @param date 日期
     * @return 返回值
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取格式化日期的 日期部份
     * 2020-01-01
     *
     * @param date 日期
     * @return 返回值
     */
    public static String getFormatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取格式化日期的 时间部份
     * 13:00:00
     *
     * @param date 日期
     * @return 返回值
     */
    public static String getFormatTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT_HHMMSS);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取格式化日期的 年部份
     * 2020
     *
     * @param date 日期
     * @return 返回值
     */
    public static String getFormatYear(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取日期的月
     *
     * @param date 日期
     * @return 返回值
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的日
     *
     * @param date 日期
     * @return 返回值
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取日期的时
     *
     * @param date 日期
     * @return 返回值
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }

    /**
     * 获取日期的分种
     *
     * @param date 日期
     * @return 返回值
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取日期的秒
     *
     * @param date 日期
     * @return 返回值
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取星期几
     *
     * @param date 日期
     * @return 返回值
     */
    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek - 1;
    }

    /**
     * 获取哪一年共有多少周
     *
     * @param year 年
     * @return 返回值
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekNumOfYear(c.getTime());
    }

    /**
     * 取得某天是一年中的多少周
     *
     * @param date 日期
     * @return 返回值
     */
    public static int getWeekNumOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得某天所在周的第一天
     *
     * @param date 日期
     * @return 返回值
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }

    /**
     * 取得某天所在周的最后一天
     *
     * @param date 日期
     * @return 返回值
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        return c.getTime();
    }

    /**
     * 取得某年某周的第一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周,2009-01-05为2009年第一周的第一天
     *
     * @param year 年
     * @param week 周
     * @return 返回值
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar calFirst = Calendar.getInstance();
        calFirst.set(year, 0, 7);
        Date firstDate = getFirstDayOfWeek(calFirst.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        firstDate = getFirstDayOfWeek(cal.getTime());

        return firstDate;
    }

    /**
     * 获取当月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当年第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 取得某年某周的最后一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周, 2009-01-04为
     * 2008年最后一周的最后一天
     *
     * @param year 年
     * @param week 周
     * @return 返回值
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar calLast = Calendar.getInstance();
        calLast.set(year, 0, 7);
        Date firstDate = getLastDayOfWeek(calLast.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        Date lastDate = getLastDayOfWeek(cal.getTime());

        return lastDate;
    }

    public static Date getLastDayOfMonthDate(Date date) {
        Calendar cal = Calendar.getInstance();
        int lastDay = getLastDayOfMonth(date);
        cal.setTime(date);
        cal.set(Calendar.DATE, lastDay);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 日期操作
     *
     * @param date          日期
     * @param calendarField 参数
     * @param amount        参数
     * @return 返回值
     */
    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    /*
     * 1则代表的是对年份操作， 2是对月份操作， 3是对星期操作， 5是对日期操作， 11是对小时操作， 12是对分钟操作， 13是对秒操作，
     * 14是对毫秒操作
     */

    /**
     * 增加年
     *
     * @param date   日期
     * @param amount 参数
     * @return 返回值
     */
    public static Date addYears(Date date, int amount) {
        return add(date, 1, amount);
    }

    /**
     * 增加月
     *
     * @param date   日期
     * @param amount 参数
     * @return 返回值
     */
    public static Date addMonths(Date date, int amount) {
        return add(date, 2, amount);
    }

    /**
     * 增加周
     *
     * @param date   日期
     * @param amount 参数
     * @return 返回值
     */
    public static Date addWeeks(Date date, int amount) {
        return add(date, 3, amount);
    }

    /**
     * 增加天
     *
     * @param date   日期
     * @param amount 参数
     * @return 返回值
     */
    public static Date addDays(Date date, int amount) {
        return add(date, 5, amount);
    }

    /**
     * 增加时
     *
     * @param date   日期
     * @param amount 参数
     * @return 返回值
     */
    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    /**
     * 增加分
     *
     * @param date   日期
     * @param amount 参数
     * @return 返回值
     */
    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }

    /**
     * 增加秒
     *
     * @param date   日期
     * @param amount 参数
     * @return 返回值
     */
    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    /**
     * 增加毫秒
     *
     * @param date   日期
     * @param amount 参数
     * @return 返回值
     */
    public static Date addMilliseconds(Date date, int amount) {
        return add(date, 14, amount);
    }


    /**
     * time差
     *
     * @param before 参数
     * @param after  参数
     * @return 返回值
     */
    public static long diffTimes(Date before, Date after) {
        return after.getTime() - before.getTime();
    }

    /**
     * 秒差
     *
     * @param before 参数
     * @param after  参数
     * @return 返回值
     */
    public static long diffSeconds(Date before, Date after) {
        return (after.getTime() - before.getTime()) / 1000;
    }


    /**
     * 分种差
     *
     * @param before 参数
     * @param after  参数
     * @return 返回值
     */
    public static int diffMinute(Date before, Date after) {
        return (int) (after.getTime() - before.getTime()) / 1000 / 60;
    }

    /**
     * 时差
     *
     * @param before 参数
     * @param after  参数
     * @return 返回值
     */
    public static int diffHour(Date before, Date after) {
        return (int) (after.getTime() - before.getTime()) / 1000 / 60 / 60;
    }

    /**
     * 天数差
     *
     * @param before 参数
     * @param after  参数
     * @return 返回值
     */
    public static int diffDay(Date before, Date after) {
        return Integer.parseInt(String.valueOf(((after.getTime() - before.getTime()) / 86400000)));
    }

    /**
     * 月差
     *
     * @param before 参数
     * @param after  参数
     * @return 返回值
     */
    public static int diffMonth(Date before, Date after) {
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

    /**
     * 年差
     *
     * @param before 参数
     * @param after  参数
     * @return 返回值
     */
    public static int diffYear(Date before, Date after) {
        return getYear(after) - getYear(before);
    }

    /**
     * 年差
     *
     * @param before 参数
     * @param after  参数
     * @return 返回值
     */
    public static int diffWeek(Date before, Date after) {
        return getWeekDay(after) - getWeekDay(before);
    }

    public static DateTimeDiff diffDateTime(Date before, Date after) {
        DateTimeDiff dateTimeDiff = new DateTimeDiff();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(before);
        c2.setTime(after);

        int yearN = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        int monthN = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        monthN = yearN * 12 + monthN;
        System.out.println("年:" + yearN);
        System.out.println("月:" + monthN);

        return dateTimeDiff;

    }

    /**
     * 设置23:59:59
     *
     * @param date 参数
     * @return 返回值
     */
    public static Date setEndDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 设置00:00:00
     *
     * @param date 参数
     * @return 返回值
     */
    public static Date setStartDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 00);
        return calendar.getTime();
    }

    /**
     * 设置00:00:00
     *
     * @param date 参数
     * @return 返回值
     */
    public static Date setStartDayByMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 00);
        return calendar.getTime();
    }

    /**
     * 设置00:00:00
     *
     * @param date 参数
     * @return 返回值
     */
    public static Date setStartDayByYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 00);
        return calendar.getTime();
    }

    /**
     * 设置00:00:00
     *
     * @param date 参数
     * @return 返回值
     */
    public static Date setStartDayByWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 2);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 00);
        return calendar.getTime();
    }

    /**
     * 获取当月最后一天
     *
     * @param date
     * @return
     */
    public static int getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当年最后一天
     *
     * @param date
     * @return
     */
    public static int getLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.MONTH, 12);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
