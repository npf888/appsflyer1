package com.ami.texas.log.vo;

import com.ami.common.TimeUtil;

public class SlotPayVO {

	
	private int channelId;
	private int pid;
	private int orderStatus;
	private int charId;
	private String productId;
	private int itemNum;
	private int dollar;
	private int vipPoint;
	private String langDesc;
	private String name;
	private int girlFlag;
	private String countries;
	private long createTime;
	private long updateTime;
	private String createTimeStr;
	private String updateTimeStr;
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getCharId() {
		return charId;
	}
	public void setCharId(int charId) {
		this.charId = charId;
	}
	public String getLangDesc() {
		return langDesc;
	}
	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public int getDollar() {
		return dollar;
	}
	public void setDollar(int dollar) {
		this.dollar = dollar;
	}
	public int getVipPoint() {
		return vipPoint;
	}
	public void setVipPoint(int vipPoint) {
		this.vipPoint = vipPoint;
	}
	public void setTimeStr(){
		this.setCreateTimeStr(TimeUtil.formatLongTOStr(this.createTime));
		this.setUpdateTimeStr(TimeUtil.formatLongTOStr(this.updateTime));
	}
	public String getCountries() {
		return countries;
	}
	public void setCountries(String countries) {
		this.countries = countries;
	}
	public int getGirlFlag() {
		return girlFlag;
	}
	public void setGirlFlag(int girlFlag) {
		this.girlFlag = girlFlag;
	}
	
}
