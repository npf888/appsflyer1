package com.agent.agent.pojo;

public class GoldChange {

	private long id;
	private long passportId;
	private String reason;
	private String param;
	private long goldDelta;
	private long goldLeft;
	private long createTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPassportId() {
		return passportId;
	}
	public void setPassportId(long passportId) {
		this.passportId = passportId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public long getGoldDelta() {
		return goldDelta;
	}
	public void setGoldDelta(long goldDelta) {
		this.goldDelta = goldDelta;
	}
	public long getGoldLeft() {
		return goldLeft;
	}
	public void setGoldLeft(long goldLeft) {
		this.goldLeft = goldLeft;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	
}
