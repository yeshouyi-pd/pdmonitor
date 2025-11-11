package com.pd.environment.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.MeteorologicalDataDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.MeteorologicalDataService;
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
@RequestMapping("/meteorological")
public class MeteorologicalHttpController {


    private static final Logger LOG = LoggerFactory.getLogger(MeteorologicalDataRevController.class);
    public static final String BUSINESS_NAME = "接收第三方气象数据";

    @Resource
    private MeteorologicalDataService meteorologicalDataService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 保存数据
     */
    @PostMapping("/saveData")
    public ResponseDto saveData(@RequestBody JSONObject jsonObject) {
        ResponseDto responseDto = new ResponseDto();
        try {
            if(StringUtils.isEmpty(jsonObject.get("speed")) || StringUtils.isEmpty(jsonObject.get("winddirection"))
                    || StringUtils.isEmpty(jsonObject.get("temperature")) || StringUtils.isEmpty(jsonObject.get("humidity"))
                    || StringUtils.isEmpty(jsonObject.get("pressure")) || StringUtils.isEmpty(jsonObject.get("sbbh"))
                    || StringUtils.isEmpty(jsonObject.get("jcsj"))){
                responseDto.setSuccess(false);
                responseDto.setMessage("参数错误");
                return responseDto;
            }
            String jcsj = jsonObject.getString("jcsj");
            String isFlag = "^(\\d{4})-(\\d{2})-(\\d{2})\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";//判断时间
            if(!jcsj.matches(isFlag)){
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
            MeteorologicalDataDto dto = new MeteorologicalDataDto();
            dto.setSpeed(jsonObject.getDouble("speed"));
            dto.setWinddirection(jsonObject.getDouble("winddirection").toString());
            dto.setTemperature(jsonObject.getDouble("temperature"));
            dto.setHumidity(jsonObject.getDouble("humidity"));
            dto.setPressure(jsonObject.getDouble("pressure"));
            if(!StringUtils.isEmpty(jsonObject.get("minuterainfall"))){
                dto.setMinuterainfall(jsonObject.getDouble("minuterainfall"));
            }
            if(!StringUtils.isEmpty(jsonObject.get("hourrainfall"))){
                dto.setHourrainfall(jsonObject.getDouble("hourrainfall"));
            }
            if(!StringUtils.isEmpty(jsonObject.get("dayrainfall"))){
                dto.setDayrainfall(jsonObject.getDouble("dayrainfall"));
            }
            if(!StringUtils.isEmpty(jsonObject.get("rainfallaccumulation"))){
                dto.setRainfallaccumulation(jsonObject.getDouble("rainfallaccumulation"));
            }
            if(!StringUtils.isEmpty(jsonObject.get("solarintensity"))){
                dto.setSolarintensity(jsonObject.getDouble("solarintensity"));
            }
            dto.setBz(jsonObject.getString("sbbh"));
            dto.setCjsj(jsonObject.getDate("jcsj"));
            if(jsonObject.get("winddirection")!=null){
                Double fx = (Double)jsonObject.get("winddirection");
                if((fx>=5.877471754111438E-39&&fx<11.25) || (fx>=348.75&&fx<=360)){
                    dto.setSm("N");
                }else if(fx>=11.25&&fx<33.75){
                    dto.setSm("NNE");
                }else if(fx>=33.75&&fx<56.25){
                    dto.setSm("NE");
                }else if(fx>=56.25&&fx<78.75){
                    dto.setSm("ENE");
                }else if(fx>=78.75&&fx<101.25){
                    dto.setSm("E");
                }else if(fx>=101.25&&fx<123.75){
                    dto.setSm("ESE");
                }else if(fx>=123.75&&fx<146.25){
                    dto.setSm("SE");
                }else if(fx>=146.25&&fx<168.75){
                    dto.setSm("SSE");
                }else if(fx>=168.75&&fx<191.25){
                    dto.setSm("S");
                }else if(fx>=191.25&&fx<213.75){
                    dto.setSm("SSW");
                }else if(fx>=213.75&&fx<236.25){
                    dto.setSm("SW");
                }else if(fx>=236.25&&fx<258.75){
                    dto.setSm("WSW");
                }else if(fx>=258.75&&fx<281.25){
                    dto.setSm("W");
                }else if(fx>=281.25&&fx<303.75){
                    dto.setSm("WNW");
                }else if(fx>=303.75&&fx<326.25){
                    dto.setSm("NW");
                }else if(fx>=326.25&&fx<348.75){
                    dto.setSm("NNW");
                }
            }
            meteorologicalDataService.save(dto);
            responseDto.setSuccess(true);
            responseDto.setMessage("保存成功");
        }catch (Exception e){
            if(e instanceof ClassCastException){
                responseDto.setMessage("数据转换错误，请检查数据类型是否正确");
                responseDto.setSuccess(false);
            }else{
                LOG.error("接收到的数据："+JSONObject.toJSONString(jsonObject)+"====错误原因："+e.getMessage());
                responseDto.setMessage(e.getMessage());
                responseDto.setSuccess(false);
            }
        }
        return responseDto;
    }

}
