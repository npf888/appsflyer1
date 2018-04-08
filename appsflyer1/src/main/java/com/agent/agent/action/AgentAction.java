package com.agent.agent.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agent.agent.pojo.BazooAgent;
import com.agent.agent.service.AgentService;
import com.agent.agent.util.AgentUtils;
import com.agent.agent.util.MD5Utils;
import com.alibaba.fastjson.JSONObject;
import com.ami.api.common.AppConstant;
import com.ami.api.common.Pager;
import com.ami.api.web.action.BaseAction;
/**
 * 代理商管理后台
 * @author JavaServer
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class AgentAction extends BaseAction{
	private static Logger logger = Logger.getLogger(AgentAction.class);
	@Autowired
	private AgentService agentService;
	
	/**
	 * 全部代理商
	 * @return
	 */
	public String all(){
		String searchColumn = this.get("searchColumn");
		Pager pager = new Pager(this.getReq());
		pager = agentService.getAgentAllUser(pager,searchColumn);
		this.set("list", pager.getItems());
		this.set("searchColumn", searchColumn);
		return "all";
	}
	
	/**
	 * 修改代理商是否显示在排行榜上
	 */
	public void changeAgentDisplay(){
		agentService.changeAgentDisplay(this.getReq());
	}
	
	
	
	/**
	 * 代理商自己
	 * @return
	 */
	public String self(){
		BazooAgent parentBazooAgent = (BazooAgent) this.getReq().getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY);
		List<BazooAgent> bazooAgentList = agentService.getAgentSelfUser(String.valueOf(parentBazooAgent.getPassportId()));
		this.set("list", bazooAgentList);
		return "self";
	}
	
	
	/**
	 * 获取当前代理商的子集 代理商
	 * @return
	 */
	public String list(){
		BazooAgent parentBazooAgent = (BazooAgent) this.getReq().getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY);
		List<BazooAgent> bazooAgentList = agentService.getAgentUser(String.valueOf(parentBazooAgent.getId()));
		this.set("list", bazooAgentList);
		return "list";
	}
	
	/**
	 * 增加子集代理商 页面
	 * @return
	 */
	public String addPage(){
		String searchColumn = (String)this.get("searchColumn");
		this.set("doubles", true);
		if(StringUtils.isNotBlank(searchColumn)){
			//先查出要查询的子代理
			BazooAgent bazooAgent = agentService.getAgentUserBySearchColumn(searchColumn);
			BazooAgent parentBazooAgent = (BazooAgent) this.getReq().getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY);
			bazooAgent.setParentId(parentBazooAgent.getId());
			//再看看这个代理是否被人给添加了
			boolean doubles = agentService.judgeDouble(bazooAgent.getPassportId());
			this.set("agent", bazooAgent);
			this.set("doubles", !doubles);
		}
		this.set("searchColumn", searchColumn);
		return "sunAdd";
	}
	/**
	 * 增加子集代理商  动作
	 * @return
	 */
	public void saveSunAgent(){
		String parentId = this.get("parentId");
		String state = this.get("state");
		logger.info("--------------1:"+parentId);
		if(StringUtils.isNotBlank(parentId) && Long.valueOf(parentId).longValue() > 0  
				&& StringUtils.isNotBlank(state)){
			logger.info("--------------2:"+parentId);
			Map<String,Object> bazooAgentMap = AgentUtils.getSunAgentMap(this.getReq());
			String md5Password = MD5Utils.getMD5((String)bazooAgentMap.get("passwords"));
			bazooAgentMap.put("passwords", md5Password);
			bazooAgentMap.put("sendPackage", 0);
			agentService.saveSunAgent(bazooAgentMap);
		}
		logger.info("--------------3:"+parentId);
		
	}
	
	
	
	
	
	
	/**
	 * 给予金币的页面
	 * @return
	 */
	public String toAddGoldPage(){
		String givePassportId = this.get("givePassportId");
		String giveGold = this.get("giveGold");
		BazooAgent parentBazooAgent = (BazooAgent) this.getReq().getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY);
		this.set("costPassportId", parentBazooAgent.getPassportId());
		this.set("givePassportId", givePassportId);
		this.set("gold", giveGold);
		return "addGold";
	}
	/**
	 * 给予金币的 动作
	 * @return
	 */
	public void toAddGold(){
		
		String givePassportId = this.get("givePassportId");
		String giveGold = this.get("gold");
		if(StringUtils.isNotBlank(givePassportId) && StringUtils.isNotBlank(giveGold)){
			
			BazooAgent parentBazooAgent = (BazooAgent) this.getReq().getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY);
			agentService.toAddGold(parentBazooAgent.getPassportId(),Long.valueOf(givePassportId).longValue(),Long.valueOf(giveGold).longValue());
		}
		
	}
	
	
	
	
	
	
	
	/**
	 * 发送红包的页面
	 * @return
	 */
	public String noticeRedPackage(){
		String givePassportId = this.get("givePassportId");
		String giveGold = this.get("giveGold");
		BazooAgent parentBazooAgent = (BazooAgent) this.getReq().getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY);
		this.set("costPassportId", parentBazooAgent.getPassportId());
		this.set("givePassportId", givePassportId);
		this.set("gold", giveGold);
		return "redPackage";
	}
	/**
	 * 发送红包的 动作
	 * @return
	 * @throws IOException 
	 */
	public void sendRedPackage() throws IOException{
		
		String givePassportId = this.get("givePassportId");
		String number = this.get("number");
		String redPackageValue = this.get("redPackageValue");
		if(StringUtils.isNotBlank(givePassportId) && StringUtils.isNotBlank(number)){
			
			BazooAgent parentBazooAgent = (BazooAgent) this.getReq().getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY);
			//首先判断一下用户是否被上级代理商停止发送红包了
			boolean right = agentService.getAuthority(parentBazooAgent.getPassportId());
			if(!right){//没有权限
				JSONObject jb = new JSONObject();
				jb.put("sendRedPackage", "1");
				this.getRes().getWriter().write(jb.toJSONString());
				return;
			}
			//发送红包
			agentService.sendRedPackage(parentBazooAgent.getPassportId(),
					Long.valueOf(givePassportId).longValue(),
					Long.valueOf(number).longValue(),
					Long.valueOf(redPackageValue).longValue()
					);
		}
		JSONObject jb = new JSONObject();
		jb.put("sendRedPackage", "0");
		this.getRes().getWriter().write(jb.toJSONString());
		
	}
	/**
	 * 改变代理商发送红包的权限
	 * @return
	 */
	public void changeAuthority(){
		agentService.changeAgentAuthority(this.getReq());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 编辑个人信息 页面
	 */
	
	public String editSelfInfo(){
		BazooAgent agent = (BazooAgent) this.getReq().getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY);
		this.set("agent", agent);
		return "selfEdit";
	}
	/**
	 * 编辑个人信息  动作
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public void editSunAgent() throws UnsupportedEncodingException{
		this.getReq().setCharacterEncoding("utf-8");
		String nickname = this.get("nickname");
		String nn = new String(nickname.getBytes("iso-8859-1"),"utf-8");
		logger.info("--------------nickname:"+nn);
		String parentId = this.get("parentId");
		String passportId = this.get("passportId");
		String state = this.get("state");
		String id = this.get("id");
		if(StringUtils.isNotBlank(parentId) && StringUtils.isNotBlank(state) 
				&& StringUtils.isNotBlank(passportId) && StringUtils.isNotBlank(id)){
			Map<String,Object> bazooAgentMap = AgentUtils.getSunAgentMap(this.getReq());
			bazooAgentMap.put("nickname", nn);
			BazooAgent agent = (BazooAgent) this.getReq().getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY);
			String passwords = (String)bazooAgentMap.get("passwords");
			if(StringUtils.isBlank(passwords) ||  passwords.trim().length()>10){
				bazooAgentMap.remove("passwords");
			}else{
				String md5Password = MD5Utils.getMD5(String.valueOf(bazooAgentMap.get("passwords")).trim());
				bazooAgentMap.put("passwords", md5Password);
			}
			agentService.editSunAgent(bazooAgentMap,id);
			
			BazooAgent newAgent = AgentUtils.getSunAgent(this.getReq());
			newAgent.setId(agent.getId());
			newAgent.setNickname(nn);
			this.getReq().getSession().removeAttribute(AppConstant.LOGININFO_AGENT_KEY);
			this.getReq().getSession().setAttribute(AppConstant.LOGININFO_AGENT_KEY, newAgent);
		}
		
	}
}
