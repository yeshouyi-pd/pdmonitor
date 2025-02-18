package com.pd.monitor.quartz;

import com.pd.monitor.utils.SendSmsTool;
import com.pd.monitor.wx.conf.WxRedisConfig;
import com.pd.server.main.domain.WaterEquiplog;
import com.pd.server.main.domain.WaterEquiplogExample;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.dto.DeviceStateLogDto;
import com.pd.server.main.service.DeviceStateLogService;
import com.pd.server.main.service.WaterEquiplogService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.DateUtil;
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
public class DeviceStateLogQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceStateLogQuartz.class);

    @Resource
    private DeviceStateLogService deviceStateLogService;
    @Resource
    private WaterEquiplogService waterEquiplogService;
    @Resource
    private WaterEquipmentService waterEquipmentService;

    //每天晚上11点30执行(StatisticsDataQuartz这个类在每天统计的时候，如果有原来的历史数据，会把历史数据那天的设备状态改为正常，不管设备原本的状态是否正常)
    @Scheduled(cron = "0 30 23 * * ? ")
    public void loop(){
        String curDay = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd");
        List<WaterEquiplog> waterEquiplogList = waterEquiplogService.list(null);
        for(WaterEquiplog equiplog : waterEquiplogList){
            DeviceStateLogDto deviceStateLogDto = new DeviceStateLogDto();
            deviceStateLogDto.setRq(curDay);
            deviceStateLogDto.setSbbh(equiplog.getSbbh());
            deviceStateLogDto.setSbmc(equiplog.getSm2());
            if(curDay.equals(DateUtil.getFormatDate(equiplog.getCjsj(),"yyyy-MM-dd"))){
                //正常-在线
                deviceStateLogDto.setZt("0");
            }else{
                //异常-不在线
                deviceStateLogDto.setZt("1");
            }
            deviceStateLogService.save(deviceStateLogDto);
        }
        waterEquiplogList.clear();
    }

    //每天晚上11点30执行
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
            lists.clear();
        }
    }

//    @Scheduled(cron = "0 */3 * * * ? ")
//    public void test(){
//        loop();
//    }
}
