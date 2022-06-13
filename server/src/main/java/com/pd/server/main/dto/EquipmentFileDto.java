package com.pd.server.main.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EquipmentFileDto extends PageDto{

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

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String deptcode;

    private String sbmc;

    private  PlayDto playDto;

    private String xmbh;

    private String txtSrc;

    private boolean hasTxt;

    public PlayDto getPlayDto() {
        return playDto;
    }

    public void setPlayDto(PlayDto playDto) {
        this.playDto = playDto;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    private boolean hasAudio;//图片是否有对应的音频

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isHasAudio() {
        return hasAudio;
    }

    public void setHasAudio(boolean hasAudio) {
        this.hasAudio = hasAudio;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getTxtSrc() {
        return txtSrc;
    }

    public void setTxtSrc(String txtSrc) {
        this.txtSrc = txtSrc;
    }

    public boolean getHasTxt() {
        return hasTxt;
    }

    public void setHasTxt(boolean hasTxt) {
        this.hasTxt = hasTxt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", sbbh=").append(sbbh);
            sb.append(", tplj=").append(tplj);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }

}