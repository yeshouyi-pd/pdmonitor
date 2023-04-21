package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class SendCommandDto {

    /**
    * 主键
    */
    private String id;

    /**
    * 基站编号
    */
    private String nodeId;

    /**
    * 信标编号
    */
    private String loraId;

    /**
    * 序号
    */
    private String serialNumber;

    /**
    * 时间整数部分
    */
    private String wholeSec;

    /**
    * 时间小数部分
    */
    private String fracSec;

    /**
    * 相关参数
    */
    private String upChirpCor;

    /**
    * 相关参数
    */
    private String downChirpCor;

    /**
    * 晶振误差
    */
    private String ppm;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
    * 修改时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
    * 备注
    */
    private String bz;

    /**
    * 说明
    */
    private String sm;

    /**
    * 标注
    */
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

    public String getFracSec() {
        return fracSec;
    }

    public void setFracSec(String fracSec) {
        this.fracSec = fracSec;
    }

    public String getUpChirpCor() {
        return upChirpCor;
    }

    public void setUpChirpCor(String upChirpCor) {
        this.upChirpCor = upChirpCor;
    }

    public String getDownChirpCor() {
        return downChirpCor;
    }

    public void setDownChirpCor(String downChirpCor) {
        this.downChirpCor = downChirpCor;
    }

    public String getPpm() {
        return ppm;
    }

    public void setPpm(String ppm) {
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