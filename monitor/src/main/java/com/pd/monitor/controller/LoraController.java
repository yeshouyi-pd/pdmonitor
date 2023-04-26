package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.main.domain.SendCommand;
import com.pd.server.main.domain.StationsHeart;
import com.pd.server.main.domain.StationsHeartbeat;
import com.pd.server.main.domain.StationsInfo;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.SendCommandService;
import com.pd.server.main.service.StationsHeartService;
import com.pd.server.main.service.StationsHeartbeatService;
import com.pd.server.main.service.StationsInfoService;
import com.pd.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/lora")
public class LoraController {

    private static final Logger LOG = LoggerFactory.getLogger(LoraController.class);
    public static final String BUSINESS_NAME = "接收到的基站数据";

    @Resource
    private StationsHeartService stationsHeartService;
    @Resource
    private SendCommandService sendCommandService;
    @Resource
    private StationsInfoService stationsInfoService;
    @Resource
    private StationsHeartbeatService stationsHeartbeatService;

    @PostMapping("/stationsHeartbeat")
    public ResponseDto stationsHeartbeat(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        if(StringUtils.isEmpty(jsonObject.get("eui")) || StringUtils.isEmpty(jsonObject.get("status"))){
            responseDto.setMessage("请求参数错误");
            responseDto.setCode("4000");
            responseDto.setSuccess(false);
            return responseDto;
        }
        StationsHeartbeat stationsHeartbeat = new StationsHeartbeat();
        stationsHeartbeat.setId(UuidUtil.getShortUuid());
        stationsHeartbeat.setNodeId(jsonObject.getString("eui"));
        stationsHeartbeat.setStatus(jsonObject.getBoolean("status").toString());
        stationsHeartbeat.setCreateTime(new Date());
        stationsHeartbeatService.insertSelective(stationsHeartbeat);
        if("true".equals(stationsHeartbeat.getStatus())){
            StationsInfo stationsInfo = stationsInfoService.selectByPrimaryKey(jsonObject.getString("eui"));
            if(stationsInfo!=null && !StringUtils.isEmpty(stationsInfo.getId())){
                stationsInfo.setLastOnlineTime(new Date());
                stationsInfoService.updateByPrimaryKeySelective(stationsInfo);
            }
        }
        try {
            StationsWebSocketServer.sendInfo(JSONObject.toJSONString(stationsHeartbeat),null);
        } catch (IOException e) {
            LOG.error("stationsHeart推送消息失败");
        }
        return responseDto;
    }

    @PostMapping("/stationsInfo")
    public ResponseDto stationsInfo(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        if(StringUtils.isEmpty(jsonObject.get("name")) || StringUtils.isEmpty(jsonObject.get("num")) || StringUtils.isEmpty(jsonObject.get("id"))
                || StringUtils.isEmpty(jsonObject.get("lora_id")) || StringUtils.isEmpty(jsonObject.get("data"))){
            responseDto.setMessage("请求参数错误");
            responseDto.setCode("4000");
            responseDto.setSuccess(false);
            return responseDto;
        }
        StationsHeart stationsHeart = new StationsHeart();
        stationsHeart.setId(UuidUtil.getShortUuid());
        stationsHeart.setNodeId(jsonObject.getString("id"));
        stationsHeart.setNodeName(jsonObject.getString("name"));
        stationsHeart.setNodeNum(jsonObject.getString("num"));
        stationsHeart.setLoraId(jsonObject.getString("lora_id"));
        stationsHeart.setData(jsonObject.getString("data"));
        stationsHeart.setCreateTime(new Date());
        stationsHeartService.insertSelective(stationsHeart);
//        StationsInfo stationsInfo = stationsInfoService.selectByPrimaryKey(jsonObject.getString("id"));
//        if(stationsInfo!=null && !StringUtils.isEmpty(stationsInfo.getId())){
//            stationsInfo.setLastOnlineTime(new Date());
//            stationsInfoService.updateByPrimaryKeySelective(stationsInfo);
//        }
//        try {
//            StationsWebSocketServer.sendInfo(JSONObject.toJSONString(stationsHeart),null);
//        } catch (IOException e) {
//            LOG.error("stationsHeart推送消息失败");
//        }
        return responseDto;
    }

    @PostMapping("/sendCommand")
    public ResponseDto sendCommand(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        if(StringUtils.isEmpty(jsonObject.get("node_id")) || StringUtils.isEmpty(jsonObject.get("lora_id")) || StringUtils.isEmpty(jsonObject.get("serial_number"))
                || StringUtils.isEmpty(jsonObject.get("whole_sec")) || StringUtils.isEmpty(jsonObject.get("frac_sec"))|| StringUtils.isEmpty(jsonObject.get("up_chirp_cor"))
                || StringUtils.isEmpty(jsonObject.get("down_chirp_cor")) || StringUtils.isEmpty(jsonObject.get("ppm"))){
            responseDto.setMessage("请求参数错误");
            responseDto.setCode("4000");
            responseDto.setSuccess(false);
            return responseDto;
        }
        SendCommand sendCommand = new SendCommand();
        sendCommand.setId(UuidUtil.getShortUuid());
        sendCommand.setNodeId(jsonObject.getString("node_id"));
        sendCommand.setLoraId(jsonObject.getString("lora_id"));
        sendCommand.setSerialNumber(jsonObject.getString("serial_number"));
        sendCommand.setWholeSec(jsonObject.getString("whole_sec"));
        sendCommand.setFracSec(jsonObject.getDouble("frac_sec"));
        sendCommand.setUpChirpCor(jsonObject.getFloat("up_chirp_cor"));
        sendCommand.setDownChirpCor(jsonObject.getFloat("down_chirp_cor"));
        sendCommand.setPpm(jsonObject.getDouble("ppm"));
        sendCommandService.insertSelective(sendCommand);
        try {
            SendCommandWebSocketServer.sendInfo(JSONObject.toJSONString(sendCommand),null);
        } catch (IOException e) {
            LOG.error("SendCommand推送消息失败");
        }
        return responseDto;
    }
}
