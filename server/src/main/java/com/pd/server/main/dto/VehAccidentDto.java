package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class VehAccidentDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 创建人
    */
    private String createBy;

    /**
    * 创建时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
    * 更新人
    */
    private String updateBy;

    /**
    * 更新时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
    * 机构代码
    */
    private String sysOrgCode;

    /**
    * 事故编号
    */
    private String sgbh;

    /**
    * 流水号
    */
    private String lsh;

    /**
    * 事故发生时间
    */
    private String sgfssj;

    /**
    * 事故地点
    */
    private String sgdd;

    /**
    * 事故情形
    */
    private String sgqx;

    /**
    * 行政区划
    */
    private String xzqh;

    /**
    * 管理部门
    */
    private String glbm;

    /**
    * 地址坐标
    */
    private String dzzb;

    /**
    * 是否高架/高速（1是2否）
    */
    private String sfgj;

    /**
    * 天气
    */
    private String tq;

    /**
    * 用户代号
    */
    private String yhdh;

    /**
    * 处理方式1自行商定2远程定责
    */
    private String clfs;

    /**
    * 当事人数目
    */
    private String dsrsm;

    /**
    * 当事人1所属(1甲方2乙方)
    */
    private String dsrss1;

    /**
    * 当事人1姓名
    */
    private String dsrxm1;

    /**
    * 当事人1身份证明号码
    */
    private String dsrsfzmhm1;

    /**
    * 当事人1手机号
    */
    private String dsrsjhm1;

    /**
    * 当事人1的号牌种类
    */
    private String hpzl1;

    /**
    * 当事人1的号牌号码
    */
    private String hphm1;

    /**
    * 当事人1交通方式
    */
    private String dsrjtfs1;

    /**
    * 当事人1车损部位
    */
    private String dsrcsbw1;

    /**
    * 当事人1事故责任
    */
    private String dsrbsgzr1;

    /**
    * 当事人1违法行为
    */
    private String dsrwfxw1;

    /**
    * 当事人1违法条款
    */
    private String dsrwftk1;

    /**
    * 当事人1保险公司
    */
    private String dsrbxgs1;

    /**
    * 当事人1保险期止
    */
    private String dsrbxqz1;

    /**
    * 当事人1保险凭证号
    */
    private String dsrbxpzh1;

    /**
    * 当事人2所属(1甲方2乙方)
    */
    private String dsrss2;

    /**
    * 当事人2姓名
    */
    private String dsrxm2;

    /**
    * 当事人2身份证明号码
    */
    private String dsrsfzmhm2;

    /**
    * 当事人2手机号
    */
    private String dsrsjhm2;

    /**
    * 当事人2的号牌种类
    */
    private String hpzl2;

    /**
    * 当事人2的号牌号码
    */
    private String hphm2;

    /**
    * 当事人2交通方式
    */
    private String dsrjtfs2;

    /**
    * 当事人2车损部位
    */
    private String dsrcsbw2;

    /**
    * 当事人2事故责任
    */
    private String dsrbsgzr2;

    /**
    * 当事人1违法行为
    */
    private String dsrwfxw2;

    /**
    * 当事人2违法条款
    */
    private String dsrwftk2;

    /**
    * 当事人2保险公司
    */
    private String dsrbxgs2;

    /**
    * 当事人2保险期止
    */
    private String dsrbxqz2;

    /**
    * 当事人2保险凭证号
    */
    private String dsrbxpzh2;

    /**
    * 微信用户openid
    */
    private String openid;

    /**
    * 备注
    */
    private String bz;

    /**
    * 审核人
    */
    private String shrxm;

    /**
    * 审核原因
    */
    private String shyy;

    /**
    * 审核结果
    */
    private String shsm;

    /**
    * 定责民警
    */
    private String dzmj;

    /**
    * 定责时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dzsj;

    /**
    * 状态 0未处理 1已审核 2已定则
    */
    private String zt;

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
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

    public String getSgbh() {
    return sgbh;
    }

    public void setSgbh(String sgbh) {
    this.sgbh = sgbh;
    }

    public String getLsh() {
    return lsh;
    }

    public void setLsh(String lsh) {
    this.lsh = lsh;
    }

    public String getSgfssj() {
    return sgfssj;
    }

    public void setSgfssj(String sgfssj) {
    this.sgfssj = sgfssj;
    }

    public String getSgdd() {
    return sgdd;
    }

    public void setSgdd(String sgdd) {
    this.sgdd = sgdd;
    }

    public String getSgqx() {
    return sgqx;
    }

    public void setSgqx(String sgqx) {
    this.sgqx = sgqx;
    }

    public String getXzqh() {
    return xzqh;
    }

    public void setXzqh(String xzqh) {
    this.xzqh = xzqh;
    }

    public String getGlbm() {
    return glbm;
    }

    public void setGlbm(String glbm) {
    this.glbm = glbm;
    }

    public String getDzzb() {
    return dzzb;
    }

    public void setDzzb(String dzzb) {
    this.dzzb = dzzb;
    }

    public String getSfgj() {
    return sfgj;
    }

    public void setSfgj(String sfgj) {
    this.sfgj = sfgj;
    }

    public String getTq() {
    return tq;
    }

    public void setTq(String tq) {
    this.tq = tq;
    }

    public String getYhdh() {
    return yhdh;
    }

    public void setYhdh(String yhdh) {
    this.yhdh = yhdh;
    }

    public String getClfs() {
    return clfs;
    }

    public void setClfs(String clfs) {
    this.clfs = clfs;
    }

    public String getDsrsm() {
    return dsrsm;
    }

    public void setDsrsm(String dsrsm) {
    this.dsrsm = dsrsm;
    }

    public String getDsrss1() {
    return dsrss1;
    }

    public void setDsrss1(String dsrss1) {
    this.dsrss1 = dsrss1;
    }

    public String getDsrxm1() {
    return dsrxm1;
    }

    public void setDsrxm1(String dsrxm1) {
    this.dsrxm1 = dsrxm1;
    }

    public String getDsrsfzmhm1() {
    return dsrsfzmhm1;
    }

    public void setDsrsfzmhm1(String dsrsfzmhm1) {
    this.dsrsfzmhm1 = dsrsfzmhm1;
    }

    public String getDsrsjhm1() {
    return dsrsjhm1;
    }

    public void setDsrsjhm1(String dsrsjhm1) {
    this.dsrsjhm1 = dsrsjhm1;
    }

    public String getHpzl1() {
    return hpzl1;
    }

    public void setHpzl1(String hpzl1) {
    this.hpzl1 = hpzl1;
    }

    public String getHphm1() {
    return hphm1;
    }

    public void setHphm1(String hphm1) {
    this.hphm1 = hphm1;
    }

    public String getDsrjtfs1() {
    return dsrjtfs1;
    }

    public void setDsrjtfs1(String dsrjtfs1) {
    this.dsrjtfs1 = dsrjtfs1;
    }

    public String getDsrcsbw1() {
    return dsrcsbw1;
    }

    public void setDsrcsbw1(String dsrcsbw1) {
    this.dsrcsbw1 = dsrcsbw1;
    }

    public String getDsrbsgzr1() {
    return dsrbsgzr1;
    }

    public void setDsrbsgzr1(String dsrbsgzr1) {
    this.dsrbsgzr1 = dsrbsgzr1;
    }

    public String getDsrwfxw1() {
    return dsrwfxw1;
    }

    public void setDsrwfxw1(String dsrwfxw1) {
    this.dsrwfxw1 = dsrwfxw1;
    }

    public String getDsrwftk1() {
    return dsrwftk1;
    }

    public void setDsrwftk1(String dsrwftk1) {
    this.dsrwftk1 = dsrwftk1;
    }

    public String getDsrbxgs1() {
    return dsrbxgs1;
    }

    public void setDsrbxgs1(String dsrbxgs1) {
    this.dsrbxgs1 = dsrbxgs1;
    }

    public String getDsrbxqz1() {
    return dsrbxqz1;
    }

    public void setDsrbxqz1(String dsrbxqz1) {
    this.dsrbxqz1 = dsrbxqz1;
    }

    public String getDsrbxpzh1() {
    return dsrbxpzh1;
    }

    public void setDsrbxpzh1(String dsrbxpzh1) {
    this.dsrbxpzh1 = dsrbxpzh1;
    }

    public String getDsrss2() {
    return dsrss2;
    }

    public void setDsrss2(String dsrss2) {
    this.dsrss2 = dsrss2;
    }

    public String getDsrxm2() {
    return dsrxm2;
    }

    public void setDsrxm2(String dsrxm2) {
    this.dsrxm2 = dsrxm2;
    }

    public String getDsrsfzmhm2() {
    return dsrsfzmhm2;
    }

    public void setDsrsfzmhm2(String dsrsfzmhm2) {
    this.dsrsfzmhm2 = dsrsfzmhm2;
    }

    public String getDsrsjhm2() {
    return dsrsjhm2;
    }

    public void setDsrsjhm2(String dsrsjhm2) {
    this.dsrsjhm2 = dsrsjhm2;
    }

    public String getHpzl2() {
    return hpzl2;
    }

    public void setHpzl2(String hpzl2) {
    this.hpzl2 = hpzl2;
    }

    public String getHphm2() {
    return hphm2;
    }

    public void setHphm2(String hphm2) {
    this.hphm2 = hphm2;
    }

    public String getDsrjtfs2() {
    return dsrjtfs2;
    }

    public void setDsrjtfs2(String dsrjtfs2) {
    this.dsrjtfs2 = dsrjtfs2;
    }

    public String getDsrcsbw2() {
    return dsrcsbw2;
    }

    public void setDsrcsbw2(String dsrcsbw2) {
    this.dsrcsbw2 = dsrcsbw2;
    }

    public String getDsrbsgzr2() {
    return dsrbsgzr2;
    }

    public void setDsrbsgzr2(String dsrbsgzr2) {
    this.dsrbsgzr2 = dsrbsgzr2;
    }

    public String getDsrwfxw2() {
    return dsrwfxw2;
    }

    public void setDsrwfxw2(String dsrwfxw2) {
    this.dsrwfxw2 = dsrwfxw2;
    }

    public String getDsrwftk2() {
    return dsrwftk2;
    }

    public void setDsrwftk2(String dsrwftk2) {
    this.dsrwftk2 = dsrwftk2;
    }

    public String getDsrbxgs2() {
    return dsrbxgs2;
    }

    public void setDsrbxgs2(String dsrbxgs2) {
    this.dsrbxgs2 = dsrbxgs2;
    }

    public String getDsrbxqz2() {
    return dsrbxqz2;
    }

    public void setDsrbxqz2(String dsrbxqz2) {
    this.dsrbxqz2 = dsrbxqz2;
    }

    public String getDsrbxpzh2() {
    return dsrbxpzh2;
    }

    public void setDsrbxpzh2(String dsrbxpzh2) {
    this.dsrbxpzh2 = dsrbxpzh2;
    }

    public String getOpenid() {
    return openid;
    }

    public void setOpenid(String openid) {
    this.openid = openid;
    }

    public String getBz() {
    return bz;
    }

    public void setBz(String bz) {
    this.bz = bz;
    }

    public String getShrxm() {
    return shrxm;
    }

    public void setShrxm(String shrxm) {
    this.shrxm = shrxm;
    }

    public String getShyy() {
    return shyy;
    }

    public void setShyy(String shyy) {
    this.shyy = shyy;
    }

    public String getShsm() {
    return shsm;
    }

    public void setShsm(String shsm) {
    this.shsm = shsm;
    }

    public String getDzmj() {
    return dzmj;
    }

    public void setDzmj(String dzmj) {
    this.dzmj = dzmj;
    }

    public Date getDzsj() {
    return dzsj;
    }

    public void setDzsj(Date dzsj) {
    this.dzsj = dzsj;
    }

    public String getZt() {
    return zt;
    }

    public void setZt(String zt) {
    this.zt = zt;
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", createBy=").append(createBy);
    sb.append(", createTime=").append(createTime);
    sb.append(", updateBy=").append(updateBy);
    sb.append(", updateTime=").append(updateTime);
    sb.append(", sysOrgCode=").append(sysOrgCode);
    sb.append(", sgbh=").append(sgbh);
    sb.append(", lsh=").append(lsh);
    sb.append(", sgfssj=").append(sgfssj);
    sb.append(", sgdd=").append(sgdd);
    sb.append(", sgqx=").append(sgqx);
    sb.append(", xzqh=").append(xzqh);
    sb.append(", glbm=").append(glbm);
    sb.append(", dzzb=").append(dzzb);
    sb.append(", sfgj=").append(sfgj);
    sb.append(", tq=").append(tq);
    sb.append(", yhdh=").append(yhdh);
    sb.append(", clfs=").append(clfs);
    sb.append(", dsrsm=").append(dsrsm);
    sb.append(", dsrss1=").append(dsrss1);
    sb.append(", dsrxm1=").append(dsrxm1);
    sb.append(", dsrsfzmhm1=").append(dsrsfzmhm1);
    sb.append(", dsrsjhm1=").append(dsrsjhm1);
    sb.append(", hpzl1=").append(hpzl1);
    sb.append(", hphm1=").append(hphm1);
    sb.append(", dsrjtfs1=").append(dsrjtfs1);
    sb.append(", dsrcsbw1=").append(dsrcsbw1);
    sb.append(", dsrbsgzr1=").append(dsrbsgzr1);
    sb.append(", dsrwfxw1=").append(dsrwfxw1);
    sb.append(", dsrwftk1=").append(dsrwftk1);
    sb.append(", dsrbxgs1=").append(dsrbxgs1);
    sb.append(", dsrbxqz1=").append(dsrbxqz1);
    sb.append(", dsrbxpzh1=").append(dsrbxpzh1);
    sb.append(", dsrss2=").append(dsrss2);
    sb.append(", dsrxm2=").append(dsrxm2);
    sb.append(", dsrsfzmhm2=").append(dsrsfzmhm2);
    sb.append(", dsrsjhm2=").append(dsrsjhm2);
    sb.append(", hpzl2=").append(hpzl2);
    sb.append(", hphm2=").append(hphm2);
    sb.append(", dsrjtfs2=").append(dsrjtfs2);
    sb.append(", dsrcsbw2=").append(dsrcsbw2);
    sb.append(", dsrbsgzr2=").append(dsrbsgzr2);
    sb.append(", dsrwfxw2=").append(dsrwfxw2);
    sb.append(", dsrwftk2=").append(dsrwftk2);
    sb.append(", dsrbxgs2=").append(dsrbxgs2);
    sb.append(", dsrbxqz2=").append(dsrbxqz2);
    sb.append(", dsrbxpzh2=").append(dsrbxpzh2);
    sb.append(", openid=").append(openid);
    sb.append(", bz=").append(bz);
    sb.append(", shrxm=").append(shrxm);
    sb.append(", shyy=").append(shyy);
    sb.append(", shsm=").append(shsm);
    sb.append(", dzmj=").append(dzmj);
    sb.append(", dzsj=").append(dzsj);
    sb.append(", zt=").append(zt);
sb.append("]");
return sb.toString();
}

}