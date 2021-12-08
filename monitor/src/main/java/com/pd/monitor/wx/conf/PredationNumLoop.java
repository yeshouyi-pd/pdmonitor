package com.pd.monitor.wx.conf;

import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.dto.PredationNumDto;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.main.service.PredationNumService;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class PredationNumLoop  extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(AlarmEventLoop.class);
    @Resource
    private EquipmentFileService equipmentFileService;
    @Resource
    private PredationNumService predationNumService;

    /**
     * 计算前一天翁叫声(捕食)次数
     */
    @Scheduled(cron = "0 0 3 * * ? ")
    public void loop() {
        String beforeDate = DateUtil.getFormatDate(DateUtil.getNextNumDay(new Date(),-1),"yyyy-MM-dd");
        //出现次数
        EquipmentFileExample example1 = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca1 = example1.createCriteria();
        ca1.andRqEqualTo(beforeDate);
        ca1.andTpljLike("%png");
        List<AlarmNumbersDto> allList = equipmentFileService.groupByRq(example1);
        for(AlarmNumbersDto alarmNumbersDto : allList){
            //捕食次数
            EquipmentFileExample example = new EquipmentFileExample();
            EquipmentFileExample.Criteria ca = example.createCriteria();
            ca1.andRqEqualTo(beforeDate);
            ca.andTpljLike("%png");
            ca.andTpljLike("%predation%");
            if(!StringUtils.isEmpty(alarmNumbersDto.getDeptcode())){
                ca.andDeptcodeEqualTo(alarmNumbersDto.getDeptcode());
            }
            ca.andSbbhEqualTo(alarmNumbersDto.getSbbh());
            List<EquipmentFile> predationList = equipmentFileService.listAll(example);
            PredationNumDto dto = new PredationNumDto();
            dto.setPredationNum(predationList.size());
            dto.setAlarmNum(alarmNumbersDto.getAlarmNum());
            dto.setCjsj(DateUtil.toDate(beforeDate,"yyyy-MM-dd"));
            dto.setSbbh(alarmNumbersDto.getSbbh());
            if(!StringUtils.isEmpty(alarmNumbersDto.getDeptcode())){
                dto.setDeptcode(alarmNumbersDto.getDeptcode());
            }
            predationNumService.save(dto);
        }

    }
}
