package com.pd.monitor.controller;

import com.pd.monitor.utils.PoiExcleUtil;
import com.pd.monitor.wx.conf.BaseWxController;
import com.pd.monitor.wx.conf.WxRedisConfig;
import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.ForecastNumDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.service.ForecastNumService;
import com.pd.server.util.DateTools;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/import")
public class importFileController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(importFileController.class);
    public static final String BUSINESS_NAME = "导入模块";

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private ForecastNumService forecastNumService;

    /**
     * 导出模板
     */
    @GetMapping("/exportForecastNumTemplate")
    public void exportForecastNumTemplate(HttpServletRequest request, HttpServletResponse response) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 创建一个工作表
            String fileName = "出现次数预测导入模板.xls";
            HSSFSheet sheet = workbook.createSheet("出现次数预测导入模板");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(28);
            // 添加表头行
            HSSFRow titileRow = sheet.createRow(0);
            // 设置单元格格式居中
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            List<String> title = Arrays.asList("设备sn","出现时间", "出现次数");
            for (int i = 0; i < title.size(); i++) {
                HSSFCell headCell = titileRow.createCell(i);
                headCell.setCellValue(title.get(i));
                headCell.setCellStyle(cellStyle);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入
     */
    @PostMapping("/importForecastNum")
    public void importForecastNum(HttpServletRequest request, HttpServletResponse response)throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        List<List<Object>> list = null;
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (file.isEmpty()) {
                out.print("请上传文件");
            }
            InputStream inputStream = file.getInputStream();
            List<String> title = Arrays.asList("设备sn","出现时间", "出现次数");
            List<String> fields = Arrays.asList("sbbh","cxsj","cxcs");
            ResponseDto rs = PoiExcleUtil.getBankListByExcel(inputStream, file.getOriginalFilename(), title, fields);
            if (rs.getSuccess()) {
                list = (List<List<Object>>) rs.getContent();
            } else {
                out.print(rs.getContent());
                return;
            }
            List<Map<String, Object>> successList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> errorList = new ArrayList<Map<String, Object>>();
            if (!list.isEmpty()) {
                Map<String,String> sbbhDeptMap = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.SBSNCENTERCODE);
                String isNan = "^[0-9]+.?[0-9]*";//判断字符串是否为数字
                String isDate = "^(\\d{4})-(\\d{2})-(\\d{2})\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";//判断时间
                for (int i = 0; i < list.size(); i++) {
                    List<String> remarkList = new ArrayList<String>();
                    Map<String, Object> map = (Map<String, Object>) list.get(i);
                    Map<String, Object> newMap = new HashMap<String, Object>();
                    Set<String> keyList = map.keySet();
                    for (String key : keyList) {
                        Object value = map.get(key);
                        if (value != null && !value.toString().equals("")) {
                            newMap.put(key, value);
                        }
                        if (key.equals("sbbh")) {
                            if (value == null || value.equals("")) {
                                remarkList.add("设备sn未填写");
                            }else{
                                if(!sbbhDeptMap.keySet().contains(value)){
                                    remarkList.add("设备sn填写错误");
                                }
                            }
                        } else if (key.equals("cxsj")) {
                            if (value == null || value.equals("")) {
                                remarkList.add("出现时间未填写");
                            }else{
                                if(!value.toString().matches(isDate)){
                                    remarkList.add("出现时间填写错误");
                                }
                            }
                        } else if (key.equals("cxcs")) {
                            if (value == null || value.equals("")) {
                                remarkList.add("出现次数未填写");
                            } else {
                                if(!value.toString().matches(isNan)){
                                    remarkList.add("出现次数请填写数字");
                                }
                            }
                        }
                    }
                    if (!remarkList.isEmpty()) {
                        newMap.put("remarks", remarkList.stream()
                                .collect(Collectors.collectingAndThen(Collectors.joining(","), obj -> obj)));
                        errorList.add(newMap);
                    } else {
                        successList.add(newMap);
                        ForecastNumDto forecastNumDto = new ForecastNumDto();
                        forecastNumDto.setSbbh(newMap.get("sbbh").toString());
                        forecastNumDto.setCxcs(newMap.get("cxcs").toString());
                        forecastNumDto.setCxsj(newMap.get("cxsj").toString());
                        forecastNumDto.setCxrq(forecastNumDto.getCxsj().substring(0,10));
                        forecastNumDto.setDepcode(sbbhDeptMap.get(forecastNumDto.getSbbh()));
                        forecastNumService.save(forecastNumDto);
                    }
                }
            }
            String returnStr = "";
            response.setContentType("application/json;charset=utf-8");
            if (errorList.isEmpty()) {
                returnStr = "数据总数：" + list.size() + "条；导入成功:" + list.size() + "条；导入失败:0条";
            } else {
                returnStr = "数据总数：" + list.size() + "条；导入成功:" + successList.size() + "条；导入失败:" + errorList.size()
                        + "条；<a href="+getErrorUrl(errorList)+" target='_blank' style='color:red;'>(下载失败数据)</a>";
            }
            out.print(returnStr);
        } catch (Exception e) {
            e.printStackTrace();
            out.print("解析Excel出错");
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private String getErrorUrl(List<Map<String, Object>> errorList) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("出现次数预测导入失败清单");
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(28);
        // 添加表头行
        HSSFRow titileRow = sheet.createRow(0);
        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        List<String> title = Arrays.asList("设备sn","出现时间", "出现次数", "错误原因");
        List<String> dataList = Arrays.asList("sbbh","cxsj","cxcs","remarks");
        for (int i = 0; i < title.size(); i++) {
            HSSFCell headCell = titileRow.createCell(i);
            headCell.setCellValue(title.get(i));
            headCell.setCellStyle(cellStyle);
        }
        if (errorList != null && errorList.size() > 0) {
            for (int i = 0; i < errorList.size(); i++) {
                HSSFRow contentRow = sheet.createRow(i + 1);
                Map<String, Object> temp = errorList.get(i);
                for (int j = 0; j < dataList.size(); j++) {
                    HSSFCell cell = contentRow.createCell(j);
                    cell.setCellStyle(cellStyle);
                    Object data = temp.get(dataList.get(j));
                    cell.setCellValue(data == null ? "" : data.toString());
                }
            }
        }
        String path = WxRedisConfig.createFileDriectory(WxRedisConfig.getPicStorePath());
        String fileName = "出现次数预测导入失败清单";
        String filehz = ".xls";
        File file=new File(path);
        workbook.write(new FileOutputStream(new File(file, "出现次数预测导入失败清单.xls")));
        String readpath = "/system/f/"+ DateTools.getFormatDate(new Date(),"yyyyMM")+"/"+fileName+filehz;
        return readpath;
    }

}
