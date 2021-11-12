package com.pd.monitor.wx.conf;

import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.domain.WaterQualityResult;
import com.pd.server.main.domain.WaterQualityResultExample;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.main.service.WaterQualityResultService;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class EquipmentAlarmConfig extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(AlarmEventLoop.class);

    @Resource
    private WaterQualityResultService waterQualityResultService;
    @Resource
    private WaterEquipmentService waterEquipmentService;

    /**
     * 判断设备离线
     */
    @Scheduled(cron = "0 10 0/1 * * ? ")
    public void loop() {
        try{
            WaterEquipmentExample example = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria ca = example.createCriteria();
            ca.andSblbEqualTo("0002");
            List<WaterEquipment> equips = waterEquipmentService.list(example);
            for(WaterEquipment equip : equips){
                WaterQualityResultExample resultExample = new WaterQualityResultExample();
                WaterQualityResultExample.Criteria resultCa = resultExample.createCriteria();
                resultCa.andIpEqualTo(equip.getSbsn());
                resultCa.andCreateTimeEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd HH"),"%Y-%m-%d %H");
                List<WaterQualityResult> resultList = waterQualityResultService.findByExample(resultExample);
                if(resultList==null || resultList.size()<=0){
                    if("1".equals(equip.getSbzt())){
                        equip.setSbzt("2");
                    }
                }else {
                    equip.setSbzt("1");
                }
                waterEquipmentService.updateZt(equip);
            }
        }catch (Exception e){
            LOG.error(e.getMessage());
        }
    }

    /**
     * 判断设备故障
     */
    @Scheduled(cron = "0 30 0 1/2 * ? ")
    public void equipGz(){
        try{
            WaterEquipmentExample example = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria ca = example.createCriteria();
            ca.andSblbEqualTo("0002");
            List<WaterEquipment> equips = waterEquipmentService.list(example);
            for(WaterEquipment equip : equips){
                WaterQualityResultExample resultExample = new WaterQualityResultExample();
                WaterQualityResultExample.Criteria resultCa = resultExample.createCriteria();
                resultCa.andIpEqualTo(equip.getSbsn());
                resultCa.andCreateTimeGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-2),"yyyy-MM-dd"));
                resultCa.andCreateTimeLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
                List<WaterQualityResult> resultList = waterQualityResultService.findByExample(resultExample);
                if(resultList==null || resultList.size()<=0){
                    equip.setSbzt("2");
                }
                waterEquipmentService.updateZt(equip);
            }
        }catch (Exception e){
            LOG.error(e.getMessage());
        }
    }
}
