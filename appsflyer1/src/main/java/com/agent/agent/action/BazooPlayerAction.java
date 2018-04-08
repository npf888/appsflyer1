package com.agent.agent.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.agent.agent.pojo.HumanInfo;
import com.agent.agent.service.BazooPlayerService;
import com.ami.api.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class BazooPlayerAction extends BaseAction{

	@Autowired
	private BazooPlayerService playerService;
	/**
	 * 获取当前代理商的子集 代理商
	 * @return
	 */
	public String list(){
		String passportId = this.get("passportId");
		
		if(StringUtils.isNotBlank(passportId)){
			List<HumanInfo> humanInfoList = playerService.getHumanInfoByPassportId(passportId);
			this.set("list", humanInfoList);
			this.set("passportId", passportId);
		}
		return "list";
	}
}
