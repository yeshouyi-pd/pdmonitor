package com.pd.server.main.dto.basewx.my;
/**
 *@Title OCR_IdCardocr.java
 *@description 身份证信息
 *@time 2018-5-15 上午10:44:31
 *@author ldq
 *@version 1.0
 */
public class OCR_IdCardocrDto {

	private String name;     //	证件姓名
	private String sex;     //	性别
	private String nation;     //	民族
	private String birth;     //	出生日期
	private String address;     //	地址
	private String id;     //	身份证号
	private String frontimage;     //	OCR识别的身份证正面base64编码照片
	private String valid_date;     //	证件的有效期
	private String authority;     //	发证机关
	private String backimage;     //	OCR识别的证件身份证反面base64编码照片
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFrontimage() {
		return frontimage;
	}
	public void setFrontimage(String frontimage) {
		this.frontimage = frontimage;
	}
	public String getValid_date() {
		return valid_date;
	}
	public void setValid_date(String valid_date) {
		this.valid_date = valid_date;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getBackimage() {
		return backimage;
	}
	public void setBackimage(String backimage) {
		this.backimage = backimage;
	}
	@Override
	public String toString() {
		return "OCR_IdCardocr [name=" + name + ", sex=" + sex + ", nation="
				+ nation + ", birth=" + birth + ", address=" + address
				+ ", id=" + id + ", frontimage=" + frontimage + ", valid_date="
				+ valid_date + ", authority=" + authority + ", backimage="
				+ backimage + "]";
	}
}
