package com.pd.monitor.controller;

import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.AppearNumDpDto;
import com.pd.server.main.service.*;
import com.pd.server.util.DateUtil;
import com.pd.server.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/environmentDp")
public class EnvironmentDpController {

    private static final Logger LOG = LoggerFactory.getLogger(EnvironmentDpController.class);
    public static final String BUSINESS_NAME = "环境大屏数据";

    @Resource
    private CurrentMeterService currentMeterService;
    @Resource
    private TurbidityService turbidityService;
    @Resource
    private MeteorologicalDataService meteorologicalDataService;
    @Resource
    private WaterQualityNewService waterQualityNewService;
    @Resource
    private WaveDataService waveDataService;
    @Resource
    private VideoEventService videoEventService;
    @Resource
    private AttrService attrService;
    @Resource
    private AppearNumbersService appearNumbersService;
    @Resource
    private ForecastNumService forecastNumService;
    @Resource
    private SeaSurfaceSalinityService seaSurfaceSalinityService;


    @GetMapping("/getSeaSurfacePic")
    public ResponseDto getSeaSurfacePic(){
        ResponseDto responseDto = new ResponseDto();
        SeaSurfaceSalinity seaSurfaceSalinity = seaSurfaceSalinityService.selectLastOne();
        responseDto.setContent(seaSurfaceSalinity);
        return responseDto;
    }

    @PostMapping("/getHourCxcsBySbbh")
    public ResponseDto getHourCxcsBySbbh(@RequestBody AppearNumbersDto appearNumbersDto){
        ResponseDto responseDto = new ResponseDto();
        AppearNumbersExample example = new AppearNumbersExample();
        AppearNumbersExample.Criteria ca = example.createCriteria();
        ca.andSbbhEqualTo(appearNumbersDto.getSbbh());
        if(!StringUtils.isEmpty(appearNumbersDto.getStime())){
            ca.andBjsjGreaterThanOrEqualTo(appearNumbersDto.getStime());
        }else{
            ca.andBjsjGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-4),"yyyy-MM-dd"));
        }
        if(!StringUtils.isEmpty(appearNumbersDto.getEtime())){
            ca.andBjsjLessThanOrEqualTo(appearNumbersDto.getEtime());
        }else {
            ca.andBjsjLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        }
        List<AppearNumDpDto> lists = appearNumbersService.listSumAlarmNumByXs(example);
        ForecastNumExample forecastNumExample = new ForecastNumExample();
        ForecastNumExample.Criteria forecastNumCa = forecastNumExample.createCriteria();
        forecastNumCa.andSbbhEqualTo(appearNumbersDto.getSbbh());
        forecastNumCa.andCxrqGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),1),"yyyy-MM-dd"));
        forecastNumExample.setOrderByClause(" cxsj desc ");
        List<ForecastNum> listForecast = forecastNumService.selectByExample(forecastNumExample);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("history", lists);
        resultMap.put("forecast", listForecast);
        responseDto.setContent(resultMap);
        return responseDto;
    }

    @PostMapping("/getVideoEvent")
    public ResponseDto getVideoEvent(@RequestBody VideoEventDto videoEventDto){
        ResponseDto responseDto = new ResponseDto();
        VideoEventExample example = new VideoEventExample();
        VideoEventExample.Criteria ca = example.createCriteria();
        ca.andSfyspEqualTo(0);
        ca.andRqEqualTo(DateUtils.getDateToStrFormat(new Date(),"yyyy-MM-dd"));
        example.setOrderByClause(" kssj desc ");
        List<VideoEvent> lists = videoEventService.selectByExample(example);
        if(lists.size()<=0){
            VideoEventExample limitExample = new VideoEventExample();
            VideoEventExample.Criteria limitCa = limitExample.createCriteria();
            limitCa.andSfyspEqualTo(0);
            limitExample.setOrderByClause(" kssj desc ");
            String limitNumStr = attrService.findByAttrKey("limitNum");
            Integer limitNum = 10;
            if(!org.springframework.util.StringUtils.isEmpty(limitNumStr)){
                limitNum = Integer.parseInt(limitNumStr);
            }
            List<VideoEvent> listslimit = videoEventService.selectByDp(limitExample, limitNum);
            responseDto.setContent(listslimit);
            return responseDto;
        }
        responseDto.setContent(lists);
        return responseDto;
    }

    @PostMapping("/getWaveDataData")
    public ResponseDto getWaveDataData(@RequestBody WaveDataDto waveDataDto){
        ResponseDto responseDto = new ResponseDto();
        WaveDataExample example = new WaveDataExample();
        WaveDataExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(waveDataDto.getSbbh())){
            ca.andSbbhEqualTo(waveDataDto.getSbbh());
        }
        if(!StringUtils.isEmpty(waveDataDto.getStime())){
            ca.andCjsjEqualTo(waveDataDto.getStime(),"%Y-%m-%d");
        }else{
            ca.andCjsjEqualTo(DateUtils.getDateToStrFormat(new Date(),"yyyy-MM-dd"),"%Y-%m-%d");
        }
        example.setOrderByClause(" cjsj ");
        List<WaveData> lists = waveDataService.selectByExample(example);
        responseDto.setContent(lists);
        return responseDto;
    }

    @PostMapping("/getWaterQualityNewData")
    public ResponseDto getWaterQualityNewData(@RequestBody WaterQualityNewDto waterQualityNewDto){
        ResponseDto responseDto = new ResponseDto();
        WaterQualityNewExample example = new WaterQualityNewExample();
        WaterQualityNewExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(waterQualityNewDto.getSbbh())){
            ca.andSbbhEqualTo(waterQualityNewDto.getSbbh());
        }
        if(!StringUtils.isEmpty(waterQualityNewDto.getStime())){
            ca.andCjsjEqualTo(waterQualityNewDto.getStime(),"%Y-%m-%d");
        }else{
            ca.andCjsjEqualTo(DateUtils.getDateToStrFormat(new Date(),"yyyy-MM-dd"),"%Y-%m-%d");
        }
        example.setOrderByClause(" cjsj ");
        List<WaterQualityNew> lists = waterQualityNewService.selectByExample(example);
        responseDto.setContent(lists);
        return responseDto;
    }

    @PostMapping("/getMeteorologicalData")
    public ResponseDto getMeteorologicalData(@RequestBody MeteorologicalDataDto meteorologicalDataDto){
        ResponseDto responseDto = new ResponseDto();
        MeteorologicalDataExample example = new MeteorologicalDataExample();
        MeteorologicalDataExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(meteorologicalDataDto.getBz())){
            ca.andBzEqualTo(meteorologicalDataDto.getBz());
        }
        if(!StringUtils.isEmpty(meteorologicalDataDto.getStime())){
            ca.andCjsjEqualTo(meteorologicalDataDto.getStime(),"%Y-%m-%d");
        }else{
            ca.andCjsjEqualTo(DateUtils.getDateToStrFormat(new Date(),"yyyy-MM-dd"),"%Y-%m-%d");
        }
        example.setOrderByClause(" cjsj ");
        List<MeteorologicalData> lists = meteorologicalDataService.selectByExample(example);
        responseDto.setContent(lists);
        return responseDto;
    }

    @PostMapping("/getTurbidityData")
    public ResponseDto getTurbidityData(@RequestBody TurbidityDto turbidityDto){
        ResponseDto responseDto = new ResponseDto();
        TurbidityExample turbidityExample = new TurbidityExample();
        TurbidityExample.Criteria ca = turbidityExample.createCriteria();
        if(!StringUtils.isEmpty(turbidityDto.getBz())){
            ca.andBzEqualTo(turbidityDto.getBz());
        }
        if(!StringUtils.isEmpty(turbidityDto.getStime())){
            ca.andDateTimeEqualTo(turbidityDto.getStime(),"%Y-%m-%d");
        }else{
            ca.andDateTimeEqualTo(DateUtils.getDateToStrFormat(new Date(),"yyyy-MM-dd"),"%Y-%m-%d");
        }
        turbidityExample.setOrderByClause(" date_time ");
        List<Turbidity> lists = turbidityService.selectByExample(turbidityExample);
        responseDto.setContent(lists);
        return responseDto;
    }

    @PostMapping("/getCurrentMeterData")
    public ResponseDto getCurrentMeterData(@RequestBody CurrentMeterDto currentMeterDto){
        ResponseDto responseDto = new ResponseDto();
        CurrentMeter currentMeter = new CurrentMeter();
        CurrentMeterExample currentMeterExample = new CurrentMeterExample();
        CurrentMeterExample.Criteria ca = currentMeterExample.createCriteria();
        if(!StringUtils.isEmpty(currentMeterDto.getBz())){
            ca.andBzEqualTo(currentMeterDto.getBz());
        }
        if(!StringUtils.isEmpty(currentMeterDto.getStime())){
            ca.andCjsjEqualTo(currentMeterDto.getStime(),"%Y-%m-%d");
        }else{
            ca.andCjsjEqualTo(DateUtils.getDateToStrFormat(new Date(),"yyyy-MM-dd"),"%Y-%m-%d");
        }
        currentMeterExample.setOrderByClause(" cjsj desc ");
        List<CurrentMeter> currentMeterList = currentMeterService.selectByExample(currentMeterExample);
        if(currentMeterList.size()>0){
            currentMeter = currentMeterList.get(0);
        }
        responseDto.setContent(currentMeter);
        return responseDto;
    }
}
