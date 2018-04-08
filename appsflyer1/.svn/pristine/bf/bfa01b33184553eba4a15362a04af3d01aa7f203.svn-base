package com.agent.agent.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agent.agent.dao.AgentLoginDao;
import com.agent.agent.pojo.BazooAgent;

@Service
public class AgentLoginService {

	
	@Autowired
	private AgentLoginDao agentLoginDao;
	
	/**
	 * 验证用户 
	 * @param username
	 * @param password
	 * @return 成功 或者 失败
	 */
	public BazooAgent getAgentUser(String username,String password){
		if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
			return agentLoginDao.getUserByNameAndPassword(username, password);
		}
		return  null;
	}
}
