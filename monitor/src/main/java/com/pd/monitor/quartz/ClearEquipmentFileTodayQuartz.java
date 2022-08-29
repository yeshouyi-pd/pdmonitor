package com.pd.monitor.quartz;

import com.pd.server.main.domain.EquipmentFileToday;
import com.pd.server.main.domain.EquipmentFileTodayExample;
import com.pd.server.main.service.EquipmentFileTodayService;
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
public class ClearEquipmentFileTodayQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(ClearEquipmentFileTodayQuartz.class);

    @Resource
    private EquipmentFileTodayService todayService;

    /**
     * EquipmentFileToday只保存当天数据，凌晨一点清除前一天的数据
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    public void clearData(){
        EquipmentFileTodayExample example = new EquipmentFileTodayExample();
        example.createCriteria().andRqEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        List<EquipmentFileToday> list = todayService.listAll(example);
        for(EquipmentFileToday entity : list){
            todayService.delete(entity.getId());
        }
    }

}
