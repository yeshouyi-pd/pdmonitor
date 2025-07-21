package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.utils.ContainerSingleton;
import com.pd.monitor.utils.SendSmsTool;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.monitor.wx.conf.WxRedisConfig;
import com.pd.monitor.wx.wxutlis.utils.ShjJsonConstant;
import com.pd.server.config.CodeType;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.InterfaceLog;
import com.pd.server.main.mapper.InterfaceLogMapper;
import com.pd.server.main.service.PointerSecondService;
import com.pd.server.main.service.shj.AbstractScanRequest;
import com.pd.server.util.UuidUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/shj")
public class ShjController{
    private static final Logger LOG = LoggerFactory.getLogger(ShjController.class);
    public static final String BUSINESS_NAME = "水环境接口";

    private AbstractScanRequest dsjkRequest;

    @PostMapping(value = "execute", produces ="application/json;charset=UTF-8")
    public JSONObject execute(@RequestBody String jsonParam, HttpServletRequest request) {
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
            AbstractScanRequest shjRequest = ContainerSingleton.singletonMap.get(methodname);
            if(null ==  shjRequest){
                shjRequest = (AbstractScanRequest) SpringUtil.getBean( ShjJsonConstant.commandMap.get(methodname));
                ContainerSingleton.putInstance(methodname,shjRequest);
            }
            data = shjRequest.request(jsonObject.getJSONObject("data"));
            if("EquipmentFile".equals(methodname)||"EquipmentFileByTy".equals(methodname)){
                if(data.contains("data")){
                    JSONObject result = JSONObject.parseObject(data);
                    String resultdata = result.getString("data");
                    if("保存成功".equals(resultdata)){
                        //判断是不是基站和驱离文件。如果不是需要传到前端页面做地图实时展示
                        if(StringUtils.isEmpty(result.getString("otherFile"))){
                            JSONObject entity = result.getJSONObject("entity");
                            //向页面推送数据
                            WebSocketServer.sendInfo(entity.toJSONString(),null);
                            //如果是铜陵长江大桥的数据，则需要发送短信
                            if(("A4001".equals(entity.getString("sbbh"))||"A4002".equals(entity.getString("sbbh"))||"A4003".equals(entity.getString("sbbh")))&&("1019".equals(entity.getString("type")))){
                                //发送短信
                                String templateId = "1018".equals(entity.getString("type"))?"1823144":"1847667";
                                String sbmc = "";
                                if("A4001".equals(entity.getString("sbbh"))){
                                    sbmc = "下游1";
                                }else if("A4002".equals(entity.getString("sbbh"))){
                                    sbmc = "大桥2";
                                }else if("A4003".equals(entity.getString("sbbh"))){
                                    sbmc = "上游3";
                                }
                                SendSmsTool.sendSmsNotPhone(templateId,sbmc+"-"+entity.getString("ts")+"dB");
                            }
                            entity.remove("sm1");
                        }
                    }
                }
            }
            if("参数错误".equals(data) || data.contains("错误")){
                returnObject.put("returnCode", ShjJsonConstant.CODE_4000);
                returnObject.put("returnMsg", ShjJsonConstant.MSG_4000);
            }else if(data.contains("成功")){
                returnObject.put("returnCode", ShjJsonConstant.CODE_0000);
                returnObject.put("returnMsg", ShjJsonConstant.MSG_0000);
            }else {
                returnObject.put("returnCode", ShjJsonConstant.CODE_2001);
                returnObject.put("returnMsg", ShjJsonConstant.MSG_2001);
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
