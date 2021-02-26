package com.pd.server.main.dto;

        import java.util.Date;
        import java.util.List;

        import com.fasterxml.jackson.annotation.JsonFormat;

public class VehImpawnDto extends PageDto{

    /**
     * id
     */
    private String id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 所属部门
     */
    private String sysOrgCode;

    /**
     * 机动车序号
     */
    private String xh;

    /**
     * 车辆识别代号
     */
    private String clsbdh;

    /**
     * 主合同号
     */
    private String zhth;

    /**
     * 抵押合同号
     */
    private String dyhth;

    /**
     * 抵押金额
     */
    private Double dyje;

    /**
     * 抵押登记日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date dydjrq;

    /**
     * 机动车所有人
     */
    private String jbr;

    /**
     * 流水号
     */
    private String lsh;

    /**
     * 机动车所有人身份证明名称
     */
    private String sfzmmc;

    /**
     * 机动车所有人身份证明号码
     */
    private String sfzmhm;

    /**
     * 抵押权人行政区划
     */
    private String xzqh;

    /**
     * 详细地址
     */
    private String xxdz;

    /**
     * 邮政编码
     */
    private String yzbm;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 解除抵押日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date jcrq;

    /**
     * 管理部门
     */
    private String glbm;

    /**
     * 相关资料
     */
    private String xgzl;

    /**
     * 审核人
     */
    private String shr;

    /**
     * 审核时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date shsj;

    /**
     * 审核说明
     */
    private String shsm;

    /**
     * 审核原因
     */
    private String shyy;

    /**
     * 备注
     */
    private String bz;

    /**
     * 状态
     */
    private String zt;

    /**
     * 抵押权人代码
     */
    private String dyqrdm;

    /**
     * 抵押权人类型|1-商业银行2-汽车金融机构
     */
    private String dyqrlx;

    /**
     * 抵押权人身份证名称|B-组织机构代码证N-统一社会信用代码
     */
    private String dyqrsfzmmc;

    /**
     * 抵押权人身份证号码
     */
    private String dyqrsfzmhm;

    /**
     * 抵押权人手机号
     */
    private String sjhm;

    /**
     * 号牌种类
     */
    private String hpzl;

    /**
     * 号牌号码
     */
    private String hphm;

    /**
     * 抵押状态|0-已解除抵押，1-抵押中
     */
    private String dyzt;

    private List<String> tpListStr;

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

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getClsbdh() {
        return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }

    public String getZhth() {
        return zhth;
    }

    public void setZhth(String zhth) {
        this.zhth = zhth;
    }

    public String getDyhth() {
        return dyhth;
    }

    public void setDyhth(String dyhth) {
        this.dyhth = dyhth;
    }

    public Double getDyje() {
        return dyje;
    }

    public void setDyje(Double dyje) {
        this.dyje = dyje;
    }

    public Date getDydjrq() {
        return dydjrq;
    }

    public void setDydjrq(Date dydjrq) {
        this.dydjrq = dydjrq;
    }

    public String getJbr() {
        return jbr;
    }

    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getSfzmmc() {
        return sfzmmc;
    }

    public void setSfzmmc(String sfzmmc) {
        this.sfzmmc = sfzmmc;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getXxdz() {
        return xxdz;
    }

    public void setXxdz(String xxdz) {
        this.xxdz = xxdz;
    }

    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public Date getJcrq() {
        return jcrq;
    }

    public void setJcrq(Date jcrq) {
        this.jcrq = jcrq;
    }

    public String getGlbm() {
        return glbm;
    }

    public void setGlbm(String glbm) {
        this.glbm = glbm;
    }

    public String getXgzl() {
        return xgzl;
    }

    public void setXgzl(String xgzl) {
        this.xgzl = xgzl;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public Date getShsj() {
        return shsj;
    }

    public void setShsj(Date shsj) {
        this.shsj = shsj;
    }

    public String getShsm() {
        return shsm;
    }

    public void setShsm(String shsm) {
        this.shsm = shsm;
    }

    public String getShyy() {
        return shyy;
    }

    public void setShyy(String shyy) {
        this.shyy = shyy;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getDyqrdm() {
        return dyqrdm;
    }

    public void setDyqrdm(String dyqrdm) {
        this.dyqrdm = dyqrdm;
    }

    public String getDyqrlx() {
        return dyqrlx;
    }

    public void setDyqrlx(String dyqrlx) {
        this.dyqrlx = dyqrlx;
    }

    public String getDyqrsfzmmc() {
        return dyqrsfzmmc;
    }

    public void setDyqrsfzmmc(String dyqrsfzmmc) {
        this.dyqrsfzmmc = dyqrsfzmmc;
    }

    public String getDyqrsfzmhm() {
        return dyqrsfzmhm;
    }

    public void setDyqrsfzmhm(String dyqrsfzmhm) {
        this.dyqrsfzmhm = dyqrsfzmhm;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getDyzt() {
        return dyzt;
    }

    public void setDyzt(String dyzt) {
        this.dyzt = dyzt;
    }

    public List<String> getTpListStr() {
        return tpListStr;
    }

    public void setTpListStr(List<String> tpListStr) {
        this.tpListStr = tpListStr;
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
        sb.append(", xh=").append(xh);
        sb.append(", clsbdh=").append(clsbdh);
        sb.append(", zhth=").append(zhth);
        sb.append(", dyhth=").append(dyhth);
        sb.append(", dyje=").append(dyje);
        sb.append(", dydjrq=").append(dydjrq);
        sb.append(", jbr=").append(jbr);
        sb.append(", lsh=").append(lsh);
        sb.append(", sfzmmc=").append(sfzmmc);
        sb.append(", sfzmhm=").append(sfzmhm);
        sb.append(", xzqh=").append(xzqh);
        sb.append(", xxdz=").append(xxdz);
        sb.append(", yzbm=").append(yzbm);
        sb.append(", lxdh=").append(lxdh);
        sb.append(", jcrq=").append(jcrq);
        sb.append(", glbm=").append(glbm);
        sb.append(", xgzl=").append(xgzl);
        sb.append(", shr=").append(shr);
        sb.append(", shsj=").append(shsj);
        sb.append(", shsm=").append(shsm);
        sb.append(", shyy=").append(shyy);
        sb.append(", bz=").append(bz);
        sb.append(", zt=").append(zt);
        sb.append(", dyqrdm=").append(dyqrdm);
        sb.append(", dyqrlx=").append(dyqrlx);
        sb.append(", dyqrsfzmmc=").append(dyqrsfzmmc);
        sb.append(", dyqrsfzmhm=").append(dyqrsfzmhm);
        sb.append(", sjhm=").append(sjhm);
        sb.append(", hpzl=").append(hpzl);
        sb.append(", hphm=").append(hphm);
        sb.append(", dyzt=").append(dyzt);
        sb.append("]");
        return sb.toString();
    }

}