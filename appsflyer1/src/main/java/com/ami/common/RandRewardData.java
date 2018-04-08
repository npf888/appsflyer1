package com.ami.common;


/**
 * 通用奖励
 * @author wayne
 *
 */
public class RandRewardData {
	/**奖励id*/
	private int rewardId;
	/**奖励数量*/
	private int rewardCount;
	/**只有全服奖励的时候 才能用到这个字段*/
	private int vippoint;
	public int getRewardId() {
		return rewardId;
	}
	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}
	public int getRewardCount() {
		return rewardCount;
	}
	public void setRewardCount(int rewardCount) {
		this.rewardCount = rewardCount;
	}
	public int getVippoint() {
		return vippoint;
	}
	public void setVippoint(int vippoint) {
		this.vippoint = vippoint;
	}
	
}
