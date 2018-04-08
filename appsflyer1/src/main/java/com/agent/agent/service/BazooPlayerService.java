package com.agent.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agent.agent.dao.AgentDao;
import com.agent.agent.dao.BazooPlayerDao;
import com.agent.agent.pojo.HumanInfo;

/**
 * 
 * @author JavaServer
 *
 */
@Service
public class BazooPlayerService {

	@Autowired
	private BazooPlayerDao playerDao;
	@Autowired
	private AgentDao agentDao;
	
	
	public List<HumanInfo> getHumanInfoByPassportId(String passportId){
		return agentDao.getHumanInfoBySearchColumn(passportId);
	}
}
