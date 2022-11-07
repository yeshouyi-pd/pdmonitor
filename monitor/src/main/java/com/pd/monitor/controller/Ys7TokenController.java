package com.pd.monitor.controller;


import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.utils.Ys7Utils;
import com.pd.server.main.domain.Attr;
import com.pd.server.main.domain.AttrExample;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.mapper.AttrMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ys7Token")
public class Ys7TokenController {

    private static final Logger LOG = LoggerFactory.getLogger(Ys7TokenController.class);

    @Resource
    private AttrMapper attrMapper;
    /**
     * 获取萤石云token
     * @return
     */
    @GetMapping("/getYcToken")
    public ResponseDto getYcToken() {
        ResponseDto responseDto = new ResponseDto();
        String token_ys = "";
        if(StringUtils.isBlank(Ys7Utils.YS_TOKEN)){
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
                        token_ys = Ys7Utils.YS_TOKEN;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        responseDto.setContent(token_ys);
        return responseDto;
    }
}
