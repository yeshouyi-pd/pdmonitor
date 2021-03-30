package com.pd.server.main.dto;

import java.util.List;

public class MonitorEquipmentDto {

    private String name;
    private String code;
    private String type;
    private List<MonitorEquipmentDto> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MonitorEquipmentDto> getChildren() {
        return children;
    }

    public void setChildren(List<MonitorEquipmentDto> children) {
        this.children = children;
    }
}
