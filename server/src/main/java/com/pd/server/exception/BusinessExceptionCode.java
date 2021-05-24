package com.pd.server.exception;

public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("登录名已存在"),
    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    DEPT_CODE_EXIST("部门编号已存在"),
    CODE_TYPE_EXIST("代码和参数名已存在"),
    UPCODE_EXIST("当前部门存在下级部门，不能删除"),
    YYTIME_EXIST("当前时段存存在 相关重复信息，请重试！"),
    YYTIME_VAILD("请输入合法预约配置参数信息！"),
    YYTIME_BEFOR_VAILD("开始时间段不能大于结束时间段"),
    ATTR_CODE_EXIST("代码属性已存在"),
    CENTER_CODE_EXIST("代码和名称已存在"),
    EQUIPMENT_EXIST("设备不唯一，设备sn已存在");

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
