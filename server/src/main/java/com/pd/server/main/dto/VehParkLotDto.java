package com.pd.server.main.dto;

        import com.fasterxml.jackson.annotation.JsonFormat;

        import java.util.Date;
        import java.util.List;

public class VehParkLotDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 录入人
    */
    private String createBy;

    /**
    * 录入日期
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
    * 修改人
    */
    private String updateBy;

    /**
    * 修改时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
    * 停车场部门 编号
    */
    private String sysOrgCode;

    /**
    * 归类
    */
    private String gl;

    /**
    * 条形码流水号
    */
    private String lsh;

    /**
    * 号牌号码
    */
    private String hphm;

    /**
    * 车辆识别代号
    */
    private String clsbdh;

    /**
    * 发动机号
    */
    private String fdjh;

    /**
    * 车辆类别
    */
    private String cllb;

    /**
    * 车牌颜色
    */
    private String cpys;

    /**
    * 车身颜色
    */
    private String csys;

    /**
    * 车主姓名
    */
    private String syr;

    /**
    * 身份证明号码
    */
    private String sfzmhm;

    /**
    * 凭证编号
    */
    private String pzbh;

    /**
    * 机动车序号
    */
    private String xh;

    /**
    * 档案编号
    */
    private String dabh;

    /**
    * 详细地址
    */
    private String zsxxdz;

    /**
    * 违法时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date wfsj;

    /**
    * 违法地点
    */
    private String wfdd;

    /**
    * 违法地址
    */
    private String wfdz;

    /**
    * 执勤民警
    */
    private String zqmj;

    /**
    * 大队名称
    */
    private String fxjgmc;

    /**
    * 大队代码
    */
    private String fzjg;

    /**
    * 拯救司机姓名
    */
    private String sjxm;

    /**
    * 拯救车编号
    */
    private String zjcbh;

    /**
    * 违法代码
    */
    private String wfdm;

    /**
    * 入场日期
    */
        @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date rcrq;

    /**
    * 验车员
    */
    private String ycy;

    /**
    * 停放位置
    */
    private String tfwz;

    /**
    * 出场日期
    */
        @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date ccrq;

    /**
    * 备注
    */
    private String bz;

    /**
    * 出厂原因
    */
    private String ccyy;

    /**
    * 移交单位
    */
    private String yjdw;

    /**
    * 移交单位名称
    */
    private String yjdwmc;

    /**
    * 出场接收人
    */
    private String ccjsr;

    /**
    * 出场人联系电话
    */
    private String ccrlxdh;

    /**
    * 出场描述
    */
    private String ccms;

    /**
    * 状态1入场2出场
    */
    private String zt;

    /**
     * 入场日期开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTimeIn;

    /**
     * 入场日期结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTimeIn;

    /**
     * 出场日期开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTimeOut;

    /**
     * 出场日期结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTimeOut;

    /**
     * 批量出场的车辆id
     */
    private List<String> idList;

    private Integer sumddchp;

    private Integer sumddcnp;

    private Integer sumddcwp;

    private Integer summtcyp;

    private Integer summtcwp;

    private Integer sumslc;

    private Integer sumqc;

    private Integer sumnyc;

    private Integer sumqt;

    private List<String> sysOrgCodeListStr;

    private   String chooseType;//统计类型

    private   String  checkdate;//统计时间


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

    public String getGl() {
    return gl;
    }

    public void setGl(String gl) {
    this.gl = gl;
    }

    public String getLsh() {
    return lsh;
    }

    public void setLsh(String lsh) {
    this.lsh = lsh;
    }

    public String getHphm() {
    return hphm;
    }

    public void setHphm(String hphm) {
    this.hphm = hphm;
    }

    public String getClsbdh() {
    return clsbdh;
    }

    public void setClsbdh(String clsbdh) {
    this.clsbdh = clsbdh;
    }

    public String getFdjh() {
    return fdjh;
    }

    public void setFdjh(String fdjh) {
    this.fdjh = fdjh;
    }

    public String getCllb() {
    return cllb;
    }

    public void setCllb(String cllb) {
    this.cllb = cllb;
    }

    public String getCpys() {
    return cpys;
    }

    public void setCpys(String cpys) {
    this.cpys = cpys;
    }

    public String getCsys() {
    return csys;
    }

    public void setCsys(String csys) {
    this.csys = csys;
    }

    public String getSyr() {
    return syr;
    }

    public void setSyr(String syr) {
    this.syr = syr;
    }

    public String getSfzmhm() {
    return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
    this.sfzmhm = sfzmhm;
    }

    public String getPzbh() {
    return pzbh;
    }

    public void setPzbh(String pzbh) {
    this.pzbh = pzbh;
    }

    public String getXh() {
    return xh;
    }

    public void setXh(String xh) {
    this.xh = xh;
    }

    public String getDabh() {
    return dabh;
    }

    public void setDabh(String dabh) {
    this.dabh = dabh;
    }

    public String getZsxxdz() {
    return zsxxdz;
    }

    public void setZsxxdz(String zsxxdz) {
    this.zsxxdz = zsxxdz;
    }

    public Date getWfsj() {
    return wfsj;
    }

    public void setWfsj(Date wfsj) {
    this.wfsj = wfsj;
    }

    public String getWfdd() {
    return wfdd;
    }

    public void setWfdd(String wfdd) {
    this.wfdd = wfdd;
    }

    public String getWfdz() {
    return wfdz;
    }

    public void setWfdz(String wfdz) {
    this.wfdz = wfdz;
    }

    public String getZqmj() {
    return zqmj;
    }

    public void setZqmj(String zqmj) {
    this.zqmj = zqmj;
    }

    public String getFxjgmc() {
    return fxjgmc;
    }

    public void setFxjgmc(String fxjgmc) {
    this.fxjgmc = fxjgmc;
    }

    public String getFzjg() {
    return fzjg;
    }

    public void setFzjg(String fzjg) {
    this.fzjg = fzjg;
    }

    public String getSjxm() {
    return sjxm;
    }

    public void setSjxm(String sjxm) {
    this.sjxm = sjxm;
    }

    public String getZjcbh() {
    return zjcbh;
    }

    public void setZjcbh(String zjcbh) {
    this.zjcbh = zjcbh;
    }

    public String getWfdm() {
    return wfdm;
    }

    public void setWfdm(String wfdm) {
    this.wfdm = wfdm;
    }

    public Date getRcrq() {
    return rcrq;
    }

    public void setRcrq(Date rcrq) {
    this.rcrq = rcrq;
    }

    public String getYcy() {
    return ycy;
    }

    public void setYcy(String ycy) {
    this.ycy = ycy;
    }

    public String getTfwz() {
    return tfwz;
    }

    public void setTfwz(String tfwz) {
    this.tfwz = tfwz;
    }

    public Date getCcrq() {
    return ccrq;
    }

    public void setCcrq(Date ccrq) {
    this.ccrq = ccrq;
    }

    public String getBz() {
    return bz;
    }

    public void setBz(String bz) {
    this.bz = bz;
    }

    public String getCcyy() {
    return ccyy;
    }

    public void setCcyy(String ccyy) {
    this.ccyy = ccyy;
    }

    public String getYjdw() {
    return yjdw;
    }

    public void setYjdw(String yjdw) {
    this.yjdw = yjdw;
    }

    public String getYjdwmc() {
    return yjdwmc;
    }

    public void setYjdwmc(String yjdwmc) {
    this.yjdwmc = yjdwmc;
    }

    public String getCcjsr() {
    return ccjsr;
    }

    public void setCcjsr(String ccjsr) {
    this.ccjsr = ccjsr;
    }

    public String getCcrlxdh() {
    return ccrlxdh;
    }

    public void setCcrlxdh(String ccrlxdh) {
    this.ccrlxdh = ccrlxdh;
    }

    public String getCcms() {
    return ccms;
    }

    public void setCcms(String ccms) {
    this.ccms = ccms;
    }

    public String getZt() {
    return zt;
    }

    public void setZt(String zt) {
    this.zt = zt;
    }

    public Date getStartTimeIn() {
        return startTimeIn;
    }

    public void setStartTimeIn(Date startTimeIn) {
        this.startTimeIn = startTimeIn;
    }

    public Date getStartTimeOut() {
        return startTimeOut;
    }

    public void setStartTimeOut(Date startTimeOut) {
        this.startTimeOut = startTimeOut;
    }

    public Date getEndTimeIn() {
        return endTimeIn;
    }

    public void setEndTimeIn(Date endTimeIn) {
        this.endTimeIn = endTimeIn;
    }

    public Date getEndTimeOut() {
        return endTimeOut;
    }

    public void setEndTimeOut(Date endTimeOut) {
        this.endTimeOut = endTimeOut;
    }

    public List<String> getIdList() { return idList; }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public Integer getSumddchp() {
        return sumddchp;
    }

    public void setSumddchp(Integer sumddchp) {
        this.sumddchp = sumddchp;
    }

    public Integer getSumddcnp() {
        return sumddcnp;
    }

    public void setSumddcnp(Integer sumddcnp) {
        this.sumddcnp = sumddcnp;
    }

    public Integer getSumddcwp() {
        return sumddcwp;
    }

    public void setSumddcwp(Integer sumddcwp) {
        this.sumddcwp = sumddcwp;
    }

    public Integer getSummtcyp() {
        return summtcyp;
    }

    public void setSummtcyp(Integer summtcyp) {
        this.summtcyp = summtcyp;
    }

    public Integer getSummtcwp() {
        return summtcwp;
    }

    public void setSummtcwp(Integer summtcwp) {
        this.summtcwp = summtcwp;
    }

    public Integer getSumslc() {
        return sumslc;
    }

    public void setSumslc(Integer sumslc) {
        this.sumslc = sumslc;
    }

    public Integer getSumqc() {
        return sumqc;
    }

    public void setSumqc(Integer sumqc) {
        this.sumqc = sumqc;
    }

    public Integer getSumnyc() {
        return sumnyc;
    }

    public void setSumnyc(Integer sumnyc) {
        this.sumnyc = sumnyc;
    }

    public Integer getSumqt() {
        return sumqt;
    }

    public void setSumqt(Integer sumqt) {
        this.sumqt = sumqt;
    }

    public List<String> getSysOrgCodeListStr() {
        return sysOrgCodeListStr;
    }

    public void setSysOrgCodeListStr(List<String> sysOrgCodeListStr) {
        this.sysOrgCodeListStr = sysOrgCodeListStr;
    }

    public String getChooseType() {
        return chooseType;
    }

    public void setChooseType(String chooseType) {
        this.chooseType = chooseType;
    }

    public String getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(String checkdate) {
        this.checkdate = checkdate;
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
    sb.append(", gl=").append(gl);
    sb.append(", lsh=").append(lsh);
    sb.append(", hphm=").append(hphm);
    sb.append(", clsbdh=").append(clsbdh);
    sb.append(", fdjh=").append(fdjh);
    sb.append(", cllb=").append(cllb);
    sb.append(", cpys=").append(cpys);
    sb.append(", csys=").append(csys);
    sb.append(", syr=").append(syr);
    sb.append(", sfzmhm=").append(sfzmhm);
    sb.append(", pzbh=").append(pzbh);
    sb.append(", xh=").append(xh);
    sb.append(", dabh=").append(dabh);
    sb.append(", zsxxdz=").append(zsxxdz);
    sb.append(", wfsj=").append(wfsj);
    sb.append(", wfdd=").append(wfdd);
    sb.append(", wfdz=").append(wfdz);
    sb.append(", zqmj=").append(zqmj);
    sb.append(", fxjgmc=").append(fxjgmc);
    sb.append(", fzjg=").append(fzjg);
    sb.append(", sjxm=").append(sjxm);
    sb.append(", zjcbh=").append(zjcbh);
    sb.append(", wfdm=").append(wfdm);
    sb.append(", rcrq=").append(rcrq);
    sb.append(", ycy=").append(ycy);
    sb.append(", tfwz=").append(tfwz);
    sb.append(", ccrq=").append(ccrq);
    sb.append(", bz=").append(bz);
    sb.append(", ccyy=").append(ccyy);
    sb.append(", yjdw=").append(yjdw);
    sb.append(", yjdwmc=").append(yjdwmc);
    sb.append(", ccjsr=").append(ccjsr);
    sb.append(", ccrlxdh=").append(ccrlxdh);
    sb.append(", ccms=").append(ccms);
    sb.append(", zt=").append(zt);
sb.append("]");
return sb.toString();
}

}