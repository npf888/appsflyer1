package com.ami.texas.activity.pojo;

import java.util.ArrayList;
import java.util.List;

import com.ami.common.ActivityRewardRule;

public class ActivityVO {
	private long id;
	private int activityType;
	private int pageLink;
	private String title;
	private String title_cn;
	private String title_tw;
	private String activityDesc;
	private String activityDesc_cn;
	private String activityDesc_tw;
	private String pic;
	private String pic_cn;
	private String pic_tw;
	private String hall_pic;
	private String hall_pic_cn;
	private String hall_pic_tw;
	private String rulePack;
	private int daily;
	private long startTime;
	private long endTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getActivityType() {
		return activityType;
	}
	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}
	public int getPageLink() {
		return pageLink;
	}
	public void setPageLink(int pageLink) {
		this.pageLink = pageLink;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle_cn() {
		return title_cn;
	}
	public void setTitle_cn(String title_cn) {
		this.title_cn = title_cn;
	}
	public String getTitle_tw() {
		return title_tw;
	}
	public void setTitle_tw(String title_tw) {
		this.title_tw = title_tw;
	}
	public String getActivityDesc() {
		return activityDesc;
	}
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	public String getActivityDesc_cn() {
		return activityDesc_cn;
	}
	public void setActivityDesc_cn(String activityDesc_cn) {
		this.activityDesc_cn = activityDesc_cn;
	}
	public String getActivityDesc_tw() {
		return activityDesc_tw;
	}
	public void setActivityDesc_tw(String activityDesc_tw) {
		this.activityDesc_tw = activityDesc_tw;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getPic_cn() {
		return pic_cn;
	}
	public void setPic_cn(String pic_cn) {
		this.pic_cn = pic_cn;
	}
	public String getPic_tw() {
		return pic_tw;
	}
	public void setPic_tw(String pic_tw) {
		this.pic_tw = pic_tw;
	}
	public String getHall_pic() {
		return hall_pic;
	}
	public void setHall_pic(String hall_pic) {
		this.hall_pic = hall_pic;
	}
	public String getHall_pic_cn() {
		return hall_pic_cn;
	}
	public void setHall_pic_cn(String hall_pic_cn) {
		this.hall_pic_cn = hall_pic_cn;
	}
	public String getHall_pic_tw() {
		return hall_pic_tw;
	}
	public void setHall_pic_tw(String hall_pic_tw) {
		this.hall_pic_tw = hall_pic_tw;
	}
	
	
	public String getRulePack() {
		return rulePack;
	}
	public void setRulePack(String rulePack) {
		this.rulePack = rulePack;
	}
	public int getDaily() {
		return daily;
	}
	public void setDaily(int daily) {
		this.daily = daily;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	
}
