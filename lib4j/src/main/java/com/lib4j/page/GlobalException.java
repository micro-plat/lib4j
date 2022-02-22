package com.lib4j.page;

import javax.servlet.http.HttpServletRequest;

import com.lib4j.errs.CodeException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    public void handleHttpException(HttpServletRequest req, Exception ex){

        if(ex instanceof CodeException){
            CodeException ce=(CodeException)ex;
        
        }
        // ex.getClass().isAssignableFrom(CodeException);
        System.out.println("发生异常了");
    }
}
