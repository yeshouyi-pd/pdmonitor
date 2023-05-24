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

    /* 每天凌晨5点50重启 */
    @Scheduled(cron = "0 50 5 * * ? ")
    public void reloadLoop() {
        String iccids = attrService.findByAttrKey("iccidsReload");
        try {
            saveTaskList(iccids, "cmd:203");
            Thread.sleep(30000);
            saveTaskList(iccids, "cmd:202");
        }catch (Exception e){
            LOG.error("重启失败："+e.getMessage());
        }
    }

    /* 每天凌晨5点50打开设备通道一 */
    @Scheduled(cron = "0 50 5 * * ? ")
    public void openLoop(){
        String iccids = attrService.findByAttrKey("iccidsZsTd1");
        String zszxml = attrService.findByAttrKey("zszxml");//早上5点50执行的命令
        saveTaskList(iccids, zszxml);
    }

    /* 每天晚上7点10关闭设备通道一 */
    @Scheduled(cron = "0 10 19 * * ? ")
    public void closeLoop(){
        String iccids = attrService.findByAttrKey("iccidsWsTd1");
        String wszxml = attrService.findByAttrKey("wszxml");//晚上7点10执行的命令
        saveTaskList(iccids, wszxml);
    }

    /* 每天晚上5点40打开设备通道二 */
    @Scheduled(cron = "0 40 17 * * ? ")
    public void open2Loop(){
        String iccids = attrService.findByAttrKey("iccidsZsTd2");
        String zszxml = attrService.findByAttrKey("zszxml2");//晚上5点40执行的命令
        saveTaskList(iccids, zszxml);
    }

    /* 每天晚上7点10关闭设备通道二 */
    @Scheduled(cron = "0 10 19 * * ? ")
    public void close2Loop(){
        String iccids = attrService.findByAttrKey("iccidsWsTd2");
        String wszxml = attrService.findByAttrKey("wszxml2");//晚上7点10执行的命令
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
