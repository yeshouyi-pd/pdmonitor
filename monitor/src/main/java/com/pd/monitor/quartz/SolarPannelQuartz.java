package com.pd.monitor.quartz;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.wx.conf.WxRedisConfig;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.SolarPannel;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.service.SolarPannelService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.DateTools;
import com.pd.server.util.SendSmsTool;
import com.pd.server.util.UuidUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 调用电池板太阳能信息
     * 每1个小时执行一次
     * @throws Exception
     */
    @Scheduled(cron="0 */30 * * * ?")
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
                        if("1".equals(entity.getOpenTyndcb())){
                            isJudgeSendMsg(solarPannel);
                        }
                    } catch (DuplicateKeyException e){
                        LOG.error("主键冲突，不能插入。");
                    } catch (Exception e){
                        LOG.error(e.getMessage());
                    }
                }
            }
        }
    }


    public void isJudgeSendMsg(SolarPannel solarPannel){
        if(!StringUtils.isEmpty(solarPannel.getLoadCurrent()) && !StringUtils.isEmpty(solarPannel.getDailyElectricityConsumption())){
            String loadCurrent = solarPannel.getLoadCurrent().substring(0,solarPannel.getLoadCurrent().indexOf("A")).trim();
            //String dailyElectricity = solarPannel.getDailyElectricityConsumption().substring(0,solarPannel.getDailyElectricityConsumption().indexOf("KWH")).trim();
            //负载电流>=2，当日用电量>=0.3
            if(Double.parseDouble(loadCurrent)>=3){
                //太阳能板出现故障，发送短信
                sendMsg(solarPannel.getDeviceNumber(),solarPannel.getDeviceName());
            }
        }
    }

    public void sendMsg(String sbbh,String sbmc){
        try {
            String ymd = DateTools.getYMD();
            Map<String,String> attrMap = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.ATTRECODEKEY);
            String phoneNum = attrMap.get("solarPhone");
            if(!org.springframework.util.StringUtils.isEmpty(redisTemplate.opsForValue().get("TYNB"+sbbh))){
                String redisValue = (String) redisTemplate.opsForValue().get("TYNB"+sbbh);
                String[] arr = redisValue.split("-");
                //判断日期是否是今天，如果是，则判断次数是否大于3
                if(ymd.equals(arr[1])){
                    int cs = Integer.parseInt(arr[2]);
                    if(cs<=3){
                        //发送次数少于等于3次
                        SendSmsTool.sendSms("2579191",sbmc, phoneNum);
                        cs++;
                        redisTemplate.opsForValue().set("TYNB"+sbbh, sbbh+"-"+ymd+"-"+cs);
                    }
                }else{
                    //不是今天的数据，直接发送短信
                    SendSmsTool.sendSms("2579191",sbmc, phoneNum);
                    redisTemplate.opsForValue().set("TYNB"+sbbh, sbbh+"-"+ymd+"-1");
                }
            }else{
                //发送短信
                SendSmsTool.sendSms("2579191",sbmc, phoneNum);
                redisTemplate.opsForValue().set("TYNB"+sbbh, sbbh+"-"+ymd+"-1");
            }
        }catch (Exception e){
            LOG.error("太阳能板发送短信失败："+e.getMessage());
        }
    }
}
