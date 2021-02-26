package com.pd.wxbase.wx.controller;

import com.pd.server.main.domain.Dept;
import com.pd.server.main.domain.WxYyExample;
import com.pd.server.main.domain.WxYyStatisticsExample;
import com.pd.server.main.dto.WxYyDto;
import com.pd.server.main.dto.WxYyStatisticsDto;
import com.pd.server.main.dto.basewx.my.WxyyYyslGuoupDto;
import com.pd.server.main.service.DeptService;
import com.pd.server.main.service.WxYyService;
import com.pd.server.main.service.WxYyStatisticsService;
import com.pd.server.util.DateUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;


@RestController
@RequestMapping("/export/wxYyStatistics")
public class WxYyStatisticsExportController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(WxYyStatisticsExportController.class);
    public static final String BUSINESS_NAME = "预约统计导出";

    @Resource
    private WxYyStatisticsService wxYyStatisticsService;
    @Resource
    private WxYyService wxYyService;
    @Resource
    private DeptService deptService;
    @Resource
    private WxYyStatisticsController wxYyStatisticsController;

    /**
     * 业务预约数量统计导出
     */
    @GetMapping("/exportWxYwYyStatistics")
    public void exportWxYwYyStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String deptcode = request.getParameter("deptcode");
        String nf = request.getParameter("nf");
        String yf = request.getParameter("yf");
        String chooseType = request.getParameter("chooseType");
        List<String> list = getUpdeptcode(deptcode);//获取部门属性
        WxYyStatisticsDto wxYyStatisticsDto = new WxYyStatisticsDto();
        if(!StringUtils.isEmpty(nf)){
            wxYyStatisticsDto.setNf(nf);
        }
        if(!StringUtils.isEmpty(yf)){
            wxYyStatisticsDto.setYf(yf);
        }
        Map<String, Object> resultMap = new HashMap<>();
        if(!StringUtils.isEmpty(chooseType)&&"1".equals(chooseType)){
            resultMap = wxYyStatisticsController.getStatisticsByNf(list,wxYyStatisticsDto);
        }
        if(!StringUtils.isEmpty(chooseType)&&"2".equals(chooseType)){
            resultMap = wxYyStatisticsController.getStatisticsByYf(list,wxYyStatisticsDto);
        }
        //导出
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置标题的样式
        HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//标题居中左右居中
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//标题上下居中
        //设置标题字体样式
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 20);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        //标题边框
        cellStyleTitle.setFont(font);
        //设置公共单元格样式
        HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 创建一个工作表
        String fileName = "业务预约数量查询统计(" + new Date().getTime() + ").xls";
        HSSFSheet sheet = workbook.createSheet("业务预约");
        if(!StringUtils.isEmpty(chooseType)&&"1".equals(chooseType)){
            createSheetByYwYyAndNf(sheet,cellStyleTitle,cellStyleCommon,resultMap);
        }
        if(!StringUtils.isEmpty(chooseType)&&"2".equals(chooseType)){
            createSheetByYwYyAndYf(sheet,cellStyleTitle,cellStyleCommon,resultMap);
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

    public void createSheetByYwYyAndNf(HSSFSheet sheet, HSSFCellStyle cellStyleTitle, HSSFCellStyle cellStyleCommon, Map<String, Object> map){
        Map<String, String> deptMap = new LinkedHashMap<String, String>();
        List<Dept> deptlist = deptService.list(null);
        for (int i = 0; i < deptlist.size(); i++) {
            Dept dept = deptlist.get(i);
            deptMap.put(dept.getDeptcode(), dept.getDeptname());
        }
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(8);
        // 添加表头行
        HSSFRow titileRow = sheet.createRow(0);//第1行
        titileRow.setHeight((short)(40*20));
        HSSFCell headCell = titileRow.createCell(0);//第1行第1列
        headCell.setCellValue("业务预约数量查询统计");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
        headCell.setCellStyle(cellStyleTitle);
        HSSFRow secondRow = sheet.createRow(1);//第2行
        HSSFCell cell21 = secondRow.createCell(0);//第2行第1列
        cell21.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
        cell21.setCellStyle(cellStyleCommon);
        HSSFCell cell22 = secondRow.createCell(1);//第2行第2列
        cell22.setCellValue("预约数");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 3));
        cell22.setCellStyle(cellStyleCommon);
        HSSFCell cell25 = secondRow.createCell(4);//第2行第5列
        cell25.setCellValue("到场数");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 6));
        cell25.setCellStyle(cellStyleCommon);
        HSSFRow thirdRow = sheet.createRow(2);//第3行
        HSSFCell cell32 = thirdRow.createCell(1);//第3行第2列
        cell32.setCellValue("今年预约数");
        cell32.setCellStyle(cellStyleCommon);
        HSSFCell cell33 = thirdRow.createCell(2);//第3行第3列
        cell33.setCellValue("去年同期");
        cell33.setCellStyle(cellStyleCommon);
        HSSFCell cell34 = thirdRow.createCell(3);//第3行第4列
        cell34.setCellValue("同比增长");
        cell34.setCellStyle(cellStyleCommon);
        HSSFCell cell35 = thirdRow.createCell(4);//第3行第5列
        cell35.setCellValue("今年到场数");
        cell35.setCellStyle(cellStyleCommon);
        HSSFCell cell36 = thirdRow.createCell(5);//第3行第6列
        cell36.setCellValue("去年同期");
        cell36.setCellStyle(cellStyleCommon);
        HSSFCell cell37 = thirdRow.createCell(6);//第3行第7列
        cell37.setCellValue("同比增长");
        cell37.setCellStyle(cellStyleCommon);
        List<WxyyYyslGuoupDto> list = (List<WxyyYyslGuoupDto>) map.get("mx");
        WxyyYyslGuoupDto allvo = (WxyyYyslGuoupDto) map.get("zj");
        createContentByYwYyAndNf(sheet,list,allvo,cellStyleCommon,deptMap);
    }

    public void createContentByYwYyAndNf(HSSFSheet sheet, List<WxyyYyslGuoupDto> list, WxyyYyslGuoupDto allvo, HSSFCellStyle cellStyleCommon, Map<String, String> deptMap){
        for(int i=0; i<list.size() ;i++) {
            WxyyYyslGuoupDto entity = list.get(i);
            HSSFRow commonRow = sheet.createRow(i + 3);//第4行
            HSSFCell cell1 = commonRow.createCell(0);//第4行第1列
            cell1.setCellValue(deptMap.get(entity.getDeptcode()));
            cell1.setCellStyle(cellStyleCommon);
            HSSFCell cell2 = commonRow.createCell(1);//第4行第2列
            cell2.setCellValue(entity.getCurNfYysl());
            cell2.setCellStyle(cellStyleCommon);
            HSSFCell cell3 = commonRow.createCell(2);//第4行第3列
            cell3.setCellValue(entity.getBeforeNfYysl());
            cell3.setCellStyle(cellStyleCommon);
            HSSFCell cell4 = commonRow.createCell(3);//第4行第4列
            cell4.setCellValue(entity.getTbzzYysl());
            cell4.setCellStyle(cellStyleCommon);
            HSSFCell cell5 = commonRow.createCell(4);//第4行第5列
            cell5.setCellValue(entity.getCurNfDcsl());
            cell5.setCellStyle(cellStyleCommon);
            HSSFCell cell6 = commonRow.createCell(5);//第4行第6列
            cell6.setCellValue(entity.getBeforeNfDcsl());
            cell6.setCellStyle(cellStyleCommon);
            HSSFCell cell7 = commonRow.createCell(6);//第4行第7列
            cell7.setCellValue(entity.getTbzzDcsl());
            cell7.setCellStyle(cellStyleCommon);
        }
        HSSFRow laseRow = sheet.createRow(list.size()+3);//最后一行
        HSSFCell cell1 = laseRow.createCell(0);
        cell1.setCellValue("合计");
        cell1.setCellStyle(cellStyleCommon);
        HSSFCell cell2 = laseRow.createCell(1);
        cell2.setCellValue(allvo.getCurNfYysl());
        cell2.setCellStyle(cellStyleCommon);
        HSSFCell cell3 = laseRow.createCell(2);
        cell3.setCellValue(allvo.getBeforeNfYysl());
        cell3.setCellStyle(cellStyleCommon);
        HSSFCell cell4 = laseRow.createCell(3);
        cell4.setCellValue(allvo.getTbzzYysl());
        cell4.setCellStyle(cellStyleCommon);
        HSSFCell cell5 = laseRow.createCell(4);
        cell5.setCellValue(allvo.getCurNfDcsl());
        cell5.setCellStyle(cellStyleCommon);
        HSSFCell cell6 = laseRow.createCell(5);
        cell6.setCellValue(allvo.getBeforeNfDcsl());
        cell6.setCellStyle(cellStyleCommon);
        HSSFCell cell7 = laseRow.createCell(6);
        cell7.setCellValue(allvo.getTbzzDcsl());
        cell7.setCellStyle(cellStyleCommon);
    }

    public void createSheetByYwYyAndYf(HSSFSheet sheet, HSSFCellStyle cellStyleTitle, HSSFCellStyle cellStyleCommon, Map<String, Object> map){
        Map<String, String> deptMap = new LinkedHashMap<String, String>();
        List<Dept> deptlist = deptService.list(null);
        for (int i = 0; i < deptlist.size(); i++) {
            Dept dept = deptlist.get(i);
            deptMap.put(dept.getDeptcode(), dept.getDeptname());
        }
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(8);
        // 添加表头行
        HSSFRow titileRow = sheet.createRow(0);//第1行
        titileRow.setHeight((short)(40*20));
        HSSFCell headCell = titileRow.createCell(0);//第1行第1列
        headCell.setCellValue("业务预约数量查询统计");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
        headCell.setCellStyle(cellStyleTitle);
        HSSFRow secondRow = sheet.createRow(1);//第2行
        HSSFCell cell21 = secondRow.createCell(0);//第2行第1列
        cell21.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
        cell21.setCellStyle(cellStyleCommon);
        HSSFCell cell22 = secondRow.createCell(1);//第2行第2列
        cell22.setCellValue("预约数");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 3));
        cell22.setCellStyle(cellStyleCommon);
        HSSFCell cell25 = secondRow.createCell(4);//第2行第5列
        cell25.setCellValue("到场数");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 6));
        cell25.setCellStyle(cellStyleCommon);
        HSSFRow thirdRow = sheet.createRow(2);//第3行
        HSSFCell cell32 = thirdRow.createCell(1);//第3行第2列
        cell32.setCellValue("本月预约数");
        cell32.setCellStyle(cellStyleCommon);
        HSSFCell cell33 = thirdRow.createCell(2);//第3行第3列
        cell33.setCellValue("上月同期");
        cell33.setCellStyle(cellStyleCommon);
        HSSFCell cell34 = thirdRow.createCell(3);//第3行第4列
        cell34.setCellValue("环比增长");
        cell34.setCellStyle(cellStyleCommon);
        HSSFCell cell35 = thirdRow.createCell(4);//第3行第5列
        cell35.setCellValue("本月到场数");
        cell35.setCellStyle(cellStyleCommon);
        HSSFCell cell36 = thirdRow.createCell(5);//第3行第6列
        cell36.setCellValue("上月同期");
        cell36.setCellStyle(cellStyleCommon);
        HSSFCell cell37 = thirdRow.createCell(6);//第3行第7列
        cell37.setCellValue("环比增长");
        cell37.setCellStyle(cellStyleCommon);
        List<WxyyYyslGuoupDto> list = (List<WxyyYyslGuoupDto>) map.get("mx");
        WxyyYyslGuoupDto allvo = (WxyyYyslGuoupDto) map.get("zj");
        createContentByYwYyAndYf(sheet,list,allvo,cellStyleCommon,deptMap);
    }

    public void createContentByYwYyAndYf(HSSFSheet sheet, List<WxyyYyslGuoupDto> list, WxyyYyslGuoupDto allvo, HSSFCellStyle cellStyleCommon, Map<String, String> deptMap){
        for(int i=0; i<list.size() ;i++) {
            WxyyYyslGuoupDto entity = list.get(i);
            HSSFRow commonRow = sheet.createRow(i + 3);//第4行
            HSSFCell cell1 = commonRow.createCell(0);//第4行第1列
            cell1.setCellValue(deptMap.get(entity.getDeptcode()));
            cell1.setCellStyle(cellStyleCommon);
            HSSFCell cell2 = commonRow.createCell(1);//第4行第2列
            cell2.setCellValue(entity.getCurYfYysl());
            cell2.setCellStyle(cellStyleCommon);
            HSSFCell cell3 = commonRow.createCell(2);//第4行第3列
            cell3.setCellValue(entity.getBeforeYfYysl());
            cell3.setCellStyle(cellStyleCommon);
            HSSFCell cell4 = commonRow.createCell(3);//第4行第4列
            cell4.setCellValue(entity.getHbzzYysl());
            cell4.setCellStyle(cellStyleCommon);
            HSSFCell cell5 = commonRow.createCell(4);//第4行第5列
            cell5.setCellValue(entity.getCurYfDcsl());
            cell5.setCellStyle(cellStyleCommon);
            HSSFCell cell6 = commonRow.createCell(5);//第4行第6列
            cell6.setCellValue(entity.getBeforeYfDcsl());
            cell6.setCellStyle(cellStyleCommon);
            HSSFCell cell7 = commonRow.createCell(6);//第4行第7列
            cell7.setCellValue(entity.getHbzzDcsl());
            cell7.setCellStyle(cellStyleCommon);
        }
        HSSFRow laseRow = sheet.createRow(list.size()+3);//最后一行
        HSSFCell cell1 = laseRow.createCell(0);
        cell1.setCellValue("合计");
        cell1.setCellStyle(cellStyleCommon);
        HSSFCell cell2 = laseRow.createCell(1);
        cell2.setCellValue(allvo.getCurYfYysl());
        cell2.setCellStyle(cellStyleCommon);
        HSSFCell cell3 = laseRow.createCell(2);
        cell3.setCellValue(allvo.getBeforeYfYysl());
        cell3.setCellStyle(cellStyleCommon);
        HSSFCell cell4 = laseRow.createCell(3);
        cell4.setCellValue(allvo.getHbzzYysl());
        cell4.setCellStyle(cellStyleCommon);
        HSSFCell cell5 = laseRow.createCell(4);
        cell5.setCellValue(allvo.getCurYfDcsl());
        cell5.setCellStyle(cellStyleCommon);
        HSSFCell cell6 = laseRow.createCell(5);
        cell6.setCellValue(allvo.getBeforeYfDcsl());
        cell6.setCellStyle(cellStyleCommon);
        HSSFCell cell7 = laseRow.createCell(6);
        cell7.setCellValue(allvo.getHbzzDcsl());
        cell7.setCellStyle(cellStyleCommon);
    }

    /**
     * 违法处理导出
     */
    @GetMapping("/exportWxYyStatisticsByWfcl")
    public void exportWxYyStatisticsByWfcl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nf = request.getParameter("nf");
        String yf = request.getParameter("yf");
        String rq = request.getParameter("rq");
        String title = request.getParameter("title");
        String deptcode = request.getParameter("deptcode");
        String ywflListStr = request.getParameter("ywflListStr");
        List<String> ywflList = Arrays.asList(ywflListStr.split(","));
        String zt = request.getParameter("zt");
        List<String> list = getUpdeptcode(deptcode);//获取部门属性
        //获得今天的日期
        String dateStr = DateUtil.getFormatDate(new Date(), "yyyy-MM-dd");
        Map<String, Object> map = new HashMap<String, Object>();
        if (dateStr.equals(rq)) {//统计今天
            WxYyExample example = new WxYyExample();
            WxYyExample.Criteria ca = example.createCriteria();
            if (!StringUtils.isEmpty(list)) {
                ca.andDeptcodeIn(list);
            }
            if (!StringUtils.isEmpty(zt)) {
                ca.andZtEqualTo(zt);
            }
            if (!StringUtils.isEmpty(rq)) {
                ca.andYysjEqualTo(rq);
            }
            if (!StringUtils.isEmpty(ywflList)) {
                ca.andYwflIn(ywflList);
            }
            map = wxYyService.getStatisticsByToday(example);
            map.put("chooseType","today");
        } else {
            WxYyStatisticsExample example = new WxYyStatisticsExample();
            WxYyStatisticsExample.Criteria ca = example.createCriteria();
            if (!StringUtils.isEmpty(list)) {
                ca.andDeptcodeIn(list);
            }
            if (!StringUtils.isEmpty(nf)) {
                ca.andNfEqualTo(nf);
            }
            if (!StringUtils.isEmpty(yf)) {
                ca.andYfEqualTo(yf);
            }
            if (!StringUtils.isEmpty(rq)) {
                ca.andRqEqualTo(rq);
            }
            if (!StringUtils.isEmpty(zt)) {
                ca.andZtEqualTo(zt);
            }
            if (!StringUtils.isEmpty(ywflList)) {
                ca.andYwflIn(ywflList);
            }
            map = wxYyStatisticsService.getStatistics(example);
            map.put("chooseType","noToday");
        }
        map.put("title", title);
        //导出
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置标题的样式
        HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//标题居中左右居中
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//标题上下居中
        //设置标题字体样式
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 20);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        //标题边框
        cellStyleTitle.setFont(font);
        //设置公共单元格样式
        HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 创建一个工作表
        String fileName = "车架管业务预约查询统计(" + new Date().getTime() + ").xls";
        HSSFSheet sheet = workbook.createSheet("车架管业务");
        createSheetByWfcl(sheet,cellStyleTitle,cellStyleCommon,map);
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

    public void createSheetByWfcl(HSSFSheet sheet, HSSFCellStyle cellStyleTitle, HSSFCellStyle cellStyleCommon, Map<String, Object> map) {
        Map<String, String> deptMap = new LinkedHashMap<String, String>();
        List<Dept> deptlist = deptService.list(null);
        for (int i = 0; i < deptlist.size(); i++) {
            Dept dept = deptlist.get(i);
            deptMap.put(dept.getDeptcode(), dept.getDeptname());
        }
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(8);
        // 添加表头行
        HSSFRow titileRow = sheet.createRow(0);//第1行
        titileRow.setHeight((short)(40*20));
        HSSFCell headCell = titileRow.createCell(0);//第1行第1列
        headCell.setCellValue(map.get("title").toString());
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
        headCell.setCellStyle(cellStyleTitle);
        HSSFRow secondRow = sheet.createRow(1);//第2行
        HSSFCell cell21 = secondRow.createCell(1);//第2行第2列
        cell21.setCellValue("预约总数合计");
        cell21.setCellStyle(cellStyleCommon);
        HSSFCell cell23 = secondRow.createCell(2);//第2行第3列
        cell23.setCellValue("机动车违法处理");
        cell23.setCellStyle(cellStyleCommon);
        HSSFCell cell24 = secondRow.createCell(3);//第2行第4列
        cell24.setCellValue("电动车违法处理");
        cell24.setCellStyle(cellStyleCommon);
        HSSFCell cell25 = secondRow.createCell(4);//第2行第5列
        cell25.setCellValue("预约到场数");
        cell25.setCellStyle(cellStyleCommon);
        if("today".equals(map.get("chooseType"))){
            List<WxYyDto> wxYyDtoList = (List<WxYyDto>) map.get("mx");
            WxYyDto allvo = (WxYyDto) map.get("zj");
            createContentByTodayAndWfcl(sheet,wxYyDtoList,allvo,cellStyleCommon,deptMap);
        }else{
            List<WxYyStatisticsDto> wxYyStatisticsDtoList = (List<WxYyStatisticsDto>) map.get("mx");
            WxYyStatisticsDto allvo = (WxYyStatisticsDto) map.get("zj");
            createContentByWfcl(sheet,wxYyStatisticsDtoList,allvo,cellStyleCommon,deptMap);
        }
    }

    public void createContentByTodayAndWfcl(HSSFSheet sheet,List<WxYyDto> wxYyDtoList,WxYyDto allvo, HSSFCellStyle cellStyleCommon,Map<String, String> deptMap){
        for(int i=0; i<wxYyDtoList.size() ;i++) {
            WxYyDto entity = wxYyDtoList.get(i);
            HSSFRow commonRow = sheet.createRow(i + 2);//第3行
            HSSFCell cell1 = commonRow.createCell(0);//第3行第1列
            cell1.setCellValue(deptMap.get(entity.getDeptcode()));
            cell1.setCellStyle(cellStyleCommon);
            HSSFCell cell2 = commonRow.createCell(1);//第3行第2列
            cell2.setCellValue(entity.getSumwfjdc()+entity.getSumwfddc());
            cell2.setCellStyle(cellStyleCommon);
            HSSFCell cell3 = commonRow.createCell(2);//第3行第3列
            cell3.setCellValue(entity.getSumwfjdc());
            cell3.setCellStyle(cellStyleCommon);
            HSSFCell cell4 = commonRow.createCell(3);//第3行第4列
            cell4.setCellValue(entity.getSumwfddc());
            cell4.setCellStyle(cellStyleCommon);
            HSSFCell cell5 = commonRow.createCell(4);//第3行第5列
            cell5.setCellValue(entity.getSumwfdcs());
            cell5.setCellStyle(cellStyleCommon);
        }
        HSSFRow laseRow = sheet.createRow(wxYyDtoList.size()+2);//最后一行
        HSSFCell cell1 = laseRow.createCell(0);
        cell1.setCellValue("合计");
        cell1.setCellStyle(cellStyleCommon);
        HSSFCell cell2 = laseRow.createCell(1);
        cell2.setCellValue(allvo.getSumwfjdc()+allvo.getSumwfddc());
        cell2.setCellStyle(cellStyleCommon);
        HSSFCell cell3 = laseRow.createCell(2);
        cell3.setCellValue(allvo.getSumwfjdc());
        cell3.setCellStyle(cellStyleCommon);
        HSSFCell cell4 = laseRow.createCell(3);
        cell4.setCellValue(allvo.getSumwfddc());
        cell4.setCellStyle(cellStyleCommon);
        HSSFCell cell5 = laseRow.createCell(4);
        cell5.setCellValue(allvo.getSumwfdcs());
        cell5.setCellStyle(cellStyleCommon);
    }

    public void createContentByWfcl(HSSFSheet sheet,List<WxYyStatisticsDto> wxYyStatisticsDtoList,WxYyStatisticsDto allvo, HSSFCellStyle cellStyleCommon,Map<String, String> deptMap){
        for(int i=0; i<wxYyStatisticsDtoList.size() ;i++) {
            WxYyStatisticsDto entity = wxYyStatisticsDtoList.get(i);
            HSSFRow commonRow = sheet.createRow(i + 2);//第3行
            HSSFCell cell1 = commonRow.createCell(0);//第3行第1列
            cell1.setCellValue(deptMap.get(entity.getDeptcode()));
            cell1.setCellStyle(cellStyleCommon);
            HSSFCell cell2 = commonRow.createCell(1);//第3行第2列
            cell2.setCellValue(entity.getSumwfjdc()+entity.getSumwfddc());
            cell2.setCellStyle(cellStyleCommon);
            HSSFCell cell3 = commonRow.createCell(2);//第3行第3列
            cell3.setCellValue(entity.getSumwfjdc());
            cell3.setCellStyle(cellStyleCommon);
            HSSFCell cell4 = commonRow.createCell(3);//第3行第4列
            cell4.setCellValue(entity.getSumwfddc());
            cell4.setCellStyle(cellStyleCommon);
            HSSFCell cell5 = commonRow.createCell(4);//第3行第5列
            cell5.setCellValue(entity.getSumwfdcs());
            cell5.setCellStyle(cellStyleCommon);
        }
        HSSFRow laseRow = sheet.createRow(wxYyStatisticsDtoList.size()+2);//最后一行
        HSSFCell cell1 = laseRow.createCell(0);
        cell1.setCellValue("合计");
        cell1.setCellStyle(cellStyleCommon);
        HSSFCell cell2 = laseRow.createCell(1);
        cell2.setCellValue(allvo.getSumwfjdc()+allvo.getSumwfddc());
        cell2.setCellStyle(cellStyleCommon);
        HSSFCell cell3 = laseRow.createCell(2);
        cell3.setCellValue(allvo.getSumwfjdc());
        cell3.setCellStyle(cellStyleCommon);
        HSSFCell cell4 = laseRow.createCell(3);
        cell4.setCellValue(allvo.getSumwfddc());
        cell4.setCellStyle(cellStyleCommon);
        HSSFCell cell5 = laseRow.createCell(4);
        cell5.setCellValue(allvo.getSumwfdcs());
        cell5.setCellStyle(cellStyleCommon);
    }

    /**
     * 车架管导出
     */
    @GetMapping("/exportWxYyStatisticsByCjg")
    public void exportWxYyStatisticsByCjg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nf = request.getParameter("nf");
        String yf = request.getParameter("yf");
        String rq = request.getParameter("rq");
        String title = request.getParameter("title");
        String deptcode = request.getParameter("deptcode");
        String ywflListStr = request.getParameter("ywflListStr");
        List<String> ywflList = Arrays.asList(ywflListStr.split(","));
        String zt = request.getParameter("zt");
        List<String> list = getUpdeptcode(deptcode);//获取部门属性
        //获得今天的日期
        String dateStr = DateUtil.getFormatDate(new Date(), "yyyy-MM-dd");
        Map<String, Object> map = new HashMap<String, Object>();
        if (dateStr.equals(rq)) {//统计今天
            WxYyExample example = new WxYyExample();
            WxYyExample.Criteria ca = example.createCriteria();
            if (!StringUtils.isEmpty(list)) {
                ca.andDeptcodeIn(list);
            }
            if (!StringUtils.isEmpty(zt)) {
                ca.andZtEqualTo(zt);
            }
            if (!StringUtils.isEmpty(rq)) {
                ca.andYysjEqualTo(rq);
            }
            if (!StringUtils.isEmpty(ywflList)) {
                ca.andYwflIn(ywflList);
            }
            map = wxYyService.getStatisticsByToday(example);
            map.put("chooseType","today");
        } else {
            WxYyStatisticsExample example = new WxYyStatisticsExample();
            WxYyStatisticsExample.Criteria ca = example.createCriteria();
            if (!StringUtils.isEmpty(list)) {
                ca.andDeptcodeIn(list);
            }
            if (!StringUtils.isEmpty(nf)) {
                ca.andNfEqualTo(nf);
            }
            if (!StringUtils.isEmpty(yf)) {
                ca.andYfEqualTo(yf);
            }
            if (!StringUtils.isEmpty(rq)) {
                ca.andRqEqualTo(rq);
            }
            if (!StringUtils.isEmpty(zt)) {
                ca.andZtEqualTo(zt);
            }
            if (!StringUtils.isEmpty(ywflList)) {
                ca.andYwflIn(ywflList);
            }
            map = wxYyStatisticsService.getStatistics(example);
            map.put("chooseType","noToday");
        }
        map.put("title", title);
        //导出
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置标题的样式
        HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//标题居中左右居中
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//标题上下居中
        //设置标题字体样式
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 20);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        //标题边框
        cellStyleTitle.setFont(font);
        //设置公共单元格样式
        HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 创建一个工作表
        String fileName = "车架管业务预约查询统计(" + new Date().getTime() + ").xls";
        HSSFSheet sheet = workbook.createSheet("车架管业务");
        createSheetByCjg(sheet,cellStyleTitle,cellStyleCommon,map);
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

    public void createSheetByCjg(HSSFSheet sheet, HSSFCellStyle cellStyleTitle, HSSFCellStyle cellStyleCommon, Map<String, Object> map) {
        Map<String, String> deptMap = new LinkedHashMap<String, String>();
        List<Dept> deptlist = deptService.list(null);
        for (int i = 0; i < deptlist.size(); i++) {
            Dept dept = deptlist.get(i);
            deptMap.put(dept.getDeptcode(), dept.getDeptname());
        }
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(8);
        // 添加表头行
        HSSFRow titileRow = sheet.createRow(0);//第1行
        titileRow.setHeight((short)(40*20));
        HSSFCell headCell = titileRow.createCell(0);//第1行第1列
        headCell.setCellValue(map.get("title").toString());
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 22));
        headCell.setCellStyle(cellStyleTitle);
        HSSFRow secondRow = sheet.createRow(1);//第2行
        HSSFCell cell21 = secondRow.createCell(1);//第2行第2列
        cell21.setCellValue("预约总数合计");
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
        cell21.setCellStyle(cellStyleCommon);
        HSSFCell cell23 = secondRow.createCell(2);//第2行第3列
        cell23.setCellValue("机动车业务预约");
        sheet.addMergedRegion(new CellRangeAddress(1,1,2,12));
        cell23.setCellStyle(cellStyleCommon);
        HSSFCell cell214 = secondRow.createCell(13);//第2行第14列
        cell214.setCellValue("驾驶证业务预约");
        sheet.addMergedRegion(new CellRangeAddress(1,1,13,22));
        cell214.setCellStyle(cellStyleCommon);
        HSSFRow thirdRow = sheet.createRow(2);//第3行
        HSSFCell cell33 = thirdRow.createCell(2);//第3行第3列
        cell33.setCellValue("小计");
        cell33.setCellStyle(cellStyleCommon);
        HSSFCell cell34 = thirdRow.createCell(3);//第3行第4列
        cell34.setCellValue("小客车新车注册");
        cell34.setCellStyle(cellStyleCommon);
        HSSFCell cell35 = thirdRow.createCell(4);//第3行第5列
        cell35.setCellValue("二手车过户");
        cell35.setCellStyle(cellStyleCommon);
        HSSFCell cell36 = thirdRow.createCell(5);//第3行第6列
        cell36.setCellValue("小客车转入业务");
        cell36.setCellStyle(cellStyleCommon);
        HSSFCell cell37 = thirdRow.createCell(6);//第3行第7列
        cell37.setCellValue("补换领机动车号牌");
        cell37.setCellStyle(cellStyleCommon);
        HSSFCell cell38 = thirdRow.createCell(7);//第3行第8列
        cell38.setCellValue("补换领行驶证");
        cell38.setCellStyle(cellStyleCommon);
        HSSFCell cell39 = thirdRow.createCell(8);//第3行第9列
        cell39.setCellValue("抵押/解除抵押");
        cell39.setCellStyle(cellStyleCommon);
        HSSFCell cell310 = thirdRow.createCell(9);//第3行第10列
        cell310.setCellValue("大中型机动车注册登记");
        cell310.setCellStyle(cellStyleCommon);
        HSSFCell cell311 = thirdRow.createCell(10);//第3行第11列
        cell311.setCellValue("大中型机动车在用车业务");
        cell311.setCellStyle(cellStyleCommon);
        HSSFCell cell312 = thirdRow.createCell(11);//第3行第12列
        cell312.setCellValue("其他机动车业务");
        cell312.setCellStyle(cellStyleCommon);
        HSSFCell cell313 = thirdRow.createCell(12);//第3行第13列
        cell313.setCellValue("到场数");
        cell313.setCellStyle(cellStyleCommon);
        HSSFCell cell314 = thirdRow.createCell(13);//第3行第14列
        cell314.setCellValue("小计");
        cell314.setCellStyle(cellStyleCommon);
        HSSFCell cell315 = thirdRow.createCell(14);//第3行第15列
        cell315.setCellValue("外地驾驶证转入");
        cell315.setCellStyle(cellStyleCommon);
        HSSFCell cell316 = thirdRow.createCell(15);//第3行第16列
        cell316.setCellValue("期满换证");
        cell316.setCellStyle(cellStyleCommon);
        HSSFCell cell317 = thirdRow.createCell(16);//第3行第17列
        cell317.setCellValue("补换领驾驶证");
        cell317.setCellStyle(cellStyleCommon);
        HSSFCell cell318 = thirdRow.createCell(17);//第3行第18列
        cell318.setCellValue("军警换证");
        cell318.setCellStyle(cellStyleCommon);
        HSSFCell cell319 = thirdRow.createCell(18);//第3行第19列
        cell319.setCellValue("境外换证");
        cell319.setCellStyle(cellStyleCommon);
        HSSFCell cell320 = thirdRow.createCell(19);//第3行第20列
        cell320.setCellValue("满分业务");
        cell320.setCellStyle(cellStyleCommon);
        HSSFCell cell321 = thirdRow.createCell(20);//第3行第21列
        cell321.setCellValue("审验业务");
        cell321.setCellStyle(cellStyleCommon);
        HSSFCell cell322 = thirdRow.createCell(21);//第3行第22列
        cell322.setCellValue("其他业务");
        cell322.setCellStyle(cellStyleCommon);
        HSSFCell cell323 = thirdRow.createCell(22);//第3行第23列
        cell323.setCellValue("到场数");
        cell323.setCellStyle(cellStyleCommon);
        if("today".equals(map.get("chooseType"))){
            List<WxYyDto> wxYyDtoList = (List<WxYyDto>) map.get("mx");
            WxYyDto allvo = (WxYyDto) map.get("zj");
            createContentByTodayAndCjg(sheet,wxYyDtoList,allvo,cellStyleCommon,deptMap);
        }else{
            List<WxYyStatisticsDto> wxYyStatisticsDtoList = (List<WxYyStatisticsDto>) map.get("mx");
            WxYyStatisticsDto allvo = (WxYyStatisticsDto) map.get("zj");
            createContentByCjg(sheet,wxYyStatisticsDtoList,allvo,cellStyleCommon,deptMap);
        }
    }

    public void createContentByCjg(HSSFSheet sheet,List<WxYyStatisticsDto> wxYyStatisticsDtoList,WxYyStatisticsDto allvo, HSSFCellStyle cellStyleCommon,Map<String, String> deptMap){
        for(int i=0; i<wxYyStatisticsDtoList.size() ;i++){
            WxYyStatisticsDto entity = wxYyStatisticsDtoList.get(i);
            HSSFRow commonRow = sheet.createRow(i+3);//第4行
            HSSFCell cell1 = commonRow.createCell(0);//第4行第1列
            cell1.setCellValue(deptMap.get(entity.getDeptcode()));
            cell1.setCellStyle(cellStyleCommon);
            HSSFCell cell2 = commonRow.createCell(1);//第4行第2列
            cell2.setCellValue(entity.getSumjdcxkcxczc()+entity.getSumjdcescgh()+entity.getSumjdcxkczr()+entity.getSumjdcbhljdchp()+entity.getSumjdcbhlxsz()+entity.getSumjdcdyyw()+entity.getSumjdcdzxzcdj()+entity.getSumjdcdzxzycyw()+entity.getSumjdcqtyw()+
                    entity.getSumjszwdzr()+entity.getSumjszqmhz()+entity.getSumjszbhlyw()+entity.getSumjszjjhz()+entity.getSumjszjwhz()+entity.getSumjszmfyw()+entity.getSumjszsyyw()+entity.getSumjszqtyw());
            cell2.setCellStyle(cellStyleCommon);
            HSSFCell cell3 = commonRow.createCell(2);//第4行第3列
            cell3.setCellValue(entity.getSumjdcxkcxczc()+entity.getSumjdcescgh()+entity.getSumjdcxkczr()+entity.getSumjdcbhljdchp()+entity.getSumjdcbhlxsz()+entity.getSumjdcdyyw()+entity.getSumjdcdzxzcdj()+entity.getSumjdcdzxzycyw()+entity.getSumjdcqtyw());
            cell3.setCellStyle(cellStyleCommon);
            HSSFCell cell4 = commonRow.createCell(3);//第4行第4列
            cell4.setCellValue(entity.getSumjdcxkcxczc());
            cell4.setCellStyle(cellStyleCommon);
            HSSFCell cell5 = commonRow.createCell(4);//第4行第5列
            cell5.setCellValue(entity.getSumjdcescgh());
            cell5.setCellStyle(cellStyleCommon);
            HSSFCell cell6 = commonRow.createCell(5);//第4行第6列
            cell6.setCellValue(entity.getSumjdcxkczr());
            cell6.setCellStyle(cellStyleCommon);
            HSSFCell cell7 = commonRow.createCell(6);//第4行第7列
            cell7.setCellValue(entity.getSumjdcbhljdchp());
            cell7.setCellStyle(cellStyleCommon);
            HSSFCell cell8 = commonRow.createCell(7);//第4行第8列
            cell8.setCellValue(entity.getSumjdcbhlxsz());
            cell8.setCellStyle(cellStyleCommon);
            HSSFCell cell9 = commonRow.createCell(8);//第4行第9列
            cell9.setCellValue(entity.getSumjdcdyyw());
            cell9.setCellStyle(cellStyleCommon);
            HSSFCell cell10 = commonRow.createCell(9);//第4行第10列
            cell10.setCellValue(entity.getSumjdcdzxzcdj());
            cell10.setCellStyle(cellStyleCommon);
            HSSFCell cell11 = commonRow.createCell(10);//第4行第11列
            cell11.setCellValue(entity.getSumjdcdzxzycyw());
            cell11.setCellStyle(cellStyleCommon);
            HSSFCell cell12 = commonRow.createCell(11);//第4行第12列
            cell12.setCellValue(entity.getSumjdcqtyw());
            cell12.setCellStyle(cellStyleCommon);
            HSSFCell cell13 = commonRow.createCell(12);//第4行第13列
            cell13.setCellValue(entity.getSumjdcdcs());
            cell13.setCellStyle(cellStyleCommon);
            HSSFCell cell14 = commonRow.createCell(13);//第4行第14列
            cell14.setCellValue(entity.getSumjszwdzr()+entity.getSumjszqmhz()+entity.getSumjszbhlyw()+entity.getSumjszjjhz()+entity.getSumjszjwhz()+entity.getSumjszmfyw()+entity.getSumjszsyyw()+entity.getSumjszqtyw());
            cell14.setCellStyle(cellStyleCommon);
            HSSFCell cell15 = commonRow.createCell(14);//第4行第15列
            cell15.setCellValue(entity.getSumjszwdzr());
            cell15.setCellStyle(cellStyleCommon);
            HSSFCell cell16 = commonRow.createCell(15);//第4行第16列
            cell16.setCellValue(entity.getSumjszqmhz());
            cell16.setCellStyle(cellStyleCommon);
            HSSFCell cell17 = commonRow.createCell(16);//第4行第17列
            cell17.setCellValue(entity.getSumjszbhlyw());
            cell17.setCellStyle(cellStyleCommon);
            HSSFCell cell18 = commonRow.createCell(17);//第4行第18列
            cell18.setCellValue(entity.getSumjszjjhz());
            cell18.setCellStyle(cellStyleCommon);
            HSSFCell cell19 = commonRow.createCell(18);//第4行第19列
            cell19.setCellValue(entity.getSumjszjwhz());
            cell19.setCellStyle(cellStyleCommon);
            HSSFCell cell20 = commonRow.createCell(19);//第4行第20列
            cell20.setCellValue(entity.getSumjszmfyw());
            cell20.setCellStyle(cellStyleCommon);
            HSSFCell cell21 = commonRow.createCell(20);//第4行第21列
            cell21.setCellValue(entity.getSumjszsyyw());
            cell21.setCellStyle(cellStyleCommon);
            HSSFCell cell22 = commonRow.createCell(21);//第4行第22列
            cell22.setCellValue(entity.getSumjszqtyw());
            cell22.setCellStyle(cellStyleCommon);
            HSSFCell cell23 = commonRow.createCell(22);//第4行第23列
            cell23.setCellValue(entity.getSumjszdcs());
            cell23.setCellStyle(cellStyleCommon);
        }
        HSSFRow laseRow = sheet.createRow(wxYyStatisticsDtoList.size()+3);//最后一行
        HSSFCell cell1 = laseRow.createCell(0);
        cell1.setCellValue("合计");
        cell1.setCellStyle(cellStyleCommon);
        HSSFCell cell2 = laseRow.createCell(1);
        cell2.setCellValue(allvo.getSumjdcxkcxczc()+allvo.getSumjdcescgh()+allvo.getSumjdcxkczr()+allvo.getSumjdcbhljdchp()+allvo.getSumjdcbhlxsz()+allvo.getSumjdcdyyw()+allvo.getSumjdcdzxzcdj()+allvo.getSumjdcdzxzycyw()+allvo.getSumjdcqtyw()+
                allvo.getSumjszwdzr()+allvo.getSumjszqmhz()+allvo.getSumjszbhlyw()+allvo.getSumjszjjhz()+allvo.getSumjszjwhz()+allvo.getSumjszmfyw()+allvo.getSumjszsyyw()+allvo.getSumjszqtyw());
        cell2.setCellStyle(cellStyleCommon);
        HSSFCell cell3 = laseRow.createCell(2);
        cell3.setCellValue(allvo.getSumjdcxkcxczc()+allvo.getSumjdcescgh()+allvo.getSumjdcxkczr()+allvo.getSumjdcbhljdchp()+allvo.getSumjdcbhlxsz()+allvo.getSumjdcdyyw()+allvo.getSumjdcdzxzcdj()+allvo.getSumjdcdzxzycyw()+allvo.getSumjdcqtyw());
        cell3.setCellStyle(cellStyleCommon);
        HSSFCell cell4 = laseRow.createCell(3);
        cell4.setCellValue(allvo.getSumjdcxkcxczc());
        cell4.setCellStyle(cellStyleCommon);
        HSSFCell cell5 = laseRow.createCell(4);
        cell5.setCellValue(allvo.getSumjdcescgh());
        cell5.setCellStyle(cellStyleCommon);
        HSSFCell cell6 = laseRow.createCell(5);
        cell6.setCellValue(allvo.getSumjdcxkczr());
        cell6.setCellStyle(cellStyleCommon);
        HSSFCell cell7 = laseRow.createCell(6);
        cell7.setCellValue(allvo.getSumjdcbhljdchp());
        cell7.setCellStyle(cellStyleCommon);
        HSSFCell cell8 = laseRow.createCell(7);
        cell8.setCellValue(allvo.getSumjdcbhlxsz());
        cell8.setCellStyle(cellStyleCommon);
        HSSFCell cell9 = laseRow.createCell(8);
        cell9.setCellValue(allvo.getSumjdcdyyw());
        cell9.setCellStyle(cellStyleCommon);
        HSSFCell cell10 = laseRow.createCell(9);
        cell10.setCellValue(allvo.getSumjdcdzxzcdj());
        cell10.setCellStyle(cellStyleCommon);
        HSSFCell cell11 = laseRow.createCell(10);
        cell11.setCellValue(allvo.getSumjdcdzxzycyw());
        cell11.setCellStyle(cellStyleCommon);
        HSSFCell cell12 = laseRow.createCell(11);
        cell12.setCellValue(allvo.getSumjdcqtyw());
        cell12.setCellStyle(cellStyleCommon);
        HSSFCell cell13 = laseRow.createCell(12);
        cell13.setCellValue(allvo.getSumjdcdcs());
        cell13.setCellStyle(cellStyleCommon);
        HSSFCell cell14 = laseRow.createCell(13);
        cell14.setCellValue(allvo.getSumjszwdzr()+allvo.getSumjszqmhz()+allvo.getSumjszbhlyw()+allvo.getSumjszjjhz()+allvo.getSumjszjwhz()+allvo.getSumjszmfyw()+allvo.getSumjszsyyw()+allvo.getSumjszqtyw());
        cell14.setCellStyle(cellStyleCommon);
        HSSFCell cell15 = laseRow.createCell(14);
        cell15.setCellValue(allvo.getSumjszwdzr());
        cell15.setCellStyle(cellStyleCommon);
        HSSFCell cell16 = laseRow.createCell(15);
        cell16.setCellValue(allvo.getSumjszqmhz());
        cell16.setCellStyle(cellStyleCommon);
        HSSFCell cell17 = laseRow.createCell(16);
        cell17.setCellValue(allvo.getSumjszbhlyw());
        cell17.setCellStyle(cellStyleCommon);
        HSSFCell cell18 = laseRow.createCell(17);
        cell18.setCellValue(allvo.getSumjszjjhz());
        cell18.setCellStyle(cellStyleCommon);
        HSSFCell cell19 = laseRow.createCell(18);
        cell19.setCellValue(allvo.getSumjszjwhz());
        cell19.setCellStyle(cellStyleCommon);
        HSSFCell cell20 = laseRow.createCell(19);
        cell20.setCellValue(allvo.getSumjszmfyw());
        cell20.setCellStyle(cellStyleCommon);
        HSSFCell cell21 = laseRow.createCell(20);
        cell21.setCellValue(allvo.getSumjszsyyw());
        cell21.setCellStyle(cellStyleCommon);
        HSSFCell cell22 = laseRow.createCell(21);
        cell22.setCellValue(allvo.getSumjszqtyw());
        cell22.setCellStyle(cellStyleCommon);
        HSSFCell cell23 = laseRow.createCell(22);
        cell23.setCellValue(allvo.getSumjszdcs());
        cell23.setCellStyle(cellStyleCommon);
    }

    public void createContentByTodayAndCjg(HSSFSheet sheet,List<WxYyDto> wxYyDtoList,WxYyDto allvo, HSSFCellStyle cellStyleCommon,Map<String, String> deptMap){
        for(int i=0; i<wxYyDtoList.size() ;i++){
            WxYyDto entity = wxYyDtoList.get(i);
            HSSFRow commonRow = sheet.createRow(i+3);//第4行
            HSSFCell cell1 = commonRow.createCell(0);//第4行第1列
            cell1.setCellValue(deptMap.get(entity.getDeptcode()));
            cell1.setCellStyle(cellStyleCommon);
            HSSFCell cell2 = commonRow.createCell(1);//第4行第2列
            cell2.setCellValue(entity.getSumjdcxkcxczc()+entity.getSumjdcescgh()+entity.getSumjdcxkczr()+entity.getSumjdcbhljdchp()+entity.getSumjdcbhlxsz()+entity.getSumjdcdyyw()+entity.getSumjdcdzxzcdj()+entity.getSumjdcdzxzycyw()+entity.getSumjdcqtyw()+
                    entity.getSumjszwdzr()+entity.getSumjszqmhz()+entity.getSumjszbhlyw()+entity.getSumjszjjhz()+entity.getSumjszjwhz()+entity.getSumjszmfyw()+entity.getSumjszsyyw()+entity.getSumjszqtyw());
            cell2.setCellStyle(cellStyleCommon);
            HSSFCell cell3 = commonRow.createCell(2);//第4行第3列
            cell3.setCellValue(entity.getSumjdcxkcxczc()+entity.getSumjdcescgh()+entity.getSumjdcxkczr()+entity.getSumjdcbhljdchp()+entity.getSumjdcbhlxsz()+entity.getSumjdcdyyw()+entity.getSumjdcdzxzcdj()+entity.getSumjdcdzxzycyw()+entity.getSumjdcqtyw());
            cell3.setCellStyle(cellStyleCommon);
            HSSFCell cell4 = commonRow.createCell(3);//第4行第4列
            cell4.setCellValue(entity.getSumjdcxkcxczc());
            cell4.setCellStyle(cellStyleCommon);
            HSSFCell cell5 = commonRow.createCell(4);//第4行第5列
            cell5.setCellValue(entity.getSumjdcescgh());
            cell5.setCellStyle(cellStyleCommon);
            HSSFCell cell6 = commonRow.createCell(5);//第4行第6列
            cell6.setCellValue(entity.getSumjdcxkczr());
            cell6.setCellStyle(cellStyleCommon);
            HSSFCell cell7 = commonRow.createCell(6);//第4行第7列
            cell7.setCellValue(entity.getSumjdcbhljdchp());
            cell7.setCellStyle(cellStyleCommon);
            HSSFCell cell8 = commonRow.createCell(7);//第4行第8列
            cell8.setCellValue(entity.getSumjdcbhlxsz());
            cell8.setCellStyle(cellStyleCommon);
            HSSFCell cell9 = commonRow.createCell(8);//第4行第9列
            cell9.setCellValue(entity.getSumjdcdyyw());
            cell9.setCellStyle(cellStyleCommon);
            HSSFCell cell10 = commonRow.createCell(9);//第4行第10列
            cell10.setCellValue(entity.getSumjdcdzxzcdj());
            cell10.setCellStyle(cellStyleCommon);
            HSSFCell cell11 = commonRow.createCell(10);//第4行第11列
            cell11.setCellValue(entity.getSumjdcdzxzycyw());
            cell11.setCellStyle(cellStyleCommon);
            HSSFCell cell12 = commonRow.createCell(11);//第4行第12列
            cell12.setCellValue(entity.getSumjdcqtyw());
            cell12.setCellStyle(cellStyleCommon);
            HSSFCell cell13 = commonRow.createCell(12);//第4行第13列
            cell13.setCellValue(entity.getSumjdcdcs());
            cell13.setCellStyle(cellStyleCommon);
            HSSFCell cell14 = commonRow.createCell(13);//第4行第14列
            cell14.setCellValue(entity.getSumjszwdzr()+entity.getSumjszqmhz()+entity.getSumjszbhlyw()+entity.getSumjszjjhz()+entity.getSumjszjwhz()+entity.getSumjszmfyw()+entity.getSumjszsyyw()+entity.getSumjszqtyw());
            cell14.setCellStyle(cellStyleCommon);
            HSSFCell cell15 = commonRow.createCell(14);//第4行第15列
            cell15.setCellValue(entity.getSumjszwdzr());
            cell15.setCellStyle(cellStyleCommon);
            HSSFCell cell16 = commonRow.createCell(15);//第4行第16列
            cell16.setCellValue(entity.getSumjszqmhz());
            cell16.setCellStyle(cellStyleCommon);
            HSSFCell cell17 = commonRow.createCell(16);//第4行第17列
            cell17.setCellValue(entity.getSumjszbhlyw());
            cell17.setCellStyle(cellStyleCommon);
            HSSFCell cell18 = commonRow.createCell(17);//第4行第18列
            cell18.setCellValue(entity.getSumjszjjhz());
            cell18.setCellStyle(cellStyleCommon);
            HSSFCell cell19 = commonRow.createCell(18);//第4行第19列
            cell19.setCellValue(entity.getSumjszjwhz());
            cell19.setCellStyle(cellStyleCommon);
            HSSFCell cell20 = commonRow.createCell(19);//第4行第20列
            cell20.setCellValue(entity.getSumjszmfyw());
            cell20.setCellStyle(cellStyleCommon);
            HSSFCell cell21 = commonRow.createCell(20);//第4行第21列
            cell21.setCellValue(entity.getSumjszsyyw());
            cell21.setCellStyle(cellStyleCommon);
            HSSFCell cell22 = commonRow.createCell(21);//第4行第22列
            cell22.setCellValue(entity.getSumjszqtyw());
            cell22.setCellStyle(cellStyleCommon);
            HSSFCell cell23 = commonRow.createCell(22);//第4行第23列
            cell23.setCellValue(entity.getSumjszdcs());
            cell23.setCellStyle(cellStyleCommon);
        }
        HSSFRow laseRow = sheet.createRow(wxYyDtoList.size()+3);//最后一行
        HSSFCell cell1 = laseRow.createCell(0);
        cell1.setCellValue("合计");
        cell1.setCellStyle(cellStyleCommon);
        HSSFCell cell2 = laseRow.createCell(1);
        cell2.setCellValue(allvo.getSumjdcxkcxczc()+allvo.getSumjdcescgh()+allvo.getSumjdcxkczr()+allvo.getSumjdcbhljdchp()+allvo.getSumjdcbhlxsz()+allvo.getSumjdcdyyw()+allvo.getSumjdcdzxzcdj()+allvo.getSumjdcdzxzycyw()+allvo.getSumjdcqtyw()+
                allvo.getSumjszwdzr()+allvo.getSumjszqmhz()+allvo.getSumjszbhlyw()+allvo.getSumjszjjhz()+allvo.getSumjszjwhz()+allvo.getSumjszmfyw()+allvo.getSumjszsyyw()+allvo.getSumjszqtyw());
        cell2.setCellStyle(cellStyleCommon);
        HSSFCell cell3 = laseRow.createCell(2);
        cell3.setCellValue(allvo.getSumjdcxkcxczc()+allvo.getSumjdcescgh()+allvo.getSumjdcxkczr()+allvo.getSumjdcbhljdchp()+allvo.getSumjdcbhlxsz()+allvo.getSumjdcdyyw()+allvo.getSumjdcdzxzcdj()+allvo.getSumjdcdzxzycyw()+allvo.getSumjdcqtyw());
        cell3.setCellStyle(cellStyleCommon);
        HSSFCell cell4 = laseRow.createCell(3);
        cell4.setCellValue(allvo.getSumjdcxkcxczc());
        cell4.setCellStyle(cellStyleCommon);
        HSSFCell cell5 = laseRow.createCell(4);
        cell5.setCellValue(allvo.getSumjdcescgh());
        cell5.setCellStyle(cellStyleCommon);
        HSSFCell cell6 = laseRow.createCell(5);
        cell6.setCellValue(allvo.getSumjdcxkczr());
        cell6.setCellStyle(cellStyleCommon);
        HSSFCell cell7 = laseRow.createCell(6);
        cell7.setCellValue(allvo.getSumjdcbhljdchp());
        cell7.setCellStyle(cellStyleCommon);
        HSSFCell cell8 = laseRow.createCell(7);
        cell8.setCellValue(allvo.getSumjdcbhlxsz());
        cell8.setCellStyle(cellStyleCommon);
        HSSFCell cell9 = laseRow.createCell(8);
        cell9.setCellValue(allvo.getSumjdcdyyw());
        cell9.setCellStyle(cellStyleCommon);
        HSSFCell cell10 = laseRow.createCell(9);
        cell10.setCellValue(allvo.getSumjdcdzxzcdj());
        cell10.setCellStyle(cellStyleCommon);
        HSSFCell cell11 = laseRow.createCell(10);
        cell11.setCellValue(allvo.getSumjdcdzxzycyw());
        cell11.setCellStyle(cellStyleCommon);
        HSSFCell cell12 = laseRow.createCell(11);
        cell12.setCellValue(allvo.getSumjdcqtyw());
        cell12.setCellStyle(cellStyleCommon);
        HSSFCell cell13 = laseRow.createCell(12);
        cell13.setCellValue(allvo.getSumjdcdcs());
        cell13.setCellStyle(cellStyleCommon);
        HSSFCell cell14 = laseRow.createCell(13);
        cell14.setCellValue(allvo.getSumjszwdzr()+allvo.getSumjszqmhz()+allvo.getSumjszbhlyw()+allvo.getSumjszjjhz()+allvo.getSumjszjwhz()+allvo.getSumjszmfyw()+allvo.getSumjszsyyw()+allvo.getSumjszqtyw());
        cell14.setCellStyle(cellStyleCommon);
        HSSFCell cell15 = laseRow.createCell(14);
        cell15.setCellValue(allvo.getSumjszwdzr());
        cell15.setCellStyle(cellStyleCommon);
        HSSFCell cell16 = laseRow.createCell(15);
        cell16.setCellValue(allvo.getSumjszqmhz());
        cell16.setCellStyle(cellStyleCommon);
        HSSFCell cell17 = laseRow.createCell(16);
        cell17.setCellValue(allvo.getSumjszbhlyw());
        cell17.setCellStyle(cellStyleCommon);
        HSSFCell cell18 = laseRow.createCell(17);
        cell18.setCellValue(allvo.getSumjszjjhz());
        cell18.setCellStyle(cellStyleCommon);
        HSSFCell cell19 = laseRow.createCell(18);
        cell19.setCellValue(allvo.getSumjszjwhz());
        cell19.setCellStyle(cellStyleCommon);
        HSSFCell cell20 = laseRow.createCell(19);
        cell20.setCellValue(allvo.getSumjszmfyw());
        cell20.setCellStyle(cellStyleCommon);
        HSSFCell cell21 = laseRow.createCell(20);
        cell21.setCellValue(allvo.getSumjszsyyw());
        cell21.setCellStyle(cellStyleCommon);
        HSSFCell cell22 = laseRow.createCell(21);
        cell22.setCellValue(allvo.getSumjszqtyw());
        cell22.setCellStyle(cellStyleCommon);
        HSSFCell cell23 = laseRow.createCell(22);
        cell23.setCellValue(allvo.getSumjszdcs());
        cell23.setCellStyle(cellStyleCommon);
    }

}
