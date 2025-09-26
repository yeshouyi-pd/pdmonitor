package com.pd.system.controller.app;


import cn.hutool.core.collection.CollectionUtil;
import com.pd.server.main.domain.AppMonitorExp;
import com.pd.server.main.domain.PontoonGpsExample;
import com.pd.server.main.dto.AppMonitorExpDto;
import com.pd.server.main.service.AppMonitorExpService;
import com.pd.server.main.service.PontoonGpsService;
import com.pd.server.util.CopyUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class AppSyncGps {
    @Resource
    private AppMonitorExpService appMonitorExpService;

    @Resource
    private PontoonGpsService pontoonGpsService;


    @Scheduled(cron = "0 0 0/1 * * ?")
    public void syncGps() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<AppMonitorExpDto> appMonitorExpDtos = appMonitorExpService.listNoGps();
        for (AppMonitorExpDto appMonitorExpDto : appMonitorExpDtos) {
            if(StringUtils.isBlank(appMonitorExpDto.getSbbh())){ //要是没有编号直接跳转
                continue;
            }
            try {
                PontoonGpsExample example = new PontoonGpsExample();
                PontoonGpsExample.Criteria ca = example.createCriteria();
                ca.andSbbhEqualTo(appMonitorExpDto.getSbbh());
                ca.andRqEqualTo(sdf.format( appMonitorExpDto.getCjsj()));
                ca.andCjsjLessThan(appMonitorExpDto.getCjsj());
                example.setOrderByClause(" cjsj desc limit 1 ");
                List<String> gpsList = pontoonGpsService.selectGpsByExample(example);
                if (CollectionUtil.isNotEmpty(gpsList)) {
                    appMonitorExpDto.setDeclat(gpsList.get(0).split(",")[0]);
                    appMonitorExpDto.setDeclong(gpsList.get(0).split(",")[1]);
                }else{
                    example.clear();
                    ca.andSbbhEqualTo(appMonitorExpDto.getSbbh());
                    ca.andRqEqualTo(sdf.format( appMonitorExpDto.getCjsj()));
                    ca.andCjsjGreaterThan(appMonitorExpDto.getCjsj());
                    example.setOrderByClause(" cjsj desc limit 1 ");
                    List<String> gpsListS = pontoonGpsService.selectGpsByExample(example);
                    if (CollectionUtil.isNotEmpty(gpsListS)) {
                        appMonitorExpDto.setDeclat(gpsListS.get(0).split(",")[0]);
                        appMonitorExpDto.setDeclong(gpsListS.get(0).split(",")[1]);
                    }
                }
                AppMonitorExp appMonitorExp = CopyUtil.copy(appMonitorExpDto, AppMonitorExp.class);
                appMonitorExpService.update(appMonitorExp);
            }catch (Exception e){
            }
        }

    }



}
