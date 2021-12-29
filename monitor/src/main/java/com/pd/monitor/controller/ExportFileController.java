package com.pd.monitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.config.CodeType;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.EquipmentFileAlarmEventDto;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.service.EquipmentFileAlarmEventService;
import com.pd.server.main.service.EquipmentFileService;
import com.pd.server.main.service.PredationNumService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.CopyUtil;
import com.pd.server.util.DateUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
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

    /**
     * 江豚出现按捕食统计导出
     */
    @GetMapping("/exportByPredationNum")
    public void exportByPredationNum(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String deptcode = request.getParameter("deptcode");
        List<String> list = getUpdeptcode(deptcode);//获取部门属性
        List<String> sbsns = new ArrayList<>();
        PredationNumExample example = new PredationNumExample();
        PredationNumExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(request.getParameter("stime"))){
            ca.andCjsjGreaterThanOrEqualTo(request.getParameter("stime"),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(request.getParameter("etime"))){
            ca.andCjsjLessThanOrEqualTo(request.getParameter("etime"),"%Y-%m-%d");
        }
        if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
            ca.andSbbhEqualTo(request.getParameter("sbbh"));
        }
        if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
            Map<String, List<String>> mapXmbhsbsn = (Map<String, List<String>>) redisTemplate.opsForValue().get(RedisCode.PROJECTSBSNS);
            ca.andSbbhIn( mapXmbhsbsn.get(request.getParameter("xmbh")));
        }
        example.setOrderByClause(" cjsj desc ");
        List<PredationNum> dataList = predationNumService.list(example);
        //导出
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置公共单元格样式
        HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 创建一个工作表
        String fileName = "江豚捕食统计(" + new Date().getTime() + ").xls";
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
            cell.setCellStyle(cellStyleCommon);
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
        String deptcode = request.getParameter("deptcode");
        List<String> list = getUpdeptcode(deptcode);//获取部门属性
        List<String> sbsns = new ArrayList<>();
        EquipmentFileAlarmEventDto pageDto = new EquipmentFileAlarmEventDto();
        if(!StringUtils.isEmpty(request.getParameter("stime"))){
            pageDto.setStime(request.getParameter("stime"));
        }
        if(!StringUtils.isEmpty(request.getParameter("etime"))){
            pageDto.setEtime(request.getParameter("etime"));
        }
        if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
            pageDto.setSbbh(request.getParameter("sbbh"));
        }
        if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
            Map<String, List<String>> mapXmbhsbsn = (Map<String, List<String>>) redisTemplate.opsForValue().get(RedisCode.PROJECTSBSNS);
            sbsns = mapXmbhsbsn.get(request.getParameter("xmbh"));
        }
        equipmentFileAlarmEventService.listStatistics(pageDto, list, sbsns);
        List<EquipmentFileAlarmEventDto> dataList = pageDto.getList();
        //导出
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置公共单元格样式
        HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 创建一个工作表
        String fileName = "江豚事件统计(" + new Date().getTime() + ").xls";
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
            cell.setCellStyle(cellStyleCommon);
        }
        Map<String,String> mapDept = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.DEPTCODENAME);
        WaterEquipmentExample waterEquipmentExample = new WaterEquipmentExample();
        WaterEquipmentExample.Criteria ca = waterEquipmentExample.createCriteria();
        ca.andSblbEqualTo("0001");
        List<WaterEquipment> waterEquipmentList = waterEquipmentService.list(waterEquipmentExample);
        Map<String, String> mapSbxh = waterEquipmentList.stream().collect(Collectors.toMap(p -> p.getSbsn(), p -> p.getSbmc()));
        for(int i=0;i<dataList.size();i++){
            EquipmentFileAlarmEventDto entity = dataList.get(i);
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
            comCell3.setCellValue(entity.getBjsj());
            comCell3.setCellStyle(cellStyleCommon);
            HSSFCell comCell4 = comRow.createCell(4);
            comCell4.setCellValue(entity.getCounts());
            comCell4.setCellStyle(cellStyleCommon);
            EquipmentFileAlarmEvent efae = CopyUtil.copy(entity,EquipmentFileAlarmEvent.class);
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
        //设置公共单元格样式
        HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 创建一个工作表
        String fileName = "江豚出现统计(" + new Date().getTime() + ").xls";
        HSSFSheet sheet = workbook.createSheet("出现时间");
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(18);
        sheet.setDefaultRowHeight((short)(40*10));
        // 添加表头行
        HSSFRow titileRow = sheet.createRow(0);//第1行
        HSSFCell headCell = titileRow.createCell(0);//第1行第1列
        headCell.setCellValue("出现时间");
        headCell.setCellStyle(cellStyleCommon);
        HSSFCell headCell1 = titileRow.createCell(1);//第1行第2列
        if(type.equals("minute")){
            headCell1.setCellValue("出现次数");
        }else if(type.equals("hour")){
            headCell1.setCellValue("占总侦测事件百分比");
        }
        headCell1.setCellStyle(cellStyleCommon);
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
