package com.pd.system.controller.conf;

import com.pd.server.main.dto.AppMonitorExpDto;
import com.pd.server.main.service.AppMonitorExpService;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/excel")
public class ExcelController extends BaseController{

    @Resource
    private AppMonitorExpService appMonitorExpService;

    @GetMapping("/exportCarinfo")
    public void exportCarinfo(AppMonitorExpDto appMonitorExpDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<AppMonitorExpDto> list = appMonitorExpService.listByDay(appMonitorExpDto);

        try {
            // 创建Excel工作簿
            XSSFWorkbook workbook = new XSSFWorkbook();

            // 设置字体大小
            XSSFFont fontCommon = workbook.createFont();
            fontCommon.setFontHeightInPoints((short)12); // 设置字体大小为12磅

            // 设置公共单元格样式
            XSSFCellStyle cellStyleCommon = workbook.createCellStyle();
            cellStyleCommon.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleCommon.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

            // 设置字体加粗
            XSSFFont font = workbook.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short)12); // 设置字体大小为12磅
            XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
            cellStyleTitle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            cellStyleTitle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            cellStyleTitle.setFont(font);

            // 创建工作表
            String fileName = "巡查信息(" + new Date().getTime() + ").xls";
            XSSFSheet sheet = workbook.createSheet("巡查信息数据");

            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(18);
            sheet.setDefaultRowHeight((short)(40*10));

            // 添加表头行
            XSSFRow titleRow = sheet.createRow(0);//第1行
            List<String> titleStrList = Arrays.asList("Date1", "Time1", "DecLat", "DecLong", "Event", "Data1", "Data2", "Data3", "Data4", "Data5", "Data6", "Data7", "Data8");
            for(int i=0; i<titleStrList.size(); i++){
                XSSFCell cell = titleRow.createCell(i);
                cell.setCellValue(titleStrList.get(i));
                cell.setCellStyle(cellStyleTitle);
            }

            // 填充数据
            int i = 0;
            if (list != null) {
                for(AppMonitorExpDto entity : list){
                XSSFRow comRow = sheet.createRow(i+1);

                XSSFCell comCell0 = comRow.createCell(0);
                comCell0.setCellValue(entity.getDate1());
                comCell0.setCellStyle(cellStyleCommon);

                XSSFCell comCell1 = comRow.createCell(1);
                comCell1.setCellValue(entity.getTime1());
                comCell1.setCellStyle(cellStyleCommon);

                XSSFCell comCell2 = comRow.createCell(2);
                comCell2.setCellValue(entity.getDeclat());
                comCell2.setCellStyle(cellStyleCommon);

                XSSFCell comCell3 = comRow.createCell(3);
                comCell3.setCellValue(entity.getDeclong());
                comCell3.setCellStyle(cellStyleCommon);

                XSSFCell comCell4 = comRow.createCell(4);
                comCell4.setCellValue(entity.getEvents());
                comCell4.setCellStyle(cellStyleCommon);

                XSSFCell comCell5 = comRow.createCell(5);
                comCell5.setCellValue(entity.getData1());
                comCell5.setCellStyle(cellStyleCommon);

                XSSFCell comCell6 = comRow.createCell(6);
                comCell6.setCellValue(entity.getData2());
                comCell6.setCellStyle(cellStyleCommon);

                XSSFCell comCell7 = comRow.createCell(7);
                comCell7.setCellValue(entity.getData3());
                comCell7.setCellStyle(cellStyleCommon);

                XSSFCell comCell8 = comRow.createCell(8);
                comCell8.setCellValue(entity.getData4());
                comCell8.setCellStyle(cellStyleCommon);

                XSSFCell comCell9 = comRow.createCell(9);
                comCell9.setCellValue(entity.getData5());
                comCell9.setCellStyle(cellStyleCommon);

                XSSFCell comCell10 = comRow.createCell(10);
                comCell10.setCellValue(entity.getData6());
                comCell10.setCellStyle(cellStyleCommon);

                XSSFCell comCell11 = comRow.createCell(11);
                comCell11.setCellValue(entity.getData7());
                comCell11.setCellStyle(cellStyleCommon);

                XSSFCell comCell12 = comRow.createCell(12);
                comCell12.setCellValue(entity.getData8());
                comCell12.setCellStyle(cellStyleCommon);

                i++;
                }
            }

            // 设置响应头
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

            // 输出Excel文件
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}