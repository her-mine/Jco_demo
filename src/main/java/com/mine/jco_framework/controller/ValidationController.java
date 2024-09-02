package com.mine.jco_framework.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lynn
 * @date 2024/8/29/14:10
 */
@Slf4j
@RestControllerAdvice
public class ValidationController {

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public String error(Exception e) {
        if (e instanceof ConstraintViolationException exception) {
            log.error(e.getMessage());
            return e.getMessage();
        } else if (e instanceof MethodArgumentNotValidException exception) {
            if (exception.getFieldError() == null) return "未知错误";
            log.error(exception.getBindingResult().getTarget().toString());
            log.error(exception.getFieldError().getDefaultMessage());
            return exception.getFieldError().getDefaultMessage();
        }
        return "未知错误";
    }
}
