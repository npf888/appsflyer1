package com.ami.weixin.course.action;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.web.action.BaseAction;
import com.ami.weixin.course.service.LocationService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
/**
 * 谷歌地图
 * @author JavaServer
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class Simple3Action extends BaseAction{

	private static Logger logger = Logger.getLogger(Simple3Action.class);
	@Autowired
	private LocationService locationService;
	/**
	 * 所有服务器 新增人数 在线人数
	 * @return
	 * @throws IOException 
	 * @throws GeoIp2Exception 
	 */
	public String googlemap() {
		String obj = locationService.getLocation(this.getReq());
		logger.info("--- --- obj:::"+obj);
		this.getReq().setAttribute("obj", obj);
		return "googlemap";
	 }
}
