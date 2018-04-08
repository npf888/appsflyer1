package com.ami.weixin.course.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseMysqlDao;
import com.ami.common.TimeUtil;

@Component
public class WatchingDao extends BaseMysqlDao{
	private static Logger logger = Logger.getLogger(WatchingDao.class);
	public String watchLog() {
		
		String nn = "";
		Date date = new Date();
		 //每天新增加的数量
	    String newRole = "select count(DISTINCT account_id) as valueData from texas_log.player_login_log_"+TimeUtil.formatYMDHMTime(date.getTime())+" where reason=2";
	    
	    List<HashMap<String, Object>> listnewRole;
		try {
			listnewRole = this.db.query(newRole);
			if(listnewRole!= null){
				for(HashMap<String, Object> map : listnewRole){
					Object num = map.get("valueData");
					nn = String.valueOf(num);
					logger.info("--------------------:::valueData  nn::"+nn);
				}
			}
		}catch (APIException e) {
			e.printStackTrace();
		}
		return nn;
		
	}

}
