package com.pd.monitor.wx.wxutlis.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.pd.monitor.wx.wxutlis.utils.AccessToken;
import com.pd.monitor.wx.wxutlis.utils.WeixinUtil;
import org.apache.http.ParseException;




public class WeixinTest {


//Javabean对象转换成String类型的JSON字符串
//JSONObject.toJSONString(Javabean对象)

//String类型的JSON字符串转换成Javabean对象
//	JSONObject.toJavaObject(JSON字符串,Javabean.class)

//Json字符串转换成JSONObject对象
// 	JSONObject.parseObject(JSON字符串)

//JSON字符串转换成Javabean对象
// 	JSONObject.parseObject(JSON字符串,Javabean.class)

	private static final String OpenIDS_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=37_TsFHAwbF6qINeWs8xtH4C9cZRTkH6jW2HXlG1v0alTuiRv-yPJ4E2VdCPMjVhUB_5AxtUPNRWCoz5Vn-QVLACM6XQWOf7D1FT4OJ7iR1u16VCerFR7NrTSAjXA9Bj_qnLgQbj2p1bgH7Fp0OXLMjAJAJXY";
	private static final String THISUSER_URL  ="https://api.weixin.qq.com/cgi-bin/user/info?access_token=37_ja85ODGzmHsFkCoO8HMbMfCbszDk0I5ov8rzkajb2qW0d-Ohl3yiOWYk7L6R12LP7-T830Uz7kXVXR4050vWtUoEIs5YPW5PxIRMbNXZ4rz8XjXxngGowckOcqfmf6dw6cXR0oYkrLASmse6FQDbAHAYJW&openid=OPENID&lang=zh_CN";
	public static void main(String[] args) {
		AccessToken token = null;
		try {
			token = WeixinUtil.getAccessToken();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("============="+token.toString());
		//  System.err.println(token.toString());
		// String   meun  = JSONObject.toJSONString(WeixinUtil.initMenu()).toString();
		// try {
			//int resutl = WeixinUtil.createMenu(token.getToken(), meun);
			//System.out.println(resutl);
	//	} catch (ParseException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}

		 
	
	      
	
		
//		try {
//			Map<String ,String > map  = new HashMap<String, String>();
//			map.put("begin_openid", "");
//			
//			String  outStr = JSONObject.fromObject(map).toString();
//			JSONObject jsonObject = WeixinUtil.doPostStr(OpenIDS_URL,outStr);
//			JSONObject openid = jsonObject.getJSONObject("data");
//		    String openids = openid.getString("openid");
//			openids =openids.replace("[", "").replace("]", "");
//			  
//			System.out.println(openids);
//				
//			
//			
//			System.out.println("-----------end---------");
//			
//			
//		} catch (ParseException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
	}	

}
