package com.ami.weixin.course.schedule;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ami.weixin.course.dao.CoreDao;
import com.ami.weixin.course.util.Constant;
import com.ami.weixin.course.util.SomeUtils;
@Service
public class MyTask2  extends Task{

	private  static Logger logger = Logger.getLogger(MyTask2.class);
	
	@Autowired
	private CoreDao coreDao;
	
	@Override
	public void execute() {
		String access_token = getAccessToken();
		warningService.setAccessToken(access_token);
		logger.info("the current ::access_token锛�"+access_token);
		//鐩戝惉 鎵�鏈夎繙绋嬭闂�
		String rStr = handleGameServerRemoteUrl();
		if(StringUtils.isNotBlank(rStr)){
			logger.info("------------------------------:::Job2:"+rStr);
			warningService.warning(rStr,this.getIP()+"/simplet.do?method=serverMaxDetail");
		}

	}

	public String handleGameServerRemoteUrl(){
		Map<String,String> gameServerUrlMap = watchingService.getUrlProperties().getGameServerUrlMap();
		StringBuilder sb = new StringBuilder();
		Map<String,Integer> areaNum = new HashMap<String,Integer>();
		for(Entry<String,String> entry: gameServerUrlMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			try{
				HashMap<String,String> params = new HashMap<String,String>();
				params.put(Constant.active,Constant.view_all_online);
				String rStr = watchingService.handleGameServerRemoteUrl(value,params);
				logger.info("--- --- update number --------------------------------------------------:::rStr--"+rStr);
				if(StringUtils.isBlank(rStr)){
					continue;
				}
				rStr = SomeUtils.removeSymbol(rStr);
				int num = NumberUtils.toInt(rStr);
				//瀛樺偍姣忔瀹氭椂浠诲姟鐨勬椂闂村拰鍦ㄧ嚎浜烘暟 鐢ㄤ簬鍥惧舰鍖栧睍绀�
				areaNum.put(key, num);
				//瀹氭椂鎻愰啋
				if(num >= Constant.waring_max_num){
					sb.append("地区："+key+"警告：总人数已经达到："+num+"人！！！ 请妥善处理！！！");
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
		for(Entry<String,Integer> single:areaNum.entrySet()){
			String key = single.getKey();
			Integer num = single.getValue();
			coreDao.insertTimeNumber(key,num);
		}
		return sb.toString();
	}

}
