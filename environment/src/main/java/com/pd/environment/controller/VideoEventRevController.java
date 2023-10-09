package com.pd.environment.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.VideoEventDto;
import com.pd.server.main.service.VideoEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/videoEventRev")
public class VideoEventRevController {

    private static final Logger LOG = LoggerFactory.getLogger(VideoEventRevController.class);
    public static final String BUSINESS_NAME = "接收分析后的视频文件";

    public static final String ZZ_21 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A4_\\w{1,}_\\w{1,}.mp4$";
    public static final String ZZ_23 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{1,}_A4_\\w{1,}_\\w{1,}.mp4$";
    public static final String ZZ_25 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{1,}_A4_\\w{1,}_\\w{1,}.mp4$";
    public static final String ZZ_27 ="^\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{4}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_\\d{2}_predation_\\d{1,}_A4_\\w{1,}_\\w{1,}.mp4$";

    @Resource
    private VideoEventService videoEventService;

    /**
     * 保存数据
     */
    @PostMapping("/saveData")
    public ResponseDto saveData(@RequestBody JSONObject jsonObject) {
        ResponseDto responseDto = new ResponseDto();
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
            String kssj = "";
            String jssj = "";
            String temp = wjlj.substring(wjlj.lastIndexOf("/")+1,wjlj.lastIndexOf("_A4"));
            kssj = temp.substring(0,4)+"-"+temp.substring(5,7)+"-"+temp.substring(8,10)+" "+temp.substring(11,13)+":"+temp.substring(14,16)+":"+temp.substring(17,19);
            if( Pattern.matches(ZZ_21, wjlj) || Pattern.matches(ZZ_27, wjlj) ){
                jssj = temp.substring(30,34)+"-"+temp.substring(35,37)+"-"+temp.substring(38,40)+" "+temp.substring(41,43)+":"+temp.substring(44,46)+":"+temp.substring(47,49);
            }else if( Pattern.matches(ZZ_23, wjlj) || Pattern.matches(ZZ_25, wjlj) ){
                jssj = temp.substring(20,24)+"-"+temp.substring(25,27)+"-"+temp.substring(28,30)+" "+temp.substring(31,33)+":"+temp.substring(34,36)+":"+temp.substring(37,39);
            }else{
                responseDto.setCode("4000");
                responseDto.setSuccess(false);
                responseDto.setMessage("文件名称命名错误");
                return responseDto;
            }
            VideoEventDto videoEventDto = new VideoEventDto();
            videoEventDto.setSbbh(sbbh);
            videoEventDto.setKssj(kssj);
            videoEventDto.setJssj(jssj);
            videoEventDto.setRq(videoEventDto.getKssj().substring(0,10));
            videoEventDto.setWjlj(wjlj);
            videoEventService.save(videoEventDto);
            responseDto.setCode("0000");
            responseDto.setSuccess(true);
            responseDto.setMessage("保存成功");
            return responseDto;
        }catch (Exception e){
            responseDto.setCode("2001");
            responseDto.setSuccess(false);
            responseDto.setMessage("系统出错，请联系管理员");
            return responseDto;
        }
    }
}
