package com.pd.server.main.dto;


public class SeaSurfaceSalinityDto extends PageDto{

    /**
    * 主键
    */
    private String id;

    /**
    * 图片路径
    */
    private String imgUrl;

    /**
    * 图片日期
    */
    private String tprq;

    /**
    * 备注
    */
    private String bz;

    /**
    * 说明
    */
    private String sm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTprq() {
        return tprq;
    }

    public void setTprq(String tprq) {
        this.tprq = tprq;
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
            sb.append(", imgUrl=").append(imgUrl);
            sb.append(", tprq=").append(tprq);
            sb.append(", bz=").append(bz);
            sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }

}