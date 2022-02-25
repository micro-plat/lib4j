package com.lib4j.errs;

import java.text.MessageFormat;

public class CodeException extends RuntimeException {

    private int code;
    private String message;
    private Object data;

    public CodeException(int code) {
        this.code = code;
    }
    public CodeException(int code, Exception e) {
        this.code = 500;
        this.message = e.getMessage();
    }

    public CodeException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeException(int code, String message, Object data) {
        this.code = code;
        this.data = data;
    }

    public CodeException(Exception e) {
        this.code = 500;
        this.message = e.getMessage();
    }

    /**
     * 获取错误码
     * 
     * @return String
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取错误消息
     * 
     * @return String
     */
    @Override
    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    /**
     * @return Throwable
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]", this.code, this.message);
    }
}
