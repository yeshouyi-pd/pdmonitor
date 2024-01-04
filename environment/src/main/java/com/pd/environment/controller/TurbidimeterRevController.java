package com.pd.environment.controller;

import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.TurbidityDto;
import com.pd.server.main.service.TurbidityService;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/turbidimeter")
public class TurbidimeterRevController {

    private static final Logger LOG = LoggerFactory.getLogger(MeteorologicalDataRevController.class);
    public static final String BUSINESS_NAME = "接收温盐深浊度仪数据";

    @Resource
    private TurbidityService turbidityService;

    /**
     * 保存数据
     */
    @PostMapping("/getData")
    public ResponseDto getData(@RequestBody String str) {
        ResponseDto responseDto = new ResponseDto();
        try {
            TurbidityDto turbidityDto = new TurbidityDto();
            String[] arr = str.trim().split(",");
            if(arr.length==9&&"$OBS".equals(arr[0])){
                turbidityDto.setDateTime(DateUtil.toDate(arr[1].substring(0,arr[1].lastIndexOf(".")),"yyyy-MM-dd HH:mm:ss"));
                turbidityDto.setTurbidityH(arr[2]);
                turbidityDto.setTuribidityL(arr[3]);
                turbidityDto.setDepth(arr[4]);
                turbidityDto.setTemperature(arr[5]);
                turbidityDto.setConductivity(arr[6]);
                turbidityDto.setSalinity(arr[7]);
                turbidityDto.setBatVolt(arr[8]);
                turbidityDto.setBz("RPCDA4000");//设备编号
                turbidityService.save(turbidityDto);
            }else {
                LOG.error("接收到的错误数据："+str);
            }
        }catch (Exception e){
            LOG.error("接收到的数据："+str+"====错误原因："+e.getMessage());
        }
        return responseDto;
    }
}
