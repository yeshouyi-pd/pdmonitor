package com.pd.monitor.quartz;

import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.dto.LdTaskListDto;
import com.pd.server.main.service.LdTaskListService;
import com.pd.server.main.service.WaterEquipmentService;
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
public class WaterEquipQuartz {
    private static final Logger LOG = LoggerFactory.getLogger(WaterEquipQuartz.class);

    @Resource
    private WaterEquipmentService waterEquipmentService;
    @Resource
    private LdTaskListService ldTaskListService;

    /* 每天凌晨7点打开设备 */
    @Scheduled(cron = "0 0 7 * * ? ")
    public void openLoop(){
        WaterEquipmentExample example = new WaterEquipmentExample();
        example.createCriteria().andSbcjIsNotNull().andSbcjNotEqualTo("");;
        List<WaterEquipment> equipmentList = waterEquipmentService.list(example);
        for(WaterEquipment entity : equipmentList){
            LdTaskListDto dto = new LdTaskListDto();
            dto.setIccid(entity.getSbcj());
            dto.setTask("cmd:206");
            dto.setFsdate(new Date());
            ldTaskListService.save(dto);
        }
    }

    /* 每天晚上7点关闭设备 */
    @Scheduled(cron = "0 0 19 * * ? ")
    public void closeLoop(){
        WaterEquipmentExample example = new WaterEquipmentExample();
        example.createCriteria().andSbcjIsNotNull().andSbcjNotEqualTo("");
        List<WaterEquipment> equipmentList = waterEquipmentService.list(example);
        for(WaterEquipment entity : equipmentList){
            LdTaskListDto dto = new LdTaskListDto();
            dto.setIccid(entity.getSbcj());
            dto.setTask("cmd:207");
            dto.setFsdate(new Date());
            ldTaskListService.save(dto);
        }
    }
}
