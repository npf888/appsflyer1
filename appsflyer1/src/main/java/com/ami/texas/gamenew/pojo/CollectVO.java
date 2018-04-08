package com.ami.texas.gamenew.pojo;

import com.alibaba.fastjson.JSONObject;

/*
 * 前台展示
 */
public class CollectVO {
	private long id;
	private String name;
	private long level;
	
	private Integer totalNum;
	private Integer goldNum;
	private Integer silverNum;
	private Integer cuprumNum;
	
	private String debris;
	private String slotspin;
	private String slotpoint;
	private String updateTime;
	private String createTime;
	
	
	
	
	
	
	public String getDebris() {
		return debris;
	}
	public void setDebris(String debris) {
		this.debris = debris;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getLevel() {
		return level;
	}
	public void setLevel(long level) {
		this.level = level;
	}
	
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getGoldNum() {
		return goldNum;
	}
	public void setGoldNum(Integer goldNum) {
		this.goldNum = goldNum;
	}
	public Integer getSilverNum() {
		return silverNum;
	}
	public void setSilverNum(Integer silverNum) {
		this.silverNum = silverNum;
	}
	public Integer getCuprumNum() {
		return cuprumNum;
	}
	public void setCuprumNum(Integer cuprumNum) {
		this.cuprumNum = cuprumNum;
	}
	public String getSlotspin() {
		return slotspin;
	}
	public void setSlotspin(String slotspin) {
		this.slotspin = slotspin;
	}
	public String getSlotpoint() {
		return slotpoint;
	}
	public void setSlotpoint(String slotpoint) {
		this.slotpoint = slotpoint;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	/**
	 * 过滤出 奖券   1 铜 2 银 3 金 
	 * 
	 */
	
	/**
	 * 过滤出 金券
	 * 3 金 
	 */
	public void filterGoldNum(String json){
		JSONObject jobj = JSONObject.parseObject(json);
		if(jobj.containsKey("3")){
			Integer goldNum = (Integer)jobj.get("3");
			this.setGoldNum(goldNum);
		}
	}
	/**
	 * 过滤出 银券
	 * 2 银  
	 */
	public void filterSilverNum(String json){
		JSONObject jobj = JSONObject.parseObject(json);
		if(jobj.containsKey("2")){
			Integer silverNum = (Integer)jobj.get("2");
			this.setSilverNum(silverNum);
		}
	}
	/**
	 * 
	 * 过滤出 铜券
	 * 1 铜
	 *  
	 */
	public void filterCuprumNum(String json){
		JSONObject jobj = JSONObject.parseObject(json);
		if(jobj.containsKey("1")){
			Integer cuprumNum = (Integer)jobj.get("1");
			this.setCuprumNum(cuprumNum);
		}
	}
	public void filterTotalNum(String json) {
		JSONObject jobj = JSONObject.parseObject(json);
		int totalNum=0;
		if(jobj.containsKey("1")){
			Integer cuprumNum = (Integer)jobj.get("1");
			totalNum+=cuprumNum;
		}
		if(jobj.containsKey("2")){
			Integer silverNum = (Integer)jobj.get("2");
			totalNum+=silverNum;
		}
		if(jobj.containsKey("3")){
			Integer goldNum = (Integer)jobj.get("3");
			totalNum+=goldNum;
		}
		
		this.setTotalNum(totalNum);
	}
	
	
}
