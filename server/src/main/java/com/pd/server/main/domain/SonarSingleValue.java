package com.pd.server.main.domain;

public class SonarSingleValue {
    private String id;

    private String singleTargetId;

    private Double dTs;

    private Double dRange;

    private Integer nBioTypeCode;

    private String lTime;

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

    public Double getdTs() {
        return dTs;
    }

    public void setdTs(Double dTs) {
        this.dTs = dTs;
    }

    public Double getdRange() {
        return dRange;
    }

    public void setdRange(Double dRange) {
        this.dRange = dRange;
    }

    public Integer getnBioTypeCode() {
        return nBioTypeCode;
    }

    public void setnBioTypeCode(Integer nBioTypeCode) {
        this.nBioTypeCode = nBioTypeCode;
    }

    public String getlTime() {
        return lTime;
    }

    public void setlTime(String lTime) {
        this.lTime = lTime;
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
        sb.append(", nBioTypeCode=").append(nBioTypeCode);
        sb.append(", lTime=").append(lTime);
        sb.append("]");
        return sb.toString();
    }
}