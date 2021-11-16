package com.pd.server.config;

public enum RedisCode {

    CODESET("code_set"),//参数表缓存（keval）

    CODESETVO("code_setvo"),//参数表缓存(对象)

    DEPTCODENAME("dept_code_name"),//部门信息缓存

    USERCODENAME("user_code_name"),//用户缓存

    ISWORKINGDAY("is_wory_day"),//是否是工作日

    ATTRECODEKEY("attr_code_key"),//系统参数管理

    ACCESSTOKEN("access_token"),//网页授权接口调用凭证

    STATICPATH("staticpath"),//静态路径地址

    AllUSER("alluser"),//所有用户

    SBSNCENTERCODE("sbsncentercode"),//设备sn对应的监测点

    PROJECTCODENAME("project_code_name"),//项目信息缓存

    PROJECTSBSNS("project_sbsbs"),//项目编号对应的设备sn信息
    ;

    private String desc;


    RedisCode(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
