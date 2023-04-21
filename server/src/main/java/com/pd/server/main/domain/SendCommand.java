package com.pd.server.main.domain;

import java.util.Date;

public class SendCommand {
    private String id;

    private String nodeId;

    private String loraId;

    private String serialNumber;

    private String wholeSec;

    private Double fracSec;

    private Float upChirpCor;

    private Float downChirpCor;

    private Double ppm;

    private Date createTime;

    private Date updateTime;

    private String bz;

    private String sm;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getLoraId() {
        return loraId;
    }

    public void setLoraId(String loraId) {
        this.loraId = loraId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getWholeSec() {
        return wholeSec;
    }

    public void setWholeSec(String wholeSec) {
        this.wholeSec = wholeSec;
    }

    public Double getFracSec() {
        return fracSec;
    }

    public void setFracSec(Double fracSec) {
        this.fracSec = fracSec;
    }

    public Float getUpChirpCor() {
        return upChirpCor;
    }

    public void setUpChirpCor(Float upChirpCor) {
        this.upChirpCor = upChirpCor;
    }

    public Float getDownChirpCor() {
        return downChirpCor;
    }

    public void setDownChirpCor(Float downChirpCor) {
        this.downChirpCor = downChirpCor;
    }

    public Double getPpm() {
        return ppm;
    }

    public void setPpm(Double ppm) {
        this.ppm = ppm;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nodeId=").append(nodeId);
        sb.append(", loraId=").append(loraId);
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", wholeSec=").append(wholeSec);
        sb.append(", fracSec=").append(fracSec);
        sb.append(", upChirpCor=").append(upChirpCor);
        sb.append(", downChirpCor=").append(downChirpCor);
        sb.append(", ppm=").append(ppm);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", bz=").append(bz);
        sb.append(", sm=").append(sm);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}