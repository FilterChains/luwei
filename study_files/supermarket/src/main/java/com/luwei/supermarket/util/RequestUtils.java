/*
 * Copyright (c) 2018-2022 Caratacus, (caratacus@qq.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.luwei.supermarket.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Request 请求工具类
 *
 * @author Caratacus
 */
public final class RequestUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);

    /**
     * 判断请求方式GET
     *
     * @param request
     * @return
     */
    public static boolean isGet(HttpServletRequest request) {
        return RequestMethod.GET.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式POST
     *
     * @param request
     * @return
     */
    public static boolean isPost(HttpServletRequest request) {
        return RequestMethod.POST.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式PUT
     *
     * @param request
     * @return
     */
    public static boolean isPut(HttpServletRequest request) {
        return RequestMethod.PUT.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式DELETE
     *
     * @param request
     * @return
     */
    public static boolean isDelete(HttpServletRequest request) {
        return RequestMethod.DELETE.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式PATCH
     *
     * @param request
     * @return
     */
    public static boolean isPatch(HttpServletRequest request) {
        return RequestMethod.PATCH.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式TRACE
     *
     * @param request
     * @return
     */
    public static boolean isTrace(HttpServletRequest request) {
        return RequestMethod.TRACE.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式HEAD
     *
     * @param request
     * @return
     */
    public static boolean isHead(HttpServletRequest request) {
        return RequestMethod.HEAD.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式OPTIONS
     *
     * @param request
     * @return
     */
    public static boolean isOptions(HttpServletRequest request) {
        return RequestMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 获取请求
     *
     * @param request
     * @return
     */
    public static String getRequestBody(HttpServletRequest request) {
        String requestBody = null;
        if (isContainBody(request)) {
            try {
                ServletInputStream inputStream = null;
                //忽略掉Shiro包装的Request
//                if (request instanceof ShiroHttpServletRequest) {
//                    ShiroHttpServletRequest shiroRequest = (ShiroHttpServletRequest) request;
//                    inputStream = shiroRequest.getRequest().getInputStream();
//                } else {
                inputStream = request.getInputStream();
//                }
                if (Objects.nonNull(inputStream)) {
                    StringWriter writer = new StringWriter();
                    IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
                    requestBody = writer.toString();
                }
            } catch (IOException ignored) {
            }
        }
        return requestBody;
    }

    /**
     * 获取请求
     *
     * @param request
     * @return
     */
    public static byte[] getByteBody(HttpServletRequest request) {
        byte[] body = new byte[0];
        try {
            body = StreamUtils.copyToByteArray(request.getInputStream());
        } catch (IOException e) {
            LOGGER.error("Error: Get RequestBody byte[] fail," + e);
        }
        return body;
    }

    /**
     * 是否包含请求体
     *
     * @param request
     * @return
     */
    public static boolean isContainBody(HttpServletRequest request) {
        return isPost(request) || isPut(request) || isPatch(request);
    }

    /**
     * <p>@Description :
     * 获取客户端的IP地址的方法是：request.getRemoteAddr()，这种方法在大部分情况下都是有效的。
     * 但是在通过了Apache,Squid等反向代理软件就不能获取到客户端的真实IP地址了，如果通过了多级反向代理的话，
     * X-Forwarded-For的值并不止一个，而是一串IP值， 究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * 例如：X-Forwarded-For：192.168.1.110, 192.168.1.120,
     * 192.168.1.130, 192.168.1.100 用户真实IP为： 192.168.1.110
     * </p>
     * <p>@Author : luwei</p>
     * <p>@Date : 2019/2/1 15:52 </p>
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        // nginx代理获取的真实用户ip
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        /*
          对于通过多个代理的情况， 第一个IP为客户端真实IP,多个IP按照','分割 "***.***.***.***".length() =
          15
         */
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
