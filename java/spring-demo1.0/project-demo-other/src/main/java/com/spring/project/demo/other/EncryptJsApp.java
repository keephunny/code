/*
 * Copyright (c) 2019.
 */
package com.spring.project.demo.other;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * 前后端加解密 js加密 js加解密
 *
 * @author 汪强
 * 创建时间 2019-09-06 17:48
 */
public class EncryptJsApp {
    public static void main(String[] args) throws Exception {

        String str = "hik12345";
        String key = "12abcdf4567asdf8";

        String strKey = Encrypt(str, key);
        System.out.println(strKey);

        System.out.println(Decrypt(strKey, key));
    }

    /**********************************************************************************************/
	/*AES加密解密方式一：此处使用AES-128-ECB加密模式，key需要为16位。
		1.这种加密解密方式，对应JS（js-DES-AES.html）中的AES加密解密方式；
		2.加密解密的key必须是长度为16的字符串（对应128bit = 16 * 8bit）；
	*/
    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
        //return new Base64().encodeToString(encrypted);
        return Base64.encode(encrypted);
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            //先用base64转码
            //byte[] encrypted1 = new Base64().decode(sSrc);
            byte[] encrypted1 = Base64.decode(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //使用
    /***
     public static void main(String[] args) throws Exception {
     //秘钥--16位
     String cKey = "1234567812345678";
     //需要加密的字串
     String cSrc = "需要加密的内容";
     System.out.println(cSrc);
     //加密
     String enString = crypto.Encrypt(cSrc, cKey);
     System.out.println("加密后的字串是：" + enString);
     //解密
     String DeString = crypto.Decrypt(enString, cKey);
     System.out.println("解密后的字串是：" + DeString);
     }
     ****/

    /**********************************************************************************************/
	/*AES加密解密方式二
		1.这种加密解密方式，与JS（js-DES-AES.html）中的AES加密解密方式不同，不能前后端合作使用；
		2.加密解密的key没有长度限制（代码中进行了补全到相应位数）
		3.这种加密解密方式更改算法名称和秘钥的编码位数可以用于DES加密解密
	*/
    //加密
    public static String encrypt(String content, String password) {
        try {
            //将秘钥补全为128位
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //若想改为DES加密，则需要将秘钥位数改为64位
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            //创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            //初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //加密
            byte[] result = cipher.doFinal(byteContent);
            //Base64转码
            return Base64.encode(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密
    public static String decrypt(String content, String password) throws Exception {
        try {
            //将秘钥补全为128位
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //若想改为DES加密，则需要将秘钥位数改为64位
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            //创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            //初始化
            cipher.init(Cipher.DECRYPT_MODE, key);
            //Base64转码
            byte[] encrypted1 = Base64.decode(content);
            //解密
            byte[] result = cipher.doFinal(encrypted1);
            //二进制转为字符串
            return new String(result, "utf-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //使用
	/*
	public static void main(String[] args) throws Exception {
		//需要加密的字串
		String content = "需要加密的内容";
		//秘钥：长度不限
		String password = "12345678";
		System.out.println(content);
		//加密
		String encryptResult = encrypt(content, password);
		System.out.println("加密后："+encryptResult);
		//解密
		String decryptResult = decrypt(encryptResult,password);
		System.out.println("解密后：" + decryptResult);
	}
	*/

    /*加密解密时密码器的输入输出都为2进制数组，这里我们用Base64在加解密的中间过程中进行了转码，所以可以以字符串形式展示加密后的密文，
    也可以使用下面的方法将二进制数组转换为16进制的字符串来表示，同样起到转换的作用*/
    //将二进制数组转化为16进制字符串
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    //将16进制字符串转化为二进制数组
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return new byte[0];
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
