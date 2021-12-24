package com.pd.monitor.quartz;

import com.pd.server.main.domain.WaterEquiplog;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.service.AttrService;
import com.pd.server.main.service.WaterEquiplogService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.DateTools;
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
public class DataInvalidQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(DataInvalidQuartz.class);

    @Resource
    private WaterEquipmentService waterEquipmentService;

    @Resource
    private AttrService attrService;

    @Resource
    private WaterEquiplogService waterEquiplogService;

    /**
     * 每10分钟执行一次
     * @throws Exception
     */
    @Scheduled(cron="0 */30 * * * ?")
    public void updateTheCounter(){
        WaterEquipmentExample example = new WaterEquipmentExample();
        example.createCriteria().andSblbEqualTo("0001");
        List<WaterEquipment> waterList = waterEquipmentService.list(example);
        String reqinterval = attrService.findByAttrKey("reqinterval");
        for (int i = 0; i < waterList.size(); i++) {
            WaterEquipment equipment = waterList.get(i);
            WaterEquiplog log = waterEquiplogService.findBySbbh(equipment.getSbsn());
            if(null != log && log.getCode().equals("1") && Long.parseLong(DateTools.getDatePoorS(new Date(), log.getCjsj())) < Long.parseLong(reqinterval)){
                equipment.setSbzt("1");//正常
            }else{
                equipment.setSbzt("2");//离线
            }
            waterEquipmentService.updateZt(equipment);
        }
    }

}
