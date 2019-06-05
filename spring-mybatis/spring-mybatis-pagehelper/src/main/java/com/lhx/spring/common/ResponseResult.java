package com.lhx.spring.common;

public class ResponseResult<T> {
    private int code;
    private String msg;
    private T data;

    public ResponseResult() {
        this.code = 20000;
        this.msg = "success";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public ResponseResult setData(T data) {
        this.data = data;
        return this;
    }
}
