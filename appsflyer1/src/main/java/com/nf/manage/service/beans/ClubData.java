package com.nf.manage.service.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ClubData {
    public String id;
    public int ico;
//    public int club_limit;
    public int level;
    public int club_type;
    public int money;
    public int exp;
    public int totalGongXian;
    public int huoyue;
    public String name;
    public Date create_time;
    private String createTime;
    public String notice;
    public int duanweiLimit;
    public long tanheSponsor;
    public long tanheStartTs;
    public Set<String> additionalIco = new HashSet<>();
    public int memberNum;
    public int memberMax;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIco() {
		return ico;
	}
	public void setIco(int ico) {
		this.ico = ico;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getClub_type() {
		return club_type;
	}
	public void setClub_type(int club_type) {
		this.club_type = club_type;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getTotalGongXian() {
		return totalGongXian;
	}
	public void setTotalGongXian(int totalGongXian) {
		this.totalGongXian = totalGongXian;
	}
	public int getHuoyue() {
		return huoyue;
	}
	public void setHuoyue(int huoyue) {
		this.huoyue = huoyue;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public int getDuanweiLimit() {
		return duanweiLimit;
	}
	public void setDuanweiLimit(int duanweiLimit) {
		this.duanweiLimit = duanweiLimit;
	}
	public long getTanheSponsor() {
		return tanheSponsor;
	}
	public void setTanheSponsor(long tanheSponsor) {
		this.tanheSponsor = tanheSponsor;
	}
	public long getTanheStartTs() {
		return tanheStartTs;
	}
	public void setTanheStartTs(long tanheStartTs) {
		this.tanheStartTs = tanheStartTs;
	}
	public Set<String> getAdditionalIco() {
		return additionalIco;
	}
	public void setAdditionalIco(Set<String> additionalIco) {
		this.additionalIco = additionalIco;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public int getMemberMax() {
		return memberMax;
	}
	public void setMemberMax(int memberMax) {
		this.memberMax = memberMax;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
    
}
