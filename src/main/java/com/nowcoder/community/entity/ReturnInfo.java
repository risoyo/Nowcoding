package com.nowcoder.community.entity;

public class ReturnInfo {
    private int id;
    private String respCode;
    private String respInfo;
    private String sysCode;

    @Override
    public String toString() {
        return "ReturnInfo{" +
                "id=" + id +
                ", respCode='" + respCode + '\'' +
                ", respInfo='" + respInfo + '\'' +
                ", sysCode='" + sysCode + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


}
