package com.pd.monitor.wx.wxutlis.utils;

import com.pd.monitor.controller.DeviceMonitorDataShjService;
import com.pd.server.main.service.shj.*;
import org.apache.poi.ss.formula.functions.T;

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
    public static Map<String, Class> commandMap = new HashMap<String, Class>() {
        private static final long serialVersionUID = 1L;
        {
            /**
             * 设备文件
             */
            put("EquipmentFile", EquipmentFileShjService.class);
            /**
             * 拖曳(A2)设备文件
             */
            put("EquipmentFileByTy", EquipmentFileTyShjService.class);
            /**
             * 设备请求时间间隔
             */
            put("ReqInterval", ReqIntervalShjService.class);
            /**
             * 设备心跳包
             */
            put("WaterEquip", WaterEquipShjService.class);
            /**
             * 非实时数据上传
             */
            put("WaterRawfile", WaterRawfileShjService.class);
            /**
             * 群次数据回传
             */
            put("GroupEvent", GroupEventShjService.class);
            /**
             * 指针数据回传（每秒）
             */
            put("PointerSecond", PointerSecondShjService.class);
            /**
             * 指针数据回传（每天）
             */
            put("PointerDay", PointerDayShjService.class);
            /**
             * 设备监测数据回传
             */
            put("DeviceMonitorData", DeviceMonitorDataShjService.class);
            /**
             * 趸船gps
             */
            put("PontoonGPS",PontoonGPSShjService.class);
            /**
             * 岸上视频剪切回调接口
             */
            put("VideoEvent",VideoEventShjService.class);
        }
    };

}
