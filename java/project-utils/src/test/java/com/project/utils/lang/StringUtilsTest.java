package com.project.utils.lang;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void replaceFileKeyword() {
    }

    @Test
    public void replaceFileKeyword1() {
    }

    @Test
    public void replaceHtml() {
    }

    @Test
    public void underToCamel() {
        String str = "USER_INFO";

        System.out.print(str + " -> ");
        System.out.println(StringUtils.underToCamel(str));
        System.out.print(str + " -> ");
        System.out.println(StringUtils.underToCamelFirst(str));
    }

    @Test
    public void underToCamelFirst() {
    }

    @Test
    public void camelToUnder() {

        String str = "userInfo";
        System.out.print(str + " -> ");
        System.out.println(StringUtils.camelToUnder(str));
        str = "User_Info";
        System.out.print(str + " -> ");
        System.out.println(StringUtils.camelToUnder(str));
    }

    @Test
    public void changeNameRole() {
    }
}