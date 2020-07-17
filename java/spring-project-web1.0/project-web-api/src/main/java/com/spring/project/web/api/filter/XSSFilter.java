package com.spring.project.web.api.filter;

import com.spring.project.web.api.config.FilterXssConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * XSSFilter
 *
 * @author 汪强
 * 创建时间 2019-07-22 11:19
 */
public class XSSFilter implements Filter {
    @Autowired
    private FilterXssConfigEntity filterXssConfigEntity;
    /**
     * XSS处理Map
     */
    private static Map<String, String> xssMap = new LinkedHashMap<String, String>();

    private static List<String> whiteList = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 含有脚本： script
        xssMap.put("[s|S][c|C][r|R][i|C][p|P][t|T]", "");
        // 含有脚本 javascript
        xssMap.put("[\\\"\\\'][\\s]*[j|J][a|A][v|V][a|A][s|S][c|C][r|R][i|I][p|P][t|T]:(.*)[\\\"\\\']", "\"\"");
        // 含有函数： eval
        xssMap.put("[e|E][v|V][a|A][l|L]\\((.*)\\)", "");
        // 含有符号 <
        xssMap.put("<", "&lt;");
        // 含有符号 >
        xssMap.put(">", "&gt;");
        // 含有符号 (
        xssMap.put("\\(", "(");
        // 含有符号 )
        xssMap.put("\\)", ")");
        // 含有符号 '
        xssMap.put("'", "'");
        // 含有符号 "
        xssMap.put("\\\"", "\"");

        whiteList = getWhiteList();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //强制类型转换 HttpServletRequest
        HttpServletRequest httpReq = (HttpServletRequest) request;
//        System.out.println(httpReq.getRequestURI());
        String uri = httpReq.getRequestURI().toLowerCase();
        //String contentType = request.getContentType();
        //是否上传文件
        //contentType.startsWith("multipart");

        if (isPassFilter(uri)) {
            chain.doFilter(httpReq, response);
            return;
        }


        //如果是ajax返回指定数据
//        String requestedWith = httpReq.getHeader("X-Requested-With");
//        System.out.println("requestedWith " + requestedWith);
//        if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {
//            chain.doFilter(httpReq, response);
//            return;
//        }
//        if(httpReq.getRequestURI().equals("/xss")){
//            chain.doFilter(httpReq, response);
//            return;
//        }
        // 构造HttpRequestWrapper对象处理XSS
        XSSHttpRequestWrapper httpReqWarp = new XSSHttpRequestWrapper(httpReq, xssMap);
        chain.doFilter(httpReqWarp, response);
    }

    @Override
    public void destroy() {

    }

    /**
     * 是否通过filter
     *
     * @param uri
     * @return
     */
    private boolean isPassFilter(String uri) {
        if (whiteList.isEmpty()) {
            return false;
        }
        for (String reg : whiteList) {
            if (Pattern.matches(reg, uri)) {
                return true;
            }
        }
        return false;
    }

    private List<String> getWhiteList() {
        List<String> list = new ArrayList<>();
        List<String> regs = filterXssConfigEntity.getIgnores();

        if (regs.isEmpty()) {
            return list;
        }
        for (int i = 0; i < regs.size(); i++) {
            String str = regs.get(i).trim();
            str = str.replace("*", "\\S*");
            String reg = "^(?i)" + str + "$";
            list.add(reg);
            //如果有*通配符 则全部放开 只返回一个 *通配符，减少匹配 提高效率
            if (Pattern.matches("^\\*+$", reg)) {
                list.clear();
                list.add("\\S+");
                return list;
            }
        }
        return list;
    }
}
