package com.pd.monitor.servlet;

import org.apache.axis.transport.http.AxisServlet;

/**
 * 
 * 项目名称：springboot-jsp
 * 类名称：WebServlet
 * 类描述： 
 * 创建人：ye 
 * 创建时间：2018年5月29日 上午10:17:00
 * 修改人：ye 
 * 修改时间：2018年5月29日 上午10:17:00
 * 修改备注： 
 * @version
 */

@javax.servlet.annotation.WebServlet(
        urlPatterns =  "/services/*",
        loadOnStartup = 1,
        name = "AxisServlet"
)
public class WebServlet extends AxisServlet {

	/** 
	 * @Title: WebServlet.java 
	 * @Package com.pd.servlet 
	 * @Description: TODO(用一句话描述该文件做什么)
	 * @date 2018年5月29日 上午10:19:13 
	 * @version V1.0 
	 */ 
	private static final long serialVersionUID = 1L;


}
