package com.pd.server.exception;

public class WxException extends RuntimeException{

    private WxExceptionCode code;

    public WxException(WxExceptionCode code) {
        super(code.getDesc());
        this.code = code;
    }

    public WxExceptionCode getCode() {
        return code;
    }

    public void setCode(WxExceptionCode code) {
        this.code = code;
    }

    /**
     * 不写入堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}