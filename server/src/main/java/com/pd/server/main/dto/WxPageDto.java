package com.pd.server.main.dto;


public class WxPageDto extends PageDto{

    /**
    * 主键
    */
    private String id;

    /**
    * 页面名称
    */
    private String name;

    /**
    * 页面路径
    */
    private String url;

    /**
    * 页面图片
    */
    private String pic;

    /**
    * 页面顺序
    */
    private String sx;

    /**
    * 页面级别
    */
    private String level;

    /**
    * 页面关联id
    */
    private String linkId;

    /**
    * 是否启用
    */
    private String isEnable;

    /**
    * 父页面名称
    */
    private String parentName;

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

    public String getUrl() {
    return url;
    }

    public void setUrl(String url) {
    this.url = url;
    }

    public String getPic() {
    return pic;
    }

    public void setPic(String pic) {
    this.pic = pic;
    }

    public String getSx() {
    return sx;
    }

    public void setSx(String sx) {
    this.sx = sx;
    }

    public String getLevel() {
    return level;
    }

    public void setLevel(String level) {
    this.level = level;
    }

    public String getLinkId() {
    return linkId;
    }

    public void setLinkId(String linkId) {
    this.linkId = linkId;
    }

    public String getIsEnable() {
    return isEnable;
    }

    public void setIsEnable(String isEnable) {
    this.isEnable = isEnable;
    }

    public String getParentName() {
    return parentName;
    }

    public void setParentName(String parentName) {
    this.parentName = parentName;
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", name=").append(name);
    sb.append(", url=").append(url);
    sb.append(", pic=").append(pic);
    sb.append(", sx=").append(sx);
    sb.append(", level=").append(level);
    sb.append(", linkId=").append(linkId);
    sb.append(", isEnable=").append(isEnable);
    sb.append(", parentName=").append(parentName);
sb.append("]");
return sb.toString();
}

}