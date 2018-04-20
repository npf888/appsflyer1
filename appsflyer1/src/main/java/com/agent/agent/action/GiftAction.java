package com.agent.agent.action;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.agent.agent.service.GiftService;
import com.ami.api.common.Pager;
import com.ami.api.web.action.BaseAction;
import com.ami.mail.pojo.MailInfo;

@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class GiftAction extends BaseAction {
	private static Logger logger = Logger.getLogger(GiftAction.class);
	
	
	@Autowired
	private GiftService giftService;
	
	/**
	 * 当天所有发送的礼物
	 * @return
	 */
	public String curDayAllSendGift(){
		Pager pager = new Pager(this.getReq());
		if(StringUtils.isBlank(this.get("sendId"))){
			pager.setItems(new ArrayList<MailInfo>());
			this.set("PAGER", pager);
			return "all";
		}
		Long sendId = Long.valueOf(this.get("sendId"));
		pager = giftService.allSendGift(pager,sendId);
		this.set("PAGER", pager);
		this.set("sor", "send");
		this.set("passportId", sendId);
		return "all";
	}
	/**
	 * 当天所有接收到的礼物
	 * @return
	 */
	public String curDayAllReceiveGift(){
		Pager pager = new Pager(this.getReq());
		if(StringUtils.isBlank(this.get("recId"))){
			pager.setItems(new ArrayList<MailInfo>());
			this.set("PAGER", pager);
			return "all";
		}
		Long recId = Long.valueOf(this.get("recId"));
		pager = giftService.allReceiveGift(pager,recId);
		this.set("PAGER", pager);
		this.set("sor", "receive");
		this.set("passportId", recId);
		return "all";
	}
}
