package com.pd.server.main.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WebchatPostinfoDto {

    /**
     *
     */
    private String id;

    /**
     * 接口用户名
     */
    private String loginName;

    /**
     * 接口用户密码
     */
    private String loginPwd;

    /**
     * 密钥
     */
    private String secretKey;

    /**
     * 合作方唯一识别号
     */
    private String distributorOrderid;

    /**
     * 寄件人省份编号
     */
    private String senderProvinceCode;

    /**
     *
     */
    private String senderCityCode;

    /**
     * 寄件人区域编号
     */
    private String senderCountyCode;

    /**
     * 寄件公司名称
     */
    private String senderCompany;

    /**
     * 寄件人姓名
    */
    private String senderName;

    /**
     * 寄件人手机号码
    */
    private String senderPhone;

    /**
     * 寄件人地址
    */
    private String senderAddress;

    /**
     * 保价金额，单位是分，如未保价则填写0
    */
    private Integer protectPrice;

    /**
     * 保费，单位是分，如未保价则填写0
    */
    private Integer insuranceAmount;

    /**
     * 运费，单位是分
    */
    private Integer deliverMoney;

    /**
     * 物品名称
    */
    private String goods;

    /**
     * 物品数量
    */
    private Integer goodsQuantity;

    /**
     * 物品重量（单位   克），重量不确定的，就传0
    */
    private Integer goodsWeight;

    /**
     * 备注
    */
    private String demo;

    /**
     * 面单类型（0：快递包裹；1：标准快递）
    */
    private Integer emsType;

    /**
     * 面单号码
    */
    private String mailNo;

    /**
     * 传递Json格式并与邮政协商好的其他信息
    */
    private String other;

    /**
     * 如果是标准快递，该参数才有效。0：表示寄件人付款，1：表示收件人付款。默认为0
    */
    private Integer whoPay;

    /**
     * 固定填写0
    */
    private Integer senderAddressType;

    /**
     * 固定填写0
    */
    private Integer receiverAddressType;

    /**
     * 创建人
    */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人
    */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 操作部门
    */
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