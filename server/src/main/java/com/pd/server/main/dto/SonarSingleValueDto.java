package com.pd.server.main.dto;


public class SonarSingleValueDto {

    /**
    * 
    */
    private String id;

    /**
    * sonar_single_target表id
    */
    private String singleTargetId;

    /**
    * 目标强度
    */
    private Double dTs;

    /**
    * 目标距离换能器距离
    */
    private Double dRange;

    /**
    * 目标出现时间
    */
    private String lTime;

    /**
    * 生物类别代码
    */
    private Integer nBioTypeCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSingleTargetId() {
        return singleTargetId;
    }

    public void setSingleTargetId(String singleTargetId) {
        this.singleTargetId = singleTargetId;
    }

    public Double getDTs() {
        return dTs;
    }

    public void setDTs(Double dTs) {
        this.dTs = dTs;
    }

    public Double getDRange() {
        return dRange;
    }

    public void setDRange(Double dRange) {
        this.dRange = dRange;
    }

    public String getLTime() {
        return lTime;
    }

    public void setLTime(String lTime) {
        this.lTime = lTime;
    }

    public Integer getNBioTypeCode() {
        return nBioTypeCode;
    }

    public void setNBioTypeCode(Integer nBioTypeCode) {
        this.nBioTypeCode = nBioTypeCode;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", singleTargetId=").append(singleTargetId);
            sb.append(", dTs=").append(dTs);
            sb.append(", dRange=").append(dRange);
            sb.append(", lTime=").append(lTime);
            sb.append(", nBioTypeCode=").append(nBioTypeCode);
        sb.append("]");
        return sb.toString();
    }

}