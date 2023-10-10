package com.pd.server.main.domain;

public class CameraMiddle {
    private String id;

    private String sbbh;

    private String jqsj;

    private String jgsj;

    private String ip;

    private String port;

    private String username;

    private String camerapws;

    private String tdh;

    private String dvrip;

    private String sfjq;

    private String bz;

    private String sm;

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

    public String getJqsj() {
        return jqsj;
    }

    public void setJqsj(String jqsj) {
        this.jqsj = jqsj;
    }

    public String getJgsj() {
        return jgsj;
    }

    public void setJgsj(String jgsj) {
        this.jgsj = jgsj;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCamerapws() {
        return camerapws;
    }

    public void setCamerapws(String camerapws) {
        this.camerapws = camerapws;
    }

    public String getTdh() {
        return tdh;
    }

    public void setTdh(String tdh) {
        this.tdh = tdh;
    }

    public String getDvrip() {
        return dvrip;
    }

    public void setDvrip(String dvrip) {
        this.dvrip = dvrip;
    }

    public String getSfjq() {
        return sfjq;
    }

    public void setSfjq(String sfjq) {
        this.sfjq = sfjq;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sbbh=").append(sbbh);
        sb.append(", jqsj=").append(jqsj);
        sb.append(", jgsj=").append(jgsj);
        sb.append(", ip=").append(ip);
        sb.append(", port=").append(port);
        sb.append(", username=").append(username);
        sb.append(", camerapws=").append(camerapws);
        sb.append(", tdh=").append(tdh);
        sb.append(", dvrip=").append(dvrip);
        sb.append(", sfjq=").append(sfjq);
        sb.append(", bz=").append(bz);
        sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }
}