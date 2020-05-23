package com.luwei.supermarket.intercepter;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>@Description : 包装请求过滤器</p>
 * <p>@Author : luwei</p>
 * <p>@Date : 2018/8/2 14:30 </p>
 */
public class RequestBodyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String contentType = request.getContentType();
        //判断是否为multipart/form-data，如果是不采用request包装类
        if (!StringUtils.isEmpty(contentType) && contentType.contains("multipart/form-data")) {
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
        }
    }

    @Override
    public void destroy() {
    }
}
