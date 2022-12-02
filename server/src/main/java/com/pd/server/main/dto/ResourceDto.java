package com.pd.server.main.dto;


import java.util.List;

public class ResourceDto {

    /**
     * id
     */
    private String id;

    /**
     * 名称|菜单或按钮
     */
    private String name;

    /**
     * 页面|路由
     */
    private String page;

    /**
     * 请求|接口
     */
    private String request;

    /**
     * 父id
     */
    private String parent;

    private String iocn;

    private String setbar;

    private String show;

    private String bz;

    private String sm;

    private List<ResourceDto> children;

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

    public List<ResourceDto> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceDto> children) {
        this.children = children;
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
        return "ResourceDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", page='" + page + '\'' +
                ", request='" + request + '\'' +
                ", parent='" + parent + '\'' +
                ", iocn='" + iocn + '\'' +
                ", setbar='" + setbar + '\'' +
                ", children=" + children +
                '}';
    }
}