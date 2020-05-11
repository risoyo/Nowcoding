package com.nowcoder.community.common;

import com.nowcoder.community.util.NowcodingErrCode;

/**
 * 通用返回对象
 */
public class returnMessage<T> {
    private String respCode;// 错误码
    private String respInfo;// 错误信息
    private T data;//返回具体内容

    protected returnMessage() {
    }

    protected returnMessage(String respCode, String respInfo, T data) {
        this.respCode = respCode;
        this.respInfo = respInfo;
        this.data = data;
    }

    /**
     * 成功返回结果
     * <p>
     * <p>
     * respCode与respMessage为默认响应
     */
    public static returnMessage<Object> success() {
        System.out.println("now im here");
        return new returnMessage<>(NowcodingErrCode.SUCCESS.respCode(), NowcodingErrCode.SUCCESS.respMessage(), null);
    }


    /**
     * 成功返回结果
     *
     * @param data 返回的数据
     *             respCode与respMessage为默认响应
     */
    public static <T> returnMessage<T> success(T data) {
        return new returnMessage<T>(NowcodingErrCode.SUCCESS.respCode(), NowcodingErrCode.SUCCESS.respMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     *                respCode为默认响应
     */
    public static <T> returnMessage<T> success(T data, String message) {
        return new returnMessage<T>(NowcodingErrCode.SUCCESS.respCode(), message, data);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
