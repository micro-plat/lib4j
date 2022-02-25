package com.lib4j.pager;

import java.io.Serializable;
import java.text.MessageFormat;

public class Result<T> implements Serializable {

    protected int status;
    protected T data;
    public static Result<String> NotAcceptable = new Result<String>(406, "缺少必须参数");

    public Result() {
        this.status = 200;
    }

    public Result(int status) {
        this.status = status;
    }

    public Result(int status, T data) {
        this.status = status;
        this.data = data;
    }

    /**
     * 获取状态码
     * 
     * @return int
     */
    public int getCode() {
        return this.status;
    }

    /**
     * 获取响应结果
     * 
     * @return Object
     */
    public T getData() {
        return this.data;
    }

    /**
     * 是否为状态200
     * 
     * @return boolean
     */
    public boolean IsSuccess() {
        return this.status >= 200 && this.status < 400;
    }

    /**
     * 构建成功响应
     * 
     * @param obj
     * @return Result
     */
    public static <T> Result<T> Success(T obj) {
        return new Result<T>(200, obj);
    }

    /**
     * 构建失败响应
     * 
     * @param code
     * @param message
     * @return Result
     */
    public static <T> Result<T> Failed(Integer code, T message) {
        return new Result<T>(code, message);
    }

    public static <T> Result<T> Failed(Integer code) {
        return new Result<T>(code);
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]", this.status, this.data);
    }
}
