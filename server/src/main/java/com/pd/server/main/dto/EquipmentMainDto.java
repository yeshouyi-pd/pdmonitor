package com.pd.server.main.dto;

import java.util.List;

public class EquipmentMainDto {

    private String  sbbh ;

    private String  sbmc ;

    private List<EquipmentMainListDto> list;


    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    public List<EquipmentMainListDto> getList() {
        return list;
    }

    public void setList(List<EquipmentMainListDto> list) {
        this.list = list;
    }
}
