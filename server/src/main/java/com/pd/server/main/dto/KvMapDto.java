package com.pd.server.main.dto;


/**
 * 用户前端want 下拉框显示
 */
public class KvMapDto {

    private  String text;

    private  String code;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
