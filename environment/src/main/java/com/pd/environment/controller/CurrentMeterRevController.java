package com.pd.environment.controller;

import com.pd.server.main.dto.CurrentMeterDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.CurrentMeterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/currentMeter")
public class CurrentMeterRevController {

    private static final Logger LOG = LoggerFactory.getLogger(CurrentMeterRevController.class);
    public static final String BUSINESS_NAME = "接收海流计数据";

    @Resource
    private CurrentMeterService currentMeterService;

    /**
     * 保存数据
     */
    @PostMapping("/getData")
    public ResponseDto getData(@RequestBody String str) {
        ResponseDto responseDto = new ResponseDto();
        try {
            CurrentMeterDto currentMeterDto = new CurrentMeterDto();
            String[] arr = str.replace("\\r","").replace("\\n","").split(",");
            currentMeterDto.setProductNumber(arr[0]);
            currentMeterDto.setSerialNumber(arr[1]);
            currentMeterDto.setAbsSpeed(arr[2]);
            currentMeterDto.setDirection(arr[3]);
            currentMeterDto.setNorth(arr[4]);
            currentMeterDto.setEast(arr[5]);
            currentMeterDto.setHeading(arr[6]);
            currentMeterDto.setTiltX(arr[7]);
            currentMeterDto.setTiltY(arr[8]);
            currentMeterDto.setSpStd(arr[9]);
            currentMeterDto.setStrength(arr[10]);
            currentMeterDto.setPingCount(arr[11]);
            currentMeterDto.setAbsTilt(arr[12]);
            currentMeterDto.setMaxTilt(arr[13]);
            currentMeterDto.setStdTilt(arr[14].trim());
            currentMeterDto.setCjsj(new Date());
            currentMeterDto.setBz("RPCDA4016");
            currentMeterService.save(currentMeterDto);
        }catch (Exception e){
            LOG.error("接收到的数据："+str+"====错误原因："+e.getMessage());
        }
        return responseDto;
    }



}
