package com.pd.monitor.controller;


import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.main.service.WaterQualityResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/welcome")
public class WelcomeController  extends BaseWxController{

    private static final Logger LOG = LoggerFactory.getLogger(WelcomeController.class);


    /**
     * 水值监测数据
     */
    @Resource
    private WaterQualityResultService waterQualityResultService;
    /**
     * 水质告警
     */
    @Resource
    private EquipmentFileService equipmentFileService;
    /**
     * 设备信息
     */
    @Resource
    private WaterEquipmentService waterEquipmentService;





    /**
     *  welcome 水质实时数据
     */
    @GetMapping("/getLatestDate")
    public ResponseDto getLatestDate() {
        ResponseDto responseDto = new ResponseDto();
        List<WaterQualityResultDto>  list  =  waterQualityResultService.getLatestDate();
        responseDto.setContent(list);
        return responseDto;
    }

    /**
     *  welcome 实时越限警告
     */
    @GetMapping("/getWarningDate")
    public ResponseDto getWarningDate() {
        ResponseDto responseDto = new ResponseDto();
        List<WelcomeKvDto>  list  =  equipmentFileService.getWarningDate();
        responseDto.setContent(list);
        return responseDto;
    }








}
