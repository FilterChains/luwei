package com.luwei.supermarket.base;

import com.luwei.supermarket.util.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>@Description : 默认异常处理类</p>
 * <p>@Author : QiLin.Xing </p>
 * <p>@Date : 2018/8/2 17:18 </p>
 */
@ControllerAdvice
public class SuperExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(SuperExceptionHandler.class);

    /**
     * <p>@Description : 将数据绑定到@RequestMapping注解，在执行方法之前初始化数据绑定器</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2018/8/2 17:21 </p>
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * <p>@Description : 将值绑定到model中，使全局@RequestMapping可以获取到值</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2018/8/2 17:23 </p>
     * <p>
     * 设置值
     * model.addAttribute("author", "Magical Sam");
     * <p>
     * 获取值
     *
     * @RequestMapping("/home") public String home(ModelMap modelMap)
     * System.out.println(modelMap.get("author"));
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    }

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Notify errorHandler(Exception ex, HttpServletResponse response) {
        logger.error(ex.getMessage(), ex);
        return new Notify<>(HttpStatus.BAD_REQUEST, Notify.Code.EXCEPTION, "系统异常，请稍候重试", ex.getLocalizedMessage());
    }

    ///**
    // * <p>@Description : 捕捉业务异常</p>
    // * <p>@Author : QiLin.Xing </p>
    // * <p>@Date : 2018/8/2 17:25 </p>
    // */
    //@ResponseBody
    //@ExceptionHandler(value = BusinessException.class)
    //public Notify errorHandler(BusinessException ex, HttpServletResponse response) {
    //    logger.error(ex.getMessage());
    //    //response.setStatus(ex.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : ex.getStatus());
    //    int httpStatus = ex.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR.value() : ex.getStatus();
    //    CodeEnum code = Objects.isNull(ex.getCode()) ? Notify.Code.FAILED : ex.getCode();
    //    return Notify.instance(httpStatus, code, ex.getMessage(), ex.getLocalizedMessage());
    //}

    ///**
    // * <p>@Description : 捕捉Service层异常</p>
    // * <p>@Author : QiLin.Xing </p>
    // * <p>@Date : 2018/9/3 11:59 </p>
    // */
    //@ResponseBody
    //@ExceptionHandler(value = ServiceException.class)
    //public Notify errorHandler(ServiceException ex, HttpServletResponse response) {
    //    logger.error(ex.getMessage(), ex);
    //    //response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    //    return Notify.instance(HttpStatus.BAD_REQUEST, Notify.Code.EXCEPTION, ex.getMessage(), ex.getLocalizedMessage());
    //}

    ///**
    // * <p>@Description : 捕捉参数异常,验证参数手动抛出异常</p>
    // * <p>@Author : QiLin.Xing </p>
    // * <p>@Date : 2018/8/6 19:55 </p>
    // */
    //@ResponseBody
    //@ExceptionHandler(value = ParamsException.class)
    //public Notify errorHandler(ParamsException ex, HttpServletResponse response) {
    //    logger.error(ex.getMessage());
    //    //response.setStatus(HttpStatus.BAD_REQUEST.value());
    //    return Notify.instance(HttpStatus.BAD_REQUEST, Notify.Code.FAILED, ex.getMessage(), ex.getLocalizedMessage());
    //}

    /**
     * <p>@Description : 参数解析或方法验证错误异常</p>
     * <p>@Author : QiLin.Xing </p>
     * <p>@Date : 2019/4/30 0030 下午 14:37 </p>
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        StringBuilder msg = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            msg.append(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            msg.append(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        Notify notify = new Notify(HttpStatus.BAD_REQUEST, Notify.Code.EXCEPTION, "方法或参数解析异常", ex.getLocalizedMessage());
        return new ResponseEntity(notify, headers, status);
    }

}
