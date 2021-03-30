package com.pd.monitor.wx.wxutlis.po;

/**
 * 图文消息
 * @ClassName: TextMessage
 * @Description: TODO功能描述:(这里用一句话描述这个类的作用)
 * @author lqg
 * @date 2020年9月7日 上午11:22:54
 * @remark 备注:(这里是添加备注的信息...)
 */
public class TextMessage  extends BaseMessage{
	/*
	 * 文本消息内容
	 */
	private String Content;
	/*
	 * 消息id，64位整型
	 */
	private String MsgId;
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
	
	

}
