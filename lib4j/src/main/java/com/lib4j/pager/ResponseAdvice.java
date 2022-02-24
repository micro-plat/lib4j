package  com.lib4j.pager;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import com.lib4j.errs.CodeException;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(final MethodParameter returnType,
            final Class<? extends HttpMessageConverter<?>> converterType) {
        
        Method method = returnType.getMethod();
        return method.getReturnType().isAssignableFrom(Result.class);
    }

    @ExceptionHandler(value = CodeException.class)
    @ResponseBody
    public Object bizExceptionHandler(HttpServletResponse response, CodeException e) {
        response.setStatus(e.getCode());
        return e.getData();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandler(HttpServletResponse response, Exception e) {
        response.setStatus(599);
        return null;
    }

    @ResponseBody
    public Object handler(final Object body, HttpServletResponse response, Exception e) {
        response.setStatus(599);
        return null;
    }

    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter returnType,
            final MediaType selectedContentType,
            final Class<? extends HttpMessageConverter<?>> selectedConverterType,
            final ServerHttpRequest req, final ServerHttpResponse rsp) {
        Result rspt = new Result(200);
        if (body == null) {
            rspt = new Result(204);
        } else if (body instanceof Result) {
            rspt = (Result) body;
        } else {
            return body;
        }
        ServletServerHttpResponse responseTemp = (ServletServerHttpResponse) rsp;
        HttpServletResponse response = responseTemp.getServletResponse();
        response.setStatus(rspt.getCode());
        return rspt.getData();

    }
}