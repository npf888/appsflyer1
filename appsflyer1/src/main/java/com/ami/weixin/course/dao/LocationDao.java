package com.ami.weixin.course.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.weixin.course.pojo.vo.HumanInfo;
import com.ami.weixin.course.pojo.vo.TUserLocationSimpleVO;

@Component
public class LocationDao extends BaseMysqlDao{
	private static Logger logger = Logger.getLogger(LocationDao.class);

	public boolean saveLocation(HttpServletRequest req) {
		Integer user_id = Integer.valueOf(req.getParameter("user_id"));
		String country = req.getParameter("country");
		String city = req.getParameter("city");
		Double latitude = Double.valueOf(req.getParameter("latitude"));//绾害
		Double longitude = Double.valueOf(req.getParameter("longitude"));//缁忓害
		
		try {
			//查询条件
			Map<String,Object> loadParam = new HashMap<String,Object>();
			loadParam.put("user_id", user_id);
			//更新的值
			Map<String,Object> filed = new HashMap<String,Object>();
			Date now = new Date();
			filed.put("user_id",user_id);
			filed.put("country",country);
			filed.put("city",city);
			filed.put("latitude", latitude);
			filed.put("longitude", longitude);
			filed.put("update_time", now);
			Map<String,Object> rResults = this.table.load("texas.t_user_location", loadParam);
			//如果存在就根据user_id 更新一下
			if(rResults!=null && rResults.size()>0){
				this.table.update("texas.t_user_location", filed, loadParam);
				return true;
			}
			//如果不存在就插入数据
			this.table.insert("texas.t_user_location", filed);
			return true;
		} catch (APIException e) {
			e.printStackTrace();
			logger.info("save or update location failed ... ... ::: message:::"+e.getMessage());
			return false;
		}
	}

	public Object getLocation(HttpServletRequest req) {
		String country = req.getParameter("country");
		String city = req.getParameter("city");
		
		try {
			String sql = "select latitude,longitude from texas.t_user_location where 1=1 ";
			//查询条件
			Object[] loadParam = new Object[]{};
			if(StringUtils.isNotBlank(country)){
				sql += " and country=? ";
				loadParam[0]=country;
			}
			if(StringUtils.isNotBlank(city)){
				sql += " and city=? ";
				loadParam[1]=city;
			}
			//更新的值
			List<TUserLocationSimpleVO> rResults = this.db.query(sql, loadParam, TUserLocationSimpleVO.class);
			//如果存在就根据user_id 更新一下
			if(rResults!=null && rResults.size()>0){
				return rResults;
			}
			//如果不存在就插入数据
			return null;
		} catch (APIException e) {
			e.printStackTrace();
			logger.info("obtain location failed ... ... ::: message:::"+e.getMessage());
			return null;
		}
	}

	public List<HumanInfo> getAllIPs() {
		try{
			String sql = "select name,passportId,lastLoginIp from texas.t_human_info ";
			List<HumanInfo> rResults = this.db.query(sql, HumanInfo.class);
			return  rResults;
		}catch(Exception e){
			
		}
		return null;
	}
}
