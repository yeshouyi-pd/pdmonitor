package com.pd.monitor.quartz;

import com.pd.monitor.utils.SendSmsTool;
import com.pd.server.main.domain.EquipmentFileEvent;
import com.pd.server.main.domain.EquipmentFileEventExample;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.EquipmentFileTodayExample;
import com.pd.server.main.dto.basewx.my.SmsIntDto;
import com.pd.server.main.service.EquipmentFileEventService;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.main.service.EquipmentFileTodayService;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Component
@EnableScheduling
public class SendSmsQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(DataInvalidQuartz.class);

    @Resource
    private EquipmentFileTodayService equipmentFileTodayService;
    @Resource
    private EquipmentFileEventService equipmentFileEventService;
    @Resource
    private EquipmentFileService equipmentFileService;


    /**
     * 每1小时执行一次
     * @throws Exception
     */
    @Scheduled(cron="0 0 8-19 * * ? ")
    public void sendSmsCount(){
        String lasthour = DateUtil.getFormatDate(DateUtil.getHoursLater(new Date(),-1),"yyyy-MM-dd HH:mm")+":00";
        String nowhour = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd HH:mm")+":00";
        EquipmentFileTodayExample todayExample = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria todayCa = todayExample.createCriteria();
        todayCa.andSbbhIn(Arrays.asList("A4001","A4002","A4003"));
        todayCa.andTxtlxEqualTo("1");
        todayCa.andCjsjGreaterThanOrEqualTo(lasthour);
        todayCa.andCjsjLessThanOrEqualTo(nowhour);
        List<SmsIntDto> list = equipmentFileTodayService.sendSmsQuery(todayExample);
        EquipmentFileEventExample equipmentFileEventExample = new EquipmentFileEventExample();
        EquipmentFileEventExample.Criteria eventCa = equipmentFileEventExample.createCriteria();
        eventCa.andKssjGreaterThanOrEqualTo(lasthour);
        eventCa.andKssjLessThanOrEqualTo(nowhour);
        eventCa.andSbbhEqualTo("A4002");
        int allTs = equipmentFileEventService.selectTsBySms(equipmentFileEventExample);
        Map<String,Integer> sbbhBjcs = new HashMap<>();
        for (SmsIntDto entity : list){
            sbbhBjcs.put(entity.getSbbh(),entity.getBjcs());
        }
        String a1 = !StringUtils.isEmpty(sbbhBjcs.get("A4001"))?sbbhBjcs.get("A4001")+"":0+"";
        String a2 = !StringUtils.isEmpty(sbbhBjcs.get("A4002"))?sbbhBjcs.get("A4002")+"":0+"";
        String a3 = !StringUtils.isEmpty(sbbhBjcs.get("A4003"))?sbbhBjcs.get("A4003")+"":0+"";
        SendSmsTool.sendSms("1841258",a1+";"+a2+";"+a3+"-"+allTs);
    }

    /**
     * 每天8点执行
     * @throws Exception
     */
    @Scheduled(cron="0 0 8 * * ? ")
    public void sendSmsYesterday(){
        String lastday = DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd");
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        ca.andSbbhIn(Arrays.asList("A4001","A4002","A4003"));
        ca.andTxtlxEqualTo("1");
        ca.andRqEqualTo(lastday);
        List<SmsIntDto> list = equipmentFileService.sendSmsQuery(example);
        EquipmentFileEventExample equipmentFileEventExample = new EquipmentFileEventExample();
        EquipmentFileEventExample.Criteria eventCa = equipmentFileEventExample.createCriteria();
        eventCa.andRqEqualTo(lastday);
        eventCa.andSbbhEqualTo("A4002");
        int allTs = equipmentFileEventService.selectTsBySms(equipmentFileEventExample);
        Map<String,Integer> sbbhBjcs = new HashMap<>();
        for (SmsIntDto entity : list){
            sbbhBjcs.put(entity.getSbbh(),entity.getBjcs());
        }
        String a1 = !StringUtils.isEmpty(sbbhBjcs.get("A4001"))?sbbhBjcs.get("A4001")+"":0+"";
        String a2 = !StringUtils.isEmpty(sbbhBjcs.get("A4002"))?sbbhBjcs.get("A4002")+"":0+"";
        String a3 = !StringUtils.isEmpty(sbbhBjcs.get("A4003"))?sbbhBjcs.get("A4003")+"":0+"";
        SendSmsTool.sendSms("1841261",a1+";"+a2+";"+a3+"-"+allTs);
    }

//    @Scheduled(cron="0 0/1 * * * ? ")
//    public void test(){
//        sendSmsCount();
//        sendSmsYesterday();
//    }
}
