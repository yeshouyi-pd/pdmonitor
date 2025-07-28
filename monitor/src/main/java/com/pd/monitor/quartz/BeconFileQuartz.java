package com.pd.monitor.quartz;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.basewx.my.NoTodaySbbhDto;
import com.pd.server.main.service.BeconFileService;
import com.pd.server.main.service.BeconFileStatisticsService;
import com.pd.server.main.service.BeconFileTodayService;
import com.pd.server.main.service.BeconFileTyTodayService;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class BeconFileQuartz {

    @Resource
    private BeconFileService beconFileService;
    @Resource
    private BeconFileTodayService beconFileTodayService;
    @Resource
    private BeconFileStatisticsService beconFileStatisticsService;
    @Resource
    private BeconFileTyTodayService beconFileTyTodayService;
    @Resource
    private RedisTemplate redisTemplate;

    //@Scheduled(cron = "0 38 16 * * ? ")
    @Scheduled(cron = "0 30 02 * * ? ")
    public void loop() throws ParseException {
        String beforeDayStr = DateTools.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd");
        //不是今天的数据
        BeconFileTodayExample example = new BeconFileTodayExample();
        BeconFileTodayExample.Criteria ca = example.createCriteria();
        ca.andRqLessThanOrEqualTo(beforeDayStr);
        List<NoTodaySbbhDto> noTodaySbbhDtoList = beconFileTodayService.noTodaySbbhDtoList(example);
        noTodaySbbhDtoList(noTodaySbbhDtoList);
    }

    public void noTodaySbbhDtoList(List<NoTodaySbbhDto> list){
        for(NoTodaySbbhDto noTodaySbbhDto : list){
            BeconFileStatisticsExample statisticsExample = new BeconFileStatisticsExample();
            BeconFileStatisticsExample.Criteria statisticsCa = statisticsExample.createCriteria();
            statisticsCa.andRqEqualTo(noTodaySbbhDto.getRq());
            statisticsCa.andXbidEqualTo(noTodaySbbhDto.getSbbh());
            beconFileStatisticsService.deleteByExample(statisticsExample);
            BeconFileExample beconFileExample = new BeconFileExample();
            BeconFileExample.Criteria beconFileCa = beconFileExample.createCriteria();
            beconFileCa.andRqEqualTo(noTodaySbbhDto.getRq());
            beconFileCa.andXbidEqualTo(noTodaySbbhDto.getSbbh());
            List<BeconFileStatistics> statisticsList = beconFileService.statisticsByExample(beconFileExample);
            Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTemplate.opsForValue().get(RedisCode.SBBHEQUIPMAP);
            for(BeconFileStatistics entity : statisticsList){
                String sbmcs = "";
                String gps = "";
                if(!StringUtils.isEmpty(entity.getSbbhs())){
                    String[] sbbharr = entity.getSbbhs().split(",");
                    for(String sbbh : sbbharr){
                        WaterEquipment waterEquipment = JSONObject.toJavaObject(sbbhEquipMap.get("equip-"+sbbh), WaterEquipment.class);
                        sbmcs = sbmcs + waterEquipment.getSbmc() + ",";
                        gps = gps + waterEquipment.getGps() + ";";
                    }
                }
                if(sbmcs.length()!=0){
                    entity.setSbmcs(sbmcs.substring(0,sbmcs.length()-1));
                    entity.setGps(gps.substring(0,gps.length()-1));
                }
                beconFileStatisticsService.insert(entity);
            }
        }
        clearNoTodayData();
    }

    //清除数据
    public void clearNoTodayData(){
        /**
         * 只保存当天数据，清除当天之前的数据
         */
        BeconFileTodayExample todayExample = new BeconFileTodayExample();
        todayExample.createCriteria().andRqLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        beconFileTodayService.deleteByExample(todayExample);
        BeconFileTyTodayExample tyTodayExample = new BeconFileTyTodayExample();
        tyTodayExample.createCriteria().andRqLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        beconFileTyTodayService.deleteByExample(tyTodayExample);
    }
}
