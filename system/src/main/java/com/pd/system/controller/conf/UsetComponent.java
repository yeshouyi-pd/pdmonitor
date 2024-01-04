package com.pd.system.controller.conf;


import com.pd.server.config.RedisCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Component
public class UsetComponent {

    @Resource
    public RedisTemplate redisTemplate;

    /**
     * 获取当前最大用户
     * 清除过期用户
     * 定时任务从RedisCode.AllUSER 过期的数据
     * 每两分钟执行一次
     */

    @Scheduled(cron = "0 */2 * * * ?")
    public void removeUserToken(){
        Object userobject =  redisTemplate.opsForValue().get(RedisCode.AllUSER);
        Map<String,String> usermap = new HashMap<String,String>();
        if(null != userobject && userobject instanceof Map<?,?>){
            usermap = (Map<String, String>) userobject;
            if(!CollectionUtils.isEmpty(usermap)&&!CollectionUtils.isEmpty(usermap.entrySet())){
                Iterator<Map.Entry<String, String>> iterator = usermap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    Object object = redisTemplate.opsForValue().get(entry.getValue());
                    if(null == object){
                        iterator.remove();
                    }
                }
//                Set<Map.Entry<String, String>> entrySet = usermap.entrySet();
//                for (Map.Entry<String, String> entry : entrySet) {
//                    Object object = redisTemplate.opsForValue().get(entry.getValue());
//                    if(null == object){
//                        usermap.remove(entry.getKey());
//                    }
//                }
                redisTemplate.opsForValue().set(RedisCode.AllUSER, usermap);
            }

        }

    }

    @Scheduled(cron = "0 */2 * * * ?")
    public void removeUservideoToken(){
        Object userobject =  redisTemplate.opsForValue().get(RedisCode.ALLVIDEOUSER);
        Map<String,String> usermap = new HashMap<String,String>();
        if(null != userobject && userobject instanceof Map<?,?>){
            usermap = (Map<String, String>) userobject;
            if(!CollectionUtils.isEmpty(usermap)&&!CollectionUtils.isEmpty(usermap.entrySet())){
                Iterator<Map.Entry<String, String>> iterator = usermap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    Object object = redisTemplate.opsForValue().get(entry.getValue());
                    if(null == object){
                        iterator.remove();
                    }
                }
//                Set<Map.Entry<String, String>> entrySet = usermap.entrySet();
//                for (Map.Entry<String, String> entry : entrySet) {
//                    Object object = redisTemplate.opsForValue().get(entry.getValue());
//                    if(null == object){
//                        usermap.remove(entry.getKey());
//                    }
//                }
                redisTemplate.opsForValue().set(RedisCode.ALLVIDEOUSER, usermap);
            }

        }

    }
}
