package com.ami.texas.code.pojo;

public class ConversioncodeExcel {

	private String conversionCode;
	private long gold;
	private String endTime;
	private int codeType;//0：通用型-每人可用一次；1：特殊型-只能给一个人用
	
	public String getConversionCode() {
		return conversionCode;
	}
	public void setConversionCode(String conversionCode) {
		this.conversionCode = conversionCode;
	}
	public long getGold() {
		return gold;
	}
	public void setGold(long gold) {
		this.gold = gold;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getCodeType() {
		return codeType;
	}
	public void setCodeType(int codeType) {
		this.codeType = codeType;
	}
	
	
}
