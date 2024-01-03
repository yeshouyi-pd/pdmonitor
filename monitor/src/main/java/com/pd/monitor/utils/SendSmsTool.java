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

    public static Boolean sendSmsNotPhone(String templateId, String params){
        Map<String, String> attrMap = WxRedisConfig.getAttrMap();
        String secretId = attrMap.get("secretId");
        String secretKey = attrMap.get("secretKey");
        String sdkAppId = attrMap.get("sdkAppId");
        String signName = attrMap.get("signName");
        try {
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
            req.setSmsSdkAppid(sdkAppId);
            req.setSign(signName);
            req.setTemplateID(templateId);
//            String[] templateParamSet = {"A4001","145dB"};
            String[] templateParamSet = params.split("-");
            req.setTemplateParamSet(templateParamSet);
//            String[] phoneNumberSet = {"+8618827512017","+8618827512017"};
            String[] phoneNumberSet = attrMap.get("phoneNumber").split(",");
            req.setPhoneNumberSet(phoneNumberSet);
            req.setSessionContext("");
            req.setExtendCode("");
            req.setSenderId("");
            if(!StringUtils.isEmpty(attrMap.get("phoneNumber"))){
                SendSmsResponse res = client.SendSms(req);
                JSONObject resultJson = JSON.parseObject(SendSmsResponse.toJsonString(res));
                LOG.error("短信返回数据："+resultJson);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("发送短信失败："+e.getMessage());
        } finally {
            templateId = null;
            params = null;
            secretId = null;
            secretKey = null;
            sdkAppId = null;
            signName = null;
            System.gc();
        }
        return false;
    }

    public static Boolean sendSms(String templateId, String params, String phoneNum){
        Map<String, String> attrMap = WxRedisConfig.getAttrMap();
        String secretId = attrMap.get("secretId");
        String secretKey = attrMap.get("secretKey");
        String sdkAppId = attrMap.get("sdkAppId");
        String signName = attrMap.get("signName");
        try {
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
            req.setSmsSdkAppid(sdkAppId);
            req.setSign(signName);
            req.setTemplateID(templateId);
//            String[] templateParamSet = {"A4001","145dB"};
            String[] templateParamSet = params.split("-");
            req.setTemplateParamSet(templateParamSet);
//            String[] phoneNumberSet = {"+8618827512017","+8618827512017"};
            String[] phoneNumberSet = phoneNum.split(",");
            req.setPhoneNumberSet(phoneNumberSet);
            req.setSessionContext("");
            req.setExtendCode("");
            req.setSenderId("");
            if(!StringUtils.isEmpty(attrMap.get("phoneNumber"))){
                SendSmsResponse res = client.SendSms(req);
                JSONObject resultJson = JSON.parseObject(SendSmsResponse.toJsonString(res));
                LOG.error("短信返回数据："+resultJson);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("发送短信失败："+e.getMessage());
        } finally {
            templateId = null;
            params = null;
            phoneNum = null;
            secretId = null;
            secretKey = null;
            sdkAppId = null;
            signName = null;
            System.gc();
        }
        return false;
    }

    public static void main(String[] args) {
        sendSms("1823144","A4001-145dB", "");
    }
}
