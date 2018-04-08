package com.ami.weixin.course.action;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.web.action.BaseAction;
import com.ami.weixin.course.service.LocationService;

/**
 * 1、获取用户位置信息的入口
 * 访问地址 ：http://localhost:92/simplet1.do?user_id=100134&country=土耳其&city=伊斯坦布尔&longitude=121.301319&latitude=30.985262
 * @author JavaServer
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class Simple1Action extends BaseAction{
	private static Logger logger = Logger.getLogger(Simple1Action.class);
	
	@Autowired
	private LocationService locationService;
	/**
	  * 游戏客户端远程调用此接口，获取用户位置信息的入口
	 * @return 
	  * @throws IOException
	  */
	@Override
	public String execute(){
		boolean result = locationService.saveLocation(this.getReq());
		if(result){
			logger.info("save location successs... ...");
		}else{
			logger.info("save location fail... ...");
		}
		return null;
	}
}
