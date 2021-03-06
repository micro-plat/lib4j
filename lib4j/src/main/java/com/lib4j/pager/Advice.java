package com.lib4j.pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lib4j.errs.CodeException;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class Advice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(final MethodParameter returnType,
            final Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @ExceptionHandler(value = CodeException.class)
    @ResponseBody
    public Object bizExceptionHandler(HttpServletRequest request, HttpServletResponse response, CodeException e) {
        this.setCrossDomainHeader(request, response);
        response.setStatus(e.getCode());
        e.printStackTrace();
        return e.getData();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        this.setCrossDomainHeader(request, response);
        response.setStatus(599);
        e.printStackTrace();
        return e.getMessage();
    }


    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter returnType,
            final MediaType selectedContentType,
            final Class<? extends HttpMessageConverter<?>> selectedConverterType,
            final ServerHttpRequest req, final ServerHttpResponse rsp) {

        HttpServletRequest request = ((ServletServerHttpRequest) req).getServletRequest();
        HttpServletResponse response = ((ServletServerHttpResponse) rsp).getServletResponse();
        this.setCrossDomainHeader(request, response);
        Result rspt = new Result(200);
        if (body == null) {
            rspt = new Result(204);
        } else if (body instanceof Result) {
            rspt = (Result) body;
        } else if (body instanceof JwtUser) {
            JwtUser jwt = (JwtUser) body;
            response.setHeader(Jwt.HeaderName, jwt.getToken());
            return null;
        } else {
            return body;
        }
        response.setStatus(rspt.getCode());
        return rspt.getData();

    }

    private final String headers = "Authorization-Jwt,WWW-Authenticate,Authorization,X-Location";

    // ???????????????
    private void setCrossDomainHeader(HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Headers", headers);
        response.setHeader("Access-Control-Expose-Headers", headers);
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
    }
}