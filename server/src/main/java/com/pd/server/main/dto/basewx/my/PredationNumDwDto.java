package com.pd.server.main.dto.basewx.my;

import java.util.List;

public class PredationNumDwDto {

    private String id;

    private Integer cxcs;

    private Integer bscs;

    private Integer sjcs;

    private List<EquipmentFileAlarmEventDwDto> detailList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCxcs() {
        return cxcs;
    }

    public void setCxcs(Integer cxcs) {
        this.cxcs = cxcs;
    }

    public Integer getBscs() {
        return bscs;
    }

    public void setBscs(Integer bscs) {
        this.bscs = bscs;
    }

    public Integer getSjcs() {
        return sjcs;
    }

    public void setSjcs(Integer sjcs) {
        this.sjcs = sjcs;
    }

    public List<EquipmentFileAlarmEventDwDto> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<EquipmentFileAlarmEventDwDto> detailList) {
        this.detailList = detailList;
    }
}
