package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.EquipmentFileDto;
import com.pd.server.main.dto.InterfaceLogDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.basewx.my.EquipmentFileAlarmEventDwDto;
import com.pd.server.main.dto.basewx.my.EquipmentFileDwjkDto;
import com.pd.server.main.dto.basewx.my.PredationNumDwDto;
import com.pd.server.main.service.*;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import com.pd.server.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sbsn"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn不能为空。");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sqm"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权码不能为空。");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("ip"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备ip不能为空。");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("cjrq"))){
                responseDto.setCode("4000");
                responseDto.setContent("采集日期不能为空。");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
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
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else if(authorizeInfos.size()>1){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权信息过多，请联系管理员！");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else{
                AuthorizeInfo authorizeInfo = authorizeInfos.get(0);
                if(!qqcs.get("sqm").equals(authorizeInfo.getSqm())){
                    responseDto.setCode("4000");
                    responseDto.setContent("设备授权码错误。");
                    interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                if(!authorizeInfo.getIp().contains(qqcs.get("ip").toString())){
                    responseDto.setCode("4000");
                    responseDto.setContent("ip未授权。");
                    interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                EquipmentFileExample fileExample = new EquipmentFileExample();
                EquipmentFileExample.Criteria fileCa = fileExample.createCriteria();
                fileCa.andSbbhEqualTo(qqcs.get("sbsn").toString());
                fileCa.andRqEqualTo(qqcs.get("cjrq").toString());
                List<EquipmentFile> equipmentFiles = equipmentFileService.listAll(fileExample);
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
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sbsn"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn不能为空。");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sqm"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权码不能为空。");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("ip"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备ip不能为空。");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
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
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else if(authorizeInfos.size()>1){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权信息过多，请联系管理员！");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else{
                AuthorizeInfo authorizeInfo = authorizeInfos.get(0);
                if(!qqcs.get("sqm").equals(authorizeInfo.getSqm())){
                    responseDto.setCode("4000");
                    responseDto.setContent("设备授权码错误。");
                    interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                if(!authorizeInfo.getIp().contains(qqcs.get("ip").toString())){
                    responseDto.setCode("4000");
                    responseDto.setContent("ip未授权。");
                    interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                EquipmentFileTodayExample fileTodayExample = new EquipmentFileTodayExample();
                EquipmentFileTodayExample.Criteria fileCa = fileTodayExample.createCriteria();
                fileCa.andSbbhEqualTo(qqcs.get("sbsn").toString());
                fileCa.andCjsjGreaterThanOrEqualTo(DateUtil.getFormatDate(DateUtil.getMinutesLater(new Date(),-5),"yyyy-MM-dd HH:mm:ss"),"%Y-%m-%d %H:%i:%s");
                fileCa.andCjsjLessThanOrEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd HH:mm:ss"),"%Y-%m-%d %H:%i:%s");
                List<EquipmentFileToday> equipmentFiles = equipmentFileTodayService.listAll(fileTodayExample);
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
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sbsn"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备sn不能为空。");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("sqm"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权码不能为空。");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("ip"))){
                responseDto.setCode("4000");
                responseDto.setContent("设备ip不能为空。");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }
            if(StringUtils.isEmpty(qqcs.get("cjrq"))){
                responseDto.setCode("4000");
                responseDto.setContent("采集日期不能为空。");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
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
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else if(authorizeInfos.size()>1){
                responseDto.setCode("4000");
                responseDto.setContent("设备授权信息过多，请联系管理员！");
                interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                interfaceLogService.save(interfaceLog);
                return responseDto;
            }else{
                AuthorizeInfo authorizeInfo = authorizeInfos.get(0);
                if(!qqcs.get("sqm").equals(authorizeInfo.getSqm())){
                    responseDto.setCode("4000");
                    responseDto.setContent("设备授权码错误。");
                    interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
                    interfaceLogService.save(interfaceLog);
                    return responseDto;
                }
                if(!authorizeInfo.getIp().contains(qqcs.get("ip").toString())){
                    responseDto.setCode("4000");
                    responseDto.setContent("ip未授权。");
                    interfaceLog.setFhsj(JSONObject.toJSONString(responseDto));
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

}
