package com.pd.monitor.controller;

import com.pd.server.main.domain.*;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.CurrentMeterService;
import com.pd.server.main.service.MeteorologicalDataService;
import com.pd.server.main.service.TurbidityService;
import com.pd.server.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/getMeteorologicalTodayData/{sbbh}")
    public ResponseDto getMeteorologicalTodayData(@PathVariable String sbbh){
        ResponseDto responseDto = new ResponseDto();
        MeteorologicalDataExample example = new MeteorologicalDataExample();
        MeteorologicalDataExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(sbbh)){
            ca.andBzEqualTo(sbbh);
        }
        ca.andCjsjEqualTo(DateUtils.getDateToStrFormat(new Date(),"yyyy-MM-dd"),"%Y-%m-%d");
        example.setOrderByClause(" cjsj desc ");
        List<MeteorologicalData> lists = meteorologicalDataService.selectByExample(example);
        responseDto.setContent(lists);
        return responseDto;
    }

    @PostMapping("/getTurbidityTodayData/{sbbh}")
    public ResponseDto getTurbidityTodayData(@PathVariable String sbbh){
        ResponseDto responseDto = new ResponseDto();
        TurbidityExample turbidityExample = new TurbidityExample();
        TurbidityExample.Criteria ca = turbidityExample.createCriteria();
        if(!StringUtils.isEmpty(sbbh)){
            ca.andBzEqualTo(sbbh);
        }
        ca.andDateTimeEqualTo(DateUtils.getDateToStrFormat(new Date(),"yyyy-MM-dd"),"%Y-%m-%d");
        turbidityExample.setOrderByClause(" date_time desc ");
        List<Turbidity> lists = turbidityService.selectByExample(turbidityExample);
        responseDto.setContent(lists);
        return responseDto;
    }

    @PostMapping("/getCurrentMeterTodayData/{sbbh}")
    public ResponseDto getCurrentMeterTodayData(@PathVariable String sbbh){
        ResponseDto responseDto = new ResponseDto();
        CurrentMeter currentMeter = new CurrentMeter();
        CurrentMeterExample currentMeterExample = new CurrentMeterExample();
        CurrentMeterExample.Criteria ca = currentMeterExample.createCriteria();
        if(!StringUtils.isEmpty(sbbh)){
            ca.andBzEqualTo(sbbh);
        }
        ca.andCjsjEqualTo(DateUtils.getDateToStrFormat(new Date(),"yyyy-MM-dd"),"%Y-%m-%d");
        currentMeterExample.setOrderByClause(" cjsj desc ");
        List<CurrentMeter> currentMeterList = currentMeterService.selectByExample(currentMeterExample);
        if(currentMeterList.size()>0){
            currentMeter = currentMeterList.get(0);
        }
        responseDto.setContent(currentMeter);
        return responseDto;
    }
}
