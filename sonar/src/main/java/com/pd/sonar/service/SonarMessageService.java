package com.pd.sonar.service;

import com.alibaba.fastjson.JSONArray;
import com.pd.server.main.domain.SonarSingleTarget;
import com.pd.server.main.dto.*;
import com.pd.server.main.service.*;
import com.pd.server.util.UuidUtil;
import com.pd.sonar.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * 声呐消息处理服务
 * 负责处理各种类型的声呐数据消息
 */
@Service
public class SonarMessageService {
    
    private static final Logger logger = Logger.getLogger(SonarMessageService.class.getName());

    @Resource
    private SonarBiomassService sonarBiomassService;
    @Resource
    private SonarAlarmService sonarAlarmService;
    @Resource
    private SonarEchoService sonarEchoService;
    @Resource
    private SonarParameterService sonarParameterService;
    @Resource
    private SonarSingleTargetService sonarSingleTargetService;
    @Resource
    private SonarSingleValueService sonarSingleValueService;
    
    /**
     * 处理生物量数据
     */
    public void processBiomassData(SonarBiomassData biomassData) {
        try {
            logger.info("开始处理生物量数据: ID=" + biomassData.getId() + ", 设备=" + biomassData.getDeviceCode() + ", 时间=" + biomassData.getAcTime());
            SonarBiomassDto sonarBiomassDto = new SonarBiomassDto();
            sonarBiomassDto.setDeviceCode(biomassData.getDeviceCode());
            sonarBiomassDto.setAcTime(biomassData.getAcTime());
            // 解析生物量参数
            if (biomassData.getMapParaValue() != null) {
                for (String key : biomassData.getMapParaValue().keySet()) {
                    switch (key) {
                        case SonarBiomassData.BIOMASS_DENSITY:
                            sonarBiomassDto.setBiomassDensity(biomassData.getMapParaValue().get(key));
                            break;
                        case SonarBiomassData.BIOMASS_WEIGHT_ACCUMULATED:
                            sonarBiomassDto.setBiomassWeightAccumulated(biomassData.getMapParaValue().get(key));
                            break;
                        case SonarBiomassData.BIOMASS_WEIGHT:
                            sonarBiomassDto.setBiomassWeight(biomassData.getMapParaValue().get(key));
                            break;
                        case SonarBiomassData.AVERAGE_SV:
                            sonarBiomassDto.setAverageSv(biomassData.getMapParaValue().get(key));
                            break;
                    }
                }
            }
            sonarBiomassService.save(sonarBiomassDto);
            logger.info("生物量数据处理完成");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "处理生物量数据失败", e);
        }
    }
    
    /**
     * 处理报警信息
     */
    public void processAlarmData(SonarAlarmData alarmData) {
        try {
            logger.warning("收到报警信息: ID=" + alarmData.getId() + ", 设备=" + alarmData.getDeviceCode() + 
                          ", 级别=" + alarmData.getAlarmLevelDesc() + ", 描述=" + alarmData.getAlarmDesc() + 
                          ", 时间=" + alarmData.getAlarmTime());
            SonarAlarmDto sonarAlarmDto = new SonarAlarmDto();
            sonarAlarmDto.setDeviceCode(alarmData.getDeviceCode());
            sonarAlarmDto.setAlarmType(alarmData.getAlarmType());
            sonarAlarmDto.setAlarmDesc(alarmData.getAlarmDesc());
            sonarAlarmDto.setAlarmTime(alarmData.getAlarmTime());
            sonarAlarmService.save(sonarAlarmDto);
//            // 根据报警级别进行不同处理
//            switch (alarmData.getAlarmType()) {
//                case SonarAlarmData.ALARM_LEVEL_ONE:
//                    handleLevelOneAlarm(alarmData);
//                    break;
//                case SonarAlarmData.ALARM_LEVEL_TWO:
//                    handleLevelTwoAlarm(alarmData);
//                    break;
//                case SonarAlarmData.ALARM_LEVEL_THREE:
//                    handleLevelThreeAlarm(alarmData);
//                    break;
//                default:
//                    logger.warning("未知报警级别: " + alarmData.getAlarmType());
//            }
//
        } catch (Exception e) {
            logger.log(Level.SEVERE, "处理报警信息失败", e);
        }
    }
    
    /**
     * 处理回波图信息
     */
    public void processEchoData(SonarEchoData echoData) {
        try {
            logger.info("开始处理回波图信息: ID=" + echoData.getId() + ", 设备=" + echoData.getDeviceCode() + ", 时间=" + echoData.getAcTime());
            SonarEchoDto sonarEchoDto = new SonarEchoDto();
            sonarEchoDto.setDeviceCode(echoData.getDeviceCode());
            sonarEchoDto.setAcTime(echoData.getAcTime());
            sonarEchoDto.setNumPing(echoData.getNumPing());
            sonarEchoDto.setNumDot(echoData.getNumDot());
            sonarEchoDto.setDr(echoData.getDR());
            sonarEchoDto.setDbMax(echoData.getDbMax());
            sonarEchoDto.setDbMin(echoData.getDbMin());
            sonarEchoDto.setAverSv(echoData.getAverSV());
            sonarEchoDto.setAvrMax(echoData.getAvrMax());
            sonarEchoDto.setAvrMin(echoData.getAvrMin());
            sonarEchoDto.setBufferSonar(echoData.getBufferSonar());
            sonarEchoDto.setBufferSizeSonar(echoData.getBufferSizeSonar());
            sonarEchoDto.setBufferTimeStamp(echoData.getBufferTimeStamp());
            sonarEchoDto.setBufferSizeTimeStamp(echoData.getBufferSizeTimeStamp());
            sonarEchoDto.setType(1);
            sonarEchoService.save(sonarEchoDto);
//            logger.info("回波图信息: " + echoData.getEchoImageInfo());
//            logger.info("SV值范围: " + echoData.getSVRangeInfo());
//            // 处理SV值数组
//            if (echoData.getBufferSonar() != null && echoData.getBufferSizeSonar() != null) {
//                processSonarBuffer(echoData);
//            }
//            // 处理时间戳数组
//            if (echoData.getBufferTimeStamp() != null && echoData.getBufferSizeTimeStamp() != null) {
//                processTimeStampBuffer(echoData);
//            }
//            logger.info("回波图信息:"+echoData.toString());
            logger.info("回波图信息处理完成");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "处理回波图信息失败", e);
        }
    }
    
    /**
     * 处理声呐参数信息
     */
    public void processParameterData(SonarParameterData parameterData) {
        try {
            logger.info("开始处理声呐参数信息: ID=" + parameterData.getId() + ", 设备=" + parameterData.getDeviceCode() + ", 时间=" + parameterData.getChangeTime()
                    + ", 深度范围=" + parameterData.getDepthRangeInfo() + ", 截面面积=" + parameterData.getCrossSectionInfo() + ", 累加时间=" + parameterData.getAccumulationTimeInfo());
            SonarParameterDto sonarParameterDto = new SonarParameterDto();
            sonarParameterDto.setDeviceCode(parameterData.getDeviceCode());
            sonarParameterDto.setAvrMax(parameterData.getAvrMax());
            sonarParameterDto.setAvrMin(parameterData.getAvrMin());
            sonarParameterDto.setCrossSectionArea(parameterData.getCrossSectionArea());
            sonarParameterDto.setMapParaValue(JSONArray.toJSONString(parameterData.getMapParaValue()));
            sonarParameterDto.setWeightOrFluxAccTime(parameterData.getWeightOrFluxAccTime());
            sonarParameterDto.setChangeTime(parameterData.getChangeTime());
            sonarParameterService.save(sonarParameterDto);
//            // 处理报警阈值
//            if (parameterData.getMapParaValue() != null) {
//                for (String key : parameterData.getMapParaValue().keySet()) {
//                    String threshold = parameterData.getMapParaValue().get(key);
//                    String bioTypeName = getBioTypeName(key);
//                    logger.info("生物类别 " + key + ": " + bioTypeName + " 报警阈值: " + threshold);
//                }
//            }
            logger.info("声呐参数信息处理完成");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "处理声呐参数信息失败", e);
        }
    }
    
    /**
     * 处理平均SV回波图信息
     */
    public void processSVEchoData(SonarEchoData svEchoData) {
        try {
            logger.info("开始处理平均SV回波图信息: ID=" + svEchoData.getId() + ", 设备=" + svEchoData.getDeviceCode() + ", 时间=" + svEchoData.getAcTime());
            SonarEchoDto sonarEchoDto = new SonarEchoDto();
            sonarEchoDto.setDeviceCode(svEchoData.getDeviceCode());
            sonarEchoDto.setAcTime(svEchoData.getAcTime());
            sonarEchoDto.setNumPing(svEchoData.getNumPing());
            sonarEchoDto.setNumDot(svEchoData.getNumDot());
            sonarEchoDto.setDr(svEchoData.getDR());
            sonarEchoDto.setDbMax(svEchoData.getDbMax());
            sonarEchoDto.setDbMin(svEchoData.getDbMin());
            sonarEchoDto.setAverSv(svEchoData.getAverSV());
            sonarEchoDto.setAvrMax(svEchoData.getAvrMax());
            sonarEchoDto.setAvrMin(svEchoData.getAvrMin());
            sonarEchoDto.setBufferSonar(svEchoData.getBufferSonar());
            sonarEchoDto.setBufferSizeSonar(svEchoData.getBufferSizeSonar());
            sonarEchoDto.setBufferTimeStamp(svEchoData.getBufferTimeStamp());
            sonarEchoDto.setBufferSizeTimeStamp(svEchoData.getBufferSizeTimeStamp());
            sonarEchoDto.setType(2);
            sonarEchoService.save(sonarEchoDto);
            // 平均SV回波图处理逻辑与普通回波图类似
            //processEchoData(svEchoData);
            //logger.info("平均SV回波图信息："+svEchoData.toString());
            logger.info("平均SV回波图信息处理完成");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "处理平均SV回波图信息失败", e);
        }
    }
    
    /**
     * 处理单体目标信息
     */
    public void processSingleTargetData(SingleTargetData singleTargetData) {
        try {
            logger.info("开始处理单体目标信息: ID=" + singleTargetData.getId() + ", 设备=" + singleTargetData.getDeviceCode() + ", 时间=" + singleTargetData.getAcTime());
            SonarSingleTarget sonarSingleTarget = new SonarSingleTarget();
            sonarSingleTarget.setId(UuidUtil.getShortUuid());
            sonarSingleTarget.setDeviceCode(singleTargetData.getDeviceCode());
            sonarSingleTarget.setAcTime(singleTargetData.getAcTime());
            sonarSingleTarget.setNum(singleTargetData.getNum());
            // 处理每个单体目标
            if (singleTargetData.getArraySingleTarget() != null) {
                for (SingleTargetData.SingleValue singleValue : singleTargetData.getArraySingleTarget()) {
                    logger.info("单体目标: TS=" + singleValue.getDTS() + " dB, 距离=" + singleValue.getDRange() + 
                               " m, 时间=" + singleValue.getLTime() + ", 生物类别=" + singleValue.getNBioTypeCode());
                    SonarSingleValueDto sonarSingleValueDto = new SonarSingleValueDto();
                    sonarSingleValueDto.setSingleTargetId(sonarSingleTarget.getId());
                    sonarSingleValueDto.setDTs(singleValue.getDTS());
                    sonarSingleValueDto.setDRange(singleValue.getDRange());
                    sonarSingleValueDto.setLTime(singleValue.getLTime()+"");
                    sonarSingleValueDto.setNBioTypeCode(singleValue.getNBioTypeCode());
                    sonarSingleValueService.save(sonarSingleValueDto);
                }
            }
            sonarSingleTargetService.save(sonarSingleTarget);
            //logger.info("单体目标信息："+singleTargetData.toString());
            logger.info("单体目标信息处理完成");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "处理单体目标信息失败", e);
        }
    }
    
    /**
     * 获取生物量参数名称
     */
    private String getBiomassParameterName(String code) {
        switch (code) {
            case SonarBiomassData.BIOMASS_DENSITY:
                return "生物密度(个/m³)";
            case SonarBiomassData.BIOMASS_WEIGHT_ACCUMULATED:
                return "生物重量累加值(kg)";
            case SonarBiomassData.BIOMASS_WEIGHT:
                return "生物重量(kg)";
            case SonarBiomassData.AVERAGE_SV:
                return "平均SV(dB)";
            default:
                return "未知参数";
        }
    }
    
    /**
     * 获取生物类别名称
     */
    private String getBioTypeName(String code) {
        switch (code) {
            case "1701":
                return "毛虾";
            case "1702":
                return "水母";
            case "1703":
                return "鱼群";
            case "1704":
                return "藻类";
            case "1705":
                return "其他";
            default:
                return "未知";
        }
    }
    
    /**
     * 处理一级报警
     */
    private void handleLevelOneAlarm(SonarAlarmData alarmData) {
        logger.severe("处理一级报警: " + alarmData.getAlarmDesc());
        // 一级报警处理逻辑：立即通知、记录日志、可能触发紧急响应等
    }
    
    /**
     * 处理二级报警
     */
    private void handleLevelTwoAlarm(SonarAlarmData alarmData) {
        logger.warning("处理二级报警: " + alarmData.getAlarmDesc());
        // 二级报警处理逻辑：及时通知、记录日志等
    }
    
    /**
     * 处理三级报警
     */
    private void handleLevelThreeAlarm(SonarAlarmData alarmData) {
        logger.info("处理三级报警: " + alarmData.getAlarmDesc());
        // 三级报警处理逻辑：记录日志、可能通知等
    }
    
    /**
     * 处理声呐SV值数组
     */
    private void processSonarBuffer(SonarEchoData echoData) {
        try {
            // 这里可以添加具体的SV值数组处理逻辑
            // 比如：数据解析、图像生成、数据存储等
            logger.info("SV值数组大小: " + echoData.getBufferSizeSonar() + " bytes");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "处理SV值数组失败", e);
        }
    }
    
    /**
     * 处理时间戳数组
     */
    private void processTimeStampBuffer(SonarEchoData echoData) {
        try {
            // 这里可以添加具体的时间戳数组处理逻辑
            logger.info("时间戳数组大小: " + echoData.getBufferSizeTimeStamp() + " bytes");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "处理时间戳数组失败", e);
        }
    }
} 