package com.pd.monitor.quartz;

import com.pd.server.main.domain.*;
import com.pd.server.main.dto.AppearNumbersDto;
import com.pd.server.main.dto.EquipmentFileAlarmEventDto;
import com.pd.server.main.dto.EventCameraInfoDto;
import com.pd.server.main.dto.PredationNumDto;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.service.*;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import com.pd.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class StatisticsDataQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(StatisticsDataQuartz.class);

    @Resource
    private EquipmentFileTodayService equipmentFileTodayService;
    @Resource
    private EquipmentFileTyTodayService equipmentFileTyTodayService;
    @Resource
    private AppearNumbersService appearNumbersService;
    @Resource
    private EquipmentFileAlarmEventService equipmentFileAlarmEventService;
    @Resource
    private CameraInfoService cameraInfoService;
    @Resource
    private EventCameraInfoService eventCameraInfoService;
    @Resource
    private PredationNumService predationNumService;
    @Resource
    private WaterEquipmentService waterEquipmentService;

    //每天凌晨30分执行
    //1.出现次数统计(appear_numbers)
    //2.出现事件统计(equipment_file_alarm_event)
    //3.捕食次数统计(predation_num)
    //4.清除EquipmentFileToday表中昨天的数据
    @Scheduled(cron = "0 30 0 * * ? ")
    public void loop() throws ParseException {
        //出现次数统计(appearNumbers)
        String beforeDayStr = DateTools.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd");
        EquipmentFileTodayExample example = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria ca = example.createCriteria();
        //ca.andRqEqualTo(beforeDayStr);
        ca.andTxtlxEqualTo("1");
        List<AlarmNumbersDto> lists = equipmentFileTodayService.statisticsAlarmNums(example);
        for(AlarmNumbersDto item : lists){
            AppearNumbersDto dto = CopyUtil.copy(item,AppearNumbersDto.class);
            dto.setBjsj(item.getFz().substring(0,10));
            appearNumbersService.save(dto);
        }
        //出现事件统计，捕食次数统计
        //为什么捕食次数统计要放在出现事件统计完以后进行，因为捕食次数统计中包含了事件次数，需要从出现事件统计中获取
        alarmEvent(lists,example);
    }

    //出现事件统计(equipment_file_alarm_event)，然后在进行捕食次数统计(predation_num表中包含事件次数)
    public void alarmEvent(List<AlarmNumbersDto> lists,EquipmentFileTodayExample example){
        //String beforeDayStr = DateTools.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd");
        Map<String, List<AlarmNumbersDto>> mapList = lists.stream().collect(Collectors.groupingBy(AlarmNumbersDto::getSbbh));
        WaterEquipmentExample exampleTemp = new WaterEquipmentExample();
        exampleTemp.createCriteria().andDqzlEqualTo("A4");
        List<String> sbbha4 = waterEquipmentService.findSbbh(exampleTemp);
        for(String key : mapList.keySet()){
            List<AlarmNumbersDto> listsTemp = mapList.get(key).stream().sorted(Comparator.comparing(AlarmNumbersDto::getFz)).collect(Collectors.toList());
            List<AlarmNumbersDto> resultList = new ArrayList<>();
            if(!CollectionUtils.isEmpty(listsTemp)){
                AlarmNumbersDto firstEntity = listsTemp.get(0);
                String curDateStr = firstEntity.getFz();
                //String lastDateStr = laterThreeMinute(curDateStr);
                Integer bjsl = firstEntity.getAlarmNum();
                for(int i=1;i<listsTemp.size();i++){
                    AlarmNumbersDto entity = listsTemp.get(i);
                    AlarmNumbersDto beforeEntity = listsTemp.get(i-1);
                    String beforeDateStr = beforeEntity.getFz();
                    String nextDateStr = entity.getFz();
                    if(entity.getSbbh().equals(firstEntity.getSbbh())){
                        if(isOverThreeMinute(beforeDateStr, nextDateStr)){
                            bjsl = bjsl + entity.getAlarmNum();
                        }else {
                            AlarmNumbersDto result = new AlarmNumbersDto();
                            result.setDeptcode(entity.getDeptcode());
                            result.setSbbh(entity.getSbbh());
                            result.setBjsj(curDateStr+" 至 "+beforeDateStr);
                            result.setAlarmNum(bjsl);
                            resultList.add(result);
                            firstEntity = entity;
                            curDateStr = firstEntity.getFz();
                            bjsl = firstEntity.getAlarmNum();
                        }
                    }else {
                        AlarmNumbersDto result = new AlarmNumbersDto();
                        result.setDeptcode(firstEntity.getDeptcode());
                        result.setSbbh(firstEntity.getSbbh());
                        result.setBjsj(curDateStr+" 至 "+beforeDateStr);
                        result.setAlarmNum(bjsl);
                        resultList.add(result);
                        firstEntity = entity;
                        curDateStr = firstEntity.getFz();
                        bjsl = firstEntity.getAlarmNum();
                    }
                    if(i==listsTemp.size()-1){
                        AlarmNumbersDto result = new AlarmNumbersDto();
                        result.setDeptcode(entity.getDeptcode());
                        result.setSbbh(entity.getSbbh());
                        result.setBjsj(curDateStr+" 至 "+nextDateStr);
                        result.setAlarmNum(bjsl);
                        resultList.add(result);
                    }
                }
                if(listsTemp.size()==1){
                    AlarmNumbersDto result = new AlarmNumbersDto();
                    result.setDeptcode(firstEntity.getDeptcode());
                    result.setSbbh(firstEntity.getSbbh());
                    result.setBjsj(curDateStr+" 至 "+curDateStr);
                    result.setAlarmNum(bjsl);
                    resultList.add(result);
                }
            }
            for(int i=0;i<resultList.size();i++){
                AlarmNumbersDto alarmNumbersDto = resultList.get(i);
                EquipmentFileAlarmEventDto entity = new EquipmentFileAlarmEventDto();
                entity.setId(UuidUtil.getShortUuid());
                entity.setDeptcode(alarmNumbersDto.getDeptcode());
                entity.setSbbh(alarmNumbersDto.getSbbh());
                entity.setEventTime(alarmNumbersDto.getBjsj());
                entity.setAlarmNum(alarmNumbersDto.getAlarmNum());
                entity.setBjsj(alarmNumbersDto.getBjsj().substring(0,10));
                entity.setXh(i+1);
                String[] arr = entity.getEventTime().split(" 至 ");
                String kssj = arr[0]+":00";
                String jssj = arr[1]+":00";
                if(sbbha4.contains(key)){
                    //统计头数
                    EquipmentFileTodayExample tsExample = new EquipmentFileTodayExample();
                    EquipmentFileTodayExample.Criteria tsCa = tsExample.createCriteria();
                    tsCa.andSbbhEqualTo(key);
                    tsCa.andTxtlxEqualTo("4");
                    tsCa.andFzGreaterThanOrEqualTo(arr[0]);
                    tsCa.andFzLessThanOrEqualTo(arr[1]);
                    Integer tsCount = equipmentFileTodayService.countTsByExample(tsExample);
                    if(!StringUtils.isEmpty(tsCount)){
                        entity.setSm1(String.valueOf(tsCount));
                    }
                }
                equipmentFileAlarmEventService.insert(entity);
                //将信息写入事件相机表中，以便于做切片
                //saveEventCameraInfo(entity, key, kssj, jssj);
            }
        }
        //捕食次数统计
        predationStatistics(example, sbbha4);
    }

    public void predationStatistics(EquipmentFileTodayExample example, List<String> sbbha4){
        //String beforeDayStr = DateTools.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd");
        List<AlarmNumbersDto> allList = equipmentFileTodayService.groupByRq(example);
        for(AlarmNumbersDto alarmNumbersDto : allList){
            //捕食次数
            EquipmentFileTodayExample example1 = new EquipmentFileTodayExample();
            EquipmentFileTodayExample.Criteria ca1 = example1.createCriteria();
            ca1.andRqEqualTo(alarmNumbersDto.getBjsj());
            ca1.andJczlEqualTo("1");
            ca1.andSbbhEqualTo(alarmNumbersDto.getSbbh());
            List<EquipmentFileToday> predationList = equipmentFileTodayService.listAll(example1);
            PredationNumDto dto = new PredationNumDto();
            dto.setPredationNum(predationList.size());
            dto.setAlarmNum(alarmNumbersDto.getAlarmNum());
            dto.setCjsj(DateUtil.toDate(alarmNumbersDto.getBjsj(),"yyyy-MM-dd"));
            dto.setSbbh(alarmNumbersDto.getSbbh());
            if(!StringUtils.isEmpty(alarmNumbersDto.getDeptcode())){
                dto.setDeptcode(alarmNumbersDto.getDeptcode());
            }
            EquipmentFileAlarmEventExample eventExample = new EquipmentFileAlarmEventExample();
            EquipmentFileAlarmEventExample.Criteria eventCa = eventExample.createCriteria();
            eventCa.andSbbhEqualTo(alarmNumbersDto.getSbbh());
            eventCa.andBjsjEqualTo(alarmNumbersDto.getBjsj());
            long alarmCount = equipmentFileAlarmEventService.alarmCount(eventExample);
            dto.setSm1(StringUtils.isEmpty(alarmCount)?"0":Long.toString(alarmCount));
            if(sbbha4.contains(alarmNumbersDto.getSbbh())){
                Integer tsCount = equipmentFileAlarmEventService.countTsByExample(eventExample);
                dto.setSm2(StringUtils.isEmpty(tsCount)?"0":String.valueOf(tsCount));
            }
            predationNumService.save(dto);
        }
        //clearTodayData();
    }

    public void clearTodayData(){
        //清除数据
        /**
         * EquipmentFileToday,EquipmentFileTyToday只保存当天数据，凌晨一点清除前一天的数据
         */
        EquipmentFileTodayExample todayExample = new EquipmentFileTodayExample();
        todayExample.createCriteria().andRqLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        List<EquipmentFileToday> list = equipmentFileTodayService.listAll(todayExample);
        for(EquipmentFileToday entity : list){
            equipmentFileTodayService.delete(entity.getId());
        }
        EquipmentFileTyTodayExample tyExample = new EquipmentFileTyTodayExample();
        tyExample.createCriteria().andRqLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        List<EquipmentFileTyToday> tyTodayList = equipmentFileTyTodayService.selectByExample(tyExample);
        for(EquipmentFileTyToday entity : tyTodayList){
            equipmentFileTyTodayService.delete(entity.getId());
        }
    }

//    public void saveEventCameraInfo(EquipmentFileAlarmEventDto entity, String key, String kssj, String jssj){
//        if(kssj.equals(jssj)){
//            jssj = DateUtil.getFormatDate(DateUtil.getMinutesLater(DateUtil.toDate(jssj,"yyyy-MM-dd HH:mm:ss"),1),"yyyy-MM-dd HH:mm:ss");
//        }
//        //通过设备编号查询相机信息
//        List<CameraInfo> list = cameraInfoService.findBySbbh(key);
//        if(!CollectionUtils.isEmpty(list)){
//            for (CameraInfo cameraInfo : list){
//                EventCameraInfoDto info = new EventCameraInfoDto();
//                info.setDvrip(cameraInfo.getDvrip());
//                info.setTdh(cameraInfo.getSbdk());
//                info.setSxtbh(cameraInfo.getSm2());
//                info.setKssj(DateUtil.toDate(kssj,"yyyy-MM-dd HH:mm:ss"));
//                info.setJssj(DateUtil.toDate(jssj,"yyyy-MM-dd HH:mm:ss"));
//                info.setSjid(entity.getId());
//                info.setSbsn(entity.getSbbh());
//                info.setDeptcode(entity.getDeptcode());
//                eventCameraInfoService.save(info);
//            }
//        }else {
//            LOG.error("设备编号:"+key+",没有查询到对于的相机信息");
//        }
//    }

    public static Boolean isOverThreeMinute(String curDateStr, String nextDateStr){
        Date begin = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date end = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        long minute=(end.getTime()-begin.getTime())/(1000*60);//除以1000是为了转换成秒
        if(minute<=2){
            return true;
        }
        return false;
    }


    @Scheduled(cron = "0 47 14 * * ? ")
    public void test() throws ParseException {
        loop();
    }
}
