package com.luwei.supermarket.intercepter;

import com.luwei.supermarket.entity.po.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;


/**
 * @version V1.8
 * @Package: com.spring.interceptor
 * @ClassName: SpringMvcInterceptor.java
 * @Description:SpringMvc intercePtor this.class采用SpringMvc中的继承方式实现拦截,
 * @Description:还有几种实现方式其中一种为实现接口(HandlerInterceptor),一般用继承方式
 * @Author: <LU WEI>
 * @date: 2019年3月13日 下午11:02:49
 * @Alert:This document is private to <LU WEI>
 */
public class SpringSSMInterceptor extends HandlerInterceptorAdapter {


    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     * @Title: preHandle
     * @Description:SpringMvc 有三种拦截时机, 注:当返回值为true如果后面没有IntercePtor则执行controller中的Method,如果有则执行IntercePtor.如果返回值是false是则以后的所有IntercePtor 和 Controller都不会被执行;
     * @Description:1、preHandler执行时机是在业务处理器请求之前被执行;
     * @Description:2、postHandler 执行时机是在当前请求进行处理之后, 也就是Controller中的方法(method)被调用之后执行;
     * @Description:3、afterCompletion 执行时机是在当前对应的intercePtor的postHandler方法的返回值为true时才被执行;
     * @Description:注:总的来说
     * @Description:1、SpringMvc和struts2中的IntercPtor的执行顺序是一样的,只是SpringMvc中的拦截器多了一个判断,而struts2中的拦截则没有判断返回值;
     * @Description:2、struts2中的拦截器与ServLet中的过滤器(Filter)类似,都是继承一个类,重写方法中都有,初始化(inIt),就绪方法(do),摧毁(destroy);
     * @Description:3、而SpringMvc中的拦截器与struts2和ServLet(Filter)有所不同,它有执行时机并根据返回值来判断是否继续执行,思想优化;
     * @see HandlerInterceptorAdapter#preHandle(HttpServletRequest, HttpServletResponse, Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.err.println("==========欢迎进入SpringMvc============");
		/*一般处理根据请求动作获取用户登录成功后存入session中的信息来判断用户是否跳过登录窗口直接访问其他view
		return true 表示继续执行,以后的程序。相反return false表示停止当前执行程序
		处理为:转发至用户登录页面,并转发相应的请求和响应
		最后需注意,还需在springMvcConfig.xml中配置拦截*/

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        System.err.println("拦截器中的信息:" + user);
        if (Objects.isNull(user)) {
            return false;
        }
        return true;
    }


}
