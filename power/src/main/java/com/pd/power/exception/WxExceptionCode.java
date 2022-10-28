package com.pd.power.exception;

public enum WxExceptionCode {

    CHECK_FALSE("验证失败"),
    ;

    private String desc;

    WxExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
