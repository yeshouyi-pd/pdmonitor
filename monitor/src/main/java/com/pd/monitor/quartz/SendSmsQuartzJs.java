package com.pd.monitor.quartz;

import com.pd.monitor.utils.SendSmsTool;
import com.pd.monitor.wx.conf.WxRedisConfig;
import com.pd.server.main.domain.EquipmentFilePPicExample;
import com.pd.server.main.domain.EquipmentFileTodayExample;
import com.pd.server.main.dto.basewx.my.SmsIntDto;
import com.pd.server.main.service.EquipmentFilePPicService;
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
public class SendSmsQuartzJs {

    private static final Logger LOG = LoggerFactory.getLogger(DataInvalidQuartz.class);

    @Resource
    private EquipmentFileTodayService equipmentFileTodayService;
    @Resource
    private EquipmentFilePPicService equipmentFilePPicService;


    /**
     * 早上8点执行（昨天晚上8点到今日8点）一次
     * @throws Exception
     */
    @Scheduled(cron="0 0 8 * * ? ")
    public void sendSmsCount(){
        Map<String, String> attrMap = WxRedisConfig.getAttrMap();
        String phoneNum = attrMap.get("phoneNumberJs");
        String lasthour = DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd ")+"20:00:00";
        String nowhour = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd ")+"08:00:00";
        EquipmentFilePPicExample example = new EquipmentFilePPicExample();
        EquipmentFilePPicExample.Criteria ca = example.createCriteria();
        ca.andSbbhIn(Arrays.asList("JSA4001","JSA4002"));
        ca.andCjsjGreaterThanOrEqualTo(lasthour);
        ca.andCjsjLessThan(nowhour);
        List<SmsIntDto> list = equipmentFilePPicService.sendSmsQuery(example);
        Map<String,Integer> sbbhBjcs = new HashMap<>();
        for (SmsIntDto entity : list){
            sbbhBjcs.put(entity.getSbbh(),entity.getBjcs());
        }
        String a1 = !StringUtils.isEmpty(sbbhBjcs.get("JSA4001"))?sbbhBjcs.get("JSA4001")+"":0+"";
        String a2 = !StringUtils.isEmpty(sbbhBjcs.get("JSA4002"))?sbbhBjcs.get("JSA4002")+"":0+"";
        SendSmsTool.sendSms("2277017","君山农业局01、君山农业局02"+"-"+"昨日20点至今日8点"+"-"+";"+a1+";"+a2, phoneNum);
        lasthour = null;
        nowhour = null;
        phoneNum = null;
        a1 = null;
        a2 = null;
        list = null;
        sbbhBjcs = null;
    }

    /**
     * 早上11点30分执行（今日8点到11点30分）一次
     * @throws Exception
     */
    @Scheduled(cron="0 30 11 * * ? ")
    public void sendSmsCount1(){
        Map<String, String> attrMap = WxRedisConfig.getAttrMap();
        String phoneNum = attrMap.get("phoneNumberJs");
        String lasthour = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd ")+"08:00:00";
        String nowhour = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd ")+"11:30:00";
        EquipmentFileTodayExample todayExample = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria todayCa = todayExample.createCriteria();
        todayCa.andSbbhIn(Arrays.asList("JSA4001","JSA4002"));
        todayCa.andTxtlxEqualTo("1");
        todayCa.andCjsjGreaterThanOrEqualTo(lasthour);
        todayCa.andCjsjLessThan(nowhour);
        List<SmsIntDto> list = equipmentFileTodayService.sendSmsQuery(todayExample);
        Map<String,Integer> sbbhBjcs = new HashMap<>();
        for (SmsIntDto entity : list){
            sbbhBjcs.put(entity.getSbbh(),entity.getBjcs());
        }
        String a1 = !StringUtils.isEmpty(sbbhBjcs.get("JSA4001"))?sbbhBjcs.get("JSA4001")+"":0+"";
        String a2 = !StringUtils.isEmpty(sbbhBjcs.get("JSA4002"))?sbbhBjcs.get("JSA4002")+"":0+"";
        SendSmsTool.sendSms("2277017","君山农业局01、君山农业局02"+"-"+"今日8点至11点30分"+"-"+";"+a1+";"+a2, phoneNum);
        lasthour = null;
        nowhour = null;
        phoneNum = null;
        a1 = null;
        a2 = null;
        list = null;
        sbbhBjcs = null;
    }

    /**
     * 17点30分执行（11点30分到17点30分）一次
     * @throws Exception
     */
    @Scheduled(cron="0 30 17 * * ? ")
    public void sendSmsCount2(){
        Map<String, String> attrMap = WxRedisConfig.getAttrMap();
        String phoneNum = attrMap.get("phoneNumberJs");
        String lasthour = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd ")+"11:30:00";
        String nowhour = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd ")+"17:30:00";
        EquipmentFileTodayExample todayExample = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria todayCa = todayExample.createCriteria();
        todayCa.andSbbhIn(Arrays.asList("JSA4001","JSA4002"));
        todayCa.andTxtlxEqualTo("1");
        todayCa.andCjsjGreaterThanOrEqualTo(lasthour);
        todayCa.andCjsjLessThan(nowhour);
        List<SmsIntDto> list = equipmentFileTodayService.sendSmsQuery(todayExample);
        Map<String,Integer> sbbhBjcs = new HashMap<>();
        for (SmsIntDto entity : list){
            sbbhBjcs.put(entity.getSbbh(),entity.getBjcs());
        }
        String a1 = !StringUtils.isEmpty(sbbhBjcs.get("JSA4001"))?sbbhBjcs.get("JSA4001")+"":0+"";
        String a2 = !StringUtils.isEmpty(sbbhBjcs.get("JSA4002"))?sbbhBjcs.get("JSA4002")+"":0+"";
        SendSmsTool.sendSms("2277017","君山农业局01、君山农业局02"+"-"+"今日11点30分至17点30分"+"-"+";"+a1+";"+a2, phoneNum);
        lasthour = null;
        nowhour = null;
        phoneNum = null;
        a1 = null;
        a2 = null;
        list = null;
        sbbhBjcs = null;
    }

    /**
     * 20点执行（17点30分到20点）一次
     * @throws Exception
     */
    @Scheduled(cron="0 0 20 * * ? ")
    public void sendSmsCount3(){
        Map<String, String> attrMap = WxRedisConfig.getAttrMap();
        String phoneNum = attrMap.get("phoneNumberJs");
        String lasthour = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd ")+"17:30:00";
        String nowhour = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd ")+"20:00:00";
        EquipmentFileTodayExample todayExample = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria todayCa = todayExample.createCriteria();
        todayCa.andSbbhIn(Arrays.asList("JSA4001","JSA4002"));
        todayCa.andTxtlxEqualTo("1");
        todayCa.andCjsjGreaterThanOrEqualTo(lasthour);
        todayCa.andCjsjLessThan(nowhour);
        List<SmsIntDto> list = equipmentFileTodayService.sendSmsQuery(todayExample);
        Map<String,Integer> sbbhBjcs = new HashMap<>();
        for (SmsIntDto entity : list){
            sbbhBjcs.put(entity.getSbbh(),entity.getBjcs());
        }
        String a1 = !StringUtils.isEmpty(sbbhBjcs.get("JSA4001"))?sbbhBjcs.get("JSA4001")+"":0+"";
        String a2 = !StringUtils.isEmpty(sbbhBjcs.get("JSA4002"))?sbbhBjcs.get("JSA4002")+"":0+"";
        SendSmsTool.sendSms("2277017","君山农业局01、君山农业局02"+"-"+"今日17点30分至20点"+"-"+";"+a1+";"+a2, phoneNum);
        lasthour = null;
        nowhour = null;
        phoneNum = null;
        a1 = null;
        a2 = null;
        list = null;
        sbbhBjcs = null;
    }
}
