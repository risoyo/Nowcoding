package com.nowcoder.community.entity;

public class ReturnMessage<T> {
    private int id;
    private String respCode;// 错误码
    private String respInfo;// 错误信息
    private String sysCode = "000000";// 系统码
    private String message;//提示信息
    private T data;//返回具体内容

    public ReturnMessage(String respCode,String respInfo,String message, T data) {
        super();
        this.respCode = respCode;
        this.respInfo = respInfo;
        this.message = message;
        this.data = data;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespInfo() {
        return respInfo;
    }

    public void setRespInfo(String respInfo) {
        this.respInfo = respInfo;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
