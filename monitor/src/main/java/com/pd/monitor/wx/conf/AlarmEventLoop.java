package com.pd.monitor.wx.conf;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.EquipmentFileAlarmEventDto;
import com.pd.server.main.dto.EventCameraInfoDto;
import com.pd.server.main.dto.PredationNumDto;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.mapper.EquipmentFileMapper;
import com.pd.server.main.service.*;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import com.pd.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AlarmEventLoop extends BaseWxController {
    private static final Logger LOG = LoggerFactory.getLogger(AlarmEventLoop.class);
    @Resource
    private EquipmentFileService equipmentFileService;
    @Resource
    private EquipmentFileAlarmEventService equipmentFileAlarmEventService;
    @Resource
    private AttrService attrService;
    @Resource
    private RedisTemplate  redisTemplate;
    @Resource
    private EquipmentFileMapper equipmentFileMapper;
    @Resource
    private PredationNumService predationNumService;
    @Resource
    private CameraInfoService cameraInfoService;
    @Resource
    private EventCameraInfoService eventCameraInfoService;

    /**
     * 统计昨天鲸豚事件,及鲸豚捕食次数
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    public void loop() throws ParseException {
        String beforeDayStr = DateTools.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd");
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        ca.andRqEqualTo(beforeDayStr);
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNums(example);
        Map<String, List<AlarmNumbersDto>> mapList = lists.stream().collect(Collectors.groupingBy(AlarmNumbersDto::getSbbh));
        for(String key : mapList.keySet()){
            List<AlarmNumbersDto> listsTemp = mapList.get(key);
            List<AlarmNumbersDto> resultList = new ArrayList<>();
            if(!CollectionUtils.isEmpty(listsTemp)){
                AlarmNumbersDto firstEntity = listsTemp.get(0);
                String curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
                //String lastDateStr = laterThreeMinute(curDateStr);
                Integer bjsl = firstEntity.getAlarmNum();
                for(int i=1;i<listsTemp.size();i++){
                    AlarmNumbersDto entity = listsTemp.get(i);
                    AlarmNumbersDto beforeEntity = listsTemp.get(i-1);
                    String beforeDateStr = beforeEntity.getBjsj()+" "+beforeEntity.getXs()+":"+beforeEntity.getFz();
                    String nextDateStr = entity.getBjsj()+" "+entity.getXs()+":"+entity.getFz();
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
                            curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
                            //lastDateStr = laterThreeMinute(curDateStr);
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
                        curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
                        //lastDateStr = laterThreeMinute(curDateStr);
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
                entity.setBjsj(beforeDayStr);
                entity.setXh(i+1);
                equipmentFileAlarmEventService.insert(entity);
                //将信息写入事件相机表中，以便于做切片
                saveEventCameraInfo(entity, key);
            }
        }
        List<AlarmNumbersDto> allList = equipmentFileService.groupByRq(example);
        for(AlarmNumbersDto alarmNumbersDto : allList){
            //捕食次数
            EquipmentFileExample example1 = new EquipmentFileExample();
            EquipmentFileExample.Criteria ca1 = example1.createCriteria();
            ca1.andRqEqualTo(beforeDayStr);
            ca1.andTpljLike("%predation%");
            ca1.andTpljLike("%txt%");
            if(!StringUtils.isEmpty(alarmNumbersDto.getDeptcode())){
                ca1.andDeptcodeEqualTo(alarmNumbersDto.getDeptcode());
            }
            ca1.andSbbhEqualTo(alarmNumbersDto.getSbbh());
            List<EquipmentFile> predationList = equipmentFileService.listAll(example1);
            PredationNumDto dto = new PredationNumDto();
            dto.setPredationNum(predationList.size());
            dto.setAlarmNum(alarmNumbersDto.getAlarmNum());
            dto.setCjsj(DateUtil.toDate(beforeDayStr,"yyyy-MM-dd"));
            dto.setSbbh(alarmNumbersDto.getSbbh());
            if(!StringUtils.isEmpty(alarmNumbersDto.getDeptcode())){
                dto.setDeptcode(alarmNumbersDto.getDeptcode());
            }
            EquipmentFileAlarmEventExample eventExample = new EquipmentFileAlarmEventExample();
            EquipmentFileAlarmEventExample.Criteria eventCa = eventExample.createCriteria();
            eventCa.andSbbhEqualTo(alarmNumbersDto.getSbbh());
            eventCa.andBjsjEqualTo(beforeDayStr);
            long alarmCount = equipmentFileAlarmEventService.alarmCount(eventExample);
            dto.setSm1(StringUtils.isEmpty(alarmCount)?"0":Long.toString(alarmCount));
            predationNumService.save(dto);
        }

    }

    public void saveEventCameraInfo(EquipmentFileAlarmEventDto entity, String key){
        String[] arr = entity.getEventTime().split(" 至 ");
        String kssj = arr[0]+":00";
        String jssj = arr[1]+":00";
        if(kssj.equals(jssj)){
            jssj = DateUtil.getFormatDate(DateUtil.getMinutesLater(DateUtil.toDate(jssj,"yyyy-MM-dd HH:mm:ss"),1),"yyyy-MM-dd HH:mm:ss");
        }
        //通过设备编号查询相机信息
        List<CameraInfo> list = cameraInfoService.findBySbbh(key);
        if(!CollectionUtils.isEmpty(list)){
            for (CameraInfo cameraInfo : list){
                EventCameraInfoDto info = new EventCameraInfoDto();
                info.setIp(cameraInfo.getIp());
                info.setPort(cameraInfo.getPort());
                info.setTdh(cameraInfo.getSbdk());
                info.setZh(cameraInfo.getUsername());
                info.setMm(cameraInfo.getCamerapws());
                info.setKssj(DateUtil.toDate(kssj,"yyyy-MM-dd HH:mm:ss"));
                info.setJssj(DateUtil.toDate(jssj,"yyyy-MM-dd HH:mm:ss"));
                info.setSjid(entity.getId());
                info.setSbsn(entity.getSbbh());
                info.setDeptcode(entity.getDeptcode());
                eventCameraInfoService.save(info);
            }
        }else {
            LOG.error("设备编号:"+key+",没有查询到对于的相机信息");
        }
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

    /**
     * 将缓存中存储的数据写入数据库，然后再做统计
     */
    @Scheduled(cron = "0 05 0 * * ? ")
    public void loopTemplate(){
        String predationsbsn = attrService.findByAttrKey("predationsbsn");
        String[] arr = predationsbsn.split(",");
        System.out.println("arr"+arr);
        for(String sbbh : arr){
            if(!StringUtils.isEmpty(redisTemplate.opsForValue().get(sbbh))){
                String entityJson = (String) redisTemplate.opsForValue().get(sbbh);
                System.out.println("entityJson"+entityJson);
                EquipmentFile entity = JSONObject.parseObject(entityJson, EquipmentFile.class);
                EquipmentFile lastFile = equipmentFileService.selectLastOneBySbbh(sbbh);
                if(!StringUtils.isEmpty(entity.getCjsj())&&!StringUtils.isEmpty(lastFile.getCjsj())&&isOverMinute(DateUtil.getFormatDate(lastFile.getCjsj(),"yyyy-MM-dd HH:mm:ss"),DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd HH:mm:ss"))){
                    equipmentFileMapper.insert(entity);
                    redisTemplate.delete(sbbh);
                }
            }
        }
    }

    public  Boolean isOverMinute(String curDateStr, String nextDateStr){
        Date begin = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date end = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        long minute=(end.getTime()-begin.getTime())/(1000*60);//除以1000是为了转换成秒
        String predationInterval = attrService.findByAttrKey("predationInterval");
        if(minute<=Integer.parseInt(predationInterval)){
            return true;
        }
        return false;
    }
}
