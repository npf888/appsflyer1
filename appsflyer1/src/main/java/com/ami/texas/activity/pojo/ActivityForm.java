package com.ami.texas.activity.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ami.common.ActivityRewardRule;

public class ActivityForm {
	
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
	private List<ActivityRewardRule> rulePack = new ArrayList<ActivityRewardRule>();
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
	public List<ActivityRewardRule> getRulePack() {
		return rulePack;
	}
	public void setRulePack(List<ActivityRewardRule> rulePack) {
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
	
	public Map<String,Object> toFiled(ActivityForm form){
		Map<String,Object> field = new HashMap<String,Object>();
		field.put("id",form.getId());
		field.put("activityType",form.getActivityType());
		field.put("pageLink",form.getPageLink());
		field.put("title",form.getTitle());
		field.put("title_cn",form.getTitle_cn());
		field.put("title_tw",form.getTitle_tw());
		field.put("activityDesc",form.getActivityDesc());
		field.put("activityDesc_cn",form.getActivityDesc_cn());
		field.put("activityDesc_tw",form.getActivityDesc_tw());
		field.put("pic",form.getPic());
		field.put("pic_cn",form.getPic_cn());
		field.put("pic_tw",form.getPic_tw());
		field.put("hall_pic",form.getHall_pic());
		field.put("hall_pic_cn",form.getHall_pic_cn());
		field.put("hall_pic_tw",form.getHall_pic_tw());
		field.put("rulePack",JSON.toJSONString(form.getRulePack()));
		field.put("daily",form.getDaily());
		field.put("startTime",form.getStartTime());
		field.put("endTime",form.getEndTime());
		field.put("updateTime",new Date().getTime());
		field.put("createTime",new Date().getTime());
		
		return field;
	}
	
}
