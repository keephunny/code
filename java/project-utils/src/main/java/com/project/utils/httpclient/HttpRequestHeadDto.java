/*
 * Copyright (c) 2019.
 */
package com.project.utils.httpclient;

/**
 * //添加说明
 *
 * @author 汪强
 * 创建时间 2019-10-17 9:44
 */
public class HttpRequestHeadDto {
    @HttpHead("User-Agent")
    private String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTMLlike Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11";
    @HttpHead("Accept")
    private String accept = "*/*";
    @HttpHead("Accept-Encoding")
    private String acceptEncoding = "gzip, deflate, br";
    @HttpHead("Accept-Language")
    private String acceptLanguage = "zh-CN,zh;q=0.9,en;q=0.8";
    @HttpHead("Connection")
    private String connection = "keep-alive";
    @HttpHead("Cookie")
    private String cookie;
    @HttpHead("Host")
    private String host;
    @HttpHead("Referer")
    private String referer;
    @HttpHead("Upgrade-Insecure-Requests")
    private String upgradeInsecureRequests;
    @HttpHead("Cache-Control")
    private String cacheControl;
    @HttpHead("Content-Type")
    private String contentType;
    @HttpHead("Origin")
    private String origin;
    @HttpHead("X-Requested-With")
    private String xrequestedWith;
    @HttpHead("If-Modified-Since")
    private String ifModifiedSince;
    @HttpHead("If-None-Match")
    private String ifNoneMatch;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUpgradeInsecureRequests() {
        return upgradeInsecureRequests;
    }

    public void setUpgradeInsecureRequests(String upgradeInsecureRequests) {
        this.upgradeInsecureRequests = upgradeInsecureRequests;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getXrequestedWith() {
        return xrequestedWith;
    }

    public void setXrequestedWith(String xrequestedWith) {
        this.xrequestedWith = xrequestedWith;
    }

    public String getIfModifiedSince() {
        return ifModifiedSince;
    }

    public void setIfModifiedSince(String ifModifiedSince) {
        this.ifModifiedSince = ifModifiedSince;
    }

    public String getIfNoneMatch() {
        return ifNoneMatch;
    }

    public void setIfNoneMatch(String ifNoneMatch) {
        this.ifNoneMatch = ifNoneMatch;
    }
}
