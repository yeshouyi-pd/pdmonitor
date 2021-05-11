package com.pd.monitor.controller;


import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.domain.WaterQualityResultExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.main.service.WaterQualityResultService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mobile")
public class MobileController  extends BaseWxController {


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
     * 水环境数据
     * @return
     */
    @GetMapping("/getAlljcsjByDept")
    public ResponseDto getAlljcsjByDept(){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user   = getRequestHeader();
        if(null != user){
            if(!StringUtils.isEmpty(user.getDeptcode())){
               List<String > listdept   =  getUpdeptcode(user.getDeptcode());
                WaterQualityResultExample waterQualityResultExample = new WaterQualityResultExample();
                WaterQualityResultExample.Criteria  waterQualityResultca = waterQualityResultExample.createCriteria();
                waterQualityResultca.andSm1In(listdept);
                List<KvIntDto>  list   = waterQualityResultService.getAlljcsjByDept(waterQualityResultExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;

    }


    /**
     * 江豚预警
     * @return
     */
    @GetMapping("/getAlljtByDept")
    public ResponseDto getAlljtByDept(){
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user   = getRequestHeader();
        if(null != user){
            if(!StringUtils.isEmpty(user.getDeptcode())){
                List<String > listdept   =  getUpdeptcode(user.getDeptcode());
                EquipmentFileExample equipmentFileExample = new EquipmentFileExample();
                EquipmentFileExample.Criteria  equipmentFileExampleca = equipmentFileExample.createCriteria();
                equipmentFileExampleca.andDeptcodeIn(listdept);
                List<KvIntDto>  list   = equipmentFileService.getAlljcsjByDept(equipmentFileExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;

    }




    /**
     * 获取当前部门的所有数据
     * @return
     */
    @PostMapping("/getthisDeptjxsj")
    public ResponseDto getthisDeptjxsj(@RequestBody WaterQualityResultDto waterQualityResultDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != waterQualityResultDto){
            if(!StringUtils.isEmpty(waterQualityResultDto.getSm1())){
                WaterQualityResultExample waterQualityResultExample = new WaterQualityResultExample();
                WaterQualityResultExample.Criteria  waterQualityResultca = waterQualityResultExample.createCriteria();
                waterQualityResultca.andSm1EqualTo(waterQualityResultDto.getSm1());
                List<WaterQualityResultDto>  list   = waterQualityResultService.getthisDeptjxsj(waterQualityResultExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;

    }


}
