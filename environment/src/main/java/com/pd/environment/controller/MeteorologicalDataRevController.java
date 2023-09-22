package com.pd.environment.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.dto.MeteorologicalDataDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.MeteorologicalDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;

@RestController
@RequestMapping("/meteorologicalData")
public class MeteorologicalDataRevController {

    private static final Logger LOG = LoggerFactory.getLogger(MeteorologicalDataRevController.class);
    public static final String BUSINESS_NAME = "接收气象数据";

    @Resource
    private MeteorologicalDataService meteorologicalDataService;

    /**
     * 保存数据
     */
    @PostMapping("/getData")
    public ResponseDto getData(@RequestBody JSONObject jsonObject) {
        ResponseDto responseDto = new ResponseDto();
        try {
            MeteorologicalDataDto meteorologicalDataDto = new MeteorologicalDataDto();
            if(jsonObject.get("speed")!=null){
                meteorologicalDataDto.setSpeed((Double)jsonObject.get("speed")==5.877471754111438E-39?0.0:formatDouble((Double)jsonObject.get("speed")));
            }
            if(jsonObject.get("winddirection")!=null){
                meteorologicalDataDto.setWinddirection((Double)jsonObject.get("winddirection")==5.877471754111438E-39?0.0:formatDouble((Double)jsonObject.get("winddirection")));
            }
            if(jsonObject.get("temperature")!=null){
                meteorologicalDataDto.setTemperature((Double)jsonObject.get("temperature")==5.877471754111438E-39?0.0:formatDouble((Double)jsonObject.get("temperature")));
            }
            if(jsonObject.get("humidity")!=null){
                meteorologicalDataDto.setHumidity((Double)jsonObject.get("humidity")==5.877471754111438E-39?0.0:formatDouble((Double)jsonObject.get("humidity")));
            }
            if(jsonObject.get("pressure")!=null){
                meteorologicalDataDto.setPressure((Double)jsonObject.get("pressure")==5.877471754111438E-39?0.0:formatDouble((Double)jsonObject.get("pressure")));
            }
            if(jsonObject.get("minuterainfall")!=null){
                meteorologicalDataDto.setMinuterainfall((Double)jsonObject.get("minuterainfall")==5.877471754111438E-39?0.0:formatDouble((Double)jsonObject.get("minuterainfall")));
            }
            if(jsonObject.get("hourrainfall")!=null){
                meteorologicalDataDto.setHourrainfall((Double)jsonObject.get("hourrainfall")==5.877471754111438E-39?0.0:formatDouble((Double)jsonObject.get("hourrainfall")));
            }
            if(jsonObject.get("dayrainfall")!=null){
                meteorologicalDataDto.setDayrainfall((Double)jsonObject.get("dayrainfall")==5.877471754111438E-39?0.0:formatDouble((Double)jsonObject.get("dayrainfall")));
            }
            if(jsonObject.get("rainfallaccumulation")!=null){
                meteorologicalDataDto.setRainfallaccumulation((Double)jsonObject.get("rainfallaccumulation")==5.877471754111438E-39?0.0:formatDouble((Double)jsonObject.get("rainfallaccumulation")));
            }
            if(jsonObject.get("solarintensity")!=null){
                meteorologicalDataDto.setSolarintensity((Double)jsonObject.get("solarintensity")==5.877471754111438E-39?0.0:formatDouble((Double)jsonObject.get("solarintensity")));
            }
            meteorologicalDataDto.setCjsj(new Date());
            meteorologicalDataDto.setBz("RPCDA4016");
            meteorologicalDataService.save(meteorologicalDataDto);
        }catch (Exception e){
            LOG.error("接收到的数据："+JSONObject.toJSONString(jsonObject)+"====错误原因："+e.getMessage());
        }
        return responseDto;
    }

    public static double formatDouble(double d){
        DecimalFormat sf = new DecimalFormat("0.000");
        return Double.valueOf(sf.format(d));
    }
}
