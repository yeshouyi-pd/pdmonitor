package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class WaterQualityNewDto extends PageDto {

    /**
    * 
    */
    private String id;

    /**
    * 
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    /**
    * 
    */
    private String sbbh;

    /**
    * 溶解氧
    */
    private String oxidative;

    /**
    * 叶绿素
    */
    private String chlorophyll;

    /**
    * ph
    */
    private String ph;

    /**
    * 氨氮
    */
    private String ad;

    /**
    * 
    */
    private String bz;

    /**
    * 
    */
    private String sm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getOxidative() {
        return oxidative;
    }

    public void setOxidative(String oxidative) {
        this.oxidative = oxidative;
    }

    public String getChlorophyll() {
        return chlorophyll;
    }

    public void setChlorophyll(String chlorophyll) {
        this.chlorophyll = chlorophyll;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", sbbh=").append(sbbh);
            sb.append(", oxidative=").append(oxidative);
            sb.append(", chlorophyll=").append(chlorophyll);
            sb.append(", ph=").append(ph);
            sb.append(", ad=").append(ad);
            sb.append(", bz=").append(bz);
            sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }

}