package com.demo.test;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-06-20 21:38
 *
 * @author 汪强
 */
public class ExpiryMapTest {
    public static void main(String[] args) {
        ExpiryMap<String, String> map = new ExpiryMap<>();

        map.put("key1", "value");
        map.put("key2", "value2", 3000);
        System.out.println(map.get("key2"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.get("key2"));

    }
}
