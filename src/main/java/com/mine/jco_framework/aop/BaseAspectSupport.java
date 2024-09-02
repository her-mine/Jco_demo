package com.mine.jco_framework.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author lynn
 * @date 2024/9/2/13:29
 */
public class BaseAspectSupport {
    public Method resolveMethod(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();

        Method method = getDeclaredMethod(targetClass, signature.getName(), signature.getMethod().getParameterTypes());
        if (method == null) {
            throw new IllegalStateException("无法解析目标方法： " + signature.getMethod().getName());
        }
        return method;
    }

    private Method getDeclaredMethod(Class<?> targetClass, String name, Class<?>[] parameterTypes) {
        try {
            return targetClass.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superclass = targetClass.getSuperclass();
            if (superclass != null) {
                return getDeclaredMethod(superclass, name, parameterTypes);
            }
        }
        return null;
    }

}
