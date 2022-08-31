package com.pd.server.main.dto;

public class WelcomeKvDto  extends  KvMapDto{

    private  String deptname ;

    private String deptcode;

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }
}
