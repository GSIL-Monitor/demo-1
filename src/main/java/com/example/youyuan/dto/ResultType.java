package com.example.youyuan.dto;

public enum ResultType {
    /**
     * 2开头，表示成功
     */
    SUCCESS(2000,"请求成功")
    ;






    ResultType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    int code;
    String msg;

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
}
