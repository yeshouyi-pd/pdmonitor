package com.pd.server.main.dto.basewx.my;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EventDwDto {

    private String sbbh;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cxsj;

    private String sbwz;//

    private String fwj;//江豚出现方位角

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public Date getCxsj() {
        return cxsj;
    }

    public void setCxsj(Date cxsj) {
        this.cxsj = cxsj;
    }

    public String getSbwz() {
        return sbwz;
    }

    public void setSbwz(String sbwz) {
        this.sbwz = sbwz;
    }

    public String getFwj() {
        return fwj;
    }

    public void setFwj(String fwj) {
        this.fwj = fwj;
    }
}
