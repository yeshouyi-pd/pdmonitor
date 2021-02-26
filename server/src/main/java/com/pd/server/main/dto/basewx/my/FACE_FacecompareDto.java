package com.pd.server.main.dto.basewx.my;
/**
 *@Title FACE_Facecompare.java
 *@description TODO	人脸比对
 *@version 1.0
 */
public class FACE_FacecompareDto {

	private String similarity;	//两个人脸的相似度
	private String fail_flag;	//标志失败图片，0-没有失败，1-第一张，2-第二张
	public String getSimilarity() {
		return similarity;
	}
	public void setSimilarity(String similarity) {
		this.similarity = similarity;
	}
	public String getFail_flag() {
		return fail_flag;
	}
	public void setFail_flag(String fail_flag) {
		this.fail_flag = fail_flag;
	}
}
