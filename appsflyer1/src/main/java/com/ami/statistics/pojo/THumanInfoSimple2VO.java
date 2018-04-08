package com.ami.statistics.pojo;
/**
 * 统计
 * 
 * 玩家每日在线时长分布
 * 30分钟以下
 * 30-60分钟
 * 60分钟-120分钟
 * 120分钟以上	
 * @author JavaServer
 *
 */
public class THumanInfoSimple2VO {

	private String totalMinute;
	private int num;
	public String getTotalMinute() {
		return totalMinute;
	}
	public void setTotalMinute(String totalMinute) {
		this.totalMinute = totalMinute;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
