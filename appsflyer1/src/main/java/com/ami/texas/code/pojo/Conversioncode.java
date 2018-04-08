package com.ami.texas.code.pojo;

public class Conversioncode {

	private long id;
	private String conversionCode;
	private long gold;
	private String endTime;
	private int isdelete;
	private String updateTime;
	private String createTime;
	private int codeType;//0：通用型-每人可用一次；1：特殊型-只能给一个人用
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getCodeType() {
		return codeType;
	}
	public void setCodeType(int codeType) {
		this.codeType = codeType;
	}
	
	
}
