package com.agent.agent.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agent.agent.pojo.BazooAgent;
import com.agent.agent.util.MD5Utils;
import com.ami.api.exception.APIException;
import com.ami.api.web.dao.BaseDao;

@Repository
public class AgentLoginDao extends BaseDao{
	private static Logger logger = Logger.getLogger(AgentLoginDao.class);
	String sql = " select id,passportId,nickname,username,passwords,telephone,identity,address,wx,qq,parentId,state,createTime ";

	public BazooAgent  getUserByNameAndPassword(String username,String password){
		
		try {
			StringBuilder sqlBuilder = new StringBuilder(sql);
			String md5Password = MD5Utils.getMD5(password);
			sqlBuilder.append(" from sys_admin_texas.bazoo_agent where username = '"+username+"' and passwords='"+md5Password+"'");
			List<BazooAgent> bazooAgents = this.db.query(sqlBuilder.toString(), BazooAgent.class);
			if(bazooAgents != null && bazooAgents.size() >0){
				return bazooAgents.get(0);
			}
		} catch (APIException e) {
			logger.error("代理商用户登录查询失败",e);
		}
		
		return null;
	}
	
	
	 
}
