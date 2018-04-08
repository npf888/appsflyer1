package com.ami.weixin.course.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ami.common.HttpClientUtil;
import com.ami.weixin.course.dao.WatchingDao;
import com.ami.weixin.course.util.Constant;
import com.ami.weixin.course.util.SomeUtils;
import com.ami.weixin.course.util.UrlProperties;
/**
 * 被监视的 log日志
 * 此service 将要监视很多服务器的日志
 * @author 牛鹏飞
 *
 */
@Component
@Service
public class WatchingService {
	
	private static Logger logger = Logger.getLogger(WatchingService.class);
	@Autowired
	private CoreService coreService;
	@Autowired
	private WatchingDao watchingDao;

	//监听本机的日志
	public String watchLog() {
		String localInfo = watchingDao.watchLog();
		String backstageInfo = handleGETBackstageRemoteUrl();
		String gameServerInfo = handleGameServerRemoteUrl();
		String result = backstageInfo+gameServerInfo;
//		String result = " [新加坡-新增人数："+localInfo+"]"
//		  + " [土耳其::服务器异常]"
//		  + " [法国::服务器异常]"
//		  + " [游戏 服务器:土耳其-当前在线人数：0]"
//		  + " [游戏服务器:测试服务-当前在线人数：0]";
        String rStr = SomeUtils.removeSymbol(result);
		logger.info("------------------------------------------------:::"+rStr);
		return rStr;
	}
	
	private String handleGETBackstageRemoteUrl() {
		Map<String,String> backstageUrlMap = getUrlProperties().getBackstageUrlMap();
		StringBuilder sb = new StringBuilder();
		StringBuilder error = new StringBuilder();
		for(Entry<String,String> entry: backstageUrlMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			try{
				String rStr = handleGETBackstageRemoteUrl(value);
				JSONObject jb = (JSONObject)JSON.parse(rStr);
				String num = jb.getString("num");
				sb.append("[鍚庡彴:"+key+"-鏂板浜烘暟锛�"+num+"]");
			}catch(Exception e){
				try {
					logger.info("time machine area server:::"+new String(key.getBytes(),"UTF-8")+"--- url+port ::: "+value);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				error.append("[鍚庡彴:"+key+"::鏈嶅姟鍣ㄥ紓甯竇");
				e.printStackTrace();
				continue;
			}
		}
		if(StringUtils.isBlank(sb.toString())){
			return error.toString();
		}
		return sb.toString();
	}
	private String handleGETBackstageRemoteUrl(String url) {
		return HttpClientUtil.get(url);
	}
	/**
	 * 远程调用  后台服务器GET
	 * @param ss
	 * @param request
	 * @return
	 */
	public UrlProperties getUrlProperties(){
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		UrlProperties urlPropetiesMap = (UrlProperties)servletContext.getAttribute("urlProperties");
		return urlPropetiesMap;
	}
	public String handleGameServerRemoteUrl(){
		Map<String,String> gameServerUrlMap = getUrlProperties().getGameServerUrlMap();
		StringBuilder sb = new StringBuilder();
		StringBuilder error = new StringBuilder();
		for(Entry<String,String> entry: gameServerUrlMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			try{
				HashMap<String,String> params = new HashMap<String,String>();
				params.put(Constant.active,Constant.view_all_online);
				String rStr = handleGameServerRemoteUrl(value,params);
				sb.append("[娓告垙鏈嶅姟鍣�:"+key+"-褰撳墠鍦ㄧ嚎浜烘暟锛�"+rStr+"]");
			}catch(Exception e){
				try {
					logger.info("time machine area server:::"+new String(key.getBytes(),"UTF-8")+"--- url+port ::: "+value);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				error.append("[鍚庡彴:"+key+"-鏈嶅姟鍣ㄥ紓甯竇");
				e.printStackTrace();
				continue;
			}
		}
		if(StringUtils.isBlank(sb.toString())){
			return error.toString();
		}
		return sb.toString();
	}
	
	public String handleGameServerRemoteUrl(String url,HashMap<String,String> params){
		
		return HttpClientUtil.postUrl(url, params);
	}
	
	
}
