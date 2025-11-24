package com.pd.monitor.quartz;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.wx.conf.WxRedisConfig;
import com.pd.server.main.domain.SolarPannel;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.service.SolarPannelService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.UuidUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class SolarPannelQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(SolarPannelQuartz.class);

    @Resource
    private SolarPannelService solarPannelService;
    @Resource
    private WaterEquipmentService waterEquipmentService;

    /**
     * 调用电池板太阳能信息
     * 每1个小时执行一次
     * @throws Exception
     */
    @Scheduled(cron="0 0 */1 * * ?")
    public void getSolarPannelInfo(){
        Map<String, String> attrMap = WxRedisConfig.getAttrMap();
        String openConnect = attrMap.get("openConnect");
        WaterEquipmentExample example = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = example.createCriteria();
        ca.andFzrIsNotNull();
        List<WaterEquipment> waterEquipmentLists = waterEquipmentService.list(example);
        for(WaterEquipment entity : waterEquipmentLists){
            if(!StringUtils.isEmpty(entity.getFzr())){
                //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("deviceNumber", entity.getFzr());
                if("true".equals(openConnect)){
                    try {
                        String result = HttpUtil.get("http://39.108.52.229:8190/openData/getData", paramMap);
                        JSONObject jsonObject = JSON.parseObject(result);
                        SolarPannel solarPannel = jsonObject.getJSONObject("data").toJavaObject(SolarPannel.class);
                        solarPannel.setId(UuidUtil.getShortUuid());
                        solarPannelService.saveEntity(solarPannel);
                    } catch (DuplicateKeyException e){
                        LOG.error("主键冲突，不能插入。");
                    } catch (Exception e){
                        LOG.error(e.getMessage());
                    }
                }
            }
        }
    }

}
