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
import com.ami.bazoo.pojo.BazooParam;
import com.ami.bazoo.pojo.BazooPersonalEveryDayVO;
import com.ami.bazoo.service.BazooPersonalService;
import com.ami.common.TimeUtil;

/**
 * 
 * 吹牛后台 用户个人 某些信息
 * @author JavaServer
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class BazooPersonalAction extends BaseAction{

	@Autowired
	private BazooPersonalService bazooPersonalService;
	/**
	 * 总的个人信息
	 * @return
	 * @throws APIException
	 */
	public String bazooPersonal() throws APIException
	{
	        
		BazooParam param = new BazooParam();
		String accountId = this.get("accountId");
		if(StringUtils.isNotBlank(accountId)){
			param.setAccountId(accountId);
		}
		 Pager pager = new Pager(this.getReq());
		 pager = bazooPersonalService.bazooPersonal(pager,param);
	     this.set("PAGER", pager);
	     this.set("params", param);
	     return "list";
	}
	
	/**
	 * 每天的个人信息
	 * @return
	 * @throws APIException
	 */
	public String bazooPersonalEveryDay() throws APIException
	{
		
		BazooParam param = new BazooParam();
		String accountId = this.get("accountId");
		String isRobot = this.get("isRobot");
		if(StringUtils.isNotBlank(accountId)){
			param.setAccountId(accountId);
		}
		String date = this.get("date");
		if(StringUtils.isNotBlank(date)){
			param.setDate(date);
		}else{
			String nowString = TimeUtil.formatYMDHMTime(new Date().getTime());
			param.setDate(nowString);
		}
		if(StringUtils.isNotBlank(isRobot)){
			param.setReason(Integer.valueOf(isRobot));
		}else{
			param.setReason(2);
		}
		List<BazooPersonalEveryDayVO> everyDayVOList = bazooPersonalService.bazooPersonalEveryDay(param);
		this.set("everyDayVOList", everyDayVOList);
		this.set("params", param);
		return "listEveryDay";
	}
	
	 
	 
	 
}
