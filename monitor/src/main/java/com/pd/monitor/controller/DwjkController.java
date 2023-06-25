package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.EquipmentFileDto;
import com.pd.server.main.dto.InterfaceLogDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.basewx.my.*;
import com.pd.server.main.service.*;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import com.pd.server.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
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
    private EquipmentFileService equipmentFileService;
    @Resource
    private EquipmentFileTodayService equipmentFileTodayService;
    @Resource
    private AttrService attrService;
    @Resource
    private PredationNumService predationNumService;
    @Resource
    private EquipmentFileAlarmEventService equipmentFileAlarmEventService;
    @Resource
    private EquipmentTyEventService equipmentTyEventService;
    @Resource
    private EquipmentFileEventService equipmentFileEventService;
    @Resource
    private PointerDayService pointerDayService;
    @Resource
    private PointerSecondService pointerSecondService;
    @Resource
    private WaterEquipmentService waterEquipmentService;

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
                EquipmentFileExample fileExample = new EquipmentFileExample();
                EquipmentFileExample.Criteria fileCa = fileExample.createCriteria();
                fileCa.andSbbhEqualTo(qqcs.get("sbsn").toString());
                fileCa.andRqEqualTo(qqcs.get("cjrq").toString());
                List<EquipmentFile> equipmentFiles = equipmentFileService.listAllDw(fileExample);
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
                EquipmentTyEventExample example = new EquipmentTyEventExample();
                EquipmentTyEventExample.Criteria ca = example.createCriteria();
                ca.andSbbhEqualTo(qqcs.getString("sbsn"));
                ca.andRqEqualTo(qqcs.getString("cjrq"));
                List<EquipmentTyEvent> list = equipmentTyEventService.list(example);
                List<EquipmentTyEventDwDto> listdto = CopyUtil.copyList(list, EquipmentTyEventDwDto.class);
                responseDto.setCode("0000");
                responseDto.setContent(listdto);
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
                EquipmentFileEventExample example = new EquipmentFileEventExample();
                EquipmentFileEventExample.Criteria ca = example.createCriteria();
                ca.andSbbhEqualTo(qqcs.getString("sbsn"));
                ca.andRqEqualTo(qqcs.getString("cjrq"));
                example.setOrderByClause(" jssj desc");
                List<EquipmentFileEvent> list = equipmentFileEventService.list(example);
                List<EquipmentFileEventDwDto> listDto = CopyUtil.copyList(list,EquipmentFileEventDwDto.class);
                responseDto.setCode("0000");
                responseDto.setContent(listDto);
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
                EquipmentFileEventExample example = new EquipmentFileEventExample();
                EquipmentFileEventExample.Criteria ca = example.createCriteria();
                ca.andSbbhEqualTo(qqcs.getString("sbsn"));
                ca.andJssjGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getMinutesLater(new Date(),-5),"yyyy-MM-dd HH:mm:ss"));
                ca.andJssjLessThanOrEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
                example.setOrderByClause(" jssj desc");
                List<EquipmentFileEvent> list = equipmentFileEventService.list(example);
                List<EquipmentFileEventDwDto> listDto = CopyUtil.copyList(list,EquipmentFileEventDwDto.class);
                responseDto.setCode("0000");
                responseDto.setContent(listDto);
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
        PointerDayExample example = new PointerDayExample();
        String decibelValue = pointerDayService.selectByWrj(example);
        responseDto.setCode("0000");
        responseDto.setContent(decibelValue);
        return responseDto;
    }

    //无人机实时指针数据
    @GetMapping("/wrjSsZzSj")
    public ResponseDto wrjSsZzSj() {
        ResponseDto responseDto = new ResponseDto();
        PointerSecondExample example = new PointerSecondExample();
        String decibelValue = pointerSecondService.selectByWrj(example);
        responseDto.setCode("0000");
        responseDto.setContent(decibelValue);
        return responseDto;
    }

    //无人机当日聚类数据
    @GetMapping("/wrjJlsj")
    public ResponseDto wrjJlsj() {
        ResponseDto responseDto = new ResponseDto();
        WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
        waterEquipmentExample.createCriteria().andDqzlEqualTo("A4");
        List<String> a4SbbhList = waterEquipmentService.findSbbh(waterEquipmentExample);
        String wrjDept = attrService.findByAttrKey("wrjDept");
        List<String> listdept = getUpdeptcode(wrjDept);
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
        List<WrjEventDto> eventLists = CopyUtil.copyList(list,WrjEventDto.class);
        responseDto.setCode("0000");
        responseDto.setContent(eventLists);
        return responseDto;
    }

    //无人机当日声谱图
    @PostMapping("/wrjSpt")
    public ResponseDto getSwipeData(@RequestBody Map<String,String> map){
        ResponseDto responseDto = new ResponseDto();
        if(StringUtils.isEmpty(map.get("sbbh"))||StringUtils.isEmpty(map.get("kssj"))||StringUtils.isEmpty(map.get("jssj"))){
            responseDto.setCode("4000");
            responseDto.setMessage("参数错误");
            return responseDto;
        }
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
        List<String> tplist = list.stream().filter(Objects::nonNull).map(EquipmentFile::getTplj).distinct().collect(Collectors.toList());
        responseDto.setCode("0000");
        responseDto.setContent(tplist);
        return responseDto;
    }

    //无人机头次统计
    @GetMapping("/wrjTctj")
    public ResponseDto wrjTctj(){
        ResponseDto responseDto = new ResponseDto();
        EquipmentTyEventExample example = new EquipmentTyEventExample();
        EquipmentTyEventExample.Criteria ca = example.createCriteria();
        String wrjDept = attrService.findByAttrKey("wrjDept");
        List<String> listdept = getUpdeptcode(wrjDept);
        if(!CollectionUtils.isEmpty(listdept)){
            ca.andDeptcodeIn(listdept);
        }
        ca.andRqGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-4),"yyyy-MM-dd"));
        ca.andRqLessThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getDaysLater(new Date(),-1),"yyyy-MM-dd"));
        List<EquipmentTyEvent> list = equipmentTyEventService.listSumTs(example);
        List<WrjTcDto> tsList = CopyUtil.copyList(list,WrjTcDto.class);
        Map<String,Object> resultMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(list)){
            Map<String, List<WrjTcDto>> map = tsList.stream().sorted(Comparator.comparing(WrjTcDto::getRq)).collect(Collectors.groupingBy(WrjTcDto::getSbbh));
            List<String> rqs= list.stream().sorted(Comparator.comparing(EquipmentTyEvent::getRq)).filter(Objects::nonNull).map(EquipmentTyEvent::getRq).distinct().collect(Collectors.toList());
            resultMap.put("map",map);
            resultMap.put("rqs",rqs);
        }
        responseDto.setCode("0000");
        responseDto.setContent(resultMap);
        return responseDto;
    }
}
