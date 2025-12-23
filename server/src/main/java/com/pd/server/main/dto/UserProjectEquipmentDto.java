package com.pd.server.main.dto;

/**
 * 用户关联的项目和设备信息DTO
 */
public class UserProjectEquipmentDto {

    /**
     * 用户账号
     */
    private String usercode;

    /**
     * 项目编号
     */
    private String xmbh;

    /**
     * 项目名称
     */
    private String xmmc;

    /**
     * 设备序列号列表（逗号分隔）
     */
    private String sbsnList;

    /**
     * 设备名称列表（逗号分隔）
     */
    private String sbmcList;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getSbsnList() {
        return sbsnList;
    }

    public void setSbsnList(String sbsnList) {
        this.sbsnList = sbsnList;
    }

    public String getSbmcList() {
        return sbmcList;
    }

    public void setSbmcList(String sbmcList) {
        this.sbmcList = sbmcList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserProjectEquipmentDto{");
        sb.append("usercode='").append(usercode).append('\'');
        sb.append(", xmbh='").append(xmbh).append('\'');
        sb.append(", xmmc='").append(xmmc).append('\'');
        sb.append(", sbsnList='").append(sbsnList).append('\'');
        sb.append(", sbmcList='").append(sbmcList).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

