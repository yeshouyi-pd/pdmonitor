package com.pd.server.main.domain;

import java.util.Date;

public class WebchatPostinfo {
    private String id;

    private String loginName;

    private String loginPwd;

    private String secretKey;

    private String distributorOrderid;

    private String senderProvinceCode;

    private String senderCityCode;

    private String senderCountyCode;

    private String senderCompany;

    private String senderName;

    private String senderPhone;

    private String senderAddress;

    private Integer protectPrice;

    private Integer insuranceAmount;

    private Integer deliverMoney;

    private String goods;

    private Integer goodsQuantity;

    private Integer goodsWeight;

    private String demo;

    private Integer emsType;

    private String mailNo;

    private String other;

    private Integer whoPay;

    private Integer senderAddressType;

    private Integer receiverAddressType;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String sysOrgCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getDistributorOrderid() {
        return distributorOrderid;
    }

    public void setDistributorOrderid(String distributorOrderid) {
        this.distributorOrderid = distributorOrderid;
    }

    public String getSenderProvinceCode() {
        return senderProvinceCode;
    }

    public void setSenderProvinceCode(String senderProvinceCode) {
        this.senderProvinceCode = senderProvinceCode;
    }

    public String getSenderCityCode() {
        return senderCityCode;
    }

    public void setSenderCityCode(String senderCityCode) {
        this.senderCityCode = senderCityCode;
    }

    public String getSenderCountyCode() {
        return senderCountyCode;
    }

    public void setSenderCountyCode(String senderCountyCode) {
        this.senderCountyCode = senderCountyCode;
    }

    public String getSenderCompany() {
        return senderCompany;
    }

    public void setSenderCompany(String senderCompany) {
        this.senderCompany = senderCompany;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public Integer getProtectPrice() {
        return protectPrice;
    }

    public void setProtectPrice(Integer protectPrice) {
        this.protectPrice = protectPrice;
    }

    public Integer getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(Integer insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public Integer getDeliverMoney() {
        return deliverMoney;
    }

    public void setDeliverMoney(Integer deliverMoney) {
        this.deliverMoney = deliverMoney;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public Integer getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(Integer goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public Integer getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Integer goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public Integer getEmsType() {
        return emsType;
    }

    public void setEmsType(Integer emsType) {
        this.emsType = emsType;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Integer getWhoPay() {
        return whoPay;
    }

    public void setWhoPay(Integer whoPay) {
        this.whoPay = whoPay;
    }

    public Integer getSenderAddressType() {
        return senderAddressType;
    }

    public void setSenderAddressType(Integer senderAddressType) {
        this.senderAddressType = senderAddressType;
    }

    public Integer getReceiverAddressType() {
        return receiverAddressType;
    }

    public void setReceiverAddressType(Integer receiverAddressType) {
        this.receiverAddressType = receiverAddressType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginName=").append(loginName);
        sb.append(", loginPwd=").append(loginPwd);
        sb.append(", secretKey=").append(secretKey);
        sb.append(", distributorOrderid=").append(distributorOrderid);
        sb.append(", senderProvinceCode=").append(senderProvinceCode);
        sb.append(", senderCityCode=").append(senderCityCode);
        sb.append(", senderCountyCode=").append(senderCountyCode);
        sb.append(", senderCompany=").append(senderCompany);
        sb.append(", senderName=").append(senderName);
        sb.append(", senderPhone=").append(senderPhone);
        sb.append(", senderAddress=").append(senderAddress);
        sb.append(", protectPrice=").append(protectPrice);
        sb.append(", insuranceAmount=").append(insuranceAmount);
        sb.append(", deliverMoney=").append(deliverMoney);
        sb.append(", goods=").append(goods);
        sb.append(", goodsQuantity=").append(goodsQuantity);
        sb.append(", goodsWeight=").append(goodsWeight);
        sb.append(", demo=").append(demo);
        sb.append(", emsType=").append(emsType);
        sb.append(", mailNo=").append(mailNo);
        sb.append(", other=").append(other);
        sb.append(", whoPay=").append(whoPay);
        sb.append(", senderAddressType=").append(senderAddressType);
        sb.append(", receiverAddressType=").append(receiverAddressType);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", sysOrgCode=").append(sysOrgCode);
        sb.append("]");
        return sb.toString();
    }
}