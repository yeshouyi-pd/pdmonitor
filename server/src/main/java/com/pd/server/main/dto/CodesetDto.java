package com.pd.server.main.dto;


public class CodesetDto extends PageDto{

    /**
    * id
    */
    private String id;

    /**
    * code值
    */
    private String code;

    /**
    * 名称
    */
    private String name;

    /**
    * 代码类别
    */
    private String type;

    /**
    * 描述
    */
    private String content;

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

    public String getType() {
    return type;
    }

    public void setType(String type) {
    this.type = type;
    }

    public String getContent() {
    return content;
    }

    public void setContent(String content) {
    this.content = content;
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
    sb.append(", type=").append(type);
    sb.append(", content=").append(content);
sb.append("]");
return sb.toString();
}

}