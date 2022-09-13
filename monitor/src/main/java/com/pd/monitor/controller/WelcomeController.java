package com.pd.monitor.controller;


import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.main.service.EquipmentFileTodayService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.main.service.WaterQualityResultService;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/welcome")
public class WelcomeController  extends BaseWxController{

    private static final Logger LOG = LoggerFactory.getLogger(WelcomeController.class);
    public static final String BUSINESS_NAME = "首页";

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
     * 大屏 捕食行为
     */
    @PostMapping("/predationStatistics")
    public ResponseDto predationStatistics(@RequestBody EquipmentFileDto equipmentFileDto){
        ResponseDto responseDto = new ResponseDto();
        List<Map<String,Object>> resultList = new ArrayList<>();
        LoginUserDto user = getRequestHeader();
        List<String> list = getUpdeptcode(user.getDeptcode());
        //查询出最近100条出现次数
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(list)&&list.size()>0){
            ca.andDeptcodeIn(list);
        }
        if(!StringUtils.isEmpty(equipmentFileDto.getDeptcode())){
            ca.andDeptcodeEqualTo(equipmentFileDto.getDeptcode());
        }
        if(!StringUtils.isEmpty(equipmentFileDto.getSbbh())){
            ca.andSbbhEqualTo(equipmentFileDto.getSbbh());
        }
        if(!StringUtils.isEmpty(equipmentFileDto.getXmbh())){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(equipmentFileDto.getXmbh()))){
                ca.andSbbhIn(user.getXmbhsbsns().get(equipmentFileDto.getXmbh()));
            }
        }
        ca.andTpljLike("%png%");
        example.setOrderByClause(" cjsj desc ");
        List<EquipmentFile> allList = equipmentFileService.listBylimit(example);
        List<EquipmentFile> predationList = allList.stream().filter(entity -> entity.getTplj().contains("predation")).collect(Collectors.toList());
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("name", "捕食行为");
        resultMap.put("value",predationList.size());
        resultList.add(resultMap);
        Map<String,Object> resultMap1 = new HashMap<>();
        resultMap1.put("name", "江豚出现");
        resultMap1.put("value",allList.size());
        resultList.add(resultMap1);
        responseDto.setContent(resultList);
        return responseDto;
    }

    /**
     * 计算结果百分比，保留2位小数
     *
     * @param v1 除数
     * @param v2 被除数
     * @return
     */
    private static String calculateResultOfPercent(double v1, double v2) {
        if (v2 == 0) {
            return "0%";
        }

        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(v1 / v2);
    }

    /**
     *  welcome 水质实时数据
     */
    @GetMapping("/getLatestDate")
    public ResponseDto getLatestDate() {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user   = getRequestHeader();
        if(null != user) {
            if (!StringUtils.isEmpty(user.getDeptcode())) {
                List<String> listdept = getUpdeptcode(user.getDeptcode());
                WaterQualityResultExample waterQualityResultExample = new WaterQualityResultExample();
                WaterQualityResultExample.Criteria  waterQualityResultca = waterQualityResultExample.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    waterQualityResultca.andSm1In(listdept);
                }
                List<WaterQualityResultDto>  list  =  waterQualityResultService.getLatestDate(waterQualityResultExample);
                responseDto.setContent(list);
            }
        }

        return responseDto;
    }


    /**
     *  welcome 水质实时数据
     */
    @GetMapping("/getLatestDate/{xmbh}")
    public ResponseDto getLatestDate(@PathVariable String xmbh) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user   = getRequestHeader();
        if(null != user) {
            if (!StringUtils.isEmpty(user.getDeptcode())) {
                List<String> listdept = getUpdeptcode(user.getDeptcode());
                WaterQualityResultExample waterQualityResultExample = new WaterQualityResultExample();
                WaterQualityResultExample.Criteria  waterQualityResultca = waterQualityResultExample.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    waterQualityResultca.andSm1In(listdept);
                }
                if(!StringUtils.isEmpty(xmbh)){
                    if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(xmbh))){
                        waterQualityResultca.andIpIn(user.getXmbhsbsns().get(xmbh));
                    }
                }
                List<WaterQualityResultDto>  list  =  waterQualityResultService.getLatestDate(waterQualityResultExample);
                responseDto.setContent(list);
            }
        }

        return responseDto;
    }

    /**
     *  welcome 实时越限警告
     */
    @GetMapping("/getWarningDate")
    public ResponseDto getWarningDate() {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user   = getRequestHeader();
        if(null != user) {
            if (!StringUtils.isEmpty(user.getDeptcode())) {
                List<String> listdept = getUpdeptcode(user.getDeptcode());
                EquipmentFileTodayExample todayExample = new EquipmentFileTodayExample();
                EquipmentFileTodayExample.Criteria  todayCa = todayExample.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    todayCa.andDeptcodeIn(listdept);
                }
                todayCa.andTxtlxEqualTo("1");
                List<WelcomeKvDto>  list  =  equipmentFileTodayService.getWarningDate(todayExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;
    }

    /**
     *  welcome 实时越限警告
     */
    @GetMapping("/getWarningDate/{xmbh}")
    public ResponseDto getWarningDate(@PathVariable String xmbh) {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        if(null != user) {
            if (!StringUtils.isEmpty(user.getDeptcode())) {
                List<String> listdept = getUpdeptcode(user.getDeptcode());
                EquipmentFileExample equipmentFileExample = new EquipmentFileExample();
                EquipmentFileExample.Criteria  equipmentFileExampleca = equipmentFileExample.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    equipmentFileExampleca.andDeptcodeIn(listdept);
                }
                if(!StringUtils.isEmpty(xmbh)){
                    if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(xmbh))){
                        equipmentFileExampleca.andSbbhIn(user.getXmbhsbsns().get(xmbh));
                    }
                }
                List<WelcomeKvDto>  list  =  equipmentFileService.getWarningDate(equipmentFileExample);
                responseDto.setContent(list);
            }
        }
        return responseDto;
    }


    /**
     *  welcome 在线率饼状图
     *  设备状态1正常2离线3设备故障
     */
    @GetMapping("/getPieChart")
    public ResponseDto getPieChart() {
        ResponseDto responseDto = new ResponseDto();
        WaterEquipmentExample example = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = example.createCriteria();
        LoginUserDto user = getRequestHeader();
        List<String> listdept = getUpdeptcode(user.getDeptcode());
        if(!CollectionUtils.isEmpty(listdept)){
            ca.andDeptcodeIn(listdept);
        }
        List<PieChartDto>  list  =  waterEquipmentService.getPieChart(example);
        List<PieChartDto> newlit = new ArrayList<PieChartDto>();
        if(!CollectionUtils.isEmpty(list)){
            for(PieChartDto  vo :list){
                PieChartDto newvo = new PieChartDto();
                if("1".equals(vo.getLabel())){//成功
                    newvo.setData(vo.getData());
                    newvo.setLabel("正常");
                    newvo.setColor("#68BC31");
                }else if ("2".equals(vo.getLabel())){//离线
                    newvo.setData(vo.getData());
                    newvo.setLabel("离线");
                    newvo.setColor("#DA5430");
                }else if ("3".equals(vo.getLabel())){//故障
                    newvo.setData(vo.getData());
                    newvo.setLabel("故障");
                    newvo.setColor("#FEE074");
                }
                newlit.add(newvo);
            }
        }
        responseDto.setContent(newlit);
        return responseDto;
    }

    /**
     *  welcome 在线率饼状图
     *  设备状态1正常2离线3设备故障
     */
    @GetMapping("/getPieChart/{xmbh}")
    public ResponseDto getPieChart(@PathVariable String xmbh) {
        ResponseDto responseDto = new ResponseDto();
        WaterEquipmentExample example = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = example.createCriteria();
        LoginUserDto user = getRequestHeader();
        List<String> listdept = getUpdeptcode(user.getDeptcode());
        if(!CollectionUtils.isEmpty(listdept)){
            ca.andDeptcodeIn(listdept);
        }
        if(!StringUtils.isEmpty(xmbh)){
            if(!CollectionUtils.isEmpty(user.getXmbhsbsns().get(xmbh))){
                ca.andSbsnIn(user.getXmbhsbsns().get(xmbh));
            }
        }
        List<PieChartDto>  list  =  waterEquipmentService.getPieChart(example);
        List<PieChartDto> newlit = new ArrayList<PieChartDto>();
        if(!CollectionUtils.isEmpty(list)){
            for(PieChartDto  vo :list){
                PieChartDto newvo = new PieChartDto();
                if("1".equals(vo.getLabel())){//成功
                    newvo.setData(vo.getData());
                    newvo.setLabel("正常");
                    newvo.setColor("#68BC31");
                }else if ("2".equals(vo.getLabel())){//离线
                    newvo.setData(vo.getData());
                    newvo.setLabel("离线");
                    newvo.setColor("#DA5430");
                }else if ("3".equals(vo.getLabel())){//故障
                    newvo.setData(vo.getData());
                    newvo.setLabel("故障");
                    newvo.setColor("#FEE074");
                }
                newlit.add(newvo);
            }
        }
        responseDto.setContent(newlit);
        return responseDto;
    }




}
