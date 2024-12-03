package com.pd.monitor.quartz;

import com.pd.server.main.domain.*;
import com.pd.server.main.dto.AzimuthAngleUniqueDto;
import com.pd.server.main.service.AzimuthAngleRqService;
import com.pd.server.main.service.AzimuthAngleService;
import com.pd.server.main.service.AzimuthAngleUniqueService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class AzimuthAngleQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(AzimuthAngleQuartz.class);

    @Resource
    private AzimuthAngleRqService azimuthAngleRqService;
    @Resource
    private AzimuthAngleUniqueService azimuthAngleUniqueService;
    @Resource
    private AzimuthAngleService azimuthAngleService;

    //@Scheduled(cron = "0 30 01 * * ? ")
    @Scheduled(cron = "0 50 13 * * ? ")
    public void loop() throws ParseException {
        String beforeDayStr = DateTools.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd");
        AzimuthAngleRqExample example = new AzimuthAngleRqExample();
        AzimuthAngleRqExample.Criteria ca = example.createCriteria();
        ca.andRqLessThanOrEqualTo(beforeDayStr);
        List<AzimuthAngleRq> rqlists = azimuthAngleRqService.selectByQuartz(example);
        staticData(rqlists);
    }

    public void staticData(List<AzimuthAngleRq> rqlists){
        for(AzimuthAngleRq entity : rqlists){
            AzimuthAngleUniqueExample uniqueExample = new AzimuthAngleUniqueExample();
            AzimuthAngleUniqueExample.Criteria uniqueCa = uniqueExample.createCriteria();
            uniqueCa.andSbbhEqualTo(entity.getSbbh());
            uniqueCa.andRqEqualTo(entity.getRq());
            azimuthAngleUniqueService.deleteByExample(uniqueExample);
            AzimuthAngleExample example = new AzimuthAngleExample();
            AzimuthAngleExample.Criteria ca = example.createCriteria();
            ca.andRqEqualTo(entity.getRq());
            ca.andSbbhEqualTo(entity.getSbbh());
            List<AzimuthAngle> lists = azimuthAngleService.selectByQuartz(example);
            for(AzimuthAngle azimuthAngle : lists){
                AzimuthAngleUniqueDto uniqueDto = CopyUtil.copy(azimuthAngle, AzimuthAngleUniqueDto.class);
                azimuthAngleUniqueService.save(uniqueDto);
            }
        }
        clearRqData();
    }

    public void clearRqData(){
        String beforeDayStr = DateTools.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd");
        AzimuthAngleRqExample example = new AzimuthAngleRqExample();
        AzimuthAngleRqExample.Criteria ca = example.createCriteria();
        ca.andRqLessThanOrEqualTo(beforeDayStr);
        azimuthAngleRqService.deleteByExample(example);
    }
}
