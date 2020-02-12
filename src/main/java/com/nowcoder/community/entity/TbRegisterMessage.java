package com.nowcoder.community.entity;

import java.util.Date;

// 封装注册消息表的内容
public class TbRegisterMessage {
    private int id;
    private String email;
    private int verifyCode;
    private String verifyMessage;
    private int usable;
    private int status;
    private Date createTime;

    @Override
    public String toString() {
        return "TbRegistMessage{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", verifyCode=" + verifyCode +
                ", verifyMessage='" + verifyMessage + '\'' +
                ", usable=" + usable +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(int verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyMessage() {
        return verifyMessage;
    }

    public void setVerifyMessage(String verifyMessage) {
        this.verifyMessage = verifyMessage;
    }

    public int getUsable() {
        return usable;
    }

    public void setUsable(int usable) {
        this.usable = usable;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
