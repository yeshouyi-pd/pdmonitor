package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.wx.wxutlis.utils.ShjJsonConstant;
import com.pd.server.main.service.shj.AbstractScanRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shj")
public class ShjController {
    private static final Logger LOG = LoggerFactory.getLogger(ShjController.class);
    public static final String BUSINESS_NAME = "水环境接口";

    private AbstractScanRequest dsjkRequest;

    @PostMapping(value = "execute", produces ="application/json;charset=UTF-8")
    public JSONObject execute(@RequestBody String jsonParam) {
        LOG.info("================请求数据:"+jsonParam);
        JSONObject returnObject = new JSONObject();
        String methodname = "";
        String data = "";
        try {
            if(StringUtils.isEmpty(jsonParam)){
                returnObject.put("returnCode", ShjJsonConstant.CODE_4000);
                returnObject.put("returnMsg", ShjJsonConstant.MSG_4000);
                return returnObject;
            }
            JSONObject jsonObject = JSONObject.parseObject(jsonParam);
            JSONObject checkpam = checkpam(jsonObject);
            methodname = jsonObject.getString("method");
            if(null != checkpam){
                return checkpam;
            }
            String actionName = ShjJsonConstant.commandMap.get(methodname);
            Class<?> shjClass =  Class.forName(actionName);
            AbstractScanRequest shjRequest = (AbstractScanRequest)shjClass.getConstructor().newInstance();
            data = shjRequest.request(jsonObject.getJSONObject("data"));
            if("参数错误".equals(data)){
                returnObject.put("returnCode", ShjJsonConstant.CODE_4000);
                returnObject.put("returnMsg", ShjJsonConstant.MSG_4000);
            }else if("系统异常".equals(data)){
                returnObject.put("returnCode", ShjJsonConstant.CODE_2001);
                returnObject.put("returnMsg", ShjJsonConstant.MSG_2001);
            }else{
                returnObject.put("returnCode", ShjJsonConstant.CODE_0000);
                returnObject.put("returnMsg", ShjJsonConstant.MSG_0000);
            }
        } catch (Exception e){
            returnObject.put("returnCode", ShjJsonConstant.CODE_2001);
            returnObject.put("returnMsg", ShjJsonConstant.MSG_2001);
        }
        return retJson(returnObject, methodname, data);
    }

    public JSONObject checkpam(JSONObject jsonObject) {
        JSONObject returnObject = new JSONObject();
        String methodname = jsonObject.getString("method");
        String reqdate = jsonObject.getString("data");
        if(StringUtils.isBlank(methodname)){
            returnObject.put("returnCode", ShjJsonConstant.CODE_4104);
            returnObject.put("returnMsg", ShjJsonConstant.MSG_4104);
            return returnObject;
        }
        if(StringUtils.isBlank(reqdate)){
            returnObject.put("returnCode", ShjJsonConstant.CODE_4000);
            returnObject.put("returnMsg", ShjJsonConstant.MSG_4000);
            return returnObject;
        }
        return null;
    }

    public JSONObject retJson(JSONObject returnObject, String method,String data){
        returnObject.put("method", method);
        returnObject.put("data", data);
        return returnObject;
    }
}