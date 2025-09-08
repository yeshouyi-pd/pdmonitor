package com.pd.server.main.domain;

import com.pd.server.main.dto.PageDto;

import java.util.Date;

public class AppCodeSet  extends PageDto {
    private String id;

    private String codeName;

    private String codeValue;

    private String typeValue;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", codeName=").append(codeName);
        sb.append(", codeValue=").append(codeValue);
        sb.append(", typeValue=").append(typeValue);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}