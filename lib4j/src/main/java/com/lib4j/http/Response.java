package com.lib4j.http;

public class Response {
    private int status;
    private String result;

    public Response(int status, String result) {
        this.status = status;
        this.result = result;
    }

    public int GetStatus() {
        return this.status;
    }

    public String GetResult() {
        return this.result;
    }

    public boolean IsSuccess() {
        return this.status == 200;
    }
}
