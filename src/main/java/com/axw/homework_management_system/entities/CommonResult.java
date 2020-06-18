package com.axw.homework_management_system.entities;

public class CommonResult<T> {
    private int code;
    private String info;
    private T data;

    public CommonResult() {
    }

    public CommonResult(int code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
