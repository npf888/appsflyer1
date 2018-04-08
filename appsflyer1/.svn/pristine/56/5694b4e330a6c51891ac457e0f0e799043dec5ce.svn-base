package com.ami.bazoo.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.common.Pager;
import com.ami.api.exception.APIException;
import com.ami.api.web.action.BaseAction;
import com.ami.bazoo.pojo.BazooGoldVO;
import com.ami.bazoo.pojo.BazooParam;
import com.ami.bazoo.service.BazooGoldService;
import com.ami.common.TimeUtil;

/**
 * 
 * 吹牛后台 金币变化日志
 * @author JavaServer
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class BazooGoldAction extends BaseAction{

	@Autowired
	private BazooGoldService bazooGoldService;
	
	public String bazooGold() throws APIException
	{
	        
		BazooParam param = new BazooParam();
		
		String accountId = this.get("accountId");
		if(StringUtils.isNotBlank(accountId)){
			param.setAccountId(accountId);
		}
		String date = this.get("date");
		if(StringUtils.isNotBlank(date)){
			param.setDate(date);
		}else{
			param.setDate(TimeUtil.formatYMDHMTime(new Date().getTime()));
		}
		String reason = this.get("reason");
		if(StringUtils.isNotBlank(reason)){
			param.setReason(Integer.valueOf(reason));
		}
		 Pager pager = new Pager(this.getReq());
		 pager = bazooGoldService.bazooGold(pager,param);
	     this.set("PAGER", pager);
	     this.set("params", param);
	     return "goldLog";
	}
	
	//只针对抽水
	public String pumpWaterBazooGold() throws APIException
	{
		
		BazooParam param = new BazooParam();
		
		String accountId = this.get("accountId");
		if(StringUtils.isNotBlank(accountId)){
			param.setAccountId(accountId);
		}
		String roomNumber = this.get("roomNumber");
		if(StringUtils.isNotBlank(roomNumber)){
			param.setRoomNumber(roomNumber);
		}
		String date = this.get("date");
		if(StringUtils.isNotBlank(date)){
			param.setDate(date);
		}else{
			param.setDate(TimeUtil.formatYMDHMTime(new Date().getTime()));
		}
		//流水
		param.setReason(512);
		Pager pager = new Pager(this.getReq());
		pager = bazooGoldService.bazooGold(pager,param);
		List<BazooGoldVO> bazooGoldVOList = pager.getItems();
		if(bazooGoldVOList != null && bazooGoldVOList.size()>0){
			for(BazooGoldVO vo:bazooGoldVOList){
				//无双吹牛 经典模式最终 输的钱,当前房间2_0_5000_1,扣掉流水500
				String param1 = vo.getParam().replace("无双吹牛 经典模式最终 输的钱,当前房间", "");
				String p = param1.split(",")[0];
				vo.setParam(p);
			}
		}
		this.set("PAGER", pager);
		this.set("params", param);
		return "pumpWaterGoldLog";
	}
	 
	 
	 
}
