package com.ami.weixin.course.schedule;

import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ami.common.HttpClientUtil;
import com.ami.weixin.course.service.WarningService;
import com.ami.weixin.course.service.WatchingService;

public abstract class Task {
	private  static Logger logger = Logger.getLogger(Task.class);
	private String IP = "47.88.241.10";
	
	protected String AppID = "wx8f98ce73361b03fc";
	protected String AppSecret = "4a044704faa08f5910488fa4c2705cd8";
	
	private int times = 0;
	
	
	
	
	@Autowired
	protected WarningService warningService;
	
	@Autowired
	protected WatchingService watchingService;
	
	public abstract void execute();
	
	protected String getAccessToken(){
		ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		Object accessTokenTimeOut = context.getAttribute("access_token_time_out");
		Object accessToken = context.getAttribute("access_token");
		Date now = new Date();
		long nowLong = now.getTime();
		if(accessTokenTimeOut!=null){
			long timeout = (Long)accessTokenTimeOut;
			boolean isNeedReGet = (nowLong-timeout)>90*60*1000;//如果小于90分钟 
			if(!isNeedReGet){
				return (String)accessToken;
			}
		}
		logger.info("update access_token ::: ...............................................");
		String str = HttpClientUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+this.AppID+"&secret="+this.AppSecret);
		JSONObject jb = (JSONObject)JSON.parse(str);
		if(jb.containsKey("access_token")){
			String access_token  = jb.getString("access_token");
			context.setAttribute("access_token", access_token);
			context.setAttribute("access_token_time_out", nowLong);
			return access_token;
		}
		try {
			Thread.currentThread();
			Thread.sleep(2000);
			getAccessToken();
			if(times==10){
				return null;
			}
			times++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果token 没有获取到重新获取
		return null;
	}


	public String getIP() {
		return IP;
	}
	
	
}
