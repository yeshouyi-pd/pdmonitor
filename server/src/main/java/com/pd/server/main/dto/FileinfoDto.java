package com.pd.server.main.dto;


public class FileinfoDto {

    /**
    * id
    */
    private String id;

    /**
    * 文件key
    */
    private String key;

    /**
    * 项目编号
    */
    private String xmbh;

    /**
    * 设别编号
    */
    private String sbsn;

    /**
    * 
    */
    private String f1;

    /**
    * 
    */
    private String f2;

    /**
    * 创建时间
    */
    private String cjsj;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getSbsn() {
        return sbsn;
    }

    public void setSbsn(String sbsn) {
        this.sbsn = sbsn;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
            sb.append(", id=").append(id);
            sb.append(", key=").append(key);
            sb.append(", xmbh=").append(xmbh);
            sb.append(", sbsn=").append(sbsn);
            sb.append(", f1=").append(f1);
            sb.append(", f2=").append(f2);
            sb.append(", cjsj=").append(cjsj);
        sb.append("]");
        return sb.toString();
    }

}