package com.spring.project.web.utils.lang;


import java.util.UUID;

/**
 * 字符工具类
 *
 * @author 汪强
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getUUID(int length) {
        String uuid = getUUID();
        if (length > uuid.length()) {
            return uuid;
        }
        return uuid.substring(0, length);
    }
}
