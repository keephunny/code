/*
 * Copyright (c) 2019.
 */
package com.project.utils.httpclient;

import com.project.utils.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

/**
 * //添加说明
 *
 * @author 汪强
 * 创建时间 2019-10-17 9:39
 */
public class HttpClientsUtils {
    public final static Logger logger = LoggerFactory.getLogger(HttpClientsUtils.class);

    private static int Timeout = 5000;

    public static void setTimeout(int timeout) {
        Timeout = timeout;
    }

    /**
     * 创建requestConfig
     *
     * @param hostName
     * @param port
     * @param maxPerRoute
     * @return
     */
    public static CloseableHttpClient createHttpClient(String hostName, int port, int maxPerRoute) {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        final Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", plainsf)
                .register("https", sslsf)
                .build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        HttpHost httpHost = new HttpHost(hostName, port);
        //配置最大连数据
        cm.setMaxTotal(maxPerRoute);
        //配置路由基础连接
        cm.setDefaultMaxPerRoute(maxPerRoute);
        //将目标主机的最大连接数添加
        //cm.setMaxPerRoute(new HttpRoute(httpHost),maxTotal);

        //region 请求重试
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                //重试5次
                if (executionCount >= 1) {
                    return false;
                }
                //不要重试SSL握手异常
                if (exception instanceof SSLHandshakeException) {
                    return false;
                }
                //目标server不可达
                if (exception instanceof UnknownHostException) {
                    return false;
                }
                //连接被拒绝
                if (exception instanceof ConnectTimeoutException) {
                    return false;
                }
                //SSL握手异常
                if (exception instanceof SSLException) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                //如果请求是幂等，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
        //endregion

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler)
                .build();
        return httpClient;
    }

    /**
     * 创建requestConfig
     *
     * @param hostName
     * @return
     */
    public static CloseableHttpClient createHttpClient(String hostName) {
        int port = 80;
        int maxPerRoute = 10;
        return createHttpClient(hostName, port, maxPerRoute);
    }

    /**
     * 创建requestConfig
     *
     * @return
     */
    public static RequestConfig createRequestConfig() {
        return createRequestConfig(null);
    }

    /**
     * 创建requestConfig
     *
     * @param proxy 代理
     * @return
     */
    public static RequestConfig createRequestConfig(HttpHost proxy) {
        RequestConfig requestConfig = RequestConfig.custom()
                //设置连接超时间
                .setConnectTimeout(Timeout)
                //设置请求超时间
                .setConnectionRequestTimeout(Timeout)
                .setSocketTimeout(Timeout)
                //默认允许自动重定向
                .setRedirectsEnabled(true)
                .setProxy(proxy)
                .build();
        return requestConfig;
    }


    public static HttpGet createHttpGet(String url, HttpRequestHeadDto requestHead) {
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = createRequestConfig();
        httpGet.setConfig(requestConfig);

        setHead(httpGet, requestHead);

        return httpGet;
    }

    public static HttpPost createHttpPost(String url, HttpRequestHeadDto requestHead) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = createRequestConfig();
        httpPost.setConfig(requestConfig);

        setHead(httpPost, requestHead);

        return httpPost;
    }

    /**
     * 设置head头信息
     *
     * @param httpRequest
     * @param httpRequestHead
     */
    public static void setHead(HttpRequestBase httpRequest, HttpRequestHeadDto httpRequestHead) {
        if (httpRequest == null || httpRequestHead == null) {
            return;
        }

        //遍历head name value
        Field[] fields = HttpRequestHeadDto.class.getDeclaredFields();
        try {
            String headName, name, value;
            for (Field field : fields) {
                headName = "";
                HttpHead httpHead = field.getAnnotation(HttpHead.class);

                if (httpHead != null) {
                    headName = httpHead.value();
                }
                name = field.getName();
                name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                Method m = httpRequestHead.getClass().getMethod(name);
                value = (String) m.invoke(httpRequestHead);

                if (value == null) {
                    continue;
                }
                httpRequest.addHeader(headName, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String downUrl(String url, HttpRequestHeadDto httpRequestHead) {
        if (StringUtils.isEmpty(url)) {
            logger.info("url为空");
            return null;
        }
        url = url.trim();
        String hostName = getHostName(url, httpRequestHead);
        CloseableHttpClient httpClient = HttpClientsUtils.createHttpClient(hostName);
        HttpGet httpGet = HttpClientsUtils.createHttpGet(url, httpRequestHead);
        String result = null;
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            Integer statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }

        } catch (IOException e) {
            logger.error("错误 " + url, e);
        }
        return result;
    }

    public static String downUrlPost(String url, HttpRequestHeadDto httpRequestHead, List<NameValuePair> nvps) {
        if (StringUtils.isEmpty(url)) {
            return url;
        }
        url = url.trim();

        String hostName = getHostName(url, httpRequestHead);
        CloseableHttpClient httpClient = HttpClientsUtils.createHttpClient(hostName);
        HttpPost httpPost = HttpClientsUtils.createHttpPost(url, httpRequestHead);
        String result = null;
        try {
            if (nvps != null) {
                if (httpRequestHead != null && "application/json".equals(httpRequestHead.getContentType())) {
                    String json = convertToJson(nvps);
                    StringEntity se = new StringEntity(json, "utf-8");
                    se.setContentType("application/json");
                    httpPost.setEntity(se);
                } else {
                    httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
                }
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            Integer statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_NOT_IMPLEMENTED) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            } else {
                logger.error("失败 {} {}", url, statusCode);
            }
        } catch (Exception e) {
            logger.error("错误 " + url, e);
        }
        return result;
    }

    private static String getHostName(String url, HttpRequestHeadDto httpRequestHead) {
        if (httpRequestHead == null || StringUtils.isEmpty(httpRequestHead.getHost())) {
            URL url1 = null;
            try {
                url1 = new URL(url);
            } catch (MalformedURLException e) {
                logger.error("error", e);
            }
            if (url1 == null) {
                return null;
            }
            return url1.getHost();
        } else {
            return httpRequestHead.getHost();
        }
    }

    private static String convertToJson(List<NameValuePair> nvps) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int i = 0;
        for (NameValuePair pair : nvps) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("\"" + pair.getName() + "\"");
            sb.append(":");
            sb.append("\"" + pair.getValue() + "\"");
            i++;
        }
        sb.append("}");
        return sb.toString();
    }
}
