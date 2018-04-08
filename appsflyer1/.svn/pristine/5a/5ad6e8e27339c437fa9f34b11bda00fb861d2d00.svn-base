package com.ami.weixin.course.schedule;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
@Service
public class MyTask1 extends Task{
	private static Logger logger = Logger.getLogger(MyTask1.class);
	private int timeout;  
	private static int i = 0;  
	//调度工厂实例化后，经过timeout时间开始执行调度  
	public void setTimeout(int timeout) {  
		this.timeout = timeout;  
	}  
	
	
	@Override
	public void execute() {
		String access_token = getAccessToken();
		warningService.setAccessToken(access_token);
		logger.info("the current ::access_token："+access_token);
		//监听日志
//			String addr = InetAddress.getLocalHost().getHostAddress();
		warningService.warning(watchingService.watchLog(),this.getIP()+"/simplet.do?method=serverDetail");

	}
	

}
