package com.ami.weixin.course.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.weixin.course.dao.CoreDao;
import com.ami.weixin.course.dao.WatchingDao;
import com.ami.weixin.course.schedule.Task;
import com.ami.weixin.course.util.Constant;

/**
 * 远程访问 服务器
 * 主要是用着微信公众号里
 * @author 牛鹏飞
 *
 */
@Service
public class RemoteWatchingService extends Task{

	
	@Autowired
	private WarningService warningService;
	@Autowired
	private WatchingDao watchingDao;
	@Autowired
	private CoreService coreService;
	@Autowired
	private CoreDao coreDao;
	/**
	 *  土耳其-被 定时器 调用的接口
	 */
	public String offerInfo(){
		String rStr = "{'num':'peopleNum'}";
		String str = watchingDao.watchLog();
		String rs = rStr.replaceAll("peopleNum",str);
		return rs;
	}
	/**
	 * 被用户主动调用的接口
	 * @param req
	 * @return
	 */
	public String distribute(HttpServletRequest req) {
		//先判断要做什么
		String active = req.getParameter("active");
		if(StringUtils.isBlank(active)){
			return Constant.active_not_clear;
			//充值
		}else if(Constant.recharge.equals(active)){
			String passportId = req.getParameter("passportId");
			String gold = req.getParameter("gold");
			return coreService.saveGold(passportId, gold);
			//查看在线人数的表 t_human_info 根据passportId
		}else if(Constant.view_online.equals(active)){
			String passportId = req.getParameter("passportId");
			return coreDao.checkViewByPassportId(passportId);
			//游戏服务器 数据库操作问题，会调用这里 通知用户
		}else if(Constant.exception_notice.equals(active)){
			String access_token = getAccessToken();
			warningService.setAccessToken(access_token);
			String e = req.getParameter("e");
			warningService.warning(e, "");
			return Constant.exception_notice_ok;
		}else{
			return Constant.active_not_clear; 
		}
	}
	@Override
	public void execute() {
		
	}
	
}
