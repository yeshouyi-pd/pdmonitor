package com.pd.monitor.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.wx.conf.WxRedisConfig;
import com.tencentcloudapi.common.Credential;

//导入可选配置类
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

public class SendSmsTool {

    public static final Logger LOG = LoggerFactory.getLogger(SendSmsTool.class);

    public static Boolean sendSms(String templateId, String params){
        try {
            Map<String, String> attrMap = WxRedisConfig.getAttrMap();
            String secretId = attrMap.get("secretId");
            String secretKey = attrMap.get("secretKey");
            Credential cred = new Credential(secretId, secretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("POST");
            httpProfile.setConnTimeout(60);
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            String sdkAppId = attrMap.get("sdkAppId");
            req.setSmsSdkAppid(sdkAppId);
            String signName = attrMap.get("signName");
            req.setSign(signName);
            req.setTemplateID(templateId);
//            String[] templateParamSet = {"A4001","145dB"};
            String[] templateParamSet = params.split("-");
            req.setTemplateParamSet(templateParamSet);
//            String[] phoneNumberSet = {"+8618827512017","+8618827512017"};
            String[] phoneNumberSet = attrMap.get("phoneNumber").split(",");
            req.setPhoneNumberSet(phoneNumberSet);
            String sessionContext = "";
            req.setSessionContext(sessionContext);
            String extendCode = "";
            req.setExtendCode(extendCode);
            String senderid = "";
            req.setSenderId(senderid);
            if(!StringUtils.isEmpty(attrMap.get("phoneNumber"))){
                SendSmsResponse res = client.SendSms(req);
                JSONObject resultJson = JSON.parseObject(SendSmsResponse.toJsonString(res));
                LOG.error("短信返回数据："+resultJson);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("发送短信失败："+e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        sendSms("1823144","A4001-145dB");
    }
}
