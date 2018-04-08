package com.ami.weixin.course.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ami.common.HttpClientUtil;
import com.ami.weixin.course.dao.CoreDao;
/**
 * 
 * 服务器报警的service
 * @author 牛鹏飞
 *
 */
@Component
@Service
public class WarningService {

	private static Logger logger = Logger.getLogger(WarningService.class);
	
	@Autowired
	private CoreDao coreDao;
	@Autowired
	private WatchingService WatchingService;
	
	private String accessToken = "";
	public void warning(String str,String url){
		if(StringUtils.isBlank(accessToken)){
			return;
		}
		//向微信公众号 发送消息的模板
		String weixinTemplateUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
		logger.info("weixin  the curr URL-weixin :::"+weixinTemplateUrl);
		List<HashMap<String,Object>> result = coreDao.getAllActiveOpenID();
		if(result != null && result.size()>0){
			for(HashMap<String,Object> single :result){
				String openId = (String)single.get("fromUserName");
				String normalParam = getTemplateJSONStr(openId,url,str);
				logger.info("weixin  before send message JSON content :::"+normalParam);
				String rParam = HttpClientUtil.postJSONUrl(weixinTemplateUrl, normalParam);		
				logger.info("weixin  after send message JSON content:::"+rParam);
			}
		}
		logger.info("weixin ::: end end end");
		
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	private String getTemplateJSONStr(String openId,String url,String jsonStr){
		/**
		 * {{first.DATA}}
			管理员编码：{{keyword1.DATA}}
			故障时间：{{keyword2.DATA}}
			可能原因：{{keyword3.DATA}}
			{{remark.DATA}}
		 */
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd ss:HH:mm");
		Date date = new Date();
		String now = df.format(new Date());
		Map<String,String> map = System.getenv();
		String IP = "";
		try {
			InetAddress address = InetAddress.getLocalHost();
			String sIP = address.getHostAddress();
			IP = sIP;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonParam = "{"
		    +"  \"touser\":\""+openId+"\","
		    +"  \"template_id\":\"U5wroDTUoxEr2FK5IQXDa7tMhaZrlLeMZ3cRzAnaQeQ\","
		    +"  \"url\":\""+url+"\", "
		    +"  \"topcolor\":\"#FF0000\", "
		    +"  \"data\":{ "
		       +"       \"first\": { "
		       +"       	\"value\":\"幽炎科技-,IP:"+IP+"\","
		       +"       	\"color\":\"#173177\" "
		       +"    	 }, "
			   +"  		\"keyword1\":{ "
		       +"        	\"value\":\"正式服务\", "
		       +"        	\"color\":\"#173177\" "
		       +"       }, "
		       +"       \"keyword2\": { "
		       +"      		\"value\":\" "+now+"\", "
		       +"     		\"color\":\"#173177\" "
		       +"       }, "
			   +"      \"keyword3\":{ "
		       +"           \"value\":\""+jsonStr+"\", "
		       +"     		\"color\":\"#173177\" "
		       +"       }, "
		       +"      \"remark\":{ "
		       +"           \"value\":\"请关注\","
		       +"           \"color\":\"#173177\" "
		       +"       }"
		       +"    } "
		       +" }";
		
		return jsonParam;
	}
	
	private String getNormalJSONStr(String content){
		
		String normalJSONStr = " { "
							   +"   \"filter\":{ "
							   +"      \"is_to_all\":false,"
							   +"      \"group_id\":\"1\" "
							   +"  }, "
							   +"  \"text\":{ "
							   +" 	 \"content\":\" "+content+"\" "
							   +"   }, "
							   +"  \"msgtype\":\"text\" "
							   +" } ";
		
		return normalJSONStr;
	}
	private String getNormalGroupJSONStr(String openIDS,String content){
		
		String normalJSONStr = " { "
				+"   \"touser\":[ "
				+ 		openIDS
				+"  ], "
				+"  \"text\":{ "
				+" 	 \"content\":\" "+content+"\" "
				+"   }, "
				+"  \"msgtype\":\"text\" "
				+" } ";
		
		return normalJSONStr;
	}
	
	
	
	
}
