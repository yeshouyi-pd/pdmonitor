package com.pd.monitor.controller;

import com.pd.server.config.CodeType;
import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.*;
import com.pd.monitor.wx.conf.BaseWxController;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/CodeSetUtil")
public class CodeSetUtilController extends BaseWxController {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 获取水质检测项
     * @return
     */
    @GetMapping("/getSzjcx")
    public ResponseDto getSzjcx(){
        ResponseDto responseDto = new ResponseDto();
        Map<String,String> map = new LinkedHashMap<String,String>();
        Map<String, Map<String,String>>  allmap = (Map<String, Map<String, String>>) redisTemplate.opsForValue().get(RedisCode.CODESET);
        map = allmap.get(CodeType.SZJCX_CODE);
        responseDto.setContent(map);
        return responseDto;
    }

}
