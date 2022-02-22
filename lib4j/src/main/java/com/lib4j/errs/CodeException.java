package com.lib4j.errs;

import java.text.MessageFormat;

public class CodeException extends RuntimeException {
    private String code;
    private String message;

    public CodeException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeException(Exception e) {
        this.code = "500";
        this.message = e.getMessage();
    }

    /**
     * 获取错误码
     * 
     * @return String
     */
    public String getCode() {
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
