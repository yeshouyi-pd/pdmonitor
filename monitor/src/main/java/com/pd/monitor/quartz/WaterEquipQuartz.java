package com.pd.monitor.quartz;

import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.dto.LdTaskListDto;
import com.pd.server.main.service.AttrService;
import com.pd.server.main.service.LdTaskListService;
import com.pd.server.main.service.WaterEquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
    @Resource
    private AttrService attrService;

    /* 每天凌晨7点打开设备 */
    @Scheduled(cron = "0 0 7 * * ? ")
    public void openLoop(){
        WaterEquipmentExample example = new WaterEquipmentExample();
        example.createCriteria().andSbcjIsNotNull().andSbcjNotEqualTo("");;
        List<WaterEquipment> equipmentList = waterEquipmentService.list(example);
        String zszxml = attrService.findByAttrKey("zszxml");//早上7点执行的命令
        for(WaterEquipment entity : equipmentList){
            if(!StringUtils.isEmpty(zszxml)){
                LdTaskListDto dto = new LdTaskListDto();
                dto.setIccid(entity.getSbcj());
                dto.setTask(zszxml);
                dto.setFsdate(new Date());
                ldTaskListService.save(dto);
            }
        }
    }

    /* 每天晚上7点关闭设备 */
    @Scheduled(cron = "0 0 19 * * ? ")
    public void closeLoop(){
        WaterEquipmentExample example = new WaterEquipmentExample();
        example.createCriteria().andSbcjIsNotNull().andSbcjNotEqualTo("");
        List<WaterEquipment> equipmentList = waterEquipmentService.list(example);
        String wszxml = attrService.findByAttrKey("wszxml");//晚上7点执行的命令
        for(WaterEquipment entity : equipmentList){
            if(!StringUtils.isEmpty(wszxml)){
                LdTaskListDto dto = new LdTaskListDto();
                dto.setIccid(entity.getSbcj());
                dto.setTask(wszxml);
                dto.setFsdate(new Date());
                ldTaskListService.save(dto);
            }
        }
    }
}
