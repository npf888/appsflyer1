package com.ami.weixin.course.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.common.TimeUtil;
import com.ami.weixin.course.pojo.resp.TextRespMessage;
import com.ami.weixin.course.pojo.vo.TOnlineTimeNumberVO;
import com.ami.weixin.course.util.Constant;
@Component
public class CoreDao extends BaseMysqlDao{
	private static Logger logger = Logger.getLogger(CoreDao.class);
	public void saveUserInfo(TextRespMessage textRespMessage) {
        String ToUserName = textRespMessage.getToUserName();
        String FromUserName	 = textRespMessage.getFromUserName();
        String CreateTime = TimeUtil.formatYMDHMTime(textRespMessage.getCreateTime());
        String content = textRespMessage.getContent();
        String msgId = textRespMessage.getMsgId();
        String msgtype = textRespMessage.getMsgType();
        
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("toUserName", ToUserName);
        param.put("fromUserName", FromUserName);
        param.put("createTime", CreateTime);
        param.put("msgContent", content);
        param.put("msgId", msgId);
        param.put("msgType", msgtype);
        param.put("active", 1);//是否是活跃的，1：是，0 否， 如果活跃 可以接受系统发送的模板消息，否则不接受,,,默认是不活跃的
        try {
        	//先查询当前用户有没有插入过 自己的openID
        	String sql = "select * from texas.t_weixin_user_info where fromusername = ?";
        	Map<String, Object> result = this.db.load(sql, new Object[]{FromUserName});
        	logger.info("start insert.....");
        	if(result != null && result.size() >0 ){
        		logger.info("the user exist锛歰penID="+FromUserName);
        		return;
        	}
        	logger.info("start insert database.....");
			this.table.insert(" texas.t_weixin_user_info  ", param);
			logger.info("success ::锛歰penID="+FromUserName);
		} catch (APIException e) {
			e.printStackTrace();
		}
		
	}

	public List<HashMap<String,Object>> getAllActiveOpenID() {
		//先查询当前用户有没有插入过 自己的openID
    	String sql = "select fromUserName from texas.t_weixin_user_info where active = 1";
    	Map<String, Object> result;
		try {
			return this.db.query(sql);
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public boolean checkAuthority(String username,String password) throws APIException {
		// 查询条件
        Object ojb[] = new Object[]
        {username, password};
		// DB
        Map<String,Object> loginInfo = this.db.load("select * from sys_admin_texas.pre_common_member where account = ? and pwd = ?", ojb);
        
        if(loginInfo != null){
        	return true;
        }
		return false;
	}

	public String saveGold(int intGold,int passportId) {
		Object params[] = new Object[] {intGold, passportId};
		String sql = " update texas.t_human_info set gold = gold + ?  where passportId = ?";
		try {
			int n = this.db.update(sql, params);
			logger.info(" back n:::"+n+" the sql ::::"+sql);
			if(n > 0){
		    	return Constant.recharge_success;
		    }
		    return Constant.recharge_failed;
		} catch (APIException e) {
			e.printStackTrace();
		}
		 return Constant.recharge_failed;
	}
	

	public String checkViewByPassportId(String passportId) {
		Object params[] = new Object[]
		        {passportId};
		String sql = " select name,level,gold,lastLoginIp,lastLoginTime,lastLogoutTime,totalMinute,onlineStatus from texas.t_human_info  where passportId = ?";
		try {
			Map<String,Object> result = this.db.load(sql, params);
			logger.info(" result keyset::::"+result.keySet()+"result values"+result.values());
			String rResult = "";
			for(Entry<String,Object> single:result.entrySet()){
				String key = single.getKey();
				Object value = single.getValue();
				if("name".equals(key)){
					rResult+="用户名："+value+",";
					
				}else if("level".equals(key)){
					rResult+="等级："+value+",";
					
				}else if("gold".equals(key)){
					rResult+="总金币："+value+",";
					
				}else if("lastloginip".equals(key)){
					rResult+="登录ID："+value+",";
					
				}else if("lastlogintime".equals(key)){
					rResult+="登录时间："+value+",";
					
				}else if("lastlogouttime".equals(key)){
					rResult+="登出时间："+value+",";
					
				}else if("totalminute".equals(key)){
					rResult+="总共登录时间(分钟)："+value+",";
					
				}else if("onlinestatus".equals(key)){
					if(value instanceof Integer){
						Integer v = (Integer)value;
						if(v.intValue() == 1){
							rResult+="是否在线：是"+",";
						}else{
							rResult+="是否在线：否"+",";
						}
					}
				}
				logger.info(" result key::::"+key+"result value"+value);
			}
			logger.info(" back rResult::::"+rResult);	
			return rResult;
		} catch (APIException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	/**
	 * 插入时间-在线人数 的数据，用于图形化展示
	 * @return
	 */
	public void insertTimeNumber(String key,int number){
		
		try {
			Map<String,Object> filed = new HashMap<String,Object>();
			Date now = new Date();
			filed.put("area",key);
			filed.put("time",now);
			filed.put("number", number);
			int n = this.table.insert("texas.t_online_time_number", filed);
			logger.info(" key :::"+key+" time ::::"+now+"---number:::"+number);
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
	
	public List<TOnlineTimeNumberVO> getTimeNumber(String area,String startTime,String endTime){
		try {
			if(StringUtils.isBlank(area)){
				area = "土耳其";
			}
			if(StringUtils.isBlank(startTime)){
				Date now = new Date();
				startTime = TimeUtil.formatDate(now)+" 00:00:00";
			}else{
				startTime = startTime+" 00:00:00";
			}
			if(StringUtils.isBlank(endTime)){
				Date now = new Date();
				endTime = TimeUtil.formatAddDay(now, 1)+" 00:00:00";
			}else{
				endTime = TimeUtil.formatAddDay(TimeUtil.formatStrTODate(endTime), 1)+" 00:00:00";
			}
			Object params[] = new Object[] {area,startTime,endTime};
			String sql = "select id,area,time,number from texas.t_online_time_number where area=? and time>=? and time <?";
			List<TOnlineTimeNumberVO> TOnlineTimeNumberVOs = this.db.query(sql,params,TOnlineTimeNumberVO.class);
			return TOnlineTimeNumberVOs;
		} catch (APIException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<HashMap<String,Object>> getAllAreas() {
		String sql = "select distinct area from texas.t_online_time_number ";
		List<HashMap<String,Object>> areas;
		try {
			areas = this.db.query(sql);
			return areas;
		} catch (APIException e) {
			e.printStackTrace();
		}
		return null;
	}

}
