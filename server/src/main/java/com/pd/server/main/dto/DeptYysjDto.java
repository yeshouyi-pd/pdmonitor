package com.pd.server.main.dto;


public class DeptYysjDto {

    /**
    * id
    */
    private String id;

    /**
    * 部门编号
    */
    private String deptcode;

    /**
    * 开始时段
    */
    private String stime;

    /**
    * 结束时段
    */
    private String etime;

    /**
    * 预约数量
    */
    private Integer yymun;

    /**
     * 选择的天
     * @return
     */
    private String checkday;


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

    public String getCheckday() {
        return checkday;
    }

    public void setCheckday(String checkday) {
        this.checkday = checkday;
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