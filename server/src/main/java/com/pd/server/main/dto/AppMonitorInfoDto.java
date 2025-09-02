package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class AppMonitorInfoDto {

    /**
    * 
    */
    private String id;

    /**
    * 考察船类型
    */
    private String kcclx;

    /**
    * 观察者姓名
    */
    private String gczxm;

    /**
    * 考察区域
    */
    private String gcqy;

    /**
    * 考察单位
    */
    private String gcdw;

    /**
    * 开始观察时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ksgcsj;

    /**
    * 左方观察者
    */
    private String pZfgcz;

    /**
    * 记录者
    */
    private String pJlz;

    /**
    * 右方观察者
    */
    private String pYfgcz;

    /**
    * 独立观察者
    */
    private String pDlgcz;

    /**
    * 独立观察者所用方法
    */
    private String pDlgczsyff;

    /**
    * 附加观察者
    */
    private String pFjgcz;

    /**
    * 总体观察状况
    */
    private String wZtgczk;

    /**
    * 眩光所在范围
    */
    private String wXgszfw;

    /**
    * 眩光状况
    */
    private String wXgzk;

    /**
    * 近岸距离(米)
    */
    private String nJajl;

    /**
    * 水深(米)
    */
    private String nSs;

    /**
    * 船速
    */
    private String nCs;

    /**
    * 近岸方向
    */
    private String nJafx;

    /**
    * 方向
    */
    private String nFx;

    /**
    * 结束观察时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date jsgcsj;

    /**
    * 上传时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date scsj;

    /**
    * 更新时间
    */
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date gxsj;

    /**
    * 登录用户所在部门
    */
    private String deptcode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKcclx() {
        return kcclx;
    }

    public void setKcclx(String kcclx) {
        this.kcclx = kcclx;
    }

    public String getGczxm() {
        return gczxm;
    }

    public void setGczxm(String gczxm) {
        this.gczxm = gczxm;
    }

    public String getGcqy() {
        return gcqy;
    }

    public void setGcqy(String gcqy) {
        this.gcqy = gcqy;
    }

    public String getGcdw() {
        return gcdw;
    }

    public void setGcdw(String gcdw) {
        this.gcdw = gcdw;
    }

    public Date getKsgcsj() {
        return ksgcsj;
    }

    public void setKsgcsj(Date ksgcsj) {
        this.ksgcsj = ksgcsj;
    }

    public String getPZfgcz() {
        return pZfgcz;
    }

    public void setPZfgcz(String pZfgcz) {
        this.pZfgcz = pZfgcz;
    }

    public String getPJlz() {
        return pJlz;
    }

    public void setPJlz(String pJlz) {
        this.pJlz = pJlz;
    }

    public String getPYfgcz() {
        return pYfgcz;
    }

    public void setPYfgcz(String pYfgcz) {
        this.pYfgcz = pYfgcz;
    }

    public String getPDlgcz() {
        return pDlgcz;
    }

    public void setPDlgcz(String pDlgcz) {
        this.pDlgcz = pDlgcz;
    }

    public String getPDlgczsyff() {
        return pDlgczsyff;
    }

    public void setPDlgczsyff(String pDlgczsyff) {
        this.pDlgczsyff = pDlgczsyff;
    }

    public String getPFjgcz() {
        return pFjgcz;
    }

    public void setPFjgcz(String pFjgcz) {
        this.pFjgcz = pFjgcz;
    }

    public String getWZtgczk() {
        return wZtgczk;
    }

    public void setWZtgczk(String wZtgczk) {
        this.wZtgczk = wZtgczk;
    }

    public String getWXgszfw() {
        return wXgszfw;
    }

    public void setWXgszfw(String wXgszfw) {
        this.wXgszfw = wXgszfw;
    }

    public String getWXgzk() {
        return wXgzk;
    }

    public void setWXgzk(String wXgzk) {
        this.wXgzk = wXgzk;
    }

    public String getNJajl() {
        return nJajl;
    }

    public void setNJajl(String nJajl) {
        this.nJajl = nJajl;
    }

    public String getNSs() {
        return nSs;
    }

    public void setNSs(String nSs) {
        this.nSs = nSs;
    }

    public String getNCs() {
        return nCs;
    }

    public void setNCs(String nCs) {
        this.nCs = nCs;
    }

    public String getNJafx() {
        return nJafx;
    }

    public void setNJafx(String nJafx) {
        this.nJafx = nJafx;
    }

    public String getNFx() {
        return nFx;
    }

    public void setNFx(String nFx) {
        this.nFx = nFx;
    }

    public Date getJsgcsj() {
        return jsgcsj;
    }

    public void setJsgcsj(Date jsgcsj) {
        this.jsgcsj = jsgcsj;
    }

    public Date getScsj() {
        return scsj;
    }

    public void setScsj(Date scsj) {
        this.scsj = scsj;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", kcclx=").append(kcclx);
            sb.append(", gczxm=").append(gczxm);
            sb.append(", gcqy=").append(gcqy);
            sb.append(", gcdw=").append(gcdw);
            sb.append(", ksgcsj=").append(ksgcsj);
            sb.append(", pZfgcz=").append(pZfgcz);
            sb.append(", pJlz=").append(pJlz);
            sb.append(", pYfgcz=").append(pYfgcz);
            sb.append(", pDlgcz=").append(pDlgcz);
            sb.append(", pDlgczsyff=").append(pDlgczsyff);
            sb.append(", pFjgcz=").append(pFjgcz);
            sb.append(", wZtgczk=").append(wZtgczk);
            sb.append(", wXgszfw=").append(wXgszfw);
            sb.append(", wXgzk=").append(wXgzk);
            sb.append(", nJajl=").append(nJajl);
            sb.append(", nSs=").append(nSs);
            sb.append(", nCs=").append(nCs);
            sb.append(", nJafx=").append(nJafx);
            sb.append(", nFx=").append(nFx);
            sb.append(", jsgcsj=").append(jsgcsj);
            sb.append(", scsj=").append(scsj);
            sb.append(", gxsj=").append(gxsj);
            sb.append(", deptcode=").append(deptcode);
        sb.append("]");
        return sb.toString();
    }

}