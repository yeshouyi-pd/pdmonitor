package com.pd.monitor.quartz;


import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.utils.Ys7Utils;
import com.pd.server.main.domain.Attr;
import com.pd.server.main.domain.AttrExample;
import com.pd.server.main.mapper.AttrMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class Ys7TokenQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(Ys7TokenQuartz.class);

    @Resource
    private AttrMapper attrMapper;


    /**
     *  服务启动时候调用一次  后续每天凌晨1点调用一次
     *  防止system 后启动 直接加载
     *  tonken 7天过期
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    public void getYcToken(){
        List<Attr> list = attrMapper.selectByExample(new AttrExample());
        if(!CollectionUtils.isEmpty(list)){
            String appkey = "4ce8004be4474718a94a97764d91e3b6";
            String appSecret = "5f8a510445aa7863aabc4fe3cd327a7c";
            for(Attr attr : list){
                if("ys_appkey".equals(attr.getAttrcode())){
                    appkey = attr.getAttrkey();
                }
                if("ys_appSecret".equals(attr.getAttrcode())){
                    appSecret = attr.getAttrkey();
                }

            }
            String openid_url = Ys7Utils.YS_GETTOKEN.replace("APPKEY", appkey).replace("APPSECRET", appSecret);
            try {
                JSONObject jsonObj  = Ys7Utils.doPostStr(openid_url, "");
                if("200".equals(jsonObj.getString("code"))){
                    String  accessToken =  jsonObj.getJSONObject("data").getString("accessToken");
                    Ys7Utils.YS_TOKEN = accessToken;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
