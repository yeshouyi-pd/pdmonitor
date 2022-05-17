package com.pd.monitor.wx.wxutlis.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 电视交警接口常量类
 */
public class ShjJsonConstant {

    public static String CODE_0000 = "0000" ;
    public static String MSG_0000 = "成功" ;

    public static String CODE_2001 = "2001" ;
    public static String MSG_2001 = "系统异常" ;

    public static String CODE_3001 = "3001" ;
    public static String MSG_3001 = "签名校验失败" ;

    public static String CODE_3002 = "3002" ;
    public static String MSG_3002 = "业务数据解密失败" ;

    public static String CODE_4000 = "4000" ;
    public static String MSG_4000 = "请求参数错误" ;

    public static String CODE_4101 = "4101" ;
    public static String MSG_4101 = "合作方代码不存在或不合法" ;

    public static String CODE_4102 = "4102" ;
    public static String MSG_4102 = "通讯流水号不存在或不合法" ;

    public static String CODE_4103 = "4103" ;
    public static String MSG_4103 = "时间戳不存在或不合法" ;

    public static String CODE_4104 = "4104" ;
    public static String MSG_4104 = "请求方法名不存在或不合法" ;

    public static String CODE_4105 = "4105" ;
    public static String MSG_4105 = "签名不存在或不合法" ;

    /**
     * 对应的操作名称与类的对应
     */
    public static Map<String, String> commandMap = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
            /**
             * 设备文件
             */
            put("EquipmentFile", "com.pd.server.main.service.shj.EquipmentFileShjService");
            /**
             * 设备请求时间间隔
             */
            put("ReqInterval", "com.pd.server.main.service.shj.ReqIntervalShjService");
            /**
             * 设备心跳包
             */
            put("WaterEquip", "com.pd.server.main.service.shj.WaterEquipShjService");
            /**
             * 非实时数据上传
             */
            put("WaterRawfile", "com.pd.server.main.service.shj.WaterRawfileShjService");
            /**
             * 群次数据回传
             */
            put("GroupEvent", "com.pd.server.main.service.shj.GroupEventShjService");
            /**
             * 指针数据回传（每秒）
             */
            put("PointerSecond", "com.pd.server.main.service.shj.PointerSecondShjService");
            /**
             * 指针数据回传（每天）
             */
            put("PointerDay", "com.pd.server.main.service.shj.PointerDayShjService");
            /**
             * 设备监测数据回传
             */

        }
    };

}
