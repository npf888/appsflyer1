package com.agent.agent.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.agent.agent.pojo.BazooAgent;
import com.agent.agent.pojo.HumanInfo;

/**
 * agent操作的工具类
 * @author JavaServer
 *
 */
public class AgentUtils {

	
	
	public static BazooAgent getSunAgent(HttpServletRequest req) {
		String nickname = req.getParameter("nickname");
		String username = req.getParameter("username");
		String passwords = req.getParameter("passwords");
		String telephone = req.getParameter("telephone");
		String identity = req.getParameter("identity");
		String address = req.getParameter("address");
		String wx = req.getParameter("wx");
		String qq = req.getParameter("qq");
		String parentId = req.getParameter("parentId");
		String state = req.getParameter("state");
		Long passportId = Long.valueOf(String.valueOf(req.getParameter("passportId")));
		BazooAgent bazooAgent = new BazooAgent();
		bazooAgent.setPassportId(passportId);
		bazooAgent.setNickname(nickname);
		bazooAgent.setUsername(username);
		bazooAgent.setPasswords(passwords);
		bazooAgent.setTelephone(telephone);
		bazooAgent.setIdentity(identity);
		bazooAgent.setAddress(address);
		bazooAgent.setWx(wx);
		bazooAgent.setQq(qq);
		bazooAgent.setParentId(Long.valueOf(parentId));
		bazooAgent.setState(Integer.valueOf(state));
		bazooAgent.setCreateTime(new Date());
		return bazooAgent;
	}
	public static Map<String,Object> getSunAgentMap(HttpServletRequest req) {
		String nickname = req.getParameter("nickname");
		String username = req.getParameter("username");
		String passwords = req.getParameter("passwords");
		String telephone = req.getParameter("telephone");
		String identity = req.getParameter("identity");
		String address = req.getParameter("address");
		String wx = req.getParameter("wx");
		String qq = req.getParameter("qq");
		String parentId = req.getParameter("parentId");
		String state = req.getParameter("state");
		Long passportId = Long.valueOf(String.valueOf(req.getParameter("passportId")));
		Map<String,Object> bazooAgentMap = new HashMap<String,Object>();
		bazooAgentMap.put("passportId",passportId);
		bazooAgentMap.put("nickname",nickname);
		bazooAgentMap.put("username",username);
		bazooAgentMap.put("passwords",passwords);
		bazooAgentMap.put("telephone",telephone);
		bazooAgentMap.put("identity",identity);
		bazooAgentMap.put("address",address);
		bazooAgentMap.put("wx",wx);
		bazooAgentMap.put("qq",qq);
		bazooAgentMap.put("parentId",parentId);
		bazooAgentMap.put("state",state);
		bazooAgentMap.put("createTime",new Date());
		return bazooAgentMap;
	}
	public static BazooAgent getSunAgentFromHumanInfo(HumanInfo humanInfo) {
		BazooAgent bazooAgent = new BazooAgent();
		bazooAgent.setPassportId(humanInfo.getPassportId());
		bazooAgent.setNickname(humanInfo.getName());
		bazooAgent.setUsername(humanInfo.getName());
		bazooAgent.setPasswords("123456");
		bazooAgent.setTelephone("");
		bazooAgent.setIdentity("");
		bazooAgent.setAddress("");
		bazooAgent.setWx("");
		bazooAgent.setQq("");
//		bazooAgent.setParentId(Long.valueOf(parentId));
		bazooAgent.setState(1);
		bazooAgent.setCreateTime(new Date());
		return bazooAgent;
	}
}
