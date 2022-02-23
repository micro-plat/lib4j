package  com.lib4j.pager;

import java.io.Serializable;
import java.text.MessageFormat;


public class Result implements Serializable {

    private int status;
    private Object data;
    public static Result NotAcceptable = new Result(406, "缺少必须参数");

    public Result() {
        this.status = 200;
    }

    public Result(int status) {
        this.status = status;
    }

    public Result(int status, Object data) {
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
    public Object getData() {
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
    public static Result Success(Object obj) {
        return new Result(200, obj);
    }

    /**
     * 构建失败响应
     * 
     * @param code
     * @param message
     * @return Result
     */
    public static Result Failed(Integer code, Object message) {
        return new Result(code, message);
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return MessageFormat.format("{0}[{1}]", this.status,this.data);
    }
}
