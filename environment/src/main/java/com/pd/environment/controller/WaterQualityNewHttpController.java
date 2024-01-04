package com.pd.environment.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WaterQualityNewDto;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.main.service.WaterQualityNewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/waterQualityNew")
public class WaterQualityNewHttpController {

    private static final Logger LOG = LoggerFactory.getLogger(WaterQualityNewHttpController.class);
    public static final String BUSINESS_NAME = "接收第三方水质数据";

    @Resource
    private WaterQualityNewService waterQualityNewService;
    @Resource
    private WaterEquipmentService waterEquipmentService;

    /**
     * 保存数据
     */
    @PostMapping("/saveData")
    public ResponseDto saveData(@RequestBody JSONObject jsonObject) {
        ResponseDto responseDto = new ResponseDto();
        try {
            if(jsonObject.isEmpty()){
                responseDto.setSuccess(false);
                responseDto.setMessage("无效数据");
                return responseDto;
            }
            if(StringUtils.isEmpty(jsonObject.get("cjsj")) || StringUtils.isEmpty(jsonObject.get("sbbh")) ){
                responseDto.setSuccess(false);
                responseDto.setMessage("参数错误");
                return responseDto;
            }
            if(StringUtils.isEmpty(jsonObject.get("oxidative")) && StringUtils.isEmpty(jsonObject.get("chlorophyll"))
                    && StringUtils.isEmpty(jsonObject.get("ph")) && StringUtils.isEmpty(jsonObject.get("ad"))){
                responseDto.setSuccess(false);
                responseDto.setMessage("无效数据");
                return responseDto;
            }
            String cjsj = jsonObject.getString("cjsj");
            String isFlag = "^(\\d{4})-(\\d{2})-(\\d{2})\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";//判断时间
            if(!cjsj.matches(isFlag)){
                responseDto.setSuccess(false);
                responseDto.setMessage("时间格式错误");
                return responseDto;
            }
            WaterEquipmentExample example = new WaterEquipmentExample();
            List<String> sbbhs= waterEquipmentService.findSbbh(example);
            if(!sbbhs.contains(jsonObject.getString("sbbh"))){
                responseDto.setSuccess(false);
                responseDto.setMessage("设备编号错误");
                return responseDto;
            }
            WaterQualityNewDto dto = new WaterQualityNewDto();
            dto.setCjsj(jsonObject.getDate("cjsj"));
            dto.setSbbh(jsonObject.getString("sbbh"));
            dto.setOxidative(jsonObject.getString("oxidative"));
            dto.setChlorophyll(jsonObject.getString("chlorophyll"));
            dto.setPh(jsonObject.getString("ph"));
            dto.setAd(jsonObject.getString("ad"));
            waterQualityNewService.save(dto);
            responseDto.setSuccess(true);
            responseDto.setMessage("保存成功");
        }catch (Exception e){
            LOG.error("接收到的数据："+JSONObject.toJSONString(jsonObject)+"====错误原因："+e.getMessage());
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccess(false);
        }
        return responseDto;
    }
}
