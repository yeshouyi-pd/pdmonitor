package com.pd.monitor.controller;


import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.*;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/welcome")
public class WelcomeController extends BaseWxController{

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
    @Resource
    private EquipmentTyEventService equipmentTyEventService;
    @Resource
    private PointerSecondService pointerSecondService;
    @Resource
    private PointerDayService pointerDayService;
    @Resource
    private PredationNumService predationNumService;

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
        resultMap1.put("name", "侦测数据");
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

    @GetMapping("/getEventData")
    public ResponseDto getEventData() {
        ResponseDto responseDto = new ResponseDto();
        LoginUserDto user = getRequestHeader();
        if(null != user) {
            if (!StringUtils.isEmpty(user.getDeptcode())) {
                List<String> listdept = getUpdeptcode(user.getDeptcode());
                EquipmentTyEventExample example = new EquipmentTyEventExample();
                EquipmentTyEventExample.Criteria  ca = example.createCriteria();
                EquipmentTyEventExample example1 = new EquipmentTyEventExample();
                EquipmentTyEventExample.Criteria  ca1 = example1.createCriteria();
                if(!CollectionUtils.isEmpty(listdept)){
                    ca.andDeptcodeIn(listdept);
                    ca1.andDeptcodeIn(listdept);
                }
                ca.andRqEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
                List<EquipmentTyEvent> list = equipmentTyEventService.listByDp(example);
                if(CollectionUtils.isEmpty(list)){
                    ca1.andRqLessThan(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
                    ca1.andRqGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-7),"yyyy-MM-dd"));
                    list = equipmentTyEventService.listByDp(example1);
                }
                if(!CollectionUtils.isEmpty(list)){
                    list = list.stream().sorted(Comparator.comparing(EquipmentTyEvent::getKssj).reversed()).collect(Collectors.toList());
                }
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

    @GetMapping("/getDevice")
    public ResponseDto getDevice() {
        ResponseDto responseDto = new ResponseDto();
        WaterEquipmentExample example = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = example.createCriteria();
        example.setOrderByClause(" sblc ");
        List<WaterEquipment> list = waterEquipmentService.list(example);
        List<WaterEquipment> a4List = list.stream().filter(entity -> (entity.getDqzl().equals("A4")||entity.getDqzl().equals("A2"))).collect(Collectors.toList());
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("a4List",a4List);
        responseDto.setContent(map);
        return responseDto;
    }

    @PostMapping("/getSevenDayEvent")
    public ResponseDto getSevenDayEvent(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        EquipmentTyEventExample example = new EquipmentTyEventExample();
        EquipmentTyEventExample.Criteria ca = example.createCriteria();
        PredationNumExample preExample = new PredationNumExample();
        PredationNumExample.Criteria preCa = preExample.createCriteria();
        if(!StringUtils.isEmpty(jsonObject.get("type"))&&"zjglj".equals(jsonObject.get("type"))){
            ca.andSbbhEqualTo(jsonObject.getString("sbbh"));
            preCa.andSbbhEqualTo(jsonObject.getString("sbbh"));
        }else{
            LoginUserDto user = getRequestHeader();
            List<String> listdept = getUpdeptcode(user.getDeptcode());
            if(!CollectionUtils.isEmpty(listdept)){
                ca.andDeptcodeIn(listdept);
                preCa.andDeptcodeIn(listdept);
            }
        }
        ca.andRqGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-7),"yyyy-MM-dd"));
        ca.andRqLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        preCa.andCjsjGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-7),"yyyy-MM-dd"),"%Y-%m-%d");
        preCa.andCjsjLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"),"%Y-%m-%d");
        List<EquipmentTyEvent> list = new ArrayList<>();
        if(!StringUtils.isEmpty(jsonObject.get("type"))&&"zjglj".equals(jsonObject.get("type"))){
            WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
            waterEquipmentExample.createCriteria().andDqzlEqualTo("A1");
            List<String> sbbhs = waterEquipmentService.findSbbh(waterEquipmentExample);
            if(sbbhs.contains(jsonObject.getString("sbbh"))){
                list = predationNumService.listEventCount(preExample);
                list.stream().forEach(entity -> entity.setRq(entity.getRq().substring(0,10)));
            }else{
                list = equipmentTyEventService.listEventCount(example);
            }
        }else{
            list = equipmentTyEventService.listSumTs(example);
        }
        Map<String,Object> resultMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(list)){
            Map<String, List<EquipmentTyEvent>> map = list.stream().sorted(Comparator.comparing(EquipmentTyEvent::getRq)).collect(Collectors.groupingBy(EquipmentTyEvent::getSbbh));
            List<String> rqs= list.stream().sorted(Comparator.comparing(EquipmentTyEvent::getRq)).filter(Objects::nonNull).map(EquipmentTyEvent::getRq).distinct().collect(Collectors.toList());
            resultMap.put("map",map);
            resultMap.put("rqs",rqs);
        }
        responseDto.setContent(resultMap);
        return responseDto;
    }

    @GetMapping("/getThreeDayTs")
    public ResponseDto getThreeDayTs(){
        ResponseDto responseDto = new ResponseDto();
        EquipmentTyEventExample example = new EquipmentTyEventExample();
        EquipmentTyEventExample.Criteria ca = example.createCriteria();
        LoginUserDto user = getRequestHeader();
        List<String> listdept = getUpdeptcode(user.getDeptcode());
        if(!CollectionUtils.isEmpty(listdept)){
            ca.andDeptcodeIn(listdept);
        }
        ca.andRqGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-4),"yyyy-MM-dd"));
        ca.andRqLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        List<EquipmentTyEvent> list = equipmentTyEventService.listSumTs(example);
        Map<String,Object> resultMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(list)){
            Map<String, List<EquipmentTyEvent>> map = list.stream().sorted(Comparator.comparing(EquipmentTyEvent::getRq)).collect(Collectors.groupingBy(EquipmentTyEvent::getSbbh));
            List<String> rqs= list.stream().sorted(Comparator.comparing(EquipmentTyEvent::getRq)).filter(Objects::nonNull).map(EquipmentTyEvent::getRq).distinct().collect(Collectors.toList());
            resultMap.put("map",map);
            resultMap.put("rqs",rqs);
        }
        responseDto.setContent(resultMap);
        return responseDto;
    }

    @PostMapping("/getSwipeData")
    public ResponseDto getSwipeData(@RequestBody Map<String,String> map){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileTodayExample example = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(map.get("sbbh"))){
            ca.andSbbhEqualTo(map.get("sbbh"));
        }
        if(!StringUtils.isEmpty(map.get("kssj"))){
            ca.andCjsjGreaterThanOrEqualTo(DateUtil.toDate(map.get("kssj"),"yyyy-MM-dd HH:mm:ss"));
        }
        if(!StringUtils.isEmpty(map.get("jssj"))){
            ca.andCjsjLessThanOrEqualTo(DateUtil.toDate(map.get("jssj"),"yyyy-MM-dd HH:mm:ss"));
        }
        ca.andWjlxEqualTo("1");
        List<EquipmentFileToday> list = equipmentFileTodayService.listAllDw(example);
        responseDto.setContent(list);
        return responseDto;
    }

    @PostMapping("/getVideoData")
    public ResponseDto getVideoData(@RequestBody Map<String,String> map){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileTodayExample example = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(map.get("sbbh"))){
            ca.andSbbhEqualTo(map.get("sbbh"));
        }
        if(!StringUtils.isEmpty(map.get("kssj"))){
            ca.andCjsjGreaterThanOrEqualTo(DateUtil.toDate(map.get("kssj"),"yyyy-MM-dd HH:mm:ss"));
        }
        if(!StringUtils.isEmpty(map.get("jssj"))){
            ca.andCjsjLessThanOrEqualTo(DateUtil.toDate(map.get("jssj"),"yyyy-MM-dd HH:mm:ss"));
        }
        ca.andWjlxEqualTo("4");
        List<EquipmentFileToday> list = equipmentFileTodayService.listAllDw(example);
        responseDto.setContent(list);
        return responseDto;
    }

    @PostMapping("/getPointerSecond")
    public ResponseDto getPointerSecond(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        PointerSecondExample example = new PointerSecondExample();
        PointerSecondExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(jsonObject.get("type"))&&"zjglj".equals(jsonObject.get("type"))){
            ca.andSmEqualTo(jsonObject.getString("sbbh"));
        }
        PointerSecond entity = pointerSecondService.selectByDp(example);
        responseDto.setContent(entity);
        return responseDto;
    }

    @PostMapping("/getPointerDay")
    public ResponseDto getPointerDay(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        PointerDayExample example = new PointerDayExample();
        PointerDayExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(jsonObject.get("type"))&&"zjglj".equals(jsonObject.get("type"))){
            ca.andSmEqualTo(jsonObject.getString("sbbh"));
        }
        PointerDay entity = pointerDayService.selectByDp(example);
        responseDto.setContent(entity);
        return responseDto;
    }
}
