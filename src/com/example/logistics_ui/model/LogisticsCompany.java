package com.example.logistics_ui.model;

/**
 * 
 * 物流公司信息模型
 * @author zhenggangji
 *
 */
public class LogisticsCompany {
	
	private String name;
	
	private String pinyin;
	
	private String phoneNo;
	
	public LogisticsCompany(String name,String pinyin,String phoneNo){
		super();
		this.name=name;
		this.pinyin=pinyin;
		this.phoneNo=phoneNo;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
