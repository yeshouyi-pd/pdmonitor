package com.pd.environment.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.TurbidityDto;
import com.pd.server.main.service.TurbidityService;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/turbidi")
public class TurbidiHttpController {

    private static final Logger LOG = LoggerFactory.getLogger(TurbidiHttpController.class);
    public static final String BUSINESS_NAME = "接收第三方浊度仪数据";

    @Resource
    private TurbidityService turbidityService;

    /**
     * 保存数据
     */
    @PostMapping("/sendData")
    public ResponseDto sendData(@RequestBody String str) {
        ResponseDto responseDto = new ResponseDto();
        try {
            System.out.println(str);
        }catch (Exception e){
            LOG.error("接收到的数据："+str+"====错误原因："+e.getMessage());
        }
        responseDto.setCode("0000");
        responseDto.setSuccess(true);
        responseDto.setMessage("保存成功");
        return responseDto;
    }
}
