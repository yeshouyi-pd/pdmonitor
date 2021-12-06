package com.pd.monitor.controller;


import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.EquipmentFile;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.domain.WaterQualityResultExample;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.EquipmentFileService;
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
        ca.andTpljLike("%png%");
        ca.andTpljNotLike("%predation%");
        example.setOrderByClause(" cjsj desc ");
        List<EquipmentFile> allList = equipmentFileService.listBylimit(example);
        if(!CollectionUtils.isEmpty(allList)){
            //结束时间
            EquipmentFile endEntity = allList.get(0);
            //开始时间
            EquipmentFile startEntity = allList.get(allList.size()-1);
            EquipmentFileExample example1 = new EquipmentFileExample();
            EquipmentFileExample.Criteria ca1 = example1.createCriteria();
            if(!StringUtils.isEmpty(list)&&list.size()>0){
                ca1.andDeptcodeIn(list);
            }
            if(!StringUtils.isEmpty(equipmentFileDto.getDeptcode())){
                ca1.andDeptcodeEqualTo(equipmentFileDto.getDeptcode());
            }
            if(!StringUtils.isEmpty(equipmentFileDto.getSbbh())){
                ca1.andSbbhEqualTo(equipmentFileDto.getSbbh());
            }
            if(!StringUtils.isEmpty(startEntity.getCjsj())){
                ca1.andCjsjGreaterThanOrEqualTo(DateUtil.getFormatDate(startEntity.getCjsj(),"yyyy-MM-dd HH:mm:ss"),"%Y-%m-%d %H:%i:%s");
            }
            if(!StringUtils.isEmpty(endEntity.getCjsj())){
                ca1.andCjsjLessThanOrEqualTo(DateUtil.getFormatDate(endEntity.getCjsj(),"yyyy-MM-dd HH:mm:ss"),"%Y-%m-%d %H:%i:%s");
            }
            ca1.andTpljLike("%png%");
            ca1.andTpljLike("%predation%");
            List<EquipmentFile> predationList = equipmentFileService.listAll(example1);
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("name", "捕食行为");
            resultMap.put("value",predationList.size());
            resultList.add(resultMap);
            Map<String,Object> resultMap1 = new HashMap<>();
            resultMap1.put("name", "江豚出现");
            resultMap1.put("value",allList.size()-predationList.size());
            resultList.add(resultMap1);
        }
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
                EquipmentFileExample equipmentFileExample = new EquipmentFileExample();
                EquipmentFileExample.Criteria  equipmentFileExampleca = equipmentFileExample.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    equipmentFileExampleca.andDeptcodeIn(listdept);
                }
                List<WelcomeKvDto>  list  =  equipmentFileService.getWarningDate(equipmentFileExample);
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
