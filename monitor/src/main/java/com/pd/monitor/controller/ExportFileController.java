package com.pd.monitor.controller;

import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.server.main.domain.EquipmentFileExample;
import com.pd.server.main.dto.basewx.my.AlarmNumbersDto;
import com.pd.server.main.service.EquipmentFileService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * 江豚报警按分钟统计导出
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
        String fileName = "江豚报警统计(" + new Date().getTime() + ").xls";
        HSSFSheet sheet = workbook.createSheet("报警时间");
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(18);
        sheet.setDefaultRowHeight((short)(40*10));
        // 添加表头行
        HSSFRow titileRow = sheet.createRow(0);//第1行
        HSSFCell headCell = titileRow.createCell(0);//第1行第1列
        headCell.setCellValue("报警时间");
        headCell.setCellStyle(cellStyleCommon);
        HSSFCell headCell1 = titileRow.createCell(1);//第1行第2列
        headCell1.setCellValue("报警次数");
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
