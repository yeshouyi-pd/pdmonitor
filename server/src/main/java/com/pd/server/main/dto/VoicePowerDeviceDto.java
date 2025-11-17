package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class VoicePowerDeviceDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 设备编号
    */
    private String sbbh;

    /**
    * 主题名字
    */
    private String topicName;

    /**
    * 日期
    */
    private String rq;

    /**
    * 发送指令时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sendTime;

    /**
    * 是否播放|0未播放1播放
    */
    private Integer isPlay;

    /**
    * 停止指令时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date stopTime;

    /**
     * 部门编号
     */
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