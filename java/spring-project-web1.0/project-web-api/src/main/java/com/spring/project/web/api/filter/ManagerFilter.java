/*
 * Copyright (c) 2019.
 */
package com.spring.project.web.api.filter;

import com.alibaba.fastjson.JSON;
import com.spring.project.web.vo.RespResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * //TODO 添加说明
 *
 * @author 汪强
 * 创建时间 2019-09-12 14:51
 */
public class ManagerFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info(request.getRemoteHost());
        String ip = request.getRemoteHost().toLowerCase();
        ip = ip.replace("0:0:0:0:0:0:0:1", "127.0.0.1").replace("localhost", "127.00.0.1");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        RespResult respResult = new RespResult(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.name());
        writer.write(JSON.toJSONString(respResult));
        writer.flush();
        writer.close();

        //request.getRequestDispatcher("/app/info").forward(request,response);
//        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
