package com.pd.monitor.servlet;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.pd.server.config.SpringUtil;
import com.pd.server.main.domain.EventCameraInfo;
import com.pd.server.main.domain.EventCameraInfoExample;
import com.pd.server.main.service.EventCameraInfoService;
import com.pd.server.util.DateTools;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 公开 写入/查询 接口-发布对象
 * @author braveheartwzm
 * */
@WebService
public final class PdRpcOutAccess  {
	private static final Logger LOG = LoggerFactory.getLogger(PdRpcOutAccess.class);

	private EventCameraInfoService eventCameraInfoService = (EventCameraInfoService) SpringUtil.getBean("eventCameraInfoService");

	/**
	 * 视频切片需要切片的信息
	 * 默认升序 kssj 每次返回10条以下
	 * @param UTF8XmlDoc
	 * @return
	 */
	@WebMethod
	public String getDownloadList(String UTF8XmlDoc) {
		// 获取到请求参数
		String ret = "";
		try {
			UTF8XmlDoc = PNXmlTools.xmltoUTF8(UTF8XmlDoc);

		} catch (Exception e) {
			System.out.println("UTF-8转换异常，数据已还原：" + UTF8XmlDoc);
		}

		try {
			Map<String, String> params = PNXmlTools.wsXmlToMap(UTF8XmlDoc);
			String state = params.get("state");//切片状态
			List<EventCameraInfo> getDownloadList = eventCameraInfoService.getDownloadList();
			List<Map<String,String>> datalist = new ArrayList<Map<String,String>>();
			if(!CollectionUtils.isEmpty(getDownloadList)){
				for(EventCameraInfo pojo : getDownloadList){
					if(pojo.getJssj().after(pojo.getKssj())){
						Map<String,String> map = new HashMap<String,String>();
						map.put("sid", pojo.getId());//ID
						map.put("ip", pojo.getDvrip());
						map.put("channal", pojo.getTdh()+"");
						map.put("kssj", DateTools.getFormatDate(pojo.getKssj(), "yyyy-MM-dd HH:mm:ss"));
						map.put("jssj", DateTools.getFormatDate(pojo.getJssj(), "yyyy-MM-dd HH:mm:ss"));
						datalist.add(map);
					}
			   }
			}
			try {
				if(!CollectionUtils.isEmpty(datalist)){
					ret = PNXmlTools.createQueryBody(datalist);
				}else{
					ret =  PNXmlTools.wsExecptionXml("-9999","无相关数据信息！");
				}
			} catch (Exception e1) {
				ret =  PNXmlTools.wsExecptionXml("-9999",e1.getMessage());
			}
		} catch (Exception e) {
			ret =PNXmlTools.wsExecptionXml("-9999",e.getMessage());
		}
		return  ret;
	}

	@WebMethod
	public String setDownloadState(String UTF8XmlDoc)  {
		// 获取到请求参数
		String ret = "";
		// 字符串utf8转换
		try {
			UTF8XmlDoc = PNXmlTools.xmltoUTF8(UTF8XmlDoc);
		} catch (Exception e) {
			System.out.println("UTF-8转换异常，数据已还原：" + UTF8XmlDoc);
		}
		try {
			Map<String, String> params = PNXmlTools.wsXmlToMap(UTF8XmlDoc);
			String id = params.get("sid");//ID
			String dz = params.get("videourl");//播放地址
			String zt = params.get("state");//状态
			String sm = params.get("message");//说明
			if(StringUtils.isNotBlank(id)){
				EventCameraInfo vo = new EventCameraInfo();
				vo.setId(id);
				vo.setDz(dz);
				vo.setZt(zt);
				vo.setSm(sm);
				eventCameraInfoService.update(vo);
				ret  =PNXmlTools.wsWriteRetToXml();
			}else{
				ret =PNXmlTools.wsExecptionXml("-9999","缺少必要参数！");
			}

		} catch (Exception e) {
			ret =PNXmlTools.wsExecptionXml("-9999",e.getMessage());

		}
		return ret;
	}



}
