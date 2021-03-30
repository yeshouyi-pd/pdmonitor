package com.pd.monitor.wx.wxutlis.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.pd.server.main.dto.ResponseDto;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiExcleUtil {
	private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 日期格式yyyy-mm-dd
	private static DecimalFormat df = new DecimalFormat("#.#########"); // 数字格式，防止长数字成为科学计数法形式，或者int变为double形式

	public static String getCellFormatValue(Cell cell) {
		String val = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					val = fmt.format(cell.getDateCellValue()); // 日期型
				} else {
//						System.out.println(cell.getNumericCellValue());
					String cellValue = String.valueOf(cell.getNumericCellValue());
//						System.out.println("cellValue"+cellValue);
					if (cellValue.indexOf("E") > -1) {// 针对科学计数法
						val = df.format(cell.getNumericCellValue());
					} else if ((cellValue.substring(cellValue.indexOf("."), cellValue.length() - 1)).length() == 1) {// 针对整数

						val = df.format(cell.getNumericCellValue());
					} else {// 针对浮点数
						val = cellValue;
					}
//						System.out.println(val);
				}
				break;
			case XSSFCell.CELL_TYPE_STRING: // 文本类型
				val = cell.getStringCellValue();
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN: // 布尔型
				val = String.valueOf(cell.getBooleanCellValue());
				break;
			case XSSFCell.CELL_TYPE_BLANK: // 空白
				val = cell.getStringCellValue();
				break;
			case XSSFCell.CELL_TYPE_ERROR: // 错误
				val = "错误";
				break;
			case XSSFCell.CELL_TYPE_FORMULA: // 公式
				try {
					val = String.valueOf(cell.getStringCellValue());
				} catch (IllegalStateException e) {
					val = String.valueOf(cell.getNumericCellValue());
				}
				break;
			default:
				val = cell.getRichStringCellValue() == null ? null : cell.getRichStringCellValue().toString();
			}
		} else {
			val = "";
		}
		return val;
	}

	public static ResponseDto getBankListByExcel(InputStream in, String fileName, List<String> title,List<String> fields) {
		ResponseDto responseDto = new ResponseDto();
		try {
			List list = new ArrayList<>();
			Workbook workbook = null;
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			if (".xls".equals(fileType)) {
				workbook = new HSSFWorkbook(in);
			} else if (".xlsx".equals(fileType)) {
				workbook = new XSSFWorkbook(in);
			} else {
				responseDto.setSuccess(false);
				responseDto.setContent("请上传excel文件！");
				return responseDto;
			}
			Sheet sheet = null;
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				sheet = workbook.getSheetAt(i);
				if (sheet == null) {
					continue;
				}
				int rowNum = sheet.getPhysicalNumberOfRows();
				if (rowNum > 0) {
					Row titleTopRow = sheet.getRow(0);
					int colNum = titleTopRow.getLastCellNum();
					if (colNum == title.size()) {
						for (int j = 0; j < title.size(); j++) {
							if (!title.get(j).equals(titleTopRow.getCell(j).getStringCellValue())) {
								if (i == 0) {
									responseDto.setSuccess(false);
									responseDto.setContent("文件模板错误，请用最新模板导入数据");
									return responseDto;
								}
								continue;
							}
							;
						}
						for (int row = 1; row < rowNum; row++) {
							Row curRow = sheet.getRow(row);
							Map<String, Object> objects = new HashMap<String, Object>();
							for (int index = 0; index < colNum; index++) {
								String key = fields.get(index);
								Object value = "";
								if (Optional.ofNullable(curRow.getCell(index)).isPresent()) {
									value = PoiExcleUtil.getCellFormatValue(curRow.getCell(index)).trim();
								}
								objects.put(key, value);
							}
							list.add(objects);
						}
					} else {
						if (i == 0) {
							responseDto.setSuccess(false);
							responseDto.setContent("文件模板错误，请用最新模板导入数据");
							return responseDto;
						}
					}
				}
			}
			responseDto.setSuccess(true);
			responseDto.setContent(list);
			return responseDto;
		} catch (IOException e) {
			e.printStackTrace();
			responseDto.setSuccess(false);
			responseDto.setContent("文件解析错误");
			return responseDto;
		}
	}
}
