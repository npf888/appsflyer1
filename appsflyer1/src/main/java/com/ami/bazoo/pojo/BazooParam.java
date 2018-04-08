package com.ami.bazoo.pojo;
/**
 * 用于页面 前端回显 的参数
 * @author JavaServer
 *
 */
public class BazooParam {

	private String date;
	private String accountId;
	private String roomNumber;
	private int reason;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public int getReason() {
		return reason;
	}
	public void setReason(int reason) {
		this.reason = reason;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	
}
