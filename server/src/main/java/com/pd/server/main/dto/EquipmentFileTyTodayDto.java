package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class EquipmentFileTyTodayDto extends PageDto{

    /**
    * 
    */
    private String id;

    /**
    * 设备唯一标识
    */
    private String sbbh;

    /**
    * 图片路径
    */
    private String tplj;

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
    * 采集年份
    */
    private String nf;

    /**
    * 采集月份
    */
    private String yf;

    /**
    * 采集日期
    */
    private String rq;

    /**
    * 采集小时
    */
    private String xs;

    /**
    * 采集分钟
    */
    private String fz;

    /**
    * 部门
    */
    private String deptcode;

    /**
    * 项目编号
    */
    private String xmbh;

    /**
    * 报警文件类型1图片2音频3文件
    */
    private String wjlx;

    /**
    * 监测种类1江豚2白海豚
    */
    private String jczl;

    /**
    * 来源1实时数据采集2非实时数据分析
    */
    private String ly;

    /**
    * 坐标原数据
    */
    private String sm1;

    /**
    * 坐标信息
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

    /**
    * 
    */
    private String sm5;

    /**
    * 
    */
    private String sm6;

    /**
    * type，codeset表中的文件种类
    */
    private String type;

    /**
    * 头数
    */
    private String ts;

    /**
    * txt文件类型|1：必传文件，2：脉冲或者A4设备头数文件，3：A2设备头数文件
    */
    private String txtlx;

    /**
    * 文件名称截取，为了查询出对应的音频，文件，图片等数据
    */
    private String wjmc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getTplj() {
        return tplj;
    }

    public void setTplj(String tplj) {
        this.tplj = tplj;
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

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getXs() {
        return xs;
    }

    public void setXs(String xs) {
        this.xs = xs;
    }

    public String getFz() {
        return fz;
    }

    public void setFz(String fz) {
        this.fz = fz;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getWjlx() {
        return wjlx;
    }

    public void setWjlx(String wjlx) {
        this.wjlx = wjlx;
    }

    public String getJczl() {
        return jczl;
    }

    public void setJczl(String jczl) {
        this.jczl = jczl;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
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

    public String getSm5() {
        return sm5;
    }

    public void setSm5(String sm5) {
        this.sm5 = sm5;
    }

    public String getSm6() {
        return sm6;
    }

    public void setSm6(String sm6) {
        this.sm6 = sm6;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getTxtlx() {
        return txtlx;
    }

    public void setTxtlx(String txtlx) {
        this.txtlx = txtlx;
    }

    public String getWjmc() {
        return wjmc;
    }

    public void setWjmc(String wjmc) {
        this.wjmc = wjmc;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", sbbh=").append(sbbh);
            sb.append(", tplj=").append(tplj);
            sb.append(", cjsj=").append(cjsj);
            sb.append(", createTime=").append(createTime);
            sb.append(", nf=").append(nf);
            sb.append(", yf=").append(yf);
            sb.append(", rq=").append(rq);
            sb.append(", xs=").append(xs);
            sb.append(", fz=").append(fz);
            sb.append(", deptcode=").append(deptcode);
            sb.append(", xmbh=").append(xmbh);
            sb.append(", wjlx=").append(wjlx);
            sb.append(", jczl=").append(jczl);
            sb.append(", ly=").append(ly);
            sb.append(", sm1=").append(sm1);
            sb.append(", sm2=").append(sm2);
            sb.append(", sm3=").append(sm3);
            sb.append(", sm4=").append(sm4);
            sb.append(", sm5=").append(sm5);
            sb.append(", sm6=").append(sm6);
            sb.append(", type=").append(type);
            sb.append(", ts=").append(ts);
            sb.append(", txtlx=").append(txtlx);
            sb.append(", wjmc=").append(wjmc);
        sb.append("]");
        return sb.toString();
    }

}