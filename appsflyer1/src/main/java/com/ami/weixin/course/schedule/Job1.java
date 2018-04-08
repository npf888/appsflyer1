package com.ami.weixin.course.schedule;


import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ami.common.HttpClientUtil;
import com.ami.weixin.course.service.WarningService;
import com.ami.weixin.course.service.WatchingService;
/**
 * 现在不用这个，改为用MyTask1 和 MyTask2
 * @author JavaServer
 *
 */
public class Job1 extends QuartzJobBean {
	private static Logger logger = Logger.getLogger(Job1.class);
	private int timeout;  
	private static int i = 0;  
	//调度工厂实例化后，经过timeout时间开始执行调度  
	public void setTimeout(int timeout) {  
		this.timeout = timeout;  
	}  
	
	private String AppID = "wx8f98ce73361b03fc";
	private String AppSecret = "4a044704faa08f5910488fa4c2705cd8";
	
	private int times = 0;
	@Autowired
	private WarningService warningService;
	
	@Autowired
	private WatchingService watchingService;
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("---------------------------------------------------------------------------------------------------------------------11111");
		getAccessToken();
	}
	
	private void getAccessToken(){
		String str = HttpClientUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+AppID+"&secret="+AppSecret);
		JSONObject jb = (JSONObject)JSON.parse(str);
		if(jb.containsKey("access_token")){
			String access_token  = jb.getString("access_token");
			warningService.setAccessToken(access_token);
			logger.info("the current ::access_token："+access_token);
			//监听日志
			warningService.warning(watchingService.watchLog(),"");
			times=0;
			return;
		}
		try {
			Thread.currentThread();
			Thread.sleep(2000);
			getAccessToken();
			if(times==10){
				return;
			}
			times++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果token 没有获取到重新获取
		return;
		
	}


}
