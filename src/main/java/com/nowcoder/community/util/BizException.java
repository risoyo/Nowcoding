package com.nowcoder.community.util;

public class BizException extends RuntimeException {


    private String errorCode;
    private String errorMessage;

    public BizException(String errorMessage) {
        super();
        this.errorCode = "-1";
        this.errorMessage = errorMessage;
    }

    public BizException(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


    public static BizException warn(String warnMessage) {
        return new BizException(NowcodingErrCode.DEFAULT_ERROR.respCode, NowcodingErrCode.DEFAULT_WARNING.respMessage);
    }

    public static BizException error(String warnMessage) {
        return new BizException(NowcodingErrCode.DEFAULT_ERROR.respCode, NowcodingErrCode.DEFAULT_ERROR.respMessage);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
