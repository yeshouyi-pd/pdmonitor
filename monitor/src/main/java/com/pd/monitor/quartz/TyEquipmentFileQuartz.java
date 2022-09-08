package com.pd.monitor.quartz;

import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.service.EquipmentFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableScheduling
public class TyEquipmentFileQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(TyEquipmentFileQuartz.class);

    @Resource
    private EquipmentFileService equipmentFileService;

    /*  */
    @Scheduled(cron = "0 0 4 * * ? ")
    public void tyEquipmentFile(){
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        
    }
}
