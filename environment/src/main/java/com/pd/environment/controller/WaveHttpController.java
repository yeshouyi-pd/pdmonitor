package com.pd.environment.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WaveDataDto;
import com.pd.server.main.service.WaveDataService;
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
@RequestMapping("/wave")
public class WaveHttpController {

    private static final Logger LOG = LoggerFactory.getLogger(WaveHttpController.class);
    public static final String BUSINESS_NAME = "接收第三方波浪数据";

    @Resource
    private WaveDataService waveDataService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 保存数据
     */
    @PostMapping("/saveData")
    public ResponseDto saveData(@RequestBody JSONObject jsonObject) {
        ResponseDto responseDto = new ResponseDto();
        try {
            if(jsonObject.isEmpty()){
                responseDto.setSuccess(false);
                responseDto.setMessage("无效数据");
                return responseDto;
            }
            if(StringUtils.isEmpty(jsonObject.get("sbbh")) || StringUtils.isEmpty(jsonObject.get("cjsj")) ){
                responseDto.setSuccess(false);
                responseDto.setMessage("参数错误");
                return responseDto;
            }
            if(StringUtils.isEmpty(jsonObject.get("waveH")) && StringUtils.isEmpty(jsonObject.get("waveDirection"))
                    && StringUtils.isEmpty(jsonObject.get("wavePeriod")) ){
                responseDto.setSuccess(false);
                responseDto.setMessage("无效数据");
                return responseDto;
            }
            String cjsj = jsonObject.getString("cjsj");
            String isFlag = "^(\\d{4})-(\\d{2})-(\\d{2})\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";//判断时间
            if(!cjsj.matches(isFlag)){
                responseDto.setSuccess(false);
                responseDto.setMessage("时间格式错误");
                return responseDto;
            }
            Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTemplate.opsForValue().get(RedisCode.SBBHEQUIPMAP);
            if(!sbbhEquipMap.keySet().contains("equip-"+jsonObject.getString("sbbh"))){
                responseDto.setSuccess(false);
                responseDto.setMessage("设备编号错误");
                return responseDto;
            }
            WaveDataDto dto = new WaveDataDto();
            dto.setCjsj(jsonObject.getDate("cjsj"));
            dto.setSbbh(jsonObject.getString("sbbh"));
            dto.setWaveH(jsonObject.getString("waveH"));
            dto.setWaveDirection(jsonObject.getString("waveDirection"));
            dto.setWavePeriod(jsonObject.getString("wavePeriod"));
            waveDataService.save(dto);
            responseDto.setSuccess(true);
            responseDto.setMessage("保存成功");
        }catch (Exception e){
            LOG.error("接收到的数据："+JSONObject.toJSONString(jsonObject)+"====错误原因："+e.getMessage());
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }
}
