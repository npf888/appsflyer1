package com.agent.agent.pojo;

import java.util.Date;

/**
 * 实体类
 * @author JavaServer
 *
 */
public class BazooAgent {

	
	
	private long id;
	private long passportId;
	private String nickname;
	private String username;
	private String passwords;
	private String telephone;
	private String identity;
	private String address;
	private String wx;
	private String qq;
	private long parentId;
	private int state;
	private int sendPackage;//是否可以发送红包 0：是，1：否
	private long gold;
	private Date createTime;
	private int bazooAgentDisplay;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWx() {
		return wx;
	}
	public void setWx(String wx) {
		this.wx = wx;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswords() {
		return passwords;
	}
	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public long getPassportId() {
		return passportId;
	}
	public void setPassportId(long passportId) {
		this.passportId = passportId;
	}
	public long getGold() {
		return gold;
	}
	public void setGold(long gold) {
		this.gold = gold;
	}
	public int getBazooAgentDisplay() {
		return bazooAgentDisplay;
	}
	public void setBazooAgentDisplay(int bazooAgentDisplay) {
		this.bazooAgentDisplay = bazooAgentDisplay;
	}
	public int getSendPackage() {
		return sendPackage;
	}
	public void setSendPackage(int sendPackage) {
		this.sendPackage = sendPackage;
	}
	
	
	
	

}
