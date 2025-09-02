package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class AppMonitorManualEntryeDto {

    /**
    * 
    */
    private String id;

    /**
    * 
    */
    private String mid;

    /**
    * 人工观察录入时间
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
    * 渔船数量
    */
    private String vYcsl;

    /**
    * 大船数量
    */
    private String vDcsl;

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
    * 停泊船数量
    */
    private String vTbcsl;

    /**
    * 移动船数量
    */
    private String vYdcsl;


    private String gczxm;

    private String deptcode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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

    public String getVYcsl() {
        return vYcsl;
    }

    public void setVYcsl(String vYcsl) {
        this.vYcsl = vYcsl;
    }

    public String getVDcsl() {
        return vDcsl;
    }

    public void setVDcsl(String vDcsl) {
        this.vDcsl = vDcsl;
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

    public String getVTbcsl() {
        return vTbcsl;
    }

    public void setVTbcsl(String vTbcsl) {
        this.vTbcsl = vTbcsl;
    }

    public String getVYdcsl() {
        return vYdcsl;
    }

    public void setVYdcsl(String vYdcsl) {
        this.vYdcsl = vYdcsl;
    }

    public String getGczxm() {
        return gczxm;
    }

    public void setGczxm(String gczxm) {
        this.gczxm = gczxm;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }
}