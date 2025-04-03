package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.wx.conf.WxRedisConfig;
import com.pd.server.main.domain.EquipmentFileTodayExample;
import com.pd.server.main.domain.WaterEquipment;
import com.pd.server.main.domain.WaterEquipmentExample;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.UavFlyVideoDto;
import com.pd.server.main.service.EquipmentFileTodayService;
import com.pd.server.main.service.UavFlyVideoService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.DateTools;
import com.pd.server.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/UAVFly")
public class UAVFlyController {

    private static final Logger LOG = LoggerFactory.getLogger(UAVFlyController.class);
    @Resource
    private WaterEquipmentService waterEquipmentService;
    @Resource
    private EquipmentFileTodayService equipmentFileTodayService;
    @Resource
    private UavFlyVideoService uavFlyVideoService;

    @PostMapping("/UAVFlyVideo")
    public ResponseDto UAVFlyVideo(@RequestBody JSONObject jsonObject){
        ResponseDto responseDto = new ResponseDto();
        try {
            if(StringUtils.isEmpty(jsonObject.getString("wjlj")) || StringUtils.isEmpty(jsonObject.getString("cjsj"))){
                responseDto.setSuccess(false);
                responseDto.setMessage("参数错误");
                return responseDto;
            }
            String isFlag = "^(\\d{4})-(\\d{2})-(\\d{2})\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";//判断时间
            if(!jsonObject.getString("cjsj").matches(isFlag)){
                responseDto.setSuccess(false);
                responseDto.setMessage("时间格式错误！");
                return responseDto;
            }
//            WaterEquipmentExample example = new WaterEquipmentExample();
//            example.createCriteria().andSbsnEqualTo(jsonObject.getString("sbbh"));
//            List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(example);
//            if(waterEquipmentList.size()<=0){
//                responseDto.setSuccess(false);
//                responseDto.setMessage("参数错误");
//                return responseDto;
//            }
            UavFlyVideoDto uavFlyVideoDto = new UavFlyVideoDto();
            if(!StringUtils.isEmpty(jsonObject.getString("sbbh"))){
                uavFlyVideoDto.setSbbh(jsonObject.getString("sbbh"));
            }
            uavFlyVideoDto.setVideoUrl(jsonObject.getString("wjlj"));
            uavFlyVideoDto.setCjsj(jsonObject.getString("cjsj"));
            //uavFlyVideoDto.setDeptcode(waterEquipmentList.get(0).getDeptcode());
            uavFlyVideoService.save(uavFlyVideoDto);
            return responseDto;
        }catch (Exception e){
            LOG.error("入参："+jsonObject.toJSONString());
            LOG.error("无人机视频保存接口异常："+e.getMessage());
            responseDto.setSuccess(false);
            responseDto.setMessage("系统异常！");
            return responseDto;
        }
    }

    @GetMapping("/UAVFlyJudge/{deptcode}")
    public ResponseDto UAVFlyJudge(@PathVariable String deptcode){
        ResponseDto responseDto = new ResponseDto();
        try{
            WaterEquipmentExample example = new WaterEquipmentExample();
            example.createCriteria().andDeptcodeEqualTo(deptcode);
            example.setOrderByClause(" sblc ");
            List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(example);
            if(waterEquipmentList.size()<=0){
                responseDto.setSuccess(false);
                responseDto.setMessage("参数错误");
                return responseDto;
            }
            Map<String, String> attrMap = WxRedisConfig.getAttrMap();
            Integer UAVFlyTime = Integer.parseInt(attrMap.get("UAVFlyTime"));
            long UAVFlyCount = Long.parseLong(attrMap.get("UAVFlyCount"));
            for(WaterEquipment waterEquipment : waterEquipmentList){
                EquipmentFileTodayExample todayExample = new EquipmentFileTodayExample();
                EquipmentFileTodayExample.Criteria todayCa = todayExample.createCriteria();
                todayCa.andTxtlxEqualTo("1");
                todayCa.andSbbhEqualTo(waterEquipment.getSbsn());
                todayCa.andCjsjGreaterThanOrEqualTo(DateTools.getFormatDate(DateUtil.getMinutesLater(new Date(),UAVFlyTime),DateTools.yyyy_MM_dd_HH_mm_ss));
                todayCa.andCjsjLessThanOrEqualTo(DateTools.getFormatDate(new Date(),DateTools.yyyy_MM_dd_HH_mm_ss));
                long count = equipmentFileTodayService.countByExample(todayExample);
                if(count>=UAVFlyCount){
                    responseDto.setContent(waterEquipment.getFzrdh());
                    return responseDto;
                }
            }
            responseDto.setSuccess(false);
            responseDto.setMessage("侦测次数不足以飞行！");
            return responseDto;
        }catch (Exception e){
            LOG.error("入参："+deptcode);
            LOG.error("无人机自动循环查询接口异常："+e.getMessage());
            responseDto.setSuccess(false);
            responseDto.setMessage("系统异常，不能飞行！");
            return responseDto;
        }

    }

    @GetMapping("/UAVFlyList/{deptcode}")
    public ResponseDto UAVFlyList(@PathVariable String deptcode){
        ResponseDto responseDto = new ResponseDto();
        try{
            WaterEquipmentExample example = new WaterEquipmentExample();
            example.createCriteria().andDeptcodeEqualTo(deptcode);
            example.setOrderByClause(" sblc ");
            List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(example);
            if(waterEquipmentList.size()<=0){
                responseDto.setSuccess(false);
                responseDto.setMessage("参数错误");
                return responseDto;
            }
            Map<String, String> attrMap = WxRedisConfig.getAttrMap();
            Integer UAVFlyTime = Integer.parseInt(attrMap.get("UAVFlyTime"));
            List<Map<String,Object>> resultList = new ArrayList<>();
            for(WaterEquipment waterEquipment : waterEquipmentList){
                Map<String,Object> resultMap = new HashMap<>();
                EquipmentFileTodayExample todayExample = new EquipmentFileTodayExample();
                EquipmentFileTodayExample.Criteria todayCa = todayExample.createCriteria();
                todayCa.andTxtlxEqualTo("1");
                todayCa.andSbbhEqualTo(waterEquipment.getSbsn());
                todayCa.andCjsjGreaterThanOrEqualTo(DateTools.getFormatDate(DateUtil.getMinutesLater(new Date(),UAVFlyTime),DateTools.yyyy_MM_dd_HH_mm_ss));
                todayCa.andCjsjLessThanOrEqualTo(DateTools.getFormatDate(new Date(),DateTools.yyyy_MM_dd_HH_mm_ss));
                long count = equipmentFileTodayService.countByExample(todayExample);
                resultMap.put("sbbh",waterEquipment.getSbsn());//设备编号
                resultMap.put("fxhx",waterEquipment.getFzrdh());//飞行航线
                resultMap.put("mcs",count);//侦测次数(脉冲数)
                resultList.add(resultMap);
            }
            responseDto.setContent(resultList);
            return responseDto;
        }catch (Exception e){
            LOG.error("入参："+deptcode);
            LOG.error("无人机手动循环查询接口异常："+e.getMessage());
            responseDto.setSuccess(false);
            responseDto.setMessage("系统异常！");
            return responseDto;
        }

    }
}
