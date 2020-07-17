package com.project.utils.lang;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符工具类
 *
 * @author 汪强
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static final Logger logger = LoggerFactory.getLogger(StringUtils.class);
    /**
     * 常用编码
     */
    private static String[] charsetArr = {"UTF-8", "GB18030", "GB2312", "GBK", "Windows-1252", "ISO8859-1"};

    /**
     * 生成随机uuid
     *
     * @return 随机uuid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取指定长度的uuid
     *
     * @param length 长度
     * @return 随机uuid
     */
    public static String getUUID(int length) {
        String uuid = getUUID();
        if (length > uuid.length()) {
            return uuid;
        }
        return uuid.substring(0, length);
    }


    /**
     * 替换文件中的关键字并写回
     * 默认字符编码utf-8
     *
     * @param file        文件
     * @param targe       替换关键字
     * @param replacement 替换值
     * @return 操作结果
     */
    public static boolean replaceFileKeyword(File file, String targe, String replacement) {
        return replaceFileKeyword(file, targe, replacement, "utf-8");
    }

    /**
     * 替换文件中的关键字并写回
     *
     * @param file        文件
     * @param targe       替换关键字
     * @param replacement 替换值
     * @param charset     字符编码
     * @return 操作结果
     */
    public static boolean replaceFileKeyword(File file, String targe, String replacement, String charset) {
        //如果文件不存或类型为文件夹
        if (!file.exists() || file.isDirectory()) {
            logger.debug("如果文件不存或类型为文件夹");
            return false;
        }
        try {
            String str = FileUtils.readFileToString(file, charset);
            str = str.replace(targe, replacement);
            FileUtils.write(file, str, charset);
        } catch (IOException e) {
            logger.error("文件替换错误", e);
            return false;
        }
        return true;
    }

    /**
     * 替换html标签
     *
     * @param content 被替换内容
     * @return 返回替换后的内容
     */
    public static String replaceHtml(String content) {
        if (StringUtils.isEmpty(content)) {
            return content;
        }
        return content.replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<[^>]*>", "");
    }



    /*
    1.unicode转中文，中文转unicode
    2.
     */


    /**
     * 下划线转驼峰 首字母不大写
     *
     * @param str
     * @return
     */
    public static String underToCamel(String str) {
        //TODO 多下划线特殊情况转换
        str = str.toLowerCase();
        return changeNameRole(str, "_(\\w)");
    }

    /**
     * 下划线转驼峰 首字母大写
     *
     * @param str
     * @return
     */
    public static String underToCamelFirst(String str) {
        str = str.toLowerCase();
        str = changeNameRole(str, "_(\\w)");
        str = changeNameRole(str, "^(\\w)");
        return str;
    }

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     */
    public static String camelToUnder(String str) {
        String separator = "_";
        str = str.replaceAll("([a-z])([A-Z])", "$1" + separator + "$2").toLowerCase();
        return str;
    }

    /**
     * 命名规则转换
     * 根据正则表达式 转换命名规则
     *
     * @param str
     * @param reg
     * @return
     */
    public static String changeNameRole(String str, String reg) {
        Pattern linePattern = Pattern.compile(reg);
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 中文乱码恢复
     * testAllCharset("浣犲ソ);
     *
     * @param text
     * @throws UnsupportedEncodingException
     */
    public static void testAllCharset(String text) throws UnsupportedEncodingException {
        if (text == null || text.length() == 0) {
            System.out.println("文本不能为空");
            return;
        }
        System.out.println("假设当前编码       假设原始编码          编码后的内容");
        System.out.println("--------------------------------------------------------");

        for (String curCharset : charsetArr) {
            byte[] btArr = text.getBytes(curCharset);
            for (String originCharset : charsetArr) {
                if (originCharset.equals(curCharset)) {
                    continue;
                }
                String encodeText = new String(btArr, originCharset);
                printTable(curCharset, originCharset, encodeText);
            }
            System.out.println("--------------------------------------------------------");
        }
    }

    private static void printTable(String curCharset, String originCharset, String encodeText) {
        System.out.print(curCharset);
        for (int i = 0; i < 15 - curCharset.length(); i++) {
            System.out.print(" ");
        }
        System.out.print("|   " + originCharset);
        for (int i = 0; i < 16 - originCharset.length(); i++) {
            System.out.print(" ");
        }
        System.out.println("|     " + encodeText);
    }

}
