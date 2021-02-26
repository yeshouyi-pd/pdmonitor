package com.pd.server.main.dto;


public class AddrInfoDto {

    /**
    * 主键
    */
    private String id;

    /**
    * 地址编码
    */
    private String code;

    /**
    * 地址名称
    */
    private String name;

    /**
    * 上一级地址编码
    */
    private String pcode;

    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getCode() {
    return code;
    }

    public void setCode(String code) {
    this.code = code;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getPcode() {
    return pcode;
    }

    public void setPcode(String pcode) {
    this.pcode = pcode;
    }


@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(getClass().getSimpleName());
sb.append(" [");
sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", code=").append(code);
    sb.append(", name=").append(name);
    sb.append(", pcode=").append(pcode);
sb.append("]");
return sb.toString();
}

}