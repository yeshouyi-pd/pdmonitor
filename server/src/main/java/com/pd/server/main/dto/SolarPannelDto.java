package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class SolarPannelDto extends PageDto{

    /**
    * 数据 id
    */
    private String id;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
    * 删除标志 1 删除 0 未删除
    */
    private String del;

    /**
    * 设备号
    */
    private String deviceId;

    /**
    * channelId
    */
    private String channelId;

    /**
    * 设备编号
    */
    private String deviceNumber;

    /**
    * 设备名称
    */
    private String deviceName;

    /**
    * 设备密码
    */
    private String password;

    /**
    * 分组 ID
    */
    private Integer groupId;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 更新时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
    * 心跳时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date heartbeatTime;

    /**
    * 是否在线
    */
    private Integer online;

    /**
    * 手动控制开关
    */
    private Integer handSwitch;

    /**
    * 
    */
    private Integer autoMode;

    /**
    * 经度
    */
    private Double longitude;

    /**
    * 纬度
    */
    private Double latitude;

    /**
    * 设备机内温度
    */
    private String temperature;

    /**
    * 电池电压
    */
    private String batteryVoltage;

    /**
    * 当日用电量
    */
    private String dailyElectricityConsumption;

    /**
    * 当月累计用电量
    */
    private String monthlyElectricityConsumption;

    /**
    * 当日累计充电量
    */
    private String dailyCharge;

    /**
    * 当月累计充电量
    */
    private String monthlyCharge;

    /**
    * 电池电流
    */
    private String batteryCurrent;

    /**
    * 太阳能板电压
    */
    private String solarPanelVoltage;

    /**
    * 太阳能板电流
    */
    private String solarPannelCurrent;

    /**
    * 发电功率
    */
    private String powerGeneration;

    /**
    * 负载电压
    */
    private String loadVoltage;

    /**
    * 负载电流
    */
    private String loadCurrent;

    /**
    * 电池剩余电量
    */
    private String batteryPercent;

    /**
    * 
    */
    private Integer type;

    /**
    * 
    */
    private String errmsg;

    /**
    * 
    */
    private String ledmsg;

    /**
    * 
    */
    private Integer cardinfo;

    /**
    * 
    */
    private String cardNumber;

    /**
    * 最低电压
    */
    private Double minVoltage;

    /**
    * 最高电压
    */
    private Double maxVoltage;

    /**
    * 
    */
    private String csq;

    /**
    * 
    */
    private Integer tipcount;

    /**
    * 电池校准
    */
    private String batterycorrect;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(Date heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getHandSwitch() {
        return handSwitch;
    }

    public void setHandSwitch(Integer handSwitch) {
        this.handSwitch = handSwitch;
    }

    public Integer getAutoMode() {
        return autoMode;
    }

    public void setAutoMode(Integer autoMode) {
        this.autoMode = autoMode;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(String batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public String getDailyElectricityConsumption() {
        return dailyElectricityConsumption;
    }

    public void setDailyElectricityConsumption(String dailyElectricityConsumption) {
        this.dailyElectricityConsumption = dailyElectricityConsumption;
    }

    public String getMonthlyElectricityConsumption() {
        return monthlyElectricityConsumption;
    }

    public void setMonthlyElectricityConsumption(String monthlyElectricityConsumption) {
        this.monthlyElectricityConsumption = monthlyElectricityConsumption;
    }

    public String getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(String dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public String getMonthlyCharge() {
        return monthlyCharge;
    }

    public void setMonthlyCharge(String monthlyCharge) {
        this.monthlyCharge = monthlyCharge;
    }

    public String getBatteryCurrent() {
        return batteryCurrent;
    }

    public void setBatteryCurrent(String batteryCurrent) {
        this.batteryCurrent = batteryCurrent;
    }

    public String getSolarPanelVoltage() {
        return solarPanelVoltage;
    }

    public void setSolarPanelVoltage(String solarPanelVoltage) {
        this.solarPanelVoltage = solarPanelVoltage;
    }

    public String getSolarPannelCurrent() {
        return solarPannelCurrent;
    }

    public void setSolarPannelCurrent(String solarPannelCurrent) {
        this.solarPannelCurrent = solarPannelCurrent;
    }

    public String getPowerGeneration() {
        return powerGeneration;
    }

    public void setPowerGeneration(String powerGeneration) {
        this.powerGeneration = powerGeneration;
    }

    public String getLoadVoltage() {
        return loadVoltage;
    }

    public void setLoadVoltage(String loadVoltage) {
        this.loadVoltage = loadVoltage;
    }

    public String getLoadCurrent() {
        return loadCurrent;
    }

    public void setLoadCurrent(String loadCurrent) {
        this.loadCurrent = loadCurrent;
    }

    public String getBatteryPercent() {
        return batteryPercent;
    }

    public void setBatteryPercent(String batteryPercent) {
        this.batteryPercent = batteryPercent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getLedmsg() {
        return ledmsg;
    }

    public void setLedmsg(String ledmsg) {
        this.ledmsg = ledmsg;
    }

    public Integer getCardinfo() {
        return cardinfo;
    }

    public void setCardinfo(Integer cardinfo) {
        this.cardinfo = cardinfo;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getMinVoltage() {
        return minVoltage;
    }

    public void setMinVoltage(Double minVoltage) {
        this.minVoltage = minVoltage;
    }

    public Double getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(Double maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public String getCsq() {
        return csq;
    }

    public void setCsq(String csq) {
        this.csq = csq;
    }

    public Integer getTipcount() {
        return tipcount;
    }

    public void setTipcount(Integer tipcount) {
        this.tipcount = tipcount;
    }

    public String getBatterycorrect() {
        return batterycorrect;
    }

    public void setBatterycorrect(String batterycorrect) {
        this.batterycorrect = batterycorrect;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", createTime=").append(createTime);
            sb.append(", del=").append(del);
            sb.append(", deviceId=").append(deviceId);
            sb.append(", channelId=").append(channelId);
            sb.append(", deviceNumber=").append(deviceNumber);
            sb.append(", deviceName=").append(deviceName);
            sb.append(", password=").append(password);
            sb.append(", groupId=").append(groupId);
            sb.append(", userName=").append(userName);
            sb.append(", updateTime=").append(updateTime);
            sb.append(", heartbeatTime=").append(heartbeatTime);
            sb.append(", online=").append(online);
            sb.append(", handSwitch=").append(handSwitch);
            sb.append(", autoMode=").append(autoMode);
            sb.append(", longitude=").append(longitude);
            sb.append(", latitude=").append(latitude);
            sb.append(", temperature=").append(temperature);
            sb.append(", batteryVoltage=").append(batteryVoltage);
            sb.append(", dailyElectricityConsumption=").append(dailyElectricityConsumption);
            sb.append(", monthlyElectricityConsumption=").append(monthlyElectricityConsumption);
            sb.append(", dailyCharge=").append(dailyCharge);
            sb.append(", monthlyCharge=").append(monthlyCharge);
            sb.append(", batteryCurrent=").append(batteryCurrent);
            sb.append(", solarPanelVoltage=").append(solarPanelVoltage);
            sb.append(", solarPannelCurrent=").append(solarPannelCurrent);
            sb.append(", powerGeneration=").append(powerGeneration);
            sb.append(", loadVoltage=").append(loadVoltage);
            sb.append(", loadCurrent=").append(loadCurrent);
            sb.append(", batteryPercent=").append(batteryPercent);
            sb.append(", type=").append(type);
            sb.append(", errmsg=").append(errmsg);
            sb.append(", ledmsg=").append(ledmsg);
            sb.append(", cardinfo=").append(cardinfo);
            sb.append(", cardNumber=").append(cardNumber);
            sb.append(", minVoltage=").append(minVoltage);
            sb.append(", maxVoltage=").append(maxVoltage);
            sb.append(", csq=").append(csq);
            sb.append(", tipcount=").append(tipcount);
            sb.append(", batterycorrect=").append(batterycorrect);
        sb.append("]");
        return sb.toString();
    }

}