package com.pd.server.main.domain;

import java.util.Date;

public class Attr {
    private String id;

    private String attrcode;

    private String attrkey;

    private String content;

    private Date createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttrcode() {
        return attrcode;
    }

    public void setAttrcode(String attrcode) {
        this.attrcode = attrcode;
    }

    public String getAttrkey() {
        return attrkey;
    }

    public void setAttrkey(String attrkey) {
        this.attrkey = attrkey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", attrcode=").append(attrcode);
        sb.append(", attrkey=").append(attrkey);
        sb.append(", content=").append(content);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}