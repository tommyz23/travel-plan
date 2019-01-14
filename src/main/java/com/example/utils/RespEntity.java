package com.example.utils;

public class RespEntity {
    private int code;
    private String msg;
    private Object data;

    public RespEntity(RespCode respCode){
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespEntity(RespCode respCode, Object data){
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
