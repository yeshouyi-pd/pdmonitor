
package com.pd.wxbase.wx.controller;

import com.pd.server.main.domain.Dept;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.VehParkLotDto;
import com.pd.server.main.dto.VehParkLotStatisticsDto;
import com.pd.server.main.service.DeptService;
import com.pd.server.main.service.VehParkLotService;
import com.pd.server.main.service.VehParkLotStatisticsService;
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
@RequestMapping("/export/vehParkLotStatistics")
public class VehParkLotStatisticsExportController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(VehParkLotStatisticsExportController.class);
    public static final String BUSINESS_NAME = "违法车统计导出";

    @Resource
    private VehParkLotStatisticsService vehParkLotStatisticsService;
    @Resource
    private VehParkLotService vehParkLotService;
    @Resource
    private DeptService deptService;

    /**
     * 导出
     */
    @GetMapping("/exportVehParkLotStatistics")
    public void exportVehParkLotStatistics(HttpServletRequest request, HttpServletResponse response)throws Exception {
        String chooseType = request.getParameter("chooseType");
        String checkdate = request.getParameter("checkdate");
        String title = request.getParameter("title");
        String deptcode = request.getParameter("deptcode");
        VehParkLotStatisticsDto vehParkLotStatisticsDto = new VehParkLotStatisticsDto();
        vehParkLotStatisticsDto.setChooseType(chooseType);
        vehParkLotStatisticsDto.setCheckdate(checkdate);
        List<String> list = getUpdeptcode(deptcode);//获取部门属性

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
//        cellStyleTitle.setBorderTop(CellStyle.BORDER_THIN);
//        cellStyleTitle.setBorderLeft(CellStyle.BORDER_THIN);
//        cellStyleTitle.setBorderRight(CellStyle.BORDER_THIN);
//        cellStyleTitle.setBorderBottom(CellStyle.BORDER_THIN);
        //设置公共单元格样式
        HSSFCellStyle cellStyleCommon = workbook.createCellStyle();
        cellStyleCommon.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cellStyleCommon.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        cellStyleCommon.setBorderTop(CellStyle.BORDER_THIN);
//        cellStyleCommon.setBorderLeft(CellStyle.BORDER_THIN);
//        cellStyleCommon.setBorderRight(CellStyle.BORDER_THIN);
//        cellStyleCommon.setBorderBottom(CellStyle.BORDER_THIN);
        // 创建一个工作表
        String fileName = "报废车统计表(" + new Date().getTime() + ").xls";

        Map<String,Object> rlist = new HashMap<String,Object>();
        Map<String,Object> clist = new HashMap<String,Object>();
        //获得今天的日期
        String dateStr = DateUtil.getFormatDate(new Date(),"yyyy-MM-dd");
        if(dateStr.equals(vehParkLotStatisticsDto.getCheckdate())){//统计今天
            VehParkLotDto vehParkLotDto = new VehParkLotDto();
            vehParkLotDto.setZt("1");
            vehParkLotDto.setSysOrgCodeListStr(list);
            rlist = vehParkLotService.getStatisticsByToday(vehParkLotDto);
            vehParkLotDto.setZt("2");
            clist = vehParkLotService.getStatisticsByToday(vehParkLotDto);
            HSSFSheet sheet = workbook.createSheet("入场明细");
            List<VehParkLotDto> rList = !StringUtils.isEmpty(rlist.get("mx"))? (List<VehParkLotDto>) rlist.get("mx") :new ArrayList<>();
            VehParkLotDto vehParkLotDtoRc = (VehParkLotDto) rlist.get("zj");
            createSheetByToday(sheet,rList,cellStyleTitle,cellStyleCommon,title,vehParkLotDtoRc);
            HSSFSheet sheet1 = workbook.createSheet("出场明细");
            List<VehParkLotDto> cList = !StringUtils.isEmpty(clist.get("mx"))? (List<VehParkLotDto>) clist.get("mx") :new ArrayList<>();
            VehParkLotDto vehParkLotDtoCc = (VehParkLotDto) clist.get("zj");
            createSheetByToday(sheet1,cList,cellStyleTitle,cellStyleCommon,title,vehParkLotDtoCc);
        }else{//按类型同统计
            vehParkLotStatisticsDto.setSysOrgCodeListStr(list);
            vehParkLotStatisticsDto.setZt("1");
            if("1".equals(vehParkLotStatisticsDto.getChooseType())){
                vehParkLotStatisticsDto.setChooseType("%Y");//年
            }
            if("2".equals(vehParkLotStatisticsDto.getChooseType())){
                vehParkLotStatisticsDto.setChooseType("%Y-%m");//月
            }
            if("3".equals(vehParkLotStatisticsDto.getChooseType())){
                vehParkLotStatisticsDto.setChooseType("%Y-%m-%d");//日
            }
            rlist = vehParkLotStatisticsService.getStatistics(vehParkLotStatisticsDto);
            vehParkLotStatisticsDto.setZt("2");
            clist = vehParkLotStatisticsService.getStatistics(vehParkLotStatisticsDto);
            HSSFSheet sheet = workbook.createSheet("入场明细");
            List<VehParkLotStatisticsDto> rList = !StringUtils.isEmpty(rlist.get("mx"))? (List<VehParkLotStatisticsDto>) rlist.get("mx") :new ArrayList<>();
            VehParkLotStatisticsDto vehParkLotStatisticsDtoRc = (VehParkLotStatisticsDto) rlist.get("zj");
            createSheet(sheet,rList,cellStyleTitle,cellStyleCommon,title,vehParkLotStatisticsDtoRc);
            HSSFSheet sheet1 = workbook.createSheet("出场明细");
            List<VehParkLotStatisticsDto> cList = !StringUtils.isEmpty(clist.get("mx"))? (List<VehParkLotStatisticsDto>) clist.get("mx") :new ArrayList<>();
            VehParkLotStatisticsDto vehParkLotStatisticsDtoCc = (VehParkLotStatisticsDto) clist.get("zj");
            createSheet(sheet1,cList,cellStyleTitle,cellStyleCommon,title,vehParkLotStatisticsDtoCc);
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

    public void createSheetByToday(HSSFSheet sheet,List<VehParkLotDto> rArr,HSSFCellStyle cellStyleTitle,HSSFCellStyle cellStyleCommon,String title,VehParkLotDto vehParkLotDtoSum){
        Map<String,String> map = new LinkedHashMap<String,String>();
        List<Dept> deptlist = deptService.list(null);
        for (int i = 0; i < deptlist.size(); i++) {
            Dept dept = deptlist.get(i);
            map.put(dept.getDeptcode(), dept.getDeptname());
        }
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(8);
        // 添加表头行
        HSSFRow titileRow = sheet.createRow(0);//第1行
        titileRow.setHeight((short)(40*20));
        HSSFCell headCell = titileRow.createCell(0);//第1行第1列
        headCell.setCellValue(title);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));
        headCell.setCellStyle(cellStyleTitle);
        HSSFRow secondRow = sheet.createRow(1);//第2行
        HSSFCell cell21 = secondRow.createCell(1);//第2行第2列
        cell21.setCellValue("电动车");
        sheet.addMergedRegion(new CellRangeAddress(1,1,1,4));
        cell21.setCellStyle(cellStyleCommon);
        HSSFCell cell25 = secondRow.createCell(5);//第2行第6列
        cell25.setCellValue("摩托车");
        sheet.addMergedRegion(new CellRangeAddress(1,1,5,7));
        cell25.setCellStyle(cellStyleCommon);
        HSSFCell cell28 = secondRow.createCell(8);//第2行第9列
        cell28.setCellValue("三轮车");
        sheet.addMergedRegion(new CellRangeAddress(1,2,8,8));
        cell28.setCellStyle(cellStyleCommon);
        HSSFCell cell29 = secondRow.createCell(9);//第2行第10列
        cell29.setCellValue("汽车");
        sheet.addMergedRegion(new CellRangeAddress(1,2,9,9));
        cell29.setCellStyle(cellStyleCommon);
        HSSFCell cell210 = secondRow.createCell(10);//第2行第11列
        cell210.setCellValue("农用车");
        sheet.addMergedRegion(new CellRangeAddress(1,2,10,10));
        cell210.setCellStyle(cellStyleCommon);
        HSSFCell cell211 = secondRow.createCell(11);//第2行第12列
        cell211.setCellValue("其他");
        sheet.addMergedRegion(new CellRangeAddress(1,2,11,11));
        cell211.setCellStyle(cellStyleCommon);
        HSSFCell cell212 = secondRow.createCell(12);//第2行第13列
        cell212.setCellValue("合计");
        cell212.setCellStyle(cellStyleCommon);
        sheet.addMergedRegion(new CellRangeAddress(1,2,12,12));
        HSSFCell cell213 = secondRow.createCell(13);//第2行第14列
        cell213.setCellValue("进场合计");
        sheet.addMergedRegion(new CellRangeAddress(1,2,13,13));
        cell213.setCellStyle(cellStyleCommon);
        HSSFCell cell214 = secondRow.createCell(14);//第2行第15列
        cell214.setCellValue("分类");
        sheet.addMergedRegion(new CellRangeAddress(1,2,14,14));
        cell214.setCellStyle(cellStyleCommon);
        HSSFRow thirdRow = sheet.createRow(2);//第3行
        HSSFCell cell31 = thirdRow.createCell(1);//第3行第2列
        cell31.setCellValue("黄牌");
        cell31.setCellStyle(cellStyleCommon);
        HSSFCell cell32 = thirdRow.createCell(2);//第3行第3列
        cell32.setCellValue("蓝牌");
        cell32.setCellStyle(cellStyleCommon);
        HSSFCell cell33 = thirdRow.createCell(3);//第3行第4列
        cell33.setCellValue("无牌");
        cell33.setCellStyle(cellStyleCommon);
        HSSFCell cell34 = thirdRow.createCell(4);//第3行第5列
        cell34.setCellValue("小计");
        cell34.setCellStyle(cellStyleCommon);
        HSSFCell cell35 = thirdRow.createCell(5);//第3行第6列
        cell35.setCellValue("有牌");
        cell35.setCellStyle(cellStyleCommon);
        HSSFCell cell36 = thirdRow.createCell(6);//第3行第7列
        cell36.setCellValue("无牌");
        cell36.setCellStyle(cellStyleCommon);
        HSSFCell cell37 = thirdRow.createCell(7);//第3行第8列
        cell37.setCellValue("小计");
        cell37.setCellStyle(cellStyleCommon);
        HSSFRow fourRow = sheet.createRow(3);//第4行
        HSSFRow fiveRow = sheet.createRow(4);//第5行
        HSSFRow sixRow = sheet.createRow(5);//第6行
        HSSFRow sevenRow = sheet.createRow(6);//第7行
        HSSFRow eightRow = sheet.createRow(7);//第8行
        HSSFRow nineRow = sheet.createRow(8);//第9行
        HSSFRow tenRow = sheet.createRow(9);//第10行
        if(rArr==null || rArr.size()==0){
            HSSFCell cell40 = fourRow.createCell(0);//第4行第1列
            cell40.setCellValue("合计");
            cell40.setCellStyle(cellStyleCommon);
            for(int i=1;i<13;i++){
                HSSFCell cellCommon = fourRow.createCell(i);
                cellCommon.setCellValue(0);
                cellCommon.setCellStyle(cellStyleCommon);
            }
            HSSFCell cell50 = fiveRow.createCell(0);//第5行第1列
            cell50.setCellValue("总计");
            cell50.setCellStyle(cellStyleCommon);
            HSSFCell cell51 = fiveRow.createCell(1);//第5行第2列
            cell51.setCellValue(0);
            sheet.addMergedRegion(new CellRangeAddress(4,4,1,12));
            cell51.setCellStyle(cellStyleCommon);
        }else if(rArr!=null && rArr.size()==1){
            VehParkLotDto vehParkLotDto = rArr.get(0);
            createEveryEntityCellByToday(fourRow,vehParkLotDto,cellStyleCommon,map);
            createLastRowByToday(sheet,fiveRow,sixRow,vehParkLotDtoSum,5,cellStyleCommon);
        }else if(rArr!=null && rArr.size()==2){
            VehParkLotDto vehParkLotDto = rArr.get(0);
            createEveryEntityCellByToday(fourRow,vehParkLotDto,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto1 = rArr.get(1);
            createEveryEntityCellByToday(fiveRow,vehParkLotDto1,cellStyleCommon,map);
            createLastRowByToday(sheet,sixRow,sevenRow,vehParkLotDtoSum,6,cellStyleCommon);
        }else if(rArr!=null && rArr.size()==3){
            VehParkLotDto vehParkLotDto = rArr.get(0);
            createEveryEntityCellByToday(fourRow,vehParkLotDto,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto1 = rArr.get(1);
            createEveryEntityCellByToday(fiveRow,vehParkLotDto1,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto2 = rArr.get(2);
            createEveryEntityCellByToday(sixRow,vehParkLotDto2,cellStyleCommon,map);
            createLastRowByToday(sheet,sevenRow,eightRow,vehParkLotDtoSum,7,cellStyleCommon);
        }else if(rArr!=null && rArr.size()==4){
            VehParkLotDto vehParkLotDto = rArr.get(0);
            createEveryEntityCellByToday(fourRow,vehParkLotDto,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto1 = rArr.get(1);
            createEveryEntityCellByToday(fiveRow,vehParkLotDto1,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto2 = rArr.get(2);
            createEveryEntityCellByToday(sixRow,vehParkLotDto2,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto3 = rArr.get(3);
            createEveryEntityCellByToday(sevenRow,vehParkLotDto3,cellStyleCommon,map);
            createLastRowByToday(sheet,eightRow,nineRow,vehParkLotDtoSum,8,cellStyleCommon);
        }else if(rArr!=null && rArr.size()==5){
            VehParkLotDto vehParkLotDto = rArr.get(0);
            createEveryEntityCellByToday(fourRow,vehParkLotDto,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto1 = rArr.get(1);
            createEveryEntityCellByToday(fiveRow,vehParkLotDto1,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto2 = rArr.get(2);
            createEveryEntityCellByToday(sixRow,vehParkLotDto2,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto3 = rArr.get(3);
            createEveryEntityCellByToday(sevenRow,vehParkLotDto3,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto4 = rArr.get(4);
            createEveryEntityCellByToday(eightRow,vehParkLotDto4,cellStyleCommon,map);
            createLastRowByToday(sheet,nineRow,tenRow,vehParkLotDtoSum,9,cellStyleCommon);
        }else if(rArr!=null && rArr.size()==6){
            VehParkLotDto vehParkLotDto = rArr.get(0);
            createEveryEntityCellByToday(fourRow,vehParkLotDto,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto1 = rArr.get(1);
            createEveryEntityCellByToday(fiveRow,vehParkLotDto1,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto2 = rArr.get(2);
            createEveryEntityCellByToday(sixRow,vehParkLotDto2,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto3 = rArr.get(3);
            createEveryEntityCellByToday(sevenRow,vehParkLotDto3,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto4 = rArr.get(4);
            createEveryEntityCellByToday(eightRow,vehParkLotDto4,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto5 = rArr.get(5);
            createEveryEntityCellByToday(nineRow,vehParkLotDto5,cellStyleCommon,map);
            HSSFRow comRow = sheet.createRow(10);//第11行
            createLastRowByToday(sheet,tenRow,comRow,vehParkLotDtoSum,10,cellStyleCommon);
        }else if(rArr!=null && rArr.size()>=7){
            VehParkLotDto vehParkLotDto = rArr.get(0);
            createEveryEntityCellByToday(fourRow,vehParkLotDto,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto1 = rArr.get(1);
            createEveryEntityCellByToday(fiveRow,vehParkLotDto1,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto2 = rArr.get(2);
            createEveryEntityCellByToday(sixRow,vehParkLotDto2,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto3 = rArr.get(3);
            createEveryEntityCellByToday(sevenRow,vehParkLotDto3,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto4 = rArr.get(4);
            createEveryEntityCellByToday(eightRow,vehParkLotDto4,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto5 = rArr.get(5);
            createEveryEntityCellByToday(nineRow,vehParkLotDto5,cellStyleCommon,map);
            VehParkLotDto vehParkLotDto6 = rArr.get(6);
            createEveryEntityCellByToday(tenRow,vehParkLotDto6,cellStyleCommon,map);
            for(int j=7;j<rArr.size();j++){
                HSSFRow rowCommon = sheet.createRow(j+4);
                VehParkLotDto vehParkLotDtoCom = rArr.get(j);
                createEveryEntityCellByToday(rowCommon,vehParkLotDtoCom,cellStyleCommon,map);
            }
            HSSFRow row1 = sheet.createRow(rArr.size()+4);
            HSSFRow row2 = sheet.createRow(rArr.size()+5);
            createLastRowByToday(sheet,row1,row2,vehParkLotDtoSum,rArr.size()+5,cellStyleCommon);
        }
        HSSFCell cell413 = fourRow.createCell(13);//第4行第14列
        cell413.setCellValue(vehParkLotDtoSum.getSumddchp()+vehParkLotDtoSum.getSumddcnp()+vehParkLotDtoSum.getSumddcwp());
        cell413.setCellStyle(cellStyleCommon);
        HSSFCell cell414 = fourRow.createCell(14);//第4行第15列
        cell414.setCellValue("电动车");
        cell414.setCellStyle(cellStyleCommon);
        HSSFCell cell513 = fiveRow.createCell(13);//第5行第14列
        cell513.setCellValue(vehParkLotDtoSum.getSummtcyp()+vehParkLotDtoSum.getSummtcwp());
        cell513.setCellStyle(cellStyleCommon);
        HSSFCell cell514 = fiveRow.createCell(14);//第5行第15列
        cell514.setCellValue("摩托车");
        cell514.setCellStyle(cellStyleCommon);
        HSSFCell cell613 = sixRow.createCell(13);//第6行第14列
        cell613.setCellValue(vehParkLotDtoSum.getSumslc());
        cell613.setCellStyle(cellStyleCommon);
        HSSFCell cell614 = sixRow.createCell(14);//第6行第15列
        cell614.setCellValue("三轮车");
        cell614.setCellStyle(cellStyleCommon);
        HSSFCell cell713 = sevenRow.createCell(13);//第7行第14列
        cell713.setCellValue(vehParkLotDtoSum.getSumqc());
        cell713.setCellStyle(cellStyleCommon);
        HSSFCell cell714 = sevenRow.createCell(14);//第7行第15列
        cell714.setCellValue("汽车");
        cell714.setCellStyle(cellStyleCommon);
        HSSFCell cell813 = eightRow.createCell(13);//第8行第14列
        cell813.setCellValue(vehParkLotDtoSum.getSumnyc());
        cell813.setCellStyle(cellStyleCommon);
        HSSFCell cell814 = eightRow.createCell(14);//第8行第15列
        cell814.setCellValue("农用车");
        cell814.setCellStyle(cellStyleCommon);
        HSSFCell cell913 = nineRow.createCell(13);//第9行第14列
        cell913.setCellValue(vehParkLotDtoSum.getSumqt());
        cell913.setCellStyle(cellStyleCommon);
        HSSFCell cell914 = nineRow.createCell(14);//第9行第15列
        cell914.setCellValue("其他");
        cell914.setCellStyle(cellStyleCommon);
        HSSFCell cell1013 = tenRow.createCell(13);//第10行第14列
        cell1013.setCellValue(vehParkLotDtoSum.getSumddchp()+vehParkLotDtoSum.getSumddcnp()+vehParkLotDtoSum.getSumddcwp()+vehParkLotDtoSum.getSummtcwp()+vehParkLotDtoSum.getSummtcyp()+vehParkLotDtoSum.getSumslc()+vehParkLotDtoSum.getSumnyc()+vehParkLotDtoSum.getSumqc()+vehParkLotDtoSum.getSumqt());
        cell1013.setCellStyle(cellStyleCommon);
        HSSFCell cell1014 = tenRow.createCell(14);//第10行第15列
        cell1014.setCellValue("合计");
        cell1014.setCellStyle(cellStyleCommon);
    }

    public void createEveryEntityCellByToday(HSSFRow row,VehParkLotDto vehParkLotDto,HSSFCellStyle cellStyleCommon,Map<String,String> map){
        HSSFCell cell40 = row.createCell(0);//第4行第1列
        cell40.setCellValue(map.get(vehParkLotDto.getSysOrgCode()));
        cell40.setCellStyle(cellStyleCommon);
        HSSFCell cell41 = row.createCell(1);//第4行第2列
        cell41.setCellValue(!StringUtils.isEmpty(vehParkLotDto.getSumddchp())?vehParkLotDto.getSumddchp():0);
        cell41.setCellStyle(cellStyleCommon);
        HSSFCell cell42 = row.createCell(2);//第4行第3列
        cell42.setCellValue(!StringUtils.isEmpty(vehParkLotDto.getSumddcnp())?vehParkLotDto.getSumddcnp():0);
        cell42.setCellStyle(cellStyleCommon);
        HSSFCell cell43 = row.createCell(3);//第4行第4列
        cell43.setCellValue(!StringUtils.isEmpty(vehParkLotDto.getSumddcwp())?vehParkLotDto.getSumddcwp():0);
        cell43.setCellStyle(cellStyleCommon);
        HSSFCell cell44 = row.createCell(4);//第4行第5列
        cell44.setCellValue(vehParkLotDto.getSumddchp()+vehParkLotDto.getSumddcnp()+vehParkLotDto.getSumddcwp());
        cell44.setCellStyle(cellStyleCommon);
        HSSFCell cell45 = row.createCell(5);//第4行第6列
        cell45.setCellValue(!StringUtils.isEmpty(vehParkLotDto.getSummtcyp())?vehParkLotDto.getSummtcyp():0);
        cell45.setCellStyle(cellStyleCommon);
        HSSFCell cell46 = row.createCell(6);//第4行第7列
        cell46.setCellValue(!StringUtils.isEmpty(vehParkLotDto.getSummtcwp())?vehParkLotDto.getSummtcwp():0);
        cell46.setCellStyle(cellStyleCommon);
        HSSFCell cell47 = row.createCell(7);//第4行第8列
        cell47.setCellValue(vehParkLotDto.getSummtcyp()+vehParkLotDto.getSummtcwp());
        cell47.setCellStyle(cellStyleCommon);
        HSSFCell cell48 = row.createCell(8);//第4行第9列
        cell48.setCellValue(!StringUtils.isEmpty(vehParkLotDto.getSumslc())?vehParkLotDto.getSumslc():0);
        cell48.setCellStyle(cellStyleCommon);
        HSSFCell cell49 = row.createCell(9);//第4行第10列
        cell49.setCellValue(!StringUtils.isEmpty(vehParkLotDto.getSumqc())?vehParkLotDto.getSumqc():0);
        cell49.setCellStyle(cellStyleCommon);
        HSSFCell cell410 = row.createCell(10);//第4行第11列
        cell410.setCellValue(!StringUtils.isEmpty(vehParkLotDto.getSumnyc())?vehParkLotDto.getSumnyc():0);
        cell410.setCellStyle(cellStyleCommon);
        HSSFCell cell411 = row.createCell(11);//第4行第12列
        cell411.setCellValue(!StringUtils.isEmpty(vehParkLotDto.getSumqt())?vehParkLotDto.getSumqt():0);
        cell411.setCellStyle(cellStyleCommon);
        HSSFCell cell412 = row.createCell(12);//第4行第13列
        cell412.setCellValue(vehParkLotDto.getSumddchp()+vehParkLotDto.getSumddcnp()+vehParkLotDto.getSumddcwp()+
                vehParkLotDto.getSummtcyp()+vehParkLotDto.getSummtcwp()+vehParkLotDto.getSumslc()+
                vehParkLotDto.getSumqc()+vehParkLotDto.getSumnyc()+vehParkLotDto.getSumqt());
        cell412.setCellStyle(cellStyleCommon);
    }

    public void createLastRowByToday(HSSFSheet sheet,HSSFRow row1,HSSFRow row2,VehParkLotDto vehParkLotDto,int i,HSSFCellStyle cellStyleCommon){
        Integer cddchpAll = !StringUtils.isEmpty(vehParkLotDto.getSumddchp())?vehParkLotDto.getSumddchp():0;
        Integer cddcnpAll = !StringUtils.isEmpty(vehParkLotDto.getSumddcnp())?vehParkLotDto.getSumddcnp():0;
        Integer cddcwpAll = !StringUtils.isEmpty(vehParkLotDto.getSumddcwp())?vehParkLotDto.getSumddcwp():0;
        Integer cmtcypAll = !StringUtils.isEmpty(vehParkLotDto.getSummtcyp())?vehParkLotDto.getSummtcyp():0;
        Integer cmtcwpAll = !StringUtils.isEmpty(vehParkLotDto.getSummtcwp())?vehParkLotDto.getSummtcwp():0;
        Integer cslcAll = !StringUtils.isEmpty(vehParkLotDto.getSumslc())?vehParkLotDto.getSumslc():0;
        Integer cqcAll = !StringUtils.isEmpty(vehParkLotDto.getSumqc())?vehParkLotDto.getSumqc():0;
        Integer cnycAll = !StringUtils.isEmpty(vehParkLotDto.getSumnyc())?vehParkLotDto.getSumnyc():0;
        Integer cqtAll = !StringUtils.isEmpty(vehParkLotDto.getSumqt())?vehParkLotDto.getSumqt():0;
        HSSFCell cell50 = row1.createCell(0);//第5行第1列
        cell50.setCellValue("合计");
        cell50.setCellStyle(cellStyleCommon);
        HSSFCell cell51 = row1.createCell(1);//第5行第2列
        cell51.setCellValue(cddchpAll);
        cell51.setCellStyle(cellStyleCommon);
        HSSFCell cell52 = row1.createCell(2);//第5行第3列
        cell52.setCellValue(cddcnpAll);
        cell52.setCellStyle(cellStyleCommon);
        HSSFCell cell53 = row1.createCell(3);//第5行第4列
        cell53.setCellValue(cddcwpAll);
        cell53.setCellStyle(cellStyleCommon);
        HSSFCell cell54 = row1.createCell(4);//第5行第5列
        cell54.setCellValue(cddchpAll+cddcnpAll+cddcwpAll);
        cell54.setCellStyle(cellStyleCommon);
        HSSFCell cell55 = row1.createCell(5);//第5行第6列
        cell55.setCellValue(cmtcypAll);
        cell55.setCellStyle(cellStyleCommon);
        HSSFCell cell56 = row1.createCell(6);//第5行第7列
        cell56.setCellValue(cmtcwpAll);
        cell56.setCellStyle(cellStyleCommon);
        HSSFCell cell57 = row1.createCell(7);//第5行第8列
        cell57.setCellValue(cmtcypAll+cmtcwpAll);
        cell57.setCellStyle(cellStyleCommon);
        HSSFCell cell58 = row1.createCell(8);//第5行第9列
        cell58.setCellValue(cslcAll);
        cell58.setCellStyle(cellStyleCommon);
        HSSFCell cell59 = row1.createCell(9);//第5行第10列
        cell59.setCellValue(cqcAll);
        cell59.setCellStyle(cellStyleCommon);
        HSSFCell cell510 = row1.createCell(10);//第5行第11列
        cell510.setCellValue(cnycAll);
        cell510.setCellStyle(cellStyleCommon);
        HSSFCell cell511 = row1.createCell(11);//第5行第12列
        cell511.setCellValue(cqtAll);
        cell511.setCellStyle(cellStyleCommon);
        HSSFCell cell512 = row1.createCell(12);//第5行第13列
        cell512.setCellValue(cddchpAll+cddcnpAll+cddcwpAll+cmtcypAll+cmtcwpAll);
        cell512.setCellStyle(cellStyleCommon);
        HSSFCell cell60 = row2.createCell(0);//第6行第1列
        cell60.setCellValue("总计");
        cell60.setCellStyle(cellStyleCommon);
        HSSFCell cell61 = row2.createCell(1);//第6行第2列
        cell61.setCellValue(cddchpAll+cddcnpAll+cddcwpAll+cmtcypAll+cmtcwpAll+cslcAll+cqcAll+cnycAll+cqtAll);
        sheet.addMergedRegion(new CellRangeAddress(i,i,1,12));
        cell61.setCellStyle(cellStyleCommon);
    }

    public void createSheet(HSSFSheet sheet,List<VehParkLotStatisticsDto> rArr,HSSFCellStyle cellStyleTitle,HSSFCellStyle cellStyleCommon,String title,VehParkLotStatisticsDto vehParkLotStatisticsDtoSum){
        Map<String,String> map = new LinkedHashMap<String,String>();
        List<Dept> deptlist = deptService.list(null);
        for (int i = 0; i < deptlist.size(); i++) {
            Dept dept = deptlist.get(i);
            map.put(dept.getDeptcode(), dept.getDeptname());
        }
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(8);
        // 添加表头行
        HSSFRow titileRow = sheet.createRow(0);//第1行
        titileRow.setHeight((short)(40*20));
        HSSFCell headCell = titileRow.createCell(0);//第1行第1列
        headCell.setCellValue(title);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));
        headCell.setCellStyle(cellStyleTitle);
        HSSFRow secondRow = sheet.createRow(1);//第2行
        HSSFCell cell21 = secondRow.createCell(1);//第2行第2列
        cell21.setCellValue("电动车");
        sheet.addMergedRegion(new CellRangeAddress(1,1,1,4));
        cell21.setCellStyle(cellStyleCommon);
        HSSFCell cell25 = secondRow.createCell(5);//第2行第6列
        cell25.setCellValue("摩托车");
        sheet.addMergedRegion(new CellRangeAddress(1,1,5,7));
        cell25.setCellStyle(cellStyleCommon);
        HSSFCell cell28 = secondRow.createCell(8);//第2行第9列
        cell28.setCellValue("三轮车");
        sheet.addMergedRegion(new CellRangeAddress(1,2,8,8));
        cell28.setCellStyle(cellStyleCommon);
        HSSFCell cell29 = secondRow.createCell(9);//第2行第10列
        cell29.setCellValue("汽车");
        sheet.addMergedRegion(new CellRangeAddress(1,2,9,9));
        cell29.setCellStyle(cellStyleCommon);
        HSSFCell cell210 = secondRow.createCell(10);//第2行第11列
        cell210.setCellValue("农用车");
        sheet.addMergedRegion(new CellRangeAddress(1,2,10,10));
        cell210.setCellStyle(cellStyleCommon);
        HSSFCell cell211 = secondRow.createCell(11);//第2行第12列
        cell211.setCellValue("其他");
        sheet.addMergedRegion(new CellRangeAddress(1,2,11,11));
        cell211.setCellStyle(cellStyleCommon);
        HSSFCell cell212 = secondRow.createCell(12);//第2行第13列
        cell212.setCellValue("合计");
        sheet.addMergedRegion(new CellRangeAddress(1,2,12,12));
        cell212.setCellStyle(cellStyleCommon);
        HSSFCell cell213 = secondRow.createCell(13);//第2行第14列
        cell213.setCellValue("进场合计");
        sheet.addMergedRegion(new CellRangeAddress(1,2,13,13));
        cell213.setCellStyle(cellStyleCommon);
        HSSFCell cell214 = secondRow.createCell(14);//第2行第15列
        cell214.setCellValue("分类");
        sheet.addMergedRegion(new CellRangeAddress(1,2,14,14));
        cell214.setCellStyle(cellStyleCommon);
        HSSFRow thirdRow = sheet.createRow(2);//第3行
        HSSFCell cell31 = thirdRow.createCell(1);//第3行第2列
        cell31.setCellValue("黄牌");
        cell31.setCellStyle(cellStyleCommon);
        HSSFCell cell32 = thirdRow.createCell(2);//第3行第3列
        cell32.setCellValue("蓝牌");
        cell32.setCellStyle(cellStyleCommon);
        HSSFCell cell33 = thirdRow.createCell(3);//第3行第4列
        cell33.setCellValue("无牌");
        cell33.setCellStyle(cellStyleCommon);
        HSSFCell cell34 = thirdRow.createCell(4);//第3行第5列
        cell34.setCellValue("小计");
        cell34.setCellStyle(cellStyleCommon);
        HSSFCell cell35 = thirdRow.createCell(5);//第3行第6列
        cell35.setCellValue("有牌");
        cell35.setCellStyle(cellStyleCommon);
        HSSFCell cell36 = thirdRow.createCell(6);//第3行第7列
        cell36.setCellValue("无牌");
        cell36.setCellStyle(cellStyleCommon);
        HSSFCell cell37 = thirdRow.createCell(7);//第3行第8列
        cell37.setCellValue("小计");
        cell37.setCellStyle(cellStyleCommon);
        HSSFRow fourRow = sheet.createRow(3);//第4行
        HSSFRow fiveRow = sheet.createRow(4);//第5行
        HSSFRow sixRow = sheet.createRow(5);//第6行
        HSSFRow sevenRow = sheet.createRow(6);//第7行
        HSSFRow eightRow = sheet.createRow(7);//第8行
        HSSFRow nineRow = sheet.createRow(8);//第9行
        HSSFRow tenRow = sheet.createRow(9);//第10行
        if(rArr==null || rArr.size()==0){
            HSSFCell cell40 = fourRow.createCell(0);//第4行第1列
            cell40.setCellValue("合计");
            cell40.setCellStyle(cellStyleCommon);
            for(int i=1;i<13;i++){
                HSSFCell cellCommon = fourRow.createCell(i);
                cellCommon.setCellValue(0);
                cellCommon.setCellStyle(cellStyleCommon);
            }
            HSSFCell cell50 = fiveRow.createCell(0);//第5行第1列
            cell50.setCellValue("总计");
            cell50.setCellStyle(cellStyleCommon);
            HSSFCell cell51 = fiveRow.createCell(1);//第5行第2列
            cell51.setCellValue(0);
            sheet.addMergedRegion(new CellRangeAddress(4,4,1,12));
            cell51.setCellStyle(cellStyleCommon);
        }else if(rArr!=null && rArr.size()==1){
            VehParkLotStatisticsDto vehParkLotStatisticsDto = rArr.get(0);
            createEveryEntityCell(fourRow,vehParkLotStatisticsDto,cellStyleCommon,map);
            createLastRow(sheet,fiveRow,sixRow,vehParkLotStatisticsDtoSum,5,cellStyleCommon);
        }else if(rArr!=null && rArr.size()==2){
            VehParkLotStatisticsDto vehParkLotStatisticsDto = rArr.get(0);
            createEveryEntityCell(fourRow,vehParkLotStatisticsDto,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto1 = rArr.get(1);
            createEveryEntityCell(fiveRow,vehParkLotStatisticsDto1,cellStyleCommon,map);
            createLastRow(sheet,sixRow,sevenRow,vehParkLotStatisticsDtoSum,6,cellStyleCommon);
        }else if(rArr!=null && rArr.size()==3){
            VehParkLotStatisticsDto vehParkLotStatisticsDto = rArr.get(0);
            createEveryEntityCell(fourRow,vehParkLotStatisticsDto,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto1 = rArr.get(1);
            createEveryEntityCell(fiveRow,vehParkLotStatisticsDto1,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto2 = rArr.get(2);
            createEveryEntityCell(sixRow,vehParkLotStatisticsDto2,cellStyleCommon,map);
            createLastRow(sheet,sevenRow,eightRow,vehParkLotStatisticsDtoSum,7,cellStyleCommon);
        }else if(rArr!=null && rArr.size()==4){
            VehParkLotStatisticsDto vehParkLotStatisticsDto = rArr.get(0);
            createEveryEntityCell(fourRow,vehParkLotStatisticsDto,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto1 = rArr.get(1);
            createEveryEntityCell(fiveRow,vehParkLotStatisticsDto1,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto2 = rArr.get(2);
            createEveryEntityCell(sixRow,vehParkLotStatisticsDto2,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto3 = rArr.get(3);
            createEveryEntityCell(sevenRow,vehParkLotStatisticsDto3,cellStyleCommon,map);
            createLastRow(sheet,eightRow,nineRow,vehParkLotStatisticsDtoSum,8,cellStyleCommon);
        }else if(rArr!=null && rArr.size()==5){
            VehParkLotStatisticsDto vehParkLotStatisticsDto = rArr.get(0);
            createEveryEntityCell(fourRow,vehParkLotStatisticsDto,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto1 = rArr.get(1);
            createEveryEntityCell(fiveRow,vehParkLotStatisticsDto1,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto2 = rArr.get(2);
            createEveryEntityCell(sixRow,vehParkLotStatisticsDto2,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto3 = rArr.get(3);
            createEveryEntityCell(sevenRow,vehParkLotStatisticsDto3,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto4 = rArr.get(4);
            createEveryEntityCell(eightRow,vehParkLotStatisticsDto4,cellStyleCommon,map);
            createLastRow(sheet,nineRow,tenRow,vehParkLotStatisticsDtoSum,9,cellStyleCommon);
        }else if(rArr!=null && rArr.size()==6){
            VehParkLotStatisticsDto vehParkLotStatisticsDto = rArr.get(0);
            createEveryEntityCell(fourRow,vehParkLotStatisticsDto,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto1 = rArr.get(1);
            createEveryEntityCell(fiveRow,vehParkLotStatisticsDto1,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto2 = rArr.get(2);
            createEveryEntityCell(sixRow,vehParkLotStatisticsDto2,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto3 = rArr.get(3);
            createEveryEntityCell(sevenRow,vehParkLotStatisticsDto3,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto4 = rArr.get(4);
            createEveryEntityCell(eightRow,vehParkLotStatisticsDto4,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto5 = rArr.get(5);
            createEveryEntityCell(nineRow,vehParkLotStatisticsDto5,cellStyleCommon,map);
            HSSFRow comRow = sheet.createRow(10);//第11行
            createLastRow(sheet,tenRow,comRow,vehParkLotStatisticsDtoSum,10,cellStyleCommon);
        }else if(rArr!=null && rArr.size()>=7){
            VehParkLotStatisticsDto vehParkLotStatisticsDto = rArr.get(0);
            createEveryEntityCell(fourRow,vehParkLotStatisticsDto,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto1 = rArr.get(1);
            createEveryEntityCell(fiveRow,vehParkLotStatisticsDto1,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto2 = rArr.get(2);
            createEveryEntityCell(sixRow,vehParkLotStatisticsDto2,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto3 = rArr.get(3);
            createEveryEntityCell(sevenRow,vehParkLotStatisticsDto3,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto4 = rArr.get(4);
            createEveryEntityCell(eightRow,vehParkLotStatisticsDto4,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto5 = rArr.get(5);
            createEveryEntityCell(nineRow,vehParkLotStatisticsDto5,cellStyleCommon,map);
            VehParkLotStatisticsDto vehParkLotStatisticsDto6 = rArr.get(6);
            createEveryEntityCell(tenRow,vehParkLotStatisticsDto6,cellStyleCommon,map);
            for(int j=7;j<rArr.size();j++){
                HSSFRow rowCommon = sheet.createRow(j+4);
                VehParkLotStatisticsDto vehParkLotStatisticsDtoCom = rArr.get(j);
                createEveryEntityCell(rowCommon,vehParkLotStatisticsDtoCom,cellStyleCommon,map);
            }
            HSSFRow row1 = sheet.createRow(rArr.size()+4);
            HSSFRow row2 = sheet.createRow(rArr.size()+5);
            createLastRow(sheet,row1,row2,vehParkLotStatisticsDtoSum,rArr.size()+5,cellStyleCommon);
        }
        HSSFCell cell413 = fourRow.createCell(13);//第4行第14列
        cell413.setCellValue(vehParkLotStatisticsDtoSum.getSumddchp()+vehParkLotStatisticsDtoSum.getSumddcnp()+vehParkLotStatisticsDtoSum.getSumddcwp());
        cell413.setCellStyle(cellStyleCommon);
        HSSFCell cell414 = fourRow.createCell(14);//第4行第15列
        cell414.setCellValue("电动车");
        cell414.setCellStyle(cellStyleCommon);
        HSSFCell cell513 = fiveRow.createCell(13);//第5行第14列
        cell513.setCellValue(vehParkLotStatisticsDtoSum.getSummtcyp()+vehParkLotStatisticsDtoSum.getSummtcwp());
        cell513.setCellStyle(cellStyleCommon);
        HSSFCell cell514 = fiveRow.createCell(14);//第5行第15列
        cell514.setCellValue("摩托车");
        cell514.setCellStyle(cellStyleCommon);
        HSSFCell cell613 = sixRow.createCell(13);//第6行第14列
        cell613.setCellValue(vehParkLotStatisticsDtoSum.getSumslc());
        cell613.setCellStyle(cellStyleCommon);
        HSSFCell cell614 = sixRow.createCell(14);//第6行第15列
        cell614.setCellValue("三轮车");
        cell614.setCellStyle(cellStyleCommon);
        HSSFCell cell713 = sevenRow.createCell(13);//第7行第14列
        cell713.setCellValue(vehParkLotStatisticsDtoSum.getSumqc());
        cell713.setCellStyle(cellStyleCommon);
        HSSFCell cell714 = sevenRow.createCell(14);//第7行第15列
        cell714.setCellValue("汽车");
        cell714.setCellStyle(cellStyleCommon);
        HSSFCell cell813 = eightRow.createCell(13);//第8行第14列
        cell813.setCellValue(vehParkLotStatisticsDtoSum.getSumnyc());
        cell813.setCellStyle(cellStyleCommon);
        HSSFCell cell814 = eightRow.createCell(14);//第8行第15列
        cell814.setCellValue("农用车");
        cell814.setCellStyle(cellStyleCommon);
        HSSFCell cell913 = nineRow.createCell(13);//第9行第14列
        cell913.setCellValue(vehParkLotStatisticsDtoSum.getSumqt());
        cell913.setCellStyle(cellStyleCommon);
        HSSFCell cell914 = nineRow.createCell(14);//第9行第15列
        cell914.setCellValue("其他");
        cell914.setCellStyle(cellStyleCommon);
        HSSFCell cell1013 = tenRow.createCell(13);//第10行第14列
        cell1013.setCellValue(vehParkLotStatisticsDtoSum.getSumddchp()+vehParkLotStatisticsDtoSum.getSumddcnp()+vehParkLotStatisticsDtoSum.getSumddcwp()+vehParkLotStatisticsDtoSum.getSummtcwp()+vehParkLotStatisticsDtoSum.getSummtcyp()+vehParkLotStatisticsDtoSum.getSumslc()+vehParkLotStatisticsDtoSum.getSumnyc()+vehParkLotStatisticsDtoSum.getSumqc()+vehParkLotStatisticsDtoSum.getSumqt());
        cell1013.setCellStyle(cellStyleCommon);
        HSSFCell cell1014 = tenRow.createCell(14);//第10行第15列
        cell1014.setCellValue("合计");
        cell1014.setCellStyle(cellStyleCommon);
    }

    public void createEveryEntityCell(HSSFRow row,VehParkLotStatisticsDto vehParkLotStatisticsDto,HSSFCellStyle cellStyleCommon,Map<String, String> map){
        HSSFCell cell40 = row.createCell(0);//第4行第1列
        cell40.setCellValue(map.get(vehParkLotStatisticsDto.getSysOrgCode()));
        cell40.setCellStyle(cellStyleCommon);
        HSSFCell cell41 = row.createCell(1);//第4行第2列
        cell41.setCellValue(!StringUtils.isEmpty(vehParkLotStatisticsDto.getSumddchp())?vehParkLotStatisticsDto.getSumddchp():0);
        cell41.setCellStyle(cellStyleCommon);
        HSSFCell cell42 = row.createCell(2);//第4行第3列
        cell42.setCellValue(!StringUtils.isEmpty(vehParkLotStatisticsDto.getSumddcnp())?vehParkLotStatisticsDto.getSumddcnp():0);
        cell42.setCellStyle(cellStyleCommon);
        HSSFCell cell43 = row.createCell(3);//第4行第4列
        cell43.setCellValue(!StringUtils.isEmpty(vehParkLotStatisticsDto.getSumddcwp())?vehParkLotStatisticsDto.getSumddcwp():0);
        cell43.setCellStyle(cellStyleCommon);
        HSSFCell cell44 = row.createCell(4);//第4行第5列
        cell44.setCellValue(vehParkLotStatisticsDto.getSumddchp()+vehParkLotStatisticsDto.getSumddcnp()+vehParkLotStatisticsDto.getSumddcwp());
        cell44.setCellStyle(cellStyleCommon);
        HSSFCell cell45 = row.createCell(5);//第4行第6列
        cell45.setCellValue(!StringUtils.isEmpty(vehParkLotStatisticsDto.getSummtcyp())?vehParkLotStatisticsDto.getSummtcyp():0);
        cell45.setCellStyle(cellStyleCommon);
        HSSFCell cell46 = row.createCell(6);//第4行第7列
        cell46.setCellValue(!StringUtils.isEmpty(vehParkLotStatisticsDto.getSummtcwp())?vehParkLotStatisticsDto.getSummtcwp():0);
        cell46.setCellStyle(cellStyleCommon);
        HSSFCell cell47 = row.createCell(7);//第4行第8列
        cell47.setCellValue(vehParkLotStatisticsDto.getSummtcyp()+vehParkLotStatisticsDto.getSummtcwp());
        cell47.setCellStyle(cellStyleCommon);
        HSSFCell cell48 = row.createCell(8);//第4行第9列
        cell48.setCellValue(!StringUtils.isEmpty(vehParkLotStatisticsDto.getSumslc())?vehParkLotStatisticsDto.getSumslc():0);
        cell48.setCellStyle(cellStyleCommon);
        HSSFCell cell49 = row.createCell(9);//第4行第10列
        cell49.setCellValue(!StringUtils.isEmpty(vehParkLotStatisticsDto.getSumqc())?vehParkLotStatisticsDto.getSumqc():0);
        cell49.setCellStyle(cellStyleCommon);
        HSSFCell cell410 = row.createCell(10);//第4行第11列
        cell410.setCellValue(!StringUtils.isEmpty(vehParkLotStatisticsDto.getSumnyc())?vehParkLotStatisticsDto.getSumnyc():0);
        cell410.setCellStyle(cellStyleCommon);
        HSSFCell cell411 = row.createCell(11);//第4行第12列
        cell411.setCellValue(!StringUtils.isEmpty(vehParkLotStatisticsDto.getSumqt())?vehParkLotStatisticsDto.getSumqt():0);
        cell411.setCellStyle(cellStyleCommon);
        HSSFCell cell412 = row.createCell(12);//第4行第13列
        cell412.setCellValue(vehParkLotStatisticsDto.getSumddchp()+vehParkLotStatisticsDto.getSumddcnp()+vehParkLotStatisticsDto.getSumddcwp()+
                vehParkLotStatisticsDto.getSummtcyp()+vehParkLotStatisticsDto.getSummtcwp()+vehParkLotStatisticsDto.getSumslc()+
                vehParkLotStatisticsDto.getSumqc()+vehParkLotStatisticsDto.getSumnyc()+vehParkLotStatisticsDto.getSumqt());
        cell412.setCellStyle(cellStyleCommon);
    }

    public void createLastRow(HSSFSheet sheet,HSSFRow row1,HSSFRow row2,VehParkLotStatisticsDto vehParkLotStatisticsDto,int i,HSSFCellStyle cellStyleCommon){
        Integer cddchpAll = !StringUtils.isEmpty(vehParkLotStatisticsDto.getSumddchp())?vehParkLotStatisticsDto.getSumddchp():0;
        Integer cddcnpAll = !StringUtils.isEmpty(vehParkLotStatisticsDto.getSumddcnp())?vehParkLotStatisticsDto.getSumddcnp():0;
        Integer cddcwpAll = !StringUtils.isEmpty(vehParkLotStatisticsDto.getSumddcwp())?vehParkLotStatisticsDto.getSumddcwp():0;
        Integer cmtcypAll = !StringUtils.isEmpty(vehParkLotStatisticsDto.getSummtcyp())?vehParkLotStatisticsDto.getSummtcyp():0;
        Integer cmtcwpAll = !StringUtils.isEmpty(vehParkLotStatisticsDto.getSummtcwp())?vehParkLotStatisticsDto.getSummtcwp():0;
        Integer cslcAll = !StringUtils.isEmpty(vehParkLotStatisticsDto.getSumslc())?vehParkLotStatisticsDto.getSumslc():0;
        Integer cqcAll = !StringUtils.isEmpty(vehParkLotStatisticsDto.getSumqc())?vehParkLotStatisticsDto.getSumqc():0;
        Integer cnycAll = !StringUtils.isEmpty(vehParkLotStatisticsDto.getSumnyc())?vehParkLotStatisticsDto.getSumnyc():0;
        Integer cqtAll = !StringUtils.isEmpty(vehParkLotStatisticsDto.getSumqt())?vehParkLotStatisticsDto.getSumqt():0;
        HSSFCell cell50 = row1.createCell(0);//第5行第1列
        cell50.setCellValue("合计");
        cell50.setCellStyle(cellStyleCommon);
        HSSFCell cell51 = row1.createCell(1);//第5行第2列
        cell51.setCellValue(cddchpAll);
        cell51.setCellStyle(cellStyleCommon);
        HSSFCell cell52 = row1.createCell(2);//第5行第3列
        cell52.setCellValue(cddcnpAll);
        cell52.setCellStyle(cellStyleCommon);
        HSSFCell cell53 = row1.createCell(3);//第5行第4列
        cell53.setCellValue(cddcwpAll);
        cell53.setCellStyle(cellStyleCommon);
        HSSFCell cell54 = row1.createCell(4);//第5行第5列
        cell54.setCellValue(cddchpAll+cddcnpAll+cddcwpAll);
        cell54.setCellStyle(cellStyleCommon);
        HSSFCell cell55 = row1.createCell(5);//第5行第6列
        cell55.setCellValue(cmtcypAll);
        cell55.setCellStyle(cellStyleCommon);
        HSSFCell cell56 = row1.createCell(6);//第5行第7列
        cell56.setCellValue(cmtcwpAll);
        cell56.setCellStyle(cellStyleCommon);
        HSSFCell cell57 = row1.createCell(7);//第5行第8列
        cell57.setCellValue(cmtcypAll+cmtcwpAll);
        cell57.setCellStyle(cellStyleCommon);
        HSSFCell cell58 = row1.createCell(8);//第5行第9列
        cell58.setCellValue(cslcAll);
        cell58.setCellStyle(cellStyleCommon);
        HSSFCell cell59 = row1.createCell(9);//第5行第10列
        cell59.setCellValue(cqcAll);
        cell59.setCellStyle(cellStyleCommon);
        HSSFCell cell510 = row1.createCell(10);//第5行第11列
        cell510.setCellValue(cnycAll);
        cell510.setCellStyle(cellStyleCommon);
        HSSFCell cell511 = row1.createCell(11);//第5行第12列
        cell511.setCellValue(cqtAll);
        cell511.setCellStyle(cellStyleCommon);
        HSSFCell cell512 = row1.createCell(12);//第5行第13列
        cell512.setCellValue(cddchpAll+cddcnpAll+cddcwpAll+cmtcypAll+cmtcwpAll);
        cell512.setCellStyle(cellStyleCommon);
        HSSFCell cell60 = row2.createCell(0);//第6行第1列
        cell60.setCellValue("总计");
        cell60.setCellStyle(cellStyleCommon);
        HSSFCell cell61 = row2.createCell(1);//第6行第2列
        cell61.setCellValue(cddchpAll+cddcnpAll+cddcwpAll+cmtcypAll+cmtcwpAll+cslcAll+cqcAll+cnycAll+cqtAll);
        sheet.addMergedRegion(new CellRangeAddress(i,i,1,12));
        cell61.setCellStyle(cellStyleCommon);
    }
}

