package com.pd.monitor.controller;


import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.main.service.EquipmentFileTodayService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.main.service.WaterQualityResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/mobile")
public class MobileController  extends BaseWxController {
    private static final Logger LOG = LoggerFactory.getLogger(MobileController.class);
    public static final String BUSINESS_NAME = "手机端";

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
    @Resource
    private EquipmentFileTodayService equipmentFileTodayService;


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
                if(!CollectionUtils.isEmpty(listdept)){
                    waterQualityResultca.andSm1In(listdept);
                }
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
                EquipmentFileTodayExample example = new EquipmentFileTodayExample();
                EquipmentFileTodayExample.Criteria  ca = example.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    ca.andDeptcodeIn(listdept);
                }
                List<KvIntDto>  list   = equipmentFileTodayService.getAlljcsjByDept(example);
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
                Map<String,String> map  =   new LinkedHashMap<String,String>();
                if(!CollectionUtils.isEmpty(list)){
                    for(WaterQualityResultDto vo :list){
                        map.put(vo.getIp(),vo.getSbmc());
                    }
                }
                Map<String ,Object> remap = new LinkedHashMap<String ,Object>();
                remap.put("list" ,list);
                remap.put("map", map);
                responseDto.setContent(remap);

            }
        }
        return responseDto;

    }



    /**
     * 获取当前部门的所有数据 ---江豚
     * @return
     */
    @PostMapping("/getthisDeptjxsjJT")
    public ResponseDto getthisDeptjxsjJT(@RequestBody EquipmentFileDto equipmentFileDto){
        ResponseDto responseDto = new ResponseDto();

        if(null != equipmentFileDto){
            if(!StringUtils.isEmpty(equipmentFileDto.getDeptcode())){
                EquipmentFileExample equipmentFileExample = new EquipmentFileExample();
                EquipmentFileExample.Criteria  equipmentFileca = equipmentFileExample.createCriteria();
                equipmentFileca.andDeptcodeEqualTo(equipmentFileDto.getDeptcode());
                List<EquipmentFileDto>  list   = equipmentFileService.getthisDeptjxsjJT(equipmentFileExample);
                List<EquipmentFileDto>  listenw   = new ArrayList<EquipmentFileDto>();
                Map<String,String> map  =   new LinkedHashMap<String,String>();
                if(!CollectionUtils.isEmpty(list)){
                    for(EquipmentFileDto vo :list){
                        map.put(vo.getSbbh(),vo.getSbmc());
                        if(!StringUtils.isEmpty(vo.getTplj())){
                            String  wav = vo.getTplj().replaceAll("txt","wav");
                            PlayDto p = new PlayDto();
                            p.setSrc(wav);
                            vo.setPlayDto(p);
                            listenw.add(vo);

                        }

                    }
                }
                Map<String ,Object> remap = new LinkedHashMap<String ,Object>();
                remap.put("list" ,listenw);
                remap.put("map", map);
                responseDto.setContent(remap);

            }
        }
        return responseDto;

    }


}
