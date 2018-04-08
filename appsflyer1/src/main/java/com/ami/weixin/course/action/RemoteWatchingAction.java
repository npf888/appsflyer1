package com.ami.weixin.course.action;

import java.io.PrintWriter;
import java.net.InetAddress;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ami.api.web.action.BaseAction;
import com.ami.weixin.course.service.RemoteWatchingService;
/**
 * 1、此接口 需要被  CoreAction（微信入口） 调用，因为 会有多台 后台服务器（每台服务器分别对应不同的国家，一个服务器负责一个国家）
 * @author JavaServer
 *
 */
@Scope("prototype")
@Component
public class RemoteWatchingAction extends BaseAction{
	
	
	@Autowired
	private RemoteWatchingService remoteWatchingService;
	
	private static Logger logger = Logger.getLogger(RemoteWatchingAction.class);
	@Override
	public String execute() {
		 try{
			 String method = this.getReq().getMethod();
			 //确认请求来自微信服务器 
			 if(method.equals("GET")){//注意全部大写
	
			        PrintWriter out = this.getRes().getWriter();  
			        String str = remoteWatchingService.offerInfo();
			        out.print(str);  
			        InetAddress netAddress = null;
			        try {
			        	netAddress = InetAddress.getLocalHost();
			        	logger.info("host ip:" + netAddress.getHostAddress()+"--host name:" + netAddress.getHostName()+"  back str ::"+str);
					} catch (Exception e) {
						logger.error(e);
					}
			        out.close();  
			        out = null;  
			 }else{//用于交互（例如充值）
				 this.getReq().setCharacterEncoding("UTF-8");  
				 this.getRes().setCharacterEncoding("UTF-8"); 
				 String respMessage = remoteWatchingService.distribute(this.getReq());
				// 响应消息  
		         PrintWriter out = this.getRes().getWriter();  
		         out.print(respMessage);  
		         out.close();
			 }
		 }catch(Exception e){
			e.printStackTrace();
		 }
		 return null;
	 }
	
	 
}
