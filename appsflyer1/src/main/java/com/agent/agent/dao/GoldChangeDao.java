package com.agent.agent.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agent.agent.pojo.GoldChange;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseDao;

@Repository
public class GoldChangeDao extends BaseDao{
	private static Logger logger = Logger.getLogger(AgentLoginDao.class);
	String sql = " select id,account_id as passportId,reason,param,gold_delta as goldDelta,gold_left as goldLeft,createTime ";

	public List<GoldChange>  getGoldChangeByPassport(long password,String date){
		
		try {
			StringBuilder sqlBuilder = new StringBuilder(sql);
			sqlBuilder.append(" from texas_log.gold_log_"+date+" where SUBSTRING_INDEX(param,'_',-1)  = "+password +" and (reason = 520 or reason = 521)");
			List<GoldChange> goldChanges = this.db.query(sqlBuilder.toString(), GoldChange.class);
			if(goldChanges != null && goldChanges.size() >0){
				return goldChanges;
			}
		} catch (APIException e) {
			logger.error("查询销售记录失败",e);
		}
		
		return null;
	}

}
