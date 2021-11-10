package com.pd.monitor.wx.conf;

import com.pd.server.main.domain.EquipmentFileAlarmEvent;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.dto.EquipmentFileAlarmEventDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.service.EquipmentFileAlarmEventService;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import com.pd.server.util.DateUtils;
import com.pd.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class AlarmEventLoop extends BaseWxController {
    private static final Logger LOG = LoggerFactory.getLogger(AlarmEventLoop.class);
    @Resource
    private EquipmentFileService equipmentFileService;
    @Resource
    private EquipmentFileAlarmEventService equipmentFileAlarmEventService;

    @Scheduled(cron = "0 0 1 * * ? ")
    public void loop() throws ParseException {
        String beforeDayStr = DateTools.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd");
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        ca.andCjsjGreaterThanOrEqualTo(beforeDayStr+" 00:00");
        ca.andCjsjLessThanOrEqualTo(beforeDayStr+" 23:59");
        ca.andTpljLike("%png");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNumsByPage(example);
        List<AlarmNumbersDto> resultList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(lists)){
            AlarmNumbersDto firstEntity = lists.get(0);
            String curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
            //String lastDateStr = laterThreeMinute(curDateStr);
            Integer bjsl = firstEntity.getAlarmNum();
            for(int i=1;i<lists.size();i++){
                AlarmNumbersDto entity = lists.get(i);
                AlarmNumbersDto beforeEntity = lists.get(i-1);
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
                if(i==lists.size()-1){
                    AlarmNumbersDto result = new AlarmNumbersDto();
                    result.setDeptcode(entity.getDeptcode());
                    result.setSbbh(entity.getSbbh());
                    result.setBjsj(curDateStr+" 至 "+nextDateStr);
                    result.setAlarmNum(bjsl);
                    resultList.add(result);
                }
            }
        }
        for(int i=0;i<resultList.size();i++){
            AlarmNumbersDto alarmNumbersDto = resultList.get(i);
            EquipmentFileAlarmEventDto entity = new EquipmentFileAlarmEventDto();
            entity.setDeptcode(alarmNumbersDto.getDeptcode());
            entity.setSbbh(alarmNumbersDto.getSbbh());
            entity.setEventTime(alarmNumbersDto.getBjsj());
            entity.setAlarmNum(alarmNumbersDto.getAlarmNum());
            entity.setBjsj(beforeDayStr);
            entity.setXh(i+1);
            equipmentFileAlarmEventService.save(entity);
        }
    }

    public Boolean isOverThreeMinute(String curDateStr, String nextDateStr){
        Date begin = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date end = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        long minute=(end.getTime()-begin.getTime())/(1000*60);//除以1000是为了转换成秒
        if(minute<=2){
            return true;
        }
        return false;
    }
}
