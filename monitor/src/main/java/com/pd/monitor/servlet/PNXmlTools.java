package com.pd.monitor.servlet;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 结果值转换xml
 * */
public class PNXmlTools {

	/**
	 * xml头标签
	 * */
	private static final String XML_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

	/**
	 * root
	 * */
	private static final String XML_ROOT = "<root>\n$</root>";

	/**
	 * head
	 * */
	private static final String XML_HEAD = "<head>\n$\n</head>\n";

	/**
	 * body
	 * */
	private static final String XML_BODY = "<body>\n$\n</body>\n";

	/**
	 * 数据列表标签
	 * */
	private static final String XML_VEHLIST = "<vehispara id=\"#\">\n$</vehispara>";

	/**
	 * 数据列表标签
	 * */
	private static final String XML_QUERY = "<QueryCondition>\n$</QueryCondition>";
	/**
	 * 数据列表标签
	 * */
	private static final String XML_LIST = "<list>\n$</list>";
	/**
	 * 数据列表标签
	 * */
	private static final String XML_ENTITY = "<entity>\n$</entity>";
	/**
	 * 数据列表标签
	 * */
	private static final String XML_LIST_COUNTS = "<counts>\n$</counts>";

	/**
	 * code标签
	 * */
	private static final String XML_CODE = "<code>$</code>\n";

	/**
	 * message标签
	 * */
	private static final String XML_MESSAGE = "<message>$</message>\n";

	/**
	 * rownum标签
	 * */
	private static final String XML_ROWNUM = "<rownum>$</rownum>";

	/**
	 * 数据节点标签
	 * */
	private static final String XML_DATA = "\t<#>$</#>\n";

	/**
	 * 查询&写入类的的xml转换成map对象
	 * */
	public static Map<String, String> wsXmlToMap(String xml) throws Exception {
		// 声明返回的map
		Map<String, String> retmap = new HashMap<String, String>();
		if (null == xml || "".equals(xml)) {
			return retmap;
		}
		// 加载XML字符串
		Document document = DocumentHelper.parseText(xml.trim());
		// 获取根节点
		Element root = document.getRootElement();

		// 获取第一级节点
		// 获取第一级节点:QueryCondition，第一级节点只有一个标签
		List<Element> queryConditions = root.elements();
		if (queryConditions.size() > 0) {
			Element qc = queryConditions.get(0);
			List<Element> datas = qc.elements();
			for (Element dt : datas) {
				// 获取名称
				String name = dt.getName().toLowerCase();
				// 获取值
				String value = dt.getTextTrim();
				retmap.put(name, value);
			}
		}
		return retmap;
	}


	/**
	 * 返回结果对象转换xml
	 * @throws UnsupportedEncodingException 
	 * */
	public static String wsWriteRetToXml() throws UnsupportedEncodingException {
		// 添加root
		StringBuffer root = new StringBuffer(XML_VERSION);

		// 添加head
		StringBuffer headdata = new StringBuffer();
		headdata.append(XML_CODE.replace("$", "1"));
		headdata.append(XML_MESSAGE.replace("$", xmltoUTF8e("数据写入成功")));
		headdata.append(XML_ROWNUM.replace("$",
				String.valueOf("1")));
		StringBuffer head = new StringBuffer(XML_HEAD.replace("$",
				headdata.toString()));

		root.append(XML_ROOT.replace("$", head.toString()));

		return root.toString();
	}

	public static String wsSystemErrorXml(String message) {
		// 添加root
		StringBuffer root = new StringBuffer(XML_VERSION);

		// 添加head
		StringBuffer headdata = new StringBuffer();
		headdata.append(XML_CODE.replace("$", "$E"));
		headdata.append(XML_MESSAGE.replace("$", message));
		headdata.append(XML_ROWNUM.replace("$", "0"));
		StringBuffer head = new StringBuffer(XML_HEAD.replace("$",
				headdata.toString()));
		root.append(XML_ROOT.replace("$", head.toString()));

		return root.toString();
	}

	public static String wsExecptionXml(String code, String message) {
		// 添加root
		StringBuffer root = new StringBuffer(XML_VERSION);

		// 添加head
		StringBuffer headdata = new StringBuffer();
		headdata.append(XML_CODE.replace("$", code));
		headdata.append(XML_MESSAGE.replace("$", message));
		headdata.append(XML_ROWNUM.replace("$", "0"));
		StringBuffer head = new StringBuffer(XML_HEAD.replace("$",
				headdata.toString()));
		root.append(XML_ROOT.replace("$", head.toString()));

		return root.toString();
	}

	/**
	 * 返回查询xml
	 * @param mapdata 数据map
	 * */
	public static String createQueryXml(Map<String, String> mapdata) {
		// 添加root
		StringBuffer root = new StringBuffer(XML_VERSION);

		// 添加query条件节点
		StringBuffer bodydata = new StringBuffer();
		StringBuffer buffer = new StringBuffer();
		Iterator<String> keys = mapdata.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			String val = mapdata.get(key);
			// 所有标签的key都转化成小写。
			buffer.append(XML_DATA.replace("#", key.toLowerCase()).replace("$",
					val));
		}
		bodydata.append(XML_VEHLIST.replace("$", buffer.toString()));
		root.append(XML_ROOT.replace("$", bodydata.toString()));

		return root.toString();
	}
	
	/**
	 * 返回查询xml
	 * @param mapdata 数据map
	 * @throws UnsupportedEncodingException 
	 * */
	public static String createQueryXmlWithCount(Map<String, String> mapdata) throws UnsupportedEncodingException {
		// 添加root
		StringBuffer root = new StringBuffer(XML_VERSION);
		
		// 添加query条件节点
		StringBuffer bodydata = new StringBuffer();
		StringBuffer buffer = new StringBuffer();
		String num ="0";
		if(null!=mapdata && mapdata.size()>0) {
			num = "1";
			Iterator<String> keys = mapdata.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				String val = mapdata.get(key);
				// 所有标签的key都转化成小写。
				buffer.append(XML_DATA.replace("#", key.toLowerCase()).replace("$",val));
			}
		}
		// 添加head
		StringBuffer headdata = new StringBuffer();
		headdata.append(XML_CODE.replace("$", "1"));
		headdata.append(XML_MESSAGE.replace("$", xmltoUTF8e("数据查询成功")));
		headdata.append(XML_ROWNUM.replace("$",num));
		bodydata.append(XML_VEHLIST.replace("$", buffer.toString()));
		headdata.append(bodydata);
		root.append(XML_ROOT.replace("$", headdata.toString()));
		
		return root.toString();
	}
	

	/**
	 * 返回查询数据集合对应xml
	 * @param mapdata 数据map
	 * */
	public static String createQueryBody(List<Map<String, String>> listdata) {
		// 添加root
		StringBuffer root = new StringBuffer(XML_VERSION);
		StringBuffer vehlist = new StringBuffer(XML_VEHLIST);
		StringBuffer list = new StringBuffer();

		StringBuffer bodydata = new StringBuffer();
		for(Map<String, String> mapdata:listdata) {
			// 添加query条件节点
			StringBuffer buffer = new StringBuffer();
			Iterator<String> keys = mapdata.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				String val = mapdata.get(key);
				// 所有标签的key都转化成小写。
				buffer.append(XML_DATA.replace("#", key.toLowerCase()).replace("$",
						val));
			}
			bodydata.append(XML_ENTITY.replace("$", buffer.toString()));
		}
		list.append(XML_LIST_COUNTS.replace("$", String.valueOf(listdata.size())));
		list.append(XML_LIST.replace("$", bodydata.toString()));
		root.append(XML_ROOT.replace("$", vehlist.toString().replace("$", list.toString())));
		return root.toString();
	}
	
	/**
	 * 转换UTF-8
	 * */
	public static String xmltoUTF8(String xml)
			throws UnsupportedEncodingException {
		if (null != xml) {
			return URLDecoder.decode(xml, "UTF-8");
		} else {
			return xml;
		}
	}
	
	
	
	public static String xmltoUTF8e(String xml)
			throws UnsupportedEncodingException {
		if (null != xml) {
			return URLEncoder.encode(xml, "UTF-8");
		} else {
			return xml;
		}
	}
	
	
	public static void main(String[] args) {
		List<Map<String,String>> datalist = new ArrayList<Map<String,String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("jylsh1", "1000002015010700017");
		map1.put("bz1", "dfafdaf");
		datalist.add(map1);
		Map<String, String> map = new HashMap<String, String>();
		try {
			System.out.println(createQueryXmlWithCount(map));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("jylsh", "1000002015010700017");
		map.put("bz", "dfafdaf");
		datalist.add(map);
		System.out.println("--------");
//		System.out.println(createQueryBody(new ArrayList<Map<String,String>>()));
	}

}
