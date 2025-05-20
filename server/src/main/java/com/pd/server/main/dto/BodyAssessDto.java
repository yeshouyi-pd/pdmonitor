package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class BodyAssessDto {

    /**
    * 
    */
    private String id;

    /**
    * 体积
    */
    private String volume;

    /**
    * BAI
    */
    private String bai;

    /**
    * 体长
    */
    private String bodyLength;

    /**
    * 估算年龄段
    */
    private String ageGroup;

    /**
    * 总体重(kg)
    */
    private String totalWeight;

    /**
    * 总体重BMI值
    */
    private String totalBmi;

    /**
    * 胖瘦判定
    */
    private String fatThin;

    /**
    * 图片
    */
    private String imgUrl;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    /**
    * 备注
    */
    private String bz;

    /**
    * 说明
    */
    private String sm;

    private String uavFlyVideoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getBai() {
        return bai;
    }

    public void setBai(String bai) {
        this.bai = bai;
    }

    public String getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(String bodyLength) {
        this.bodyLength = bodyLength;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getTotalBmi() {
        return totalBmi;
    }

    public void setTotalBmi(String totalBmi) {
        this.totalBmi = totalBmi;
    }

    public String getFatThin() {
        return fatThin;
    }

    public void setFatThin(String fatThin) {
        this.fatThin = fatThin;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
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

    public String getUavFlyVideoId() {
        return uavFlyVideoId;
    }

    public void setUavFlyVideoId(String uavFlyVideoId) {
        this.uavFlyVideoId = uavFlyVideoId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", volume=").append(volume);
            sb.append(", bai=").append(bai);
            sb.append(", bodyLength=").append(bodyLength);
            sb.append(", ageGroup=").append(ageGroup);
            sb.append(", totalWeight=").append(totalWeight);
            sb.append(", totalBmi=").append(totalBmi);
            sb.append(", fatThin=").append(fatThin);
            sb.append(", imgUrl=").append(imgUrl);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", bz=").append(bz);
            sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }

}