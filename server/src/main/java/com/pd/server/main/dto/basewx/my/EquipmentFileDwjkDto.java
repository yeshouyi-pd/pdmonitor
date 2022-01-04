package com.pd.server.main.dto.basewx.my;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EquipmentFileDwjkDto {

    /**
     *
     */
    private String id;

    /**
     * 设备唯一标识
     */
    private String sbbh;

    /**
     * 图片路径
     */
    private String tplj;

    /**
     * 采集时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")


    private Date cjsj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getTplj() {
        return tplj;
    }

    public void setTplj(String tplj) {
        this.tplj = tplj;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }
}
