package com.pd.monitor.controller;


import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.dto.basewx.my.PredationNumDwDto;
import com.pd.server.main.service.*;
import com.pd.server.util.DateUtil;
import com.pd.server.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.ParseException;
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
    private AttrService attrService;
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
    @Resource
    private EquipmentFileEventService equipmentFileEventService;
    @Resource
    private VideoEventService videoEventService;

    @PostMapping("/getExplainVideoEvent")
    public ResponseDto getExplainVideoEvent(@RequestBody VideoEventDto videoEventDto){
        ResponseDto responseDto = new ResponseDto();
        String limitNumStr = attrService.findByAttrKey("limitNum");
        Integer limitNum = 10;
        if(!StringUtils.isEmpty(limitNumStr)){
            limitNum = Integer.parseInt(limitNumStr);
        }
        VideoEventExample example = new VideoEventExample();
        VideoEventExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(videoEventDto.getSbbh())){
            ca.andSbbhIn(Arrays.asList(videoEventDto.getSbbh().split(",")));
        }
        if(!StringUtils.isEmpty(videoEventDto.getWjmc())){
            ca.andWjmcEqualTo(videoEventDto.getWjmc());
        }
        if(!StringUtils.isEmpty(videoEventDto.getRq())){
            ca.andRqEqualTo(videoEventDto.getRq());
        }
        if(!StringUtils.isEmpty(videoEventDto.getStime())){
            ca.andRqGreaterThanOrEqualTo(videoEventDto.getStime());
        }
        if(!StringUtils.isEmpty(videoEventDto.getEtime())){
            ca.andRqLessThanOrEqualTo(videoEventDto.getEtime());
        }
        if(!StringUtils.isEmpty(videoEventDto.getSfysp())){
            ca.andSfyspEqualTo(videoEventDto.getSfysp());
        }
        List<VideoEvent> lists = videoEventService.selectByDp(example, limitNum);
        responseDto.setContent(lists);
        return responseDto;
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
                WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
                waterEquipmentExample.createCriteria().andDqzlEqualTo("A4");
                List<String> a4SbbhList = waterEquipmentService.findSbbh(waterEquipmentExample);
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
                List<EquipmentTyEvent> list = equipmentTyEventService.listByDp(example,String.join(",",a4SbbhList));
                if(CollectionUtils.isEmpty(list)){
                    ca1.andRqLessThan(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
                    ca1.andRqGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-7),"yyyy-MM-dd"));
                    list = equipmentTyEventService.listByDp(example1,String.join(",",a4SbbhList));
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
                //事件次数
                EquipmentFileTodayExample example = new EquipmentFileTodayExample();
                EquipmentFileTodayExample.Criteria ca = example.createCriteria();
                ca.andRqEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
                ca.andTxtlxEqualTo("1");
                List<AlarmNumbersDto> lists = equipmentFileTodayService.statisticsAlarmNums(example);
                Map<String, List<AlarmNumbersDto>> mapList = lists.stream().collect(Collectors.groupingBy(AlarmNumbersDto::getSbbh));
                Map<String, Integer> eventSbbhMap = new HashMap<>();
                for(String key : mapList.keySet()) {
                    List<AlarmNumbersDto> listsTemp = mapList.get(key);
                    List<AlarmNumbersDto> resultList = new ArrayList<>();
                    if (!CollectionUtils.isEmpty(listsTemp)) {
                        AlarmNumbersDto firstEntity = listsTemp.get(0);
                        String curDateStr = firstEntity.getFz();
                        //String lastDateStr = laterThreeMinute(curDateStr);
                        Integer bjsl = firstEntity.getAlarmNum();
                        for (int i = 1; i < listsTemp.size(); i++) {
                            AlarmNumbersDto entity = listsTemp.get(i);
                            AlarmNumbersDto beforeEntity = listsTemp.get(i - 1);
                            String beforeDateStr = beforeEntity.getFz();
                            String nextDateStr = entity.getFz();
                            if (entity.getSbbh().equals(firstEntity.getSbbh())) {
                                if (isOverThreeMinute(beforeDateStr, nextDateStr)) {
                                    bjsl = bjsl + entity.getAlarmNum();
                                } else {
                                    AlarmNumbersDto result = new AlarmNumbersDto();
                                    result.setDeptcode(entity.getDeptcode());
                                    result.setSbbh(entity.getSbbh());
                                    result.setBjsj(curDateStr + " 至 " + beforeDateStr);
                                    result.setAlarmNum(bjsl);
                                    resultList.add(result);
                                    firstEntity = entity;
                                    curDateStr = firstEntity.getFz();
                                    bjsl = firstEntity.getAlarmNum();
                                }
                            } else {
                                AlarmNumbersDto result = new AlarmNumbersDto();
                                result.setDeptcode(firstEntity.getDeptcode());
                                result.setSbbh(firstEntity.getSbbh());
                                result.setBjsj(curDateStr + " 至 " + beforeDateStr);
                                result.setAlarmNum(bjsl);
                                resultList.add(result);
                                firstEntity = entity;
                                curDateStr = firstEntity.getFz();
                                bjsl = firstEntity.getAlarmNum();
                            }
                            if (i == listsTemp.size() - 1) {
                                AlarmNumbersDto result = new AlarmNumbersDto();
                                result.setDeptcode(entity.getDeptcode());
                                result.setSbbh(entity.getSbbh());
                                result.setBjsj(curDateStr + " 至 " + nextDateStr);
                                result.setAlarmNum(bjsl);
                                resultList.add(result);
                            }
                        }
                        if (listsTemp.size() == 1) {
                            AlarmNumbersDto result = new AlarmNumbersDto();
                            result.setDeptcode(firstEntity.getDeptcode());
                            result.setSbbh(firstEntity.getSbbh());
                            result.setBjsj(curDateStr + " 至 " + curDateStr);
                            result.setAlarmNum(bjsl);
                            resultList.add(result);
                        }
                    }
                    eventSbbhMap.put(key,resultList.size());
                }
                Map<String,Object> returnMap = new HashMap<>();
                returnMap.put("zcsj",list);//侦测次数数据
                returnMap.put("jlsj",eventSbbhMap);//聚类次数数据
                responseDto.setContent(returnMap);
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
                //事件次数
                EquipmentFileTodayExample example = new EquipmentFileTodayExample();
                EquipmentFileTodayExample.Criteria ca = example.createCriteria();
                ca.andRqEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
                ca.andTxtlxEqualTo("1");
                List<AlarmNumbersDto> lists = equipmentFileTodayService.statisticsAlarmNums(example);
                Map<String, List<AlarmNumbersDto>> mapList = lists.stream().collect(Collectors.groupingBy(AlarmNumbersDto::getSbbh));
                Map<String, Integer> eventSbbhMap = new HashMap<>();
                for(String key : mapList.keySet()) {
                    List<AlarmNumbersDto> listsTemp = mapList.get(key);
                    List<AlarmNumbersDto> resultList = new ArrayList<>();
                    if (!CollectionUtils.isEmpty(listsTemp)) {
                        AlarmNumbersDto firstEntity = listsTemp.get(0);
                        String curDateStr = firstEntity.getFz();
                        //String lastDateStr = laterThreeMinute(curDateStr);
                        Integer bjsl = firstEntity.getAlarmNum();
                        for (int i = 1; i < listsTemp.size(); i++) {
                            AlarmNumbersDto entity = listsTemp.get(i);
                            AlarmNumbersDto beforeEntity = listsTemp.get(i - 1);
                            String beforeDateStr = beforeEntity.getFz();
                            String nextDateStr = entity.getFz();
                            if (entity.getSbbh().equals(firstEntity.getSbbh())) {
                                if (isOverThreeMinute(beforeDateStr, nextDateStr)) {
                                    bjsl = bjsl + entity.getAlarmNum();
                                } else {
                                    AlarmNumbersDto result = new AlarmNumbersDto();
                                    result.setDeptcode(entity.getDeptcode());
                                    result.setSbbh(entity.getSbbh());
                                    result.setBjsj(curDateStr + " 至 " + beforeDateStr);
                                    result.setAlarmNum(bjsl);
                                    resultList.add(result);
                                    firstEntity = entity;
                                    curDateStr = firstEntity.getFz();
                                    bjsl = firstEntity.getAlarmNum();
                                }
                            } else {
                                AlarmNumbersDto result = new AlarmNumbersDto();
                                result.setDeptcode(firstEntity.getDeptcode());
                                result.setSbbh(firstEntity.getSbbh());
                                result.setBjsj(curDateStr + " 至 " + beforeDateStr);
                                result.setAlarmNum(bjsl);
                                resultList.add(result);
                                firstEntity = entity;
                                curDateStr = firstEntity.getFz();
                                bjsl = firstEntity.getAlarmNum();
                            }
                            if (i == listsTemp.size() - 1) {
                                AlarmNumbersDto result = new AlarmNumbersDto();
                                result.setDeptcode(entity.getDeptcode());
                                result.setSbbh(entity.getSbbh());
                                result.setBjsj(curDateStr + " 至 " + nextDateStr);
                                result.setAlarmNum(bjsl);
                                resultList.add(result);
                            }
                        }
                        if (listsTemp.size() == 1) {
                            AlarmNumbersDto result = new AlarmNumbersDto();
                            result.setDeptcode(firstEntity.getDeptcode());
                            result.setSbbh(firstEntity.getSbbh());
                            result.setBjsj(curDateStr + " 至 " + curDateStr);
                            result.setAlarmNum(bjsl);
                            resultList.add(result);
                        }
                    }
                    eventSbbhMap.put(key,resultList.size());
                }
                Map<String,Object> returnMap = new HashMap<>();
                returnMap.put("zcsj",list);//侦测次数数据
                returnMap.put("jlsj",eventSbbhMap);//聚类次数数据
                responseDto.setContent(returnMap);
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
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
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
        List<EquipmentFile> list = equipmentFileService.listAllDw(example);
        responseDto.setContent(list);
        return responseDto;
    }

    @PostMapping("/getVideoData")
    public ResponseDto getVideoData(@RequestBody Map<String,String> map){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
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
        List<EquipmentFile> list = equipmentFileService.listAllDw(example);
        responseDto.setContent(list);
        return responseDto;
    }

    @GetMapping ("/getLastVedio")
    public ResponseDto getLastVedio(){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFile equipmentFile = equipmentFileService.selectVideoDp();
        responseDto.setContent(equipmentFile);
        return responseDto;
    }

    @GetMapping("/getVideoDataNew")
    public ResponseDto getVideoDataNew(){
        ResponseDto responseDto = new ResponseDto();
        //EquipmentFile equipmentFile = equipmentFileService.selectVideoDp();
        EquipmentFileEvent equipmentFileEvent = equipmentFileEventService.selectByDp();
        EquipmentFile equipmentFile = equipmentFileService.selectByPrimaryKey(equipmentFileEvent.getEquipmentFileId());
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        ca.andWjmcEqualTo(equipmentFile.getWjmc());
        ca.andWjlxEqualTo("4");
        List<EquipmentFile> lists = equipmentFileService.listAll(example);
        responseDto.setContent(lists!=null&&lists.size()>0?lists.get(0):null);
        return responseDto;
    }

    @PostMapping("/getPointerSecond")
    public ResponseDto getPointerSecond(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        PointerSecondExample example = new PointerSecondExample();
        PointerSecondExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(jsonObject.get("type"))&&"zjglj".equals(jsonObject.get("type"))&&!StringUtils.isEmpty(jsonObject.get("sbbh"))){
            ca.andSmEqualTo(jsonObject.getString("sbbh"));
        }
        ca.andBz1EqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
        PointerSecond entity = pointerSecondService.selectByDp(example);
        responseDto.setContent(entity);
        return responseDto;
    }

    @PostMapping("/getPointerDay")
    public ResponseDto getPointerDay(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        PointerDayExample example = new PointerDayExample();
        PointerDayExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(jsonObject.get("type"))&&"zjglj".equals(jsonObject.get("type"))&&!StringUtils.isEmpty(jsonObject.get("sbbh"))){
            ca.andSmEqualTo(jsonObject.getString("sbbh"));
        }
        ca.andBz1EqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
        PointerDay entity = pointerDayService.selectByDp(example);
        responseDto.setContent(entity);
        return responseDto;
    }

    @PostMapping("/statisticsAlarmNumsByTimeSum")
    public ResponseDto statisticsAlarmNumsByTimeSum(@RequestBody JSONObject jsonObject) throws ParseException {
        ResponseDto responseDto = new ResponseDto();
        PredationNumExample example = new PredationNumExample();
        PredationNumExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(jsonObject.get("sbbh"))){
            ca.andSbbhEqualTo(jsonObject.getString("sbbh"));
        }
        if(!StringUtils.isEmpty(jsonObject.get("topDayType"))&&"1".equals(jsonObject.get("topDayType"))){
            //上个月
            ca.andCjsjGreaterThanOrEqualTo(DateUtils.getStrToDate(DateUtils.getBeginDayStrOfSomeMonth(1)));
            ca.andCjsjLessThanOrEqualTo(DateUtils.getStrToDate(DateUtils.getEndDayStrOfSomeMonth(1)));
        }else if(!StringUtils.isEmpty(jsonObject.get("topDayType"))&&"2".equals(jsonObject.get("topDayType"))){
            //本月
            ca.andCjsjGreaterThanOrEqualTo(DateUtils.getStrToDate(DateUtils.getBeginDayStrOfSomeMonth(0)));
            ca.andCjsjLessThanOrEqualTo(DateUtils.getStrToDate(DateUtils.getEndDayStrOfSomeMonth(0)));
        }else if(!StringUtils.isEmpty(jsonObject.get("topDayType"))&&"3".equals(jsonObject.get("topDayType"))){
            //本周
            ca.andCjsjGreaterThanOrEqualTo(DateUtils.getStrToDate(DateUtils.getBeginWeek()));
            ca.andCjsjLessThanOrEqualTo(DateUtils.getStrToDate(DateUtils.getEndWeek()));
        }
        List<PredationNumDwDto> lists = predationNumService.statisticsExample(example);
        Map<String,Object> map = new HashMap<String, Object>();
        System.out.println(lists.get(0));
        map.put("num",!CollectionUtils.isEmpty(lists)&&!StringUtils.isEmpty(lists.get(0))&&!StringUtils.isEmpty(lists.get(0).getCxcs())?lists.get(0).getCxcs():0);
        map.put("nnm",!CollectionUtils.isEmpty(lists)&&!StringUtils.isEmpty(lists.get(0))&&!StringUtils.isEmpty(lists.get(0).getSjcs())?lists.get(0).getSjcs():0);
        map.put("bnum",!CollectionUtils.isEmpty(lists)&&!StringUtils.isEmpty(lists.get(0))&&!StringUtils.isEmpty(lists.get(0).getBscs())?lists.get(0).getBscs():0);
        responseDto.setContent(map);
        return responseDto;
    }

    public static Boolean isOverThreeMinute(String curDateStr, String nextDateStr){
        Date begin = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date end = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        long minute=(end.getTime()-begin.getTime())/(1000*60);//除以1000是为了转换成秒
        if(minute<=2){
            return true;
        }
        return false;
    }
}
