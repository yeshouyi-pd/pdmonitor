package com.pd.wxbase.wx.controller.bmfw;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.AddrInfoService;
import com.pd.wxbase.wx.conf.BaseWxController;
import com.pd.wxbase.wx.controller.AddrInfoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/addrInfo")
public class AppAddrInfoController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(AddrInfoController.class);
    public static final String BUSINESS_NAME = "地区";

    @Resource
    private AddrInfoService addrInfoService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list() {
        ResponseDto responseDto = new ResponseDto();
        JSONObject jsonObj = (JSONObject) redisTstaticemplate.opsForValue().get(RedisCode.ADDRINFO);
        responseDto.setContent(jsonObj);
        return responseDto;
    }

}
