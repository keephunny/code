package com.alarm.monitor.vo;

public class RespResult {
    private int code;
    private String msg;
    private Object data;

    public RespResult() {
        this.code = 200;
        this.msg = "操作成功";
    }

    public RespResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
