package com.pd.system.controller.admin;

import com.pd.server.main.dto.ResourceDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.system.controller.conf.MqttClientTestXH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/realtimeData")
public class RealtimeDataController {
    private static final Logger LOG = LoggerFactory.getLogger(RealtimeDataController.class);
    public static final String BUSINESS_NAME = "获取实时数据";

    /**
     * 获取实时数据
     */
    @GetMapping("/getRealtimeData")
    public ResponseDto getRealtimeData() {
        ResponseDto responseDto = new ResponseDto();
        try {
            MqttClientTestXH testXH = MqttClientTestXH.getInstance("tcp://47.244.23.44", "subClient", "benben", "123456");
            testXH.setType("temperature");
            byte[] message = {0x01,0x04,0x00,0x00,0x00,0x01,0x31, (byte) 0xCA};
            testXH.publishMessage(message, 1);
            Thread.sleep(10000);

            testXH.setType("waterDepth");
            byte[] message2 = {0x34,0x03,0x00,0x15,0x00,0x02, (byte) 0xD0, (byte) 0x6A};
            testXH.publishMessage(message2, 1);
            Thread.sleep(10000);

            testXH.setType("waterVelocity");
            byte[] message3 = {0x34,0x03,0x00,0x0F,0x00,0x02, (byte) 0xF1, (byte) 0xAD};
            testXH.publishMessage(message3, 1);
            Thread.sleep(10000);
            responseDto.setMessage("发送成功！");
            testXH.close();
        }catch (Exception e){
            responseDto.setSuccess(false);
            responseDto.setMessage("发送失败，"+e.getMessage());
        }
        return responseDto;
    }
}
