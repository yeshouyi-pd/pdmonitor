package com.pd.environment.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.MeteorologicalData;
import com.pd.server.main.dto.CurrentMeterDto;
import com.pd.server.main.dto.MeteorologicalDataDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.TurbidityDto;
import com.pd.server.main.service.CurrentMeterService;
import com.pd.server.main.service.MeteorologicalDataService;
import com.pd.server.main.service.TurbidityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/environment")
public class EnvironmentController {

    private static final Logger LOG = LoggerFactory.getLogger(CurrentMeterRevController.class);
    public static final String BUSINESS_NAME = "接收环境数据";


    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private TurbidityService turbidityService;//浊度仪
    @Resource
    private MeteorologicalDataService meteorologicalDataService;//气象数据
    @Resource
    private CurrentMeterService currentMeterService;//海流计

    /**
     * 保存数据
     */
    @PostMapping("/saveData")
    public ResponseDto saveData(@RequestBody JSONObject jsonObj) {
        ResponseDto responseDto = new ResponseDto();
        try {
            if(jsonObj.isEmpty()){
                responseDto.setSuccess(false);
                responseDto.setMessage("无效数据");
                return responseDto;
            }
            JSONArray jsonArray = jsonObj.getJSONArray("data");
            for(int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MeteorologicalDataDto meteorologicalDataDto = new MeteorologicalDataDto();
                meteorologicalDataDto.setTemperature(jsonObject.getDouble("air_temperature"));
                meteorologicalDataDto.setHumidity(jsonObject.getDouble("relative_humidity"));
                meteorologicalDataDto.setPressure(jsonObject.getDouble("air_pressure"));
                meteorologicalDataDto.setShortwave(jsonObject.getDouble("short_wave"));
                meteorologicalDataDto.setLongwave(jsonObject.getDouble("long_wave"));
                meteorologicalDataDto.setUwindSpeed(jsonObject.getDouble("uwind_speed"));
                meteorologicalDataDto.setVwindSpeed(jsonObject.getDouble("vwind_speed"));
                meteorologicalDataDto.setCjsj(jsonObject.getDate("jcsj"));
                meteorologicalDataDto.setBz(jsonObject.getString("sbbh"));
                meteorologicalDataService.save(meteorologicalDataDto);
                TurbidityDto turbidityDto = new TurbidityDto();
                turbidityDto.setTemperature(jsonObject.getDouble("temperature").toString());
                turbidityDto.setSalinity(jsonObject.getDouble("salinity").toString());
                turbidityDto.setDateTime(jsonObject.getDate("jcsj"));
                turbidityDto.setBz(jsonObject.getString("sbbh"));
                turbidityService.save(turbidityDto);
                CurrentMeterDto currentMeterDto = new CurrentMeterDto();
                currentMeterDto.setUspeed(jsonObject.getDouble("u"));
                currentMeterDto.setVspeed(jsonObject.getDouble("v"));
                currentMeterDto.setZetaData(jsonObject.getDouble("zeta"));
                currentMeterDto.setCjsj(jsonObject.getDate("jcsj"));
                currentMeterDto.setBz(jsonObject.getString("sbbh"));
                currentMeterService.save(currentMeterDto);
            }
        }catch (Exception e){
            LOG.error("接收到的数据："+JSONObject.toJSONString(jsonObj)+"====错误原因："+e.getMessage());
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }

}
