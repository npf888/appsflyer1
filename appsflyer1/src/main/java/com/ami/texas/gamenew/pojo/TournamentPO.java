package com.ami.texas.gamenew.pojo;

public class TournamentPO {
	private String accountId;
	private String accountName;
	private long level;
	private int reason;
	
	private long tournamentId;
	private int tournamentType;
	private int slotType;
	private long totalReward;
	private long reward;
	private long rewards;
	private long obtainedReward;
	private String createTime;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public long getLevel() {
		return level;
	}
	public void setLevel(long level) {
		this.level = level;
	}
	public int getReason() {
		return reason;
	}
	public void setReason(int reason) {
		this.reason = reason;
	}
	public long getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(long tournamentId) {
		this.tournamentId = tournamentId;
	}
	public int getTournamentType() {
		return tournamentType;
	}
	public void setTournamentType(int tournamentType) {
		this.tournamentType = tournamentType;
	}
	public int getSlotType() {
		return slotType;
	}
	public void setSlotType(int slotType) {
		this.slotType = slotType;
	}
	public long getTotalReward() {
		return totalReward;
	}
	public void setTotalReward(long totalReward) {
		this.totalReward = totalReward;
	}
	public long getReward() {
		return reward;
	}
	public void setReward(long reward) {
		this.reward = reward;
	}
	public long getRewards() {
		return rewards;
	}
	public void setRewards(long rewards) {
		this.rewards = rewards;
	}
	public long getObtainedReward() {
		return obtainedReward;
	}
	public void setObtainedReward(long obtainedReward) {
		this.obtainedReward = obtainedReward;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
