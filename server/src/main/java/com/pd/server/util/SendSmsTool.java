package com.pd.server.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.Attr;
import com.pd.server.main.mapper.AttrMapper;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SendSmsTool {

    public static final Logger LOG = LoggerFactory.getLogger(SendSmsTool.class);

    public static Boolean sendSms(String templateId, String params, String phoneNumber){
        AttrMapper attrMapper = SpringUtil.getBean(AttrMapper.class);
        List<Attr> attrList = attrMapper.selectByExample(null);
        Map<String, String> attrMap = attrList.stream().collect(Collectors.toMap(p -> p.getAttrcode(), p -> p.getAttrkey()));
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
            if(!StringUtils.isEmpty(params)){
                String[] templateParamSet = params.split("-");
                req.setTemplateParamSet(templateParamSet);
            }
//            String[] phoneNumberSet = {"+8618827512017","+8618827512017"};
            String[] phoneNumberSet = phoneNumber.split(",");
            req.setPhoneNumberSet(phoneNumberSet);
            req.setSessionContext("");
            req.setExtendCode("");
            req.setSenderId("");
            if(!StringUtils.isEmpty(phoneNumber)){
                SendSmsResponse res = client.SendSms(req);
                JSONObject resultJson = JSON.parseObject(SendSmsResponse.toJsonString(res));
                LOG.error("短信返回数据："+resultJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("发送短信失败："+e.getMessage());
            return false;
        } finally {
            templateId = null;
            params = null;
            phoneNumber = null;
            attrList.clear();
            attrMap.clear();
            secretId = null;
            secretKey = null;
            sdkAppId = null;
            signName = null;
            System.gc();
        }
        return true;
    }
}
