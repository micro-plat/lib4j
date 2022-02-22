package com.lib4j.http;

import java.text.MessageFormat;

public class Response {
    private int status;
    private Object result;

    public static Response NotAcceptable = new Response(406, "缺少必须参数");

    public Response() {
        this.status = 200;
    }

    public Response(int status) {
        this.status = status;
    }

    public Response(int status, Object result) {
        this.status = status;
        this.result = result;
    }

    /**
     * 获取状态码
     * 
     * @return int
     */
    public int GetStatus() {
        return this.status;
    }

    /**
     * 获取响应结果
     * 
     * @return Object
     */
    public Object GetResult() {
        return this.result;
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
     * @return Response
     */
    public static Response Success(Object obj) {
        return new Response(200, obj);
    }

    /**
     * 构建失败响应
     * 
     * @param code
     * @param message
     * @return Response
     */
    public static Response Failed(Integer code, Object message) {
        return new Response(code, message);
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]", this.status, this.result);
    }

    /**
     * @return String
     */
    public String ToJson() {
        return "";
    }

}
