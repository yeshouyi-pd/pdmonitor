package com.pd.wxbase.wx.controller.ssp;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.pd.server.main.domain.WebchatIllcode;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WebchatIllcodeDto;
import com.pd.server.main.service.WebchatIllcodeService;
import com.pd.server.util.DateTools;
import com.pd.server.util.UuidUtil;
import com.pd.wxbase.wx.conf.BaseWxController;
import com.pd.wxbase.wx.conf.WxRedisConfig;
import com.pd.wxbase.wx.wxutlis.utils.PoiExcleUtil;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
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
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/import/webchatIllcodeImport")
public class WebchatIllcodeImportController extends BaseWxController {

    private static final Logger LOG = LoggerFactory.getLogger(WebchatIllcodeImportController.class);
    public static final String BUSINESS_NAME = "违法代码导入";

    @Resource
    private WebchatIllcodeService webchatIllcodeService;

    /**
     * 导出模板
     */
    @GetMapping("/exportIllcodeTemplate")
    public void exportIllcodeTemplate(HttpServletRequest request, HttpServletResponse response) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 创建一个工作表
            String fileName = "违法代码导入模板.xls";
            HSSFSheet sheet = workbook.createSheet("违法代码导入模板");
            // 自适应列宽度
            sheet.autoSizeColumn(1, true);
            sheet.setDefaultColumnWidth(28);
            // 添加表头行
            HSSFRow titileRow = sheet.createRow(0);
            // 设置单元格格式居中
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            List<String> title = Arrays.asList("违法代码","违法行为", "违法代码类别(1:机动车,2:电动车)", "违法代码状态(1:启用,2:禁用)", "记分", "处罚金额(元)", "处罚依据", "违法条例", "其他处罚", "措施", "备注");
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
    @PostMapping("/importIllcode")
    public void importIllcode(HttpServletRequest request, HttpServletResponse response)throws Exception {
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
            List<String> title = Arrays.asList("违法代码","违法行为", "违法代码类别(1:机动车,2:电动车)", "违法代码状态(1:启用,2:禁用)", "记分", "处罚金额(元)", "处罚依据", "违法条例", "其他处罚", "措施", "备注");
            List<String> fields = Arrays.asList("wfdm","wfxw","lb","zt","jf","fkje","cfyj","wftl","qtcf","cs","bz");
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
                String isNan = "^[0-9]+.?[0-9]*";//判断字符串是否为数字
                String isFF = "^[0-9]*$";//判断正整数+0
                List<String> wfdmList = webchatIllcodeService.findAllWfdm();
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
                        if (key.equals("wfdm")) {
                            if (value == null || value.equals("")) {
                                remarkList.add("违法代码未填写");
                            }else{
                                if(wfdmList.contains(value)){
                                    remarkList.add("违法代码重复");
                                }else{
                                    wfdmList.add(value.toString());
                                }
                            }
                        } else if (key.equals("wfxw")) {
                            if (value == null || value.equals("")) {
                                remarkList.add("违法行为未填写");
                            }
                        } else if (key.equals("lb")) {
                            if (value == null || value.equals("")) {
                                remarkList.add("违法代码类别未填写");
                            } else {
                                String lb = value.toString();
                                if (!("1".equals(lb) || "2".equals(lb))) {
                                    remarkList.add("违法代码类别填写错误(如果是机动车请填1如果是电动车请填2)");
                                }
                            }
                        } else if (key.equals("zt")) {
                            if (value == null || value.equals("")) {
                                remarkList.add("违法代码状态未填写");
                            } else {
                                String lb = value.toString();
                                if (!("1".equals(lb) || "2".equals(lb))) {
                                    remarkList.add("违法代码状态填写错误(如果是启用请填1如果是禁用请填2)");
                                }
                            }
                        } else if (key.equals("jf")) {
                            if (value == null || value.equals("")) {
                                remarkList.add("记分未填写");
                            } else {
                                if(!value.toString().matches(isFF)){
                                    remarkList.add("记分请填写正整数");
                                }
                            }
                        } else if (key.equals("fkje")) {
                            if (value == null || value.equals("")) {
                                remarkList.add("处罚金额未填写");
                            } else {
                                if(!value.toString().matches(isNan)){
                                    remarkList.add("处罚金额请填写数字");
                                }
                            }
                        } else if (key.equals("cfyj")) {
                            if (value == null || value.equals("")) {
                                remarkList.add("处罚依据未填写");
                            }
                        } else if (key.equals("wftl")) {
							if (value == null || value.equals("")) {
								remarkList.add("违法条例未填写");
							}
                        }
                    }
                    if (!remarkList.isEmpty()) {
                        newMap.put("remarks", remarkList.stream()
                                .collect(Collectors.collectingAndThen(Collectors.joining(","), obj -> obj)));
                        errorList.add(newMap);
                    } else {
                        successList.add(newMap);
                        WebchatIllcodeDto webchatIllcodeDto = new WebchatIllcodeDto();
                        webchatIllcodeDto.setWfdm(newMap.get("wfdm").toString());
                        webchatIllcodeDto.setWfxw(newMap.get("wfxw").toString());
                        webchatIllcodeDto.setLb(newMap.get("lb").toString());
                        webchatIllcodeDto.setZt(newMap.get("zt").toString());
                        webchatIllcodeDto.setJf(newMap.get("jf").toString());
                        webchatIllcodeDto.setFkje(Float.parseFloat(newMap.get("fkje").toString()));
                        webchatIllcodeDto.setCfyj(newMap.get("cfyj").toString());
                        webchatIllcodeDto.setWftl(newMap.get("wftl").toString());
                        if(!StringUtils.isEmpty(newMap.get("qtcf"))){
                            webchatIllcodeDto.setQtcf(newMap.get("qtcf").toString());
                        }
                        if(!StringUtils.isEmpty(newMap.get("cs"))){
                            webchatIllcodeDto.setCs(newMap.get("cs").toString());
                        }
                        if(!StringUtils.isEmpty(newMap.get("bz"))){
                            webchatIllcodeDto.setBz(newMap.get("bz").toString());
                        }
                        LoginUserDto loginUserDto   = getRequestHeader();
                        webchatIllcodeDto.setUsercode(loginUserDto.getLoginName());
                        webchatIllcodeDto.setDeptcode(loginUserDto.getDeptcode());
                        webchatIllcodeService.save(webchatIllcodeDto);
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
        HSSFSheet sheet = workbook.createSheet("违法代码导入失败清单");
        // 自适应列宽度
        sheet.autoSizeColumn(1, true);
        sheet.setDefaultColumnWidth(28);
        // 添加表头行
        HSSFRow titileRow = sheet.createRow(0);
        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        List<String> title = Arrays.asList("违法代码","违法行为", "违法代码类别(1:机动车,2:电动车)", "违法代码状态(1:启用,2:禁用)", "记分", "处罚金额(元)", "处罚依据", "违法条例", "其他处罚", "措施", "备注", "错误原因");
        List<String> dataList = Arrays.asList("wfdm","wfxw","lb","zt","jf","fkje","cfyj","wftl","qtcf","cs","bz","remarks");
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
//        String basePath = path+"file"+File.separator+ DateTools.getFormatDate(new Date(),"yyyyMM");
        String fileName = "违法代码导入失败清单";
        String filehz = ".xls";
//        String fullPath = basePath+File.separator+fileName+filehz;
//        File dest = new File(fullPath);
//        if(!dest.exists()){
//            dest.mkdirs();
//        }
        File file=new File(path);
        workbook.write(new FileOutputStream(new File(file, "违法代码导入失败清单.xls")));
        String readpath = "/system/f/"+DateTools.getFormatDate(new Date(),"yyyyMM")+"/"+fileName+filehz;
        return readpath;
    }
}
