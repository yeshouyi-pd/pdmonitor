package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.dto.VideoEventDto;
import com.pd.server.main.service.VideoEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Service
public class VideoEventShjService extends AbstractScanRequest {

    private static final Logger LOG = LoggerFactory.getLogger(VideoEventShjService.class);
    public static final String ZZ_28 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A1_\\w{1,}.mp4$";
    public static final String ZZ_24 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A4_\\w{1,}.mp4$";

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
            VideoEventService videoEventService = SpringUtil.getBean(VideoEventService.class);
            VideoEventDto videoEventDto = new VideoEventDto();
            videoEventDto.setSbbh(sbbh);
            videoEventDto.setKssj(wjmclj.substring(0,4)+"-"+wjmclj.substring(5,7)+"-"+wjmclj.substring(8,10)+" "+wjmclj.substring(11,13)+":"+wjmclj.substring(14,16)+":"+wjmclj.substring(17,19));
            videoEventDto.setJssj(wjmclj.substring(20,24)+"-"+wjmclj.substring(25,27)+"-"+wjmclj.substring(28,30)+" "+wjmclj.substring(31,33)+":"+wjmclj.substring(34,36)+":"+wjmclj.substring(37,39));
            videoEventDto.setRq(wjmclj.substring(0,4)+"-"+wjmclj.substring(5,7)+"-"+wjmclj.substring(8,10));
            videoEventDto.setWjlj(tplj);
            videoEventDto.setWjmc(wjmclj.substring(0,39));
            videoEventDto.setSfysp(1);
            videoEventService.save(videoEventDto);
            data = "保存成功";
        }catch (Exception e){
            LOG.error("VideoEventShjService:"+e.getMessage());
            data = "系统异常";
        }
        return data;
    }
}