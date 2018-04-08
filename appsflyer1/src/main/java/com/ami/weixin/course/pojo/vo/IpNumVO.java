package com.ami.weixin.course.pojo.vo;
/**
 * 展示服务器的 ip ,在线人数的VO
 * @author JavaServer
 *
 */
public class IpNumVO {

	private String ip;
	private String place;
	private String onLineNum;
	private String newNum;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOnLineNum() {
		return onLineNum;
	}
	public void setOnLineNum(String onLineNum) {
		this.onLineNum = onLineNum;
	}
	public String getNewNum() {
		return newNum;
	}
	public void setNewNum(String newNum) {
		this.newNum = newNum;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	
	
}
