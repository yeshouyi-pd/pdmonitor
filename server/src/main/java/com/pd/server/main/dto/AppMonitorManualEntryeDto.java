package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class AppMonitorManualEntryeDto extends PageDto {

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

    public String getpZfgcz() {
        return pZfgcz;
    }

    public void setpZfgcz(String pZfgcz) {
        this.pZfgcz = pZfgcz;
    }

    public String getpJlz() {
        return pJlz;
    }

    public void setpJlz(String pJlz) {
        this.pJlz = pJlz;
    }

    public String getpYfgcz() {
        return pYfgcz;
    }

    public void setpYfgcz(String pYfgcz) {
        this.pYfgcz = pYfgcz;
    }

    public String getpDlgcz() {
        return pDlgcz;
    }

    public void setpDlgcz(String pDlgcz) {
        this.pDlgcz = pDlgcz;
    }

    public String getpDlgczsyff() {
        return pDlgczsyff;
    }

    public void setpDlgczsyff(String pDlgczsyff) {
        this.pDlgczsyff = pDlgczsyff;
    }

    public String getpFjgcz() {
        return pFjgcz;
    }

    public void setpFjgcz(String pFjgcz) {
        this.pFjgcz = pFjgcz;
    }

    public String getwZtgczk() {
        return wZtgczk;
    }

    public void setwZtgczk(String wZtgczk) {
        this.wZtgczk = wZtgczk;
    }

    public String getwXgszfw() {
        return wXgszfw;
    }

    public void setwXgszfw(String wXgszfw) {
        this.wXgszfw = wXgszfw;
    }

    public String getwXgzk() {
        return wXgzk;
    }

    public void setwXgzk(String wXgzk) {
        this.wXgzk = wXgzk;
    }

    public String getnJajl() {
        return nJajl;
    }

    public void setnJajl(String nJajl) {
        this.nJajl = nJajl;
    }

    public String getnSs() {
        return nSs;
    }

    public void setnSs(String nSs) {
        this.nSs = nSs;
    }

    public String getnCs() {
        return nCs;
    }

    public void setnCs(String nCs) {
        this.nCs = nCs;
    }

    public String getnJafx() {
        return nJafx;
    }

    public void setnJafx(String nJafx) {
        this.nJafx = nJafx;
    }

    public String getnFx() {
        return nFx;
    }

    public void setnFx(String nFx) {
        this.nFx = nFx;
    }

    public String getvYcsl() {
        return vYcsl;
    }

    public void setvYcsl(String vYcsl) {
        this.vYcsl = vYcsl;
    }

    public String getvDcsl() {
        return vDcsl;
    }

    public void setvDcsl(String vDcsl) {
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

    public String getvTbcsl() {
        return vTbcsl;
    }

    public void setvTbcsl(String vTbcsl) {
        this.vTbcsl = vTbcsl;
    }

    public String getvYdcsl() {
        return vYdcsl;
    }

    public void setvYdcsl(String vYdcsl) {
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