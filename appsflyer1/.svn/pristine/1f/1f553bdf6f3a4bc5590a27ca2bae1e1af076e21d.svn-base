package com.ami.weixin.course.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.web.action.BaseAction;
import com.ami.weixin.course.pojo.vo.IpNumVO;
import com.ami.weixin.course.pojo.vo.TOnlineTimeNumberVO;
import com.ami.weixin.course.service.Simple2Service;
/**
 * 在线人数 新增人数 ， 还有图形化界面
 * @author JavaServer
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class Simple2Action extends BaseAction{


	@Autowired
	private Simple2Service simple2Service;
	
	private static Logger logger = Logger.getLogger(Simple2Action.class);
	/**
	 * 所有服务器 新增人数 在线人数
	 * @return
	 */
	public String serverDetail() {
		List<IpNumVO> ipNumVOList = simple2Service.serviceDetail(this.getReq());
		this.getReq().setAttribute("ipNumVOList", ipNumVOList);
		return "serverDetail";
	 }
	/**
	 * 所有服务器  在线人数 -超过最大值后 显示的信息
	 * @return
	 */
	public String serverMaxDetail() {
		List<IpNumVO> ipNumVOList = simple2Service.serviceDetail(this.getReq());
		this.getReq().setAttribute("ipNumVOList", ipNumVOList);
		return "serverMaxDetail";
	}
	
	
	/**
	 * 图形化界面
	 * @return
	 */

	public String viewNumList() {
		//获取所有的地区
		List<HashMap<String,Object>> areas = simple2Service.getAllAreas();
		List<TOnlineTimeNumberVO> timeNums = simple2Service.getTimeNumbers(this.getReq());
		String startTime = this.getReq().getParameter("startTime");
		String endTime = this.getReq().getParameter("endTime");
		String area = "";
		String labels = "";
		String flow = "";
		Date date =new Date();
		if(timeNums != null && timeNums.size()>0){
			area = timeNums.get(0).getArea();
			for(TOnlineTimeNumberVO timeNumber:timeNums){
				flow +=timeNumber.getNumber()+",";
				labels+=timeNumber.getTime()+",";
			}
			labels=labels.substring(0, labels.length()-1);
			flow=flow.substring(0, flow.length()-1);
		}
		String path = this.getReq().getContextPath();
		logger.info("--- info ---:::"+path);
		this.getReq().setAttribute("startTime", startTime);
		this.getReq().setAttribute("endTime", endTime);
		this.getReq().setAttribute("area", area);
		this.getReq().setAttribute("areas", areas);
		this.getReq().setAttribute("path", path);
		this.getReq().setAttribute("area", area);
		this.getReq().setAttribute("labels", labels);
		this.getReq().setAttribute("flow", flow);
		return "newNumList";
	}
}

