package com.pd.monitor.controller;

import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.*;
import com.pd.server.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
