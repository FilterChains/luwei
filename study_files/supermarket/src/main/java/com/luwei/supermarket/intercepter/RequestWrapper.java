package com.luwei.supermarket.intercepter;

import com.luwei.supermarket.util.RequestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * <p>@Description : request包装类
 * 1.解决body只能读一次的问题
 * 2.预防XSS攻击
 * </p>
 * <p>@Author : QiLin.Xing </p>
 * <p>@Date : 2018/8/3 16:17 </p>
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    /**
     * 存储requestBody byte[]
     */
    private final byte[] body;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        this.body = RequestUtils.getByteBody(request);
    }

    @Override
    public BufferedReader getReader() {
        ServletInputStream inputStream = getInputStream();
        return Objects.isNull(inputStream) ? null : new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public ServletInputStream getInputStream() {
        if (ObjectUtils.isEmpty(body)) {
            return null;
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            @SuppressWarnings("EmptyMethod")
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() {
                return bais.read();
            }
        };
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = htmlEscape(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (value == null) {
            return null;
        }
        return htmlEscape(value);
    }

    @Override
    public Object getAttribute(String name) {
        Object value = super.getAttribute(name);
        if (value instanceof String) {
            htmlEscape((String) value);
        }
        return value;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return htmlEscape(value);
    }

    @Override
    public String getQueryString() {
        String value = super.getQueryString();
        if (value == null) {
            return null;
        }
        return htmlEscape(value);
    }

    /**
     * 使用spring HtmlUtils 转义html标签达到预防xss攻击效果
     *
     * @param str
     * @see HtmlUtils#htmlEscape
     */
    protected String htmlEscape(String str) {
        return HtmlUtils.htmlEscape(str);
    }

}
