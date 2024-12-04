package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.dto.basewx.my.VideoEventExport;
import com.pd.server.main.service.*;
import com.pd.server.util.DateUtil;
import com.pd.server.util.DateUtils;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/export")
public class ExportFileController extends BaseWxController{

    private static final Logger LOG = LoggerFactory.getLogger(ExportFileController.class);
    public static final String BUSINESS_NAME = "导出模块";

    @Resource
    private EquipmentFileService equipmentFileService;
    @Resource
    private EquipmentFileAlarmEventService equipmentFileAlarmEventService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private WaterEquipmentService waterEquipmentService;
    @Resource
    private PredationNumService predationNumService;
    @Resource
    private DeviceStateLogService deviceStateLogService;
    @Resource
    private WaterProEquipService waterProEquipService;
    @Resource
    private EquipmentTyEventService equipmentTyEventService;
    @Resource
    private EquipmentFileEventService equipmentFileEventService;
    @Resource
    private PointerDayService pointerDayService;
    @Resource
    private PointerSecondService pointerSecondService;
    @Resource
    private AppearNumbersService appearNumbersService;
    @Resource
    private VideoEventService videoEventService;
    @Resource
    private CurrentMeterService currentMeterService;
    @Resource
    private MeteorologicalDataService meteorologicalDataService;
    @Resource
    private TurbidityService turbidityService;
    @Resource
    private WaterQualityNewService waterQualityNewService;
    @Resource
    private WaveDataService waveDataService;
    @Resource
    private AzimuthAngleUniqueService azimuthAngleUniqueService;

    /**
     * 方位角统计(历史)导出
     */
    @GetMapping("/exportAzimuthAngleUnique")
    public void exportAzimuthAngleUnique(HttpServletRequest request, HttpServletResponse response){
        try {
            List<String> sbbhs = new ArrayList<>();
            if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                sbbhs = waterProEquipService.findSbsnByXmbh(request.getParameter("xmbh"));
            }
            AzimuthAngleUniqueExample example = new AzimuthAngleUniqueExample();
            AzimuthAngleUniqueExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSbbhEqualTo(request.getParameter("sbbh"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
            }
            example.setOrderByClause(" fz desc ");
            List<AzimuthAngleUnique> lists = azimuthAngleUniqueService.list(example);
            //导出
            HSSFWorkbook workbook = new HSSFWorkbook();
            //设置字体大小
            HSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "方位角历史统计(" + new Date().getTime() + ").xls";
            HSSFSheet sheet = workbook.createSheet("方位角统计");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            HSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("所属机构","设备编号","设备名称","日期","分钟","发声次数","北-北东","北东-东","东-东南","东南-南","南-南西","南西-西","西-西北","西北-北");
            for(int i=0;i<titleStrList.size();i++){
                HSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria caEquip = waterEquipmentExample.createCriteria();
            caEquip.andSblbEqualTo("0001");
            caEquip.andDqzlEqualTo("A4");
            List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
            Map<String, String> mapSbxh = waterEquipmentList.stream().collect(Collectors.toMap(p -> p.getSbsn(), p -> p.getSbmc()));
            Map<String,String> mapDept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
            int i=0;
            for(AzimuthAngleUnique entity : lists){
                if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                    if(sbbhs.contains(entity.getSbbh())){
                        HSSFRow comRow = sheet.createRow(i+1);
                        HSSFCell comCell0 = comRow.createCell(0);
                        comCell0.setCellValue(mapDept.get(entity.getDeptcode()));
                        comCell0.setCellStyle(cellStyleCommon);
                        HSSFCell comCell1 = comRow.createCell(1);
                        comCell1.setCellValue(entity.getSbbh());
                        comCell1.setCellStyle(cellStyleCommon);
                        HSSFCell comCell2 = comRow.createCell(2);
                        comCell2.setCellValue(mapSbxh.get(entity.getSbbh()));
                        comCell2.setCellStyle(cellStyleCommon);
                        HSSFCell comCell3 = comRow.createCell(3);
                        comCell3.setCellValue(entity.getRq());
                        comCell3.setCellStyle(cellStyleCommon);
                        HSSFCell comCell4 = comRow.createCell(4);
                        comCell4.setCellValue(entity.getFz());
                        comCell4.setCellStyle(cellStyleCommon);
                        HSSFCell comCell5 = comRow.createCell(5);
                        comCell5.setCellValue(entity.getTs());
                        comCell5.setCellStyle(cellStyleCommon);
                        HSSFCell comCell6 = comRow.createCell(6);
                        comCell6.setCellValue(entity.getNorthNortheast());
                        comCell6.setCellStyle(cellStyleCommon);
                        HSSFCell comCell7 = comRow.createCell(7);
                        comCell7.setCellValue(entity.getNortheastEast());
                        comCell7.setCellStyle(cellStyleCommon);
                        HSSFCell comCell8 = comRow.createCell(8);
                        comCell8.setCellValue(entity.getEastEastsouth());
                        comCell8.setCellStyle(cellStyleCommon);
                        HSSFCell comCell9 = comRow.createCell(9);
                        comCell9.setCellValue(entity.getEastsouthSouth());
                        comCell9.setCellStyle(cellStyleCommon);
                        HSSFCell comCell10 = comRow.createCell(10);
                        comCell10.setCellValue(entity.getSouthSouthwest());
                        comCell10.setCellStyle(cellStyleCommon);
                        HSSFCell comCell11 = comRow.createCell(11);
                        comCell11.setCellValue(entity.getSouthwestWest());
                        comCell11.setCellStyle(cellStyleCommon);
                        HSSFCell comCell12 = comRow.createCell(12);
                        comCell12.setCellValue(entity.getWestWestnorth());
                        comCell12.setCellStyle(cellStyleCommon);
                        HSSFCell comCell13 = comRow.createCell(13);
                        comCell13.setCellValue(entity.getWestnorthNorth());
                        comCell13.setCellStyle(cellStyleCommon);
                        i++;
                    }
                }else{
                    HSSFRow comRow = sheet.createRow(i+1);
                    HSSFCell comCell0 = comRow.createCell(0);
                    comCell0.setCellValue(mapDept.get(entity.getDeptcode()));
                    comCell0.setCellStyle(cellStyleCommon);
                    HSSFCell comCell1 = comRow.createCell(1);
                    comCell1.setCellValue(entity.getSbbh());
                    comCell1.setCellStyle(cellStyleCommon);
                    HSSFCell comCell2 = comRow.createCell(2);
                    comCell2.setCellValue(mapSbxh.get(entity.getSbbh()));
                    comCell2.setCellStyle(cellStyleCommon);
                    HSSFCell comCell3 = comRow.createCell(3);
                    comCell3.setCellValue(entity.getRq());
                    comCell3.setCellStyle(cellStyleCommon);
                    HSSFCell comCell4 = comRow.createCell(4);
                    comCell4.setCellValue(entity.getFz());
                    comCell4.setCellStyle(cellStyleCommon);
                    HSSFCell comCell5 = comRow.createCell(5);
                    comCell5.setCellValue(entity.getTs());
                    comCell5.setCellStyle(cellStyleCommon);
                    HSSFCell comCell6 = comRow.createCell(6);
                    comCell6.setCellValue(entity.getNorthNortheast());
                    comCell6.setCellStyle(cellStyleCommon);
                    HSSFCell comCell7 = comRow.createCell(7);
                    comCell7.setCellValue(entity.getNortheastEast());
                    comCell7.setCellStyle(cellStyleCommon);
                    HSSFCell comCell8 = comRow.createCell(8);
                    comCell8.setCellValue(entity.getEastEastsouth());
                    comCell8.setCellStyle(cellStyleCommon);
                    HSSFCell comCell9 = comRow.createCell(9);
                    comCell9.setCellValue(entity.getEastsouthSouth());
                    comCell9.setCellStyle(cellStyleCommon);
                    HSSFCell comCell10 = comRow.createCell(10);
                    comCell10.setCellValue(entity.getSouthSouthwest());
                    comCell10.setCellStyle(cellStyleCommon);
                    HSSFCell comCell11 = comRow.createCell(11);
                    comCell11.setCellValue(entity.getSouthwestWest());
                    comCell11.setCellStyle(cellStyleCommon);
                    HSSFCell comCell12 = comRow.createCell(12);
                    comCell12.setCellValue(entity.getWestWestnorth());
                    comCell12.setCellStyle(cellStyleCommon);
                    HSSFCell comCell13 = comRow.createCell(13);
                    comCell13.setCellValue(entity.getWestnorthNorth());
                    comCell13.setCellStyle(cellStyleCommon);
                    i++;
                }
            }
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            String agent = request.getHeader("User-Agent");
            if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            response.setCharacterEncoding("utf-8");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 海浪数据导出
     */
    @GetMapping("/exportWaveData")
    public void exportWaveData(HttpServletRequest request, HttpServletResponse response){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 水质数据导出
     */
    @GetMapping("/exportWaterQualityNew")
    public void exportWaterQualityNew(HttpServletRequest request, HttpServletResponse response){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 温盐深浊度仪数据导出
     */
    @GetMapping("/exportTurbidity")
    public void exportTurbidity(HttpServletRequest request, HttpServletResponse response){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 气象数据导出
     */
    @GetMapping("/exportMeteorologicalData")
    public void exportMeteorologicalData(HttpServletRequest request, HttpServletResponse response){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 海流计数据导出
     */
    @GetMapping("/exportCurrentMeter")
    public void exportCurrentMeter(HttpServletRequest request, HttpServletResponse response){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 分析视频事件导出
     */
    @GetMapping("/exportVideoEvent")
    public void exportVideoEvent(HttpServletRequest request, HttpServletResponse response){
        try {
            VideoEventExample example = new VideoEventExample();
            VideoEventExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSbbhEqualTo(request.getParameter("sbbh"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("sm"))){
                ca.andSmEqualTo(request.getParameter("sm"));
            }
            if(!StringUtils.isEmpty(request.getParameter("sfysp"))){
                ca.andSfyspEqualTo(Integer.parseInt(request.getParameter("sfysp")));
            }
            example.setOrderByClause(" kssj desc ");
            List<VideoEventExport> videoEventList = videoEventService.selectExportByExample(example);
            //导出
            HSSFWorkbook workbook = new HSSFWorkbook();
            //设置字体大小
            HSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            //设置字体大小,加粗
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "分析视频事件(" + new Date().getTime() + ").xls";
            HSSFSheet sheet = workbook.createSheet("分析视频事件");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            HSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("监测点","设备sn","开始时间","结束时间","核查情况");
            for(int i=0;i<titleStrList.size();i++){
                HSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria caEquip = waterEquipmentExample.createCriteria();
            caEquip.andSblbEqualTo("0001");
            List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
            Map<String, String> mapSbxh = waterEquipmentList.stream().collect(Collectors.toMap(p -> p.getSbsn(), p -> p.getSbmc()));
            for(int i=0;i<videoEventList.size();i++){
                VideoEventExport videoEventExport = videoEventList.get(i);
                HSSFRow comRow = sheet.createRow(i+1);
                HSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(mapSbxh.get(videoEventExport.getSbbh()));
                comCell0.setCellStyle(cellStyleCommon);
                HSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(videoEventExport.getSbbh());
                comCell1.setCellStyle(cellStyleCommon);
                HSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(videoEventExport.getKssj());
                comCell2.setCellStyle(cellStyleCommon);
                HSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(videoEventExport.getJssj());
                comCell3.setCellStyle(cellStyleCommon);
                HSSFCell comCell4 = comRow.createCell(4);
                if("0".equals(videoEventExport.getHcjg())){
                    comCell4.setCellValue("未核查");
                }else if("1".equals(videoEventExport.getHcjg())){
                    comCell4.setCellValue("核查通过");
                }else if("2".equals(videoEventExport.getHcjg())){
                    comCell4.setCellValue("不通过");
                }
                comCell4.setCellStyle(cellStyleCommon);
            }
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            String agent = request.getHeader("User-Agent");
            if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            response.setCharacterEncoding("utf-8");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 实时分析视频事件导出
     */
    @GetMapping("/exportVideoEventSS")
    public void exportVideoEventSS(HttpServletRequest request, HttpServletResponse response){
        try {
            VideoEventExample example = new VideoEventExample();
            VideoEventExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSbbhEqualTo(request.getParameter("sbbh"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("sm"))){
                ca.andSmEqualTo(request.getParameter("sm"));
            }
            ca.andSfyspEqualTo(2);
            example.setOrderByClause(" kssj desc ");
            List<VideoEvent> videoEventList = videoEventService.selectByExample(example);
            //导出
            HSSFWorkbook workbook = new HSSFWorkbook();
            //设置字体大小
            HSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            //设置字体大小,加粗
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "实时分析视频事件(" + new Date().getTime() + ").xls";
            HSSFSheet sheet = workbook.createSheet("实时分析视频事件");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            HSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("监测点","设备sn","开始时间","结束时间","核查情况");
            for(int i=0;i<titleStrList.size();i++){
                HSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria caEquip = waterEquipmentExample.createCriteria();
            caEquip.andSblbEqualTo("0001");
            List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
            Map<String, String> mapSbxh = waterEquipmentList.stream().collect(Collectors.toMap(p -> p.getSbsn(), p -> p.getSbmc()));
            for(int i=0;i<videoEventList.size();i++){
                VideoEvent videoEvent = videoEventList.get(i);
                HSSFRow comRow = sheet.createRow(i+1);
                HSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(mapSbxh.get(videoEvent.getSbbh()));
                comCell0.setCellStyle(cellStyleCommon);
                HSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(videoEvent.getSbbh());
                comCell1.setCellStyle(cellStyleCommon);
                HSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(videoEvent.getKssj());
                comCell2.setCellStyle(cellStyleCommon);
                HSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(videoEvent.getJssj());
                comCell3.setCellStyle(cellStyleCommon);
                HSSFCell comCell4 = comRow.createCell(4);
                if("0".equals(videoEvent.getSm())){
                    comCell4.setCellValue("未核查");
                }else if("1".equals(videoEvent.getSm())){
                    comCell4.setCellValue("核查通过");
                }else if("2".equals(videoEvent.getSm())){
                    comCell4.setCellValue("不通过");
                }
                comCell4.setCellStyle(cellStyleCommon);
            }
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            String agent = request.getHeader("User-Agent");
            if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            response.setCharacterEncoding("utf-8");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 出现次数导出
     */
    @GetMapping("/exportAlarmNumber")
    public void exportAlarmNumber(HttpServletRequest request, HttpServletResponse response){
        try {
            AppearNumbersDto dto = new AppearNumbersDto();
            AppearNumbersExample example = new AppearNumbersExample();
            AppearNumbersExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andFzGreaterThanOrEqualTo(request.getParameter("stime"));
                dto.setStime(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andFzLessThanOrEqualTo(request.getParameter("etime"));
                dto.setEtime(request.getParameter("etime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSbbhEqualTo(request.getParameter("sbbh"));
                dto.setSbbh(request.getParameter("sbbh"));
            }
            example.setOrderByClause(" fz desc ");
            List<AppearNumbers> appearNumbersList = new ArrayList<>();
            if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                dto.setXmbh(request.getParameter("xmbh"));
                appearNumbersList = appearNumbersService.selectByExampleSpecial(dto);
            }else{
                appearNumbersList = appearNumbersService.list(example);
            }
            //导出
            HSSFWorkbook workbook = new HSSFWorkbook();
            //设置字体大小
            HSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            //设置字体大小,加粗
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "出现次数(" + new Date().getTime() + ").xls";
            HSSFSheet sheet = workbook.createSheet("出现次数");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            HSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("监测点","设备名称","设备sn","出现时间","出现次数");
            for(int i=0;i<titleStrList.size();i++){
                HSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapDept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
            WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria caEquip = waterEquipmentExample.createCriteria();
            caEquip.andSblbEqualTo("0001");
            List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
            Map<String, String> mapSbxh = waterEquipmentList.stream().collect(Collectors.toMap(p -> p.getSbsn(), p -> p.getSbmc()));
            for(int i=0;i<appearNumbersList.size();i++){
                AppearNumbers appearNumbers = appearNumbersList.get(i);
                HSSFRow comRow = sheet.createRow(i+1);
                HSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(mapDept.get(appearNumbers.getDeptcode()));
                comCell0.setCellStyle(cellStyleCommon);
                HSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(mapSbxh.get(appearNumbers.getSbbh()));
                comCell1.setCellStyle(cellStyleCommon);
                HSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(appearNumbers.getSbbh());
                comCell2.setCellStyle(cellStyleCommon);
                HSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(appearNumbers.getFz());
                comCell3.setCellStyle(cellStyleCommon);
                HSSFCell comCell4 = comRow.createCell(4);
                comCell4.setCellValue(appearNumbers.getAlarmNum());
                comCell4.setCellStyle(cellStyleCommon);
            }
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            String agent = request.getHeader("User-Agent");
            if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            response.setCharacterEncoding("utf-8");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 出现事件导出
     */
    @GetMapping("/exportAlarmEvent")
    public void exportAlarmEvent(HttpServletRequest request, HttpServletResponse response){
        try {
            EquipmentFileAlarmEventDto dto = new EquipmentFileAlarmEventDto();
            EquipmentFileAlarmEventExample example = new EquipmentFileAlarmEventExample();
            EquipmentFileAlarmEventExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSbbhEqualTo(request.getParameter("sbbh"));
                dto.setSbbh(request.getParameter("sbbh"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andBjsjGreaterThanOrEqualTo(request.getParameter("stime"));
                dto.setStime(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andBjsjLessThanOrEqualTo(request.getParameter("etime"));
                dto.setEtime(request.getParameter("etime"));
            }
            example.setOrderByClause(" bjsj desc,xh desc ");
            List<EquipmentFileAlarmEvent> equipmentFileAlarmEventList = new ArrayList<>();
            if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                dto.setXmbh(request.getParameter("xmbh"));
                equipmentFileAlarmEventList = equipmentFileAlarmEventService.selectByExampleSpecial(dto);
            }else{
                equipmentFileAlarmEventList = equipmentFileAlarmEventService.list(example);
            }
            //导出
            HSSFWorkbook workbook = new HSSFWorkbook();
            //设置字体大小
            HSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "出现事件(" + new Date().getTime() + ").xls";
            HSSFSheet sheet = workbook.createSheet("出现事件");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            HSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("监测点","设备名称","设备sn","出现时间","出现次数","事件次数");
            for(int i=0;i<titleStrList.size();i++){
                HSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapDept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
            WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria caEquip = waterEquipmentExample.createCriteria();
            caEquip.andSblbEqualTo("0001");
            List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
            Map<String, String> mapSbxh = waterEquipmentList.stream().collect(Collectors.toMap(p -> p.getSbsn(), p -> p.getSbmc()));
            for(int i=0;i<equipmentFileAlarmEventList.size();i++){
                EquipmentFileAlarmEvent alarmEvent = equipmentFileAlarmEventList.get(i);
                HSSFRow comRow = sheet.createRow(i+1);
                HSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(mapDept.get(alarmEvent.getDeptcode()));
                comCell0.setCellStyle(cellStyleCommon);
                HSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(mapSbxh.get(alarmEvent.getSbbh()));
                comCell1.setCellStyle(cellStyleCommon);
                HSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(alarmEvent.getSbbh());
                comCell2.setCellStyle(cellStyleCommon);
                HSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(alarmEvent.getEventTime());
                comCell3.setCellStyle(cellStyleCommon);
                HSSFCell comCell4 = comRow.createCell(4);
                comCell4.setCellValue(alarmEvent.getAlarmNum());
                comCell4.setCellStyle(cellStyleCommon);
                HSSFCell comCell5 = comRow.createCell(5);
                comCell5.setCellValue(1);
                comCell5.setCellStyle(cellStyleCommon);
            }
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            String agent = request.getHeader("User-Agent");
            if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            response.setCharacterEncoding("utf-8");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 实时水下环境指针预警数据
     */
    @GetMapping("/exportPointerSecond")
    public void exportPointerSecond(HttpServletRequest request, HttpServletResponse response){
        try {
            List<String> sbbhs = new ArrayList<>();
            if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                sbbhs = waterProEquipService.findSbsnByXmbh(request.getParameter("xmbh"));
            }
            PointerSecondExample example = new PointerSecondExample();
            PointerSecondExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSmEqualTo(request.getParameter("sbbh"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andCjsjGreaterThanOrEqualTo(request.getParameter("stime")+" 00:00:00");
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andCjsjLessThanOrEqualTo(request.getParameter("etime")+" 23:59:59");
            }
            example.setOrderByClause(" cjsj desc ");
            List<PointerSecond> lists = pointerSecondService.selectByExample(example);
            //导出
            HSSFWorkbook workbook = new HSSFWorkbook();
            //设置字体大小
            HSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "实时水下环境指针预警数据(" + new Date().getTime() + ").xls";
            HSSFSheet sheet = workbook.createSheet("预警数据");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            HSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("设备名称","设备编号","预警数值","预警时间");
            for(int i=0;i<titleStrList.size();i++){
                HSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria caEquip = waterEquipmentExample.createCriteria();
            caEquip.andSblbEqualTo("0001");
            List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
            Map<String, String> mapSbxh = waterEquipmentList.stream().collect(Collectors.toMap(p -> p.getSbsn(), p -> p.getSbmc()));
            int i=0;
            for(PointerSecond pointerSecond : lists){
                if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                    if(sbbhs.contains(pointerSecond.getSm())){
                        HSSFRow comRow = sheet.createRow(i+1);
                        HSSFCell comCell0 = comRow.createCell(0);
                        comCell0.setCellValue(mapSbxh.get(pointerSecond.getSm()));
                        comCell0.setCellStyle(cellStyleCommon);
                        HSSFCell comCell1 = comRow.createCell(1);
                        comCell1.setCellValue(pointerSecond.getSm());
                        comCell1.setCellStyle(cellStyleCommon);
                        HSSFCell comCell2 = comRow.createCell(2);
                        comCell2.setCellValue(pointerSecond.getDecibelValue());
                        comCell2.setCellStyle(cellStyleCommon);
                        HSSFCell comCell3 = comRow.createCell(3);
                        comCell3.setCellValue(DateUtil.getFormatDate(pointerSecond.getCjsj(),"yyyy-MM-dd HH:mm:ss"));
                        comCell3.setCellStyle(cellStyleCommon);
                        i++;
                    }
                }else{
                    HSSFRow comRow = sheet.createRow(i+1);
                    HSSFCell comCell0 = comRow.createCell(0);
                    comCell0.setCellValue(mapSbxh.get(pointerSecond.getSm()));
                    comCell0.setCellStyle(cellStyleCommon);
                    HSSFCell comCell1 = comRow.createCell(1);
                    comCell1.setCellValue(pointerSecond.getSm());
                    comCell1.setCellStyle(cellStyleCommon);
                    HSSFCell comCell2 = comRow.createCell(2);
                    comCell2.setCellValue(pointerSecond.getDecibelValue());
                    comCell2.setCellStyle(cellStyleCommon);
                    HSSFCell comCell3 = comRow.createCell(3);
                    comCell3.setCellValue(DateUtil.getFormatDate(pointerSecond.getCjsj(),"yyyy-MM-dd HH:mm:ss"));
                    comCell3.setCellStyle(cellStyleCommon);
                    i++;
                }
            }
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            String agent = request.getHeader("User-Agent");
            if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            response.setCharacterEncoding("utf-8");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 当日水下环境指针预警数据
     */
    @GetMapping("/exportPointerDay")
    public void exportPointerDay(HttpServletRequest request, HttpServletResponse response){
        try {
            List<String> sbbhs = new ArrayList<>();
            if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                sbbhs = waterProEquipService.findSbsnByXmbh(request.getParameter("xmbh"));
            }
            PointerDayExample example = new PointerDayExample();
            PointerDayExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSmEqualTo(request.getParameter("sbbh"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andCjsjGreaterThanOrEqualTo(request.getParameter("stime")+" 00:00:00");
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andCjsjLessThanOrEqualTo(request.getParameter("etime")+" 23:59:59");
            }
            example.setOrderByClause(" cjsj desc ");
            List<PointerDay> lists = pointerDayService.selectByExample(example);
            //导出
            HSSFWorkbook workbook = new HSSFWorkbook();
            //设置字体大小
            HSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "当日水下环境指针预警数据(" + new Date().getTime() + ").xls";
            HSSFSheet sheet = workbook.createSheet("预警数据");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            HSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("设备名称","设备编号","预警数值","预警时间");
            for(int i=0;i<titleStrList.size();i++){
                HSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria caEquip = waterEquipmentExample.createCriteria();
            caEquip.andSblbEqualTo("0001");
            List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
            Map<String, String> mapSbxh = waterEquipmentList.stream().collect(Collectors.toMap(p -> p.getSbsn(), p -> p.getSbmc()));
            int i=0;
            for(PointerDay pointerDay : lists){
                if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                    if(sbbhs.contains(pointerDay.getSm())){
                        HSSFRow comRow = sheet.createRow(i+1);
                        HSSFCell comCell0 = comRow.createCell(0);
                        comCell0.setCellValue(mapSbxh.get(pointerDay.getSm()));
                        comCell0.setCellStyle(cellStyleCommon);
                        HSSFCell comCell1 = comRow.createCell(1);
                        comCell1.setCellValue(pointerDay.getSm());
                        comCell1.setCellStyle(cellStyleCommon);
                        HSSFCell comCell2 = comRow.createCell(2);
                        comCell2.setCellValue(pointerDay.getDecibelValue());
                        comCell2.setCellStyle(cellStyleCommon);
                        HSSFCell comCell3 = comRow.createCell(3);
                        comCell3.setCellValue(DateUtil.getFormatDate(pointerDay.getCjsj(),"yyyy-MM-dd HH:mm:ss"));
                        comCell3.setCellStyle(cellStyleCommon);
                        i++;
                    }
                }else{
                    HSSFRow comRow = sheet.createRow(i+1);
                    HSSFCell comCell0 = comRow.createCell(0);
                    comCell0.setCellValue(mapSbxh.get(pointerDay.getSm()));
                    comCell0.setCellStyle(cellStyleCommon);
                    HSSFCell comCell1 = comRow.createCell(1);
                    comCell1.setCellValue(pointerDay.getSm());
                    comCell1.setCellStyle(cellStyleCommon);
                    HSSFCell comCell2 = comRow.createCell(2);
                    comCell2.setCellValue(pointerDay.getDecibelValue());
                    comCell2.setCellStyle(cellStyleCommon);
                    HSSFCell comCell3 = comRow.createCell(3);
                    comCell3.setCellValue(DateUtil.getFormatDate(pointerDay.getCjsj(),"yyyy-MM-dd HH:mm:ss"));
                    comCell3.setCellStyle(cellStyleCommon);
                    i++;
                }
            }
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            String agent = request.getHeader("User-Agent");
            if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            response.setCharacterEncoding("utf-8");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * A4设备聚类事件
     */
    @GetMapping("/exportFileEvent")
    public void exportFileEvent(HttpServletRequest request, HttpServletResponse response){
        try {
            List<String> sbbhs = new ArrayList<>();
            EquipmentFileEventDto record = new EquipmentFileEventDto();
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                record.setSbbh(request.getParameter("sbbh"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                record.setStime(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                record.setEtime(request.getParameter("etime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                sbbhs = waterProEquipService.findSbsnByXmbh(request.getParameter("xmbh"));
            }
            List<EquipmentFileEvent> equipmentFileEventList = equipmentFileEventService.selectByExampleExport(record);
            //导出
            HSSFWorkbook workbook = new HSSFWorkbook();
            //设置字体大小
            HSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "聚类统计(" + new Date().getTime() + ").xls";
            HSSFSheet sheet = workbook.createSheet("聚类统计");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            HSSFRow titleRow = sheet.createRow(0);//第1行
            sheet.setColumnWidth(4,256*12+184);
            List<String> titleStrList = Arrays.asList("声检测点位","设备编号","开始时间","结束时间","预估头数");
            for(int i=0;i<titleStrList.size();i++){
                HSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria caEquip = waterEquipmentExample.createCriteria();
            caEquip.andSblbEqualTo("0001");
            List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
            Map<String, String> mapSbxh = waterEquipmentList.stream().collect(Collectors.toMap(p -> p.getSbsn(), p -> p.getSbmc()));
            int i=0;
            for(EquipmentFileEvent fileEvent : equipmentFileEventList){
                if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                    if(sbbhs.contains(fileEvent.getSbbh())){
                        HSSFRow comRow = sheet.createRow(i+1);
                        HSSFCell comCell0 = comRow.createCell(0);
                        comCell0.setCellValue(mapSbxh.get(fileEvent.getSbbh()));
                        comCell0.setCellStyle(cellStyleCommon);
                        HSSFCell comCell1 = comRow.createCell(1);
                        comCell1.setCellValue(fileEvent.getSbbh());
                        comCell1.setCellStyle(cellStyleCommon);
                        HSSFCell comCell2 = comRow.createCell(2);
                        comCell2.setCellValue(fileEvent.getKssj());
                        comCell2.setCellStyle(cellStyleCommon);
                        HSSFCell comCell3 = comRow.createCell(3);
                        comCell3.setCellValue(fileEvent.getJssj());
                        comCell3.setCellStyle(cellStyleCommon);
                        HSSFCell comCell4 = comRow.createCell(4);
                        comCell4.setCellValue(fileEvent.getTs());
                        comCell4.setCellStyle(cellStyleCommon);
                        i++;
                    }
                }else{
                    HSSFRow comRow = sheet.createRow(i+1);
                    HSSFCell comCell0 = comRow.createCell(0);
                    comCell0.setCellValue(mapSbxh.get(fileEvent.getSbbh()));
                    comCell0.setCellStyle(cellStyleCommon);
                    HSSFCell comCell1 = comRow.createCell(1);
                    comCell1.setCellValue(fileEvent.getSbbh());
                    comCell1.setCellStyle(cellStyleCommon);
                    HSSFCell comCell2 = comRow.createCell(2);
                    comCell2.setCellValue(fileEvent.getKssj());
                    comCell2.setCellStyle(cellStyleCommon);
                    HSSFCell comCell3 = comRow.createCell(3);
                    comCell3.setCellValue(fileEvent.getJssj());
                    comCell3.setCellStyle(cellStyleCommon);
                    HSSFCell comCell4 = comRow.createCell(4);
                    comCell4.setCellValue(fileEvent.getTs());
                    comCell4.setCellStyle(cellStyleCommon);
                    i++;
                }
            }
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            String agent = request.getHeader("User-Agent");
            if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            response.setCharacterEncoding("utf-8");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 导出拖曳设备聚类事件
     */
    @GetMapping("/exportTyEvent")
    public void exportTyEvent(HttpServletRequest request, HttpServletResponse response){
        try {
            List<String> sbbhs = new ArrayList<>();
            EquipmentTyEventDto record = new EquipmentTyEventDto();
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                record.setSbbh(request.getParameter("sbbh"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                record.setStime(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                record.setEtime(request.getParameter("etime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                sbbhs = waterProEquipService.findSbsnByXmbh(request.getParameter("xmbh"));
            }
            List<EquipmentTyEvent> equipmentTyEventList = equipmentTyEventService.selectByExampleExport(record);
            //导出
            HSSFWorkbook workbook = new HSSFWorkbook();
            //设置字体大小
            HSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            HSSFFont font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "聚类统计(" + new Date().getTime() + ").xls";
            HSSFSheet sheet = workbook.createSheet("聚类统计");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            HSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("声检测点位","设备编号","开始时间","结束时间","预估头数","坐标","经度","纬度","速度");
            for(int i=0;i<titleStrList.size();i++){
                HSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
            WaterEquipmentExample.Criteria caEquip = waterEquipmentExample.createCriteria();
            caEquip.andSblbEqualTo("0001");
            List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
            Map<String, String> mapSbxh = waterEquipmentList.stream().collect(Collectors.toMap(p -> p.getSbsn(), p -> p.getSbmc()));
            int i=0;
            for(EquipmentTyEvent tyEvent : equipmentTyEventList){
                if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                    if(sbbhs.contains(tyEvent.getSbbh())){
                        HSSFRow comRow = sheet.createRow(i+1);
                        HSSFCell comCell0 = comRow.createCell(0);
                        comCell0.setCellValue(mapSbxh.get(tyEvent.getSbbh()));
                        comCell0.setCellStyle(cellStyleCommon);
                        HSSFCell comCell1 = comRow.createCell(1);
                        comCell1.setCellValue(tyEvent.getSbbh());
                        comCell1.setCellStyle(cellStyleCommon);
                        HSSFCell comCell2 = comRow.createCell(2);
                        comCell2.setCellValue(tyEvent.getKssj());
                        comCell2.setCellStyle(cellStyleCommon);
                        HSSFCell comCell3 = comRow.createCell(3);
                        comCell3.setCellValue(tyEvent.getJssj());
                        comCell3.setCellStyle(cellStyleCommon);
                        HSSFCell comCell4 = comRow.createCell(4);
                        comCell4.setCellValue(Integer.parseInt(tyEvent.getTs()));
                        comCell4.setCellStyle(cellStyleCommon);
                        comCell4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        HSSFCell comCell5 = comRow.createCell(5);
                        comCell5.setCellValue(tyEvent.getGps());
                        comCell5.setCellStyle(cellStyleCommon);
                        if(!StringUtils.isEmpty(tyEvent.getGps())&&tyEvent.getGps().split(",").length==2){
                            HSSFCell comCell6 = comRow.createCell(6);
                            comCell6.setCellValue(tyEvent.getGps().split(",")[0]);
                            comCell6.setCellStyle(cellStyleCommon);
                            HSSFCell comCell7 = comRow.createCell(7);
                            comCell7.setCellValue(tyEvent.getGps().split(",")[1]);
                            comCell7.setCellStyle(cellStyleCommon);
                        }
                        HSSFCell comCell8 = comRow.createCell(8);
                        comCell8.setCellValue(tyEvent.getSm1());
                        comCell8.setCellStyle(cellStyleCommon);
                        i++;
                    }
                }else{
                    HSSFRow comRow = sheet.createRow(i+1);
                    HSSFCell comCell0 = comRow.createCell(0);
                    comCell0.setCellValue(mapSbxh.get(tyEvent.getSbbh()));
                    comCell0.setCellStyle(cellStyleCommon);
                    HSSFCell comCell1 = comRow.createCell(1);
                    comCell1.setCellValue(tyEvent.getSbbh());
                    comCell1.setCellStyle(cellStyleCommon);
                    HSSFCell comCell2 = comRow.createCell(2);
                    comCell2.setCellValue(tyEvent.getKssj());
                    comCell2.setCellStyle(cellStyleCommon);
                    HSSFCell comCell3 = comRow.createCell(3);
                    comCell3.setCellValue(tyEvent.getJssj());
                    comCell3.setCellStyle(cellStyleCommon);
                    HSSFCell comCell4 = comRow.createCell(4);
                    comCell4.setCellValue(Integer.parseInt(tyEvent.getTs()));
                    comCell4.setCellStyle(cellStyleCommon);
                    comCell4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    HSSFCell comCell5 = comRow.createCell(5);
                    comCell5.setCellValue(tyEvent.getGps());
                    comCell5.setCellStyle(cellStyleCommon);
                    if(!StringUtils.isEmpty(tyEvent.getGps())&&tyEvent.getGps().split(",").length==2){
                        HSSFCell comCell6 = comRow.createCell(6);
                        comCell6.setCellValue(tyEvent.getGps().split(",")[0]);
                        comCell6.setCellStyle(cellStyleCommon);
                        HSSFCell comCell7 = comRow.createCell(7);
                        comCell7.setCellValue(tyEvent.getGps().split(",")[1]);
                        comCell7.setCellStyle(cellStyleCommon);
                    }
                    HSSFCell comCell8 = comRow.createCell(8);
                    comCell8.setCellValue(tyEvent.getSm1());
                    comCell8.setCellStyle(cellStyleCommon);
                    i++;
                }
            }
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            String agent = request.getHeader("User-Agent");
            if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            response.setCharacterEncoding("utf-8");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 导出设备运行日志
     */
    @GetMapping("/exportDeviceStateLog")
    public void exportDeviceStateLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DeviceStateLogExample deviceStateLogExample = new DeviceStateLogExample();
        DeviceStateLogExample.Criteria ca = deviceStateLogExample.createCriteria();
        if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
            List<String> sbbhs = waterProEquipService.findSbsnByXmbh(request.getParameter("xmbh"));
            ca.andSbbhIn(sbbhs);
        }
        if(!StringUtils.isEmpty(request.getParameter("stime"))){
            ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
        }
        if(!StringUtils.isEmpty(request.getParameter("etime"))){
            ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
        }
        deviceStateLogExample.setOrderByClause(" rq desc ");
        List<DeviceStateLog> deviceStateLogList = deviceStateLogService.selectByExample(deviceStateLogExample);
        Map<String,List<DeviceStateLog>> rqMap = deviceStateLogList.stream().collect(Collectors.groupingBy(DeviceStateLog::getRq,LinkedHashMap::new,Collectors.toList()));
        Map<String,Map<String,String>> resultMap = new LinkedHashMap<>();
        for(String rq : rqMap.keySet()){
            List<DeviceStateLog> list = rqMap.get(rq);
            Map<String,String> sbbhMap = list.stream().collect(Collectors.toMap(p -> p.getSbbh(), p-> p.getZt()));
            resultMap.put(rq,sbbhMap);
        }
        //获取所有的设备
        WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca1 = waterEquipmentExample.createCriteria();
        ca1.andGpsIsNotNull();
        waterEquipmentExample.setOrderByClause(" sblc ");
        List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
        //导出
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置字体大小
        HSSFFont fontCommon = workbook.createFont();
        fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        //设置公共单元格样式
        HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //cellStyleCommon.setFont(fontCommon);
        //设置字体加粗
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyleTitle.setFont(font);
        // 创建一个工作表
        String fileName = "设备运行日志(" + new Date().getTime() + ").xls";
        HSSFSheet sheet = workbook.createSheet("设备运行日志");
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(18);
        sheet.setDefaultRowHeight((short)(40*10));
        // 添加表头行
        HSSFRow titleRow = sheet.createRow(0);//第1行
        List<String> titleSbbhList = new ArrayList<>();
        for(int i=0;i<waterEquipmentList.size();i++){
            HSSFCell cell = titleRow.createCell(i+1);
            cell.setCellValue(waterEquipmentList.get(i).getSbmc());
            cell.setCellStyle(cellStyleTitle);
            titleSbbhList.add(waterEquipmentList.get(i).getSbsn());
        }
        int rowIndex = 1;
        for(String rq : resultMap.keySet()){
            HSSFRow rowComment = sheet.createRow(rowIndex);//第1行
            HSSFCell cell = rowComment.createCell(0);
            cell.setCellValue(rq);
            cell.setCellStyle(cellStyleCommon);
            Map<String,String> sbbhMap = resultMap.get(rq);
            int cellIndex = 1;
            for(String sbbh : titleSbbhList){
                HSSFCell cellComment = rowComment.createCell(cellIndex);
                if(!StringUtils.isEmpty(sbbhMap.get(sbbh))){
                    if("0".equals(sbbhMap.get(sbbh))){
                        cellComment.setCellValue("√");
                    }else if("1".equals(sbbhMap.get(sbbh))){
                        cellComment.setCellValue("×");
                    }else{
                        cellComment.setCellValue("");
                    }
                }
                cellComment.setCellStyle(cellStyleCommon);
                cellIndex++;
            }
            rowIndex++;
        }
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        String agent = request.getHeader("User-Agent");
        if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } else {
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
        }
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
    }

    /**
     * 江豚出现按捕食统计导出
     */
    @GetMapping("/exportByPredationNum")
    public void exportByPredationNum(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PredationNumDto dto = new PredationNumDto();
        PredationNumExample example = new PredationNumExample();
        PredationNumExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(request.getParameter("stime"))){
            ca.andCjsjGreaterThanOrEqualTo(request.getParameter("stime"),"%Y-%m-%d");
            dto.setStime(request.getParameter("stime"));
        }
        if(!StringUtils.isEmpty(request.getParameter("etime"))){
            ca.andCjsjLessThanOrEqualTo(request.getParameter("etime"),"%Y-%m-%d");
            dto.setEtime(request.getParameter("etime"));
        }
        if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
            ca.andSbbhEqualTo(request.getParameter("sbbh"));
            dto.setSbbh(request.getParameter("sbbh"));
        }
        example.setOrderByClause(" cjsj desc ");
        List<PredationNum> dataList = new ArrayList<>();
        if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
            dto.setXmbh(request.getParameter("xmbh"));
            dataList = predationNumService.selectByExampleSpecial(dto);
        }else{
            dataList = predationNumService.list(example);
        }
        //导出
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置字体大小
        HSSFFont fontCommon = workbook.createFont();
        fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        //设置公共单元格样式
        HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //cellStyleCommon.setFont(fontCommon);
        //设置字体加粗
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyleTitle.setFont(font);
        // 创建一个工作表
        String fileName = "捕食统计(" + new Date().getTime() + ").xls";
        HSSFSheet sheet = workbook.createSheet("按捕食次数统计");
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(18);
        sheet.setDefaultRowHeight((short)(40*10));
        // 添加表头行
        HSSFRow titleRow = sheet.createRow(0);//第1行
        List<String> titleStrList = Arrays.asList("所属机构","检测点","设备sn","捕食日期","鲸豚出现次数","鲸豚捕食次数","鲸豚事件次数");
        for(int i=0;i<titleStrList.size();i++){
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(titleStrList.get(i));
            cell.setCellStyle(cellStyleTitle);
        }
        Map<String,String> mapDept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
        WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria caEquip = waterEquipmentExample.createCriteria();
        caEquip.andSblbEqualTo("0001");
        List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
        Map<String, String> mapSbxh = waterEquipmentList.stream().collect(Collectors.toMap(p -> p.getSbsn(), p -> p.getSbmc()));
        for(int i=0;i<dataList.size();i++){
            PredationNum entity = dataList.get(i);
            HSSFRow comRow = sheet.createRow(i+1);
            HSSFCell comCell0 = comRow.createCell(0);
            comCell0.setCellValue(mapDept.get(entity.getDeptcode()));
            comCell0.setCellStyle(cellStyleCommon);
            HSSFCell comCell1 = comRow.createCell(1);
            comCell1.setCellValue(mapSbxh.get(entity.getSbbh()));
            comCell1.setCellStyle(cellStyleCommon);
            HSSFCell comCell2 = comRow.createCell(2);
            comCell2.setCellValue(entity.getSbbh());
            comCell2.setCellStyle(cellStyleCommon);
            HSSFCell comCell3 = comRow.createCell(3);
            comCell3.setCellValue(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd"));
            comCell3.setCellStyle(cellStyleCommon);
            HSSFCell comCell4 = comRow.createCell(4);
            comCell4.setCellValue(entity.getAlarmNum());
            comCell4.setCellStyle(cellStyleCommon);
            HSSFCell comCell5 = comRow.createCell(5);
            comCell5.setCellValue(entity.getPredationNum());
            comCell5.setCellStyle(cellStyleCommon);
            HSSFCell comCell6 = comRow.createCell(6);
            comCell6.setCellValue(entity.getSm1());
            comCell6.setCellStyle(cellStyleCommon);
        }
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        String agent = request.getHeader("User-Agent");
        if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } else {
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
        }
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
    }

    /**
     * 江豚出现按事件统计导出
     */
    @GetMapping("/exportByAlarmEvent")
    public void exportByAlarmEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PredationNumDto dto = new PredationNumDto();
        PredationNumExample example = new PredationNumExample();
        PredationNumExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
            ca.andSbbhEqualTo(request.getParameter("sbbh"));
            dto.setSbbh(request.getParameter("sbbh"));
        }
        if(!StringUtils.isEmpty(request.getParameter("stime"))){
            ca.andCjsjGreaterThanOrEqualTo(request.getParameter("stime"),"%Y-%m-%d");
            dto.setStime(request.getParameter("stime"));
        }
        if(!StringUtils.isEmpty(request.getParameter("etime"))){
            ca.andCjsjLessThanOrEqualTo(request.getParameter("etime"),"%Y-%m-%d");
            dto.setEtime(request.getParameter("etime"));
        }
        example.setOrderByClause(" cjsj desc ");
        List<PredationNum> dataList = new ArrayList<>();
        if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
            dto.setXmbh(request.getParameter("xmbh"));
            dataList = predationNumService.selectByExampleSpecial(dto);
        }else {
            dataList = predationNumService.list(example);
        }
        //导出
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置字体大小
        HSSFFont fontCommon = workbook.createFont();
        fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        //设置公共单元格样式
        HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //cellStyleCommon.setFont(fontCommon);
        //设置字体加粗
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyleTitle.setFont(font);
        // 创建一个工作表
        String fileName = "事件统计(" + new Date().getTime() + ").xls";
        HSSFSheet sheet = workbook.createSheet("按事件统计");
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(18);
        sheet.setDefaultRowHeight((short)(40*10));
        // 添加表头行
        HSSFRow titleRow = sheet.createRow(0);//第1行
        List<String> titleStrList = Arrays.asList("所属监测点","设备名称","设备sn","事件日期","事件次数","事件发生时间集合");
        for(int i=0;i<titleStrList.size();i++){
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(titleStrList.get(i));
            cell.setCellStyle(cellStyleTitle);
        }
        Map<String,String> mapDept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
        WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria waterCa = waterEquipmentExample.createCriteria();
        waterCa.andSblbEqualTo("0001");
        waterCa.andDqzlIn(Arrays.asList("A1","A4"));
        List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
        Map<String, String> mapSbxh = waterEquipmentList.stream().collect(Collectors.toMap(p -> p.getSbsn(), p -> p.getSbmc()));
        for(int i=0;i<dataList.size();i++){
            EquipmentFileAlarmEvent efae = new EquipmentFileAlarmEvent();
            PredationNum entity = dataList.get(i);
            HSSFRow comRow = sheet.createRow(i+1);
            HSSFCell comCell0 = comRow.createCell(0);
            comCell0.setCellValue(mapDept.get(entity.getDeptcode()));
            comCell0.setCellStyle(cellStyleCommon);
            efae.setDeptcode(entity.getDeptcode());
            HSSFCell comCell1 = comRow.createCell(1);
            comCell1.setCellValue(mapSbxh.get(entity.getSbbh()));
            comCell1.setCellStyle(cellStyleCommon);
            efae.setSbbh(entity.getSbbh());
            HSSFCell comCell2 = comRow.createCell(2);
            comCell2.setCellValue(entity.getSbbh());
            comCell2.setCellStyle(cellStyleCommon);
            HSSFCell comCell3 = comRow.createCell(3);
            comCell3.setCellValue(entity.getCjsj());
            comCell3.setCellStyle(cellStyleCommon);
            efae.setBjsj(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd"));
            HSSFCell comCell4 = comRow.createCell(4);
            comCell4.setCellValue(entity.getSm1());
            comCell4.setCellStyle(cellStyleCommon);
            List<EquipmentFileAlarmEvent> detailAlarmTimes = equipmentFileAlarmEventService.detailByParam(efae);
            List<String> eventTimes=  detailAlarmTimes.stream().filter(Objects::nonNull).map(EquipmentFileAlarmEvent::getEventTime).collect(Collectors.toList());
            HSSFCell comCell5 = comRow.createCell(5);
            comCell5.setCellValue(JSONArray.toJSONString(eventTimes));
            comCell5.setCellStyle(cellStyleCommon);
        }
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        String agent = request.getHeader("User-Agent");
        if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } else {
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
        }
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
    }

    /**
     * 江豚出现按分钟统计导出
     */
    @GetMapping("/exportEquipmentFile")
    public void exportEquipmentFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String deptcode = request.getParameter("deptcode");
        String stime = request.getParameter("stime");
        String etime = request.getParameter("etime");
        String sbbh = request.getParameter("sbbh");
        String type = request.getParameter("type");
        List<String> list = getUpdeptcode(deptcode);//获取部门属性
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(sbbh)){
            ca.andSbbhEqualTo(sbbh);
        }
        if(!StringUtils.isEmpty(deptcode)){
            ca.andDeptcodeIn(list);
        }
        ca.andTpljLike("%png");
        EquipmentFileExample.Criteria caor = example.or();
        if(!StringUtils.isEmpty(sbbh)){
            caor.andSbbhEqualTo(sbbh);
        }
        if(!StringUtils.isEmpty(deptcode)){
            caor.andDeptcodeIn(list);
        }
        caor.andTpljLike("%jpg");
        List<AlarmNumbersDto> lists = new ArrayList<>();
        Optional<Integer> op = null;
        if(type.equals("minute")){
            if(!StringUtils.isEmpty(stime)){
                ca.andCjsjGreaterThanOrEqualTo(stime);
            }
            if(!StringUtils.isEmpty(etime)){
                ca.andCjsjLessThanOrEqualTo(etime);
            }
            lists = equipmentFileService.statisticsAlarmNums(example);
        }else if(type.equals("hour")){
            if(!StringUtils.isEmpty(stime)){
                ca.andRqGreaterThanOrEqualTo(stime);
            }
            if(!StringUtils.isEmpty(etime)){
                ca.andRqLessThanOrEqualTo(etime);
            }
            lists = equipmentFileService.statisticsAlarmNumsByHour(example);
            op = lists.stream().map(AlarmNumbersDto::getAlarmNum).reduce(Integer::sum);
        }
        //导出
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置字体大小
        HSSFFont fontCommon = workbook.createFont();
        fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        //设置公共单元格样式
        HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //cellStyleCommon.setFont(fontCommon);
        //设置字体加粗
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyleTitle.setFont(font);
        // 创建一个工作表
        String fileName = "出现统计(" + new Date().getTime() + ").xls";
        HSSFSheet sheet = workbook.createSheet("出现时间");
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(18);
        sheet.setDefaultRowHeight((short)(40*10));
        // 添加表头行
        HSSFRow titileRow = sheet.createRow(0);//第1行
        HSSFCell headCell = titileRow.createCell(0);//第1行第1列
        headCell.setCellValue("出现时间");
        headCell.setCellStyle(cellStyleTitle);
        HSSFCell headCell1 = titileRow.createCell(1);//第1行第2列
        if(type.equals("minute")){
            headCell1.setCellValue("出现次数");
        }else if(type.equals("hour")){
            headCell1.setCellValue("占总侦测事件百分比");
        }
        headCell1.setCellStyle(cellStyleTitle);
        for(int i=0;i<lists.size();i++){
            AlarmNumbersDto entity = lists.get(i);
            if(type.equals("minute")){
                HSSFRow row = sheet.createRow(i+1);//第1行
                HSSFCell cell = row.createCell(0);//第1行第1列
                cell.setCellValue(entity.getBjsj()+" "+entity.getXs()+":"+entity.getFz());
                cell.setCellStyle(cellStyleCommon);
                HSSFCell cell1 = row.createCell(1);//第1行第2列
                cell1.setCellValue(entity.getAlarmNum());
                cell1.setCellStyle(cellStyleCommon);
            }else if(type.equals("hour")) {
                HSSFRow row = sheet.createRow(i+1);//第1行
                HSSFCell cell = row.createCell(0);//第1行第1列
                cell.setCellValue(entity.getBjsj()+" "+entity.getXs());
                cell.setCellStyle(cellStyleCommon);
                HSSFCell cell1 = row.createCell(1);//第1行第2列
                if(op.get()!=null && op.get()!=0){
                    cell1.setCellValue(div(entity.getAlarmNum(),op.get(),4)*100);
                }
                cell1.setCellStyle(cellStyleCommon);
            }
        }
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        String agent = request.getHeader("User-Agent");
        if (agent.contains("MSIE") || agent.contains("Trident") || agent.contains("Edge")) {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } else {
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + new String((fileName).getBytes("UTF-8"), "ISO-8859-1") + "\"");
        }
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1            被除数
     * @param v2            除数
     * @param scale         表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(int v1, int v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Integer.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
