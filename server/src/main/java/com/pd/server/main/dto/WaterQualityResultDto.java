package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class WaterQualityResultDto extends PageDto{

    /**
    * 主键
    */
    private String id;

    /**
    * 设备IP
    */
    private String ip;

    /**
    * 设备端口号
    */
    private Integer port;

    /**
    * 数据中心
    */
    private String datacenter;

    /**
    * 检测项目
    */
    private String jcxm;

    /**
    * 指令
    */
    private String instruct;

    /**
    * 检测数据
    */
    private String dataResult;

    /**
    * 原始数据
    */
    private String dataOriginal;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
    * 备注
    */
    private String bz;

    /**
    * 
    */
    private String sm1;

    /**
    * 
    */
    private String sm2;

    /**
    * 
    */
    private String sm3;

    /**
    * 
    */
    private String sm4;

    private String deviceId;//设备id

    private String chooseTimeType;

    private String sbmc;

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDatacenter() {
        return datacenter;
    }

    public void setDatacenter(String datacenter) {
        this.datacenter = datacenter;
    }

    public String getJcxm() {
        return jcxm;
    }

    public void setJcxm(String jcxm) {
        this.jcxm = jcxm;
    }

    public String getInstruct() {
        return instruct;
    }

    public void setInstruct(String instruct) {
        this.instruct = instruct;
    }

    public String getDataResult() {
        return dataResult;
    }

    public void setDataResult(String dataResult) {
        this.dataResult = dataResult;
    }

    public String getDataOriginal() {
        return dataOriginal;
    }

    public void setDataOriginal(String dataOriginal) {
        this.dataOriginal = dataOriginal;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChooseTimeType() {
        return chooseTimeType;
    }

    public void setChooseTimeType(String chooseTimeType) {
        this.chooseTimeType = chooseTimeType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", ip=").append(ip);
            sb.append(", port=").append(port);
            sb.append(", datacenter=").append(datacenter);
            sb.append(", jcxm=").append(jcxm);
            sb.append(", instruct=").append(instruct);
            sb.append(", dataResult=").append(dataResult);
            sb.append(", dataOriginal=").append(dataOriginal);
            sb.append(", createTime=").append(createTime);
            sb.append(", bz=").append(bz);
            sb.append(", sm1=").append(sm1);
            sb.append(", sm2=").append(sm2);
            sb.append(", sm3=").append(sm3);
            sb.append(", sm4=").append(sm4);
        sb.append("]");
        return sb.toString();
    }

}