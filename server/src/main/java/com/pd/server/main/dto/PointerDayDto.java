package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class PointerDayDto {

    /**
    * 主键
    */
    private String id;

    /**
    * 分贝值
    */
    private String decibelValue;

    /**
    * 采集时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
    * 说明
    */
    private String sm;

    /**
    * 备注
    */
    private String bz;

    /**
    * 备注字段
    */
    private String bz1;

    /**
    * 备注字段
    */
    private String bz2;

    /**
    * 备注字段
    */
    private String bz3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDecibelValue() {
        return decibelValue;
    }

    public void setDecibelValue(String decibelValue) {
        this.decibelValue = decibelValue;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getBz1() {
        return bz1;
    }

    public void setBz1(String bz1) {
        this.bz1 = bz1;
    }

    public String getBz2() {
        return bz2;
    }

    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }

    public String getBz3() {
        return bz3;
    }

    public void setBz3(String bz3) {
        this.bz3 = bz3;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", decibelValue=").append(decibelValue);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", createTime=").append(createTime);
            sb.append(", sm=").append(sm);
            sb.append(", bz=").append(bz);
            sb.append(", bz1=").append(bz1);
            sb.append(", bz2=").append(bz2);
            sb.append(", bz3=").append(bz3);
        sb.append("]");
        return sb.toString();
    }

}