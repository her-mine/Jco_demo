package com.mine.jco_framework.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lynn
 * @date 2024/9/2/13:03
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect extends BaseAspectSupport {

    @Autowired
    HttpServletRequest request;

    @Pointcut("execution (* com.mine.jco_framework.controller..*.*(..))")
    public void test() {

    }

    @Around("test()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("before");
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();

            Method method = resolveMethod(proceedingJoinPoint);
            log.info("接收参数为:" + getParameter(method, proceedingJoinPoint.getArgs()));

        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.out.println("after");
        log.info("after");
        return result;
    }

    @AfterThrowing("test()")
    public void doAfterThrowing(JoinPoint point) {
        log.error("访问路径:" + request.getServletPath());
    }

    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            String key = parameters[i].getName();
            if (requestBody != null) {
                argList.add(args[i]);
            } else if (requestParam != null) {
                map.put(key, args[i]);
            } else {
                map.put(key, args[i]);
            }
        }
        if (map.size() > 0) {
            argList.add(map);
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }


}
