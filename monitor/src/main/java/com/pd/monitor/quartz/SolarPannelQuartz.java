package com.pd.monitor.quartz;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.SolarPannel;
import com.pd.server.main.service.SolarPannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

@Component
@EnableScheduling
public class SolarPannelQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(SolarPannelQuartz.class);

    @Resource
    private SolarPannelService solarPannelService;

    /**
     * 每10分钟执行一次
     * @throws Exception
     */
    @Scheduled(cron="0 */10 * * * ?")
    public void getSolarPannelInfo(){
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("deviceNumber", "ML182S737703S4");
        String result = HttpUtil.get("http://39.108.52.229:8190/openData/getData", paramMap);
        try {
            JSONObject jsonObject = JSON.parseObject(result);
            SolarPannel entity = jsonObject.getJSONObject("data").toJavaObject(SolarPannel.class);
            solarPannelService.saveEntity(entity);
        } catch (DuplicateKeyException e){
            LOG.error("主键冲突，不能插入。");
        } catch (Exception e){
            LOG.error(e.getMessage());
            LOG.error(result);
        }
    }

}
