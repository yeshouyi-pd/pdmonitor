package com.pd.monitor.quartz;

import com.pd.server.main.domain.*;
import com.pd.server.main.service.InterfaceLogService;
import com.pd.server.main.service.WaterEquiplogService;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@EnableScheduling
public class ClearDataQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(ClearDataQuartz.class);

    @Resource
    private InterfaceLogService interfaceLogService;
    @Resource
    private WaterEquiplogService waterEquiplogService;

    /* 每天凌晨6点开始清理数据 */
    @Scheduled(cron = "0 0 6 * * ? ")
    public void clearData(){
        /**
         * 清除6个月之前的日志数据(interface_log表)
         */
        InterfaceLogExample logExample = new InterfaceLogExample();
        logExample.createCriteria().andQqsjLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getMonthBeforeOrLater(-6),"yyyy-MM-dd"));
        List<InterfaceLog> logList = interfaceLogService.listAll(logExample);
        for(InterfaceLog entity : logList){
            interfaceLogService.delete(entity.getId());
        }
        /**
         * 清除1个月之前的心跳包数据(water_equiplog表)
         */
        WaterEquiplogExample equiplogExample = new WaterEquiplogExample();
        equiplogExample.createCriteria().andCjsjLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getMonthBeforeOrLater(-1),"yyyy-MM-dd"));
        List<WaterEquiplog> equiplogList = waterEquiplogService.list(equiplogExample);
        for(WaterEquiplog entity : equiplogList){
            waterEquiplogService.delete(entity.getId());
        }
    }
}
