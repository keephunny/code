/*
 * Copyright (c) 2019.
 */
package com.project.encrypt;


import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * 前后端加解密类
 * 前端js加密，后端java解密
 * 后端java加密，前端js解密
 * 不可单独用于后端加解密，极不安全
 * @author 汪强
 * 创建时间 2019-09-06 17:52
 */
public class JsEncryptUtils {
    private final static Logger logger = LoggerFactory.getLogger(JsEncryptUtils.class);
    private static Base64 base64 = new Base64();

    public static void main(String[] args) throws Exception {
        String str = "zjj#2019";
        String key = "12abcdf4567asdf8";

        String strKey = encrypt(str, key);
        logger.info(strKey);
        logger.info(decrypt(strKey, key));
        //kpwBem+vRJcAvYfE30fF7w==
    }

    /**
     * 加密
     * AES加密解密方式一：此处使用AES-128-ECB加密模式，key需要为16位。
     * 1.这种加密解密方式，对应JS（js-DES-AES.html）中的AES加密解密方式；
     * 2.加密解密的key必须是长度为16的字符串（对应128bit = 16 * 8bit）；
     *
     * @param sSrc 需要加密的字符
     * @param sKey 密钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            logger.info("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            logger.info("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
        //return new Base64().encodeToString(encrypted);
        return new String(base64.encode(encrypted));
    }

    /**
     * 解密
     *
     * @param sSrc 需要解密的字符
     * @param sKey 密钥
     * @return
     */
    public static String decrypt(String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null || sKey.length() != 16) {
                logger.info("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            //先用base64转码
            //byte[] encrypted1 = new Base64().decode(sSrc);
            byte[] encrypted1 = base64.decode(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                logger.error("", e);
            }
        } catch (Exception ex) {
            logger.error("", ex);
        }
        return null;
    }
}
