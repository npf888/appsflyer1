package com.ami.weixin.course.schedule;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import com.ami.weixin.course.util.Constant;
import com.ami.weixin.course.util.SomeUtils;
/**
 * 鐜板湪涓嶇敤杩欎釜锛屾敼涓虹敤MyTask1 鍜� MyTask2
 * @author JavaServer
 *
 */
public class Job2 extends QuartzJobBean {
	private static Logger logger = Logger.getLogger(Job1.class);
	private int timeout;  
	private static int i = 0;  
	//璋冨害宸ュ巶瀹炰緥鍖栧悗锛岀粡杩噒imeout鏃堕棿寮�濮嬫墽琛岃皟搴�  
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
	
		getAccessToken();
	}
	
	private void getAccessToken(){
		String str = HttpClientUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+AppID+"&secret="+AppSecret);
		JSONObject jb = (JSONObject)JSON.parse(str);
		if(jb.containsKey("access_token")){
			String access_token  = jb.getString("access_token");
			warningService.setAccessToken(access_token);
			logger.info("the current ::access_token锛�"+access_token);
			//鐩戝惉 鎵�鏈夎繙绋嬭闂�
			String rStr = handleGameServerRemoteUrl();
			String tStr = SomeUtils.removeSymbol(rStr);
			if(StringUtils.isNotBlank(tStr)){
				logger.info("------------------------------:::Job2:"+tStr);
				warningService.warning(tStr,"");
			}
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
		//濡傛灉token 娌℃湁鑾峰彇鍒伴噸鏂拌幏鍙�
		return;
		
	}

	public String handleGameServerRemoteUrl(){
		Map<String,String> gameServerUrlMap = watchingService.getUrlProperties().getGameServerUrlMap();
		StringBuilder sb = new StringBuilder();
		StringBuilder error = new StringBuilder();
		for(Entry<String,String> entry: gameServerUrlMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			try{
				HashMap<String,String> params = new HashMap<String,String>();
				params.put(Constant.active,Constant.view_all_online);
				String rStr = watchingService.handleGameServerRemoteUrl(value,params);
				if(StringUtils.isBlank(rStr)){
					continue;
				}
				logger.info("--- --- num all_online_num --------------------------------------------------:::rStr--"+rStr);
				boolean isNum = false;
				try{
					isNum = NumberUtils.isNumber(rStr);
					int num = NumberUtils.toInt(rStr);
					logger.info("--- --- num all_online_num --------------------------------------------------:::num--"+num);
				}catch(Exception e){
					e.printStackTrace();
				}
				logger.info("--- --- num all_online_num --------------------------------------------------:::isNum--"+isNum);
				if(isNum){
					logger.info("--- --- num all_online_num start:::"+rStr);
					int num = NumberUtils.toInt(rStr);
					logger.info("--- --- num all_online_num ent :::"+num);
					if(num >= Constant.waring_max_num){
						sb.append("鍦板尯锛�"+key+"--鏈嶅姟鍣�"+value+"璀﹀憡锛氭�讳汉鏁板凡缁忚揪鍒颁笂闄愶細"+num+"浜猴紒锛侊紒 璇峰Ε鍠勫鐞嗭紒锛侊紒");
					}
				}
			}catch(Exception e){
				try {
					logger.info("time machine area server:::"+new String(key.getBytes(),"UTF-8")+"--- url+port ::: "+value);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				continue;
			}
		}
		return sb.toString();
	}
}
