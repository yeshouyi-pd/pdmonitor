package com.pd.server.main.dto.basewx.my;

public class TyDataDto {

    private Integer cxcs;//出现次数

    private Integer bscs;//捕食次数

    private Integer jlcs;//聚类次数

    private Integer ts;//最大豚数

    public Integer getCxcs() {
        return cxcs;
    }

    public void setCxcs(Integer cxcs) {
        this.cxcs = cxcs;
    }

    public Integer getBscs() {
        return bscs;
    }

    public void setBscs(Integer bscs) {
        this.bscs = bscs;
    }

    public Integer getJlcs() {
        return jlcs;
    }

    public void setJlcs(Integer jlcs) {
        this.jlcs = jlcs;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }
}
