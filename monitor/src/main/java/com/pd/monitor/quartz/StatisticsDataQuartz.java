package com.pd.monitor.quartz;

import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.AppearNumbersDto;
import com.pd.server.main.dto.EquipmentFileAlarmEventDto;
import com.pd.server.main.dto.EventCameraInfoDto;
import com.pd.server.main.dto.PredationNumDto;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.dto.basewx.my.NoTodaySbbhDto;
import com.pd.server.main.dto.basewx.my.PredationStaticticsDto;
import com.pd.server.main.service.*;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import com.pd.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
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
    private PredationNumService predationNumService;
    @Resource
    private WaterEquipmentService waterEquipmentService;
    @Resource
    private DeviceStateLogService deviceStateLogService;
    @Resource
    private EquipmentFilePPicService equipmentFilePPicService;
    @Resource
    private EquipmentFilePClusterService equipmentFilePClusterService;
    @Resource
    private RedisTemplate redisTemplate;

    //每天凌晨30分执行
    //1.出现次数统计(appear_numbers)
    //2.出现事件统计(equipment_file_alarm_event)
    //3.捕食次数统计(predation_num)
    //4.清除EquipmentFileToday表中昨天的数据
    @Scheduled(cron = "0 30 0 * * ? ")
    public void loop() throws ParseException {
        LOG.error("统计开始时间："+DateUtil.getFormatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        String beforeDayStr = DateTools.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd");
        //不是今天的数据
        EquipmentFileTodayExample noTodayExample = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria noTodayCa = noTodayExample.createCriteria();
        noTodayCa.andRqLessThanOrEqualTo(beforeDayStr);
        noTodayCa.andTxtlxEqualTo("1");
        List<NoTodaySbbhDto> noTodaySbbhDtoList = equipmentFileTodayService.noTodaySbbhDtoList(noTodayExample);
        noTodaySbbhDtoList(noTodaySbbhDtoList);
        //将之前的设备状态改为正常
        changeDeviceStateLog(noTodaySbbhDtoList);
        LOG.error("统计结束时间："+DateUtil.getFormatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }

    public void changeDeviceStateLog(List<NoTodaySbbhDto> noTodaySbbhDtoList){
        for(NoTodaySbbhDto entity : noTodaySbbhDtoList){
            deviceStateLogService.updateZt(entity.getRq(), entity.getSbbh());
        }
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
        list=null;
        EquipmentFileTyTodayExample tyExample = new EquipmentFileTyTodayExample();
        tyExample.createCriteria().andRqLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        List<EquipmentFileTyToday> tyTodayList = equipmentFileTyTodayService.selectByExample(tyExample);
        for(EquipmentFileTyToday entity : tyTodayList){
            equipmentFileTyTodayService.delete(entity.getId());
        }
        tyTodayList=null;
    }

    public void noTodaySbbhDtoList(List<NoTodaySbbhDto> list){
        WaterEquipmentExample exampleTemp = new WaterEquipmentExample();
        exampleTemp.createCriteria().andDqzlEqualTo("A4");
        List<String> sbbha4 = waterEquipmentService.findSbbh(exampleTemp);
        //设备编号对应的部门编号
        Map<String,String> deptcodeSbbhMap = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBSNCENTERCODE);
        for(NoTodaySbbhDto noTodaySbbhDto : list){
            AppearNumbersExample appearDelExample = new AppearNumbersExample();
            AppearNumbersExample.Criteria appearDelCa = appearDelExample.createCriteria();
            appearDelCa.andSbbhEqualTo(noTodaySbbhDto.getSbbh());
            appearDelCa.andBjsjEqualTo(noTodaySbbhDto.getRq());
            appearNumbersService.deleteByExample(appearDelExample);
            EquipmentFileAlarmEventExample alarmEventDelExample = new EquipmentFileAlarmEventExample();
            EquipmentFileAlarmEventExample.Criteria alarmEventDelCa = alarmEventDelExample.createCriteria();
            alarmEventDelCa.andSbbhEqualTo(noTodaySbbhDto.getSbbh());
            alarmEventDelCa.andBjsjEqualTo(noTodaySbbhDto.getRq());
            equipmentFileAlarmEventService.deleteByExample(alarmEventDelExample);
            PredationNumExample predationDelExample = new PredationNumExample();
            PredationNumExample.Criteria predationDelCa = predationDelExample.createCriteria();
            predationDelCa.andSbbhEqualTo(noTodaySbbhDto.getSbbh());
            predationDelCa.andCjsjEqualTo(noTodaySbbhDto.getRq()+" 00:00:00");
            predationNumService.deleteByExample(predationDelExample);
            //出现次数统计(appearNumbers)
            EquipmentFilePPicExample example = new EquipmentFilePPicExample();
            EquipmentFilePPicExample.Criteria ca = example.createCriteria();
            ca.andSbbhEqualTo(noTodaySbbhDto.getSbbh());
            ca.andFzGreaterThanOrEqualTo(noTodaySbbhDto.getRq()+" 00:00");
            ca.andFzLessThanOrEqualTo(noTodaySbbhDto.getRq()+" 23:59");
            List<AlarmNumbersDto> lists = equipmentFilePPicService.statisticsAlarmNumsByMinute(example);
            for(AlarmNumbersDto item : lists){
                AppearNumbersDto dto = CopyUtil.copy(item,AppearNumbersDto.class);
                dto.setBjsj(item.getFz().substring(0,10));
                dto.setXs(item.getFz().substring(0,13));
                dto.setDeptcode(deptcodeSbbhMap.get(item.getSbbh()));
                appearNumbersService.save(dto);
            }
            List<AlarmNumbersDto> listsTemp = lists.stream().sorted(Comparator.comparing(AlarmNumbersDto::getFz)).collect(Collectors.toList());
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
                if(sbbha4.contains(noTodaySbbhDto.getSbbh())){
                    //统计头数
                    EquipmentFilePClusterExample pClusterExample = new EquipmentFilePClusterExample();
                    EquipmentFilePClusterExample.Criteria pClusterCa = pClusterExample.createCriteria();
                    pClusterCa.andSbbhEqualTo(noTodaySbbhDto.getSbbh());
                    pClusterCa.andFzGreaterThanOrEqualTo(arr[0]);
                    pClusterCa.andFzLessThanOrEqualTo(arr[1]);
                    Integer tsCount = equipmentFilePClusterService.countTsByExample(pClusterExample);
                    if(!StringUtils.isEmpty(tsCount)){
                        entity.setSm1(String.valueOf(tsCount));
                    }
                    tsCount = null;
                }
                equipmentFileAlarmEventService.insert(entity);
                kssj = null;
                jssj = null;
            }
            lists = null;
            listsTemp = null;
            resultList = null;
            // 用完后置为null，帮助GC
            lists = null;
            listsTemp = null;
            resultList = null;
            //捕食次数
            EquipmentFilePPicExample pPicExample = new EquipmentFilePPicExample();
            EquipmentFilePPicExample.Criteria ca1 = pPicExample.createCriteria();
            ca1.andRqEqualTo(noTodaySbbhDto.getRq());
            ca1.andSbbhEqualTo(noTodaySbbhDto.getSbbh());
            PredationStaticticsDto predationStaticticsDto = equipmentFilePPicService.predationStatictics(pPicExample);
            PredationNumDto dto = new PredationNumDto();
            dto.setPredationNum(predationStaticticsDto.getBscs());
            dto.setAlarmNum(predationStaticticsDto.getCxcs());
            dto.setDeptcode(deptcodeSbbhMap.get(noTodaySbbhDto.getSbbh()));
            dto.setCjsj(DateUtil.toDate(noTodaySbbhDto.getRq(),"yyyy-MM-dd"));
            dto.setSbbh(noTodaySbbhDto.getSbbh());
            EquipmentFileAlarmEventExample eventExample = new EquipmentFileAlarmEventExample();
            EquipmentFileAlarmEventExample.Criteria eventCa = eventExample.createCriteria();
            eventCa.andSbbhEqualTo(noTodaySbbhDto.getSbbh());
            eventCa.andBjsjEqualTo(noTodaySbbhDto.getRq());
            long alarmCount = equipmentFileAlarmEventService.alarmCount(eventExample);
            dto.setSm1(StringUtils.isEmpty(alarmCount)?"0":Long.toString(alarmCount));
            if(sbbha4.contains(noTodaySbbhDto.getSbbh())){
                Integer tsCount = equipmentFileAlarmEventService.countTsByExample(eventExample);
                dto.setSm2(StringUtils.isEmpty(tsCount)?"0":String.valueOf(tsCount));
            }
            predationNumService.save(dto);
            predationStaticticsDto = null;
            dto = null;
        }
        clearTodayData();
        list = null;
        sbbha4 = null;
        // 用完后置为null，帮助GC
        list = null;
        sbbha4 = null;
    }

    public static Boolean isOverThreeMinute(String curDateStr, String nextDateStr){
        Date begin = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date end = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        long minute=(end.getTime()-begin.getTime())/(1000*60);//除以1000是为了转换成秒
        if(minute<=2){
            return true;
        }
        return false;
    }

}
