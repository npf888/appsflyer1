package com.agent.agent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agent.agent.dao.AgentDao;
import com.agent.agent.pojo.BazooAgent;
import com.agent.agent.pojo.HumanInfo;
import com.agent.agent.util.AgentUtils;
import com.ami.api.common.AppConstant;
import com.ami.api.common.Pager;
import com.ami.common.HttpClientUtil;

@Service
public class AgentService {

	
	@Autowired
	private AgentDao agentDao;
	
	/**
	 * 验证用户 
	 * @param username
	 * @param password
	 * @return 成功 或者 失败
	 */
	public List<BazooAgent> getAgentUser(String id){
		return agentDao.getAgentByID(id);
	}

	public void toAddGold(long costPassportId,long givePassportId,long giveGold) {
		agentDao.toAddGold(costPassportId,givePassportId,giveGold);
		
	}
	public void sendRedPackage(long costPassportId,long givePassportId,long number,long redPackageValue) {
		agentDao.sendRedPackage(costPassportId,givePassportId,number,redPackageValue);
		
	}


	public void saveSunAgent(Map<String,Object> bazooAgentMap) {
		agentDao.saveSunAgent(bazooAgentMap);
		
	}

	public BazooAgent getAgentUserBySearchColumn(String searchColumn) {
		HumanInfo humanInfo = agentDao.getAgentUserBySearchColumn(searchColumn);
		if(humanInfo == null){
			return new BazooAgent();
		}
		return AgentUtils.getSunAgentFromHumanInfo(humanInfo);
	}

	public boolean judgeDouble(long passportId) {
		return agentDao.judgeDouble(passportId);
	}

	public List<BazooAgent> getAgentSelfUser(String passportId) {
		return agentDao.getAgentSelfUser(passportId);
	}

	public Pager getAgentAllUser(Pager pager, String searchColumn) {
		return agentDao.getAgentAllUser(pager,searchColumn);
	}

	public void editSunAgent(Map<String, Object> bazooAgentMap,String id) {
		agentDao.editSunAgent(bazooAgentMap,id);
		
	}

	public void changeAgentDisplay(HttpServletRequest req) {
		String url = AppConstant.SERVER_URL_HTTP+"api/changeAgentDisplay";
		String passportId = req.getParameter("passportId");
		String display = req.getParameter("display");
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("passportId", passportId);
		params.put("display", display);
		String result = HttpClientUtil.postUrl(url, params);
		
	}
	public void changeAgentAuthority(HttpServletRequest req) {
		agentDao.changeAgentAuthority(req);
		
	}

	public boolean getAuthority(long passportId) {
		return agentDao.getAuthority(passportId);
		
	}
}
