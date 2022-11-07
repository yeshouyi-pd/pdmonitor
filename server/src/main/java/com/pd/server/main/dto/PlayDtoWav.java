package com.pd.server.main.dto;

import org.apache.commons.lang.StringUtils;

public class PlayDtoWav {

     private  String  src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        if(StringUtils.isNotBlank(src)){
            this.src = src.replaceAll("txt","wav");
        }else{
            this.src = src;
        }
    }
}
