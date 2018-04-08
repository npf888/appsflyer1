package com.agent.agent.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.agent.agent.pojo.BazooAgent;
import com.agent.agent.pojo.GoldChange;
import com.agent.agent.service.GoldChangeService;
import com.ami.api.common.AppConstant;
import com.ami.api.web.action.BaseAction;
import com.ami.common.TimeUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class GoldChangeAction extends BaseAction{

	@Autowired
	private GoldChangeService goldChangeService;
	/**
	 * ÏúÊÛ¼ÇÂ¼
	 * @return
	 */
	public String list(){
		String date = this.get("date");
		if(StringUtils.isBlank(date)){
			Date d = new Date();
			date=TimeUtil.formatYMDHMTime(d.getTime());
		}
	
		BazooAgent parentBazooAgent = (BazooAgent) this.getReq().getSession().getAttribute(AppConstant.LOGININFO_AGENT_KEY);
		List<GoldChange> GoldChangeList = goldChangeService.getGoldChangeByPassport(parentBazooAgent.getPassportId(), date);
		this.set("list", GoldChangeList);
		this.set("date", date);
		return "list";
	}
}
