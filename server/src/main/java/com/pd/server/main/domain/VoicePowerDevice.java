package com.pd.server.main.domain;

import java.util.Date;

public class VoicePowerDevice {
    private String id;

    private String sbbh;

    private String topicName;

    private String rq;

    private Date sendTime;

    private Integer isPlay;

    private Date stopTime;

    private String deptcode;

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

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getIsPlay() {
        return isPlay;
    }

    public void setIsPlay(Integer isPlay) {
        this.isPlay = isPlay;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sbbh=").append(sbbh);
        sb.append(", topicName=").append(topicName);
        sb.append(", rq=").append(rq);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", isPlay=").append(isPlay);
        sb.append(", stopTime=").append(stopTime);
        sb.append(", deptcode=").append(deptcode);
        sb.append("]");
        return sb.toString();
    }
}