package com.pd.monitor.quartz;

import com.pd.monitor.utils.SendSmsTool;
import com.pd.monitor.wx.conf.WxRedisConfig;
import com.pd.server.config.CodeType;
import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.dto.KvIntDto;
import com.pd.server.main.service.AttrService;
import com.pd.server.main.service.EquipmentFileTodayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class SendSmsQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(DataInvalidQuartz.class);

    @Resource
    private EquipmentFileTodayService equipmentFileTodayService;


    /**
     * 每1小时执行一次
     * @throws Exception
     */
    @Scheduled(cron="0 0 8-19 * * ? ")
    public void sendSmsCount(){
        List<KvIntDto> list = equipmentFileTodayService.sendSmsQuery();
        StringBuilder sbbhSb = new StringBuilder();
        StringBuilder cxcsSb = new StringBuilder();
        for (KvIntDto entity : list){
            sbbhSb.append(entity.getKey()+",");
            cxcsSb.append(entity.getValue()+",");
        }
        if(!StringUtils.isEmpty(sbbhSb.toString())){
            SendSmsTool.sendSms("1840448",sbbhSb.toString().substring(0,sbbhSb.toString().length()-1)+"-"+cxcsSb.toString().substring(0,cxcsSb.toString().length()-1));
        }
    }

}
