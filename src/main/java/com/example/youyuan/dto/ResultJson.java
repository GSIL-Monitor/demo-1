package com.example.youyuan.dto;

public class ResultJson {
    int code;
    String msg;
    Object data;
    Exception e;

    public ResultJson(ResultType type, Object data) {
        this.data = data;
        this.code = type.getCode();
        this.msg = type.getMsg();
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
