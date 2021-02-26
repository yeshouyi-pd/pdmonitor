package com.pd.wxbase.wx.wxutlis.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 电视交警接口常量类
 */
public class DsjkJsonConstant {

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
    public static String MSG_4104 = "业务数据不存在或不合法" ;

    public static String CODE_4105 = "4105" ;
    public static String MSG_4105 = "签名不存在或不合法" ;

    /**
     * 对应的操作名称与类的对应
     */
    public static Map<String, String> commandMap = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
            /**
             * 新闻头条
             */
            put("NewsHeadlines", "com.pd.server.main.service.dsjk.NewsHeadlinesService");
            /**
             * 违法曝光台
             */
            put("IllegalExposure", "com.pd.server.main.service.dsjk.IllegalExposureService");
            /**
             * 预约查询
             */
            put("Wxyy", "com.pd.server.main.service.dsjk.WxyyService");
            /**
             * 办事网点
             */
            put("Bswd", "com.pd.server.main.service.dsjk.BswdService");
            /**
             * 办事流程
             */
            put("Bslc", "com.pd.server.main.service.dsjk.BslcService");
            /**
             * 违法在线教育视频
             */
            put("Wfzxjyspxx", "com.pd.server.main.service.dsjk.WfzxjyspxxService");
        }
    };

}
