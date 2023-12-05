package com.pd.environment.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.ForecastNumDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.ForecastNumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/forecastNum")
public class ForecastNumRevController {

    private static final Logger LOG = LoggerFactory.getLogger(CurrentMeterRevController.class);
    public static final String BUSINESS_NAME = "江豚出现次数预测数据";

    @Resource
    private ForecastNumService forecastNumService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 保存数据
     */
    @PostMapping("/saveData")
    public ResponseDto saveData(@RequestBody JSONObject jsonObject) {
        ResponseDto responseDto = new ResponseDto();
        try {
            if(StringUtils.isEmpty(jsonObject.get("sbbh")) || StringUtils.isEmpty(jsonObject.get("cxcs"))
                || StringUtils.isEmpty(jsonObject.get("cxsj"))){
                responseDto.setSuccess(false);
                responseDto.setMessage("参数错误");
                return responseDto;
            }
            String sbbh = jsonObject.getString("sbbh");
            String cxcs = jsonObject.getString("cxcs");
            String cxsj = jsonObject.getString("cxsj");
            String isFlag = "^(\\d{4})-(\\d{2})-(\\d{2})\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";//判断时间
            if(!cxsj.matches(isFlag)){
                responseDto.setSuccess(false);
                responseDto.setMessage("出现时间格式错误");
                return responseDto;
            }
            Map<String,String> map = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBSNCENTERCODE);
            ForecastNumDto forecastNumDto = new ForecastNumDto();
            forecastNumDto.setSbbh(sbbh);
            forecastNumDto.setCxcs(cxcs);
            forecastNumDto.setCxsj(cxsj);
            forecastNumDto.setCxrq(cxsj.substring(0,10));
            forecastNumDto.setDepcode(map.get(sbbh));
            forecastNumService.save(forecastNumDto);
        }catch (Exception e){
            LOG.error("接收到的数据："+JSONObject.toJSONString(jsonObject)+"====错误原因："+e.getMessage());
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }
}
