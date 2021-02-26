package com.pd.server.main.dto.basewx.my;

public class YwyyParamDto {
    /**
     * 业务预约参数传递对象
     */

    private String ywfl;//业务分类
    private String ywlx;//业务类型

    private String searchvalue;//部门搜索

    public String getYwfl() {
        return ywfl;
    }

    public void setYwfl(String ywfl) {
        this.ywfl = ywfl;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }


    public String getSearchvalue() {
        return searchvalue;
    }

    public void setSearchvalue(String searchvalue) {
        this.searchvalue = searchvalue;
    }
}
