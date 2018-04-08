package com.ami.weixin.course.action;

import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ami.api.web.action.BaseAction;
import com.ami.weixin.course.service.CoreService;
import com.ami.weixin.course.service.WarningService;
import com.ami.weixin.course.util.SignUtil;

/**
 * 
 * 1、微信公众号消息处理的总入口 （有且只有这一个入口）
 * @author 牛鹏飞
 *
 */
@Controller
public class CoreAction extends BaseAction{
	
	@Autowired
	private CoreService coreService;
	
	
	@Autowired
	private WarningService warningService;

	public  Logger logger = Logger.getLogger(CoreAction.class);
	
	/**
	 * 微信公众号消息处理的总入口 （有且只有这一个入口）
	 */
	 @Override
	public String execute(){
		 try{
			String method = this.getReq().getMethod();
			//确认请求来自微信服务器 
			 if(method.equals("GET")){//注意全部大写
	
				 // 微信加密签名  
			        String signature = this.getReq().getParameter("signature");  
			     // 时间戳  
			        String timestamp = this.getReq().getParameter("timestamp");  
			        // 随机数  
			        String nonce = this.getReq().getParameter("nonce");  
			        // 随机字符串  
			        String echostr = this.getReq().getParameter("echostr");  
			  
			        logger.info("the echostr param :"+echostr);
			        PrintWriter out = this.getRes().getWriter();  
			        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
			        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
			            out.print(echostr);  
			            logger.info("is pass锛歜ack:signature"+signature+",timestamp"+timestamp+",nonce"+nonce+",echostr:"+echostr);
			            out.close();  
				        out = null;  
				        return null;
			        }  
			        out.print(echostr);  
			        logger.info("is pass too : back:signature"+signature+",timestamp"+timestamp+",nonce"+nonce+",echostr:"+echostr);
			        out.close();  
			        out = null;  
			 }else{//POST
				 this.getReq().setCharacterEncoding("UTF-8");  
				 this.getRes().setCharacterEncoding("UTF-8"); 
				 // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
				 logger.info("user     post     in........");
				// 调用核心业务类接收消息、处理消息  
		         String respMessage = coreService.processRequest(this.getReq());  
		         // 响应消息  
		         PrintWriter out = this.getRes().getWriter();  
		         out.print(respMessage);  
		         out.close();
		             
			 }
			 logger.info("====");
			 System.out.println("-----------------");
		 }catch(Exception e){
			e.printStackTrace();
		 }
		 return null;
	 }
	 
	
}
