package com.pd.monitor.quartz;

import com.pd.server.main.domain.WaterEquiplog;
import com.pd.server.main.dto.DeviceStateLogDto;
import com.pd.server.main.service.DeviceStateLogService;
import com.pd.server.main.service.WaterEquiplogService;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class DeviceStateLogQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceStateLogQuartz.class);

    @Resource
    private DeviceStateLogService deviceStateLogService;
    @Resource
    private WaterEquiplogService waterEquiplogService;

    //统计设备是否在线，如果当天设备心跳包有数据，则今天的设备就判定为在线，否则就是离线
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
        waterEquiplogList=null;
    }


}
