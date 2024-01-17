package com.pd.environment.controller;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.log.Log;
import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VideoEventDto;
import com.pd.server.main.mapper.WaterEquipmentMapper;
import com.pd.server.main.service.VideoEventService;
import com.pd.server.main.service.WaterEquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/videoEventRev")
public class VideoEventRevController {

    private static final Logger LOG = LoggerFactory.getLogger(VideoEventRevController.class);
    public static final String BUSINESS_NAME = "接收分析后的视频文件";


    public static final String ZZ_29 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A1_\\w{1,}_\\w{1,}.mp4$";
    public static final String ZZ_30 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A4_\\w{1,}_\\w{1,}.mp4$";

    @Resource
    private VideoEventService videoEventService;
    @Resource
    private WaterEquipmentService waterEquipmentService;

    /**
     * 保存数据
     */
    @PostMapping("/saveData")
    public ResponseDto saveData(@RequestBody JSONObject jsonObject) {
        ResponseDto responseDto = new ResponseDto();
        LOG.error("算法分析到海豚后的回调数据："+jsonObject.toJSONString());
        try {
            if(!StringUtils.isEmpty(jsonObject.getString("sbbh"))){
                responseDto.setCode("4000");
                responseDto.setSuccess(false);
                responseDto.setMessage("请求参数存在错误");
            }
            if(!StringUtils.isEmpty(jsonObject.getString("wjlj"))){
                responseDto.setCode("4000");
                responseDto.setSuccess(false);
                responseDto.setMessage("请求参数存在错误");
            }
            String sbbh = jsonObject.getString("sbbh");
            String wjlj = jsonObject.getString("wjlj");
            String wjmclj = wjlj.substring(wjlj.lastIndexOf("/")+1);
            if(!Pattern.matches(ZZ_29, wjmclj) && !Pattern.matches(ZZ_30, wjmclj)){
                responseDto.setCode("4000");
                responseDto.setSuccess(false);
                responseDto.setMessage("文件名称命名错误");
                return responseDto;
            }
            WaterEquipmentExample example = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria ca = example.createCriteria();
            ca.andSbsnEqualTo(sbbh);
            List<WaterEquipment> lists = waterEquipmentService.list(example);
            if(lists==null || lists.isEmpty()){
                responseDto.setCode("4000");
                responseDto.setSuccess(false);
                responseDto.setMessage("设备编号不存在");
                return responseDto;
            }
            VideoEventDto videoEventDto = new VideoEventDto();
            videoEventDto.setSbbh(sbbh);
            videoEventDto.setKssj(wjmclj.substring(0,4)+"-"+wjmclj.substring(5,7)+"-"+wjmclj.substring(8,10)+" "+wjmclj.substring(11,13)+":"+wjmclj.substring(14,16)+":"+wjmclj.substring(17,19));
            videoEventDto.setJssj(wjmclj.substring(20,24)+"-"+wjmclj.substring(25,27)+"-"+wjmclj.substring(28,30)+" "+wjmclj.substring(31,33)+":"+wjmclj.substring(34,36)+":"+wjmclj.substring(37,39));
            videoEventDto.setRq(videoEventDto.getKssj().substring(0,10));
            videoEventDto.setWjlj(wjlj);
            videoEventDto.setWjmc(wjmclj.substring(0,39));
            videoEventDto.setSfysp(!StringUtils.isEmpty(jsonObject.getString("sssp"))?2:0);//0分析视频2实时视频
            videoEventDto.setBz(lists.get(0).getDeptcode());
            videoEventService.save(videoEventDto);
            responseDto.setCode("0000");
            responseDto.setSuccess(true);
            responseDto.setMessage("保存成功");
        }catch (Exception e){
            responseDto.setCode("2001");
            responseDto.setSuccess(false);
            responseDto.setMessage("系统出错，请联系管理员");
            return responseDto;
        }finally {

        }
        return responseDto;
    }


}
