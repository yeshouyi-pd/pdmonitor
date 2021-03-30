package com.pd.monitor.wx.wxutlis.po;

import java.util.List;

/**
 * 被动回复用户图文消息
 * @ClassName: NewsMessage
 * @Description: TODO功能描述:(这里用一句话描述这个类的作用)
 * @author lqg
 * @date 2020年9月7日 上午11:34:47
 * @remark 备注:(这里是添加备注的信息...)
 */
public class NewsMessage  extends BaseMessage{
	
	private int ArticleCount;
	
	private List<News> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<News> getArticles() {
		return Articles;
	}

	public void setArticles(List<News> articles) {
		Articles = articles;
	}
	
	
	
}
