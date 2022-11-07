package com.pd.server.main.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class EquipmentMainListDto {

    private String id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    private String tplj;


    private String deptcode;


    private  PlayDtoWav  playDto;

    public PlayDtoWav getPlayDto() {
        return playDto;
    }

    public void setPlayDto(PlayDtoWav playDto) {
        this.playDto = playDto;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getTplj() {
        return tplj;
    }

    public void setTplj(String tplj) {
        if(StringUtils.isNotBlank(tplj)){
            this.tplj = tplj.replaceAll("txt","jpg");
        }else{
            this.tplj = tplj;
        }
    }
}
