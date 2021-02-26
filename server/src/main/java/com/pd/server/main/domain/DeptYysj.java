package com.pd.server.main.domain;

public class DeptYysj {
    private String id;

    private String deptcode;

    private String stime;

    private String etime;

    private Integer yymun;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public Integer getYymun() {
        return yymun;
    }

    public void setYymun(Integer yymun) {
        this.yymun = yymun;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", deptcode=").append(deptcode);
        sb.append(", stime=").append(stime);
        sb.append(", etime=").append(etime);
        sb.append(", yymun=").append(yymun);
        sb.append("]");
        return sb.toString();
    }
}