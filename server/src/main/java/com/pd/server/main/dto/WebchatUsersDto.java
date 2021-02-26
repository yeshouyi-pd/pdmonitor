package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class WebchatUsersDto extends PageDto{

    /**
    * ID
    */
    private String id;

    /**
    * openid
    */
    private String openid;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 微信头像
    */
    private String headimgurl;

    /**
    * 备注名
    */
    private String remark;

    /**
    * 性别
    */
    private String sex;

    /**
    * 市
    */
    private String city;

    /**
    * 省
    */
    private String province;

    /**
    * 国家
    */
    private String country;

    /**
    * 证件名称
    */
    private String zjmc;

    /**
    * 证件号
    */
    private String zjhm;

    /**
    * 证件类型
    */
    private String zjlx;

    /**
    * 证件图片
    */
    private String zjtp;

    /**
    * 证件有效期始
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date yxqs;

    /**
    * 证件有效期止
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date yxqz;

    /**
    * 证件地址
    */
    private String zjdz;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cjsj;

    /**
    * 是否认证
    */
    private String sfrz;

    /**
    * 准驾车型
    */
    private String zjcx;

    /**
    * 系统编号
    */
    private String xtbh;

    /**
    * 备注
    */
    private String bz;

    /**
    * 正脸照片
    */
    private String tp1;

    /**
    * 身份证正面
    */
    private String tp2;

    /**
    * 身份证反面
    */
    private String tp3;

    /**
    * 两个人脸的相似度
    */
    private String similarity;

    /**
    * 标志失败图片，0-没有失败，1-第一张，2-第二张
    */
    private String failflag;

    /**
    * 驾驶证号
    */
    private String jszh;

    /**
    * 初次领证日期
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cclzrq;

    /**
    * 有效期止
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date jszyxqz;

    /**
    * 联系电话
    */
    private String lxdh;

    /**
    * 用户等级
    */
    private String yhdj;

    /**
    * 关注车辆
    */
    private String gzcl;

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getOpenid() {
    return openid;
    }

    public void setOpenid(String openid) {
    this.openid = openid;
    }

    public String getNickname() {
    return nickname;
    }

    public void setNickname(String nickname) {
    this.nickname = nickname;
    }

    public String getHeadimgurl() {
    return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
    this.headimgurl = headimgurl;
    }

    public String getRemark() {
    return remark;
    }

    public void setRemark(String remark) {
    this.remark = remark;
    }

    public String getSex() {
    return sex;
    }

    public void setSex(String sex) {
    this.sex = sex;
    }

    public String getCity() {
    return city;
    }

    public void setCity(String city) {
    this.city = city;
    }

    public String getProvince() {
    return province;
    }

    public void setProvince(String province) {
    this.province = province;
    }

    public String getCountry() {
    return country;
    }

    public void setCountry(String country) {
    this.country = country;
    }

    public String getZjmc() {
    return zjmc;
    }

    public void setZjmc(String zjmc) {
    this.zjmc = zjmc;
    }

    public String getZjhm() {
    return zjhm;
    }

    public void setZjhm(String zjhm) {
    this.zjhm = zjhm;
    }

    public String getZjlx() {
    return zjlx;
    }

    public void setZjlx(String zjlx) {
    this.zjlx = zjlx;
    }

    public String getZjtp() {
    return zjtp;
    }

    public void setZjtp(String zjtp) {
    this.zjtp = zjtp;
    }

    public Date getYxqs() {
    return yxqs;
    }

    public void setYxqs(Date yxqs) {
    this.yxqs = yxqs;
    }

    public Date getYxqz() {
    return yxqz;
    }

    public void setYxqz(Date yxqz) {
    this.yxqz = yxqz;
    }

    public String getZjdz() {
    return zjdz;
    }

    public void setZjdz(String zjdz) {
    this.zjdz = zjdz;
    }

    public Date getCjsj() {
    return cjsj;
    }

    public void setCjsj(Date cjsj) {
    this.cjsj = cjsj;
    }

    public String getSfrz() {
    return sfrz;
    }

    public void setSfrz(String sfrz) {
    this.sfrz = sfrz;
    }

    public String getZjcx() {
    return zjcx;
    }

    public void setZjcx(String zjcx) {
    this.zjcx = zjcx;
    }

    public String getXtbh() {
    return xtbh;
    }

    public void setXtbh(String xtbh) {
    this.xtbh = xtbh;
    }

    public String getBz() {
    return bz;
    }

    public void setBz(String bz) {
    this.bz = bz;
    }

    public String getTp1() {
    return tp1;
    }

    public void setTp1(String tp1) {
    this.tp1 = tp1;
    }

    public String getTp2() {
    return tp2;
    }

    public void setTp2(String tp2) {
    this.tp2 = tp2;
    }

    public String getTp3() {
    return tp3;
    }

    public void setTp3(String tp3) {
    this.tp3 = tp3;
    }

    public String getSimilarity() {
    return similarity;
    }

    public void setSimilarity(String similarity) {
    this.similarity = similarity;
    }

    public String getFailflag() {
    return failflag;
    }

    public void setFailflag(String failflag) {
    this.failflag = failflag;
    }

    public String getJszh() {
    return jszh;
    }

    public void setJszh(String jszh) {
    this.jszh = jszh;
    }

    public Date getCclzrq() {
    return cclzrq;
    }

    public void setCclzrq(Date cclzrq) {
    this.cclzrq = cclzrq;
    }

    public Date getJszyxqz() {
    return jszyxqz;
    }

    public void setJszyxqz(Date jszyxqz) {
    this.jszyxqz = jszyxqz;
    }

    public String getLxdh() {
    return lxdh;
    }

    public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
    }

    public String getYhdj() {
    return yhdj;
    }

    public void setYhdj(String yhdj) {
    this.yhdj = yhdj;
    }

    public String getGzcl() {
    return gzcl;
    }

    public void setGzcl(String gzcl) {
    this.gzcl = gzcl;
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", openid=").append(openid);
    sb.append(", nickname=").append(nickname);
    sb.append(", headimgurl=").append(headimgurl);
    sb.append(", remark=").append(remark);
    sb.append(", sex=").append(sex);
    sb.append(", city=").append(city);
    sb.append(", province=").append(province);
    sb.append(", country=").append(country);
    sb.append(", zjmc=").append(zjmc);
    sb.append(", zjhm=").append(zjhm);
    sb.append(", zjlx=").append(zjlx);
    sb.append(", zjtp=").append(zjtp);
    sb.append(", yxqs=").append(yxqs);
    sb.append(", yxqz=").append(yxqz);
    sb.append(", zjdz=").append(zjdz);
    sb.append(", cjsj=").append(cjsj);
    sb.append(", sfrz=").append(sfrz);
    sb.append(", zjcx=").append(zjcx);
    sb.append(", xtbh=").append(xtbh);
    sb.append(", bz=").append(bz);
    sb.append(", tp1=").append(tp1);
    sb.append(", tp2=").append(tp2);
    sb.append(", tp3=").append(tp3);
    sb.append(", similarity=").append(similarity);
    sb.append(", failflag=").append(failflag);
    sb.append(", jszh=").append(jszh);
    sb.append(", cclzrq=").append(cclzrq);
    sb.append(", jszyxqz=").append(jszyxqz);
    sb.append(", lxdh=").append(lxdh);
    sb.append(", yhdj=").append(yhdj);
    sb.append(", gzcl=").append(gzcl);
sb.append("]");
return sb.toString();
}

}