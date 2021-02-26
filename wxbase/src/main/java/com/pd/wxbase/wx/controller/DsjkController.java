package com.pd.wxbase.wx.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.pd.server.main.service.dsjk.AbstractScanRequest;
import com.pd.server.util.DateUtil;
import com.pd.wxbase.wx.wxutlis.utils.Aes;
import com.pd.wxbase.wx.wxutlis.utils.DsjkJsonConstant;
import com.pd.wxbase.wx.wxutlis.utils.JKUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.TreeMap;

@RestController
@RequestMapping("/dsjk")
public class DsjkController {

    private static final Logger LOG = LoggerFactory.getLogger(DsjkController.class);
    public static final String BUSINESS_NAME = "电机交警接口";

    public static final String APPID = "APPID88888";

    private AbstractScanRequest dsjkRequest;

    @PostMapping(value = "execute", produces ="application/json;charset=UTF-8")
    public JSONObject execute(@RequestBody String jsonParam) {
        LOG.info("================请求数据:"+jsonParam);
        JSONObject returnObject = new JSONObject();
        String methodname = "";
        String serialNo = "";
        String data = "";
        try {
            if(StringUtils.isEmpty(jsonParam)){
                returnObject.put("returnCode", DsjkJsonConstant.CODE_4000);
                returnObject.put("returnMsg", DsjkJsonConstant.MSG_4000);
                return returnObject;
            }
            JSONObject jsonObject = JSONObject.parseObject(jsonParam);
            JSONObject checkpam = checkpam(jsonObject);
            methodname = jsonObject.getString("method");
            serialNo = jsonObject.getString("serialNo");
            if(null != checkpam){
                return retJson(checkpam,serialNo, methodname, data);
            }
            String actionName = DsjkJsonConstant.commandMap.get(methodname);
            Class<?> dsjkClass =  Class.forName(actionName);
            AbstractScanRequest dsjkRequest = (AbstractScanRequest)dsjkClass.getConstructor().newInstance();
            String reqdate = jsonObject.getString("data");
            if(reqdate.equals("KG2miYeWQxsM+Txusqy+Hg==")){
                data = dsjkRequest.request(reqdate);
            }else{
                data = dsjkRequest.request(Aes.getDecoder(reqdate, Aes.MD5EncodeByte(Aes.appKey, "UTF-8")));
            }
            if("参数错误".equals(data)){
                returnObject.put("returnCode", DsjkJsonConstant.CODE_4000);
                returnObject.put("returnMsg", DsjkJsonConstant.MSG_4000);
            }else if("系统异常".equals(data)){
                returnObject.put("returnCode", DsjkJsonConstant.CODE_2001);
                returnObject.put("returnMsg", DsjkJsonConstant.MSG_2001);
            }else{
                returnObject.put("returnCode", DsjkJsonConstant.CODE_0000);
                returnObject.put("returnMsg", DsjkJsonConstant.MSG_0000);
            }
        } catch (Exception e){
            returnObject.put("returnCode", DsjkJsonConstant.CODE_2001);
            returnObject.put("returnMsg", DsjkJsonConstant.MSG_2001);
        }
        return retJson(returnObject,serialNo, methodname, data);
    }

    public JSONObject retJson(JSONObject returnObject,String serialNo, String method,String data){
        returnObject.put("appId", APPID);
        returnObject.put("serialNo", serialNo);
        String timeStamp = DateUtil.getYmdhms();
        returnObject.put("timeStamp", timeStamp);
        returnObject.put("method", method);
        String jiamdata = Aes.getEncoder(data, Aes.MD5EncodeByte(Aes.appKey, "UTF-8"));
        returnObject.put("data", jiamdata);
        TreeMap<String,String> treeMap = new TreeMap<>();
        treeMap.put("appId", APPID);
        treeMap.put("serialNo", serialNo);
        treeMap.put("timeStamp", timeStamp);
        treeMap.put("method", method);
        treeMap.put("data", jiamdata);
        treeMap.put("appKey", Aes.appKey);
        String newSign = JKUtils.getSign(JKUtils.createLinkString(treeMap));
        returnObject.put("sign", newSign);
        return returnObject;
    }

    public JSONObject checkpam(JSONObject jsonObject) {
        JSONObject returnObject = new JSONObject();
        String appId = jsonObject.getString("appId");
        String serialNo = jsonObject.getString("serialNo");
        String timeStamp = jsonObject.getString("timeStamp");
        String methodname = jsonObject.getString("method");
        String reqdate = jsonObject.getString("data");
        String sign = jsonObject.getString("sign");
        if(StringUtils.isBlank(appId) || !APPID.equals(appId)){
            returnObject.put("returnCode", DsjkJsonConstant.CODE_4101);
            returnObject.put("returnMsg", DsjkJsonConstant.MSG_4101);
            return returnObject;
        }
        if(StringUtils.isBlank(serialNo)){
            returnObject.put("returnCode", DsjkJsonConstant.CODE_4102);
            returnObject.put("returnMsg", DsjkJsonConstant.MSG_4102);
            return returnObject;
        }
        if(StringUtils.isBlank(timeStamp)){
            returnObject.put("returnCode", DsjkJsonConstant.CODE_4103);
            returnObject.put("returnMsg", DsjkJsonConstant.MSG_4103);
            return returnObject;
        }
        if(StringUtils.isBlank(reqdate)){
            returnObject.put("returnCode", DsjkJsonConstant.CODE_4104);
            returnObject.put("returnMsg", DsjkJsonConstant.MSG_4104);
            return returnObject;
        }
        String jiemdata = Aes.getDecoder(reqdate, Aes.MD5EncodeByte(Aes.appKey, "UTF-8"));
        if(null == jiemdata){
            returnObject.put("returnCode", DsjkJsonConstant.CODE_3002);
            returnObject.put("returnMsg", DsjkJsonConstant.MSG_3002);
            return returnObject;
        }
        if(StringUtils.isBlank(sign)){
            returnObject.put("returnCode", DsjkJsonConstant.CODE_4105);
            returnObject.put("returnMsg", DsjkJsonConstant.MSG_4105);
            return returnObject;
        }
        if(!isSign(sign,serialNo,timeStamp,methodname,reqdate)){
            returnObject.put("returnCode", DsjkJsonConstant.CODE_3001);
            returnObject.put("returnMsg", DsjkJsonConstant.MSG_3001);
            return returnObject;
        }
        if(StringUtils.isBlank(methodname)){
            returnObject.put("returnCode", DsjkJsonConstant.CODE_4000);
            returnObject.put("returnMsg", DsjkJsonConstant.MSG_4000);
            return returnObject;
        }
        return null;
    }

    public static boolean isSign(String sign, String serialNo, String timeStamp, String method, String data){
        TreeMap<String,String> treeMap = new TreeMap<>();
        treeMap.put("appId", APPID);
        treeMap.put("serialNo", serialNo);
        treeMap.put("timeStamp", timeStamp);
        treeMap.put("method", method);
        treeMap.put("data", data);
        treeMap.put("appKey", Aes.appKey);
        String px = JKUtils.createLinkString(treeMap);
        String newSign = JKUtils.getSign(px);
        if(newSign.equals(sign)){
            return true;
        }
        return false;
    }

    public static boolean isParamEmpty(String jsonParam,String equalParam){
        boolean returnValue = true;
        /**
         * 先判断字符串里面是否传递过该参数
         */
        if(jsonParam.contains(equalParam)){
            JSONObject jsonObject = JSONObject.parseObject(jsonParam);
            /**
             * 如果传递过该参数就判断值是否为空
             */
            String paramValue = jsonObject.getString(equalParam);
            if(!StringUtil.isEmpty(paramValue)){
                returnValue = false;
            }
        }
        return returnValue;
    }
}
