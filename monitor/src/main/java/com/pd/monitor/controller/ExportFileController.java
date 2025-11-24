package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.pd.monitor.utils.CalculateUtil;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.*;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.dto.basewx.my.SpaceFileExcel;
import com.pd.server.main.dto.basewx.my.VideoEventExport;
import com.pd.server.main.service.*;
import com.pd.server.util.DateUtil;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Resource
    private SpaceFileService spaceFileService;
    @Resource
    private BeconFileTyService beconFileTyService;
    @Resource
    private BeconFileStatisticsService beconFileStatisticsService;
    @Resource
    private SolarPannelService solarPannelService;
    @Resource
    private PontoonGpsService pontoonGpsService;
    @Resource
    private EquipmentFilePClusterService equipmentFilePClusterService;
    @Resource
    private EquipmentFileTClusterService equipmentFileTClusterService;
    @Resource
    private EquipmentFilePPicService equipmentFilePPicService;
    @Resource
    private VoicePowerDeviceService voicePowerDeviceService;

    /**
     * 驱离设备工作日志导出
     */
    @GetMapping("/exportVoicePowerDevice")
    public void exportVoicePowerDevice(HttpServletRequest request, HttpServletResponse response){
        try {
            VoicePowerDeviceExample example = new VoicePowerDeviceExample();
            VoicePowerDeviceExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSbbhEqualTo(request.getParameter("sbbh"));
            }
            example.setOrderByClause(" send_time desc ");
            List<VoicePowerDevice> voicePowerDeviceList = voicePowerDeviceService.selectByExample(example);
            //导出
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            XSSFCellStyle cellStyleCommonLeft = workbook.createCellStyle();
            cellStyleCommonLeft.setAlignment(XSSFCellStyle.ALIGN_LEFT);
            cellStyleCommonLeft.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "设备工作日志(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("设备工作日志");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("设备名称","设备编号","主题","日期","开始指令下发时间","是否播放","结束指令下发时间");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            int i=0;
            for(VoicePowerDevice entity : voicePowerDeviceList){
                XSSFRow comRow = sheet.createRow(i+1);
                XSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(mapSbxh.get("sbmc-"+entity.getSbbh()));
                comCell0.setCellStyle(cellStyleCommon);
                XSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(entity.getSbbh());
                comCell1.setCellStyle(cellStyleCommon);
                XSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(entity.getTopicName());
                comCell2.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(entity.getRq());
                comCell3.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell4 = comRow.createCell(4);
                comCell4.setCellValue(DateUtil.getFormatDate(entity.getSendTime(),"yyyy-MM-dd HH:mm:ss"));
                comCell4.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell5 = comRow.createCell(5);
                comCell5.setCellValue(1==entity.getIsPlay()?"已播放":"未播放");
                comCell5.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell6 = comRow.createCell(6);
                comCell6.setCellValue(DateUtil.getFormatDate(entity.getStopTime(),"yyyy-MM-dd HH:mm:ss"));
                comCell6.setCellStyle(cellStyleCommonLeft);
                i++;
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
     * 路线图导出
     */
    @GetMapping("/exportPontoonGps")
    public void exportPontoonGps(HttpServletRequest request, HttpServletResponse response){
        try {
            PontoonGpsExample example = new PontoonGpsExample();
            PontoonGpsExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSbbhEqualTo(request.getParameter("sbbh"));
            }
            example.setOrderByClause(" cjsj desc ");
            List<PontoonGps> gpsList = pontoonGpsService.selectByExample(example);
            //导出
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            XSSFCellStyle cellStyleCommonLeft = workbook.createCellStyle();
            cellStyleCommonLeft.setAlignment(XSSFCellStyle.ALIGN_LEFT);
            cellStyleCommonLeft.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "路线图数据(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("路线图数据");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("设备名称","设备sn","采集时间","gps");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            int i=0;
            for(PontoonGps entity : gpsList){
                XSSFRow comRow = sheet.createRow(i+1);
                XSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(mapSbxh.get("sbmc-"+entity.getSbbh()));
                comCell0.setCellStyle(cellStyleCommon);
                XSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(entity.getSbbh());
                comCell1.setCellStyle(cellStyleCommon);
                XSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd HH:mm:ss"));
                comCell2.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(entity.getGps());
                comCell3.setCellStyle(cellStyleCommonLeft);
                i++;
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
     * 太阳能电池板导出
     */
    @GetMapping("/exportSolarPannel")
    public void exportSolarPannel(HttpServletRequest request, HttpServletResponse response){
        try {
            SolarPannelExample example = new SolarPannelExample();
            SolarPannelExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("deviceName"))){
                ca.andDeviceNameEqualTo(request.getParameter("deviceName"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andUpdateTimeGreaterThanOrEqualTo(request.getParameter("stime")+" 00:00:00");
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andUpdateTimeLessThanOrEqualTo(request.getParameter("etime")+" 23:59:59");
            }
            example.setOrderByClause(" update_time desc ");
            List<SolarPannel> lists = solarPannelService.list(example);
            //导出
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            XSSFCellStyle cellStyleCommonLeft = workbook.createCellStyle();
            cellStyleCommonLeft.setAlignment(XSSFCellStyle.ALIGN_LEFT);
            cellStyleCommonLeft.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "太阳能板数据(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("太阳能板数据");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("设备名称","设备编号","设备温度","电池电压","负载电压","负载电流","当日用电","当月累计用电","太阳能电压","太阳能电流","发电功率","当日累计充电","当月累计充电","电池电量","更新时间");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            int i=0;
            for(SolarPannel entity : lists){
                XSSFRow comRow = sheet.createRow(i+1);
                XSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(entity.getDeviceName());
                comCell0.setCellStyle(cellStyleCommon);
                XSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(entity.getDeviceNumber());
                comCell1.setCellStyle(cellStyleCommon);
                XSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(entity.getTemperature());
                comCell2.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(entity.getBatteryVoltage());
                comCell3.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell4 = comRow.createCell(4);
                comCell4.setCellValue(entity.getLoadVoltage());
                comCell4.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell5 = comRow.createCell(5);
                comCell5.setCellValue(entity.getLoadCurrent());
                comCell5.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell6 = comRow.createCell(6);
                comCell6.setCellValue(entity.getDailyElectricityConsumption());
                comCell6.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell7 = comRow.createCell(7);
                comCell7.setCellValue(entity.getMonthlyElectricityConsumption());
                comCell7.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell8 = comRow.createCell(8);
                comCell8.setCellValue(entity.getSolarPanelVoltage());
                comCell8.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell9 = comRow.createCell(9);
                comCell9.setCellValue(entity.getSolarPannelCurrent());
                comCell9.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell10 = comRow.createCell(10);
                comCell10.setCellValue(entity.getPowerGeneration());
                comCell10.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell11 = comRow.createCell(11);
                comCell11.setCellValue(entity.getDailyCharge());
                comCell11.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell12 = comRow.createCell(12);
                comCell12.setCellValue(entity.getMonthlyCharge());
                comCell12.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell13 = comRow.createCell(13);
                comCell13.setCellValue(entity.getBatteryPercent());
                comCell13.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell14 = comRow.createCell(14);
                comCell14.setCellValue(DateUtil.getFormatDate(entity.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
                comCell14.setCellStyle(cellStyleCommonLeft);
                i++;
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
     * 定点基站导出
     */
    @GetMapping("/exportBeconFile")
    public void exportBeconFile(HttpServletRequest request, HttpServletResponse response){
        try {
            BeconFileStatisticsExample example = new BeconFileStatisticsExample();
            BeconFileStatisticsExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("xbid"))){
                ca.andXbidEqualTo(request.getParameter("xbid"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
            }
            example.setOrderByClause(" rq desc ");
            List<BeconFileStatistics> lists = beconFileStatisticsService.selectByExample(example);
            //导出
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            XSSFCellStyle cellStyleCommonLeft = workbook.createCellStyle();
            cellStyleCommonLeft.setAlignment(XSSFCellStyle.ALIGN_LEFT);
            cellStyleCommonLeft.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "定点基站数据(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("定点基站数据");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("信标id","日期","设备编号合集","设备名称合集","gps合集");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            int i=0;
            for(BeconFileStatistics entity : lists){
                XSSFRow comRow = sheet.createRow(i+1);
                XSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(entity.getXbid());
                comCell0.setCellStyle(cellStyleCommon);
                XSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(entity.getRq());
                comCell1.setCellStyle(cellStyleCommon);
                XSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(entity.getSbbhs());
                comCell2.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(entity.getSbmcs());
                comCell3.setCellStyle(cellStyleCommonLeft);
                XSSFCell comCell4 = comRow.createCell(4);
                comCell4.setCellValue(entity.getGps());
                comCell4.setCellStyle(cellStyleCommonLeft);
                i++;
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
     * 移动基站导出
     */
    @GetMapping("/exportBeconFileTy")
    public void exportBeconFileTy(HttpServletRequest request, HttpServletResponse response){
        try {
            List<String> sbbhs = new ArrayList<>();
            if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                sbbhs = waterProEquipService.findSbsnByXmbh(request.getParameter("xmbh"));
            }
            BeconFileTyExample example = new BeconFileTyExample();
            BeconFileTyExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSbbhEqualTo(request.getParameter("sbbh"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
            }
            example.setOrderByClause(" cjsj desc ");
            List<BeconFileTy> lists = beconFileTyService.selectByExample(example);
            //导出
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            XSSFCellStyle cellStyleCommonLeft = workbook.createCellStyle();
            cellStyleCommonLeft.setAlignment(XSSFCellStyle.ALIGN_LEFT);
            cellStyleCommonLeft.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "移动基站数据(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("移动基站数据");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("设备编号","设备名称","采集时间","信标合集","坐标","经度","纬度");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            int i=0;
            for(BeconFileTy entity : lists){
                if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                    if(sbbhs.contains(entity.getSbbh())){
                        XSSFRow comRow = sheet.createRow(i+1);
                        XSSFCell comCell0 = comRow.createCell(0);
                        comCell0.setCellValue(entity.getSbbh());
                        comCell0.setCellStyle(cellStyleCommon);
                        XSSFCell comCell1 = comRow.createCell(1);
                        comCell1.setCellValue(mapSbxh.get("sbmc-"+entity.getSbbh()));
                        comCell1.setCellStyle(cellStyleCommon);
                        XSSFCell comCell2 = comRow.createCell(2);
                        comCell2.setCellValue(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd HH:mm:ss"));
                        comCell2.setCellStyle(cellStyleCommon);
                        XSSFCell comCell3 = comRow.createCell(3);
                        comCell3.setCellValue(entity.getXbid());
                        comCell3.setCellStyle(cellStyleCommonLeft);
                        XSSFCell comCell4 = comRow.createCell(4);
                        comCell4.setCellValue(entity.getGps());
                        comCell4.setCellStyle(cellStyleCommon);
                        XSSFCell comCell5 = comRow.createCell(5);
                        comCell5.setCellValue(entity.getJd());
                        comCell5.setCellStyle(cellStyleCommon);
                        XSSFCell comCell6 = comRow.createCell(6);
                        comCell6.setCellValue(entity.getWd());
                        comCell6.setCellStyle(cellStyleCommon);
                        i++;
                    }
                }else{
                    XSSFRow comRow = sheet.createRow(i+1);
                    XSSFCell comCell0 = comRow.createCell(0);
                    comCell0.setCellValue(entity.getSbbh());
                    comCell0.setCellStyle(cellStyleCommon);
                    XSSFCell comCell1 = comRow.createCell(1);
                    comCell1.setCellValue(mapSbxh.get("sbmc-"+entity.getSbbh()));
                    comCell1.setCellStyle(cellStyleCommon);
                    XSSFCell comCell2 = comRow.createCell(2);
                    comCell2.setCellValue(entity.getCjsj());
                    comCell2.setCellStyle(cellStyleCommon);
                    XSSFCell comCell3 = comRow.createCell(3);
                    comCell3.setCellValue(entity.getXbid());
                    comCell3.setCellStyle(cellStyleCommonLeft);
                    XSSFCell comCell4 = comRow.createCell(4);
                    comCell4.setCellValue(entity.getGps());
                    comCell4.setCellStyle(cellStyleCommon);
                    XSSFCell comCell5 = comRow.createCell(5);
                    comCell5.setCellValue(entity.getJd());
                    comCell5.setCellStyle(cellStyleCommon);
                    XSSFCell comCell6 = comRow.createCell(6);
                    comCell6.setCellValue(entity.getWd());
                    comCell6.setCellStyle(cellStyleCommon);
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
     * 驱离文件管理导出
     */
    @GetMapping("/exportSpaceFile")
    public void exportSpaceFile(HttpServletRequest request, HttpServletResponse response){
        try {
            List<String> sbbhs = new ArrayList<>();
            if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                sbbhs = waterProEquipService.findSbsnByXmbh(request.getParameter("xmbh"));
            }
            SpaceFileExample example = new SpaceFileExample();
            SpaceFileExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSbbhEqualTo(request.getParameter("sbbh"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
            }
            example.setOrderByClause(" cjsj desc ");
            List<SpaceFileExcel> lists = spaceFileService.selectByExampleExcel(example);
            //导出
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            XSSFCellStyle cellStyleCommonLeft = workbook.createCellStyle();
            cellStyleCommonLeft.setAlignment(XSSFCellStyle.ALIGN_LEFT);
            cellStyleCommonLeft.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "驱离文件统计(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("驱离文件统计");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("设备编号","设备名称","日期","时间");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            Map<String,String> mapDept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
            int i=0;
            for(SpaceFileExcel entity : lists){
                if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                    if(sbbhs.contains(entity.getSbbh())){
                        XSSFRow comRow = sheet.createRow(i+1);
                        XSSFCell comCell0 = comRow.createCell(0);
                        comCell0.setCellValue(entity.getSbbh());
                        comCell0.setCellStyle(cellStyleCommon);
                        XSSFCell comCell1 = comRow.createCell(1);
                        comCell1.setCellValue(mapSbxh.get("sbmc-"+entity.getSbbh()));
                        comCell1.setCellStyle(cellStyleCommon);
                        XSSFCell comCell2 = comRow.createCell(2);
                        comCell2.setCellValue(entity.getRq());
                        comCell2.setCellStyle(cellStyleCommon);
                        XSSFCell comCell3 = comRow.createCell(3);
                        comCell3.setCellValue(entity.getGroupcjsj());
                        comCell3.setCellStyle(cellStyleCommonLeft);
                        i++;
                    }
                }else{
                    XSSFRow comRow = sheet.createRow(i+1);
                    XSSFCell comCell0 = comRow.createCell(0);
                    comCell0.setCellValue(entity.getSbbh());
                    comCell0.setCellStyle(cellStyleCommon);
                    XSSFCell comCell1 = comRow.createCell(1);
                    comCell1.setCellValue(mapSbxh.get("sbmc-"+entity.getSbbh()));
                    comCell1.setCellStyle(cellStyleCommon);
                    XSSFCell comCell2 = comRow.createCell(2);
                    comCell2.setCellValue(entity.getRq());
                    comCell2.setCellStyle(cellStyleCommon);
                    XSSFCell comCell3 = comRow.createCell(3);
                    comCell3.setCellValue(entity.getGroupcjsj());
                    comCell3.setCellStyle(cellStyleCommonLeft);
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
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "方位角历史统计(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("方位角统计");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("所属机构","设备编号","设备名称","日期","分钟","发声次数","北偏东45度","东偏北45度","东偏南45度","南偏东45度","南偏西45度","西偏南45度","西偏北45度","北偏西45度");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            Map<String,String> mapDept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
            int i=0;
            for(AzimuthAngleUnique entity : lists){
                if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                    if(sbbhs.contains(entity.getSbbh())){
                        XSSFRow comRow = sheet.createRow(i+1);
                        XSSFCell comCell0 = comRow.createCell(0);
                        comCell0.setCellValue(mapDept.get(entity.getDeptcode()));
                        comCell0.setCellStyle(cellStyleCommon);
                        XSSFCell comCell1 = comRow.createCell(1);
                        comCell1.setCellValue(entity.getSbbh());
                        comCell1.setCellStyle(cellStyleCommon);
                        XSSFCell comCell2 = comRow.createCell(2);
                        comCell2.setCellValue(mapSbxh.get("sbmc-"+entity.getSbbh()));
                        comCell2.setCellStyle(cellStyleCommon);
                        XSSFCell comCell3 = comRow.createCell(3);
                        comCell3.setCellValue(entity.getRq());
                        comCell3.setCellStyle(cellStyleCommon);
                        XSSFCell comCell4 = comRow.createCell(4);
                        comCell4.setCellValue(entity.getFz());
                        comCell4.setCellStyle(cellStyleCommon);
                        XSSFCell comCell5 = comRow.createCell(5);
                        comCell5.setCellValue(entity.getTs());
                        comCell5.setCellStyle(cellStyleCommon);
                        XSSFCell comCell6 = comRow.createCell(6);
                        comCell6.setCellValue(entity.getNorthNortheast());
                        comCell6.setCellStyle(cellStyleCommon);
                        XSSFCell comCell7 = comRow.createCell(7);
                        comCell7.setCellValue(entity.getNortheastEast());
                        comCell7.setCellStyle(cellStyleCommon);
                        XSSFCell comCell8 = comRow.createCell(8);
                        comCell8.setCellValue(entity.getEastEastsouth());
                        comCell8.setCellStyle(cellStyleCommon);
                        XSSFCell comCell9 = comRow.createCell(9);
                        comCell9.setCellValue(entity.getEastsouthSouth());
                        comCell9.setCellStyle(cellStyleCommon);
                        XSSFCell comCell10 = comRow.createCell(10);
                        comCell10.setCellValue(entity.getSouthSouthwest());
                        comCell10.setCellStyle(cellStyleCommon);
                        XSSFCell comCell11 = comRow.createCell(11);
                        comCell11.setCellValue(entity.getSouthwestWest());
                        comCell11.setCellStyle(cellStyleCommon);
                        XSSFCell comCell12 = comRow.createCell(12);
                        comCell12.setCellValue(entity.getWestWestnorth());
                        comCell12.setCellStyle(cellStyleCommon);
                        XSSFCell comCell13 = comRow.createCell(13);
                        comCell13.setCellValue(entity.getWestnorthNorth());
                        comCell13.setCellStyle(cellStyleCommon);
                        i++;
                    }
                }else{
                    XSSFRow comRow = sheet.createRow(i+1);
                    XSSFCell comCell0 = comRow.createCell(0);
                    comCell0.setCellValue(mapDept.get(entity.getDeptcode()));
                    comCell0.setCellStyle(cellStyleCommon);
                    XSSFCell comCell1 = comRow.createCell(1);
                    comCell1.setCellValue(entity.getSbbh());
                    comCell1.setCellStyle(cellStyleCommon);
                    XSSFCell comCell2 = comRow.createCell(2);
                    comCell2.setCellValue(mapSbxh.get("sbmc-"+entity.getSbbh()));
                    comCell2.setCellStyle(cellStyleCommon);
                    XSSFCell comCell3 = comRow.createCell(3);
                    comCell3.setCellValue(entity.getRq());
                    comCell3.setCellStyle(cellStyleCommon);
                    XSSFCell comCell4 = comRow.createCell(4);
                    comCell4.setCellValue(entity.getFz());
                    comCell4.setCellStyle(cellStyleCommon);
                    XSSFCell comCell5 = comRow.createCell(5);
                    comCell5.setCellValue(entity.getTs());
                    comCell5.setCellStyle(cellStyleCommon);
                    XSSFCell comCell6 = comRow.createCell(6);
                    comCell6.setCellValue(entity.getNorthNortheast());
                    comCell6.setCellStyle(cellStyleCommon);
                    XSSFCell comCell7 = comRow.createCell(7);
                    comCell7.setCellValue(entity.getNortheastEast());
                    comCell7.setCellStyle(cellStyleCommon);
                    XSSFCell comCell8 = comRow.createCell(8);
                    comCell8.setCellValue(entity.getEastEastsouth());
                    comCell8.setCellStyle(cellStyleCommon);
                    XSSFCell comCell9 = comRow.createCell(9);
                    comCell9.setCellValue(entity.getEastsouthSouth());
                    comCell9.setCellStyle(cellStyleCommon);
                    XSSFCell comCell10 = comRow.createCell(10);
                    comCell10.setCellValue(entity.getSouthSouthwest());
                    comCell10.setCellStyle(cellStyleCommon);
                    XSSFCell comCell11 = comRow.createCell(11);
                    comCell11.setCellValue(entity.getSouthwestWest());
                    comCell11.setCellStyle(cellStyleCommon);
                    XSSFCell comCell12 = comRow.createCell(12);
                    comCell12.setCellValue(entity.getWestWestnorth());
                    comCell12.setCellStyle(cellStyleCommon);
                    XSSFCell comCell13 = comRow.createCell(13);
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
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //设置字体大小,加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "分析视频事件(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("分析视频事件");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("监测点","设备sn","开始时间","结束时间","核查情况");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            for(int i=0;i<videoEventList.size();i++){
                VideoEventExport videoEventExport = videoEventList.get(i);
                XSSFRow comRow = sheet.createRow(i+1);
                XSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(mapSbxh.get("sbmc-"+videoEventExport.getSbbh()));
                comCell0.setCellStyle(cellStyleCommon);
                XSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(videoEventExport.getSbbh());
                comCell1.setCellStyle(cellStyleCommon);
                XSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(videoEventExport.getKssj());
                comCell2.setCellStyle(cellStyleCommon);
                XSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(videoEventExport.getJssj());
                comCell3.setCellStyle(cellStyleCommon);
                XSSFCell comCell4 = comRow.createCell(4);
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
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //设置字体大小,加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "实时分析视频事件(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("实时分析视频事件");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("监测点","设备sn","开始时间","结束时间","核查情况");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            for(int i=0;i<videoEventList.size();i++){
                VideoEvent videoEvent = videoEventList.get(i);
                XSSFRow comRow = sheet.createRow(i+1);
                XSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(mapSbxh.get("sbmc-"+videoEvent.getSbbh()));
                comCell0.setCellStyle(cellStyleCommon);
                XSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(videoEvent.getSbbh());
                comCell1.setCellStyle(cellStyleCommon);
                XSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(videoEvent.getKssj());
                comCell2.setCellStyle(cellStyleCommon);
                XSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(videoEvent.getJssj());
                comCell3.setCellStyle(cellStyleCommon);
                XSSFCell comCell4 = comRow.createCell(4);
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
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //设置字体大小,加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "出现次数(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("出现次数");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("监测点","设备名称","设备sn","出现时间","出现次数");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapDept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            for(int i=0;i<appearNumbersList.size();i++){
                AppearNumbers appearNumbers = appearNumbersList.get(i);
                XSSFRow comRow = sheet.createRow(i+1);
                XSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(mapDept.get(appearNumbers.getDeptcode()));
                comCell0.setCellStyle(cellStyleCommon);
                XSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(mapSbxh.get("sbmc-"+appearNumbers.getSbbh()));
                comCell1.setCellStyle(cellStyleCommon);
                XSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(appearNumbers.getSbbh());
                comCell2.setCellStyle(cellStyleCommon);
                XSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(appearNumbers.getFz());
                comCell3.setCellStyle(cellStyleCommon);
                XSSFCell comCell4 = comRow.createCell(4);
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
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "出现事件(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("出现事件");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("监测点","设备名称","设备sn","出现时间","出现次数","事件次数");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapDept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            for(int i=0;i<equipmentFileAlarmEventList.size();i++){
                EquipmentFileAlarmEvent alarmEvent = equipmentFileAlarmEventList.get(i);
                XSSFRow comRow = sheet.createRow(i+1);
                XSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(mapDept.get(alarmEvent.getDeptcode()));
                comCell0.setCellStyle(cellStyleCommon);
                XSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(mapSbxh.get("sbmc-"+alarmEvent.getSbbh()));
                comCell1.setCellStyle(cellStyleCommon);
                XSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(alarmEvent.getSbbh());
                comCell2.setCellStyle(cellStyleCommon);
                XSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(alarmEvent.getEventTime());
                comCell3.setCellStyle(cellStyleCommon);
                XSSFCell comCell4 = comRow.createCell(4);
                comCell4.setCellValue(alarmEvent.getAlarmNum());
                comCell4.setCellStyle(cellStyleCommon);
                XSSFCell comCell5 = comRow.createCell(5);
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
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "实时水下环境指针预警数据(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("预警数据");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("设备名称","设备编号","预警数值","预警时间");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            int i=0;
            for(PointerSecond pointerSecond : lists){
                if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                    if(sbbhs.contains(pointerSecond.getSm())){
                        XSSFRow comRow = sheet.createRow(i+1);
                        XSSFCell comCell0 = comRow.createCell(0);
                        comCell0.setCellValue(mapSbxh.get("sbmc-"+pointerSecond.getSm()));
                        comCell0.setCellStyle(cellStyleCommon);
                        XSSFCell comCell1 = comRow.createCell(1);
                        comCell1.setCellValue(pointerSecond.getSm());
                        comCell1.setCellStyle(cellStyleCommon);
                        XSSFCell comCell2 = comRow.createCell(2);
                        comCell2.setCellValue(pointerSecond.getDecibelValue());
                        comCell2.setCellStyle(cellStyleCommon);
                        XSSFCell comCell3 = comRow.createCell(3);
                        comCell3.setCellValue(DateUtil.getFormatDate(pointerSecond.getCjsj(),"yyyy-MM-dd HH:mm:ss"));
                        comCell3.setCellStyle(cellStyleCommon);
                        i++;
                    }
                }else{
                    XSSFRow comRow = sheet.createRow(i+1);
                    XSSFCell comCell0 = comRow.createCell(0);
                    comCell0.setCellValue(mapSbxh.get("sbmc-"+pointerSecond.getSm()));
                    comCell0.setCellStyle(cellStyleCommon);
                    XSSFCell comCell1 = comRow.createCell(1);
                    comCell1.setCellValue(pointerSecond.getSm());
                    comCell1.setCellStyle(cellStyleCommon);
                    XSSFCell comCell2 = comRow.createCell(2);
                    comCell2.setCellValue(pointerSecond.getDecibelValue());
                    comCell2.setCellStyle(cellStyleCommon);
                    XSSFCell comCell3 = comRow.createCell(3);
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
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "当日水下环境指针预警数据(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("预警数据");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("设备名称","设备编号","预警数值","预警时间");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            int i=0;
            for(PointerDay pointerDay : lists){
                if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                    if(sbbhs.contains(pointerDay.getSm())){
                        XSSFRow comRow = sheet.createRow(i+1);
                        XSSFCell comCell0 = comRow.createCell(0);
                        comCell0.setCellValue(mapSbxh.get("sbmc-"+pointerDay.getSm()));
                        comCell0.setCellStyle(cellStyleCommon);
                        XSSFCell comCell1 = comRow.createCell(1);
                        comCell1.setCellValue(pointerDay.getSm());
                        comCell1.setCellStyle(cellStyleCommon);
                        XSSFCell comCell2 = comRow.createCell(2);
                        comCell2.setCellValue(pointerDay.getDecibelValue());
                        comCell2.setCellStyle(cellStyleCommon);
                        XSSFCell comCell3 = comRow.createCell(3);
                        comCell3.setCellValue(DateUtil.getFormatDate(pointerDay.getCjsj(),"yyyy-MM-dd HH:mm:ss"));
                        comCell3.setCellStyle(cellStyleCommon);
                        i++;
                    }
                }else{
                    XSSFRow comRow = sheet.createRow(i+1);
                    XSSFCell comCell0 = comRow.createCell(0);
                    comCell0.setCellValue(mapSbxh.get("sbmc-"+pointerDay.getSm()));
                    comCell0.setCellStyle(cellStyleCommon);
                    XSSFCell comCell1 = comRow.createCell(1);
                    comCell1.setCellValue(pointerDay.getSm());
                    comCell1.setCellStyle(cellStyleCommon);
                    XSSFCell comCell2 = comRow.createCell(2);
                    comCell2.setCellValue(pointerDay.getDecibelValue());
                    comCell2.setCellStyle(cellStyleCommon);
                    XSSFCell comCell3 = comRow.createCell(3);
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
            if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                sbbhs = waterProEquipService.findSbsnByXmbh(request.getParameter("xmbh"));
            }
            EquipmentFilePClusterExample example = new EquipmentFilePClusterExample();
            EquipmentFilePClusterExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSbbhEqualTo(request.getParameter("sbbh"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
            }
            example.setOrderByClause(" kssj desc ");
            List<EquipmentFilePCluster> pClusters = equipmentFilePClusterService.selectByExample(example);
            //导出
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "聚类统计(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("聚类统计");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            sheet.setColumnWidth(4,256*12+184);
            List<String> titleStrList = Arrays.asList("声检测点位","设备编号","开始时间","结束时间","预估头数");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            int i=0;
            for(EquipmentFilePCluster fileEvent : pClusters){
                if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                    if(sbbhs.contains(fileEvent.getSbbh())){
                        XSSFRow comRow = sheet.createRow(i+1);
                        XSSFCell comCell0 = comRow.createCell(0);
                        comCell0.setCellValue(mapSbxh.get("sbmc-"+fileEvent.getSbbh()));
                        comCell0.setCellStyle(cellStyleCommon);
                        XSSFCell comCell1 = comRow.createCell(1);
                        comCell1.setCellValue(fileEvent.getSbbh());
                        comCell1.setCellStyle(cellStyleCommon);
                        XSSFCell comCell2 = comRow.createCell(2);
                        comCell2.setCellValue(fileEvent.getKssj());
                        comCell2.setCellStyle(cellStyleCommon);
                        XSSFCell comCell3 = comRow.createCell(3);
                        comCell3.setCellValue(fileEvent.getJssj());
                        comCell3.setCellStyle(cellStyleCommon);
                        XSSFCell comCell4 = comRow.createCell(4);
                        comCell4.setCellValue(fileEvent.getTs());
                        comCell4.setCellStyle(cellStyleCommon);
                        i++;
                    }
                }else{
                    XSSFRow comRow = sheet.createRow(i+1);
                    XSSFCell comCell0 = comRow.createCell(0);
                    comCell0.setCellValue(mapSbxh.get("sbmc-"+fileEvent.getSbbh()));
                    comCell0.setCellStyle(cellStyleCommon);
                    XSSFCell comCell1 = comRow.createCell(1);
                    comCell1.setCellValue(fileEvent.getSbbh());
                    comCell1.setCellStyle(cellStyleCommon);
                    XSSFCell comCell2 = comRow.createCell(2);
                    comCell2.setCellValue(fileEvent.getKssj());
                    comCell2.setCellStyle(cellStyleCommon);
                    XSSFCell comCell3 = comRow.createCell(3);
                    comCell3.setCellValue(fileEvent.getJssj());
                    comCell3.setCellStyle(cellStyleCommon);
                    XSSFCell comCell4 = comRow.createCell(4);
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
            if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                sbbhs = waterProEquipService.findSbsnByXmbh(request.getParameter("xmbh"));
            }
            EquipmentFileTClusterExample example = new EquipmentFileTClusterExample();
            EquipmentFileTClusterExample.Criteria ca = example.createCriteria();
            if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
                ca.andSbbhEqualTo(request.getParameter("sbbh"));
            }
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
            }
            example.setOrderByClause(" kssj desc ");
            List<EquipmentFileTCluster> tClusters = equipmentFileTClusterService.selectByExample(example);
            //导出
            XSSFWorkbook workbook = new XSSFWorkbook();
            //设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            //设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            //cellStyleCommon.setFont(fontCommon);
            //设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);
            // 创建一个工作表
            String fileName = "聚类统计(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("聚类统计");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));
            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("声检测点位","设备编号","开始时间","结束时间","预估头数","坐标","经度","纬度","速度");
            for(int i=0;i<titleStrList.size();i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }
            Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
            int i=0;
            for(EquipmentFileTCluster tyEvent : tClusters){
                if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
                    if(sbbhs.contains(tyEvent.getSbbh())){
                        XSSFRow comRow = sheet.createRow(i+1);
                        XSSFCell comCell0 = comRow.createCell(0);
                        comCell0.setCellValue(mapSbxh.get("sbmc-"+tyEvent.getSbbh()));
                        comCell0.setCellStyle(cellStyleCommon);
                        XSSFCell comCell1 = comRow.createCell(1);
                        comCell1.setCellValue(tyEvent.getSbbh());
                        comCell1.setCellStyle(cellStyleCommon);
                        XSSFCell comCell2 = comRow.createCell(2);
                        comCell2.setCellValue(tyEvent.getKssj());
                        comCell2.setCellStyle(cellStyleCommon);
                        XSSFCell comCell3 = comRow.createCell(3);
                        comCell3.setCellValue(tyEvent.getJssj());
                        comCell3.setCellStyle(cellStyleCommon);
                        XSSFCell comCell4 = comRow.createCell(4);
                        comCell4.setCellValue(Integer.parseInt(tyEvent.getTs()));
                        comCell4.setCellStyle(cellStyleCommon);
                        comCell4.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        XSSFCell comCell5 = comRow.createCell(5);
                        comCell5.setCellValue(tyEvent.getSm2());
                        comCell5.setCellStyle(cellStyleCommon);
                        if(!StringUtils.isEmpty(tyEvent.getSm2())&&tyEvent.getSm2().split(",").length==2){
                            XSSFCell comCell6 = comRow.createCell(6);
                            comCell6.setCellValue(tyEvent.getSm2().split(",")[0]);
                            comCell6.setCellStyle(cellStyleCommon);
                            XSSFCell comCell7 = comRow.createCell(7);
                            comCell7.setCellValue(tyEvent.getSm2().split(",")[1]);
                            comCell7.setCellStyle(cellStyleCommon);
                        }
                        XSSFCell comCell8 = comRow.createCell(8);
                        comCell8.setCellValue(tyEvent.getSm6());
                        comCell8.setCellStyle(cellStyleCommon);
                        i++;
                    }
                }else{
                    XSSFRow comRow = sheet.createRow(i+1);
                    XSSFCell comCell0 = comRow.createCell(0);
                    comCell0.setCellValue(mapSbxh.get("sbmc-"+tyEvent.getSbbh()));
                    comCell0.setCellStyle(cellStyleCommon);
                    XSSFCell comCell1 = comRow.createCell(1);
                    comCell1.setCellValue(tyEvent.getSbbh());
                    comCell1.setCellStyle(cellStyleCommon);
                    XSSFCell comCell2 = comRow.createCell(2);
                    comCell2.setCellValue(tyEvent.getKssj());
                    comCell2.setCellStyle(cellStyleCommon);
                    XSSFCell comCell3 = comRow.createCell(3);
                    comCell3.setCellValue(tyEvent.getJssj());
                    comCell3.setCellStyle(cellStyleCommon);
                    XSSFCell comCell4 = comRow.createCell(4);
                    comCell4.setCellValue(Integer.parseInt(tyEvent.getTs()));
                    comCell4.setCellStyle(cellStyleCommon);
                    comCell4.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    XSSFCell comCell5 = comRow.createCell(5);
                    comCell5.setCellValue(tyEvent.getSm2());
                    comCell5.setCellStyle(cellStyleCommon);
                    if(!StringUtils.isEmpty(tyEvent.getSm2())&&tyEvent.getSm2().split(",").length==2){
                        XSSFCell comCell6 = comRow.createCell(6);
                        comCell6.setCellValue(tyEvent.getSm2().split(",")[0]);
                        comCell6.setCellStyle(cellStyleCommon);
                        XSSFCell comCell7 = comRow.createCell(7);
                        comCell7.setCellValue(tyEvent.getSm2().split(",")[1]);
                        comCell7.setCellStyle(cellStyleCommon);
                    }
                    XSSFCell comCell8 = comRow.createCell(8);
                    comCell8.setCellValue(tyEvent.getSm6());
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
        XSSFWorkbook workbook = new XSSFWorkbook();
        //设置字体大小
        XSSFFont fontCommon = workbook.createFont();
        fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        //设置公共单元格样式
        XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //cellStyleCommon.setFont(fontCommon);
        //设置字体加粗
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyleTitle.setFont(font);
        // 创建一个工作表
        String fileName = "设备运行日志(" + new Date().getTime() + ").xls";
        XSSFSheet sheet = workbook.createSheet("设备运行日志");
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(18);
        sheet.setDefaultRowHeight((short)(40*10));
        // 添加表头行
        XSSFRow titleRow = sheet.createRow(0);//第1行
        List<String> titleSbbhList = new ArrayList<>();
        for(int i=0;i<waterEquipmentList.size();i++){
            XSSFCell cell = titleRow.createCell(i+1);
            cell.setCellValue(waterEquipmentList.get(i).getSbmc());
            cell.setCellStyle(cellStyleTitle);
            titleSbbhList.add(waterEquipmentList.get(i).getSbsn());
        }
        int rowIndex = 1;
        for(String rq : resultMap.keySet()){
            XSSFRow rowComment = sheet.createRow(rowIndex);//第1行
            XSSFCell cell = rowComment.createCell(0);
            cell.setCellValue(rq);
            cell.setCellStyle(cellStyleCommon);
            Map<String,String> sbbhMap = resultMap.get(rq);
            int cellIndex = 1;
            for(String sbbh : titleSbbhList){
                XSSFCell cellComment = rowComment.createCell(cellIndex);
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
        XSSFWorkbook workbook = new XSSFWorkbook();
        //设置字体大小
        XSSFFont fontCommon = workbook.createFont();
        fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        //设置公共单元格样式
        XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //cellStyleCommon.setFont(fontCommon);
        //设置字体加粗
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyleTitle.setFont(font);
        // 创建一个工作表
        String fileName = "捕食统计(" + new Date().getTime() + ").xls";
        XSSFSheet sheet = workbook.createSheet("按捕食次数统计");
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(18);
        sheet.setDefaultRowHeight((short)(40*10));
        // 添加表头行
        XSSFRow titleRow = sheet.createRow(0);//第1行
        List<String> titleStrList = Arrays.asList("所属机构","检测点","设备sn","捕食日期","鲸豚出现次数","鲸豚捕食次数","鲸豚事件次数");
        for(int i=0;i<titleStrList.size();i++){
            XSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(titleStrList.get(i));
            cell.setCellStyle(cellStyleTitle);
        }
        Map<String,String> mapDept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
        Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
        for(int i=0;i<dataList.size();i++){
            PredationNum entity = dataList.get(i);
            XSSFRow comRow = sheet.createRow(i+1);
            XSSFCell comCell0 = comRow.createCell(0);
            comCell0.setCellValue(mapDept.get(entity.getDeptcode()));
            comCell0.setCellStyle(cellStyleCommon);
            XSSFCell comCell1 = comRow.createCell(1);
            comCell1.setCellValue(mapSbxh.get("sbmc-"+entity.getSbbh()));
            comCell1.setCellStyle(cellStyleCommon);
            XSSFCell comCell2 = comRow.createCell(2);
            comCell2.setCellValue(entity.getSbbh());
            comCell2.setCellStyle(cellStyleCommon);
            XSSFCell comCell3 = comRow.createCell(3);
            comCell3.setCellValue(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd"));
            comCell3.setCellStyle(cellStyleCommon);
            XSSFCell comCell4 = comRow.createCell(4);
            comCell4.setCellValue(entity.getAlarmNum());
            comCell4.setCellStyle(cellStyleCommon);
            XSSFCell comCell5 = comRow.createCell(5);
            comCell5.setCellValue(entity.getPredationNum());
            comCell5.setCellStyle(cellStyleCommon);
            XSSFCell comCell6 = comRow.createCell(6);
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
        XSSFWorkbook workbook = new XSSFWorkbook();
        //设置字体大小
        XSSFFont fontCommon = workbook.createFont();
        fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        //设置公共单元格样式
        XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //cellStyleCommon.setFont(fontCommon);
        //设置字体加粗
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyleTitle.setFont(font);
        // 创建一个工作表
        String fileName = "事件统计(" + new Date().getTime() + ").xls";
        XSSFSheet sheet = workbook.createSheet("按事件统计");
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(18);
        sheet.setDefaultRowHeight((short)(40*10));
        // 添加表头行
        XSSFRow titleRow = sheet.createRow(0);//第1行
        List<String> titleStrList = Arrays.asList("所属监测点","设备名称","设备sn","事件日期","事件次数","事件发生时间集合");
        for(int i=0;i<titleStrList.size();i++){
            XSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(titleStrList.get(i));
            cell.setCellStyle(cellStyleTitle);
        }
        Map<String,String> mapDept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
        Map<String,String> mapSbxh = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBBHSBMC);
        for(int i=0;i<dataList.size();i++){
            EquipmentFileAlarmEvent efae = new EquipmentFileAlarmEvent();
            PredationNum entity = dataList.get(i);
            XSSFRow comRow = sheet.createRow(i+1);
            XSSFCell comCell0 = comRow.createCell(0);
            comCell0.setCellValue(mapDept.get(entity.getDeptcode()));
            comCell0.setCellStyle(cellStyleCommon);
            efae.setDeptcode(entity.getDeptcode());
            XSSFCell comCell1 = comRow.createCell(1);
            comCell1.setCellValue(mapSbxh.get("sbmc-"+entity.getSbbh()));
            comCell1.setCellStyle(cellStyleCommon);
            efae.setSbbh(entity.getSbbh());
            XSSFCell comCell2 = comRow.createCell(2);
            comCell2.setCellValue(entity.getSbbh());
            comCell2.setCellStyle(cellStyleCommon);
            XSSFCell comCell3 = comRow.createCell(3);
            comCell3.setCellValue(entity.getCjsj());
            comCell3.setCellStyle(cellStyleCommon);
            efae.setBjsj(DateUtil.getFormatDate(entity.getCjsj(),"yyyy-MM-dd"));
            XSSFCell comCell4 = comRow.createCell(4);
            comCell4.setCellValue(entity.getSm1());
            comCell4.setCellStyle(cellStyleCommon);
            List<EquipmentFileAlarmEvent> detailAlarmTimes = equipmentFileAlarmEventService.detailByParam(efae);
            List<String> eventTimes=  detailAlarmTimes.stream().filter(Objects::nonNull).map(EquipmentFileAlarmEvent::getEventTime).collect(Collectors.toList());
            XSSFCell comCell5 = comRow.createCell(5);
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
        String type = request.getParameter("type");
        EquipmentFilePPicExample example = new EquipmentFilePPicExample();
        EquipmentFilePPicExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
            ca.andSbbhEqualTo(request.getParameter("sbbh"));
        }
        List<AlarmNumbersDto> lists = new ArrayList<>();
        Optional<Integer> op = null;
        if(type.equals("minute")){
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andFzGreaterThanOrEqualTo(request.getParameter("stime"));
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andFzLessThanOrEqualTo(request.getParameter("etime"));
            }
            lists = equipmentFilePPicService.statisticsAlarmNumsByMinute(example);
        }else if(type.equals("hour")){
            if(!StringUtils.isEmpty(request.getParameter("stime"))){
                ca.andXsGreaterThanOrEqualTo(request.getParameter("stime")+" 00");
            }
            if(!StringUtils.isEmpty(request.getParameter("etime"))){
                ca.andXsLessThanOrEqualTo(request.getParameter("etime")+" 23");
            }
            lists = equipmentFilePPicService.statisticsAlarmNumsByHour(example);
            op = lists.stream().filter(Objects::nonNull).map(AlarmNumbersDto::getAlarmNum).reduce(Integer::sum);
        }
        //导出
        XSSFWorkbook workbook = new XSSFWorkbook();
        //设置字体大小
        XSSFFont fontCommon = workbook.createFont();
        fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        //设置公共单元格样式
        XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //cellStyleCommon.setFont(fontCommon);
        //设置字体加粗
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
        XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyleTitle.setFont(font);
        // 创建一个工作表
        String fileName = "出现统计(" + new Date().getTime() + ").xls";
        XSSFSheet sheet = workbook.createSheet("出现时间");
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(18);
        sheet.setDefaultRowHeight((short)(40*10));
        // 添加表头行
        XSSFRow titileRow = sheet.createRow(0);//第1行
        XSSFCell headCell = titileRow.createCell(0);//第1行第1列
        headCell.setCellValue("出现时间");
        headCell.setCellStyle(cellStyleTitle);
        XSSFCell headCell1 = titileRow.createCell(1);//第1行第2列
        if(type.equals("minute")){
            headCell1.setCellValue("出现次数");
        }else if(type.equals("hour")){
            headCell1.setCellValue("占总侦测事件百分比");
        }
        headCell1.setCellStyle(cellStyleTitle);
        for(int i=0;i<lists.size();i++){
            AlarmNumbersDto entity = lists.get(i);
            if(type.equals("minute")){
                XSSFRow row = sheet.createRow(i+1);//第1行
                XSSFCell cell = row.createCell(0);//第1行第1列
                cell.setCellValue(entity.getFz());
                cell.setCellStyle(cellStyleCommon);
                XSSFCell cell1 = row.createCell(1);//第1行第2列
                cell1.setCellValue(entity.getAlarmNum());
                cell1.setCellStyle(cellStyleCommon);
            }else if(type.equals("hour")) {
                XSSFRow row = sheet.createRow(i+1);//第1行
                XSSFCell cell = row.createCell(0);//第1行第1列
                cell.setCellValue(entity.getXs());
                cell.setCellStyle(cellStyleCommon);
                XSSFCell cell1 = row.createCell(1);//第1行第2列
                if(op.get()!=null && op.get()!=0){
                    cell1.setCellValue(CalculateUtil.calculateResultOfPercent(entity.getAlarmNum(),op.get()));
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


}
