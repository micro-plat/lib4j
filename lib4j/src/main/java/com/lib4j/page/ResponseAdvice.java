package com.lib4j.page;

import java.lang.reflect.Method;

import com.lib4j.http.Response;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(final MethodParameter returnType,
            final Class<? extends HttpMessageConverter<?>> converterType) {

        Method method = returnType.getMethod();
        return !method.getReturnType().isAssignableFrom(String.class);
    }

    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter returnType,
            final MediaType selectedContentType,
            final Class<? extends HttpMessageConverter<?>> selectedConverterType,
            final ServerHttpRequest request, final ServerHttpResponse response) {

        Result rspt = new Result(200);
        if (body == null) {
            rspt = new Result(204);
        } else if (body instanceof Result) {
            rspt = (Result) body;
        } else {
            rspt = new Result(200, body);
        }
        response.setStatusCode(HttpStatus.resolve(200));
        return rspt;
    }
}