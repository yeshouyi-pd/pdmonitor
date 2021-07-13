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
        String beforeDateStr = DateTools.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd");
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        ca.andCjsjGreaterThanOrEqualTo(beforeDateStr+" 00:00");
        ca.andCjsjLessThanOrEqualTo(beforeDateStr+" 23:59");
        ca.andTpljLike("%png");
        List<AlarmNumbersDto> lists = equipmentFileService.statisticsAlarmNumsByPage(example);
        List<AlarmNumbersDto> resultList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(lists)){
            AlarmNumbersDto firstEntity = lists.get(0);
            String curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
            String lastDateStr = laterThreeMinute(curDateStr);
            Integer bjsl = firstEntity.getAlarmNum();
            for(int i=1;i<lists.size();i++){
                AlarmNumbersDto entity = lists.get(i);
                String nextDateStr = entity.getBjsj()+" "+entity.getXs()+":"+entity.getFz();
                if(entity.getSbbh().equals(firstEntity.getSbbh())){
                    if(isBetween(curDateStr, nextDateStr, lastDateStr)){
                        bjsl = bjsl + entity.getAlarmNum();
                    }else {
                        AlarmNumbersDto result = new AlarmNumbersDto();
                        result.setDeptcode(entity.getDeptcode());
                        result.setSbbh(entity.getSbbh());
                        result.setBjsj(curDateStr+" 至 "+lastDateStr);
                        result.setAlarmNum(bjsl);
                        resultList.add(result);
                        firstEntity = entity;
                        curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
                        lastDateStr = laterThreeMinute(curDateStr);
                        bjsl = firstEntity.getAlarmNum();
                    }
                }else {
                    AlarmNumbersDto result = new AlarmNumbersDto();
                    result.setDeptcode(firstEntity.getDeptcode());
                    result.setSbbh(firstEntity.getSbbh());
                    result.setBjsj(curDateStr+" 至 "+lastDateStr);
                    result.setAlarmNum(bjsl);
                    resultList.add(result);
                    firstEntity = entity;
                    curDateStr = firstEntity.getBjsj()+" "+firstEntity.getXs()+":"+firstEntity.getFz();
                    lastDateStr = laterThreeMinute(curDateStr);
                    bjsl = firstEntity.getAlarmNum();
                }
                if(i==lists.size()-1){
                    AlarmNumbersDto result = new AlarmNumbersDto();
                    result.setDeptcode(entity.getDeptcode());
                    result.setSbbh(entity.getSbbh());
                    result.setBjsj(curDateStr+" 至 "+lastDateStr);
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
            entity.setBjsj(beforeDateStr);
            entity.setXh(i+1);
            equipmentFileAlarmEventService.save(entity);
        }
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1            被除数
     * @param v2            除数
     * @param scale         表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public double div(int v1, int v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Integer.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public String laterThreeMinute(String curDateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(curDateStr));
        calendar.add(Calendar.MINUTE, 2);// 3分钟之后的时间
        Date beforeD = calendar.getTime();
        String time = sdf.format(beforeD);
        return time;
    }

    public Boolean isBetween(String curDateStr, String nextDateStr, String lastDateStr){
        Date curDate = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date nextDate = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        Date lastDate = DateUtil.toDate(lastDateStr,"yyyy-MM-dd HH:mm");
        if(curDate.getTime()<=nextDate.getTime()&&nextDate.getTime()<=lastDate.getTime()){
            return true;
        }
        return false;
    }
}
