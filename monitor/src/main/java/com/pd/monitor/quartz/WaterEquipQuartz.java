package com.pd.monitor.quartz;

import com.pd.server.main.service.AttrService;
import com.pd.server.secondary.dto.LdTaskListSecDto;
import com.pd.server.secondary.service.LdTaskListSecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

@Component
@EnableScheduling
public class WaterEquipQuartz {
    private static final Logger LOG = LoggerFactory.getLogger(WaterEquipQuartz.class);

    @Resource
    private LdTaskListSecService ldTaskListSecService;
    @Resource
    private AttrService attrService;

    /* 每天凌晨7点打开设备 */
    @Scheduled(cron = "0 0 7 * * ? ")
    public void openLoop(){
        String iccids = attrService.findByAttrKey("iccids");
        String zszxml = attrService.findByAttrKey("zszxml");//早上7点执行的命令
        saveTaskList(iccids, zszxml);
    }

    /* 每天晚上7点关闭设备 */
    @Scheduled(cron = "0 0 19 * * ? ")
    public void closeLoop(){
        String iccids = attrService.findByAttrKey("iccids");
        String wszxml = attrService.findByAttrKey("wszxml");//晚上7点执行的命令
        saveTaskList(iccids, wszxml);
    }

    public void saveTaskList(String iccids,String zxml){
        if(!StringUtils.isEmpty(iccids)){
            for(String iccid : iccids.split(",")){
                if(!StringUtils.isEmpty(zxml)){
                    LdTaskListSecDto dto = new LdTaskListSecDto();
                    dto.setIccid(iccid);
                    dto.setTask(zxml);
                    dto.setFsdate(new Date());
                    ldTaskListSecService.save(dto);
                }
            }
        }
    }
}
