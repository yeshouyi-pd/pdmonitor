package com.pd.server.main.dto;

        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class AppMonitorManualEntryeDto {

    private String id;

    private String mid;
    /**
     * 人工观察录入时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ksgcsj;

    /**
     * 左方观察者
     */
    private String pzfgcz;
    /**
     * 记录者
     */
    private String pjlz;
    /**
     * 右方观察者
     */
    private String pyfgcz;
    /**
     * 独立观察者
     */
    private String pdlgcz;
    /**
     * 独立观察者所用方法
     */
    private String pdlgczsyff;
    /**
     * 附加观察者
     */
    private String pfjgcz;
    /**
     * 总体观察状况
     */
    private String wztgczk;

    /**
     * 眩光所在范围
     */
    private String wxgszfw;
    /**
     * 眩光状况
     */
    private String wxgzk;
    /**
     * 近岸距离(米)
     */
    private String njajl;
    /**
     * 水深(米)
     */
    private String nss;
    /**
     * 船速
     */
    private String ncs;
    /**
     * 近岸方向
     */
    private String njafx;
    /**
     * 方向
     */
    private String nfx;
    /**
     * 渔船数量
     */
    private String vycsl;
    /**
     * 大船数量
     */
    private String vdcsl;
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

    private String vtbcsl;
    /**
     * 移动船数量
     */
    private String vydcsl;

    private String deptcode;

    private String gczxm;

    private String gps;

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

    public String getPzfgcz() {
        return pzfgcz;
    }

    public void setPzfgcz(String pzfgcz) {
        this.pzfgcz = pzfgcz;
    }

    public String getPjlz() {
        return pjlz;
    }

    public void setPjlz(String pjlz) {
        this.pjlz = pjlz;
    }

    public String getPyfgcz() {
        return pyfgcz;
    }

    public void setPyfgcz(String pyfgcz) {
        this.pyfgcz = pyfgcz;
    }

    public String getPdlgcz() {
        return pdlgcz;
    }

    public void setPdlgcz(String pdlgcz) {
        this.pdlgcz = pdlgcz;
    }

    public String getPdlgczsyff() {
        return pdlgczsyff;
    }

    public void setPdlgczsyff(String pdlgczsyff) {
        this.pdlgczsyff = pdlgczsyff;
    }

    public String getPfjgcz() {
        return pfjgcz;
    }

    public void setPfjgcz(String pfjgcz) {
        this.pfjgcz = pfjgcz;
    }

    public String getWztgczk() {
        return wztgczk;
    }

    public void setWztgczk(String wztgczk) {
        this.wztgczk = wztgczk;
    }

    public String getWxgszfw() {
        return wxgszfw;
    }

    public void setWxgszfw(String wxgszfw) {
        this.wxgszfw = wxgszfw;
    }

    public String getWxgzk() {
        return wxgzk;
    }

    public void setWxgzk(String wxgzk) {
        this.wxgzk = wxgzk;
    }

    public String getNjajl() {
        return njajl;
    }

    public void setNjajl(String njajl) {
        this.njajl = njajl;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getNcs() {
        return ncs;
    }

    public void setNcs(String ncs) {
        this.ncs = ncs;
    }

    public String getNjafx() {
        return njafx;
    }

    public void setNjafx(String njafx) {
        this.njafx = njafx;
    }

    public String getNfx() {
        return nfx;
    }

    public void setNfx(String nfx) {
        this.nfx = nfx;
    }

    public String getVycsl() {
        return vycsl;
    }

    public void setVycsl(String vycsl) {
        this.vycsl = vycsl;
    }

    public String getVdcsl() {
        return vdcsl;
    }

    public void setVdcsl(String vdcsl) {
        this.vdcsl = vdcsl;
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

    public String getVtbcsl() {
        return vtbcsl;
    }

    public void setVtbcsl(String vtbcsl) {
        this.vtbcsl = vtbcsl;
    }

    public String getVydcsl() {
        return vydcsl;
    }

    public void setVydcsl(String vydcsl) {
        this.vydcsl = vydcsl;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getGczxm() {
        return gczxm;
    }

    public void setGczxm(String gczxm) {
        this.gczxm = gczxm;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }
}