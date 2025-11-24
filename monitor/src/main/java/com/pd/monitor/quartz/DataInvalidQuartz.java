package com.pd.monitor.quartz;

import com.pd.monitor.utils.SendSmsTool;
import com.pd.monitor.wx.conf.WxRedisConfig;
import com.pd.server.main.domain.WaterEquiplog;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.service.AttrService;
import com.pd.server.main.service.WaterEquiplogService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.DateTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class DataInvalidQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(DataInvalidQuartz.class);

    @Resource
    private WaterEquipmentService waterEquipmentService;

    @Resource
    private AttrService attrService;

    @Resource
    private WaterEquiplogService waterEquiplogService;

    //江豚设备状态
    /**
     * 每10分钟执行一次
     * @throws Exception
     */
    @Scheduled(cron="0 */10 * * * ?")
    public void updateTheCounter(){
        WaterEquipmentExample example = new WaterEquipmentExample();
        example.createCriteria().andSblbEqualTo("0001");
        List<WaterEquipment> waterList = waterEquipmentService.list(example);
        String reqinterval = attrService.findByAttrKey("reqinterval");
        String reqintervalextend = attrService.findByAttrKey("reqintervalextend");
        for (int i = 0; i < waterList.size(); i++) {
            WaterEquipment equipment = waterList.get(i);
            WaterEquiplog log = waterEquiplogService.findBySbbh(equipment.getSbsn());
            if(null != log && log.getCode().equals("1") && Long.parseLong(DateTools.getDatePoorS(new Date(), log.getCjsj())) < (Long.parseLong(reqinterval)+Long.parseLong(reqintervalextend))){
                equipment.setSbzt("1");//正常
            }else{
                equipment.setSbzt("2");//离线
            }
            waterEquipmentService.updateZt(equipment);
        }
        reqinterval = null;
        reqintervalextend = null;
        waterList = null;
    }


    //每天8点，12点，18点执行，如果有5个设备的状态为离线，则发送短信
    @Scheduled(cron = "0 0 08,12,18 * * ? ")
    public void sendMsg(){
        Map<String, String> attrMap = WxRedisConfig.getAttrMap();
        if(!StringUtils.isEmpty(attrMap.get("deviceErrorPhone"))){
            WaterEquipmentExample example = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria ca = example.createCriteria();
            ca.andSblbEqualTo("0001");
            ca.andSbztEqualTo("2");
            List<WaterEquipment> lists = waterEquipmentService.list(example);
            if(lists.size()>5){
                SendSmsTool.sendSms("2038983","",attrMap.get("deviceErrorPhone"));
            }
            lists=null;
        }
    }

}
