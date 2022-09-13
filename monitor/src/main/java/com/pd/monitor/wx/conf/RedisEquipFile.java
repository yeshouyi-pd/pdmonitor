package com.pd.monitor.wx.conf;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.*;
import com.pd.server.main.mapper.EquipmentFileMapper;
import com.pd.server.main.mapper.EquipmentFileTodayMapper;
import com.pd.server.main.service.*;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Component
public class RedisEquipFile extends BaseWxController {
    private static final Logger LOG = LoggerFactory.getLogger(RedisEquipFile.class);
    @Resource
    private EquipmentFileService equipmentFileService;
    @Resource
    private AttrService attrService;
    @Resource
    private RedisTemplate  redisTemplate;
    @Resource
    private EquipmentFileMapper equipmentFileMapper;
    @Resource
    private EquipmentFileTodayMapper equipmentFileTodayMapper;

    /**
     * 将缓存中存储的数据写入数据库，然后再做统计
     */
    @Scheduled(cron = "0 05 0 * * ? ")
    public void loopTemplate(){
        String predationsbsn = attrService.findByAttrKey("predationsbsn");
        String[] arr = predationsbsn.split(",");
        System.out.println("arr"+arr);
        for(String sbbh : arr){
            if(!StringUtils.isEmpty(redisTemplate.opsForValue().get(sbbh+"WB"))){
                String entityJson = (String) redisTemplate.opsForValue().get(sbbh+"WB");
                System.out.println("entityJson"+entityJson);
                EquipmentFile entity = JSONObject.parseObject(entityJson, EquipmentFile.class);
                EquipmentFile lastFile = equipmentFileService.selectLastOneBySbbh(sbbh);
                if(!StringUtils.isEmpty(entity.getCjsj())&&!StringUtils.isEmpty(lastFile.getCjsj())&&isOverMinute(DateUtil.getFormatDate(lastFile.getCjsj(),"yyyy-MM-dd HH:mm:ss"),DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd HH:mm:ss"))){
                    equipmentFileMapper.insert(entity);
                    equipmentFileTodayMapper.insertEquipFile(entity);
                    redisTemplate.delete(sbbh+"WB");
                }
            }
        }
    }

    public  Boolean isOverMinute(String curDateStr, String nextDateStr){
        Date begin = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date end = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        long minute=(end.getTime()-begin.getTime())/(1000*60);//除以1000是为了转换成秒
        String predationInterval = attrService.findByAttrKey("predationInterval");
        if(minute<=Integer.parseInt(predationInterval)){
            return true;
        }
        return false;
    }
}
