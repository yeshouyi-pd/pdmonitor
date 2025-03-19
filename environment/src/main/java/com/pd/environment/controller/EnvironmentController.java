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
                if(!StringUtils.isEmpty(jsonObject.get("air_temperature"))){
                    meteorologicalDataDto.setTemperature(jsonObject.getDouble("air_temperature"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("relative_humidity"))){
                    meteorologicalDataDto.setHumidity(jsonObject.getDouble("relative_humidity"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("air_pressure"))){
                    meteorologicalDataDto.setPressure(jsonObject.getDouble("air_pressure"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("short_wave"))){
                    meteorologicalDataDto.setShortwave(jsonObject.getDouble("short_wave"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("long_wave"))){
                    meteorologicalDataDto.setLongwave(jsonObject.getDouble("long_wave"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("uwind_speed"))){
                    meteorologicalDataDto.setUwindSpeed(jsonObject.getDouble("uwind_speed"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("vwind_speed"))){
                    meteorologicalDataDto.setVwindSpeed(jsonObject.getDouble("vwind_speed"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("jcsj"))){
                    meteorologicalDataDto.setCjsj(jsonObject.getDate("jcsj"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("sbbh"))){
                    meteorologicalDataDto.setBz(jsonObject.getString("sbbh"));
                }
                meteorologicalDataService.save(meteorologicalDataDto);
                TurbidityDto turbidityDto = new TurbidityDto();
                if(!StringUtils.isEmpty(jsonObject.get("temperature"))){
                    turbidityDto.setTemperature(jsonObject.getDouble("temperature").toString());
                }
                if(!StringUtils.isEmpty(jsonObject.get("salinity"))){
                    turbidityDto.setSalinity(jsonObject.getDouble("salinity").toString());
                }
                if(!StringUtils.isEmpty(jsonObject.get("turbidity"))){
                    turbidityDto.setTuribidityL(jsonObject.getString("turbidity"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("jcsj"))){
                    turbidityDto.setDateTime(jsonObject.getDate("jcsj"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("sbbh"))){
                    turbidityDto.setBz(jsonObject.getString("sbbh"));
                }
                turbidityService.save(turbidityDto);
                CurrentMeterDto currentMeterDto = new CurrentMeterDto();
                if(!StringUtils.isEmpty(jsonObject.get("u"))){
                    currentMeterDto.setUspeed(jsonObject.getDouble("u"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("v"))){
                    currentMeterDto.setVspeed(jsonObject.getDouble("v"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("zeta"))){
                    currentMeterDto.setZetaData(jsonObject.getDouble("zeta"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("jcsj"))){
                    currentMeterDto.setCjsj(jsonObject.getDate("jcsj"));
                }
                if(!StringUtils.isEmpty(jsonObject.get("sbbh"))){
                    currentMeterDto.setBz(jsonObject.getString("sbbh"));
                }
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
