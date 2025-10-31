package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.InterfaceLogDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.basewx.my.*;
import com.pd.server.main.service.*;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/externalInterface")
public class DwjkController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(InterfaceLogController.class);
    public static final String BUSINESS_NAME = "对外接口";

    @Resource
    private InterfaceLogService interfaceLogService;
    @Resource
    private AuthorizeInfoService authorizeInfoService;
    @Resource
    private EquipmentFileTodayService equipmentFileTodayService;
    @Resource
    private AttrService attrService;
    @Resource
    private PredationNumService predationNumService;
    @Resource
    private EquipmentFileAlarmEventService equipmentFileAlarmEventService;
    @Resource
    private PointerDayService pointerDayService;
    @Resource
    private PointerSecondService pointerSecondService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private EquipmentFilePClusterService equipmentFilePClusterService;
    @Resource
    private EquipmentFilePPicService equipmentFilePPicService;
    @Resource
    private EquipmentFilePTxtService equipmentFilePTxtService;
    @Resource
    private EquipmentFilePWavService equipmentFilePWavService;
    @Resource
    private EquipmentFilePVideoService equipmentFilePVideoService;
    @Resource
    private EquipmentFileTClusterService equipmentFileTClusterService;



    /**
     * 当天最近的一条聚类信息
     */
    @PostMapping("/lastEventBySbbh")
    public ResponseDto lastEventBySbbh(@RequestBody JSONObject qqcs){
        ResponseDto responseDto = new ResponseDto();
        InterfaceLogDto interfaceLog = new InterfaceLogDto();
        try {
            if(StringUtils.isEmpty(qqcs.get("sbbh"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            // 获取设备信息
            Map<String, JSONObject> sbbhEquipMap = (Map<String, JSONObject>) redisTemplate.opsForValue().get(RedisCode.SBBHEQUIPMAP);
            if(!sbbhEquipMap.keySet().contains("equip-"+qqcs.get("sbbh"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn错误。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            EquipmentFilePClusterExample example = new EquipmentFilePClusterExample();
            EquipmentFilePClusterExample.Criteria ca = example.createCriteria();
            ca.andSbbhEqualTo(qqcs.get("sbbh").toString());
            ca.andRqEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
            example.setOrderByClause(" kssj desc ");
            List<EquipmentFilePCluster> pClusters = equipmentFilePClusterService.selectLastDw(example);
            if(pClusters.size()>0){
                EquipmentFilePCluster pCluster = pClusters.get(0);
                EventDwDto eventDwDto = new EventDwDto();
                eventDwDto.setSbbh(pCluster.getSbbh());
                eventDwDto.setSbwz("116.05429,29.44149");
                eventDwDto.setCxsj(DateUtil.toDate(pCluster.getJssj(),"yyyy-MM-dd HH:mm:ss"));
                eventDwDto.setFwj(pCluster.getJtnr());
                responseDto.setContent(eventDwDto);
            }else {
                responseDto.setContent(null);
            }
            responseDto.setCode("0000");

        }catch (Exception e){
            responseDto.setCode("2001");
            interfaceLog.setFhsj("系统异常："+e.getMessage());
            interfaceLogService.save(interfaceLog);
        }
        return responseDto;
    }


    /**
     * 江豚图片查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody JSONObject qqcs) {
        ResponseDto responseDto = new ResponseDto();
        InterfaceLogDto interfaceLog = new InterfaceLogDto();
        try {
            interfaceLog.setQqcs(qqcs.toString());
            interfaceLog.setIp(StringUtils.isEmpty(qqcs.get("ip"))?"":qqcs.get("ip").toString());
            interfaceLog.setQqry(StringUtils.isEmpty(qqcs.get("dwmc"))?"":qqcs.get("dwmc").toString());
            interfaceLog.setQqsj(new Date());
            if(StringUtils.isEmpty(qqcs.get("dwmc"))){
                responseDto.setCode("4000");
                responseDto.setContent("单位名称不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sbsn"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sqm"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权码不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("ip"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备ip不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("cjrq"))){
                responseDto.setCode("4000");
                responseDto.setContent("采集日期不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            AuthorizeInfoExample authorizeInfoExample = new AuthorizeInfoExample();
            AuthorizeInfoExample.Criteria authorizeCa = authorizeInfoExample.createCriteria();
            authorizeCa.andSbsnEqualTo(qqcs.get("sbsn").toString());
            authorizeCa.andDwmcEqualTo(qqcs.get("dwmc").toString());
            List<AuthorizeInfo> authorizeInfos = authorizeInfoService.listAll(authorizeInfoExample);
            if(authorizeInfos.size()<=0){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn未授权。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else if(authorizeInfos.size()>1){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权信息过多，请联系管理员！");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else{
                AuthorizeInfo authorizeInfo = authorizeInfos.get(0);
                if(!qqcs.get("sqm").equals(authorizeInfo.getSqm())){
                    responseDto.setCode("4000");
                    responseDto.setContent("设备授权码错误。");
                    interfaceLog.setFhsj(responseDto.toString());
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                if(!authorizeInfo.getIp().contains(qqcs.get("ip").toString())){
                    responseDto.setCode("4000");
                    responseDto.setContent("ip未授权。");
                    interfaceLog.setFhsj(responseDto.toString());
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                List<EquipmentFileDwjkDto> resultList = new ArrayList<>();
                EquipmentFilePClusterExample clusterExample = new EquipmentFilePClusterExample();
                clusterExample.createCriteria().andSbbhEqualTo(qqcs.get("sbsn").toString()).andRqEqualTo(qqcs.get("cjrq").toString());
                List<EquipmentFileDwjkDto> clusterList = equipmentFilePClusterService.listAllDw(clusterExample);
                resultList.addAll(clusterList);
                EquipmentFilePPicExample picExample = new EquipmentFilePPicExample();
                picExample.createCriteria().andSbbhEqualTo(qqcs.get("sbsn").toString()).andRqEqualTo(qqcs.get("cjrq").toString());
                List<EquipmentFileDwjkDto> picList = equipmentFilePPicService.listAllDw(picExample);
                resultList.addAll(picList);
                EquipmentFilePTxtExample txtExample = new EquipmentFilePTxtExample();
                txtExample.createCriteria().andSbbhEqualTo(qqcs.get("sbsn").toString()).andRqEqualTo(qqcs.get("cjrq").toString());
                List<EquipmentFileDwjkDto> txtList = equipmentFilePTxtService.listAllDw(txtExample);
                resultList.addAll(txtList);
                EquipmentFilePWavExample wavExample = new EquipmentFilePWavExample();
                wavExample.createCriteria().andSbbhEqualTo(qqcs.get("sbsn").toString()).andRqEqualTo(qqcs.get("cjrq").toString());
                List<EquipmentFileDwjkDto> wavList = equipmentFilePWavService.listAllDw(wavExample);
                resultList.addAll(wavList);
                EquipmentFilePVideoExample videoExample = new EquipmentFilePVideoExample();
                videoExample.createCriteria().andSbbhEqualTo(qqcs.get("sbsn").toString()).andRqEqualTo(qqcs.get("cjrq").toString());
                List<EquipmentFileDwjkDto> videoList = equipmentFilePVideoService.listAllDw(videoExample);
                resultList.addAll(videoList);
                responseDto.setCode("0000");
                responseDto.setContent(resultList.stream()
                        .sorted(Comparator.comparing(EquipmentFileDwjkDto::getCjsj).reversed())
                        .collect(Collectors.toList()));
            }
        }catch (Exception e){
            responseDto.setCode("2001");
            interfaceLog.setFhsj("系统异常："+e.getMessage());
            interfaceLogService.save(interfaceLog);
        }
        return responseDto;
    }

    /**
     * 江豚图片查询（当前时间前5分钟的数据）
     */
    @PostMapping("/listByToday")
    public ResponseDto listByToday(@RequestBody JSONObject qqcs) {
        ResponseDto responseDto = new ResponseDto();
        InterfaceLogDto interfaceLog = new InterfaceLogDto();
        try{
            interfaceLog.setQqcs(qqcs.toString());
            interfaceLog.setIp(StringUtils.isEmpty(qqcs.get("ip"))?"":qqcs.get("ip").toString());
            interfaceLog.setQqry(StringUtils.isEmpty(qqcs.get("dwmc"))?"":qqcs.get("dwmc").toString());
            interfaceLog.setQqsj(new Date());
            if(StringUtils.isEmpty(qqcs.get("dwmc"))){
                responseDto.setCode("4000");
                responseDto.setContent("单位名称不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sbsn"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sqm"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权码不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("ip"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备ip不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            AuthorizeInfoExample authorizeInfoExample = new AuthorizeInfoExample();
            AuthorizeInfoExample.Criteria authorizeCa = authorizeInfoExample.createCriteria();
            authorizeCa.andDwmcEqualTo(qqcs.get("dwmc").toString());
            authorizeCa.andSbsnEqualTo(qqcs.get("sbsn").toString());
            List<AuthorizeInfo> authorizeInfos = authorizeInfoService.listAll(authorizeInfoExample);
            if(authorizeInfos.size()<=0){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn未授权。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else if(authorizeInfos.size()>1){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权信息过多，请联系管理员！");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else{
                AuthorizeInfo authorizeInfo = authorizeInfos.get(0);
                if(!qqcs.get("sqm").equals(authorizeInfo.getSqm())){
                    responseDto.setCode("4000");
                    responseDto.setContent("设备授权码错误。");
                    interfaceLog.setFhsj(responseDto.toString());
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                if(!authorizeInfo.getIp().contains(qqcs.get("ip").toString())){
                    responseDto.setCode("4000");
                    responseDto.setContent("ip未授权。");
                    interfaceLog.setFhsj(responseDto.toString());
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                EquipmentFileTodayExample fileTodayExample = new EquipmentFileTodayExample();
                EquipmentFileTodayExample.Criteria fileCa = fileTodayExample.createCriteria();
                fileCa.andSbbhEqualTo(qqcs.get("sbsn").toString());
                fileCa.andCjsjGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getMinutesLater(new Date(),-5),"yyyy-MM-dd HH:mm:ss"),"%Y-%m-%d %H:%i:%s");
                fileCa.andCjsjLessThanOrEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd HH:mm:ss"),"%Y-%m-%d %H:%i:%s");
                List<EquipmentFileToday> equipmentFiles = equipmentFileTodayService.listAllDw(fileTodayExample);
                List<EquipmentFileDwjkDto> equipmentFilesDto = CopyUtil.copyList(equipmentFiles, EquipmentFileDwjkDto.class);
                responseDto.setCode("0000");
                responseDto.setContent(equipmentFilesDto);
            }
        }catch (Exception e){
            responseDto.setCode("2001");
            interfaceLog.setFhsj("系统异常："+e.getMessage());
            interfaceLogService.save(interfaceLog);
        }
        return responseDto;
    }

    /**
     * 获取某一天江豚事件
     */
    @PostMapping("/listEvent")
    public ResponseDto listEvent(@RequestBody JSONObject qqcs) {
        ResponseDto responseDto = new ResponseDto();
        InterfaceLogDto interfaceLog = new InterfaceLogDto();
        try {
            interfaceLog.setQqcs(qqcs.toString());
            interfaceLog.setIp(StringUtils.isEmpty(qqcs.get("ip"))?"":qqcs.get("ip").toString());
            interfaceLog.setQqry(StringUtils.isEmpty(qqcs.get("dwmc"))?"":qqcs.get("dwmc").toString());
            interfaceLog.setQqsj(new Date());
            if(StringUtils.isEmpty(qqcs.get("dwmc"))){
                responseDto.setCode("4000");
                responseDto.setContent("单位名称不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sbsn"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sqm"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权码不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("ip"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备ip不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("cjrq"))){
                responseDto.setCode("4000");
                responseDto.setContent("采集日期不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            AuthorizeInfoExample authorizeInfoExample = new AuthorizeInfoExample();
            AuthorizeInfoExample.Criteria authorizeCa = authorizeInfoExample.createCriteria();
            authorizeCa.andDwmcEqualTo(qqcs.get("dwmc").toString());
            authorizeCa.andSbsnEqualTo(qqcs.get("sbsn").toString());
            List<AuthorizeInfo> authorizeInfos = authorizeInfoService.listAll(authorizeInfoExample);
            if(authorizeInfos.size()<=0){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn未授权。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else if(authorizeInfos.size()>1){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权信息过多，请联系管理员！");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else{
                AuthorizeInfo authorizeInfo = authorizeInfos.get(0);
                if(!qqcs.get("sqm").equals(authorizeInfo.getSqm())){
                    responseDto.setCode("4000");
                    responseDto.setContent("设备授权码错误。");
                    interfaceLog.setFhsj(responseDto.toString());
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                if(!authorizeInfo.getIp().contains(qqcs.get("ip").toString())){
                    responseDto.setCode("4000");
                    responseDto.setContent("ip未授权。");
                    interfaceLog.setFhsj(responseDto.toString());
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                PredationNumExample example = new PredationNumExample();
                PredationNumExample.Criteria ca = example.createCriteria();
                ca.andSbbhEqualTo(qqcs.getString("sbsn"));
                ca.andCjsjEqualTo(qqcs.getString("cjrq"),"%Y-%m-%d");
                List<PredationNumDwDto> list = predationNumService.listDw(example);
                EquipmentFileAlarmEventExample eventExample = new EquipmentFileAlarmEventExample();
                EquipmentFileAlarmEventExample.Criteria eventCa = eventExample.createCriteria();
                eventCa.andSbbhEqualTo(qqcs.getString("sbsn"));
                eventCa.andBjsjEqualTo(qqcs.getString("cjrq"),"%Y-%m-%d");
                eventExample.setOrderByClause(" xh ");
                List<EquipmentFileAlarmEventDwDto> detailList = equipmentFileAlarmEventService.selectDwByExample(eventExample);
                if(!CollectionUtils.isEmpty(list)){
                    list.get(0).setDetailList(detailList);
                }
                responseDto.setCode("0000");
                responseDto.setContent(CollectionUtils.isEmpty(list)?"":list.get(0));
            }
        }catch (Exception e){
            responseDto.setCode("2001");
            interfaceLog.setFhsj("系统异常："+e.getMessage());
            interfaceLogService.save(interfaceLog);
        }
        return responseDto;
    }

    /**
     * 获取A2设备聚类事件
     */
    @PostMapping("/listTyEvent")
    public ResponseDto listTyEvent(@RequestBody JSONObject qqcs) {
        ResponseDto responseDto = new ResponseDto();
        InterfaceLogDto interfaceLog = new InterfaceLogDto();
        try {
            interfaceLog.setQqcs(qqcs.toString());
            interfaceLog.setIp(StringUtils.isEmpty(qqcs.get("ip"))?"":qqcs.get("ip").toString());
            interfaceLog.setQqry(StringUtils.isEmpty(qqcs.get("dwmc"))?"":qqcs.get("dwmc").toString());
            interfaceLog.setQqsj(new Date());
            if(StringUtils.isEmpty(qqcs.get("dwmc"))){
                responseDto.setCode("4000");
                responseDto.setContent("单位名称不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sbsn"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sqm"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权码不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("ip"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备ip不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("cjrq"))){
                responseDto.setCode("4000");
                responseDto.setContent("采集日期不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            AuthorizeInfoExample authorizeInfoExample = new AuthorizeInfoExample();
            AuthorizeInfoExample.Criteria authorizeCa = authorizeInfoExample.createCriteria();
            authorizeCa.andDwmcEqualTo(qqcs.get("dwmc").toString());
            authorizeCa.andSbsnEqualTo(qqcs.get("sbsn").toString());
            List<AuthorizeInfo> authorizeInfos = authorizeInfoService.listAll(authorizeInfoExample);
            if(authorizeInfos.size()<=0){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn未授权。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else if(authorizeInfos.size()>1){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权信息过多，请联系管理员！");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else{
                AuthorizeInfo authorizeInfo = authorizeInfos.get(0);
                if(!qqcs.get("sqm").equals(authorizeInfo.getSqm())){
                    responseDto.setCode("4000");
                    responseDto.setContent("设备授权码错误。");
                    interfaceLog.setFhsj(responseDto.toString());
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                if(!authorizeInfo.getIp().contains(qqcs.get("ip").toString())){
                    responseDto.setCode("4000");
                    responseDto.setContent("ip未授权。");
                    interfaceLog.setFhsj(responseDto.toString());
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                EquipmentFileTClusterExample example = new EquipmentFileTClusterExample();
                EquipmentFileTClusterExample.Criteria ca = example.createCriteria();
                ca.andSbbhEqualTo(qqcs.getString("sbsn"));
                ca.andRqEqualTo(qqcs.getString("cjrq"));
                List<EquipmentTyEventDwDto> list = equipmentFileTClusterService.listAllDw(example);
                responseDto.setCode("0000");
                responseDto.setContent(list);
            }
        }catch (Exception e){
            responseDto.setCode("2001");
            interfaceLog.setFhsj("系统异常："+e.getMessage());
            interfaceLogService.save(interfaceLog);
        }
        return responseDto;
    }

    /**
     * 获取A4设备聚类事件
     */
    @PostMapping("/listEquipmentFileEvent")
    public ResponseDto listEquipmentFileEvent(@RequestBody JSONObject qqcs) {
        ResponseDto responseDto = new ResponseDto();
        InterfaceLogDto interfaceLog = new InterfaceLogDto();
        try {
            interfaceLog.setQqcs(qqcs.toString());
            interfaceLog.setIp(StringUtils.isEmpty(qqcs.get("ip"))?"":qqcs.get("ip").toString());
            interfaceLog.setQqry(StringUtils.isEmpty(qqcs.get("dwmc"))?"":qqcs.get("dwmc").toString());
            interfaceLog.setQqsj(new Date());
            if(StringUtils.isEmpty(qqcs.get("dwmc"))){
                responseDto.setCode("4000");
                responseDto.setContent("单位名称不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sbsn"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sqm"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权码不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("ip"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备ip不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("cjrq"))){
                responseDto.setCode("4000");
                responseDto.setContent("采集日期不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            AuthorizeInfoExample authorizeInfoExample = new AuthorizeInfoExample();
            AuthorizeInfoExample.Criteria authorizeCa = authorizeInfoExample.createCriteria();
            authorizeCa.andDwmcEqualTo(qqcs.get("dwmc").toString());
            authorizeCa.andSbsnEqualTo(qqcs.get("sbsn").toString());
            List<AuthorizeInfo> authorizeInfos = authorizeInfoService.listAll(authorizeInfoExample);
            if(authorizeInfos.size()<=0){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn未授权。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else if(authorizeInfos.size()>1){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权信息过多，请联系管理员！");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else{
                AuthorizeInfo authorizeInfo = authorizeInfos.get(0);
                if(!qqcs.get("sqm").equals(authorizeInfo.getSqm())){
                    responseDto.setCode("4000");
                    responseDto.setContent("设备授权码错误。");
                    interfaceLog.setFhsj(responseDto.toString());
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                if(!authorizeInfo.getIp().contains(qqcs.get("ip").toString())){
                    responseDto.setCode("4000");
                    responseDto.setContent("ip未授权。");
                    interfaceLog.setFhsj(responseDto.toString());
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                EquipmentFilePClusterExample example = new EquipmentFilePClusterExample();
                EquipmentFilePClusterExample.Criteria ca = example.createCriteria();
                ca.andSbbhEqualTo(qqcs.getString("sbsn"));
                ca.andRqEqualTo(qqcs.getString("cjrq"));
                example.setOrderByClause(" jssj desc");
                List<EquipmentFileEventDwDto> list = equipmentFilePClusterService.listDw(example);
                responseDto.setCode("0000");
                responseDto.setContent(list);
            }
        }catch (Exception e){
            responseDto.setCode("2001");
            interfaceLog.setFhsj("系统异常："+e.getMessage());
            interfaceLogService.save(interfaceLog);
        }
        return responseDto;
    }


    /**
     * 获取最近5天之前的A4设备聚类事件(包含当天)
     */
    @PostMapping("/listEquipmentFileEventToday")
    public ResponseDto listEquipmentFileEventToday(@RequestBody JSONObject qqcs) {
        ResponseDto responseDto = new ResponseDto();
        InterfaceLogDto interfaceLog = new InterfaceLogDto();
        try {
            interfaceLog.setQqcs(qqcs.toString());
            interfaceLog.setIp(StringUtils.isEmpty(qqcs.get("ip"))?"":qqcs.get("ip").toString());
            interfaceLog.setQqry(StringUtils.isEmpty(qqcs.get("dwmc"))?"":qqcs.get("dwmc").toString());
            interfaceLog.setQqsj(new Date());
            if(StringUtils.isEmpty(qqcs.get("dwmc"))){
                responseDto.setCode("4000");
                responseDto.setContent("单位名称不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sbsn"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sqm"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权码不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("ip"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备ip不能为空。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            AuthorizeInfoExample authorizeInfoExample = new AuthorizeInfoExample();
            AuthorizeInfoExample.Criteria authorizeCa = authorizeInfoExample.createCriteria();
            authorizeCa.andDwmcEqualTo(qqcs.get("dwmc").toString());
            authorizeCa.andSbsnEqualTo(qqcs.get("sbsn").toString());
            List<AuthorizeInfo> authorizeInfos = authorizeInfoService.listAll(authorizeInfoExample);
            if(authorizeInfos.size()<=0){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn未授权。");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else if(authorizeInfos.size()>1){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权信息过多，请联系管理员！");
                interfaceLog.setFhsj(responseDto.toString());
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else{
                AuthorizeInfo authorizeInfo = authorizeInfos.get(0);
                if(!qqcs.get("sqm").equals(authorizeInfo.getSqm())){
                    responseDto.setCode("4000");
                    responseDto.setContent("设备授权码错误。");
                    interfaceLog.setFhsj(responseDto.toString());
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                if(!authorizeInfo.getIp().contains(qqcs.get("ip").toString())){
                    responseDto.setCode("4000");
                    responseDto.setContent("ip未授权。");
                    interfaceLog.setFhsj(responseDto.toString());
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                EquipmentFilePClusterExample example = new EquipmentFilePClusterExample();
                EquipmentFilePClusterExample.Criteria ca = example.createCriteria();
                ca.andSbbhEqualTo(qqcs.getString("sbsn"));
                ca.andJssjGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getMinutesLater(new Date(),-4),"yyyy-MM-dd HH:mm:ss"));
                ca.andJssjLessThanOrEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
                example.setOrderByClause(" jssj desc");
                List<EquipmentFileEventDwDto> list = equipmentFilePClusterService.listDw(example);
                responseDto.setCode("0000");
                responseDto.setContent(list);
            }
        }catch (Exception e){
            responseDto.setCode("2001");
            interfaceLog.setFhsj("系统异常："+e.getMessage());
            interfaceLogService.save(interfaceLog);
        }
        return responseDto;
    }

    //无人机当天指针数据
    @GetMapping("/wrjDrZzSj")
    public ResponseDto wrjDrZzSj() {
        ResponseDto responseDto = new ResponseDto();
        String wrjDept = attrService.findByAttrKey("wrjDept");
        List<String> listdept = getUpdeptcode(wrjDept);
        PointerDayExample example = new PointerDayExample();
        PointerDayExample.Criteria ca = example.createCriteria();
        if(!CollectionUtils.isEmpty(listdept)){
            ca.andBzIn(listdept);
        }
        String decibelValue = pointerDayService.selectByWrj(example);
        responseDto.setCode("0000");
        responseDto.setContent(decibelValue);
        return responseDto;
    }

    //无人机实时指针数据（Fish）
    @GetMapping("/wrjSsZzSj")
    public ResponseDto wrjSsZzSj() {
        ResponseDto responseDto = new ResponseDto();
        String wrjDept = attrService.findByAttrKey("wrjDept");
        List<String> listdept = getUpdeptcode(wrjDept);
        PointerSecondExample example = new PointerSecondExample();
        PointerSecondExample.Criteria ca = example.createCriteria();
        if(!CollectionUtils.isEmpty(listdept)){
            ca.andBzIn(listdept);
        }
        String decibelValue = pointerSecondService.selectByWrj(example);
        responseDto.setCode("0000");
        responseDto.setContent(decibelValue);
        return responseDto;
    }

    //无人机当日聚类数据（所有设备）
    @GetMapping("/wrjJlsj")
    public ResponseDto wrjJlsj() {
        ResponseDto responseDto = new ResponseDto();
        String wrjDept = attrService.findByAttrKey("wrjDept");
        List<String> listdept = getUpdeptcode(wrjDept);
        EquipmentFilePClusterExample example = new EquipmentFilePClusterExample();
        EquipmentFilePClusterExample.Criteria  ca = example.createCriteria();
        if(!CollectionUtils.isEmpty(listdept)){
            ca.andDeptcodeIn(listdept);
        }
        ca.andRqEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
        List<WrjEventDto> list = equipmentFilePClusterService.listAllWrj(example);
        responseDto.setCode("0000");
        responseDto.setContent(list);
        return responseDto;
    }

    //无人机当日声谱图（所有设备）
    @PostMapping("/wrjSpt")
    public ResponseDto getSwipeData(@RequestBody Map<String,String> map){
        ResponseDto responseDto = new ResponseDto();
        if(StringUtils.isEmpty(map.get("sbbh"))||StringUtils.isEmpty(map.get("kssj"))||StringUtils.isEmpty(map.get("jssj"))){
            responseDto.setCode("4000");
            responseDto.setMessage("参数错误");
            return responseDto;
        }
        EquipmentFilePPicExample example = new EquipmentFilePPicExample();
        EquipmentFilePPicExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(map.get("sbbh"))){
            ca.andSbbhEqualTo(map.get("sbbh"));
        }
        if(!StringUtils.isEmpty(map.get("kssj"))){
            ca.andCjsjGreaterThanOrEqualTo(DateUtil.toDate(map.get("kssj"),"yyyy-MM-dd HH:mm:ss"));
        }
        if(!StringUtils.isEmpty(map.get("jssj"))){
            ca.andCjsjLessThanOrEqualTo(DateUtil.toDate(map.get("jssj"),"yyyy-MM-dd HH:mm:ss"));
        }
        List<EquipmentFileDwjkDto> list = equipmentFilePPicService.listAllDw(example);
        List<String> tplist = list.stream().filter(Objects::nonNull).map(EquipmentFileDwjkDto::getTplj).distinct().collect(Collectors.toList());
        responseDto.setCode("0000");
        responseDto.setContent(tplist);
        return responseDto;
    }

    //无人机头次统计（所有设备前五天的数据）
    @GetMapping("/wrjTctj")
    public ResponseDto wrjTctj(){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFilePClusterExample example = new EquipmentFilePClusterExample();
        EquipmentFilePClusterExample.Criteria ca = example.createCriteria();
        String wrjDept = attrService.findByAttrKey("wrjDept");
        List<String> listdept = getUpdeptcode(wrjDept);
        if(!CollectionUtils.isEmpty(listdept)){
            ca.andDeptcodeIn(listdept);
        }
        ca.andRqGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-4),"yyyy-MM-dd"));
        ca.andRqLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        List<WrjTcDto> list = equipmentFilePClusterService.listSumTsWrj(example);
        Map<String,Object> resultMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(list)){
            Map<String, List<WrjTcDto>> map = list.stream().sorted(Comparator.comparing(WrjTcDto::getRq)).collect(Collectors.groupingBy(WrjTcDto::getSbbh));
            List<String> rqs= list.stream().sorted(Comparator.comparing(WrjTcDto::getRq)).filter(Objects::nonNull).map(WrjTcDto::getRq).distinct().collect(Collectors.toList());
            resultMap.put("map",map);
            resultMap.put("rqs",rqs);
        }
        responseDto.setCode("0000");
        responseDto.setContent(resultMap);
        return responseDto;
    }

    //无人机当日捕食，事件，侦测次数统计
    @GetMapping("/wrjTcdtsj")
    public ResponseDto wrjTcdtsj(){
        ResponseDto responseDto = new ResponseDto();
        EquipmentFileTodayExample example = new EquipmentFileTodayExample();
        EquipmentFileTodayExample example1 = new EquipmentFileTodayExample();
        EquipmentFileTodayExample.Criteria ca = example.createCriteria();
        EquipmentFileTodayExample.Criteria ca1 = example1.createCriteria();
        String wrjDept = attrService.findByAttrKey("wrjDept");
        List<String> listdept = getUpdeptcode(wrjDept);
        if(!CollectionUtils.isEmpty(listdept)){
            ca.andDeptcodeIn(listdept);
            ca1.andDeptcodeIn(listdept);
        }
        ca.andRqEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
        ca1.andRqEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
        ca.andTxtlxEqualTo("1");
        List<AlarmNumbersDto> lists = equipmentFileTodayService.statisticsAlarmNums(example);
        ca1.andTxtlxEqualTo("1");
        ca1.andJczlEqualTo("1");
        List<EquipmentFileToday> predationList = equipmentFileTodayService.listAll(example1);
        List<AlarmNumbersDto> resultList = new ArrayList<>();
        Map<String, List<AlarmNumbersDto>> mapList = lists.stream().collect(Collectors.groupingBy(AlarmNumbersDto::getSbbh));
        for(String key : mapList.keySet()){
            List<AlarmNumbersDto> listsTemp = mapList.get(key);
            if(!CollectionUtils.isEmpty(listsTemp)){
                AlarmNumbersDto firstEntity = listsTemp.get(0);
                String curDateStr = firstEntity.getFz();
                Integer bjsl = firstEntity.getAlarmNum();
                for(int i=1;i<listsTemp.size();i++){
                    AlarmNumbersDto entity = listsTemp.get(i);
                    AlarmNumbersDto beforeEntity = listsTemp.get(i-1);
                    String beforeDateStr = beforeEntity.getFz();
                    String nextDateStr = entity.getFz();
                    if(entity.getSbbh().equals(firstEntity.getSbbh())){
                        if(isOverThreeMinute(beforeDateStr, nextDateStr)){
                            bjsl = bjsl + entity.getAlarmNum();
                        }else {
                            AlarmNumbersDto result = new AlarmNumbersDto();
                            result.setDeptcode(entity.getDeptcode());
                            result.setSbbh(entity.getSbbh());
                            result.setBjsj(curDateStr+" 至 "+beforeDateStr);
                            result.setAlarmNum(bjsl);
                            resultList.add(result);
                            firstEntity = entity;
                            curDateStr = firstEntity.getFz();
                            bjsl = firstEntity.getAlarmNum();
                        }
                    }else {
                        AlarmNumbersDto result = new AlarmNumbersDto();
                        result.setDeptcode(firstEntity.getDeptcode());
                        result.setSbbh(firstEntity.getSbbh());
                        result.setBjsj(curDateStr+" 至 "+beforeDateStr);
                        result.setAlarmNum(bjsl);
                        resultList.add(result);
                        firstEntity = entity;
                        curDateStr = firstEntity.getFz();
                        bjsl = firstEntity.getAlarmNum();
                    }
                    if(i==listsTemp.size()-1){
                        AlarmNumbersDto result = new AlarmNumbersDto();
                        result.setDeptcode(entity.getDeptcode());
                        result.setSbbh(entity.getSbbh());
                        result.setBjsj(curDateStr+" 至 "+nextDateStr);
                        result.setAlarmNum(bjsl);
                        resultList.add(result);
                    }
                }
                if(listsTemp.size()==1){
                    AlarmNumbersDto result = new AlarmNumbersDto();
                    result.setDeptcode(firstEntity.getDeptcode());
                    result.setSbbh(firstEntity.getSbbh());
                    result.setBjsj(curDateStr+" 至 "+curDateStr);
                    result.setAlarmNum(bjsl);
                    resultList.add(result);
                }
            }
        }
        Integer num = 0;
        for (int i = 0; i < resultList.size(); i++) {
            Integer alarmNum = resultList.get(i).getAlarmNum();
            num = num + alarmNum;
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("num",num);
        map.put("nnm",resultList.size());
        map.put("bnum",predationList.size());
        responseDto.setContent(map);
        return responseDto;
    }

    public Boolean isOverThreeMinute(String curDateStr, String nextDateStr){
        Date begin = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date end = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        long minute=(end.getTime()-begin.getTime())/(1000*60);//除以1000是为了转换成秒
        if(minute<=2){
            return true;
        }
        return false;
    }
}
