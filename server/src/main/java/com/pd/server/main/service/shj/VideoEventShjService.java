package com.pd.server.main.service.shj;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.VideoEvent;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.dto.VideoEventDto;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.main.service.VideoEventService;
import com.pd.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class VideoEventShjService extends AbstractScanRequest {

    private static final Logger LOG = LoggerFactory.getLogger(VideoEventShjService.class);
    public static final String ZZ_28 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A1_\\w{1,}.mp4$";
    public static final String ZZ_24 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A4_\\w{1,}.mp4$";

    public  static RedisTemplate redisTstaticemplate;


    @Resource
    private RedisTemplate redisTemplate;

    @PostConstruct
    protected void init() {
        redisTstaticemplate = redisTemplate;
    }


    /**
     * 岸上视频剪切回调接口
     */
    public String request(JSONObject jsonParam) throws Exception {
        try {
            String sbbh = jsonParam.getString("sbbh");
            String tplj = jsonParam.getString("tplj");
            if (StringUtils.isEmpty(sbbh) || StringUtils.isEmpty(tplj)) {
                data = "参数错误";
                return data;
            }
            String wjmclj = tplj.substring(tplj.lastIndexOf("/")+1);
            if(!Pattern.matches(ZZ_28, wjmclj) && !Pattern.matches(ZZ_24, wjmclj)){
                data = "图片路径格式错误";
                return data;
            }
            Map<String,String> map = (Map<String, String>) redisTstaticemplate.opsForValue().get(RedisCode.SBSNCENTERCODE);
            VideoEventService videoEventService = SpringUtil.getBean(VideoEventService.class);
            VideoEvent videoEvent = new VideoEvent();
            videoEvent.setId(UuidUtil.getShortUuid());
            videoEvent.setSbbh(sbbh);
            videoEvent.setKssj(wjmclj.substring(0,4)+"-"+wjmclj.substring(5,7)+"-"+wjmclj.substring(8,10)+" "+wjmclj.substring(11,13)+":"+wjmclj.substring(14,16)+":"+wjmclj.substring(17,19));
            videoEvent.setJssj(wjmclj.substring(20,24)+"-"+wjmclj.substring(25,27)+"-"+wjmclj.substring(28,30)+" "+wjmclj.substring(31,33)+":"+wjmclj.substring(34,36)+":"+wjmclj.substring(37,39));
            videoEvent.setRq(wjmclj.substring(0,4)+"-"+wjmclj.substring(5,7)+"-"+wjmclj.substring(8,10));
            videoEvent.setWjlj(tplj);
            videoEvent.setWjmc(wjmclj.substring(0,39));
            videoEvent.setSfysp(1);
            videoEvent.setBz(map.get(sbbh));
            videoEventService.saveItem(videoEvent);
            sendDataToAnalysis(videoEvent);
            data = "保存成功";
        }catch (Exception e){
            LOG.error("VideoEventShjService:"+e.getMessage());
            data = "系统异常";
        }
        return data;
    }

    private void sendDataToAnalysis(VideoEvent videoEvent){
        VideoEventService videoEventService = SpringUtil.getBean(VideoEventService.class);
        try {
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("equip_num", videoEvent.getSbbh());
            paramMap.put("address", videoEvent.getWjlj());
            String result= HttpUtil.post("http://192.168.3.11:8080/detect", paramMap);
            LOG.error("算法分析返回："+result);
            videoEvent.setSm("0");
        }catch (Exception e){
            LOG.error("请求算法分析失败："+e.getMessage());
            videoEvent.setSm("1");
        }
        videoEventService.updateItem(videoEvent);
    }


}
