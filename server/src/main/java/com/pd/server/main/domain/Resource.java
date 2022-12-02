package com.pd.server.main.domain;

public class Resource {
    private String id;

    private String name;

    private String page;

    private String request;

    private String parent;

    private String iocn;

    private String setbar;

    private String show;

    private String bz;

    private String sm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getIocn() {
        return iocn;
    }

    public void setIocn(String iocn) {
        this.iocn = iocn;
    }

    public String getSetbar() {
        return setbar;
    }

    public void setSetbar(String setbar) {
        this.setbar = setbar;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
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
        sb.append(", name=").append(name);
        sb.append(", page=").append(page);
        sb.append(", request=").append(request);
        sb.append(", parent=").append(parent);
        sb.append(", iocn=").append(iocn);
        sb.append(", setbar=").append(setbar);
        sb.append(", show=").append(show);
        sb.append(", bz=").append(bz);
        sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }
}