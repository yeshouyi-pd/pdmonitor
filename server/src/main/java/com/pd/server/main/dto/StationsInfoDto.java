package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class StationsInfoDto extends PageDto{

    /**
    * 基站id
    */
    private String id;

    /**
    * 基站名称
    */
    private String nodeName;

    /**
    * 基站编号
    */
    private String nodeNum;

    /**
    * 信标编号
    */
    private String loraId;

    /**
    * 序号
    */
    private String xh;

    /**
    * 坐标点
    */
    private String gps;

    /**
    * 百度坐标点
    */
    private String bdGps;

    /**
    * 电话
    */
    private String dh;

    /**
    * 地址
    */
    private String dz;

    /**
    * 部门编号
    */
    private String deptCode;

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

    /**
    * 最后在线时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastOnlineTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(String nodeNum) {
        this.nodeNum = nodeNum;
    }

    public String getLoraId() {
        return loraId;
    }

    public void setLoraId(String loraId) {
        this.loraId = loraId;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getBdGps() {
        return bdGps;
    }

    public void setBdGps(String bdGps) {
        this.bdGps = bdGps;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
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

    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", nodeName=").append(nodeName);
            sb.append(", nodeNum=").append(nodeNum);
            sb.append(", loraId=").append(loraId);
            sb.append(", xh=").append(xh);
            sb.append(", gps=").append(gps);
            sb.append(", bdGps=").append(bdGps);
            sb.append(", dh=").append(dh);
            sb.append(", dz=").append(dz);
            sb.append(", deptCode=").append(deptCode);
            sb.append(", createTime=").append(createTime);
            sb.append(", updateTime=").append(updateTime);
            sb.append(", bz=").append(bz);
            sb.append(", sm=").append(sm);
            sb.append(", remark=").append(remark);
            sb.append(", lastOnlineTime=").append(lastOnlineTime);
        sb.append("]");
        return sb.toString();
    }

}