package com.pd.server.main.domain;

import java.util.Date;

public class StationsHeart {
    private String id;

    private String nodeId;

    private String nodeName;

    private String nodeNum;

    private String loraId;

    private String data;

    private Date createTime;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        sb.append(", nodeName=").append(nodeName);
        sb.append(", nodeNum=").append(nodeNum);
        sb.append(", loraId=").append(loraId);
        sb.append(", data=").append(data);
        sb.append(", createTime=").append(createTime);
        sb.append(", bz=").append(bz);
        sb.append(", sm=").append(sm);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}