//package com.luwei.supermarket.base;
//
//import com.alibaba.fastjson.JSON;
//import com.luwei.supermarket.util.RequestUtils;
//import javassist.NotFoundException;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Optional;
//
///**
// * <p>@Description : 记录请求日志和请求日志</p>
// * <p>@Author : QiLin.Xing </p>
// * <p>@Date : 2019/2/2 9:00 </p>
// */
//@Aspect
//public class RequestLogAspect {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogAspect.class);
//
//    private static final Logger BUSINESS_LOG = LoggerFactory.getLogger(LoggerConstant.BUSINESS_LOG);
//
//    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)")
//    public void pointCut() {
//    }
//
//    @Pointcut("@annotation(businessLog)")
//    public void recordLog(BusinessLog businessLog) {
//    }
//
//    @AfterThrowing(value = "pointCut()", throwing = "ex")
//    public void afterThrowingMethod(Exception ex) {
//        RequestLogBo requestLogBo = this.getRequestLog();
//        requestLogBo.setResponse(ex.getLocalizedMessage());
//        LOGGER.error(requestLogBo.getUrl() + " | " + JSON.toJSONString(requestLogBo));
//    }
//
//    @AfterReturning(returning = "result", pointcut = "pointCut()")
//    public void doAfterReturning(Object result) {
//        RequestLogBo requestLogBo = this.getRequestLog();
//        //requestLogBo.setResponse(result);
//        LOGGER.info(requestLogBo.getUrl() + " | " + JSON.toJSONString(requestLogBo));
//    }
//
//    /**
//     * <p>@Description : 记录业务日志</p>
//     * <p>@Author : QiLin.Xing </p>
//     * <p>@Date : 2019/2/2 11:30 </p>
//     */
//    @AfterReturning(pointcut = "pointCut() && recordLog(businessLog)", returning = "result")
//    public void restoreDataSource(JoinPoint joinPoint, Object result, BusinessLog businessLog) throws NotFoundException, ClassNotFoundException {
//        HttpServletRequest httpServletRequest = SpringContextUtils.getRequest();
//        String classType = joinPoint.getTarget().getClass().getName();
//        Class<?> clazz = Class.forName(classType);
//        BusinessLogBo businessLogBo = new BusinessLogBo();
//        businessLogBo.setClazz(clazz.getName());
//        businessLogBo.setMethod(joinPoint.getSignature().getName());
//        businessLogBo.setParams(httpServletRequest.getParameterMap());
//        businessLogBo.setUserId(httpServletRequest.getAttribute(RequestConstant.REQ_UID));
//        businessLogBo.setModule(businessLog.module());
//        businessLogBo.setType(businessLog.type());
//        businessLogBo.setResult(result);
//        businessLogBo.setContent(businessLog.value());
//        BUSINESS_LOG.info(JSON.toJSONString(businessLogBo));
//    }
//
//    /**
//     * <p>@Description : 获取请求日志</p>
//     * <p>@Author : QiLin.Xing </p>
//     * <p>@Date : 2019/2/2 9:49 </p>
//     */
//    private RequestLogBo getRequestLog() {
//        HttpServletRequest httpServletRequest = SpringContextUtils.getRequest();
//        RequestLogBo requestLog = new RequestLogBo();
//        requestLog.setParamsMap(httpServletRequest.getParameterMap());
//        requestLog.setUserId(String.valueOf(httpServletRequest.getAttribute(RequestConstant.REQ_UID)));
//        String requestBody = RequestUtils.getRequestBody(httpServletRequest);
//        requestLog.setRequestBody(Optional.ofNullable(requestBody).orElse(requestBody));
//        requestLog.setUrl(httpServletRequest.getRequestURI());
//        requestLog.setMethod(httpServletRequest.getMethod());
//        Object startTimeObj = httpServletRequest.getAttribute(RequestConstant.REQ_START_TIME);
//        long execTime = startTimeObj == null ? 0 : System.currentTimeMillis() - Long.valueOf(startTimeObj.toString());
//        requestLog.setExecTime(execTime + "ms");
//        requestLog.setIp(RequestUtils.getIpAddr(httpServletRequest));
//        return requestLog;
//    }
//}
