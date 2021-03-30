package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class WaterDataDto {

    /**
    * 
    */
    private String id;

    /**
    * 所属监测点
    */
    private String deptcode;

    /**
    * 数据中心代码
    */
    private String centerCode;

    /**
    * 数据中心名称
    */
    private String centerName;

    /**
    * 数据中心IP
    */
    private String ip;

    /**
    * 数据中心端口
    */
    private Integer port;

    /**
    * 数据中心说明
    */
    private String sm;

    /**
    * 数据中心 备注
    */
    private String bz;

    /**
    * 说明1
    */
    private String sm1;

    /**
    * 说明2
    */
    private String sm2;

    /**
    * 说明3
    */
    private String sm3;

    /**
    * 说明4
    */
    private String sm4;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
    * 创建人员
    */
    private String createBy;

    /**
    * 更新时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gxsj;

    /**
    * 更新人员
    */
    private String updateBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
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

    public String getSm1() {
        return sm1;
    }

    public void setSm1(String sm1) {
        this.sm1 = sm1;
    }

    public String getSm2() {
        return sm2;
    }

    public void setSm2(String sm2) {
        this.sm2 = sm2;
    }

    public String getSm3() {
        return sm3;
    }

    public void setSm3(String sm3) {
        this.sm3 = sm3;
    }

    public String getSm4() {
        return sm4;
    }

    public void setSm4(String sm4) {
        this.sm4 = sm4;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", deptcode=").append(deptcode);
            sb.append(", centerCode=").append(centerCode);
            sb.append(", centerName=").append(centerName);
            sb.append(", ip=").append(ip);
            sb.append(", port=").append(port);
            sb.append(", sm=").append(sm);
            sb.append(", bz=").append(bz);
            sb.append(", sm1=").append(sm1);
            sb.append(", sm2=").append(sm2);
            sb.append(", sm3=").append(sm3);
            sb.append(", sm4=").append(sm4);
            sb.append(", createTime=").append(createTime);
            sb.append(", createBy=").append(createBy);
            sb.append(", gxsj=").append(gxsj);
            sb.append(", updateBy=").append(updateBy);
        sb.append("]");
        return sb.toString();
    }

}